<input type="hidden" id="hddIdEvento" value="0">
<input type="hidden" id="hddIdEventoParticipante" value="0">
<input type="hidden" id="hddIdInstitucion" value="0">
<input type="hidden" id="hddIdEventoInstitucion" value="0">
<input type="hidden" id="hddIdParticipante" value="0">
<input type="hidden" id="hddIdInstitucionParticipante" value="0">
<input type="hidden" id="hddIdCargoParticipante" value="0">
<input type="hidden" id="hddIdNombreCompleto" value="0">






<div class="form-group row">
    <h1>Constancias y Reconocimientos</h1>


</div>
<div id="frmNuevosDocumentos">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-10">
        <button type="button" class="btn btn-primary btn-sm btn-space" id="nuevoEvento" onClick="nuevoEvento()">Nuevo
            Evento</button>


        <div class="dropdown-divider"></div>
    </div>
</div>
<div id="listaEventos"></div>

<div id="frmEvento">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-10">
        <button type="button" class="btn btn-primary btn-sm btn-space" id="" onclick="guardarEvento()">Guardar</button>
        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="mostrarParticipantes()">Lista
            Participantes</button>
        <button type="button" class="btn btn-secondary btn-sm btn-space "
            onClick="mostrarConsultaParticipantes()">Consulta de Participantes</button>
        <button type="button" class="btn btn-secondary btn-sm btn-space " onClick="activate();activate2();">Escanear
            QR</button>
        <button type="button" class="btn btn-success btn-space" onClick="cancelarEvento()">Salir</button>

        <button type="button" class="btn btn-secondary btn-sm btn-space " onClick="enviarCorreo()">Enviar
            Correo</button>

        <div class="dropdown-divider"></div>
    </div>
    <label for="example-search-input" class="col-10 col-form-label">Nombre del Evento:</label>
    <div class="col-10">
        <input class="form-control " type="text" value="" id="txtNombreEvento">
    </div>

    <label for="example-search-input" class="col-10 col-form-label ">Fecha del Evento:</label>
    <div class="col-10">
        <input class="form-control datepicker" type="text" value="" id="txtFechaEvento">
    </div>
    <label for="example-search-input" class="col-10 col-form-label ">Hora del Evento:</label>
    <div class="col-10">
        <input class="form-control " type="text" value="" id="txtHoraEvento">
    </div>
    <label for="example-search-input" class="col-10 col-form-label ">Lugar del Evento:</label>
    <div class="col-10">
        <input class="form-control " type="text" value="" id="txtLugarEvento">
    </div>
    <label for="example-search-input" class="col-10 col-form-label">Institución que otorga:</label>
    <div class="col-10">
        <input class="form-control " type="text" value="" id="txtInstitucion">
    </div>
    <label for="example-search-input" class="col-10 col-form-label">Motivo del Evento:</label>
    <div class="col-10">
        <input class="form-control " type="text" value="" id="txtMotivoEvento">
    </div>
    <!--    <label for="example-search-input" class="col-10 col-form-label">Motivo del Evento:</label>
        <div class="col-10">
            <input class="form-control " type="text" value=""
                   id="txtMotivoEvento">
        </div>-->
    <label for="example-search-input" class="col-10 col-form-label">Seleccione el tipo de documento
        :</label>
    <div class="col-10">
        <select class="form-control" id="cmbMotivo">
            <option value="1">Constancia</option>
            <option value="2">Reconocimiento</option>
            <option value="3">Constancia Adolescentes</option>
        </select>
    </div>

    <div class="col-10">
        <label class="btn btn-secondary "><input class="form-control " type="checkbox" value=""
                id="checkPublicar">Publicar Evento:</label>

    </div>
    <label for="example-search-input" class="col-10 col-form-label">Introducci�n del Evento:</label>
    <div class="col-10">
        <input class="form-control " type="text" value="" id="txtIntroduccion">
    </div>



    <div class="dropdown-divider"></div>

