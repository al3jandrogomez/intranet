$(document).ready(function () {
    $("#certificado").change(handleParsingCertFile);
    $("#llave").change(handleParsingPrivKeyFile);
});

function signatureError(error) {
}


var privateKeyBuffer = new ArrayBuffer(0); // ArrayBuffer with loaded private key
var certificateBuffer = new ArrayBuffer(0); // ArrayBuffer with loaded certificate

function handleParsingPrivKeyFile(evt) {
    var temp_reader = new FileReader();

    var current_files = evt.target.files;

    temp_reader.onload =
            function (event)
            {
                privateKeyBuffer = event.target.result;
            };

    temp_reader.readAsArrayBuffer(current_files[0]);
}

function handleParsingCertFile(evt) {
    var temp_reader = new FileReader();

    var current_files = evt.target.files;

    temp_reader.onload =
            function (event)
            {
                certificateBuffer = event.target.result;
            };

    temp_reader.readAsArrayBuffer(current_files[0]);
}

function signData(curp, password, sendSignResult) {
    var r = isWCAPISupported();
    if (r.invalid) {
        return r;
    }

    var certX509;
    var certificateBufferString = arrayBufferToString(certificateBuffer);
    var pCert = certificateBufferString.replace(/(-----(BEGIN|END) CERTIFICATE-----|\r\n)/g, '');

    if (pCert.charAt(0) === "M") {
        certX509 = window.atob(pCert);
    } else {
        certX509 = certificateBufferString;
    }


    var cipheredKey;
    var privateKeyBufferString = arrayBufferToString(privateKeyBuffer);
    var pKey = privateKeyBufferString.replace(/(-----(BEGIN|END) PRIVATE KEY-----|\r\n)/g, '');

    if (pKey.charAt(0) === "M") {
        cipheredKey = window.atob(pKey);
    } else {
        cipheredKey = privateKeyBufferString;
    }
    
    validateKeysAndSign(certX509, cipheredKey, curp, password, sendSignResult);
    return r;
}

//End of signData 


function isWCAPISupported() {
    var crypto;
    var cryptoSubtle;
    var r = {
        invalid: false,
        message: ""
    };

    if (window.msCrypto) { // IE
        crypto = window.msCrypto;
    } else if (window.crypto) {  // all others
        crypto = window.crypto;
    } else {
        r.message = "<center>" +
                "<span style='font-weight:bold'>Tu Navegador no soporta Web Cryptography API! Esta pagina no funcionará. [No Crypto Namespace]" +
                "</center>";
        r.invalid = true;
        return r;
    }
    if (!window.Promise) {
        r.message = "<center>" +
                "<span style='font-weight:bold'>Tu Navegador no soporta \"Promises\"! Esta pagina no funcionará" +
                "</center>";
        r.invalid = true;
        return r;
    }
    // get crypto.subtle
    if (crypto.webkitSubtle) {  // Safari
        cryptoSubtle = crypto.webkitSubtle;
    } else if (crypto.subtle) { // all others
        cryptoSubtle = crypto.subtle;
    } else {
        r.message = "<center>" +
                "<span style='font-weight:bold'>Tu Navegador no soporta Web Cryptography API! Esta pagina no funcionará. [No Crypto Namespace]" +
                "</center>";
        r.invalid = true;
        return r;
    }

    return r;
}

