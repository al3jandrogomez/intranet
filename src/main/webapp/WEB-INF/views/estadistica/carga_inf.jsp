<input type="hidden" id="hddCveAdscripcion" value="0">
<div class="form-group row">
    <h1>Envio de Informes Mensuales</h1>


</div>
<div id="frmMateriaPenal">
    <form id="formulario" action="">
        <div class="dropdown-divider"></div>
        <div id="wrap">
            <div class="field">
                <ul class="options">
                    <li>
                        <input type="file" id="myfile" name="files" class="rm-input"
                            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />


                    </li>
                </ul>
            </div>
            <!--            <progress id="progressBar" value="0" max="100" class="rm-progress"></progress>
            <div id="percentageCalc"></div>-->
        </div>
        <div class="form-group ">
            <label for="example-search-input" class="col-10 col-form-label">Escriba la adscripcion de la cual
                reporta:</label>
            <div class="col-10">
                <input class="form-control" type="text" value="" id="txtDescAdscripcion">
                <input type="hidden" value="" name="cveAdscripcion" id="cveAdscripcion">
            </div>
            <label for="example-search-input" class="col-10 col-form-label">A&ntilde;o que esta reportando:
                :</label>
            <div class="col-10">
                <select class="form-control" id="cmbAnios" name="" disabled>
                    <option value="2022">2022</option>


                </select>
            </div>

            <label for="example-search-input" class="col-10 col-form-label">Mes que esta reportando:
                :</label>
            <div class="col-10">
                <select class="form-control" id="cmbEstatus" name="numMes">
                    <option value="1">Enero</option>
                    <option value="2">Febrero</option>
                    <option value="3">Marzo</option>
                    <option value="4">Abril</option>
                    <option value="5">Mayo</option>
                    <option value="6">Junio</option>
                    <option value="7">Julio</option>
                    <option value="8">Agosto</option>
                    <option value="9">Septiembre</option>
                    <option value="10">Octubre</option>
                    <option value="11">Noviembre</option>
                    <option value="12">Diciembre</option>

                </select>
            </div>

            <div class="col-10">
                <input type="button" value="Subir Archivo" onClick="uploadFile()"
                    class="btn btn-success btn-sm btn-space" />
            </div>
            <div class="col-10">
                <input type="button" value="limpiar" onClick="limpiar()" class="btn btn-warning btn-sm btn-space" />
            </div>

        </div>
    </form>

    <div class="dropdown-divider"></div>

    <div id="listaInformes" style="display: block"></div>


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

        function uploadFile() {
            var archivos = $("#myfile")[0].files.length;
            var cveAdscripcion = $("#hddCveAdscripcion").val();
            if (archivos > 0 && cveAdscripcion > 0) {
                console.log("Se puede enviar archivo");
                $("#cveAdscripcion").val(cveAdscripcion);
                var form = $('#formulario')[0];
                var anio = $("#cmbAnios option:selected").val();
                var data = new FormData(form);
                data.append("cveAdscripcion", cveAdscripcion);
                data.append("anio", anio);

                $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "/rest/uploadMultiFiles",
                    data: data,

                    // prevent jQuery from automatically transforming the data into a query string
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 1000000,
                    success: function (image) {
                        if (image.estatus === "OK") {
                            $("#modalCorrect").empty();
                            $("#modalCorrect").append("Se subio el archivo con exito.");
                            $("#myModalCorrect").modal("show");
                            listaInforme();
                        }
                        else if (image.estatus === "EX") {
                            $("#modalCorrect").empty();
                            $("#modalCorrect").append("El archivo ya ha sido enviado");
                            $("#myModalCorrect").modal("show")
                        }
                    }
                });

            }
            else {
                console.log("no se puede enviar archivo");
            }


            //            $("#modalCorrect").empty();
            //           $("#modalCorrect").append("Se subio el archivo con exito");

            ///   $("#myModalCorrect").modal("show");


        }

        function limpiar() {
            $("#cveAdscripcion").val(0);
            $("#txtDescAdscripcion").val("");
            $("#hddCveAdscripcion").val(0);
            $("#myfile").val(null);

        }



        listaInforme = function () {
            var lista = "<table class=\"table\"> <thead>";
            lista += "<thead><tr><th>#</th> <th>Informe</th><th>fecha Recepcion</th> <th>Estatus</th></tr></thead><tbody>";

            if (validarSesion()) {
                $
                    .ajax({
                        async: false,
                        url: 'listainformes',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {
                            // 				$("#modalCorrect").empty();
                            // 				$("#modalCorrect").append("Procesando");
                            // 				$("#myModalCorrect").modal();
                        },
                        success: function (response) {
                            $("#listaInformes").empty();


                            $
                                .each(
                                    response,
                                    function (index, element) {
                                        var estatus = element.estatusInforme;
                                        var descripcion = "";
                                        if (estatus === "C")
                                            descripcion = "En proceso de valiaci&oaute;n del coordinador";
                                        else if (estatus === "E")
                                            descripcion = "Hubo un error al cargar el archivo";
                                        else if (estatus === "A")
                                            descripcion = "Informe Validado";
                                        else if (estatus === "R")
                                            descripcion = "Informe Rechazado";

                                        lista += "<tr><th scope=\"row\">"
                                            + (index + 1)
                                            + "</th><td>"
                                            + element.adscripcion.descAdscripcion
                                            + " "
                                            + "</td><td>"
                                            + element.fechaRegistro
                                            + "</td>"
                                            + "</td><td>"
                                            + descripcion
                                            + "</td>"
                                            + "<td></td></tr>";




                                    });
                            lista += "</tbody></table>";
                            $("#listaInformes").append(lista);


                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            $("#myModalError").append("Error al cargar los datos");
                            $("#myModalError").modal();
                        }
                    });
            }

        };
        $(function () {
            listaInforme();

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
                                        //                                                $("#txtNombreBusqueda").val("");
                                        //                                                $("#hddCveUsuario").val(0);
                                        //                                                $("#txtDescPuesto").val("");
                                        //                                                $("#hddCvePuesto").val(0);
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

        });
    </script>