</div>
<div class="dropdown-divider"></div>
<div id="frmCapturaParticipantes">
    <div class="form-group row">
        <h3 id="descEvento"></h3>


    </div>
    <button type="button" class="btn btn-primary btn-sm btn-space nuevoParticipante" id=""
        onclick="guardarParticipante()">Guardar</button>
    <button type="button" class="btn btn-warning btn-sm btn-space listaParticipantes"
        onClick="regresarEvento()">Salir</button>
    <button type="button" class="btn btn-warning btn-sm btn-space nuevoParticipante"
        onClick="regresarListaParticipantes()">regresar a la lista</button>
    <button type="button" class="btn btn-success btn-sm btn-space listaParticipantes" onClick="nuevoParticante()">Nuevo
        Participante</button>

    <div class="dropdown-divider"></div>

    <div class="col-10 nuevoParticipante">
        <label class="btn btn-secondary controlj col-9">
            <input id="txtCorreoElectronico" type="text" name="options" autocomplete="off" class="form-control "> Correo
            Electr&oacute;nico:
        </label>
    </div>
    <div class="col-10 nuevoParticipante">
        <label class="btn btn-secondary controlj col-3">
            <input id="txtNombreParticipante" type="text" name="options" autocomplete="off" class="form-control ">
            Nombre
        </label>
        <label class="btn btn-secondary controlj col-3">
            <input id="txtPaternoParticipante" type="text" name="options" autocomplete="off" class="form-control ">
            Paterno
        </label>
        <label class="btn btn-secondary controlj col-3">
            <input id="txtMaternoParticipante" type="text" name="options" autocomplete="off" class="form-control ">
            Materno
        </label>
    </div>
    <div class="col-10 nuevoParticipante">
        <label class="btn btn-secondary controlj col-9">
            <input id="txtCargo" type="text" class="form-control "> Cargo
        </label>
    </div>
    <div class="col-10 nuevoParticipante">
        <label class="btn btn-secondary controlj col-9">
            <input id="txtInstitucionParticipante" type="text" class="form-control "> Dependencia/Instituci�n
        </label>
    </div>
    <div class="col-10 nuevoParticipante">
        <label class="btn btn-secondary "><input class="form-control " type="checkbox" value=""
                id="checkAsistencia">Asistencia:</label>

    </div>
    <div class="col-10 nuevoParticipante">
        <label for="example-search-input" class=" btn btn-secondary ">Seleccione genero
            :

            <select class="form-control" id="cmbGenero">
                <option value="0">Seleccione</option>
                <option value="1">Masculino</option>
                <option value="2">Femenino</option>

            </select>
        </label>
    </div>

    <div class="listaParticipantes" id="listaParticipantes"></div>

</div>

<div id="frmAsistencia">
    <div class="form-group row">
        <h3 id="descEvento"></h3>
    </div>
    <div class="dropdown-divider"></div>
    <div class="col-10 ">
        <label class="btn btn-secondary  col-10">
            <input id="txtNombreCompleto" type="text" name="options" autocomplete="off" class="form-control "> Nombre
            Completo
            <button type="button" class="btn btn-primary btn-sm btn-space " id=""
                onclick="buscarParticipantes()">Consultar</button>
            <button type="button" class="btn btn-success btn-sm btn-space " id=""
                onclick="limpiarConfirmacion()">Limpiar</button>
            <button type="button" class="btn btn-warning btn-sm btn-space " id=""
                onclick="regresarListaParticipantes()">Regresar</button>

        </label>

    </div>
    <div class="listaParticipantesAsis" id="listaParticipantesAsis"></div>
</div>




<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">Los datos que ingreso son correctos?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" onclick="guardar();">Guardar
                    Entrada</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">Los datos que ingreso son correctos?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" onclick="guardarDetalle();">Guardar Entrada</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModalEliminar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button class="btnEliminarDetalle btn btn-primary" type="button">Eliminar
                    Articulo</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModalCorrect" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modalCorrect"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModalError" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Se encontraron
                    algunos errores</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="modalErrorText" class="modal-body">aqui va el mensaje</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>
</div>
<div id="containerqr">
    <h1>QR Code Scanner</h1>

    <a id="btn-scan-qr">
        <img src="https://dab1nmslvvntp.cloudfront.net/wp-content/uploads/2017/07/1499401426qr_icon.svg">
    </a>

    <canvas hidden="" id="qr-canvas"></canvas>

    <div id="qr-result" hidden="">
        <b>Data:</b> <span id="outputData"></span>
    </div>
</div>

<script type="text/javascript" src="/resources/js/summernote-cleaner.js"></script>
<script type="text/javascript" src="/resources/js/summernote.min.js"></script>
<script type="text/javascript" src="/resources/js/summernote-es-ES.js"></script>
<script type="text/javascript" src="/resources/js/so.min.js"></script>
<script type="text/javascript" src="/resources/js/moment.min.js"></script>
<script type="text/javascript" src="/resources/js/loading.min.js"></script>
<script type="text/javascript" src="/resources/js/bootbox.min.js"></script>
<script type="text/javascript" src="/resources/js/loading.min.js"></script>

