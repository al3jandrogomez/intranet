
// ATENDEMOS A UNA PERSONA
var buscarRepresentados = true;

function atiendePersona() {
    var r = new Object();
    r.id = $("#hddCveRegion").val();
    $.ajax({
        url: "atenderpersona",
        type: 'POST',
        async: false,
        data: JSON.stringify(r),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            if (response.activo === "A") {
                buscarRepresentados = false;
                while (messageArea.firstChild) {
                    messageArea.removeChild(messageArea.firstChild);
                }
                var nombre = response.personal.nombre + " " + response.personal.paterno + " " + response.personal.materno;
                console.log("nombre=" + nombre),
                        nameInput.val(nombre);
                var room = $("#hddCveRegion").val();
                roomInput.val(response.idChat);
                $("#hddIdChat").val(response.idChat);
                chat.val(response.idChat);
                console.log("nameInput" + nameInput.val());
                console.log("roomInput" + roomInput.val());
                connect();
            } else {

            }








        }, error: function (xhr, ajaxOptions, thrownError) {

        }
    });


}
;


function consultarUsuario() {

    $.ajax({
        url: "buscarusuariologueado",
        type: 'POST',
        async: false,
//        data: JSON.stringify(r),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            $.each(response, function (index, element) {
                username = element.nombre;
                $("#hddCveUsuario").val(element.cveUsuario);
                $("#hddCveRegion").val(element.idRegion);
            });
        }, error: function (xhr, ajaxOptions, thrownError) {

        }
    });


}
;

function cargarListaRepresentadosChat() {
    var c = new Object();
    c.cveRegion = $("#hddCveRegion").val();
    c.activo = "E";
    $.ajax({
        url: "listarepresentadoschat",
        type: 'POST',
        async: false,
        data: JSON.stringify(c),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            $("#listaChatPendiente").empty();
            var nombre = "";
            var boton = "";
            $.each(response, function (index, element) {

                nombre += "<div  class='persona_espera'  id='" + element.idChat + "'>" + element.representado.nombre + " " + element.representado.paterno + " " + element.representado.materno + "</div>";
            });
            $("#listaChatPendiente").append(nombre);
        }, error: function (xhr, ajaxOptions, thrownError) {

        }
    });

}
;
//ASESORIAS SIN TERMINAR 
function cargarListaRepresentadosActivosChat() {
    var c = new Object();
    var p = new Object();
    p.cveUsuario = $("#hddCveUsuario").val();
    c.cveRegion = $("#hddCveRegion").val();
    c.activo = "A";
    c.personal = p;


    $.ajax({
        url: "listarepresentadoschat",
        type: 'POST',
        async: false,
        data: JSON.stringify(c),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            var nombre = "";
            var boton = "";
            if (response.length == 0)
                $("#atender").prop("disabled", false);
            $("#terminar").prop("disabled", true);
            $("#listaChatActivas").empty();
            $.each(response, function (index, element) {
                $("#atender").prop("disabled", true);
                $("#terminar").prop("disabled", false);
                nombre += "<div  class='persona_espera'  id='" + element.idChat + "'>" + element.representado.nombre + " " + element.representado.paterno + " " + element.representado.materno + "</div>";

            });
            $("#listaChatActivas").append(nombre);
        }, error: function (xhr, ajaxOptions, thrownError) {

        }
    });

}
;
//ELEGIR SOLO UNA ASESORIA PARA TERMINAR
cargarAsesoriaPendiente = function () {
    var c = new Object();
     var p = new Object();
    p.cveUsuario = $("#hddCveUsuario").val();
    var idChat = $("#hddIdChat").val();
    c.cveRegion = $("#hddCveRegion").val();
    c.activo = "A";
    c.idChat = idChat == 0 ? null : idChat;
    c.personal = p;
    $.ajax({
        url: "listarepresentadoschat",
        type: 'POST',
        async: false,
        data: JSON.stringify(c),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {

            if (response.length > 0) {
                $("#terminaAsesoria").modal("show");
                $("#hddIdChat").val(response[0].idChat);

            }

        }, error: function (xhr, ajaxOptions, thrownError) {

        }
    });

};
// FINALIZAR ASESORIA
terminarAsesoria = function () {
    var chat = new Object();
    chat.idChat = $("#hddIdChat").val();

    chat.observaciones = $("#observaciones").val();
    $.ajax({
        url: "terminarasesoria",
        type: 'POST',
        async: false,
        data: JSON.stringify(chat),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {

            buscarRepresentados = true;
            $("#terminaAsesoria").modal("hide");
            $("#hddIdChat").val(0);
            chatPage.classList.add('hidden');


            consultarUsuario();
            cargarListaRepresentadosChat();
            cargarListaRepresentadosActivosChat();

        }, error: function (xhr, ajaxOptions, thrownError) {

        }
    });

};
// CONSULTAR LA LISTA CADA MINUTO
consultaRepresentadosRecursiva = function () {
    if (buscarRepresentados) {
        setInterval(cargarListaRepresentadosChat, 60000);
        setInterval(cargarListaRepresentadosActivosChat, 60000);
    }
};

$(document).ready(function () {
    consultarUsuario();
    cargarListaRepresentadosChat();
    consultaRepresentadosRecursiva();
    cargarListaRepresentadosActivosChat();




});
