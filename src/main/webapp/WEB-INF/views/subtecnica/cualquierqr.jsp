<input type="hidden" id="hddIdEncuesta" value="0">
<input type="hidden" id="hddIdPregunta" value="0">
<input type="hidden" id="hddIdRespuesta" value="0">
<input type="hidden" id="hddIdAplicacion" value="0">


<div class="form-group row">
    <h1>Registro de Encuestas</h1>


</div>
<div id="frmEncuestas" >
    <div class="dropdown-divider"></div>

    <div class="  form-group row btn-toolbar col-10" id = "regEncuestas" style="display: block;">

       
        <div class="col-10">
            <label for="example-search-input" class="btn btn-secondary"><input class="form-control " type="text" value=""
                                                                               id="txtLiga">Liga pagina:</label>
        </div>
        <div class="col-10">
            <button type="button" class="btn btn-danger btn-space " onclick="generarqr()">Generar QR</button>

        </div>


    </div>

    <div id="testEncuestas" style="display: none;">
        <div class="col-10">

            <div class = "" id="listaPreguntas2">hola</div>
        </div>

    </div>

    <div class="  form-group row btn-toolbar col-10" id = "estadistica" style="display: none;">

    </div>

    <div class="modal fade" id="myModalRespuesta" tabindex="-1" role="dialog"
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
                <div class="modal-body" id="modalCorrect">
                    <label for="example-search-input" class="btn btn-secondary"><input class="form-control " type="text" value=""
                                                                                       id="txtDescRespuesta">Respuesta:</label>
                    <select id="cargaTipoRespuesta"></select>
                    <button type="button" class="btn btn-danger btn-space " onclick="agregarRespuesta()">Agregar Respuesta</button>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

                </div>
            </div>
        </div>
    </div>


</div>

<div class="dropdown-divider"></div>

<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script>
                         
                        generarqr  = function () {
                            var liga = $("#txtLiga").val();

                            console.log("IdCita" + liga);
                            var key = "";
                            var data = "";

                            key = "liga";
                            data = liga;
                            ;
                            var url = "liga";

                            var form = $('<form target="_blank"></form>').attr('action', url)
                                    .attr('method', 'post');
                            // Add the one key/value
                            form.append($("<input></input>").attr('type', 'hidden').attr(
                                    'name', key).attr('value', data));
                            form.appendTo('body').submit();


                        };



</script>

