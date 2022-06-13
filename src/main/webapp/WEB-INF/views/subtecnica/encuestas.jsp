<style>
    .tituloEncuesta{
        font-size: 25px;
        font-weight: bold;
    }
</style>

<input type="hidden" id="hddIdEncuesta" value="0">
<input type="hidden" id="hddIdPregunta" value="0">
<input type="hidden" id="hddIdRespuesta" value="0">
<input type="hidden" id="hddIdAplicacion" value="0">


<div class="form-group row">
    <h1>Registro de Encuestas</h1>


</div>
<div id="frmEncuestas">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-10" id="listaEncuestas">
        <div class="col-10">
            <button type="button" class="btn btn-primary btn-sm btn-space" onclick="nuevaEncuesta()">Registrar nueva
                encuesta</button>
        </div>

        <div class="col-10">
            <div class="listaRegEncuesta" id="listaRegEncuesta"></div>
        </div>
    </div>
    <div class="  form-group row btn-toolbar col-10" id="regEncuestas" style="display: none;">

        <button type="button" class="btn btn-primary btn-sm btn-space" onclick="actualizarEncuesta()">Guardar</button>


        <div class="col-10">
            <button type="button" class="btn btn-warning btn-sm btn-space" onclick="salirEncuesta()">Salir</button>
        </div>

        <div class="col-10">
            <label for="example-search-input" class="btn btn-secondary"><input class="form-control " type="text"
                    value="" id="txtDescEncuesta">Nombre de la encuesta:</label>
            <label class="btn btn-secondary "><input class="form-control publicar" name="publicar" type="radio"
                    value="S">Publicar en lista de encuesta:</label>
            <label class="btn btn-secondary "><input class="form-control publicar" name="publicar" type="radio"
                    value="I">Publicar pagina Principal:</label>
            <label class="btn btn-secondary "><input class="form-control publicar" name="publicar" type="radio"
                    value="N">Sin publicar:</label>
        </div>
        <div class="col-10">
            <label for="example-search-input" class="btn btn-secondary"><input class="form-control " type="text"
                    value="" id="txtDescPregunta">Pregunta:</label>

            <button type="button" class="btn btn-danger btn-space " onclick="agregarPregunta()">Agregar
                Pregunta</button>

        </div>
        <div class="col-10">

            <div class="" id="listaPreguntas"></div>
        </div>

    </div>

    <div id="testEncuestas" style="display: none;">
        <div class="col-10">

            <div class="" id="listaPreguntas2">hola</div>
        </div>

    </div>

    <div class="  form-group row btn-toolbar col-10 estadistica" style="display: none;">
        <div class="form-group col-10">
            <label for="example-search-input" class="col-10 col-form-label tituloEncuesta"></label>


        </div>

        <div class="form-group col-5">
            <label for="example-search-input" class="col-10 col-form-label">Fecha Inicio:</label>
            <div class="col-10">
                <input class="form-control datepicker" type="text" value="" id="txtFechaRegistro">
            </div>
            <label for="example-search-input" class="col-10 col-form-label">Fecha Termino:</label>
            <div class="col-10">
                <input class="form-control datepicker" type="text" value="" id="txtFechaActualizacion">
            </div>
            <div class="col-10">
                <button type="button" class="btn btn-warning btn-sm btn-space" onClick="estadistica()">Buscar</button>
            </div>

        </div>


    </div>
    <div class="  form-group row btn-toolbar col-10" id="estadistica" style="display: none;">

    </div>

    <div class="modal fade" id="myModalRespuesta" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="modalCorrect">
                    <label for="example-search-input" class="btn btn-secondary"><input class="form-control " type="text"
                            value="" id="txtDescRespuesta">Respuesta:</label>
                    <select id="cargaTipoRespuesta"></select>
                    <button type="button" class="btn btn-danger btn-space " onclick="agregarRespuesta()">Agregar
                        Respuesta</button>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

                </div>
            </div>
        </div>
    </div>


</div>

