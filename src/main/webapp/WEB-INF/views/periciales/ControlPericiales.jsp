<input type="hidden" id="hddIdRequisiciones" value="0">
<input type="hidden" id="hddCveArticulo" value="0">
<input type="hidden" id="hddIdDetalleRequisicion" value="0">
<div class="form-group row">
    <h1>Recepción y turnado de Solicitudes de Interveción Pericial</h1>


</div>
<div id="frmRequisiciones" style="display: none">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-10">

        <button type="button" class="btn btn-primary btn-sm btn-space"
                id="enviarRequisicion">Autorizar Intervención</button>
        <button type="button" class="btn btn-danger btn-space eliminar">Rechazar Intervención</button>
        
        <button type="button" class="btn btn-warning btn-sm btn-space"
                onclick="buscarRequisiciones(3)">Salir</button>
        

    </div>
    <div id="adsSolicitante">
        <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Nombre
            del Perito que atendera</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtAddArticulo">
        </div>
    </div>
    
    <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Observaciones de la intervención</label>
        <div class="col-10">
            <input class="form-control" type="text" value=""
                   id="txtCantidadRequerida">
        </div>
    </div>
    </div>
    <div class="dropdown-divider"></div>
</div>
<div id="ListaRequisiciones" style="display: block">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-8">

        <select id="cmbListaEstatus" class="form-control"
                onchange='buscarRequisiciones(this.value)'>
            <option>Recibas</option>
            <option>Turnadas</option>
            <option>Finalizadas</option>
        </select>

    </div>
    <div class="  form-group row btn-toolbar col-10">


        <div class="col-4">
            <input class="form-control numero" type="text" value=""
                   id="txtNumero" placeholder="Numero de solicitud">
        </div>
        <div class="col-4">
            <input class="form-control numero" type="text" value="" id="txtAnio"
                   placeholder="Ańo del solicitud">
        </div>
        <div class="col-4">
            <button type="button" class="btn btn-warning btn-sm btn-space"
                    onclick="buscarRequisiciones()">Buscar</button>
            <button type="button" class="btn btn-primary btn-sm btn-space"
                    onclick="limpiarBusqueda()">Limpiar</button>
        </div>
        <div class="dropdown-divider"></div>
        <div id="tblListaSoluPericiales col-8">
             <table class="table"> <thead></thead><thead><tr><th>#</th> <th>Numero Solicitud</th><th>Fecha Registro</th> <th>Fecha Autorizacion</th> <th>Estatus</th><th>&nbsp;</th></tr></thead><tbody><tr><th scope="row">1</th><td><b>SOLICITUD 11/2020</b> <br>JUZGADO DE CONTROL DE TLALNEPANTLA</td><td>25/11/2020</td><td></td><td>RECIBIDA</td><td><img onclick="mostrarDetalleRequisicion(546, 'R')" class="modificar" src="resources/images/edit.jpg"></td></tr><tr><tr><th scope="row">4</th><td><b>REQUISICIÓN 12/2020</b> <br>JUZGADO DE CONTROL DE TOLUCA</td><td>13/11/2020</td><td></td><td>RECIBIDA</td><td><img onclick="mostrarDetalleRequisicion(542, 'R')" class="modificar" src="resources/images/edit.jpg"></td></tr></tbody></table>
        </div>

    </div>

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
            <div class="modal-body">żEsta seguro que quiere generar una
                nueva solicitud?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary"
                        onclick="guardarRequisiciones();">Generar Nueva Solicitud</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModalNoAut" tabindex="-1" role="dialog"
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
            <div class="modal-body">żEsta seguro que no desea autorizar la
                solicitud?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary"
                        onclick="autorizarSolicitud(2);">No autorizar solicitud</button>
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
            <div class="modal-body">żla informacion capturada es correcta?</div>
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
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">żEsta seguro que quiere autorizar la
                solicitud?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <button type="button" class="btn btn-primary"
                        onclick="autorizarSolicitud(1);">Si</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalRes" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Regresar
                    Requisicion</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>

                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Justificaci&oacute;n:</label>
                        <textarea class="form-control" id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" onclick="regresarRequisicion()">Regresar
                    Requisici&oacute;n</button>
            </div>
        </div>
    </div>
</div>


