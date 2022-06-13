<link rel="stylesheet" href="resources/css/adminpersonal/controlPersonal.css">
<input type="hidden" id="hddCveUsuario" value="0" />

<div class="form-group row">
    <h1>Administraci&oacute;n de Personal</h1>


</div>
<div id="frmConslutaPersonal">
    <div class="dropdown-divider"></div>
    <div class="form-group ">

        <label for="example-search-input" class="col-10 col-form-label">Nombre Servidor P&uacute;blico:</label>
        <label for="example-search-input" class="col-10 col-form-label"></label>

        <label for="example-search-input" class="col-10 col-form-label">Clave Servidor Público</label>
        <label for="example-search-input" class="col-10 col-form-label"></label>
        <label for="example-search-input" class="col-10 col-form-label">Clave Isemmym</label>
        <label for="example-search-input" class="col-10 col-form-label"></label>

    </div>
    <div class="  form-group row btn-toolbar col-10">

        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="">Nuevo</button>

        <button type="button" class="btn btn-success btn-space" onclick="limpiar()">Limpiar</button>

    </div>
    <div class="dropdown-divider"></div>
</div>

<div id="frmServidorPublico">
    <div class="dropdown-divider"></div>
    <div class="form-group ">

        <label for="example-search-input" class=" col-form-label">Nombre</label>

        <input class="form-control" type="text" value="" id="txtNombreBusqueda">

        <label for="example-search-input" class=" col-form-label">Paterno</label>

        <input class="form-control" type="text" value="" id="txtNombreBusqueda">

        <label for="example-search-input" class=" col-form-label">Materno</label>

        <input class="form-control" type="text" value="" id="txtNombreBusqueda">


    </div>
    <div class="  form-group row btn-toolbar col-10">

        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="">Buscar </button>
        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="">Nuevo</button>

        <button type="button" class="btn btn-success btn-space" onclick="limpiar()">Limpiar</button>

    </div>
    <div class="dropdown-divider"></div>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

    function cargarServidorPublico() {
        $("#frmInicioControlPersonal").hide();
        $("#frmServidorPublico").show();
    }

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
                                $("#hddCvePuesto").val(0);
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
                    cargarServidorPublico();


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



//        $(function () {
//        });
</script>