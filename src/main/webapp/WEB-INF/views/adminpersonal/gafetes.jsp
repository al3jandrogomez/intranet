<input type="hidden" id="hddCveUsuario" value="0" />
<input type="hidden" id="hddCveAdscripcion" value="0" />
<input type="hidden" id="hddCvePuesto" value="0" />
<input type="hidden" id="hddCvePuestoNominal" value="0" />
<div class="form-group row">
    <h1>Gafetes</h1>


</div>
<div id="frmMateriaPenal">
    <div class="dropdown-divider"></div>
    <div class="form-group ">

        <label for="example-search-input" class="col-10 col-form-label">Nombre Servidor P&uacute;blico:</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtNombreBusqueda">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Adscripcion:</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtDescAdscripcion">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Puesto Nominal:</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtDescPuesto">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Puesto Funcional:</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtDescPuestoNominal">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Región:</label>
        <select class="form-control" id="cmbRegion">
            <option value="0">Seleccione</option>
            <option value="3">Oriente</option>
            <option value="1">Toluca</option>
            <option value="2">Nororiente</option>
        </select>
    </div>
    <div class="  form-group row btn-toolbar col-10">

        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="generarGafetes(1)">Generar</button>
        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="generarGafetes(2)">Generar PVC</button>
        <button type="button" class="btn btn-success btn-space" onclick="limpiar()">Limpiar</button>

    </div>
    <div class="dropdown-divider"></div>


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
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                    <button type="button" class="btn btn-primary" onclick="guardarMateriaPenal();">Guardar
                        Articulo</button>
                </div>
            </div>
        </div>
    </div>
    <script>

        $("#txtNombreBusqueda")
            .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtNombreBusqueda").val() == "" ? null
                            : "%"
                            + $("#txtNombreBusqueda").val().replace(
                                /\s/g, "%") + "%";


                        var dNombre = new Object();
                        dNombre.nombreCompleto = nombre;
                        dNombre = JSON.stringify(dNombre);
                        console.log(dNombre);

                        $
                            .ajax({
                                data: dNombre,
                                url: 'buscarpersonal',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {
                                    $("#txtDescAdscripcion").val("");
                                    $("#hddCveAdscripcion").val(0);
                                    $("#txtDescPuesto").val("");
                                    $("#txtDescPuestoNominal").val("");
                                    $("#hddCvePuesto").val(0);
                                    $("#hddCvePuestoNominal").val(0);
                                    $("#cmbRegion").val(0);
                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.nombreCompleto,
                                                    value: item.cveUsuario
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
                        $("#txtNombreBusqueda").val(ui.item.label);
                        $("#hddCveUsuario").val(ui.item.value);


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

        $("#txtDescAdscripcion")
            .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtDescAdscripcion").val() == "" ? null
                            : "%"
                            + $("#txtDescAdscripcion").val().replace(
                                /\s/g, "%") + "%";

                        var dNombre = new Object();
                        dNombre.descAdscripcion = nombre;
                        dNombre = JSON.stringify(dNombre);
                        console.log(dNombre);

                        $
                            .ajax({
                                data: dNombre,
                                url: 'buscaradscripciones',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {
                                    $("#txtNombreBusqueda").val("");
                                    $("#hddCveUsuario").val(0);
                                    $("#txtDescPuesto").val("");
                                    $("#txtDescPuestoNominal").val("");
                                    $("#hddCvePuesto").val(0);
                                    $("#hddCvePuestoNominal").val(0);
                                    $("#cmbRegion").val(0);
                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.descAdscripcion,
                                                    value: item.cveAdscripcion
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
                        $("#txtDescAdscripcion").val(ui.item.label);
                        $("#hddCveAdscripcion").val(ui.item.value);

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

        $("#txtDescPuesto")
            .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtDescPuesto").val() == "" ? null
                            : "%"
                            + $("#txtDescPuesto").val().replace(
                                /\s/g, "%") + "%";

                        var dNombre = new Object();
                        dNombre.descPuesto = nombre;
                        dNombre = JSON.stringify(dNombre);
                        console.log(dNombre);

                        $
                            .ajax({
                                data: dNombre,
                                url: 'consultapuestos',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {
                                    $("#txtNombreBusqueda").val("");
                                    $("#hddCveUsuario").val(0);
                                    $("#txtDescAdscripcion").val("");
                                    $("#hddCveAdscripcion").val(0);
                                    $("#cmbRegion").val(0);
                                    
                                    $("#txtDescPuestoNominal").val("");
                                    
                                    $("#hddCvePuestoNominal").val(0);
                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.descPuesto,
                                                    value: item.cvePuesto
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
                        $("#txtDescPuesto").val(ui.item.label);
                        $("#hddCvePuesto").val(ui.item.value);

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

                $("#txtDescPuestoNominal")
            .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtDescPuestoNominal").val() == "" ? null
                            : "%"
                            + $("#txtDescPuestoNominal").val().replace(
                                /\s/g, "%") + "%";

                        var dNombre = new Object();
                        dNombre.descPuestoFuncional = nombre;
                        dNombre = JSON.stringify(dNombre);
                        console.log(dNombre);

                        $
                            .ajax({
                                data: dNombre,
                                url: 'consultapuestosnominal',
                                dataType: 'json',
                                contentType: "application/json",
                                type: 'post',
                                beforeSend: function () {
                                    $("#txtNombreBusqueda").val("");
                                    $("#hddCveUsuario").val(0);
                                    $("#txtDescAdscripcion").val("");
                                    $("#hddCveAdscripcion").val(0);
                                   
                                    $("#txtDescPuesto").val("");
                                    
                                    $("#hddCvePuesto").val(0);
                                    
                                },
                                success: function (data) {
                                    response($
                                        .map(
                                            data,
                                            function (item) {
                                                return {
                                                    label: item.descPuestoFuncional,
                                                    value: item.cvePuestoFuncional
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
                        $("#txtDescPuestoNominal").val(ui.item.label);
                        $("#hddCvePuestoNominal").val(ui.item.value);

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

        var generarGafetes = function (tipoGafete) {
            console.log("Generar Gafetes");
            imprimirDocumento2(tipoGafete);

        };

        imprimirDocumento2 = function (tipoGafete) {

            var key = "";
            var data = "";
            var cveUsuario = $("#hddCveUsuario").val();
            var cveAdscripcion = $("#hddCveAdscripcion").val();
            var cvePuesto = $("#hddCvePuesto").val();
            var cvePuestoNominal = $("#hddCvePuestoNominal").val();
            var cveRegion = $("#cmbRegion option:selected").val();
            var datos = new Object();
            var personal = new Object();
            var adscripcion = new Object();
            var puesto = new Object();
            adscripcion.cveAdscripcion = cveAdscripcion;
            personal.cveUsuario = cveUsuario;
            if (cveUsuario != null)
                datos.personal = personal;
            else if (cveAdscripcion != null)
                datos.adscripcion = adscripcion;
            else if (cvePuesto != null)
                datos.cvePuesto = cvePuesto;
            else if (cveRegion != null)
                datos.cveRegion = cveRegion;
                else if (cvePuestoNominal != null)
                datos.cvePuestoNominal = cvePuestoNomial;
                


            key = "cveUsuario";
            data = cveUsuario;
            ;
            var url = "";
            if (tipoGafete == 1)
                url = "/gafetes";
            else
                url = "/gafetespvc";
            var form = $('<form target="_blank"></form>').attr('action', url).attr(
                'method', 'post');
            // Add the one key/value
            form.append($("<input></input>").attr('type', 'hidden').attr('name',
                key).attr('value', data));
            key = "cveAdscripcion";
            data = cveAdscripcion;
            form.append($("<input></input>").attr('type', 'hidden').attr('name',
                key).attr('value', data));
            key = "cvePuesto";
            data = cvePuesto;
            form.append($("<input></input>").attr('type', 'hidden').attr('name',
                key).attr('value', data));
            key = "cveRegion";
            data = cveRegion;
            form.append($("<input></input>").attr('type', 'hidden').attr('name',
                key).attr('value', data));
                key = "cvePuestoNominal";
            data = cvePuestoNominal;
            form.append($("<input></input>").attr('type', 'hidden').attr('name',
                key).attr('value', data));
            form.appendTo('body').submit();

        }

        $(function () {
            $("#cmbRegion").change(function () {
                $("#txtNombreBusqueda").val("");
                $("#hddCveUsuario").val(0);
                $("#txtDescAdscripcion").val("");
                $("#hddCveAdscripcion").val(0);
                $("#hddCvePuesto").val(0);
            });
        });
    </script>