function validateKeysAndSign(certX509, privKey, cuts, password, sendSignResult) {
    try {

        var cerAsn1 = forge.asn1.fromDer(certX509);
        var x509cer = forge.pki.certificateFromAsn1(cerAsn1);
        var attributes = x509cer.subject.attributes;
        var attr;
        var serialName;
        for (var x in attributes) {
            attr = attributes[x];
            if (attr.name !== undefined && attr.name === 'serialName') {
                serialName = attr.value;
            }
        }
        if (serialName === null) {
            return false;
        }
        serialName = serialName.replace("/", "").trim();
        if (serialName === cuts) {
console.log("privKey ="+privKey+" password="+password);
            SDSgLib_openPKCS8PrivateKey(privKey, password).then(function (pkcs8) {
                if (pkcs8[0x00] != '0') {
                    $('#firma-modal').modal('hide');
                    bootbox.dialog({
                        closeButton: false,
                        message: "La llave privada esta corrupta o la contraseña es incorrecta uno",
                        title: "Error",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-success",
                                callback: function () {
                                    $('#firma-modal').modal('show');
                                }
                            }
                        }
                    });
                    return false;
                }
                var pkeyAsn1 = forge.asn1.fromDer(pkcs8);
                var privateKey = forge.pki.privateKeyFromAsn1(pkeyAsn1);
                if (_bnToHex(x509cer.publicKey.e) === _bnToHex(privateKey.e) && _bnToHex(x509cer.publicKey.n) === _bnToHex(privateKey.n)) {
                    var hashAlgorithm = parseInt(2);
                    signAndSend(password, privKey, certX509, hashAlgorithm, sendSignResult);
                } else {
                    $('#firma-modal').modal('hide');
                    bootbox.dialog({
                        closeButton: false,
                        message: "El certificado no corresponde con la llave privada",
                        title: "Atencion",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-success",
                                callback: function () {
                                    $('#firma-modal').modal('show');
                                }
                            }
                        }
                    });
                    false;
                }
            }, function (error) {
                $('#firma-modal').modal('hide');
                bootbox.dialog({
                    closeButton: false,
                    message: "La llave privada esta corrupta o la contraseña es incorrecta 2",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-success",
                            callback: function () {
                                $('#firma-modal').modal('show');
                            }
                        }
                    }
                });
                return false;
            });
        } else {
            $('#firma-modal').modal('hide');
            bootbox.dialog({
                closeButton: false,
                message: "El certificado no corresponde con el usuario",
                title: "Atencion",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-success",
                        callback: function () {
                            $('#firma-modal').modal('show');
                        }
                    }
                }
            });
            return false;
        }
    } catch (err) {
        
        /*signatureError("Error FSI001 - SgDataCrypto Error:\n " + err.message + "\n" + err.stack);*/
        $('#firma-modal').modal('hide');
        bootbox.dialog({
            closeButton: false,
            message: "Ocurrio un error durante la Firma del Documento.\n Favor de reportarlo!!!",
            title: "Error FSI001",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-success",
                    callback: function () {
                        $('#firma-modal').modal('show');
                        //history.back();
                    }
                }
            }
        });
        return false;
    }
}

function resetModalFirma() {
    var parent = $("#certificate_file").parent();
    var content = $(parent).html();
    $(parent).html(content);

    parent = $("#privkey_file").parent();
    content = $(parent).html();
    $(parent).html(content);
    $("#password").val("");
    $("#tokenTOTP").val("");

    $("#certificate_file").change(handleParsingCertFile);
    $("#privkey_file").change(handleParsingPrivKeyFile);
}

function _bnToHex(b) {
    // prepend 0x00 if first byte >= 0x80
    var hex = b.toString(16);
    if (hex[0] >= '8') {
        hex = '00' + hex;
    }
    return hex;
}

function generateDigest(bytes) {
    var md = forge.md.sha1.create();

    md.start();
    md.update(bytes);
    var digest = md.digest();
    var hex = digest.toHex();
    return hex;
}

function signAndSend(password, cipheredKey, certX509, hashAlgorithm, sendSignResult) {
    try {
        // Signing hash
        var signPromise = pkcs7FromHash(password, cipheredKey, certX509, hashAlgorithm, $.xmlHash);

        signPromise.then(function (Signature) {          
            var signature = btoa(arrayBufferToString(Signature), false, true);           
            sendSignResult(signature);
        }, function (error) {
            $("#signature").val("");
            if (error.message.indexOf("Error while decrypting private key") >= 0) {
                $('#firma-modal').modal('hide');
                bootbox.dialog({
                    closeButton: false,
                    message: "La llave privada esta corrupta o la contraseña es incorrecta 3",
                    title: "Atencion",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-success",
                            callback: function () {
                                $('#firma-modal').modal('show');
                                //history.back();
                            }
                        }
                    }
                });
                return false;
            } else {
                $('#firma-modal').modal('hide');
                bootbox.dialog({
                    closeButton: false,
                    message: "Ocurrio un error durante la Firma del Documento.\n Favor de reportarlo!!!",
                    title: "Error FSI002",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-success",
                            callback: function () {
                                $('#firma-modal').modal('show');
                                //history.back();
                            }
                        }
                    }
                });
                return false;
            }
        });
    } catch (err) {
        $('#firma-modal').modal('hide');
        bootbox.dialog({
            closeButton: false,
            message: "Ocurrio un error durante la Firma del Documento.\n Favor de reportarlo!!!",
            title: "Error FSI001",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-success",
                    callback: function () {
                        $('#firma-modal').modal('show');
                        //history.back();
                    }
                }
            }
        });
        return false;
    }
}
