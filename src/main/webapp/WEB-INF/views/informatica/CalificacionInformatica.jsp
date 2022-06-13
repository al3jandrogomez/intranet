<input type="hidden" id="hddIdRequisiciones" value="0">
<input type="hidden" id="hddCveArticulo" value="0">
<input type="hidden" id="hddIdDetalleRequisicion" value="0">
<div class="form-group row">
    <h1>Calificaci�n de Reportes</h1>


</div>
<div id="frmRequisiciones" style="display: none">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-10">
        <button type="button" class="btn btn-success btn-space"
                onclick="limpiarRequisiciones()">Limpiar</button>
        <button type="button" class="btn btn-primary btn-sm btn-space"
                id="enviarRequisicion">Enviar Calificaci�n</button>
        <button type="button" class="btn btn-warning btn-sm btn-space"
                onClick="buscarRequisiciones()">Salir</button>
    </div>
    <div class="dropdown-divider"></div>

    <div class="form-group ">
       <label for="example-search-input" class="col-10 col-form-label">Calificaci�n(1 mas baja, 5 mas alta)</label>
       <select id="cmbListaEstatus" class="form-control"
                >
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
        </select>
    </div>
    <div class="form-group ">
       <label for="example-search-input" class="col-10 col-form-label">Atenci�n por parte del personal de Inform�tica</label>
       <select id="cmbListaEstatus" class="form-control"
                >
            <option>Excelente</option>
            <option>Buena</option>
            <option>Regular</option>
            <option>Mala</option>
            <option>Pesima</option>
        </select>
    </div>
    <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Comentario sobre la atenci�n</label>
        <div>
            <input class="form-control" type="text" value="" id="txtobservaciones">
        </div>
    </div>
</div>
<div id="ListaRequisiciones" style="display: block">
    <div class="dropdown-divider"></div>
   
    <div class="dropdown-divider"></div>
    <div id="tblListaRequisiciones"></div>
</div>

<div id="ListaDetalleRequisiciones" style="display: none">
    <div class="dropdown-divider"></div>
</div>

<div class="modal fade" id="myModalCorrect" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
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

<div class="modal fade" id="myModalError" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Se encontraron
                    algunos errores</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">�Esta seguro que quiere generar una
                nueva solicitud?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary"
                        onclick="guardarRequisiciones();">Generar Nueva Solicitud</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModalEliminar" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">�Esta seguro eliminar la solicitud?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary"
                        onclick="eliminarSolicitudes();">Eliminar Solicitud</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">�la informacion capturada es correcta?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-primary"
                        onclick="agregarArticulo();">Si</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">�Esta seguro que quiere enviar la
                solicitud?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-primary"
                        onclick="enviarSolicitud();">Si</button>
            </div>
        </div>
    </div>
</div>


