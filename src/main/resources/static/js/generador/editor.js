$(document).ready(function () {
    $('.form_date').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY'
    });

    var action = null, sm = false, vp = false;
    $.xmlHash = null;
    $.xml = null
    $.id = null;

//

//    var actionFirmar = function (data) {
//console.log("actionFirmar"+data.estatus);
//        if (data.estatus === "OK") {
//            $.xmlHash = data.campos.xmlEncrip;
//            $.xml = data.campos.xml;
//            $.id = data.campos.id;
//
//            $('#form-firma').submit();
//        } else {
//            $.xmlHash = null;
//        }
//    };


    var sign = function (signature) {

        var postData = new Object();
        postData.id = $.id;
        postData.signature = signature;
        postData.xml = $.xml;


        $.ajax({
            url: "/generador/editor/firmar",
            type: 'POST',
            data: JSON.stringify(postData),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                if (response.estatus === 'OK') {
                    mostrarLinks(response);
                } else {
                    $('#btn-form-firma').empty().append('Firmar').prop('disabled', false);
                    bootbox.dialog({
                        closeButton: true,
                        message: response.respuesta,
                        title: "Error",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-danger"
                            }
                        }
                    });
                }
            }
        });
    };



    var mostrarLinks = function (response) {
        $('#btn-form-firma').empty().append('Firmar').prop('disabled', false);
        var l = "data:application/cms;base64," + $.base64.decode(response.campos.cms);
        var link = document.createElement("a");
        link.href = l;
        link.download = response.campos.nombre;
        link.target = "blank";
        document.body.appendChild(link);
        link.click();

        document.body.removeChild(link);
        var body = "<p><b>Los documentos fueron generados con éxito, al archivo CMS se descargara automáticamente.</b></p>\n\
                    <p>Para descargar los archivos manualmente haga clic en los siguientes enlaces:</p>\n\
                    <p><a href=\"" + l + ".cms\" download=\"" + response.campos.nombre + ".cms\" target=\"asdasdasd_BLANK\">" + response.campos.nombre + ".cms</a></p>\n\
                    <p><a href=\"" + response.campos.pdf + "\\" + response.campos.nombre + ".pdf\" target=\"_BLANK\">" + response.campos.nombre + ".pdf</a></p>";
        $('#firma-modal').modal('hide');
        bootbox.dialog({
            closeButton: false,
            message: body,
            title: "Mensaje",
            buttons: {
                success: {
                    label: "Cerrar",
                    className: "btn-default",
                    callback: function () {
                        history.back();
                    }
                }
            }
        });
    };



    $('#cancelar-asunto').on('click', function (e) {
        e.preventDefault();
        history.back();
    });

//    $('#documento-form').validate({
//        ignore: "",
//        highlight: function (element) {
//            console.log("element =>"+element);
//            $(element).closest('.form-group').addClass('has-error');
//        },
//        unhighlight: function (element) {
//            $(element).closest('.form-group').removeClass('has-error');
//        },
//        errorElement: 'span',
//        errorClass: 'help-block',
//        errorPlacement: function (error, element) {
//            element.closest('.control').after(error);
//        },
//        submitHandler: function (form) {
//            console.log("validando documento-forms2"+form.toString);
//
//            var postData = $(form).serializeObject();
//            console.log("postData =>"+ postData.toString());
//
//            $.ajax({
//                url: $(form).attr('action'),
//                type: 'POST',
//                data: JSON.stringify(postData), //Nota:Aqui se envia la información del formulario
//                dataType: 'json',
//                contentType: 'application/json',
//                beforeSend: function (xhr) {
//                    console.log("antes de ...");
//                    if (sm) {
//                        waitingDialog.show('Procesando la información del documento', {progressType: 'success'});
//                        sm = false;
//                    }
//                    if (vp) {
//                        $('#messages').append('<p><b><span class="fa fa-spinner fa-spin fa-fw"></span> Generando vista previa</b></p>');
//                        vp = false;
//                    }
//                },
//                success: function (data) {
//                    console.log("respuesta exitosa ejecutando action "+data);
//                    action(data);
//                },
//                error: function (jqXHR, textStatus, errorThrown) {
//
//                    $('#messages').empty();
//                    $('#btn-form-firma').empty().append('Firmar').prop('disabled', false);
//                    bootbox.dialog({
//                        closeButton: false,
//                        message: textStatus,
//                        title: "Error",
//                        buttons: {
//                            success: {
//                                label: "Aceptar",
//                                className: "btn-danger"
//                            }
//                        }
//                    });
//                }
//            });
//        }
//    });
//
//
//    $('#form-firma').validate({
//        ignore: "",
//        highlight: function (element) {
//            $(element).closest('.form-group').addClass('has-error');
//        },
//        unhighlight: function (element) {
//            $(element).closest('.form-group').removeClass('has-error');
//        },
//        errorElement: 'span',
//        errorClass: 'help-block',
//        errorPlacement: function (error, element) {
//            element.closest('.control').after(error);
//        },
//        submitHandler: function (form) {
//            console.log("validando form-firma");
//            var password = $("#password").val();
//            var curp = $.curp;
//
//            var s = signData(curp, password, sign);
//
//            if (s.invalid) {
//                $('#btn-form-firma').empty().append('Firmar').prop('disabled', false);
//                bootbox.dialog({
//                    closeButton: true,
//                    message: s.message,
//                    title: "Error",
//                    buttons: {
//                        success: {
//                            label: "Aceptar",
//                            className: "btn-danger"
//                        }
//                    }
//                });
//            }else{
//                console.log("no es valido s");
//            }
//        }
//    });

    $('#guardar-btn').on('click', function (e) {
        e.preventDefault();
        sm = true;
        $('#documento-form').prop('action', $.guardar);
        $('#documento-form').submit();
        action = actionGuardar;
    });

    $('#firma-btn').on('click', function (e) {
        e.preventDefault();
        $('#firma-modal').modal('show');
        $('#documento-form').prop('action', $.generaXML);
    });
    $('#vista-btn').on('click', function (e) {
        e.preventDefault();
        vp = true;

        $('#documento-form').prop('action', $.vista);
        $('#documento-form').submit();
        action = actionVista;
    });

    $('#btn-form-firma').on('click', function (e) {
        console.log("boton firmar");
        e.preventDefault();
        $('#btn-form-firma').empty().append('<span class="fa fa-spinner fa-spin fa-fw"></span> Firmando').prop('disabled', true);
       // $('#documento-form').submit();
       obtenerLista();
//        action = actionFirmar;
    });
});