<div class="dropdown-divider"></div>

<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script>



    terminarEncuesta = function () {


        var idAplicacionEncuesta = $("#hddIdAplicacion").val();
        console.log("terminar encuesta" + preguntas.toString());
        var listaPreguntas = [];

        var guardar = true;
        $(".respuestas").each(function (index) {

            if ($(this).prop("checked")) {
                var preguntasrespuestas = new Object();
                var preguntas2 = preguntas.find(x => x.nombre === $(this).prop("name"));
                preguntas2.contestada = "S";
                var pregunta = new Object();
                var respuesta = new Object();
                pregunta.idPregunta = preguntas2.idPregunta;
                preguntasrespuestas.pregunta = pregunta;
                respuesta.idRespuesta = $(this).val();
                preguntasrespuestas.respuesta = respuesta;
                var aplicacionEncuesta = new Object();
                aplicacionEncuesta.idAplicacionEncuesta = idAplicacionEncuesta;
                preguntasrespuestas.aplicacion = aplicacionEncuesta;
                listaPreguntas.push(preguntasrespuestas);
                console.log($(this).prop("name") + "<==>" + $(this).prop("checked") + "<==>" + $(this).val());
            }
        });

        $.each(preguntas, function (index, element) {
            if (element.contestada == "N") {
                var nombre = $("label[name=" + element.nombre + "]").prop("name");
                console.log(nombre);
                guardar = false;
            }
        });
        guardar = false;

        console
        if (guardar) {
            $.ajax({
                url: "guardarrespuestasencuesta",
                type: 'POST',
                data: JSON.stringify(listaPreguntas),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    salirEncuesta();
                    $("#hddIdAplicacion").val(response.idAplicacionEncuesta);
                    bootbox.dialog({
                        closeButton: true,
                        message: "Encuesta enviada, gracias.",
                        title: "Enviada",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-success"
                            }
                        }
                    });


                }, error: function (xhr, ajaxOptions, thrownError) {
                    bootbox.dialog({
                        closeButton: true,
                        message: "Error cargar los tipos de preguntas",
                        title: "Error",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-danger"
                            }
                        }
                    });
                }
            });
            console.log("todas las preguntas fueron contestadas");
        } else
            console.log("faltan preguntas por responder");
    };

    aplicacionEncuesta = function (idEncuesta) {
        console.log("id encuesta aplicacionEncuesta" + idEncuesta);
        var aplicacion = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = idEncuesta;
        aplicacion.encuesta = encuesta;
        $.ajax({
            url: "guardaraplicacion",
            type: 'POST',
            data: JSON.stringify(aplicacion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdAplicacion").val(response.idAplicacionEncuesta);



            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al generar la encuesta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };


    cargaListaPreguntas2 = function (idEncuesta) {
        console.log("Carga PrguntasidEncuenta" + idEncuesta);
        aplicacionEncuesta(idEncuesta);

        var pregunta = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = idEncuesta;
        pregunta.encuesta = encuesta;
        pregunta.activo = "S";
        $.ajax({
            url: "listapreguntas",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                var table = "<table class=\"table table-striped\">" +
                    "<thead>" +
                    //                        " <tr>" +
                    //                        "  <th scope=\"col\">#</th>" +
                    //                        " <th scope=\"col\">Pregunta</th>" +
                    //                        " </tr>" +
                    "</thead><tbody>";
                var boton = " <button type=\"button\" class=\"btn btn-warning btn-sm btn-space\"" +
                    "onclick=\"mostrarModalRespuesta(idPregunta)\">Agregar Respuesta</button>";
                var respuesta = "";
                var html = "";
                var idPregunta = "";
                var espacio = "";
                $.each(response, function (index, element) {
                    respuesta = "";
                    html = "";
                    idPregunta = element.idPregunta;
                    $.each(element.respuesta, function (index, element) {


                        if (element.tipoRespuesta.idTipoRespuesta !== 4) {
                            html = element.tipoRespuesta.descHtml;
                            respuesta += html.replace("contenido", "<b>" + element.descRespuesta + "</b><br>").replace("valor", element.idRespuesta).replace("nombre", "pregunta" + idPregunta);
                        } else {
                            respuesta += "<b>" + element.descRespuesta + "</b><br>";
                        }

                        $.each(element.listahijos, function (index, element) {
                            html = element.tipoRespuesta.descHtml;
                            respuesta += "&nbsp;&nbsp;&nbsp;&nbsp;" + html.replace("contenido", "<b>" + element.descRespuesta + "</b><br>").replace("valor", element.idRespuesta).replace("nombre", "pregunta" + idPregunta);

                        });



                    });
                    var pregunta = new Object();
                    pregunta.nombre = "pregunta" + idPregunta;
                    pregunta.idPregunta = idPregunta;
                    pregunta.contestada = "N";
                    preguntas.push(pregunta);

                    table += "<tr>" +
                        "<th class=\"rengpreguntas\" scope=\"row\">" + (index + 1) + "</th>" +
                        "<td> <label class=\"rengpreguntas\">" + element.descPregunta + "</label><br>" + respuesta + "</td>" +
                        "</tr>";
                });
                table += "<tr>" +
                    "<td colspan='2'> <button type=\"button\" class=\"btn btn-danger btn-space\"  onclick=\"terminarEncuesta()\">Terminar Encuesta</button></td>" +
                    "</tr>";
                table += " </tbody>" +
                    "</table>";
                $("#listaPreguntas2").empty();
                $("#listaPreguntas2").show();
                $("#listaPreguntas2").append(table);




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    };


    agregarRespuesta = function () {
        var respuesta = new Object();
        var tipoRespuesta = new Object();
        var pregunta = new Object();
        var padre = new Object();
        pregunta.idPregunta = $("#hddIdPregunta").val() == 0 ? null : $("#hddIdPregunta").val();
        tipoRespuesta.idTipoRespuesta = $("#cargaTipoRespuesta").val();
        if (pregunta.idPregunta > 0)
            respuesta.pregunta = pregunta;
        ;
        padre.idRespuesta = $("#hddIdRespuesta").val() == 0 ? null : $("#hddIdRespuesta").val();
        if (padre.idRespuesta > 0)
            respuesta.padre = padre;
        respuesta.descRespuesta = $("#txtDescRespuesta").val();
        respuesta.tipoRespuesta = tipoRespuesta;
        respuesta.activo = "S";

        $.ajax({
            url: "guardarrespuesta",
            type: 'POST',
            data: JSON.stringify(respuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdPregunta").val(0);
                $("#hddIdRespuesta").val(0);


                $("#txtDescRespuesta").val("");
                cargaListaPreguntas();
                $("#myModalRespuesta").modal("hide");


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar respuesta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    };
    mostrarModalRespuesta = function (idPregunta) {
        $("#hddIdPregunta").val(idPregunta);
        $("#myModalRespuesta").modal("show");
        cargaTipoRespuesta();

    };
    mostrarModalRespuesta2 = function (idRespuesta) {
        $("#hddIdRespuesta").val(idRespuesta);
        $("#myModalRespuesta").modal("show");
        cargaTipoRespuesta();

    };

    agregarPregunta = function () {

        var pregunta = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = $("#hddIdEncuesta").val();
        pregunta.encuesta = encuesta;
        pregunta.descPregunta = $("#txtDescPregunta").val();
        pregunta.idPregunta = $("#hddIdPregunta").val() == 0 ? null : $("#hddIdPregunta").val();
        pregunta.activo = "S";
        $.ajax({
            url: "guardarpregunta",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdPregunta").val(0);
                $("#hddIdRespuesta").val(0);
                $("#txtDescPregunta").val("");
                cargaListaPreguntas();


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };

    eliminarPregunta = function (idPregunta) {

        var pregunta = new Object();
        pregunta.idPregunta = idPregunta;

        $.ajax({
            url: "eliminarpregunta",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdPregunta").val(0);
                $("#txtDescPregunta").val("");
                cargaListaPreguntas();


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };



    nuevaEncuesta = function () {
        $("#regEncuestas").show();
        $("#listaEncuestas").hide();
        var encuesta = new Object();
        encuesta.activo = "S";
        encuesta.publicar = "N";
        $.ajax({
            url: "guardarencuesta",
            type: 'POST',
            data: JSON.stringify(encuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdEncuesta").val(response.idEncuesta);


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    };
    actualizarEncuesta = function () {
        $("#regEncuestas").show();
        $("#listaEncuestas").hide();
        var encuesta = new Object();
        encuesta.descEncuesta = $("#txtDescEncuesta").val();
        encuesta.activo = "S";
        encuesta.idEncuesta = $("#hddIdEncuesta").val();
        $(".publicar").each(function (index) {
            if ($(this).prop("checked")) {
                encuesta.publicar = $(this).prop("value");
            }
        });



        $.ajax({
            url: "guardarencuesta",
            type: 'POST',
            data: JSON.stringify(encuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdEncuesta").val(response.idEncuesta);


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });


    };

    salirEncuesta = function () {
        $("#hddIdEncuesta").val(0);
        $("#regEncuestas").hide();
        $("#listaEncuestas").show();
        $("#hddIdEncuesta").val(0);
        var encuesta = new Object();
        encuesta.activo = "S";

        $.ajax({
            url: "listaencuestas",
            type: 'POST',
            data: JSON.stringify(encuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                var table = "<table class=\"table table-striped\">" +
                    "<thead>" +
                    " <tr>" +
                    "  <th scope=\"col\">#</th>" +
                    " <th scope=\"col\">Encuesta</th>" +
                    " <th scope=\"col\">Publicado</th>" +
                    "<th scope=\"col\">Fecha de Registro</th>" +
                    "<th scope=\"col\">&nbsp;</th>" +
                    "<th scope=\"col\">&nbsp;</th>" +
                    " </tr>" +
                    "</thead><tbody>";

                $.each(response, function (index, element) {
                    var publicado = "";
                    if (element.publicar === "S")
                        publicado = "PUBLICADO";
                    else
                        publicado = "SIN PUBLICAR";
                    table += "<tr>" +
                        "<th scope=\"row\">" + (index + 1) + "</th>" +
                        "<td>" + element.descEncuesta + "</td>" +
                        "<td>" + publicado + "</td>" +
                        "<td><img onclick=\"cargarEncuesta("
                        + element.idEncuesta
                        + ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">" +
                        "<img onclick=\"estadistica("
                        + element.idEncuesta
                        + ")\" class='modificar' src=\"resources/images/estadistica-icon.png\" width='60' heigth='70'  class=\"img-thumbnail\"></td>" +
                        "<td><img class='eliminar' id='"
                        + element.idEncuesta
                        + "'src=\"resources/images/test.png\" width='60' heigth='70'  onclick=\"testEncuesta(" + element.idEncuesta + ")\" class=\"img-thumbnail\"></td>" +
                        "<td><img class='eliminar' id='"
                        + element.idEncuesta
                        + "'src=\"resources/images/delete.png\" onclick=\"eliminarEncuesta(" + element.idEncuesta + ")\" class=\"img-thumbnail\"></td>" +
                        "</tr>";
                });
                table += " </tbody>" +
                    "</table>";
                $("#listaRegEncuesta").empty();
                $("#listaRegEncuesta").show();
                $("#listaRegEncuesta").append(table);


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };

    cargarEncuesta = function (idEncuesta) {
        $("#regEncuestas").show();
        $("#listaEncuestas").hide();
        var encuesta = new Object();
        encuesta.idEncuesta = idEncuesta;

        $.ajax({
            url: "listaencuestas",
            type: 'POST',
            data: JSON.stringify(encuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $.each(response, function (index, element) {
                    $("#hddIdEncuesta").val(element.idEncuesta);
                    $("#txtDescEncuesta").val(element.descEncuesta);

                    $(".publicar").each(function (index) {
                        if ($(this).prop("value") === element.publicar) {
                            $(this).prop("checked", true);
                        }
                    });

                    //                                        if (element.publicar === "S")
                    //                                            $("#checkPublicar").prop("checked", true);
                    //                                        else
                    //                                            $("#checkPublicar").prop("checked", false);
                });


                cargaListaPreguntas();




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };


    cargaListaPreguntas = function () {

        var pregunta = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = $("#hddIdEncuesta").val();
        pregunta.encuesta = encuesta;
        pregunta.activo = "S";
        $.ajax({
            url: "listapreguntas",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                var table = "<table class=\"table table-striped\">" +
                    "<thead>" +
                    " <tr>" +
                    "  <th scope=\"col\">#</th>" +
                    " <th scope=\"col\">pregunta</th>" +
                    "<th scope=\"col\">Fecha de Registro</th>" +
                    "<th scope=\"col\">&nbsp;</th>" +
                    "<th scope=\"col\">&nbsp;</th>" +
                    " </tr>" +
                    "</thead><tbody>";
                var boton = " <button type=\"button\" class=\"btn btn-warning btn-sm btn-space\"" +
                    "onclick=\"mostrarModalRespuesta(idPregunta)\">Agregar Respuesta</button>";
                var respuesta = "";
                var html = "";
                $.each(response, function (index, element) {
                    respuesta = "";
                    html = "";
                    $.each(element.respuesta, function (index, element) {
                        html = element.tipoRespuesta.descHtml;
                        respuesta += html.replace("contenido", "<b>" + element.descRespuesta + "</b>").replace("onclick", " onclick='mostrarModalRespuesta2(" + element.idRespuesta + ")'");

                        $.each(element.listahijos, function (index, element) {
                            html = element.tipoRespuesta.descHtml;
                            respuesta += html.replace("contenido", element.descRespuesta).replace("onclick", " onclick='mostrarModalRespuesta2(" + element.idRespuesta + ")'");

                        });



                    });

                    table += "<tr>" +
                        "<th scope=\"row\">" + (index + 1) + "</th>" +
                        "<td>" + element.descPregunta + "<br>" + boton.replace("idPregunta", element.idPregunta) + "<br>" + respuesta + "</td>" +
                        "<td><img onclick=\"cargarPregunta("
                        + element.idPregunta
                        + ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\"></td>" +
                        "<td><img class='eliminar' id='"
                        + element.idPregunta
                        + "'src=\"resources/images/delete.png\" onclick=\"eliminarPregunta(" + element.idPregunta + ")\" class=\"img-thumbnail\"></td>" +
                        "</tr>";
                });
                table += " </tbody>" +
                    "</table>";
                $("#listaPreguntas").empty();
                $("#listaPreguntas").show();
                $("#listaPreguntas").append(table);




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    };

    cargarPregunta = function (idPregunta) {

        var pregunta = new Object();
        pregunta.idPregunta = idPregunta;
        pregunta.activo = "S";
        $.ajax({
            url: "listapreguntas",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {



                $.each(response, function (index, element) {
                    $("#hddIdPregunta").val(element.idPregunta);
                    $("#txtDescPregunta").val(element.descPregunta);


                });





            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };
    cargaTipoRespuesta = function () {
        $("#regEncuestas").show();
        $("#listaEncuestas").hide();
        var tipoPregunta = new Object();
        tipoPregunta.activo = "S";
        $("#cargaTipoRespuesta").empty();

        $.ajax({
            url: "listatiposrespuestas",
            type: 'POST',
            data: JSON.stringify(tipoPregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                var option = "";
                $.each(response, function (index, element) {
                    option += "<option value='" + element.idTipoRespuesta + "'>" + element.descTipoRespuesta + "</option>";
                });

                $("#cargaTipoRespuesta").append(option);




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error cargar los tipos de preguntas",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };

    estadistica = function (idEncuesta) {
        tituloEncuesta(idEncuesta);

        if (idEncuesta != null)
            $("#hddIdEncuesta").val(idEncuesta);

        idEncuesta = $("#hddIdEncuesta").val();

        $("#estadistica").show();
        $(".estadistica").show();
        $("#listaEncuestas").hide();


        var aplicacionencuesta = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = idEncuesta;
        aplicacionencuesta.encuesta = encuesta;

        var fecha1 = cambiarfecha($("#txtFechaRegistro").val());
        var fecha2 = cambiarfecha($("#txtFechaActualizacion").val());
        if (fecha1 != "" && fecha2 != "") {
            aplicacionencuesta.fechaRegistro = fecha1+" 00:00:00";
            aplicacionencuesta.fechaActualizacion = fecha2 +" 23:59:59";
        }


        $.ajax({
            url: "graficapreguntas",
            type: 'POST',
            data: JSON.stringify(aplicacionencuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                var numPregunta = 0;
                var listaPreguntas = [];
                var listaRespuestas = [];
                var pregunta = new Object();
                var respuesta = new Object();
                $.each(response, function (index, element) {

                    if (index == 0) {
                        numPregunta = element.idPregunta;


                    }
                    if (numPregunta != element.idPregunta) {
                        pregunta.listaRespuestas = listaRespuestas;
                        listaPreguntas.push(pregunta);
                        numPregunta = element.idPregunta;
                        listaRespuestas = [];
                        pregunta = new Object();

                    }
                    pregunta.idPregunta = element.idPregunta;
                    pregunta.descPregunta = element.descPregunta;
                    respuesta.y = element.total;
                    respuesta.label = element.descRespuesta;
                    listaRespuestas.push(respuesta);
                    respuesta = new Object();


                });
                pregunta.listaRespuestas = listaRespuestas;
                listaPreguntas.push(pregunta);

                console.log(listaPreguntas);
                graficar(listaPreguntas);
                //
                //                                    $("#cargaTipoRespuesta").append(option);




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error cargar los tipos de preguntas",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });



    };
    tituloEncuesta = function (idEncuesta) {
        if (idEncuesta != null)
            $("#hddIdEncuesta").val(idEncuesta);

        idEncuesta = $("#hddIdEncuesta").val();


        console.log("Numero de encuesta:" + idEncuesta);

        $(".tituloEncuesta").empty();



        var encuesta = new Object();
        encuesta.idEncuesta = idEncuesta;





        $.ajax({
            url: "listaencuestas",
            type: 'POST',
            data: JSON.stringify(encuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                console.log("la respuesta: "+response)
                $.each(response,function(i,e){
                    $(".tituloEncuesta").append(e.descEncuesta);
                });
                


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error cargar los tipos de preguntas",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });



    };

    graficar = function (listaPreguntas) {
        var div = "";
        $.each(listaPreguntas, function (index, element) {
            var div = "<div id=\"chartContainer" + index + "\" style=\"height: 370px; width: 100%; margin:5px;\"></div>";

            $("#estadistica").append(div);

            var chart = new CanvasJS.Chart("chartContainer" + index, {
                theme: "light2", // "light2", "dark1", "dark2"
                animationEnabled: false, // change to true		
                title: {
                    text: element.descPregunta
                },
                data: [
                    {
                        // Change type to "bar", "area", "spline", "pie",etc.
                        type: "pie",
                        indexLabel: "{label} - {y}",
                        dataPoints: element.listaRespuestas
                    }
                ]
            });
            chart.render();

        });



    };
    testEncuesta = function (idEncuesta) {
        console.log("testa encuesta IdEncuesta" + idEncuesta);
        cargaListaPreguntas2(idEncuesta);
        $("#testEncuestas").show();
        $("#listaEncuestas").hide();




    };


    $(function () {

        $(".datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
            setDate: 'today'
        });
        salirEncuesta();

    });


</script>