<script>
    Requisicion = function (idRequisicion, cveUsuario, cveAdscripcion, activo,
            cveEstatusRequisicion, numeroRequisicion, anioRequisicion) {
        this.numeroRequisicion = numeroRequisicion;
        this.anioRequisicion = anioRequisicion;
        this.idRequisicion = idRequisicion;
        this.cveUsuario = cveUsuario;
        this.cveAdscripcion = cveAdscripcion;
        this.activo = activo;
        this.cveEstatusRequisicion = cveEstatusRequisicion;

    };
    estatusRequisicion = function (cveEstatusRequisicion) {
        this.cveEstatusRequisicion = cveEstatusRequisicion;
    };
    movRequisicion = function (requisicion, estatusRequisicion, observaciones, idMovRequisicion) {
        this.requisicion = requisicion;
        this.estatusRequisicion = estatusRequisicion;
        this.observaciones = observaciones;
        this.idMovRequisicion = idMovRequisicion;

    };

    regresarRequisicion = function () {
        var idRequisicion = $("#hddIdRequisiciones").val() == 0 ? null : $(
                "#hddIdRequisiciones").val();

        var observaciones = $("#message-text").val();
        $("#exampleModalRes").modal('hide');
        console.log("regresar requisicion" + observaciones + " idRequisicion " + idRequisicion);
        dRequisicion = new Requisicion(idRequisicion, null, null, null, idRequisicion, null, null);
        dEstatusRequisicion = new estatusRequisicion(8);
        dMovRequisicion = new movRequisicion(dRequisicion, dEstatusRequisicion, observaciones, idRequisicion);

        dMovRequisicion = JSON.stringify(dMovRequisicion);
        console.log(dMovRequisicion);
        $
                .ajax({
                    data: dMovRequisicion,
                    url: '',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal('hide');
                        $("#myModal2").modal("hide");
                        if (response.idMovRequisicion > 0) {
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

    autorizarSolicitud = function (tipoAut) {
        var operacion = "autorizarRequ";
        var idRequisicion = $("#hddIdRequisiciones").val();
        dRequisicion = new Requisicion(idRequisicion, null, null, null);
        dRequisicion = JSON.stringify(dRequisicion);
        console.log(dRequisicion);

        if (tipoAut == 1)
            operacion = "autorizarreq";
        else if (tipoAut == 2)
            operacion = "noautorizarreq";

        $
                .ajax({
                    data: dRequisicion,
                    async: false,
                    url: operacion,
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#ListaDetalleRequisiciones").empty();
                        $("#modalCorrect").empty();
                        $("#myModal3").modal("hide");
                        $("#myModal3").hide();
                        $("#myModalNoAut").modal("hide");
                        $("#myModalNoAut").hide();
                        if (response.idRequisicion > 0) {
                            $("#hddIdRequisiciones").val(0);
                            $("#frmRequisiciones").hide();
                            $("#ListaRequisiciones").show();

                            limpiarRequisiciones();
                            buscarRequisiciones(3);

                        } else {
                            $("#modalCorrect")
                                    .append(
                                            "El detalle  ya existe verifique los datos");
                            $("#myModalCorrect").modal();
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModal3").hide();
                        $("#myModal3").modal('hide');
                        $("#myModalNoAut").hide();
                        $("#myModalNoAut").modal('hide');

                        $("#myModalError")
                                .append(
                                        "No se encontraron programas con los criterios ingresados");
                        $("#myModalError").modal();
                    }
                });

    };

    guardarRequisiciones = function (tipoAut) {
        // 		$("#modalCorrect").empty();
        // 		$("#modalCorrect").append("Procesando");
        // 		$("#myModalCorrect").modal();
        $("#enviarRequisicion").hide();

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

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModal").hide();
                        if (response.idRequisicion > 0) {
                            $("#hddIdRequisiciones")
                                    .val(response.idRequisicion);
                            $("#frmRequisiciones").show();
                            $("#ListaRequisiciones").hide();

                            limpiarRequisiciones();
                            buscarRequisiciones(3)

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
                                                                    label: item.descArticulo,
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
            cantidadRequerida, cantidadAutorizada, activo, observaciones) {
        this.idDetalleRequisicion = idDetalleRequisicion;
        this.requisicion = requisicion;
        this.articulo = articulo;
        this.cantidadRequerida = cantidadRequerida;
        this.cantidadAutorizada = cantidadAutorizada;
        this.activo = activo;
        this.observaciones = observaciones;

    };
    mostrarDetalleRequisicion = function (idRequisicion, tipoRequisicion) {
        $("#frmRequisiciones").show();
        $("#ListaDetalleRequisiciones").show();
        $("#ListaRequisiciones").hide();
        $("#myModalCorrect").modal('hide');
        $("#myModal").modal('hide');
        $("#ListaDetalleRequisiciones").empty();
        dRequisicion = new Requisicion(idRequisicion, null, null, null);
        dRequisiciones = new DetalleRequisicion(null, dRequisicion, null, null,
                null, null);
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
                            lista += "<thead><tr><th>#</th> <th>Articulo</th><th>Cantidad solicitada</th><th>Cantidad autorizada</th><th>Observaciones</th> <th>Disponibilidad en Almacen</th><th>&nbsp;</th></tr></thead><tbody>";

                            $
                                    .each(
                                            response,
                                            function (index, element) {
                                                var edicion = "";
                                                var observaciones = element.observaciones == "" ? "" : element.observaciones
                                                if (index == 0) {
                                                    $("#adsSolicitante")
                                                            .empty();
                                                    $("#adsSolicitante")
                                                            .append(
                                                                    "<h2 style='font-size:12px; font-weight:bold;'>"
                                                                    + element.requisicion.adscripcion.descAdscripcion
                                                                    + " Solicitud: " + tipoRequisicion + " " +
                                                                    +element.requisicion.numeroRequisicion
                                                                    + "/"
                                                                    + element.requisicion.anioRequisicion
                                                                    + "</h2> ");
                                                }
                                                if (element.requisicion.tipoRequisicion === 'V' && tipoRequisicion === "V") {
                                                    edicion = "<td><input  class ='txtAutorizadas' id = 'txt" + element.idDetalleRequisicion + "' type='text' value='" + element.cantidadAutorizada + "' />"
                                                            + "</td><td> <input class='txtObservaciones' type ='text' id='txt" + element.idDetalleRequisicion + "obs' value='" + element.observaciones + "' name = 'observacionesAutorizadas[]'/>"
                                                            + "</td>";
                                                } else if (element.requisicion.tipoRequisicion === 'R' && tipoRequisicion == "R") {
                                                    edicion = "<td><input  class ='txtAutorizadas' id = 'txt" + element.idDetalleRequisicion + "' type='text' value='" + element.cantidadAutorizada + "' />"
                                                            + "</td><td> <input class='txtObservaciones' type ='text' id='txt" + element.idDetalleRequisicion + "obs' value='" + element.observaciones + "' name = 'observacionesAutorizadas[]'/>"
                                                            + "</td>";
                                                } else {
                                                    edicion = "<td>" + element.cantidadAutorizada
                                                            + "</td><td>" + observaciones
                                                            + "</td>";
                                                }

                                                lista += "<tr><th scope=\"row\">"
                                                        + (index + 1)
                                                        + "</th><td>"
                                                        + element.articulo.descArticulo
                                                        + " "
                                                        + element.articulo.marcas.descMarca

                                                        + "</td><td>"
                                                        + element.cantidadRequerida
                                                        + "</td>" + edicion + "<td>&nbsp;"
                                                        //+ element.disponibilidad+
                                                        + "</td><td>"
                                                // 														<img onclick=\"cargarDetalleRequisicion("
                                                // 														+ element.idDetalleRequisicion
                                                // 														+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">
                                                "</td></tr>";
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

    testCampos = function () {
        var lista = [];
        var cantidadAutorizada = $(".txtAutorizadas");
        var observaciones = $(".txtObservaciones");

        console.log(" cantidad de campos cantidades="
                + cantidadAutorizada.length);
        console.log(" cantidad de campos observaciones= "
                + observaciones.length);

        var idRequisicion = $("#hddIdRequisiciones").val();
        $.each(cantidadAutorizada, function (index, element) {
            cantidad = element.value;
            observacion = observaciones[index].value;
            idDetalleRequisicion = element.id.split("txt");

            dRequisicion = new Requisicion(idRequisicion, null, null, "S")
            dDetalleRequisicion = new DetalleRequisicion(
                    idDetalleRequisicion[1], dRequisicion, null, null,
                    cantidad, "S", observacion);
            lista.push(dDetalleRequisicion);
        });
        lista = JSON.stringify(lista);
        console.log(lista);

        $
                .ajax({
                    data: lista,
                    url: '',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal('hide');
                        $("#myModal2").modal("hide");
                        if (response[0].idDetalleRequisicion > 0) {
                            $("#modalCorrect").append(
                                    "se guardar&oacute;n los cambios");
                            $("#myModalCorrect").modal();
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

    };

    // 	buscarRequisiciones = function(cveEstatusRequisicion) {
    // 		$("#ListaDetalleRequisiciones").hide();
    // 		$("#tblListaRequisiciones").empty();
    // 		$("#myModalCorrect").modal('hide');
    // 		$("#myModal").modal('hide');
    // 		dRequisicion = new Requisicion(cveEstatusRequisicion, null, null, null,
    // 				cveEstatusRequisicion);
    // 		dRequisicion = JSON.stringify(dRequisicion);
    // 		console.log(dRequisicion);
    // 		$
    // 				.ajax({
    // 					data : dRequisicion,
    // 					async : false,
    // 					url : 'buscarrequisionesaut',
    // 					dataType : 'json',
    // 					contentType : "application/json",
    // 					type : 'post',
    // 					beforeSend : function() {
    // 						$("#ListaDetalleRequisiciones").empty();
    // 						$("#ListaDetalleRequisiciones").hide();
    // 						$("#tblListaRequisiciones").empty();
    // 						$("#hddIdRequisiciones").val(0);
    // 					},
    // 					success : function(response) {
    // 						$("#modalCorrect").empty();
    // 						$("#myModalCorrect").modal("hide");
    // 						$("#myModal").hide();
    // 						if (response.length > 0) {
    // 							$("#ListaDetalleRequisiciones").empty();
    // 							var numero = "";
    // 							var descAdscripcion = "";
    // 							var fechaenvio = "";
    // 							var botones = "";
    // 							var estatus = "";
    // 							var lista = "<table class=\"table\"> <thead>";
    // 							lista += "<thead><tr><th>#</th> <th>Numero Solicitud</th><th>Fecha Registro</th> <th>Fecha Autorizacion</th> <th>Estatus</th><th>&nbsp;</th></tr></thead><tbody>";

    // 							$
    // 									.each(
    // 											response,
    // 											function(index, element) {

    // 												botones = "";
    // 												numero = "";
    // 												descAdscripcion = "";
    // 												fechaenvio = "";
    // 												estatus = "";
    // 												if (element.numeroRequisicion == null)
    // 													numero = "";
    // 												else
    // 													numero = element.numeroRequisicion
    // 															+ "/"
    // 															+ element.anioRequisicion;
    // 												if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 3) {
    // 													estatus = "RECIBIDA";

    // 													botones = "<img onclick=\"mostrarDetalleRequisicion("
    // 															+ element.idRequisicion
    // 															+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">";
    // 												} else if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion >= 4
    // 														&& element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion < 6) {
    // 													estatus = element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion;
    // 													botones = "<img onclick=\"mostrarDetalleRequisicion("
    // 															+ element.idRequisicion
    // 															+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">";
    // 													if(element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion>=3){
    // 														fechaenvio=cambiarFecha2(element.movRequisicion[0].fechaRegistro);

    // 													}
    // 												} else {
    // 													estatus = element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion;
    // 												}

    // 												if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 4 || element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 6){
    // 													fechaenvio = cambiarFecha2(element.movRequisicion[0].fechaRegistro);
    // 													botones+="<img onclick=\"imprimirDocumento("
    // 														+ element.idRequisicion
    // 														+ ")\" class='documento' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\">";
    // 												}else{
    // 													fechaenvio
    // 												}

    // 												lista += "<tr><th scope=\"row\">"
    // 														+ (index + 1)
    // 														+ "</th><td><b>"
    // 														+ numero
    // 														+ "</b> <br>"
    // 														+ element.adscripcion.descAdscripcion
    // 														+ "</td><td>"
    // 														+ cambiarFecha2(element.fechaRegistro)
    // 														+ "</td><td>"
    // 														+ fechaenvio
    // 														+ "</td><td>"
    // 														+ estatus
    // 														+ "</td><td>"
    // 														+ botones
    // 														+ "</td></tr>";
    // 											});
    // 							lista += "</tbody></table>";
    // 							$("#tblListaRequisiciones").append(lista);
    // 							$("#ListaRequisiciones").show();
    // 							$("#frmRequisiciones").hide();

    // 						} else {
    // 							$("#modalCorrect").append(
    // 									"No hay requisiciones capturadas");
    // 							$("#myModalCorrect").modal();
    // 						}
    // 					},
    // 					error : function(xhr, ajaxOptions, thrownError) {

    // 						$("#myModalError")
    // 								.append(
    // 										"No se encontraron programas con los criterios ingresados");
    // 						$("#myModalError").modal();
    // 					}
    // 				});

    // 	};

    buscarRequisiciones = function (cveEstatusRequisicion) {

        var numero = null;
        var anio = null;
        var busqueda = 1;
        var mensaje = "";
        var operacion = "";

        if (cveEstatusRequisicion == null) {
            numero = $("#txtNumero").val();
            anio = $("#txtAnio").val();
            if (numero === "") {
                mensaje += "Debe ingresar el numero de la solicitud<br>";
                busqueda = 0;
            }
            if (anio === "") {
                mensaje += "Debe ingresar el a&ntilde;o de la solicitud<br>";
                busqueda = 0;
            }
            operacion = "buscarrequisionesEntrega";
            busqueda = 1;

        } else {
            operacion = "buscarrequisionesaut";
            cveEstatusRequisicion = $("#cmbListaEstatus").val();
        }
        if (busqueda == 1) {
            $("#ListaRequisiciones").show();
            $("#frmRequisiciones").hide();
            $("#adsSolicitante").empty();
            $("#ListaDetalleRequisiciones").hide();
            $("#tblListaRequisiciones").empty();
            $("#myModalCorrect").modal('hide');
            $("#myModal").modal('hide');
            dRequisicion = new Requisicion(cveEstatusRequisicion, null, null,
                    null, cveEstatusRequisicion, numero, anio);
            dRequisicion = JSON.stringify(dRequisicion);
            console.log(dRequisicion);
            $
                    .ajax({
                        data: dRequisicion,
                        async: false,
                        url: "",
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {
                            $("#ListaDetalleRequisiciones").empty();
                            $("#ListaDetalleRequisiciones").hide();
                            $("#tblListaRequisiciones").empty();
                            $("#hddIdRequisiciones").val(0);
                        },
                        success: function (response) {
                            $("#modalCorrect").empty();
                            $("#myModalCorrect").modal("hide");
                            $("#myModal").hide();
                            if (response.length > 0) {
                                $("#ListaDetalleRequisiciones").empty();
                                var numero = "";
                                var descAdscripcion = "";
                                var fechaenvio = "";
                                var botones = "";
                                var estatus = "";
                                var lista = "<table class=\"table\"> <thead>";
                                lista += "<thead><tr><th>#</th> <th>Numero Solicitud</th><th>Fecha Registro</th> <th>Fecha Autorizacion</th> <th>Estatus</th><th>&nbsp;</th></tr></thead><tbody>";

                                $
                                        .each(
                                                response,
                                                function (index, element) {

                                                    botones = "";
                                                    numero = "";
                                                    descAdscripcion = "";
                                                    fechaenvio = "";
                                                    estatus = "";
                                                    if (element.numeroRequisicion == null)
                                                        numero = "VALE DE ALMACÉN";
                                                    else
                                                        numero = "REQUISICIÓN " + element.numeroRequisicion
                                                                + "/"
                                                                + element.anioRequisicion;
                                                    if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 3) {
                                                        estatus = "RECIBIDA";

                                                        botones = "<img onclick=\"mostrarDetalleRequisicion("
                                                                + element.idRequisicion
                                                                + ",'" + element.tipoRequisicion + "')\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">";
                                                    } else if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion >= 4
                                                            && element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion <= 6) {
                                                        estatus = element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion;
                                                        botones = "<img onclick=\"mostrarDetalleRequisicion("
                                                                + element.idRequisicion
                                                                + ",'" + element.tipoRequisicion + "')\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">";
                                                        if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion >= 3) {
                                                            fechaenvio = cambiarFecha2(element.movRequisicion[0].fechaRegistro);

                                                        }
                                                    } else {
                                                        estatus = element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion;
                                                    }

                                                    if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 4
                                                            || element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 6 || element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 7) {
                                                        fechaenvio = cambiarFecha2(element.movRequisicion[0].fechaRegistro);
                                                        botones += "<img onclick=\"imprimirDocumento("
                                                                + element.idRequisicion
                                                                + ")\" class='documento' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\">";
                                                    } else {
                                                        fechaenvio
                                                    }

                                                    lista += "<tr><th scope=\"row\">"
                                                            + (index + 1)
                                                            + "</th><td><b>"
                                                            + numero
                                                            + "</b> <br>"
                                                            + element.adscripcion.descAdscripcion + "-" + element.tipoRequisicion
                                                            + "</td><td>"
                                                            + cambiarFecha2(element.fechaRegistro)
                                                            + "</td><td>"
                                                            + fechaenvio
                                                            + "</td><td>"
                                                            + estatus
                                                            + "</td><td>"
                                                            + botones
                                                            + "</td></tr>";
                                                });
                                lista += "</tbody></table>";
                                $("#tblListaRequisiciones").append(lista);
                                $("#ListaRequisiciones").show();
                                $("#frmRequisiciones").hide();

                            } else {
                                $("#modalCorrect").append(
                                        "No hay requisiciones capturadas");
                                $("#myModalCorrect").modal();
                            }
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            $("#modalErrorText").empty();
                            $("#modalErrorText")
                                    .append(
                                            "No se encontraron programas con los criterios ingresados");
                            $("#myModalError").modal();
                        }
                    });
        } else {
            $("#modalErrorText").empty();
            $("#modalErrorText").append(mensaje);
            $("#myModalError").modal();
        }

    };
    limpiarBusqueda = function () {
        $("#txtNumero").val("");
        $("#txtAnio").val("");
        var cmbEstatus = $("#cmbListaEstatus").val();
        buscarRequisiciones(cmbEstatus);

    }
    cargarDetalleRequisicion = function (idDetalleRequisicion) {
        var cantidadAutorizada = 0;
        var observaciones = "";
        if ($("#txt" + idDetalleRequisicion).length == 0) {
            cantidadAutorizada = $("#td" + idDetalleRequisicion).text();
            $("#td" + idDetalleRequisicion).empty();
            observaciones = $("#td" + idDetalleRequisicion + "obs").text();
            $("#td" + idDetalleRequisicion + "obs").empty();
            var input = "<input id ='txt" + idDetalleRequisicion + "' type='text' value='" + cantidadAutorizada + "'/>";
            var observaciones = "<input id ='txt" + idDetalleRequisicion + "obs' type='text' value='" + observaciones + "'/>";
            $("#td" + idDetalleRequisicion).append(input);
            $("#td" + idDetalleRequisicion + "obs").append(observaciones);

        } else {
            var idRequisicion = $("#hddIdRequisiciones").val();
            cantidadAutorizada = $("#txt" + idDetalleRequisicion).val();
            observaciones = $("#txt" + idDetalleRequisicion + "obs").val();

            dRequisicion = new Requisicion(idRequisicion, null, null, "S")
            dDetalleRequisicion = new DetalleRequisicion(idDetalleRequisicion,
                    dRequisicion, null, cantidadAutorizada, "S", observaciones);

            dDetalleRequisicion = JSON.stringify(dDetalleRequisicion);
            console.log(dDetalleRequisicion);
            $
                    .ajax({
                        data: dDetalleRequisicion,
                        url: '',
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

        }

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
                    buscarRequisiciones(3);

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
    var EstatusReq = function (cveEstatusRequisicion) {
        this.cveEstatusRequisicion = cveEstatusRequisicion;
    }

    ListaEstatus = function () {
        dEstatusReq = new EstatusReq(null);
        dEstatusReq = JSON.stringify(dEstatusReq);
        console.log(dEstatusReq);
        $
                .ajax({
                    data: dEstatusReq,
                    async: false,
                    url: 'listaestatusreq',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModalEliminar").modal('hide');
                        var combo = "";
                        var desc = "";
                        if (response.length > 0) {
                            $
                                    .each(
                                            response,
                                            function (index, element) {

                                                if (element.cveEstatusRequisicion > 2) {
                                                    desc = element.cveEstatusRequisicion === 3 ? "RECIBIDA"
                                                            : element.descEstatusRequisicion
                                                    combo += "<option value='" + element.cveEstatusRequisicion + "'>"
                                                            + desc
                                                            + "</option>";
                                                }
                                            });
                            $("#cmbListaEstatus").append(combo);

                        } else {
                            $("#modalCorrect").append(
                                    "Error al eliminar la requisicion");
                            $("#myModalCorrect").modal();
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalEliminar").modal('hide');

                        $("#myModalError").append(
                                "No se pudo enviar la solicitud");
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

            body: "Este es le cuerpo de la notificación",

            icon: "imgs/logoNotifs.png"

        };

        var notif = new Notification("Ejemplo de notificación", options);

    }

    $(function () {
        // 		AskForWebNotificationPermissions();
        // 		notificar();
        //ListaEstatus();

        $("#guardarRequisicion").click(function () {
            $('#myModal').modal();

        });
        $("#agregarArticulo").click(function () {
            $('#myModal2').modal();

        });
        $(".eliminar").click(function () {
            $('#myModalNoAut').modal();

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
                                                                                + item.marcas.descMarca,
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
        //buscarRequisiciones(3);
        $("#enviarRequisicion").click(function () {
            $('#myModal3').modal();
        });

    });
</script>

