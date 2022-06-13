
<input type="hidden" id="hddIdEvento" value="0">
<input type="hidden" id="hddIdNombreCompleto" value="0">
<input type="hidden" id="hddIdCargoParticipante" value="0">
<input type="hidden" id="hddIdInstitucionParticipante" value="0">
<input type="hidden" id="hddIdParticipante" value="0">
<div id="trending-items" class="section-container bg-silver">
    <!-- BEGIN container -->
    <div class="col-10 containersec">
        <!-- BEGIN section-title -->
        <h4 class="section-title clearfix">
            Eventos
            <small> Diplomados, Cursos, Talleres </small>
        </h4>
        <!-- END section-title -->
        <!-- BEGIN row -->
        <div class="row row-space-10 eventos">


        </div>
    </div>

    <div id="frmAsistencia" style="display: none;">
        <div class="form-group row">
            <h3 id="descEvento"></h3>
        </div>
        <div class="dropdown-divider"></div>
        <!--<div class="col-10 ">-->
        <label class="btn btn-secondary  col-10">
            <input id="txtNombreCompleto" type="text" name="options"  autocomplete="off" class="form-control "> Nombre Completo
            <button type="button" class="btn btn-success btn-sm btn-space btn-espacio"
                    id="" onclick="buscarParticipantes()">Consultar</button>
                    <button type="button" class="btn btn-success btn-sm btn-space btn-espacio"
                    id="" onclick="escanearParticipantes()">Escanear QR</button>
            <button type="button" class="btn btn-warning btn-sm btn-space btn-espacio"
                    id="" onclick="regresar()">Regresar</button>

        </label>

        <!--</div>-->
        <div class = "listaParticipantesAsis" id="listaParticipantesAsis"></div>
    </div>

    <div id="frmCapturaParticipantes" style="display: none;">
        <div  class="col-10 " >
            <h3 id="descEvento"></h3>
            <button type="button" id="agregarParticipante" style ="display:none;" class="btn btn-primary btn-sm btn-space extraParticipante"
                    onclick="agregarParticipante()">Agregar Participante</button>
         
            
            <button type="button" id="guardarParticipante" style ="display:none;" class="btn btn-primary btn-sm btn-space nuevoParticipante"
                    id="" onclick="guardarParticipante()">Guardar</button>
            <button type="button" class="btn btn-warning btn-sm btn-space listaParticipantes"
                    onClick="regresar()">Regresar</button>

        </div>



        <div class="dropdown-divider"></div>
        <div class="col-9 nuevoParticipante">
            <label class="btn btn-secondary controlj col-9">  
                <input id="txtCorreoElectronico" type="text"  placeholder="Ingrese su correo electr�nico" name="options"  autocomplete="off" class="form-control "> Correo Electr&oacute;nico:  
                <button type="button" class="btn btn-primary btn-sm btn-space " id="btn-buscar" onclick="buscarParticipantesRegistro();">Buscar</button>
                <button type="button" class="btn btn-warning btn-sm btn-space " id="btn-limpiar" onclick="limpiarBusqueda();">limpiar</button>
            </label>
        </div>
        <div class="col-9  completo">

            <label class="btn btn-secondary controlj col-3">
                <input id="txtNombreParticipante" type="text" name="options"  autocomplete="off" class="form-control " required> Nombre 
            </label>
            <label class="btn btn-secondary controlj col-3">
                <input id="txtPaternoParticipante" type="text" name="options"  autocomplete="off" class="form-control "required> Paterno
            </label>
            <label class="btn btn-secondary controlj col-3">
                <input id="txtMaternoParticipante" type="text" name="options"  autocomplete="off" class="form-control " required> Materno 
            </label>
        </div>
        <div class="col-9 completo">
            <label class="btn btn-secondary controlj col-9">
                <input id="txtCargo" type="text"   class="form-control " required> Cargo
            </label>
        </div>
        <div class="col-9 completo">
            <label class="btn btn-secondary controlj col-9">
                <input id="txtInstitucionParticipante" type="text"   class="form-control " required> Dependencia/Instituci�n 
            </label>
        </div>


        <div class = "col-9 listaParticipantes" id="listaParticipantes"></div>

    </div>

    
  
      <script src="resources/js/qrCodeScanner.js"></script>
</div>


<script>
    var seccion = "<div class=\"col-md-4 col-sm-7\">" +
            "<div class=\"item item-thumbnail\">" +
            "   <a href=\"#\" class=\"item-image\">" +
