<div class="form-group row">
    <h1>Oficios de Canalizaci&oacute;n</h1>


</div>
<div id="frmArticulos">
    <div class="dropdown-divider"></div>
    <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Fecha:</label>
        <div class="col-10">
            <input class="form-control datepicker" type="text" value=""
                   id="txtFechaRegistro">
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Region:</label>
        <div class="col-10">
            <select class="form-control" id="cmbRegion" disabled>
            </select>
        </div>
        <label for="example-search-input" class="col-10 col-form-label">Estatus
            :</label>
        <div class="col-10">
            <select class="form-control" id="cmbEstatus">
                <option value="1">Patrocinio Autorizado</option>
                <option value="2">Patrocinio Turnado</option>
            </select>
        </div>
    </div>
    <div class="  form-group row btn-toolbar col-10">
        <!-- 		<button type="button" class="btn btn-primary btn-sm btn-space" -->
        <!-- 			id="guardarArticulo">Guardar</button> -->
        <button type="button" class="btn btn-warning btn-sm btn-space"
                onClick="buscarOficios()">Buscar</button>
       <button type="button" class="btn btn-success btn-space" 
                onclick="mostrarFormulario()">Firmar</button> 
        <!-- 		<button type="button" class="btn btn-danger btn-space">Eliminar</button> -->

    </div>
    <div class="dropdown-divider"></div>

    <div id="listaOficios" style="display: block"></div>


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
                <div class="modal-body" id="modalCorrect">hola</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">Cerrar</button>

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
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">Cerrar</button>

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
                <div class="modal-body">Los datos que ingreso son correctos?</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary"
                            onclick="guardarArticulos();">Guardar Articulo</button>
                </div>
            </div>
        </div>
    </div>
    <form role="form" method="POST" id="form-firma">
            <div class="modal fade" id="firma-modal" tabindex="-1" role="dialog" aria-labelledby="firma-modal" aria-hidden="true" data-keyboard="false" data-backdrop="static">
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
                                    <input type="password" class="form-control" name="passwordf" id="passwordf" required/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" onclick="" class="btn btn-success" id="btn-form-firma">Firmar</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
<script type="text/javascript" src="/resources/js/summernote-cleaner.js" ></script>
    <script type="text/javascript" src="/resources/js/summernote.min.js" ></script>
    <script type="text/javascript" src="/resources/js/summernote-es-ES.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/editor.js" ></script>
    <script type="text/javascript" src="/resources/js/so.min.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/firma.js"></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/JSgLibv2.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/SDSgLib_PBKDF2.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/SDSgLib_PKCS7.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/SDSgLib_PKCS8.js"></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/SgDataCrypto.js"></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/pkcs8_wp52_code.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/pkcs8_wp52_schema.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/sgdatajs/pkcs8_wp52_simpl.js"></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/forge/forge.min.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/cryptojs/tripledes.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/asn1.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/cms_schema.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/cms_simpl.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/common.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/ocsp_tsp_schema.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/ocsp_tsp_simpl.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/x509_schema.js" ></script>
    <script type="text/javascript" src="/resources/js/generador/crypto/pkijs/x509_simpl.js" ></script>
    <script type="text/javascript" src="/resources/js/jquery.base64.min.js" ></script>