<script>
    Requisicion = function (idRequisicion, cveUsuario, cveAdscripcion, activo) {
        this.idRequisicion = idRequisicion;
        this.cveUsuario = cveUsuario;
        this.cveAdscripcion = cveAdscripcion;
        this.activo = activo;

    };

    imprimirDocumento = function (idRequisicion) {

        var key = "";
        var data = "";

        key = "idRequisicion";
        data = idRequisicion;
        ;
        var url = "/reportes/reporterequisicion";

        var form = $('<form target="_blank"></form>').attr('action', url).attr(
                'method', 'post');
        // Add the one key/value
        form.append($("<input></input>").attr('type', 'hidden').attr('name',
                key).attr('value', data));
        form.appendTo('body').submit();

    }

    agregarArticulo = function () {

        var idRequisicion = $("#hddIdRequisiciones").val() == 0 ? null : $(
                "#hddIdRequisiciones").val();
        var idDetalleRequisicion = $("#hddIdDetalleRequisicion").val() == 0 ? null
                : $("#hddIdDetalleRequisicion").val();
        var cveArticulo = $("#hddCveArticulo").val();
        var cantidad = $("#txtCantidadRequerida").val();

        var flag = 1;
        var faltante = "";
        if (cantidad == "") {
            faltante += "Falta ingresar la cantidad de articulos<br>";
            flag = 0;
        }
        if (cveArticulo == 0) {
            faltante += "Falta seleccionar un articulo<br>";
            flag = 0;
        }

        if (flag == 1) {
            dArticulo = new Articulo(cveArticulo);

            dRequisicion = new Requisicion(idRequisicion, null, null, "S")
            dDetalleRequisicion = new DetalleRequisicion(idDetalleRequisicion,
                    dRequisicion, dArticulo, cantidad, "S");

            dDetalleRequisicion = JSON.stringify(dDetalleRequisicion);
            console.log(dDetalleRequisicion);
            $
                    .ajax({
                        data: dDetalleRequisicion,
                        url: 'guardardrequisicion',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {

                        },
                        success: function (response) {
                            $("#modalCorrect").empty();
                            $("#myModalCorrect").modal('hide');
                            $("#myModal2").modal("hide");
                            if (response.idDetalleRequisicion > 0) {
                                $("#modalCorrect").append(
                                        "Se guardo el detalle");
                                limpiarRequisiciones();
                                mostrarDetalleRequisicion(idRequisicion);
                            } else {
                                $("#modalCorrect")
                                        .append(
                                                "El detalle  ya existe verifique los datos");
                                $("#myModalCorrect").modal();
                            }
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
        } else {
            $("#modalCorrect").empty();
            $("#myModalCorrect").modal('hide');
            $("#myModal2").modal("hide");
            $("#modalErrorText").empty();
            $("#modalErrorText").append(faltante);

            $("#myModalError").modal();

        }

    };

    guardarRequisiciones = function () {
        // 		$("#modalCorrect").empty();
        // 		$("#modalCorrect").append("Procesando");
        // 		$("#myModalCorrect").modal();
       // $("#enviarRequisicion").hide();

        $("#myModalCorrect").modal('hide');
        $("#myModal").modal('hide');
        dRequisicion = new Requisicion(null, 1, 2, "R");
        dRequisicion = JSON.stringify(dRequisicion);
        console.log(dRequisicion);
        $
                .ajax({
                    data: dRequisicion,
                    async: false,
                    url: '',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModal").hide();
                        $("#frmRequisiciones").show();
                        $("#ListaRequisiciones").hide();
                    },
                    success: function (response) {

                        if (response.idRequisicion > 0) {
                            $("#hddIdRequisiciones")
                                    .val(response.idRequisicion);
                            limpiarRequisiciones();
                        } else {
                            $("#modalCorrect")
                                    .append(
                                            "El detalle  ya existe verifique los datos");
                            $("#myModalCorrect").modal();
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError")
                                .append(
                                        "No se encontraron programas con los criterios ingresados");
                        $("#myModalError").modal();
                    }
                });
    };

    $("#txtAddArticulo")
            .autocomplete(
                    {
                        source: function (request, response) {
                            var nomArticulo = $("#txtAddArticulo").val() == "" ? null
                                    : "%" + $("#txtAddArticulo").val() + "%";

                            var dArticulo = new Articulo(null, nomArticulo,
                                    null, null, null, null, null, null, null);
                            dArticulo = JSON.stringify(dArticulo);
                            console.log(dArticulo);

                            $
                                    .ajax({
                                        data: dArticulo,
                                        url: 'buscarlikearticulos',
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
                                                                    label: item.descArticulo
                                                                            + " "
                                                                            + item.unidades[0].descUnidad,
                                                                    value: item.cveArticulo
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
                            $("#txtAddArticulo").val(ui.item.label);
                            $("#hddCveArticulo").val(ui.item.value);

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
    limpiarRequisiciones = function () {
        $("#hddCveArticulo").val(0);
        $("#hddIdDetalleRequisicion").val(0);
        $("#txtAddArticulo").val("");
        $("#txtCantidadRequerida").val("");

    };
    DetalleRequisicion = function (idDetalleRequisicion, requisicion, articulo,
            cantidadRequerida, activo) {
        this.idDetalleRequisicion = idDetalleRequisicion;
        this.requisicion = requisicion;
        this.articulo = articulo;
        this.cantidadRequerida = cantidadRequerida;
        this.activo = activo;

    };
    mostrarDetalleRequisicion = function (idRequisicion) {
        $("#frmRequisiciones").show();
        $("#ListaDetalleRequisiciones").show();
        $("#ListaRequisiciones").hide();
        $("#myModalCorrect").modal('hide');
        $("#myModal").modal('hide');
        $("#ListaDetalleRequisiciones").empty();
        dRequisicion = new Requisicion(idRequisicion, null, null, null);
        dRequisiciones = new DetalleRequisicion(null, dRequisicion, null, null,
                null);
        dRequisiciones = JSON.stringify(dRequisiciones);
        console.log(dRequisiciones);
        $
                .ajax({
                    data: dRequisiciones,
                    async: false,
                    url: '',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModal").hide();
                        if (response.length > 0) {
                            $("#enviarRequisicion").show();
                            $("#hddIdRequisiciones").val(idRequisicion);
                            $("#ListaRequisiciones").hide();
                            var lista = "<table class=\"table\"> <thead>";
                            lista += "<thead><tr><th>#</th> <th>Articulo</th><th>Cantidad</th> <th>&nbsp;</th></tr></thead><tbody>";

                            $
                                    .each(
                                            response,
                                            function (index, element) {

                                                lista += "<tr><th scope=\"row\">"
                                                        + (index + 1)
                                                        + "</th><td>"
                                                        + element.articulo.descArticulo
                                                        + " "
                                                        + element.articulo.marcas.descMarca
                                                        + " UNIDAD: "
                                                        + element.articulo.unidades.descUnidad

                                                        + "</td><td>"
                                                        + element.cantidadRequerida
                                                        + "</td><td><img onclick=\"cargarDetalleRequisicion("
                                                        + element.idDetalleRequisicion
                                                        + ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\"><img class='eliminarDetalle' onclick=\"eliminarArticulo("
                                                        + element.idDetalleRequisicion
                                                        + ")\" src=\"resources/images/delete.png\"  class=\"img-thumbnail\"></td></tr>";
                                            });
                            lista += "</tbody></table>";
                            $("#ListaDetalleRequisiciones").append(lista);

                        } else {
                            $("#enviarRequisicion").hide();
                            $("#hddIdRequisiciones").val(idRequisicion);
                            $("#ListaDetalleRequisiciones").append(
                                    "Sin Articulos");
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {

                        $("#myModalError")
                                .append(
                                        "No se encontraron programas con los criterios ingresados");
                        $("#myModalError").modal();
                    }
                });

    };

    buscarRequisiciones = function () {
        $("#ListaDetalleRequisiciones").hide();
        $("#tblListaRequisiciones").empty();
        $("#myModalCorrect").modal('hide');
        $("#myModal").modal('hide');
        dRequisicion = new Requisicion(null, null, 2, null);
        dRequisicion = JSON.stringify(dRequisicion);
        console.log(dRequisicion);
        $
                .ajax({
                    data: dRequisicion,
                    async: false,
                    url: 'buscarrequisiones',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModal").hide();
                        if (response.length > 0) {
                            var numero = "";
                            var fechaenvio = "";
                            var botones = "";
                            var motivo = "";
                            var lista = "<table class=\"table\"> <thead>";
                            lista += "<thead><tr><th>#</th> <th>Numero Reporte</th><th>Fecha Registro</th> <th>Fecha Envio</th> <th>Estatus</th><th>&nbsp;</th></tr></thead><tbody>";

                            $
                                    .each(
                                            response,
                                            function (index, element) {

                                                botones = "";
                                                numero = "";
                                                fechaenvio = "";
                                                motivo = "";
                                                if (element.numeroRequisicion == null)
                                                    numero = "";
                                                else
                                                    numero = element.numeroRequisicion
                                                            + "/"
                                                            + element.anioRequisicion;
                                                if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion >= 3
                                                        && element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion < 8) {
                                                    fechaenvio = cambiarFecha2(element.movRequisicion[0].fechaRegistro);
                                                    botones += "<img onclick=\"imprimirDocumento("
                                                            + element.idRequisicion
                                                            + ")\" class='documento' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\">";
                                                } else {
                                                    botones = "<img onclick=\"mostrarDetalleRequisicion("
                                                            + element.idRequisicion
                                                            + ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\"><img class='eliminar' id='"
                                                            + element.idRequisicion
                                                            + "'src=\"resources/images/delete.png\" onclick=\"eliminarSolicitudes(" + element.idRequisicion + ")\" class=\"img-thumbnail\">";
                                                }
                                                if (element.movRequisicion[0].observaciones !== null && element.movRequisicion[0].observaciones !== "") {
                                                    motivo = "<b>MOTIVO: "
                                                            + element.movRequisicion[0].observaciones
                                                            + "</b>";
                                                }

                                                lista += "<tr><th scope=\"row\">"
                                                        + (index + 1)
                                                        + "</th><td>"
                                                        + numero
                                                        + "</td><td>"
                                                        + cambiarFecha2(element.fechaRegistro)
                                                        + "</td><td>"
                                                        + fechaenvio
                                                        + "</td><td>"
                                                        + element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion
                                                        + " "
                                                        + motivo
                                                        + "</td><td>"
                                                        + botones
                                                        + "</td></tr>";
                                            });
                            lista += "</tbody></table>";
                            $("#tblListaRequisiciones").append(lista);
                            $("#frmRequisiciones").hide();
                            $("#ListaRequisiciones").show();

                        } else {
                            console.log(response.errorType + " "
                                    + response.mensaje);
                            $("#myModalCorrect").modal('hide');
                            $("#myModal").modal('hide');

                            if (typeof response.errorType) {
                                errores(response.errorType, response.mensaje);
                            } else {
                                $("#modalCorrect").append(
                                        "No hay requisisiciones capturadas");
                                $("#myModalCorrect").modal();
                            }

                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {

                        $("#myModalError")
                                .append(
                                        "No se encontraron programas con los criterios ingresados");
                        $("#myModalError").modal();
                    }
                });

    };
    cargarDetalleRequisicion = function (idDetalleRequisicion) {

        dDetalle = new DetalleRequisicion(idDetalleRequisicion, null, null,
                null, "S");
        dDetalle = JSON.stringify(dDetalle);
        console.log(dDetalle);

        $.ajax({
            data: dDetalle,
            async: false,
            url: 'buscardetallearticulo',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                $.each(response, function (index, element) {
                    $("#hddIdDetalleRequisicion").val(
                            element.idDetalleRequisicion);
                    $("#txtAddArticulo").val(element.articulo.descArticulo);
                    $("#hddCveArticulo").val(element.articulo.cveArticulo);
                    $("#txtCantidadRequerida").val(element.cantidadRequerida);
                })

            },
            error: function (xhr, ajaxOptions, thrownError) {

                $("#myModalError").append("Error al cargar el articulo");
                $("#myModalError").modal();
            }
        });

    };

    eliminarArticulo = function (idDetalleRequisicion) {
        var idRequisicion = $("#hddIdRequisiciones").val() == 0 ? null : $(
                "#hddIdRequisiciones").val();
        dDetalle = new DetalleRequisicion(idDetalleRequisicion, null, null,
                null, "S");
        dDetalle = JSON.stringify(dDetalle);
        console.log(dDetalle);

        $.ajax({
            data: dDetalle,
            async: false,
            url: 'eliminardetallearticulo',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                limpiarRequisiciones();
                mostrarDetalleRequisicion(idRequisicion);

            },
            error: function (xhr, ajaxOptions, thrownError) {

                $("#myModalError").append("No se pudo enviar la solicitud");
                $("#myModalError").modal();
            }
        });

    };

    Articulo = function (cveArticulo, descArticulo, especificacion, capacidad,
            unidad, marca, clave, version, partida) {
        this.cveArticulo = cveArticulo;
        this.descArticulo = descArticulo;
        this.especificacion = especificacion;
        this.capacidad = capacidad;
        this.unidades = unidad;
        this.marcas = marca;
        this.clave = clave;
        this.version = version;
        this.partidas = partida;

    };

    enviarSolicitud = function (idRequisicion) {
        idRequisicion = idRequisicion == null ? $("#hddIdRequisiciones").val()
                : idRequisicion;
        dRequisicion = new Requisicion(idRequisicion, null, 2, null);
        dRequisicion = JSON.stringify(dRequisicion);
        console.log(dRequisicion);
        $.ajax({
            data: dRequisicion,
            async: false,
            url: '',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                $("#modalCorrect").empty();
                $("#myModalCorrect").modal("hide");
                $("#myModal").modal('hide');
                $("#myModal3").modal('hide');
                if (response.idRequisicion > 0) {

                    $("#frmRequisiciones").hide();
                    $("#ListaDetalleRequisiciones").hide();
                    $("#ListaRequisiciones").show();

                    limpiarRequisiciones();
                    buscarRequisiciones();

                } else {
                    $("#modalCorrect").append(
                            "El detalle  ya existe verifique los datos");
                    $("#myModalCorrect").modal();
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {

                $("#myModalError").append("No se pudo enviar la solicitud");
                $("#myModalError").modal();
            }
        });

    }

    eliminarSolicitudes = function (idRequisicion) {
        idRequisicion = idRequisicion == null ? $("#hddIdRequisiciones").val()
                : idRequisicion;
        dRequisicion = new Requisicion(idRequisicion, null, 2, null);
        dRequisicion = JSON.stringify(dRequisicion);
        console.log(dRequisicion);
        $.ajax({
            data: dRequisicion,
            async: false,
            url: '',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                $("#modalCorrect").empty();
                $("#myModalCorrect").modal("hide");
                $("#myModalEliminar").modal('hide');

                if (response.idRequisicion > 0) {

                    $("#frmRequisiciones").hide();
                    $("#ListaDetalleRequisiciones").hide();
                    $("#ListaRequisiciones").show();

                    limpiarRequisiciones();
                    buscarRequisiciones();

                } else {
                    $("#modalCorrect").append(
                            "Error al eliminar la requisicion");
                    $("#myModalCorrect").modal();
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                $("#myModalEliminar").modal('hide');

                $("#myModalError").append("No se pudo enviar la solicitud");
                $("#myModalError").modal();
            }
        });
    };

    function AskForWebNotificationPermissions()

    {

        if (Notification) {

            Notification.requestPermission();

        }

    }
    notificar = function () {

        var options = {

            body: "Este es le cuerpo de la notificaci�n",

            icon: "imgs/logoNotifs.png"

        };

        var notif = new Notification("Ejemplo de notificaci�n", options);

    }

    $(function () {
        // 		AskForWebNotificationPermissions();
        // 		notificar();

        $("#guardarRequisicion").click(function () {
            $('#myModal').modal();

        });
        $("#agregarArticulo").click(function () {
            $('#myModal2').modal();

        });
        $(".eliminar").click(function () {
            $('#myModalEliminar').modal();

        });

        $("#txtAddArticulo")
                .autocomplete(
                        {
                            source: function (request, response) {
                                var nomArticulo = $("#txtAddArticulo").val() == "" ? null
                                        : "%" + $("#txtAddArticulo").val()
                                        + "%";

                                var dArticulo = new Articulo(null, nomArticulo,
                                        null, null, null, null, null, null,
                                        null);
                                dArticulo = JSON.stringify(dArticulo);
                                console.log(dArticulo);

                                $
                                        .ajax({
                                            data: dArticulo,
                                            url: 'buscarlikearticulos',
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
                                                                        label: item.descArticulo
                                                                                + " "
                                                                                + item.marcas.descMarca
                                                                                + " UNIDAD:"
                                                                                + item.unidades.descUnidad,
                                                                        value: item.cveArticulo
                                                                    }
                                                                }));
                                            },
                                            error: function (xhr, ajaxOptions,
                                                    thrownError) {
                                                $('#myModal').modal('hide');
                                                $("#myModalCorrect").modal(
                                                        "hide");
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
                                console.log(ui.item ? "Selected: "
                                        + ui.item.label
                                        : "Nothing selected, input was "
                                        + this.value);
                                $("#txtAddArticulo").val(ui.item.label);
                                $("#hddCveArticulo").val(ui.item.value);

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
        buscarRequisiciones();
        $("#enviarRequisicion").click(function () {
            $('#myModal3').modal();
        });

    });
</script>

