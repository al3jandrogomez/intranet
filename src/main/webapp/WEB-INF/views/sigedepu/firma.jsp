<div class="page-header">
    <h3><span class="fa fa-pencil-square-o fa-fw"></span>Editor de documentos electronicos</h3>
</div>
<form role="form" action="/generador/editor/firmar" method="POST" id="documento-form" data-toggle="validator">
            
            <div class="" id="errorFirma"></div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Datos del documento</h3>
                </div>
                <div class="panel-body">
                
                    <div class="row">
                        <div class="col-xs-12 col-lg-12 col-md-12">
                            <div class="pull-right">
                                <div id="messages"></div>
                                <div class="btn-group dropup">
                                    <button type="submit" class="btn btn-default" id="guardar-btn">Guardar</button>
                                    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="caret"></span>
                                        <span class="sr-only">Opciones</span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#" id="firma-btn">Firmar</a></li>
                                        <li role="separator" class="divider"></li>
                                        
                                    </ul>
                                </div>
                                <button role="button" id="cancelar-asunto" class="btn btn-default">Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>


<!-- Modal -->
<form role="form" method="POST" id="form-firma">
    <div class="modal fade" id="firma-modal" tabindex="0" role="dialog" aria-labelledby="firma-modal" aria-hidden="false" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Firmar documento electronico</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="control">
                        <label for="certificado">Certificado</label>
                        <input type="file" name="certificado" id="certificado" required/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control">
                        <label for="llave">Llave privada</label>
                        <input type="file" name="llave" id="llave" required/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control">
                        <label for="password">Contraseña</label>
                        <input type="password" class="form-control" name="password" id="password" required/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btn-form-firma">Firmar</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
    </div>
</form>

<script type="text/javascript" src="/resources/js/generador/firma.js"  ></script>
<script type="text/javascript" src="/resources/js/generador/editor.js"  ></script>
<script type="text/javascript" src="/resources/js/moment.min.js"  ></script>
<script type="text/javascript" src="/resources/js/so.min.js"  ></script>
<script type="text/javascript" src="/resources/js/loading.min.js"  ></script>
<script type="text/javascript" src="/resources/js/bootbox.min.js"  ></script>
<script type="text/javascript" src="/resources/js/loading.min.js"  ></script>

<script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js"  ></script>



