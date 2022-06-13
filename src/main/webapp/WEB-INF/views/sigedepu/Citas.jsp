<input type="hidden" id="hddCveMunicipio" value="0">
<input type="hidden" id="rooidm-id" value="">
<input type="hidden" id="hddIdRepresentado" value="0">
<input type="hidden" id="hddIdCita" value="0">
<input type="hidden" id="hddnombre" value="0">



<style>
    * {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    #contenido {
        height: 800px;
    }


    .divCita {
        float: left;

    }

    .disponible {
        float: left;
        background-color: #05F972;
        margin: 5px 5px;
        padding: 7px;
        border: solid 1px #000;
    }

    .nodisponible {
        float: left;
        background-color: #F9340A;
        margin: 5px 5px;
        padding: 7px;
        border: solid 1px #000;
    }
</style>


<div id="Citas">

    <div class="listaCitas">
        <img class="chat" src="resources/images/thumbnail_16 intranet-cita.png" onclick="cargarFrmCalendario()">

    </div>

    <div class="modal fade" id="myModalCalendario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Citas para Asesoria</h5>
                    <button type="button" class="close" data-dismiss="modal" onclick="location.reload(true);"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="modalCorrect">
                    <div class="form-group">
                        <input type="text" id="txtMunicipio" placeholder="Seleccione su municipio"
                            class="form-control" />
                    </div>
                    <div class="form-group ">
                        <div class="datepicker divCita" id="divCalendario" style="display: hidden;">
                        </div>
                        <div class="divCita" id="divHoras" style="display: hidden;">
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        onclick="limpiar();">Cerrar</button>

                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModalRepresentado" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                    <div id="username-page">
                        <div class="username-page-container">
                            <h1 class="title">Ingrese los siguientes datos</h1>
                            <form id="usernameForm" name="usernameForm">
                                <div class="form-group">
                                    <input type="text" id="nombre" placeholder="Nombre" autocomplete="off"
                                        class="form-control" />
                                </div>
                                <div class="form-group">
                                    <input type="text" id="paterno" placeholder="Apellido Paterno" autocomplete="off"
                                        class="form-control" />
                                </div>
                                <div class="form-group">
                                    <input type="text" id="materno" placeholder="Apellido Materno" autocomplete="off"
                                        class="form-control" />
                                </div>

                                <div class="form-group">
                                    <input type="text" id="txtcorreo" placeholder="Correo Electr&oacute;nico"
                                        class="form-control" />
                                </div>
                                <div class="form-group">
                                    <input type="text" id="txtcurp" placeholder="CURP" class="form-control" />
                                </div>

                                <div class="form-group">
                                    <input type="button" id="generarCita" class="btn btn-success"
                                        onClick="finalizarCita()" value="Generar Cita" />
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        onclick="limpiar()">Cerrar</button>

                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModalConsultarCita" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                    <div id="username-page">
                        <div class="username-page-container">
                            <h1 class="title">Ya tiene una cita registrada, de click en el boton para ver su comprobante
                            </h1>
                            <form id="usernameForm" name="usernameForm">


                                <div class="form-group">
                                    <button id="generarCita" type="button" class="btn btn-success"
                                        onClick="generarPDFCita()">Generar comprobante</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        onclick="limpiar()">Cerrar</button>

                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModalVisualizar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                    <div id="username-page">
                        <div class="username-page-container">
                            <h1 class="title">Su cita ha sido registrada, de click en el boton para descargar su
                                comprobante</h1>
                            <form id="usernameForm" name="usernameForm">


                                <div class="form-group">
                                    <button id="generarCita" type="button" class="btn btn-success"
                                        onClick="generarPDFCita()">Generar comprobante</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        onclick="limpiar()">Cerrar</button>

                </div>
            </div>
        </div>
    </div>

</div>

<div class="dropdown-divider"></div>

<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script src="/webjars/js-cookie/js.cookie.js"></script>

