<input type="hidden" id="hddIdDefensor" />
<input type="hidden" id="hddJuzgadoConsulta" value="0"/>
<input type="hidden" id="hddTipoCarpetaConsulta"  value = "9"/>
<input type="hidden" id="hddJuzgadoRegistro" value="0"/>
<input type="hidden" id="hddTipoCarpetaRegistro"  value = "9"/>
<input type="hidden" id="hddMunicipio"  value = "0"/>
<input type="hidden" id="hddIdCarpeta"  value = "0"/>
<input type="hidden" id="hddIdRepresentado"  value = "0"/>
<input type="hidden" id="hddCveMunicipio"  value = "0"/>
<input type="hidden" id="hddCveEtnia"  value = "0"/>
<input type="hidden" id="hddCveTipoAudiencia"  value = "0"/>
<input type="hidden" id="hddCveTipoConclusion"  value = "0"/>
<input type="hidden" id="hddDefensa_id"  value = "0"/>
<input type="hidden" id="hddIdAudiencia"  value = "0"/>
<input type="hidden" id="hddIdCarpetaRepresentado"  value = "0"/>
<input type="hidden" id="hddCveRecurso"  value = "0"/>
<input type="hidden" id="rutaFormulario"  value = "estadistica/capturaadoles"/>




<link rel="stylesheet" href="resources/css/controloral.css">
<div class="icon-bar" id = "barrras">
    <a href="#"  id = "barra1" class=" barra barra1 active"><i class="fa1">Consulta de Carpetas Administrativas Adolescentes</i></a>
    <a href="#"  id = "barra2" class=" barra barra2"><i class="fa2">Registro de Carpetas Administrativas Adolescentes</i></a>
    <!--<a href="#"  id = "barra3" class=" barra barra3"><i class="fa3">Audiencias SIGEDEPU</i></a>-->