<!--    <script type="text/javascript" src="/resources/js/generador/firma.js"  ></script>
    <script type="text/javascript" src="/resources/js/generador/editor.js"  ></script>-->
    <script type="text/javascript" src="/resources/js/moment.min.js"  ></script>
    <!--<script type="text/javascript" src="/resources/js/so.min.js"  ></script>-->
    <script type="text/javascript" src="/resources/js/loading.min.js"  ></script>
    <script type="text/javascript" src="/resources/js/bootbox.min.js"  ></script>
    <script type="text/javascript" src="/resources/js/loading.min.js"  ></script>

    <script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js"  ></script>

    <script type="text/javascript">
        var action = null, sm = false, vp = false;
        $.xmlHash = null;
        $.xml = null
        $.id = null;
        solicitudAtencion = function (region, fechaRegistro, estatus,
                fechaRechazo, id) {
            this.id = id;
            this.region = region;
            this.fecha_registro = fechaRegistro;
            this.estatus = estatus;
            this.fecha_rechazo = fechaRechazo;

        };
        regiones = function (id, nombre) {
            this.id = id;
            this.nombre = nombre;

        };
        buscarRegiones = function () {
            $
                    .ajax({
                        async: false,
                        url: 'listaRegiones',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {
                            $("#cmbRegion").empty();

                        },
                        success: function (response) {
                            var regiones = "";
                            $.each(response, function (index, element) {
                                regiones += "<option value=" + element.id + ">"
                                        + element.nombre + "</option>";
                            });

                            $("#cmbRegion").append(regiones);

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

        asignar = function (idRepresentacion) {
            console.log("asignando....");
            var formulario = '<div class="form-group ">'
                    + '<label for="example-search-input" class="col-10 col-form-label">Distrito:</label>'
                    + '<div class="col-10">'
                    + '	<select class="form-control" id="cmbDistritos" onChange="buscarDefensores();">'
                    + '	</select>'
                    + '</div>'
                    + '<label for="example-search-input" class="col-10 col-form-label">Defensores:</label>'
                    + '<div class="col-10">'
                    + '	<select class="form-control" id="cmbDefensores">'
                    + '	</select>'
                    + '</div>'
                    + '<label for="example-search-input" class="col-10 col-form-label">N&uacute;mero de oficio'
                    + '	:</label>'
                    + '<div class="col-10">'
                    + '	<input type="number" id="txtNumeroOficio" class="form-control">'
                    + '</div>' + '</div>';
            $("#myModalCorrect").modal();
            $("#modalCorrect").empty();
            $("#exampleModalLabel").empty();
            $("#modalCorrect").append(formulario);
            $("#exampleModalLabel").append(
                    "Asignaci&oacute;n de patroc&iacute;nio");
            buscarDistritos();
            buscarDefensores();
            buscarRepresentacion(idRepresentacion);

        };

        representacion = function (idRepresentacion) {
            this.id = idRepresentacion
        }

        buscarRepresentacion = function (idRepresentacion) {

            dRepresentacion = new representacion(idRepresentacion);

            console.log("idRepresentacion:" + idRepresentacion);

            dRepresentacion = JSON.stringify(dRepresentacion);

            console.log(dRepresentacion);
            $
                    .ajax({
                        data: dRepresentacion,
                        url: 'BuscarRepresentacion',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {
                            $("#cmbDefensores").empty();

                        },
                        success: function (response) {
                            // // 							var regiones = "";
                            // 							$.each(response, function(index, element) {
                            // // 								if(element.modulo_id==null)
                            // // 								regiones += "<option value="+element.id+">"
                            // // 										+ element.nombre + " "+ element.paterno +" "+ element.materno +"</option>";
                            // 							});

                            // // 							$("#cmbDefensores").append(regiones);

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
        distrito = function (id, distrito, region_id) {
            this.id = id;
            this.distrito = distrito;
            this.region_id = region_id;
        }
        usuario = function (id, distrito_id) {
            this.id = id;
            this.distrito_id = distrito_id;

        };
        buscarDefensores = function () {
            var idDistrito = $("#cmbDistritos").val();
            dUsuario = new usuario(null, idDistrito);

            dUsuario = JSON.stringify(dUsuario);
            $
                    .ajax({
                        data: dUsuario,
                        url: 'listaDefensores',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {
                            $("#cmbDefensores").empty();

                        },
                        success: function (response) {
                            var regiones = "";
                            $.each(response, function (index, element) {
                                if (element.modulo_id == null)
                                    regiones += "<option value=" + element.id + ">"
                                            + element.nombre + " "
                                            + element.paterno + " "
                                            + element.materno + "</option>";
                            });

                            $("#cmbDefensores").append(regiones);

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
        buscarDistritos = function () {
            var idRegion = $("#cmbRegion").val();
            console.log("la region seleccionada es: " + idRegion);
            dDistrito = new distrito(null, null, idRegion);

            dDistrito = JSON.stringify(dDistrito);
            $
                    .ajax({
                        data: dDistrito,
                        async: false,
                        url: 'listaDistritos',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {
                            $("#cmbDistritos").empty();

                        },
                        success: function (response) {
                            var regiones = "";
                            $.each(response, function (index, element) {
                                regiones += "<option value=" + element.id + ">"
                                        + element.distrito + "</option>";
                            });

                            $("#cmbDistritos").append(regiones);

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
        buscarOficios = function (idRegion) {
            var region = "";
            if (idRegion == null) {
                var region = $("#cmbRegion").val();
            } else {
                region = idRegion;
            }

            console.log("Region seleccionada: " + region);
            var fechaRegistro = cambiarfecha($("#txtFechaRegistro").val());
            var fechaRechazo = cambiarfecha($("#txtFechaRegistro").val());
            var estatus = $("#cmbEstatus :selected").text();
            var dRegion = new regiones(region, null);
            var solicitud = new solicitudAtencion(dRegion, fechaRegistro,
                    estatus, fechaRechazo);
            solicitud = JSON.stringify(solicitud);
            $
                    .ajax({
                        data: solicitud,
                        url: 'buscaroficios',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {

                            $("#listaOficios").empty();
                        },
                        success: function (response) {
                            $("#listaOficios").empty();
                            var lista = "<table class=\"table\"> <thead>";
                            var nombre = "";
                            var fechaSolicitud = "";
                            var defensor = "";
                            var asignacion = "";
                            var firma = "";

                            lista += "<thead><tr><th>#</th> <th>folio </th><th>peticionario </th><th>Fecha Solicitud</th><th>Estatus</th><th>Distrito</th><th>Defensor</th><th>OFICIO</th><th>FIRMAR</th><th><div id='botonasignar'></div></th></thead><tbody>";
                            $
                                    .each(
                                            response,
                                            function (index, element) {
                                                if (index == 0
                                                        && element.estatus === "Patrocinio Autorizado") {
                                                    console.log("Autorizado");
                                                    asignacion = "<button type='button' class='btn btn-warning btn-sm btn-space' onClick=\"asignar("
                                                            + element.idrepresentacion
                                                            + ")\">Asignar</button>";

                                                }
                                                if (element.idFirma == null) {
                                                    firma = "<input type=\"checkbox\" name=\"options\" id=\"" + element.idSolicitudAtencion + "\" autocomplete=\"off\" >";
                                                } else
                                                    firma = "<img onclick=\"generarPDFOficio(" + element.idSolicitudAtencion + ")\" class=\"documento\" src=\"resources/images/listo.png\"  class=\"img-thumbnail\">";

                                                if (element.defensor != null)
                                                    defensor = element.defensor;
                                                else
                                                    defensor = "";
                                                console
                                                        .log("Estauts del patrocinio: "
                                                                + element.estatus
                                                                + " folio: "
                                                                + element.folio);
                                                nombre = element.peticionario;
                                                fechaSolicitud = cambiarFecha2(element.fechaSolicitud);
                                                lista += "<tr><th scope=\"row\">"
                                                        + (index + 1)
                                                        + "</th><td>"
                                                        + element.folio
                                                        + "</td><td>"
                                                        + nombre
                                                        + "</td><td>"
                                                        + fechaSolicitud
                                                        + "</td><td>"
                                                        + element.distrito
                                                        + "</td><td>"
                                                        + element.estatus
                                                        + "</td><td>"
                                                        + defensor
                                                        + "</td><td><img onclick=\"generarPDFOficio("
                                                        + element.idSolicitudAtencion
                                                        + ")\" class='documento' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\"></td>" +
                                                        "<td>  " + firma
                                                "</td><td>&nbsp;</td></tr>";
                                            });
                            lista += "</tbody></table>";
                            $("#listaOficios").append(lista);
                            $("#botonasignar").append(asignacion);

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

        obtenerLista = function () {
            console.log("obtener lista");
            var sList = "";
            var id = 0;
            $('input[name=options]').each(function () {
                if (this.checked) {
                   
                    id = this.id;
                   
                }


            });
            dSolicitud = new solicitudAtencion(null, null, null, null, id);

            dSolicitud = JSON.stringify(dSolicitud);
            $
                    .ajax({
                        data: dSolicitud,
                        async: false,
                        url: '/editor/generaXML',
                        dataType: 'json',
                        contentType: "application/json",
                        type: 'post',
                        beforeSend: function () {


                        },
                        success: function (response) {
                            actionFirmar(response);


                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            $("#myModalCorrect").modal("hide");
                            $("#myModal2").modal("hide");

                            $("#myModalError")
                                    .append(
                                            "Error el generar XML");
                            $("#myModalError").modal();
                        }

                    });
            console.log(sList);
        };
        
        var mostrarFormulario = function(){
            $('#firma-modal').modal('show');
        };

        var actionFirmar = function (data) {
            console.log(" de oficios jsp");

            if (data.estatus === "OK") {
                $.xmlHash = data.campos.xmlEncrip;
                $.xml = data.campos.xml;
                $.id = data.campos.id;

                obtenerDatos();
            } else {
                $.xmlHash = null;
            }
        };
        var sign = function (signature) {

            var postData = new Object();
            postData.id = $.id;
            postData.signature = signature;
            postData.xml = $.xml;


            $.ajax({
                url: "/generador/editor/firmar",
                type: 'POST',
                data: JSON.stringify(postData),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {

                    if (response.estatus === 'OK') {
                        mostrarLinks(response);
                    } else {
                        $('#btn-form-firma').empty().append('Firmar').prop('disabled', false);
                        bootbox.dialog({
                            closeButton: true,
                            message: response.respuesta,
                            title: "Error",
                            buttons: {
                                success: {
                                    label: "Aceptar",
                                    className: "btn-danger"
                                }
                            }
                        });
                    }
                }
            });
        };

        obtenerDatos = function () {
            var password = $("#passwordf").val();
//            var curp = $.curp; 
            var curp = "GOAA820717HMCMLL00";

console.log("password="+password);
            var s = signData(curp, password, sign);

            if (s.invalid) {
                $('#btn-form-firma').empty().append('Firmar').prop('disabled', false);
                bootbox.dialog({
                    closeButton: true,
                    message: s.message,
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        };





        $(function () {

            $(".datepicker").datepicker({
                dateFormat: 'dd/mm/yy',
                setDate: 'today'
            });
            var region = $("#idRegion").val();
            console.log("Region Recibida:" + region);
            if (region == 1)
                region = 2;
            else if (region == 2)
                region = 3;
            else if (region == 3)
                region = 1;
            console.log("Region con cambio:" + region);

            $("#cmbEstatus").val(2);
            buscarRegiones();
            $("#cmbRegion").val(region);
            buscarOficios(region);


        });
    </script>

    