<script>
    const today = new Date()
    var fechaSelect = "";
    var tomorrow = new Date(today)
    limpiar = function () {
        $("#hddCveMunicipio").val("");
        $("#divHoras").empty();
        $("#nombre").val("");
        $("#paterno").val("");
        $("#materno").val("");
        $("#txtcorreo").val("");
        $("#txtcurp").val("");
        $("#hddIdCita").val("");
        $("#hddIdRepresentado").val("");
        $("#txtMunicipio").val("");
        location.reload(true);


    }

    cargarFrmRepresentado = function () {

        $("#myModalRepresentado").modal("show");
    };

    cargarFrmCalendario = function () {

        $("#myModalCalendario").modal("show");
    };
    cerrarFrmRepresentado = function () {

        $("#myModalRepresentado").modal("hide");
    };

    cerrarFrmCalendario = function () {

        $("#myModalCalendario").modal("hide");
    };
    var sortByTimeAsc = function (lhs, rhs) {
        var results;
        var lhsTemp = lhs.hrs.toString();
        var rhsTemp = rhs.hrs.toString();
        lhsTemp = parseInt(lhsTemp.toString().replace(":", ""));
        rhsTemp = parseInt(rhsTemp.toString().replace(":", ""));
        results = lhsTemp > rhsTemp ? 1 : lhsTemp < rhsTemp ? -1 : 0;


        return results;
    };
    var gridHoras = function (horas, espacio) {

        var tabla = "<table class='divCita'>";

        for (var x = 0; x < horas.length; x++) {
            var clase = "";
            var boton = "";
            if (x == 0)
                tabla += "<tr>";
            if (horas[x].disponible == "S") {
                clase = "disponible"
                boton = "onclick=generarCita('" + horas[x].hrs + "')";
            } else
                clase = "nodisponible"
            tabla += "<td class='" + clase + "'" + boton + ">" + horas[x].hrs + "</td>";
            if (((x + 1) % 2) == 0 && x > 0) {
                tabla += "</tr><tr>";
            }
        }

        tabla += "</table>";
        $("#" + espacio).empty();
        $("#" + espacio).append(tabla);
        $("#" + espacio).show();

    };
    actualizarCita = function (idRepresentado) {

        var cita = new Object();
        var representado = new Object();
        representado.idRepresentado = idRepresentado;
        cita.estatus = "G";
        cita.activo = "S";
        cita.idCita = $("#hddIdCita").val();
        cita.representado = representado;

        $
            .ajax({
                data: JSON.stringify(cita),
                url: 'actualizarCita',
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (data) {
                    $("#myModalVisualizar").modal("show");

                    //  llamarRequisitos();
                    // limpiar();
                },
                error: function (xhr, ajaxOptions,
                    thrownError) {
                }
            });

    };
    function ValidaEmail(email) {
        if (email == "")
            return false;
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    finalizarCita = function () {
        var idCita = $("#hddIdCita").val();
        var cveAdscripcion = $("#hddCveMunicipio").val();
        cveAdscripcion = cveAdscripcion.split(",");
        var cveMunicipio = cveAdscripcion[0];
        var municipio = new Object();
        municipio.idMunicipio = cveMunicipio;

        var nombre = $("#nombre").val();
        var paterno = $("#paterno").val();
        var materno = $("#materno").val();
        var correo = $("#txtcorreo").val();
        var curp = $("#txtcurp").val();
        var representado = new Object();
        representado.nombre = nombre;
        representado.paterno = paterno;
        representado.materno = materno;
        representado.correo = correo;
        representado.curp = curp;
        representado.municipio = municipio;
        if (curp !== "" && ValidaEmail(correo)) {

            $
                .ajax({
                    data: JSON.stringify(representado),
                    url: 'guardarRepresentadoCita',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (data) {
                        if (data.activo == "R") {
                            $("#hddIdCita").val(data.idRepresentado);
                            $("#myModalConsultarCita").modal("show");
                            $("#myModalRepresentado").modal("hide");

                        } else {

                            cerrarFrmRepresentado();
                            cerrarFrmCalendario();
                            actualizarCita(data.idRepresentado);

                        }


                    },
                    error: function (xhr, ajaxOptions,
                        thrownError) {
                    }
                });
        } else {
            bootbox.dialog({
                closeButton: true,
                message: "El correo electr&oacute;nico y curp son datos obligatorios para registrar su cita",
                title: "Atencion",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger",
                        callback: function () {
                            //                                                $('#firma-modal').modal('show');
                        }
                    }
                }
            });
        }
    }

    generarCita = function (hora) {
        console.log("la hora requerida es: " + hora + " fecha: " + fechaSelect)
        var cveAdscripcion = $("#hddCveMunicipio").val();

        var cveRegion = cveAdscripcion.split(",");
        var cita = new Object();
        cita.fechaCita = cambiarfecha(fechaSelect);
        cita.horaCita = hora;
        cita.estatus = "R";
        cita.activo = "S";
        cita.cveRegion = cveRegion[1];

        $
            .ajax({
                data: JSON.stringify(cita),
                url: 'guardarCita',
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (data) {

                    if(data.idCita>0){
                    $("#hddIdCita").val(data.idCita)
                    cargarFrmRepresentado();
                    }
                    else{
                        bootbox.dialog({
                closeButton: true,
                message: "El horario seleccionado ya no esta disponible, favor de elegir otra opci&oacute;n",
                title: "Atencion",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger",
                        callback: function () {
                            //                                                $('#firma-modal').modal('show');
                        }
                    }
                }
            });
                    }
                },
                error: function (xhr, ajaxOptions,
                    thrownError) {
                }
            });

    };
    consultaCitas = function (fecha) {
        fechaSelect = fecha;
        var cveAdscripcion = $("#hddCveMunicipio").val();
        console.log(fecha);
        cveAdscripcion = cveAdscripcion.split(",");
        var cita = new Object();
        cita.cveRegion = cveAdscripcion[1];
        cita.fechaCita = cambiarfecha(fecha);
        $
            .ajax({
                data: JSON.stringify(cita),
                url: 'listaCitas',
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (data) {
                    var horas = [];
                    console.log(data);
                   if(data.length>0){
                        $.each(data,function(index, elem){
                            var hora = new Object();
                            hora.hrs = elem.hora
                            hora.disponible = elem.activo;
                            horas.push(hora);
                        });
                        horas.sort(sortByTimeAsc);
                        gridHoras(horas, "divHoras")
                        console.log(horas);
                    } else {
                        $("#divHoras").empty();
                        $("#divHoras").append("No hay citas disponibles, seleccione otra fecha");
                    }


                },
                error: function (xhr, ajaxOptions,
                    thrownError) {
                }
            });

    };

    generarPDFCita = function (idCita) {
        if (typeof idCita == 'undefined' || idCita == "")
            idCita = $("#hddIdCita").val();

        console.log("IdCita" + idCita);
        var key = "";
        var data = "";

        key = "idCita";
        data = idCita;
        ;
        var url = "documentos/cita";

        var form = $('<form target="_blank"></form>').attr('action', url)
            .attr('method', 'post');
        // Add the one key/value
        form.append($("<input></input>").attr('type', 'hidden').attr(
            'name', key).attr('value', data));
        form.appendTo('body').submit();


    };

    llamarRequisitos = function () {


        var key = "";
        var data = "";

        key = "idCita";

        ;
        var url = "http://idp.edomex.gob.mx/sites/idp.edomex.gob.mx/files/files/15%20Requisitos%20IDP.pdf";

        var form = $('<form target="_blank"></form>').attr('action', url)

        // Add the one key/value

        form.appendTo('body').submit();

    };


    $("#txtMunicipio")
        .autocomplete(
            {
                source: function (request, response) {
                    var nombre = $("#txtMunicipio").val() == "" ? null
                        : "%" + $("#txtMunicipio").val()
                        + "%";
                    var participante = new Object();
                    participante.descMunicipio = nombre;
                    $
                        .ajax({
                            data: JSON.stringify(participante),
                            url: 'consultarmunicipios',
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
                                                label: item.descMunicipio + ", " + altasbajas(item.estado.descEstado),
                                                value: item.cveMunicipio + "," + item.cveRegion
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
                    $("#txtMunicipio").val(ui.item.label);
                    $("#hddCveMunicipio").val(ui.item.value);
                    $("#divCalendario").show();
                    console.log(tomorrow)
                    var dd = tomorrow.getDate();
                    var mm = tomorrow.getMonth() + 1;

                    var yyyy = tomorrow.getFullYear();
                    if (dd < 10) {
                        dd = '0' + dd;
                    }
                    if (mm < 10) {
                        mm = '0' + mm;
                    }
                    var newTomorrow = dd + '/' + mm + '/' + yyyy;
                    consultaCitas(newTomorrow);
                },
                open: function () {
                    $(this).removeClass("ui-corner-all").addClass(
                        "ui-corner-top");
                    $(this).autocomplete('widget').css('z-index', 10100);
                },
                close: function () {
                    $(this).removeClass("ui-corner-top").addClass(
                        "ui-corner-all");
                }
            });

    diaSiguiente = function () {
        console.log("dia siguiente");
        tomorrow.setDate(today.getDate() + 1);
        console.log("hoy es: " + today.getDate());
        console.log("mañana es: " + tomorrow.getDate());
        var manana = tomorrow.getDay();
        console.log("mañana: " + manana);
        if (manana == 6) {
            tomorrow.setDate(tomorrow.getDate() + 2);
            console.log("nuevo mañana sabado: " + tomorrow);
        } else if (manana == 0) {
            tomorrow.setDate(tomorrow.getDate() + 1);
            console.log("nuevo mañana domingo: " + tomorrow);
        }

    }

    function altasbajas(texto) {
        texto = texto.toLowerCase();
        var partes = texto.split(" ");
        texto="";
        for (var i = 0; i < partes.length; i++) {
            var parte = partes[i];
            if (i == 0)
                texto += parte.charAt(0).toUpperCase() + parte.slice(1, (parte.length))+" ";
            else if (parte.length > 2)
                texto += parte.charAt(0).toUpperCase() + parte.slice(1, (parte.length))+" ";
            else
                texto += parte+" ";
        }
        return texto;

    }

    $(function () {

        diaSiguiente();



        var noWeek = $.datepicker.noWeekends;
        console.log(noWeek);
        $(".datepicker").datepicker({
            closeText: 'Cerrar',
            prevText: '<Ant',
            nextText: 'Sig>',
            currentText: 'Hoy',
            monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
            dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
            dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb'],
            dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
            weekHeader: 'Sm',
            language: 'es',
            dateFormat: 'dd/mm/yy',
            minDate: tomorrow,
            beforeShowDay: $.datepicker.noWeekends,
            onSelect: function (dateText) {
                console.log("Selected date: " + dateText + "; input's current value: " + this.value);
                consultaCitas(dateText);
            }
        });
        $("#divCalendario").hide();

        cargarFrmCalendario();
    });
    $(function () {


    });




</script>
<script src="resources/js/chat.js"></script>
<script type="text/javascript" src="/resources/js/bootbox.min.js"></script>
<script type="text/javascript" src="/resources/js/loading.min.js"></script>