</div>
<div class="opciones">

    <div class=" root busquedacarpeta  carpeta active">
        <form class="form-register1">
            <div class="form-register-header"> 
                <h1 class="form-register-title">Consulta de Carpetas Administrativas Adolescentes</h1>
            </div>
            <div class="form-register-body"> 
                <div class="step active " >
                    <div class="step-header">
                        <h2 class="step-title">Informaci&oacute;n de Carpetas Administrativas Adolescentes</h2>
                    </div>
                    <div class="step-body">
                        <input type="text" placeholder="Numero del Carpetas" id = "txtNumeroExpConsulta" class="step-input">
                        <input type="text" placeholder="A&ntilde;o del Carpetas" id = "txtAnioExpConsulta" class="step-input">
                        <input type="text" placeholder="Juzgado" id="txtJuzgadoConsulta"class="step-input">
                        <input type="text" placeholder="Tipo de Carpeta" id="txtTipoCarpetaConsulta" class="step-input">
                        <input type="text" placeholder="N&uacute;mero Unico de Carpeta(NUC)" id="txtNucConsulta" class="step-input">
                    </div>
                    <div class="step-footer">
                        <button type="button" class="step-button step-button-next" data-to_step="2" data-step="1" onclick="buscarCarpetas()">Buscar</button>
                        <button type="button" class="step-button step-button-next"  onclick="limpiarCarpetas()">Limpiar</button>
                    </div>
                </div>


            </div>

        </form>

        <div id="listaCarpetasConsulta" style="display: none"></div>

    </div>
    <div class="root registracarpeta carpeta">
        <form class="form-register">
            <div class="form-register-header"> 
                <ul class="progressbar">
                    <li class="progressbar-option active">Registro de Carpetas Administrativas </li>
                    <li class="progressbar-option ">Registro de Representados</li>
                    <li class="progressbar-option ">Registro Actuaciones</li>
                </ul>
                <h1 class="form-register-title">Registro de Carpetas Administrativas</h1>

            </div>
            <div class="form-register-body"> 
                <div class="step active " id="step-1">
                    <div class="step-header">
                        <h2 class="step-title">Informaci&oacute;n del Carpetas </h2>
                    </div>
                    <div class="step-body">
                        <input type="text" placeholder="Numero del Expediente" id="txtNumeroExpRegistro" class="step-input">
                        <input type="text" placeholder="A&ntilde;o del Expediente" id="txtAnioExpRegistro" class="step-input">
                        <input type="text" placeholder="Juzgado" class="step-input" id="txtJuzgadoConsultaRegistro" >
                        <input type="text" placeholder="Tipo de Carpeta" class="step-input" id="txtTipoCarpetaRegistro">
                        <input type="text" placeholder="N&uacute;mero Unico de Carpeta(NUC)" id="txtNucRegistro" class="step-input">
                    </div>
                    <div class="step-footer">
                        <button type="button" class="step-button step-button-next" data-to_step="2" data-step="1" onclick="guardarCarpeta(this)">Siguiente</button>
                    </div>
                </div>
                <div class="step" id="step-2">
                    <div class="step-header">
                        <h2 class="step-title">Informaci&oacute;n del Imputados </h2>
                    </div>
                    <div class="step-body">
                        <input type="text" placeholder="Nombre" id="txtNombreImputado" class="step-input">
                        <input type="text" placeholder="Paterno" id="txtPaternoImputado" class="step-input">
                        <input type="text" placeholder="Materno" id="txtMaternoImputado" class="step-input">
                        <input type="text" placeholder="Fecha Nacimiento" id="txtFechaNacimientoImputado" class="step-input datepicker">
                        <input type="text" placeholder="Edad" id="txtEdadImputado" class="step-input ">
                     
                        <select id="cmdSexo" class="step-input">
                            <option value="1">Hombre</option>
                            <option value="2">Mujer</option>
                        </select>
                        <input type="text" placeholder="Munic&iacute;pio Originario" id="txtMunicipio" class="step-input">
                        <input type="text" placeholder="Etnia" id="txtEtnia" class="step-input">
                        <input type="text" placeholder="Discapacidad" id = "txtDispacacidad" class="step-input">
                        <input type="text" placeholder="Grupos Vunerables" id="txtGrupoVulnerable" class="step-input">

                        <div id ="listGruposVulnerables"></div>
                        <input type="text" placeholder="Del&iacute;tos" id = "txtDelito" class="step-input">
                        <div id ="listDelitos"></div>
                    </div>
                    <div class="step-footer">
                        <button type="button" id ="btnRegresarRepr" class="step-button step-button-back" data-to_step="1" data-step="2" onclick="cambiarPasos(this,true)" >Regresar</button>
                        <button id = "bt-guardar-representado" type="button" class=" step-button" style="display: none;" onclick="guardarRepresentado()">Guardar</button>
                        <button id = "bt-cancelar-representado" type="button" class=" step-button" style="display: none;" onclick="cancelarRepresentado()">Cancelar</button>
                        <button type="button" id ="btnSiguienteRepr" class="step-button step-button-next" data-to_step="3" data-step="2" onclick="limpiarImputados(this)">Siguiente</button>
                    </div>
                </div>
                <div id="listaRepresentados" style="display: none"></div>
                <div class="step" id="step-3">
                    <div class="step-header">
                        <h2 class="step-title">Informaci&oacute;n de la actuación </h2>
                    </div>
                    <div class="step-body">

                        <input type="text" id= "txtFechaAudiencia" placeholder="Fecha Realización Actuación" class="step-input datepicker">
                        <input type="text" id= "txtFechaControlDeten" placeholder="Fecha Control de Detención" class="step-input datepicker">
                        <input type="text" id= "txtFechaFormulacion" placeholder="Fecha Formulaci&oacute;n Imputaci&oacute;n" class="step-input datepicker">
                        <input type="text" id= "txtFechaprorroga" placeholder="Fecha Prorroga Constitucional" class="step-input datepicker">
                        <input type="text" id= "txtFechaVinculacion" placeholder="Fecha de Vinculaci&oacute;n" class="step-input datepicker">
                        <input type="text" id= "txtFechaMedida" placeholder="Fecha Medida Cautelar" class="step-input datepicker">
                        <input type="text" id= "txtFechaInicioInvest" placeholder=" Inicio Investigaci&oacute;n Complementaria" class="step-input datepicker">
                        <input type="text" id= "txtFechaFinInvest" placeholder=" Fin Investigaci&oacute;n Complementaria" class="step-input datepicker">
                        <input type="text" id= "txtFechapeticionpartes" placeholder="Petición de Partes" class="step-input datepicker">



                        <!--                        <input type="text" id= "txtFechaProximaAudiencia" placeholder="Fecha ProximaAudiencia" class="step-input datepicker">-->
                        <input type="text"  placeholder="Tipo de Actuación" class="step-input" id = "txtTipoAudiencia">
                        <input type="text"  placeholder="Terminación" class="step-input" id="txtTipoconclusiones">
                        <input type="text"  placeholder="Recurso" class="step-input" id="txtRecurso">
                        
                        <div>Interno/Externo


                            <select id ="cmbEstadoBeneficiado" class="step-input"  >
                                <option value='S'>Con Detenido</option>
                                <option value='N'>Sin Detenido</option>
                            </select>
                        </div>
                        <input type="text" id= "txtObservaciones" placeholder="Observaciones" class="step-input">
                        <div>Representados en la Actuación
                            <div id = "divRepresentadosAudiencia"></div>
                        </div>
                    </div>
                    <div class="step-footer">
                        <button type="button" class="step-button step-button-back" data-to_step="2" data-step="3" onclick="consultarImputados(); cambiarPasos(this,true)">Regresar</button>
                        <button id ="btn-guardar-audiencia" style = "display: none;" type="button" class="step-button" onclick="guardarAudiencia()" >Guardar</button>
                        <button id ="btn-cancelar-audiencia" style = "display: none;" type="button" class="step-button" onclick="limpiarActuacion()" >Cancelar</button>
                    </div>
                </div>

            </div>

        </form>
        <div id="listaAudienciasConsulta" style="display: none"></div>

    </div>
    <!--    <div class=" root audienciassigedepu carpeta">
            <form class="form-register1">
                <div class="form-register-header"> 
    
                    <h1 class="form-register-title">Audiencias SIGEDEPU pendientes</h1>
    
                </div>
                <div class="form-register-body"> 
                    <div class="step active " >
                        <div class="step-header">
                            <h2 class="step-title">Informaci&oacute;n de la Carpeta </h2>
                        </div>
                        <div class="step-body">
                            <input type="text" placeholder="Numero del Expediente" class="step-input">
                            <input type="text" placeholder="A&ntilde;o del Expediente" class="step-input">
                            <input type="text" placeholder="Juzgado" class="step-input">
                            <input type="text" placeholder="Tipo de Carpeta" class="step-input">
                            <input type="text" placeholder="N&uacute;mero Unico de Causa(NUC)" class="step-input">
                        </div>
                        <div class="step-footer">
                            <button type="button" class="step-button step-button-next" data-to_step="2" data-step="1">Buscar</button>
                        </div>
                    </div>
    
    
                </div>
    
            </form>
            <div id="listaAudienciasSigedepu" style="display: none"></div>
        </div>-->
</div>



<script src="resources/js/adolescentes.js"/>



