<link rel="stylesheet" href="resources/css/adminpersonal/controlPersonal.css">
<input type="hidden" id="hddCveUsuario" value="0" />

<div class="form-group row">
    <h1>Administraci&oacute;n de Personal</h1>


</div>
<div id="frmInicioControlPersonal">
    <div class="dropdown-divider"></div>
    <div class="form-group ">

        <label for="example-search-input" class="col-10 col-form-label">Nombre Servidor P&uacute;blico:</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtNombreBusqueda">
        </div>

    </div>
    <div class="  form-group row btn-toolbar col-10">

        <button type="button" class="btn btn-warning btn-sm btn-space" onClick="">Nuevo</button>

        <button type="button" class="btn btn-success btn-space" onclick="limpiar()">Limpiar</button>
        <button type="button" class="actualizarInfo btn btn-success btn-space" onclick="">Actualizar Domicilio</button>
        <button type="button" class="actualizarInfo btn btn-success btn-space" onclick="">Actualizar Telefono</button>
        <button type="button" class="actualizarInfo btn btn-success btn-space" onclick="">Actualizar Fotografia</button>
        <button type="button" class="actualizarInfo btn btn-success btn-space" onclick="">Actualizar Firma</button>
        <button type="button" class="actualizarInfo btn btn-success btn-space" onclick="">Actualizar Email</button>
        <button type="button" class="actualizarInfo btn btn-success btn-space" onclick="">Registrar Movimientos</button>

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

</div>