//            "      <img src=\"URL\" >" +
//                   "     <div class=\"discount\">ESTATUS</div>"+
            " </a>" +
            " <div class=\"item-info\">" +
            "     <h4 class=\"item-title\">" +
            "         <a href=\"#\" data-toggle=\"modal\">EVENTO</a>" +
            "     </h4>" +
            "     <p class=\"item-desc\">LUGAR</p>" +
            "     <p class=\"item-desc\">horario</p>" +
            "     <p class=\"item-desc\"><a class=\"modal2\" onclick=\"captura(publicado,clave)\" data-id=\"INDEX\" href=\"#modal-dialog\" data-toggle=\"modal\">REGISTRARSE</a></p>" +
            " </div>" +
            "</div>" +
            "</div>";
    limpiarBusqueda = function () {
    $("#listaParticipantes").empty();
    $("#txtCorreoElectronico").val("");
    $("#hddIdCargoParticipante").val(0);
    $("#hddIdInstitucionParticipante").val(0);
    $("#hddIdParticipante").val(0);
    $("#txtInstitucionParticipante").prop('disabled', false);
    $(".extraParticipante").hide();
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
    $("#btn-buscar").prop('disabled', false);
    $("#btn-limpiar").prop('disabled', false);
    $("#txtCargo").prop('disabled', false);
    $("#txtInstitucionParticipante").prop('disabled', false);
    };
    guardarParticipante = function () {
    var nombreParticipante = $("#txtNombreParticipante").val();
    var paternoParticipante = $("#txtPaternoParticipante").val();
    var maternoParticipante = $("#txtMaternoParticipante").val();
    var cargoParticipante = $("#txtCargo").val();
    var institucionParticipante = $("#txtInstitucionParticipante").val();
    var idParticipante = null;
    var idInstitucionParticipante = $("#hddIdInstitucionParticipante").val() == 0 ? "" : $("#hddIdInstitucionParticipante").val();
    var idCargoParticipante = $("#hddIdCargoParticipante").val() == 0 ? "" : $("#hddIdCargoParticipante").val();
    var idEventoP = $("#hddIdEvento").val();
    var idEventoParticipante = 0;
    var correoElectronico = $("#txtCorreoElectronico").val();
    var EventoParticipante = new Object();
    var Institucion = new Object();
    Institucion.idInstitucion = idInstitucionParticipante;
    Institucion.descInstitucion = institucionParticipante;
    var Participante = new Object();
    Participante.nombre = nombreParticipante;
    Participante.paterno = paternoParticipante;
    Participante.materno = maternoParticipante;
//        Participante.idParticipante = idParticipante;
    Participante.correoElectronico = correoElectronico;
    var Cargo = new Object();
    Cargo.idCargo = idCargoParticipante;
    Cargo.descCargo = cargoParticipante;
    var Evento = new Object();
    Evento.idEvento = idEventoP;
    EventoParticipante.institucion = Institucion;
    EventoParticipante.idEventoParticipante = idEventoParticipante;
    EventoParticipante.idEvento = idEventoP;
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
            // limpiarParticipante();
            //  mostrarParticipantes();


            bootbox.confirm({
            message: "Gracias por registrarse al concurso, �desea ingregar mas personas?",
                    buttons: {
                    confirm: {
                    label: 'SI',
                            className: 'btn-success'
                    },
                            cancel: {
                            label: 'No',
                                    className: 'btn-danger'
                            }
                    },
                    callback: function (result) {
                    if (result) {
                    $("#txtNombreParticipante").val("");
                    $("#txtPaternoParticipante").val("");
                    $("#txtMaternoParticipante").val("");
                    $("#btn-buscar").prop('disabled', true);
                    $("#btn-limpiar").prop('disabled', true);
                    $("#txtCargo").prop('disabled', true);
                    $("#txtInstitucionParticipante").prop('disabled', true);
                    $("#txtCorreoElectronico").prop('disabled', true);
                    $("#hddIdInstitucionParticipante").val(response.institucion.idInstitucion);
                    $("#hddIdCargoParticipante").val(response.cargo.idCargo);
                    buscarParticipantesRegistro();
                    } else {
                    
                    $(".completo").hide();
                    $("#guardarParticipante").hide();
                    
                    bootbox.dialog({
                    closeButton: true,
                            message: 'Se ha realizado tu inscripci�n con exito.'
                            ,
                            title: "Gracias por inscribirte.",
                            buttons: {
                            success: {
                            label: "Aceptar",
                                    className: "btn-success",callback: function(){
                                    comprobanteInscripcion();
                                    limpiarParticipante();
                                    regresar();
                                    return true;
                                    }
                            },
                            noclose: {
                            label: "Comprobante de Inscripci�n",
                                    className: 'btn-warning',
                                    callback: function(){
                                    comprobanteInscripcion();
                                    limpiarParticipante();
                                    regresar();
                                    return false;
                                    }
                            },
                            }
                    });
                    }
                    }
            });
            }, error: function (xhr, ajaxOptions, thrownError) {
    bootbox.dialog({
    closeButton: true,
            message: "Error al guardar la insitituci&oacute;n",
//                    title: "Error",
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
    comprobanteInscripcion = function () {
    var key = "";
    var data = "";
    key = "correo";
    data = $("#txtCorreoElectronico").val();
    var url = "/eventos/comprobante";
    var form = $('<form target="_blank"></form>').attr('action', url).attr(
            'method', 'post');
    // Add the one key/value
    form.append($("<input></input>").attr('type', 'hidden').attr('name',
            key).attr('value', data));
    key = "idEvento";
    data = $("#hddIdEvento").val();
    form.append($("<input></input>").attr('type', 'hidden').attr('name',
            key).attr('value', data));
    form.appendTo('body').submit();
    };
    regresar = function () {
    $(".completo").hide();
    $("#listaParticipantesAsis").empty();
    $("#frmAsistencia").hide();
    $(".eventos").show();
    $("#hddIdEvento").val(0);
    $("#hddIdNombreCompleto").val(0);
    $("#txtNombreCompleto").val("");
    $("#frmCapturaParticipantes").hide();
    $(".extraParticipante").hide();
    limpiarBusqueda();
    };
    buscarParticipantesRegistro = function () {

    var idParticipante = $("#hddIdParticipante").val();
    var correo = $("#txtCorreoElectronico").val();
    var idEvento = $("#hddIdEvento").val();
    var Participante = new Object();
    Participante.correoElectronico = correo;
    var Evento = new Object();
    Evento.idEvento = idEvento;
    var EventoParticipante = new Object();
    EventoParticipante.participante = Participante;
    EventoParticipante.evento = Evento;
    $("#listaParticipantes").empty();
    $.ajax({
    url: "buscarparticipanteregistro",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
            if (response.length > 0) {
            console.log("Mostrar Eventos");
            $(".extraParticipante").show();
            var table = "<table class=\"table table-striped\">" +
//                            "<thead>" +
//                            " <tr>" +
//                            "  <th scope=\"col\">#</th>" +
//                            " <th scope=\"col\">Nombre</th>" +
//                            " <th scope=\"col\">Instituci&oacute;n</th>" +
//                            "<th scope=\"col\">Asistencia</th>" +
//                            "<th scope=\"col\">&nbsp;</th>" +
//                            "<th scope=\"col\">&nbsp;</th>" +
//                            " </tr>" +
//                            "</thead>" +
                    "<tbody>";
            $.each(response, function (index, element) {
            var nombre = element.participante.nombre.toString().toUpperCase() + " " + element.participante.paterno.toString().toUpperCase() + " " + element.participante.materno.toString().toUpperCase();
            var button = "";
            var idEvento = $("#hddIdEvento").val();
            if (element.evento.idEvento != idEvento)
                    button = "<button type=\"button\" class=\"btn btn-primary btn-espacio\" onclick=\"registrar(" + element.participante.idParticipante + "," + element.cargo.idCargo + "," + element.institucion.idInstitucion + ");\">Registrarse al evento</button>";
            else {
            button = "Ya se encuentra registrado en este evento";
            }
//                        button += "<button type=\"button\" class=\"btn btn-primary btn-espacio\" onclick=\"registrar(" + element.participante.idParticipante + ");\">Actualizar Datos</button>";
//                        else
//                            button = "<b>No puede generar su documento porque no asistio al evento</b>";
            table += "<tr>" +
                    "<th scope=\"row\">" + (index + 1) + "</th>" +
                    "<td>" + nombre + "</td>" +
                    "<td>" + element.cargo.descCargo.toString().toUpperCase() + "</td>" +
                    "<td>" + element.institucion.descInstitucion + "</td>" +
//                                "<td>" + asistencia + "</td>" +
                    "<td>&nbsp;</td>" +
                    "<td>" + button + "</td>" +
                    "</tr>";
            });
            table += " </tbody>" +
                    "</table>";
            $("#listaParticipantes").show();
            $("#listaParticipantes").append(table);
            } else {
            bootbox.dialog({
            closeButton: true,
                    message: "No se encuentra registrado",
                    title: "Error",
                    buttons: {
                    success: {
                    label: "Aceptar",
                            className: "btn-success"
                    }
                    }
            });
            $("#hddIdCargoParticipante").val(0);
            $("#hddIdInstitucionParticipante").val(0);
            $("#hddIdParticipante").val(0);
            $("#listaParticipantes").empty();
            $(".completo").show();
            $("#guardarParticipante").show();
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
    agregarParticipante = function () {
    $(".extraParticipante").hide();
    $("#hddIdCargoParticipante").val(0);
    $("#hddIdInstitucionParticipante").val(0);
    $("#hddIdParticipante").val(0);
    // $("#listaParticipantes").empty();
    $(".completo").show();
    $("#guardarParticipante").show();
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
            $.each(response, function (index, element) {
            var asistencia = element.asistencia === "S" ? "Asistio" : "No Asistio";
            var button = "";
            if (element.asistencia === "S")
                    button = "<button type=\"button\" class=\"btn btn-primary\" onclick=\"imprimirConstancia(" + element.idEventoParticipante + ");\">Imprimir Documento </button>";
            else
                    button = "<b>No puede generar su documento porque no asistio al evento</b>";
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
                    message: "No se encuentra registrado en este evento",
                    title: "Error",
                    buttons: {
                    success: {
                    label: "Aceptar",
                            className: "btn-success"
                    }
                    }
            });
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
    captura = function (proceso, idEvento) {
    console.log(proceso + " " + idEvento);
    $(".eventos").hide();
    $("#hddIdEvento").val(idEvento);
    if (proceso == 1) {
    $("#frmAsistencia").show();
    } else {
    $("#frmCapturaParticipantes").show();
    }

    };
    buscarEventos = function () {

    $.ajax({
    url: "buscareventospub",
            type: 'POST',
//            data: JSON.stringify(eventoInstitucion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
            if (response.length > 0) {
            $(".eventos").empty();
            $.each(response, function (index, element) {
            var tempSeccion = seccion;
            tempSeccion = tempSeccion.replace(/INDEX/gi, index);
            tempSeccion = tempSeccion.replace(/LUGAR/gi, element.lugarEvento);
            tempSeccion = tempSeccion.replace(/EVENTO/gi, element.descEvento);
            tempSeccion = tempSeccion.replace(/horario/gi, cambiarFecha2(element.fechaEvento) + " " + element.horario);
            if (element.activo === "P") {
            tempSeccion = tempSeccion.replace(/REGISTRARSE/gi, "IMPRIMIR CONSTANCIA");
            tempSeccion = tempSeccion.replace(/publicado/gi, "1");
            } else {
            tempSeccion = tempSeccion.replace(/publicado/gi, "0");
            }
            tempSeccion = tempSeccion.replace(/clave/gi, element.idEvento);
            $(".eventos").append(tempSeccion);
            });
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
    registrar = function (idParticipante, idCargo, idInstitucion) {

    var Participante = new Object();
    var Cargo = new Object();
    var Institucion = new Object();
    var EventoParticipante = new Object();
    var Evento = new Object();
    Evento.idEvento = $("#hddIdEvento").val();
    Participante.idParticipante = idParticipante;
    Cargo.idCargo = idCargo;
    Institucion.idInstitucion = idInstitucion;
    EventoParticipante.participante = Participante;
    EventoParticipante.cargo = Cargo;
    EventoParticipante.institucion = Institucion;
    EventoParticipante.evento = Evento;
    $.ajax({
    url: "guardarparticipante",
            type: 'POST',
            data: JSON.stringify(EventoParticipante),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
            limpiarParticipante();
            //  mostrarParticipantes();
            $(".completo").hide();
            regresar();
            bootbox.dialog({
            closeButton: true,
                    message: "Gracias por registrarse al evento",
//                    title: "Error",
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
//                    title: "Error",
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
    $("#frmAsistencia").hide();
    $("#frmCapturaParticipantes").hide();
    $(".completo").hide();
    buscarEventos();
    $("#txtNombreCompleto")
            .autocomplete(
            {
            source: function (request, response) {
            var nombre = $("#txtNombreCompleto").val() == "" ? null
                    : "%" + $("#txtNombreCompleto").val()
                    + "%";
            var participante = new Object();
            participante.nombre = nombre;
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
    $("#txtCorreoElectronico")
            .autocomplete(
            {
            source: function (request, response) {
            var correoElectronico = $("#txtCorreoElectronico").val() == "" ? null
                    : "%" + $("#txtCorreoElectronico").val() + "%";
            var participante = new Object();
            participante.correoElectronico = correoElectronico;
            $
                    .ajax({
                    data: JSON.stringify(participante),
                            url: 'buscarporcorreo',
                            dataType: 'json',
                            contentType: "application/json",
                            type: 'post',
                            beforeSend: function () {

                            },
                            success: function (data) {

                            response($
                                    .map(
                                            data.slice(0, 1),
                                            function (item) {
                                            return {
                                            label: item.correoElectronico,
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
                    $("#txtCorreoElectronico").val(ui.item.label);
                    $("#hddIdParticipante").val(ui.item.value);
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
    });
    
    materialEjemplo = function(){
        
    };

</script>
<style>
    .eventos{
        font-size: 25px;
    }
    .item-info{
        font-size: 35px;
    }
    
</style>
<!-- END item -->
