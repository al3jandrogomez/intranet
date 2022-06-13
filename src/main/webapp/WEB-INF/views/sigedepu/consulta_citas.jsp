<input type="hidden" id="hddIdRepresentado" value="0" />
<div class="form-group row">
    <h1>Consulta de Citas</h1>


</div>
<div id="frmArticulos">
    <div class="dropdown-divider"></div>
    <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Fecha:</label>
        <div class="col-10">
            <input class="form-control datepicker" type="text" value="" id="txtFechaRegistro">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Region:</label>
        <div class="col-10">
            <select class="form-control" id="cmbRegion">
                <option value="1">Toluca</option>
                <option value="2">Nororiente</option>
                <option value="3">Oriente</option>
            </select>
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Estatus de Cita:</label>
        <div class="col-10">
            <select class="form-control" id="cmbEstatusCita">
                <option value="G">Generadas</option>
                <option value="T">Terminadas</option>
                
            </select>
        </div>
        <label for="example-search-input" class="col-10 col-form-label">N&uacute;mero de Folio
            :</label>
        <div class="col-10">
            <input class="form-control " type="text" value="" id="txtNoFolio">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Nombre Peticionario
            :</label>
        <div class="col-10">
            <input class="form-control " type="text" value="" id="txtNombreCompleto">
        </div>
    </div>
    <div class="  form-group row btn-toolbar col-10">
        <!-- 		<button type="button" class="btn btn-primary btn-sm btn-space" -->
        <!-- 			id="guardarArticulo">Guardar</button> -->
        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="buscarCitas()">Buscar</button>

    </div>
    <div class="dropdown-divider"></div>

    <div id="listaCitas" style="display: block"></div>


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
                <div class="modal-body" id="modalCorrect">hola</div>
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
                <div id="modalErrorText" class="modal-body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="/resources/js/bootbox.min.js"></script>
    <script type="text/javascript" src="/resources/js/loading.min.js"></script>



    <script type="text/javascript">

        regiones = function (id, nombre) {
            this.id = id;
            this.nombre = nombre;

        };

        buscarCitas = function (idRegion) {
            var region = "";
            var noFolio = $("#txtNoFolio").val();
            var idRepresentado = $("#hddIdRepresentado").val();
            var representado = new Object();
            if (idRegion == null) {
                region = $("#cmbRegion").val();
            } else {
                region = idRegion;
            }

            var cita = new Object();
            var fechaCita = $("#txtFechaRegistro").val();

            cita.fechaCita = cambiarfecha(fechaCita);
            if (noFolio !== "")
                cita.idCita = noFolio;

            cita.activo = "S";
            cita.estatus = $("#cmbEstatusCita option:selected").val();
            cita.cveRegion = region;
            if (idRepresentado > 0) {
                representado.idRepresentado = idRepresentado;
                cita.representado = representado;
            }


            $
                .ajax({
                    data: JSON.stringify(cita),
                    url: 'buscarCitasActivas',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                        $("#listaCitas").empty();
                    },
                    success: function (response) {
                        $("#listaCitas").empty();
                        var lista = "<table class=\"table\"> <thead>";
                        var nombre = "";
                        var estatus = "";
                        var fechaCita = "";
                        var defensor = "";
                        var asignacion = "";
                        var firma = "";

                        lista += "<thead><tr><th>#</th> <th>folio </th><th>peticionario </th><th>Fecha Cita</th><th>Hora de la Cita</th><th>Estatus</th><th>&nbsp;</th></thead><tbody>";
                        $
                            .each(
                                response,
                                function (index, element) {
                                    if (element.estatus !== "R") {
                                        if (element.estatus == "G")
                                            estatus = "Cita Registrada";
                                        else if (element.estatus == "T")
                                            estatus = "Cita Terminada";
                                        nombre = element.representado.nombre + " " + element.representado.paterno + " " + element.representado.materno;
                                        fechaCita = cambiarFecha2(element.fechaCita);
                                        lista += "<tr><th scope=\"row\">"
                                            + (index + 1)
                                            + "</th><td>"
                                            + element.idCita
                                            + "</td><td>"
                                            + nombre
                                            + "</td><td>"
                                            + fechaCita
                                            + "</td><td>"
                                            + element.horaCita
                                            + "</td><td>"
                                            + estatus
                                            + "</td><td><input type='button' value='Imprimir Cita' onclick='generarPDFCita(" + element.idCita + ")' ></td>" +
                                            "<td>&nbsp;</td></tr>";
                                    }
                                });
                        lista += "</tbody></table>";

                        $("#listaCitas").append(lista);


                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalCorrect").modal("hide");
                        $("#myModal2").modal("hide");

                        $("#myModalError")
                            .append(
                                "No se encontraron programas con los criterios ingresados");
                        $("#myModalError").modal();
                    }
                });

        };

        generarPDFOficio = function (idSolicitudAtencion) {

            console.log("idSolicitudAtencion:" + idSolicitudAtencion);
            var key = "";
            var data = "";

            key = "idSolicitudAtencion";
            data = idSolicitudAtencion;
            ;
            var url = "/documentos/oficio";

            var form = $('<form target="_blank"></form>').attr('action', url)
                .attr('method', 'post');
            // Add the one key/value
            form.append($("<input></input>").attr('type', 'hidden').attr(
                'name', key).attr('value', data));
            form.appendTo('body').submit();

        };
        generarPDFCita = function (idCita) {

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



        var mostrarFormulario = function () {
            $('#firma-modal').modal('show');
        };
        $("#txtNombreCompleto")
            .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtNombreCompleto").val() == "" ? null
                            : "%"
                            + $("#txtNombreCompleto").val().replace(
                                /\s/g, "%") + "%";



                        var representado = new Object();
                        representado.nombre = nombre;
                        var cita = new Object();
                        cita.representado = representado;


                        $
                            .ajax({
                                data: JSON.stringify(cita),
                                url: 'buscarrepresentado',
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
                                                    label: item.representado.nombre + " " + item.representado.paterno + " " + item.representado.materno,
                                                    value: item.representado.idRepresentado
                                                }
                                            }));
                                },
                                error: function (xhr, ajaxOptions,
                                    thrownError) {
                                    $('#myModal').modal('hide');
                                    $("#myModalCorrect").modal("hide");
                                    $("#myModalError")
                                        .append(
                                            "No se pudo registrar el articulo, verifique los datos");
                                    $("#myModalError").modal();
                                }
                            });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: " + ui.item.label
                            : "Nothing selected, input was "
                            + this.value);
                        $("#txtNombreCompleto").val(ui.item.label);
                        $("#hddIdRepresentado").val(ui.item.value);
                        buscarCitas();


                        //$("#txtnombre").val(ui.item.value);

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








        $(function () {

            $(".datepicker").datepicker({
                dateFormat: 'dd/mm/yy',
                setDate: 'today'
            });
            var region = $("#idRegion").val();
            console.log("Region Recibida:" + region);
            //            if (region == 1)
            //                region = 2;
            //            else if (region == 2)
            //                region = 3;
            //            else if (region == 3)
            //                region = 1;
            console.log("Region con cambio:" + region);

            $("#cmbRegion").val(region);
            buscarCitas(region);


        });
    </script>