<div id="frmConsultaPersonal">
    <div class="dropdown-divider"></div>
    <div class="datos">
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Nombre Servidor P&uacute;blico:</label>
            <label for="example-search-input" class="" id="lblNombre"></label>

            <label for="example-search-input" class="descPersonal">Sexo: </label>
            <label for="example-search-input" class="" id='lblSexo'></label>
            <label for="example-search-input" class="descPersonal" id="lbledad">Edad: </label>
            <label for="example-search-input" class=""></label>


        </div>
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">CURP: </label>
            <label for="example-search-input" class="" id='lblCurp'></label>

            <label for="example-search-input" class="descPersonal">RFC: </label>
            <label for="example-search-input" class="" id="lblRfc"></label>
            <label for="example-search-input" class="descPersonal">Discapacidad: </label>
            <label for="example-search-input" class="" id="lblDiscapacidad"></label>

        </div>
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Telefono Movil:</label>
            <label for="example-search-input" class="" id='lblTelefonoMovil'></label>

            <label for="example-search-input" class="descPersonal">Telefono Fijo: </label>
            <label for="example-search-input" class="" id="lblTelefonoFijo"></label>
            <label for="example-search-input" class="descPersonal">Correo Electr&oacute;nico: </label>
            <label for="example-search-input" class="" id="lblEmail"></label>

        </div>

        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Calle:</label>
            <label for="example-search-input" class="" id='lblCalle'></label>

            <label for="example-search-input" class="descPersonal">No. Exterior: </label>
            <label for="example-search-input" class="" id="lblNoExterior"></label>
            <label for="example-search-input" class="descPersonal">No. Interior: </label>
            <label for="example-search-input" class="" id="lblNoInterior"></label>

        </div>
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Colonia:</label>
            <label for="example-search-input" class="" id='lblColonia'></label>

            <label for="example-search-input" class="descPersonal">C.P. : </label>
            <label for="example-search-input" class="" id="lblCodigoPostal"></label>
            <label for="example-search-input" class="descPersonal">Municipio: </label>
            <label for="example-search-input" class="" id="lblMunicipio"></label>

        </div>
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Estado:</label>
            <label for="example-search-input" class="" id='lblEstado'></label>

            <label for="example-search-input" class="descPersonal">Fecha Nacimiento: </label>
            <label for="example-search-input" class="" id="lblFechaNacimiento"></label>
            <label for="example-search-input" class="descPersonal">Lugar Nacimiento: </label>
            <label for="example-search-input" class="" id="lblLugarNacimiento"></label>

        </div>
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Estado Civil:</label>
            <label for="example-search-input" class="" id='lblEstadoCivil'></label>

            <label for="example-search-input" class="descPersonal">Padre de Familia: </label>
            <label for="example-search-input" class="" id="lblPadre"></label>
            <label for="example-search-input" class="descPersonal">Numero de hijos: </label>
            <label for="example-search-input" class="" id="lblNoHijos"></label>


        </div>
        <div class="form-group2">

            <label for="example-search-input" class="descPersonal">Grado de Estudios:</label>
            <label for="example-search-input" class="" id='lblEstudios'></label>

            <label for="example-search-input" class="descPersonal">Titulado: </label>
            <label for="example-search-input" class="" id="lblTitulado"></label>
            <label for="example-search-input" class="descPersonal"> </label>
            <label for="example-search-input" class="" id="lblTitulado"></label>


        </div>
        <div class="form-group2">
            <div id="listaMovimientos2"></div>
            

        </div>
    </div>
    <div class="imagenes" id=""><img src="" class="fotografia" alt="" id="fotografia">
        <img src="" class="firma" alt="" id="firma">  </div>

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
        //   $("#frmInicioControlPersonal").hide();
        $("#frmConsultaPersonal").css("display", "flex");
        $(".actualizarInfo").css("display", "block");
        var cveUsuario = $("#hddCveUsuario").val();
        var usuario = new Object();
        usuario.cveUsuario = cveUsuario;
        $.ajax({
            data: JSON.stringify(usuario),
            url: 'consultaPersonal',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                limpiarEtiquetas();
                if (response.length > 0) {
                    var nombre = "";
                    var cveServidorPublico = "";
                    var curp = "";
                    var rfc = "";
                    var fechaNacimiento = "";
                    var sexo = "";
                    var email = "";
                    var cveIssemym = "";
                    var fotografia = "";
                    var firma ="";
                    var calle ="";
                    var numeroInt="";
                    var numeroExt = "";
                    var colonia="";
                    var municipio="";
                    var estado = "";
                    var codigoPostal="";

                    $.each(response, function (index, el) {
                        nombre = el.nombre + " " + el.paterno + " " + el.materno;
                        cveServidorPublico = el.cveServidorPub;
                        curp = el.curp;
                        rfc = el.rfc;
                        fechaNacimiento =cambiarFecha2(el.fechaNacimiento);
                        sexo = el.cveGenero == 1 ? "HOMBRE" : "MUJER";
                        email = el.email;
                        cveIssemym = el.cveIssemym;
                       
                        if (el.fotografiasPersonal.length > 0) {
                            $.each(el.fotografiasPersonal, function (i, e) {
                                if (e.activo == "S") {
                                    fotografia = e.rutaFotografia.replace("//", "/").replace("/../opt/integral/static", "");
                                    console.log("ruta Fotografia " + fotografia);
                                }

                            });

                        }
                        if (el.firmasPersonal.length > 0) {
                            $.each(el.firmasPersonal, function (i, e) {
                                if (e.activo == "S") {
                                    firma = e.rutaFirma.replace("//", "/").replace("/../opt/integral/static/firmas/", "firmas/");
                                    console.log("firma " + firma);
                                }

                            });

                        }
                        if (el.domicilios.length > 0) {
                            $.each(el.domicilios, function (i, e) {
                                if (e.activo == "S") {
                                    calle = e.calle;
                                    numeroInt=e.numeroInt;
                                    numeroExt=e.numeroExt;
                                    colonia=e.descColonia;
                                    municipio=e.municipio.descMunicipio;
                                    estado=e.descEstado;
                                    codigoPostal=e.codigoPostal;
                                }


                            });

                        }
                        if(el.movimientos.length>0){
                            var tabla = "<table class='table'><th>Puesto</th><th>Puesto Funcional</th><th>Plaza</th><th>Adscripcion</th><th>Tipo de Movimiento</th><th>Fecha Movimiento</th>";
                            $.each(el.movimientos, function (i, e) {
                                var puesto =e.puesto.descPuesto;
                                var puestoFuncional=e.puestoFuncional==null?"":el.puestoFuncional;
                                var Plaza ="";
                                var adscripcion  = e.adscripcion.descAdscripcion;
                                var numTipoMovimiento = parseInt(e.cveTipoMovimientoPersonal);
                                var tipoMovimiento = numTipoMovimiento==1?"ALTA":numTipoMovimiento==2?"CAMBIO":numTipoMovimiento==4?"BAJA":"FALTA MOVIMIENTO";
                                var fechaMovimiento =cambiarFecha2(e.fechaMovimiento);
                                tabla+="<tr><td>"+puesto+"</td><td>"+puestoFuncional+"</td><td>"+Plaza+"</td><td>"+adscripcion+"</td><td>"+tipoMovimiento+"</td><td>"+fechaMovimiento+"</td></tr>";


                            });
                            tabla+="</table>";
                            console.log(tabla);
                            $("#listaMovimientos2").append(tabla);
                        }


                    });
                 
                    $("#fotografia").attr("src", fotografia);
                    $("#firma").attr("src", firma);
                    $("#lblNombre").append(nombre);
                    $("#lblSexo").append(sexo);
                    //  $("#lbledad").append(edad);
                    $("#lblCurp").append(curp);
                    $("#lblRfc").append(rfc);
                    //  $("#lblDiscapacidad").append(nombre);
                    //  $("#lblTelefonoMovil").append(nombre);
                    //  $("#lblTelefonoFijo").append(nombre);
                    $("#lblEmail").append(email);
                       $("#lblCalle").append(calle);
                        $("#lblNoExterior").append(numeroExt);
                        $("#lblNoInterior").append(numeroInt);
                        $("#lblColonia").append(colonia);
                        $("#lblCodigoPostal").append(codigoPostal);
                        $("#lblMunicipio").append(municipio.toUpperCase());
                        $("#lblEstado").append(estado);
                    $("#lblFechaNacimiento").append(fechaNacimiento);
                   // $("#lblLugarNacimiento").append(nombre);
                   // $("#lblEstadoCivil").append(nombre);
                   // $("#lblPadre").append(nombre);
                   // $("#lblNoHijos").append(nombre);
                   // $("#lblEstudios").append(nombre);
                   // $("#lblTitulado").append(titu);
                   


                }

            },
            error: function (xhr, ajaxOptions, thrownError) {
                $("#ModalMensaje").empty();
                $("#ModalMensaje")
                    .append(
                        "No se encontraron entradas con los criterios ingresados");
                $("#ModalErrorIndex").modal();
            }
        });
    }

    function limpiarEtiquetas() {
     
        $("#lblNombre").empty();
        $("#lblSexo").empty();
        $("#lbledad").empty();
        $("#lblCurp").empty();
        $("#lblRfc").empty();
        $("#lblDiscapacidad").empty();
        $("#lblTelefonoMovil").empty();
        $("#lblTelefonoFijo").empty();
        $("#lblEmail").empty();
        $("#lblCalle").empty();
        $("#lblNoExterior").empty();
        $("#lblNoInterior").empty();
        $("#lblColonia").empty();
        $("#lblCodigoPostal").empty();
        $("#lblMunicipio").empty();
        $("#lblEstado").empty();
        $("#lblFechaNacimiento").empty();
        $("#lblLugarNacimiento").empty();
        $("#lblEstadoCivil").empty();
        $("#lblPadre").empty();
        $("#lblNoHijos").empty();
        $("#lblEstudios").empty();
        $("#lblTitulado").empty();
        $("#listaMovimientos2").empty();

    };

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