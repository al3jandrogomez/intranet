<style>
    .lista {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        cursor: pointer;
    }

</style>

<div class="form-group row">
    <h1>Descarga Formatos Informes Mensuales</h1>


</div>
<div id="frmMateriaPenal" class=" form-group row">
    <div class="dropdown-divider"></div>
    <div class="  form-group  btn-toolbar col-3 lista" id="listaInforme">
        
    </div>
    <div class="  form-group  btn-toolbar col-13 lista" id="listaIntructivo">
        
    </div>
    <div class="dropdown-divider"></div>

    <div id="listaOficios" style="display: block"></div>


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

        cargarListaDocumentos = function (idRegion) {
            var form = new Object();
            form.cveFormulario = 56;

            $.ajax({
                async: false,
                data: JSON.stringify(form),
                url: 'listadocumentosformularios',
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {
                    // 				$("#modalCorrect").empty();
                    // 				$("#modalCorrect").append("Procesando");
                    // 				$("#myModalCorrect").modal();
                },
                success: function (response) {
                    var informe = "";
                    var instructivo = "";
                    $("#listaInforme").empty();
                    $("#listaIntructivo").empty();

                    $.each(response, function (index, element) {
                        if (element.grupoDocumento.cveGrupoDocumento == 1) {
                            informe += ' <div class=""><a href="' + element.rutaDocumentoFormulario + '"><img src="resources/images/excel-icon.png" width="40"' +
                                'height="40"></a><label>' + element.descDocumentoFormulario + '</label></div>';
                        } else if (element.grupoDocumento.cveGrupoDocumento == 2) {
                            instructivo += ' <div class=""><a href="' + element.rutaDocumentoFormulario + '"><img src="resources/images/pdf3.png" width="40"' +
                                'height="40"></a><label>' + element.descDocumentoFormulario + '</label></div>';
                        }

                    });

                    $("#listaInforme").append(informe);
                    $("#listaIntructivo").append(instructivo);

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    $("#myModalError").append("Error al cargar los datos");
                    $("#myModalError").modal();
                }
            });

        };
        $(function () {
            cargarListaDocumentos();

        });
    </script>