<script>

    activate2 = function () {
        $("#containerqr").show();
        $("#frmEvento").hide();
    }

    enviarCorreo = function () {

        $.ajax({
            url: "enviarconstancia",
            type: 'POST',
            //                                    data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                bootbox.dialog({
                    closeButton: true,
                    message: response.mensaje,
                    //                                            title: "Mensaje",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-" + response.estatus
                        }
                    }
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

    buscarDocumentos = function () {

        $.ajax({
            url: "buscardocumentos",
            type: 'POST',
            //                                    data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                if (response.length > 0) {
                    $("#cmbMotivo").empty();
                    var opciones = "";
                    $.each(response, function (index, element) {
                        opciones += "<option value='" + element.idDocumentoEvento + "'>" + element.descDocumentoEvento + "</option>";
                    });
                    $("#cmbMotivo").append(opciones);
                }



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

    limpiarConfirmacion = function () {
        $("#listaParticipantesAsis").empty();
        $("#txtNombreCompleto").val("");
        $("#hddIdNombreCompleto").val(0);
    };
    confirmarAsistencia = function (idEventoParticipante) {

        var EventoParticipante = new Object();
        EventoParticipante.idEventoParticipante = idEventoParticipante;

        console.log("cargarParticipante");
        $.ajax({
            url: "confirmarasistencia",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                limpiarConfirmacion();
                bootbox.dialog({
                    closeButton: true,
                    message: "Entrada Registrada",
                    title: "Mensaje",
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

    confirmarSalida = function (idEventoParticipante) {

        var EventoParticipante = new Object();
        EventoParticipante.idEventoParticipante = idEventoParticipante;

        console.log("cargarParticipante");
        $.ajax({
            url: "confirmarsalida",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                limpiarConfirmacion();
                bootbox.dialog({
                    closeButton: true,
                    message: "Salida Registrada",
                    title: "Mensaje",
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
    buscarParticipantes = function () {

        var idParticipante = $("#hddIdNombreCompleto").val();
        var idEvento = $("#hddIdEvento").val();
        var Evento = new Object();
        Evento.idEvento = idEvento;

        var Participante = new Object();
        Participante.idParticipante = idParticipante;
        var EventoParticipante = new Object();
        EventoParticipante.evento = Evento;
        EventoParticipante.participante = Participante;
        $("#listaParticipantesAsis").empty();

        $.ajax({
            url: "listarparticipantes",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                if (response.length > 0) {
                    console.log("Mostrar Eventos");

                    var table = "<table class=\"table table-striped\">" +
                        "<thead>" +
                        " <tr>" +
                        "  <th scope=\"col\">#</th>" +
                        " <th scope=\"col\">Nombre</th>" +
                        " <th scope=\"col\">Instituci&oacute;n</th>" +
                        "<th scope=\"col\">Asistencia</th>" +
                        "<th scope=\"col\">&nbsp;</th>" +
                        "<th scope=\"col\">&nbsp;</th>" +
                        " </tr>" +
                        "</thead><tbody>";
                    var button = "";
                    $.each(response, function (index, element) {
                        var asistencia = element.asistencia == "S" ? "Salida Registrada" : element.asistencia == "E" ? "Entrada Registrada" : "No Asistio";

                        if (element.asistencia == "S")
                            button = "";
                        else if (element.asistencia == "E")
                            button = "<button type=\"button\" class=\"btn btn-primary\" onclick=\"confirmarSalida(" + element.idEventoParticipante + ");\">Registrar Salida </button>";
                        else
                            button = "<button type=\"button\" class=\"btn btn-primary\" onclick=\"confirmarAsistencia(" + element.idEventoParticipante + ");\">Registrar Entrada </button>";
                        //                        if (element.eventoI.publicar === "S")
                        //                            publicado = "PUBLICADO";
                        //                        else
                        //                            publicado = "SIN PUBLICAR";
                        table += "<tr>" +
                            "<th scope=\"row\">" + (index + 1) + "</th>" +
                            "<td>" + element.participante.nombre + " " + element.participante.paterno + " " + element.participante.materno + "</td>" +
                            "<td>" + element.institucion.descInstitucion + "</td>" +
                            "<td>" + asistencia + "</td>" +
                            "<td>&nbsp;</td>" +
                            "<td>" + button + "</td>" +
                            "</tr>";
                    });
                    table += " </tbody>" +
                        "</table>";
                    $("#listaParticipantesAsis").show();
                    $("#listaParticipantesAsis").append(table);

                } else {
                    bootbox.dialog({
                        closeButton: true,
                        message: "No se encuentra registrado",
                        title: "Error",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-danger"
                            }
                        }
                    });
                }


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al buscar los participantes",
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
    mostrarConsultaParticipantes = function () {
        $("#frmAsistencia").show();
        $("#frmEvento").hide();

    };
    consultaParticipantes = function () {

    };

    eliminarParticipante = function (idEventoParticipante) {
        var EventoParticipante = new Object();
        EventoParticipante.idEventoParticipante = idEventoParticipante;

        console.log("cargarParticipante");
        $.ajax({
            url: "eliminarparticipante",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                mostrarParticipantes();


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al eliminar el participante",
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

    nuevoParticante = function () {
        $(".nuevoParticipante").show();
        $(".listaParticipantes").hide();

    };
    cargarParticipante = function (idEventoParticipante) {

        var EventoParticipante = new Object();
        EventoParticipante.idEventoParticipante = idEventoParticipante;
        var cveGenero = 0;
        console.log("cargarParticipante");
        $.ajax({
            url: "buscarParticipante",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                nuevoParticante();
                console.log("Mostrar Eventos");

                $("#hddIdParticipante").val(response.participante.idParticipante);
                $("#txtNombreParticipante").val(response.participante.nombre);
                $("#txtPaternoParticipante").val(response.participante.paterno);
                $("#txtMaternoParticipante").val(response.participante.materno);
                $("#txtCargo").val(response.cargo.descCargo);
                $("#hddIdCargo").val(response.cargo.idCargo);
                $("#hddIdEventoParticipante").val(idEventoParticipante);
                $("#txtInstitucionParticipante").val(response.institucion.descInstitucion);
                $("#hddIdInstitucionParticipante").val(response.institucion.idInstitucion);
                $("#txtCorreoElectronico").val(response.participante.correoElectronico);
                if (response.participante.genero != null)
                    cveGenero = response.participante.genero.cveGenero;
                $("#cmbGenero").val(cveGenero);


                //                                        $("#checkAsistencia").val(response.eventoI.lugarEvento);
                if (response.asistencia === "S")
                    $("#checkAsistencia").prop("checked", true);
                else
                    $("#checkAsistencia").prop("checked", false);





            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al cargar el participante",
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

    cargarParticipante2 = function (idEventoParticipante) {

        var EventoParticipante = new Object();
        EventoParticipante.idEventoParticipante = idEventoParticipante;
        var cveGenero = 0;
        var datos = "";
        console.log("cargarParticipante");
        $.ajax({
            url: "buscarParticipante",
            type: 'POST',
            async: false,
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                var nombre = response.participante.nombre;
                var paterno = response.participante.paterno;
                var materno = response.participante.materno;
                var cargo = response.cargo.descCargo;
                var institucion = response.institucion.descInstitucion;

                datos = nombre + " " + paterno + " " + materno + "<br>" + cargo + "<br>" + institucion;

                bootbox.confirm({
                    message: datos,
                    buttons: {
                        confirm: {
                            label: 'Aceptar',
                            className: 'btn-success'
                        }
                    },
                    callback: function (result) {
                        if (result) {
                            activate();
                            activate2();

                        }
                    }
                });









            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.confirm({
                    message: "Error al cargar el participante",
                    buttons: {
                        confirm: {
                            label: 'Aceptar',
                            className: 'btn-success'
                        }
                    },
                    callback: function (result) {
                        if (result) {
                            activate();
                            activate2();

                        }
                    }
                });
               
            }
        });

        return datos;

    };
    regresarListaParticipantes = function () {
        limpiarParticipante();
        limpiarConfirmacion();
        $("#frmAsistencia").hide();
        $(".nuevoParticipante").hide();
        $(".listaParticipantes").show();
        mostrarParticipantes();
    };
    limpiarParticipante = function () {
        $("#txtNombreParticipante").val("");
        $("#txtPaternoParticipante").val("");
        $("#txtMaternoParticipante").val("");
        $("#txtCargo").val("");
        $("#txtInstitucionParticipante").val("");
        $("#hddIdParticipante").val(0);
        $("#hddIdInstitucionParticipante").val(0);
        $("#hddIdCargoParticipante").val(0);
        $("#txtCorreoElectronico").val("");


        $("#hddIdEventoParticipante").val(0);
        $("#checkAsistencia").prop("checked", false);
    };
    guardarParticipante = function () {
        var nombreParticipante = $("#txtNombreParticipante").val();
        var paternoParticipante = $("#txtPaternoParticipante").val();
        var maternoParticipante = $("#txtMaternoParticipante").val();
        var cargoParticipante = $("#txtCargo").val();
        var institucionParticipante = $("#txtInstitucionParticipante").val();
        var idParticipante = $("#hddIdParticipante").val() == 0 ? "" : $("#hddIdParticipante").val();
        var idInstitucionParticipante = $("#hddIdInstitucionParticipante").val() == 0 ? "" : $("#hddIdInstitucionParticipante").val();
        var idCargoParticipante = $("#hddIdCargoParticipante").val() == 0 ? "" : $("#hddIdCargoParticipante").val();
        var idEventoP = $("#hddIdEvento").val();
        var idEventoParticipante = $("#hddIdEventoParticipante").val() == 0 ? "" : $("#hddIdEventoParticipante").val();
        var asistencia = $("#checkAsistencia").prop("checked") == true ? "S" : "N";
        var correElectronico = $("#txtCorreoElectronico").val();
        var cveGenero = $("#cmbGenero").val() == 0 ? null : $("#cmbGenero").val();
        var EventoParticipante = new Object();
        var Institucion = new Object();
        Institucion.idInstitucion = idInstitucionParticipante;
        Institucion.descInstitucion = institucionParticipante;
        var Genero = new Object();
        Genero.cveGenero = cveGenero;
        var Participante = new Object();
        Participante.nombre = nombreParticipante;
        Participante.paterno = paternoParticipante;
        Participante.materno = maternoParticipante;
        Participante.idParticipante = idParticipante;
        Participante.correoElectronico = correElectronico;
        Participante.genero = Genero;
        var Cargo = new Object();
        Cargo.idCargo = idCargoParticipante;
        Cargo.descCargo = cargoParticipante;

        var Evento = new Object();
        Evento.idEvento = idEventoP;

        EventoParticipante.institucion = Institucion;
        EventoParticipante.idEventoParticipante = idEventoParticipante;
        EventoParticipante.idEvento = idEventoP;
        EventoParticipante.asistencia = asistencia;
        EventoParticipante.participante = Participante;
        EventoParticipante.cargo = Cargo;
        EventoParticipante.evento = Evento;

        console.log(JSON.stringify(EventoParticipante));




        $.ajax({
            url: "guardarparticipante",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                limpiarParticipante();
                mostrarParticipantes();

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

    nuevoEvento = function () {
        $("#frmNuevosDocumentos").hide();
        $("#frmEvento").show();
        $("#listaEventos").empty();
        $("#listaEventos").hide();
    };
    eliminarEvento = function (idEventoInstitucion) {


        var EventoInstitucion = new Object();

        EventoInstitucion.idEventoInstitucion = idEventoInstitucion;

        $.ajax({
            url: "eliminarEvento",
            type: 'POST',
            data: JSON.stringify(EventoInstitucion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                mostrarEventos();

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
    cancelarEvento = function () {
        $("#frmNuevosDocumentos").show();
        $("#frmEvento").hide();
        $("#hddIdEvento").val(0);
        $("#hddIdInstitucion").val(0);
        $("#hddIdEventoInstitucion").val(0);
        $("#txtNombreEvento").val("");

        $("#txtFechaEvento").val("");
        $("#txtInstitucion").val();

        $("#txtMotivoEvento").val("");
        $("#cmbMotivo").val(1);


        mostrarEventos();
    };
    cargarEvento = function (idEventoInstitucion) {
        var EventoInstitucion = new Object();
        buscarDocumentos();
        EventoInstitucion.idEventoInstitucion = idEventoInstitucion;
        $("#frmNuevosDocumentos").hide();
        $("#frmEvento").show();
        $("#listaEventos").hide();
        $("#listaEventos").empty();
        console.log("cargarEvento");
        $.ajax({
            url: "buscarEvento",
            type: 'POST',
            data: JSON.stringify(EventoInstitucion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                console.log("Mostrar Eventos");

                $("#txtNombreEvento").val(response.eventoI.descEvento);
                $("#hddIdEvento").val(response.eventoI.idEvento);
                $("#txtFechaEvento").val(cambiarFecha2(response.eventoI.fechaEvento));
                $("#txtInstitucion").val(response.institucion.descInstitucion);
                $("#hddIdInstitucion").val(response.institucion.idInstitucion);
                $("#txtMotivoEvento").val(response.eventoI.motivoEvento);
                $("#txtHoraEvento").val(response.eventoI.horario);
                $("#txtLugarEvento").val(response.eventoI.lugarEvento);
                $("#cmbMotivo").val(response.eventoI.documentoEvento.idDocumentoEvento);
                $("#hddIdEventoInstitucion").val(response.idEventoInstitucion);
                if (response.eventoI.publicar === "S")
                    $("#checkPublicar").prop("checked", true);
                else
                    $("#checkPublicar").prop("checked", false);
                $("#txtIntroduccion").val(response.eventoI.introduccion);




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
    mostrarEventos = function () {
        var evento = new Object();
        //        evento.idEvento = $("#hddIdEvento").val();
        //        evento.descEvento = $("#txtNombreEvento").val();
        //        evento.fechaEvento = $("#txtFechaEvento").val();
        //        evento.idInstitucion = $("#hddIdInstitucion").val();
        //        evento.idDocumentoEvento = $("#cmbMotivo").val();
        //        evento.motivoEvento = $("#txtMotivoEvento").val();
        $.ajax({
            url: "listareventos",
            type: 'POST',
            //            data: JSON.stringify(evento), 
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                if (response.length > 0) {
                    console.log("Mostrar Eventos");

                    var table = "<table class=\"table table-striped\">" +
                        "<thead>" +
                        " <tr>" +
                        "  <th scope=\"col\">#</th>" +
                        " <th scope=\"col\">Evento</th>" +
                        " <th scope=\"col\">Instituci&oacute;n</th>" +
                        "<th scope=\"col\">Fecha Evento</th>" +
                        "<th scope=\"col\">&nbsp;</th>" +
                        "<th scope=\"col\">&nbsp;</th>" +
                        " </tr>" +
                        "</thead><tbody>";

                    $.each(response, function (index, element) {
                        var publicado = "";
                        if (element.eventoI.publicar === "S")
                            publicado = "PUBLICADO";
                        else
                            publicado = "SIN PUBLICAR";
                        table += "<tr>" +
                            "<th scope=\"row\">" + (index + 1) + "</th>" +
                            "<td>" + element.eventoI.descEvento + "<br><img  width=\"30px\" heigth=\"30px\" src=\"resources/images/descarga.png\" onclick=\"descargaPaquete(" + element.eventoI.idEvento + ");\"></td>" +
                            "<td>" + element.institucion.descInstitucion + "</td>" +
                            "<td>" + cambiarFecha2(element.eventoI.fechaEvento) + "</td>" +
                            "<td>" + publicado + "</td>" +
                            "<td><img onclick=\"cargarEvento("
                            + element.idEventoInstitucion
                            + ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\"></td>" +
                            "<td><img class='eliminar' id='"
                            + element.idEventoInstitucion
                            + "'src=\"resources/images/delete.png\" onclick=\"eliminarEvento(" + element.idEventoInstitucion + ")\" class=\"img-thumbnail\"></td>" +
                            "</tr>";
                    });
                    table += " </tbody>" +
                        "</table>";
                    $("#listaEventos").show();
                    $("#listaEventos").append(table);

                }


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
    guardarInstitucion = function () {
        var institucion = new Object();
        institucion.descInstitucion = $("#txtInstitucion").val();
        $.ajax({
            url: "guardarInstitucion",
            type: 'POST',
            data: JSON.stringify(evento),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {


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
    guardarEvento = function () {
        var institucion = new Object();
        var documentoEvento = new Object();
        documentoEvento.idDocumentoEvento = $("#cmbMotivo").val();
        institucion.idInstitucion = $("#hddIdInstitucion").val();
        institucion.descInstitucion = $("#txtInstitucion").val();
        var eventoInstitucion = new Object();
        eventoInstitucion.institucion = institucion;
        var evento = new Object();
        evento.idEvento = $("#hddIdEvento").val();
        evento.descEvento = $("#txtNombreEvento").val();
        evento.lugarEvento = $("#txtLugarEvento").val();
        evento.horario = $("#txtHoraEvento").val();
        evento.fechaEvento = cambiarfecha($("#txtFechaEvento").val());
        if ($("#checkPublicar").prop("checked")) {
            evento.publicar = "S";
        } else {
            evento.publicar = "N";
        }
        evento.introduccion = $("#txtIntroduccion").val();
        //        if ($("#hddIdInstitucion").val() == "") {
        //            $("#hddIdInstitucion").val(guardarInstitucion());
        //        }
        //        evento.listaInstituciones = listaInstituciones;
        eventoInstitucion.idEventoInstitucion = $("#hddIdEventoInstitucion").val();
        evento.documentoEvento = documentoEvento;
        evento.motivoEvento = $("#txtMotivoEvento").val();
        eventoInstitucion.eventoI = evento;
        $.ajax({
            url: "guardarEvento",
            type: 'POST',
            data: JSON.stringify(eventoInstitucion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdEvento").val(response.id);
                bootbox.dialog({
                    closeButton: true,
                    message: "Se ha guardado el evento ",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
                mostrarParticipantes();



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
    regresarEvento = function () {
        $("#frmEvento").show();
        $("#frmCapturaParticipantes").hide();
    };
    mostrarParticipantes = function () {
        $("#listaParticipantes").empty();
        $("#descEvento").append("Descripcion del evento");
        $("#frmEvento").hide();
        $("#frmCapturaParticipantes").show();
        var nombreEvento = $("#txtNombreEvento").val();
        $("#descEvento").empty();
        $("#descEvento").append(nombreEvento);
        var EventoParticipante = new Object();
        var Evento = new Object();
        Evento.idEvento = $("#hddIdEvento").val();
        EventoParticipante.evento = Evento;
        if (Evento.idEvento > 0) {
            $.ajax({
                url: "listarparticipantes",
                type: 'POST',
                data: JSON.stringify(EventoParticipante),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    if (response.length > 0) {
                        console.log("Mostrar Eventos");

                        var table = "<table class=\"table table-striped\">" +
                            "<thead>" +
                            " <tr>" +
                            "  <th scope=\"col\">#</th>" +
                            " <th scope=\"col\">Nombre</th>" +
                            " <th scope=\"col\">Instituci&oacute;n</th>" +
                            "<th scope=\"col\">Asistencia</th>" +
                            "<th scope=\"col\">&nbsp;</th>" +
                            "<th scope=\"col\">&nbsp;</th>" +
                            " </tr>" +
                            "</thead><tbody>";

                        $.each(response, function (index, element) {
                            var asistencia = element.asistencia === "S" ? "Asistio" : "No Asistio";
                            //                        if (element.eventoI.publicar === "S")
                            //                            publicado = "PUBLICADO";
                            //                        else
                            //                            publicado = "SIN PUBLICAR";
                            table += "<tr>" +
                                "<th scope=\"row\">" + (index + 1) + "</th>" +
                                "<td>" + element.participante.nombre + " " + element.participante.paterno + " " + element.participante.materno + "</td>" +
                                "<td>" + element.institucion.descInstitucion + "</td>" +
                                "<td>" + asistencia + "</td>" +
                                "<td>&nbsp;</td>" +
                                "<td><img onclick=\"cargarParticipante("
                                + element.idEventoParticipante
                                + ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\"></td>" +
                                "<td><img onclick=\"imprimirConstancia("
                                + element.idEventoParticipante
                                + ")\" class='modificar' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\"></td>" +
                                "<td><img class='eliminar' id='"
                                + element.idEventoParticipante
                                + "'src=\"resources/images/delete.png\" onclick=\"eliminarParticipante(" + element.idEventoParticipante + ")\" class=\"img-thumbnail\"></td>" +
                                "</tr>";
                        });
                        table += " </tbody>" +
                            "</table>";
                        $("#listaParticipantes").show();
                        $("#listaParticipantes").append(table);

                    }


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
        }


    };
    verificarPermisos = function () {

        $.ajax({
            url: "verificarpermisos",
            type: 'POST',
            data: JSON.stringify(eventoInstitucion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdEvento").val(response.id);
                bootbox.dialog({
                    closeButton: true,
                    message: "Se ha guardado el evento ",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
                mostrarParticipantes();

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
    $(function () {

        $(".datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
            setDate: 'today'
        });
        $("#frmCapturaParticipantes").hide();
        $("#frmEvento").hide();
        $(".nuevoParticipante").hide();
        $("#frmAsistencia").hide();


        $("#listaParticipantes").hide();
        $("#listaEventos").hide();
        $("#listaParticipantes").empty();
        $("#listaEventos").empty();
        mostrarEventos();
        $("#containerqr").hide();


        $("#txtCargo")
            .autocomplete(
                {
                    source: function (request, response) {
                        var descCargo = $("#txtCargo").val() === "" ? null
                            : "%" + $("#txtCargo").val()
                            + "%";

                        var cargo = new Object();
                        cargo.descCargo = descCargo;


                        $
                            .ajax({
                                data: JSON.stringify(cargo),
                                url: 'buscarcargos',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {

                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.descCargo,
                                                    value: item.idCargo
                                                }
                                            }));
                                },
                                error: function (xhr, ajaxOptions,
                                    thrownError) {
                                    //                                                $('#myModal').modal('hide');
                                    //                                                $("#myModalCorrect").modal(
                                    //                                                        "hide");
                                    //                                                $("#myModalError")
                                    //                                                        .append(
                                    //                                                                "Seleccione una dependencia de las opciones mostradas");
                                    //                                                $("#myModalError").modal();
                                }
                            });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                            + ui.item.label
                            : "Nothing selected, input was "
                            + this.value);
                        $("#txtCargo").val(ui.item.label);
                        $("#hddIdCargoParticipante").val(ui.item.value);

                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass(
                            "ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass(
                            "ui-corner-all");
                    }
                });

        $("#txtInstitucionParticipante")
            .autocomplete(
                {
                    source: function (request, response) {
                        var descInstitucion = $("#txtInstitucionParticipante").val() == "" ? null
                            : "%" + $("#txtInstitucionParticipante").val()
                            + "%";

                        var institucion = new Object();
                        institucion.descInstitucion = descInstitucion;


                        $
                            .ajax({
                                data: JSON.stringify(institucion),
                                url: 'buscarinstituciones',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {

                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.descInstitucion,
                                                    value: item.idInstitucion
                                                }
                                            }));
                                },
                                error: function (xhr, ajaxOptions,
                                    thrownError) {
                                    //                                                $('#myModal').modal('hide');
                                    //                                                $("#myModalCorrect").modal(
                                    //                                                        "hide");
                                    //                                                $("#myModalError")
                                    //                                                        .append(
                                    //                                                                "Seleccione una dependencia de las opciones mostradas");
                                    //                                                $("#myModalError").modal();
                                }
                            });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                            + ui.item.label
                            : "Nothing selected, input was "
                            + this.value);
                        $("#txtInstitucionParticipante").val(ui.item.label);
                        $("#hddIdInstitucionParticipante").val(ui.item.value);

                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass(
                            "ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass(
                            "ui-corner-all");
                    }
                });

        $("#txtInstitucion")
            .autocomplete(
                {
                    source: function (request, response) {
                        var descInstitucion = $("#txtInstitucion").val() == "" ? null
                            : "%" + $("#txtInstitucion").val()
                            + "%";

                        var institucion = new Object();
                        institucion.descInstitucion = descInstitucion;


                        $
                            .ajax({
                                data: JSON.stringify(institucion),
                                url: 'buscarinstituciones',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {

                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.descInstitucion,
                                                    value: item.idInstitucion
                                                }
                                            }));
                                },
                                error: function (xhr, ajaxOptions,
                                    thrownError) {
                                    //                                                $('#myModal').modal('hide');
                                    //                                                $("#myModalCorrect").modal(
                                    //                                                        "hide");
                                    //                                                $("#myModalError")
                                    //                                                        .append(
                                    //                                                                "Seleccione una dependencia de las opciones mostradas");
                                    //                                                $("#myModalError").modal();
                                }
                            });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                            + ui.item.label
                            : "Nothing selected, input was "
                            + this.value);
                        $("#txtInstitucion").val(ui.item.label);
                        $("#hddIdInstitucion").val(ui.item.value);

                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass(
                            "ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass(
                            "ui-corner-all");
                    }
                });


        $("#txtNombreCompleto")
            .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtNombreCompleto").val() == "" ? null
                            : "%" + $("#txtNombreCompleto").val()
                            + "%";

                        var participante = new Object();
                        participante.nombre = nombre;
                        participante.idParticipante = $("#hddIdEvento").val();



                        $
                            .ajax({
                                data: JSON.stringify(participante),
                                url: 'buscarparticipantesasis',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {

                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.nombre + " " + item.paterno + " " + item.materno,
                                                    value: item.idParticipante
                                                }
                                            }));
                                },
                                error: function (xhr, ajaxOptions,
                                    thrownError) {
                                    //                                                $('#myModal').modal('hide');
                                    //                                                $("#myModalCorrect").modal(
                                    //                                                        "hide");
                                    //                                                $("#myModalError")
                                    //                                                        .append(
                                    //                                                                "Seleccione una dependencia de las opciones mostradas");
                                    //                                                $("#myModalError").modal();
                                }
                            });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                            + ui.item.label
                            : "Nothing selected, input was "
                            + this.value);
                        $("#txtNombreCompleto").val(ui.item.label);
                        $("#hddIdNombreCompleto").val(ui.item.value);

                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass(
                            "ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass(
                            "ui-corner-all");
                    }
                });



    });

    imprimirConstancia = function (idEventoParticipante) {
        var key = "";
        var data = "";

        key = "idEventoParticipante";
        data = idEventoParticipante;
        ;
        var url = "/eventos/documento";

        var form = $('<form target="_blank"></form>').attr('action', url).attr(
            'method', 'post');
        // Add the one key/value
        form.append($("<input></input>").attr('type', 'hidden').attr('name',
            key).attr('value', data));
        form.appendTo('body').submit();

    };

    descargaPaquete = function (idEvento) {
        var key = "";
        var data = "";

        key = "idEvento";
        data = idEvento;
        ;
        var url = "/eventos/paquetes";

        var form = $('<form target="_blank"></form>').attr('action', url).attr(
            'method', 'post');
        // Add the one key/value
        form.append($("<input></input>").attr('type', 'hidden').attr('name',
            key).attr('value', data));
        form.appendTo('body').submit();

    };
</script>
<script type="text/javascript" src="resources/js/qrCodeScanner.js"></script>