function registraRepresentado() {
    var nombre = $("#nombre").val();
    var paterno = $("#paterno").val();
    var materno = $("#materno").val();
    var claves = $("#hddCveMunicipio").val().split(',');
    var correo = $("#txtcorreo").val();
    var clave = $("#hddCveMunicipio").val();
    $("#room-id").val(claves[1]);
    var enviar = false;


    if (nombre !== "" && paterno !== "" & clave !== "0" && correo !== "")
        enviar = true;
    var r = new Object();
    var m = new Object();

    m.idMunicipio = claves[0];

    r.nombre = nombre;
    r.paterno = paterno;
    r.materno = materno;
    r.correo = correo;
    r.municipio = m;

    if (enviar)
        $.ajax({
            url: "registrarrepresentadochat",
            type: 'POST',
            async: false,
            data: JSON.stringify(r),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdRepresentado").val(response.representado.idRepresentado);
                $("#hddIdChat").val(response.idChat);
            }, error: function (xhr, ajaxOptions, thrownError) {
                enviar = False;
            }
        });
    else {
        bootbox.dialog({
            closeButton: true,
            message: "Error Todos los campos son requeridos",
            title: "Error",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-danger"
                }
            }
        });

    }
    return enviar;
}
;




$(document).ready(function () {

//    var savedName = Cookies.get('name');
//    if (savedName) {
//        nameInput.val(savedName);
//    }
//
//    var savedRoom = Cookies.get('roomId');
//    if (savedRoom) {
//        roomInput.val(savedRoom);
//    }
    usernamePage.classList.remove('hidden');
    usernameForm.addEventListener('click', connect, true);
    messageForm.addEventListener('submit', sendMessage, true);


});

