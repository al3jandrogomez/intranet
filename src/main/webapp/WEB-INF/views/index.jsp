
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
        <link rel="icon" href="resources/images/logoido2.png">
        <title>Instituto de la Defensoria Publica</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/jquery-ui.css">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css.map">
        <link rel="stylesheet" href="resources/css/leftsidebar.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <meta charset='UTF-8'>
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie-edge">
        <script src="https://rawgit.com/sitepoint-editors/jsqrcode/master/src/qr_packed.js"></script>

    </head>

    <style>

        html,body {
            height: 100%;
            /*            overflow: hidden;*/
        }

        .btn-space {
            margin-right: 5px;
        }

        #menu-toggle {
            background-color: black;
            border: solid white 1px;
        }

        #menu-toggle:active, #menu-toggle:focus {
            border: solid white 1px;
        }

        .sidebar-nav>.sidebar-brand {
            font-size: 15px;
        }

        body {
            font-size: 13px;
            background-color: #f0f3f4;
        }

        .btn-dbld {
            background-color: #d0d9da;
        }

        .modificar {
            background-size: cover;
        }
        .card{
            display: inline-block;
            font-size: 1rem;
            border: 0px;

        }

        .eliminar {
            background-size: cover;
        }
        .card-text{
            /* margin: 0px; */
            /* display: inline-block; */
            font-size: 10px;
        }

        .documento {
            background-size: cover;
        }

        .text-white {
            font-size: 13px;
        }
        .secciones{
            font-size: 13px;
            font-weight: bold;
        }
        .defensoria {
            color: 	#FFFFFF;
            font-size: 1em;
            padding-left: 15px;
        }
        .barra{
            background-color: #646569;
        }
        #sidebar-wrapper{
            background-color: #646569;
            font-weight: bold;
          
        }
       
        .line{
            background-image: url(/resources/images/line.png);
            width: 100%;
            height: 6px;
            background-repeat: repeat;
        }

        .item-thumbnail {
            border: 1px solid #c5ced4;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
        }

        .item-image{
            height: 130px;
            text-align: center;
            padding: 15px;
            line-height: 100px;
            display: block;
            position: relative;
        }

        .item.item-thumbnail .item-image img {
            max-width: 100%;
            max-height: 100%;
        }

        img {
            vertical-align: middle;
            border: 0;
            vertical-align: middle;
            border-style: none;
        }
        .item.item-thumbnail .item-title, .item.item-thumbnail .item-title a {
            font-weight: 600;
            color: #212121;
            font-size: 14px;
            line-height: 18px;
            max-height: 36px;
            overflow: hidden;
        }

        .discount{
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            position: absolute;
            bottom: 0;
            right: 15px;
            line-height: 20px;
            padding: 2px 10px;
            color: #fff;
            background: #008678;
            font-weight: 600;
            font-size: 13px;

        }
        .item.title{
            font-weight: 600;
            color: #212121;
            font-size: 14px;
            line-height: 18px;
            max-height: 36px;
            overflow: hidden;
        }
        .item.item-thumbnail .item-title a:hover, .item.item-thumbnail .item-title a:focus {
            color: #009688;
        }

        .item.item-thumbnail .item-image .discount {
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            position: absolute;
            bottom: 0;
            right: 15px;
            line-height: 20px;
            padding: 2px 10px;
            color: #fff;
            background: #008678;
            font-weight: 600;
            font-size: 13px;
        }

        .item.item-thumbnail .item-title a:hover, .item.item-thumbnail .item-title a:focus {
            color: #009688;
            text-decoration: none;
        }
        .item.item-thumbnail .item-desc {
            margin: 0;
            font-size: 9px;
            color: #707478;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;

        }



        .item.item-thumbnail {
            border: 1px solid #c5ced4;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
        }
        p {
            display: block;
            margin-block-start: 1em;
            margin-block-end: 1em;
            margin-inline-start: 0px;
            margin-inline-end: 0px;
            /*max-height: 36px;*/
        }

        .item.item-thumbnail .item-title, .item.item-thumbnail .item-title a {
            font-weight: 600;
            color: #212121;
            font-size: 11px;
            line-height: 18px;
            max-height: 50px;
            overflow: hidden;
        }

        .section-title {
            font-size: 16px;
            font-weight: 600;
            margin: -5px 0 25px;
            color: #212121;
        }

        .section-title small {
            margin-left: 5px;
            font-weight: 400;
            font-size: 14px;
            color: #999;
        }

        .row.row-space-10>[class*="col-"] {
            padding: 0 5px !important;
        }
        .container-fluid{
            height: 800px;
        }

        .containersec {
            width: 970px;
            margin-right: auto;
            /*margin-left: auto;*/
            padding-left: 15px;
            padding-right: 15px;
        }


        .item.item-thumbnail .item-info {
            padding: 15px;
            text-align: center;
        }
        .btn-espacio{
            margin: 3px;
            min-width: 172px;
        }
        .controlcontainer{
            display: flex;
        }
        .box{
            top:50%;
            left:50%;

            /*transform: translate(-50%,-50%);*/
            width:230px;
            background:#fff;
            padding: 40px;
            box-sizing: border-box;
            border:1px rgba(0,0,0,.1);
            box-shadow: 0 5px 10px rgba(0,0,0,.2);
        }
        .right-box{
            margin-left:  5px;
            top:50%;
            left:50%;
            width:80%;
            background:#fff;
            padding: 50px;
            box-sizing: border-box;
            border:1px rgba(0,0,0,.1);
            box-shadow: 0 5px 10px rgba(0,0,0,.2);
        }
        .box input{
            width:100%;
            box-sizing: border-box;
            box-shadow: none;
            outline:none;
            border: none;
            border-bottom: 2px solid  #999;

        }
        .box div{
            position: relative;
        }
        .box div label{
            /*            position:absolute;
                        top:-5px;*/
            left:0;
            color:#9D9999;
            /*transition: .5s;*/
            pointer-events: none;

        }
        .box input:focus  ~ label
        {
            top:-14px;
            left:0;
            color: #AAC035;
            font-size: 12px;
            font-weight: bold;
        }
        .box input:valid  ~ label{
            top:-14px;
            left:0;
            color: #AAC035;
            font-size: 12px;
            font-weight: bold;
        }

        .box input:focus,
        .box input:valid  {
            border-bottom: 2px solid #AAC035;
        }
        #contenido {
            /*text-align: center;*/
            height: 100%;
        }
        #listDelitos a{
            margin-left: 5px;
            font-size: 15px;
            color: #AAC035;
            font-weight: bold;
        }
        #listGruposVulnerables a{
            margin-left: 5px;
            font-size: 15px;
            color: #AAC035;
            font-weight: bold;
        }
        .table-striped{
            font-size: 10px;
        }


        /* BARRA DE NAVEGACION */


        /* FIN DE LA BARA*/


        /* #sidebar-wrapper{ */
        /* width:0; */
        /* } */
    </style>

    <body>
        <input type='hidden' id='idRegion' value=0 />
        <input type='hidden' id='cveFormulario' value=0 />
    <header>
        <nav
            class="navbar navbar-toggleable-ms navbar-expand-lg navbar-dark  barra">
            <button class="navbar-dark ml-auto" type="button" id="menu-toggle"
                    style="display: none">
                <span class="navbar-toggler-icon"></span>
            </button>
            <h1 class="defensoria">INSTITUTO DE LA DEFENSORIA PUBLICA DEL ESTADO DE M&Eacute;XICO</h1>
            <div style=" position: absolute;
                 bottom: 0; margin-left:34px;">
                <h1 class="defensoria" id="depto"></h1>
            </div>

            <div class=" navbar-collapse " id="navbarSupportedContent">
                <div class="navbar-nav ml-auto">
                    <!-- 			<a class="nav-item nav-link active" href="#">Inicio</a> <a -->
                    <!-- 				class="nav-item nav-link " href="#">Nosotros</a> <a -->
                    <!-- 				class="nav-item nav-link " href="#">Servicios</a> <a -->
                    <!-- 				class="nav-item nav-link " href="#">Contactos</a> -->
                    <div id="frmLogin" class="dropdown" style="display: none">
                        <button class="btn btn-secondary dropdown-toggle" type="button"
                                id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">Intranet</button>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                            <div class="card-body">
                                <div class="form-group">
                                    <input id="usuario" placeholder="Usuario"
                                           class="form-control form-control-sm" type="text" required="">
                                </div>
                                <div class="form-group">
                                    <input id="password" placeholder="Contrase&ntilde;a"
                                           class="form-control form-control-sm" type="password" required="">
                                </div>
                                <div class="form-group">
                                    <button id="login" type="button" onclick="login()"
                                            class="btn btn-primary btn-block">Entrar</button>
                                </div>

                                <div class="form-group text-xs-center">
                                    <small><a href="#">¿Olvido su contraseña?</a></small>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="dropdown show" id="frmDatos" style="display: none">
                        <a class="btn btn-secondary dropdown-toggle"
                           href="https://example.com" id="dropdownMenuUsuario"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#" onclick="cerrarSesion()">Cerrar
                                Sesion</a>
                        </div>
                    </div>
                    <div id="appmenu" style="display: none;" class="dropdown show">
                        <a class="btn btn-secondary dropdown-toggle"
                           href="https://example.com" id="dropdownMenuLink"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Aplicaciones </a>

                        <div class="dropdown-menu dropdown-menu-right"
                             aria-labelledby="dropdownMenuLink"
                             style="width: 20rem; height: 400px;">
                            <div id="appcontainer" class="container"></div>
                        </div>
                    </div>

                </div>
            </div>


        </nav> 
        <div class="line"></div></header>
    <div id="wrapper" class="toggled">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul id="listaFormularios" class="sidebar-nav">

            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->

        <!-- /#page-content-wrapper -->

    </div>
    <!--<div id="page-content-wrapper">-->
    <div>
        <div class="container-fluid">
            <!--            <div class="row">-->
            <div class="col-lg-12" id="contenido">
              
                <img class="chat" src="resources/images/cancelarcitas2.jpeg" >
               
               

                <!--                         <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a> -->
            </div>
            <!--</div>-->
        </div>
    </div>

    <div class="modal fade" id="ModalCorrectIndex" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalTituloCorrect"></h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="ModalMensajeCorrect">correct</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">Cerrar</button>

                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="ModalErrorIndex" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalTituloError"></h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="modalMensajeError" class="modal-body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">Cerrar</button>

                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="ModalIndex" tabindex="-1" role="dialog"
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
                            onclick="guardarMarcas();">Guardar Marca</button>
                </div>
            </div>
        </div>
    </div>


    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/popper.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>

    <script src="resources/js/jquery-ui.js"></script>
    <script src="resources/js/bootstrap.js"></script>
    <script type="text/javascript" src="resources/js/bootbox.min.js"  ></script>
    <script type="text/javascript"
    src="https://onlyoffice.idpedomex.gob.mx/web-apps/apps/api/documents/api.js"></script>
    <script>

                                var intentos = 0;
                                $("#menu-toggle").click(function (e) {
                                    e.preventDefault();
                                    mostrarOpciones();
                                });

                                cargaForm = function (form, cveFormulario) {
                                    if (validarSesion()) {
                                        $("#cveFormulario").val(cveFormulario);
//                                    $("#contenido").load(form);
                                        $("#contenido").empty();
                                        $.get("cargaFormulario", {frm: form}, function (data) {
                                            $("#contenido").append(data);

                                        });


                                    }
                                    $("#wrapper").toggleClass("toggled");

                                };
                                cargaForm2 = function (form) {
                                    $("#contenido").load(form);



                                };

                                cargaFormDiv = function (form) {

                                    $.get("cargaFormulario", {frm: form}, function (data) {
                                        $("#listaControlOral").empty();
                                        $("#listaControlOral").append(data);
                                    });



                                };
                                cambiarfecha = function (fecha) {
                                    if (fecha !== "" && fecha !== null) {
                                        var nvaFecha = fecha.split("/");
                                        var fecha = nvaFecha[2] + "-" + nvaFecha[1] + "-" + nvaFecha[0];
                                    }
                                    console.log(fecha);
                                    return fecha;

                                }

                                cambiarFecha2 = function (fecha) {

                                    if (fecha != "" && fecha !== null) {
                                        var nvaFecha = fecha.split("-");
                                        var fecha = nvaFecha[2] + "/" + nvaFecha[1] + "/" + nvaFecha[0];
                                    }

                                    return fecha;

                                }
                                Personal = function (usuario, contrasena) {

                                    this.usuario = usuario;
                                    this.contrasena = contrasena;

                                };
                                Modulos = function (cveModulo) {
                                    this.cveModulo = cveModulo;

                                };
                                Formulario = function (cveFormulario, modulo) {
                                    this.cveFormulario = cveFormulario;
                                    this.modulos = modulo;
                                };

                                login = function () {
                                    var usuario = $("#usuario").val();
                                    var contrasena = $("#password").val();

                                    var personal = new Personal(usuario, contrasena);
                                    dPersonal = JSON.stringify(personal);
                                    console.log(personal);

                                    $
                                            .ajax({
                                                data: dPersonal,
                                                url: 'loginuser',
                                                dataType: 'json',
                                                contentType: "application/json",
                                                type: 'post',
                                                beforeSend: function () {
                                                    // 							

                                                },
                                                success: function (response) {
                                                    console.log("numero de intentos =>" + intentos);

                                                    if (response) {

                                                        if (response.estatus == 2 && intentos > 0) {
                                                            console.log("numero de intentos para bloqueo =>" + intentos);
                                                            $("#modalMensajeError").empty();
                                                            $("#modalMensajeError").append(
                                                                    response.mensaje);
                                                            $("#ModalErrorIndex").modal("show");
                                                            $("#usuario").val("");
                                                            $("#password").val("");
                                                            $("#menu-toggle").hide();

                                                        } else if (response.estatus == 1
                                                                || response.estatus == 3) {
                                                            $("#frmLogin").hide();
                                                            $("#frmDatos").show();
                                                            $("#dropdownMenuUsuario").append(
                                                                    response.nombre);
                                                            $("#depto").empty();
                                                            $("#depto").append(
                                                                    response.descAdscripcion);
                                                            $("#usuario").val("");
                                                            $("#password").val("");
                                                            $("#idRegion").val(response.idRegion);
                                                            intentos++;
                                                            var caja = "";
                                                            $("#menu-toggle").show();
                                                            $("#appmenu").show();

                                                            $("#appcontainer").empty();
                                                            $
                                                                    .each(
                                                                            response.modulos,
                                                                            function (index, element) {
                                                                                if ((index) % 3 == 0)
                                                                                    caja += '<div class="row">';
                                                                                caja += '<div class="col-xs" >'
                                                                                        + '<div class=" card text-center align-items-center mx-auto"' +
                                                                                        '	style="width: 7rem;"><a onclick=cargarlistaForm('
                                                                                        + element.cveModulo
                                                                                        + ')>'
                                                                                        + '	<img class="img-fluid" src="'
                                                                                        + element.rutaImagen
                                                                                        + '"'
                                                                                        + '		alt="Almacen IDP" width="70%">'
                                                                                        + '	<div class="card-body">'
                                                                                        // 																+ '		<label class="card-title">'
                                                                                        // 																+ element.descModulo
                                                                                        // 																+ '</label>'
                                                                                        + '		<p class="card-text">' + element.descModulo + '</p>'
                                                                                        + '	</div></a>'
                                                                                        + '</div>'
                                                                                        + '</div>';
                                                                                if ((index) % 3 == 0
                                                                                        && index > 0)
                                                                                    caja += '</div>';
                                                                            });
                                                            $("#appcontainer").empty();
                                                            $("#appcontainer").append(caja);

                                                        }

                                                        if (intentos == 0) {
                                                            $("#frmLogin").show();
                                                            $("#frmDatos").hide();
                                                        }
                                                    }
                                                    intentos += 1;

                                                },
                                                error: function (xhr, ajaxOptions, thrownError) {
                                                    $("#ModalMensajeError").empty();
                                                    $("#ModalMensajeError")
                                                            .append(
                                                                    "No se encontraron entradas con los criterios ingresados");
                                                    $("#ModalErrorIndex").modal();
                                                }
                                            });
                                };
                                cerrarSesion = function () {

                                    $
                                            .ajax({
                                                data: dPersonal,
                                                url: 'logoutuser',
                                                dataType: 'json',
                                                contentType: "application/json",
                                                type: 'post',
                                                beforeSend: function () {

                                                },
                                                success: function (response) {
                                                    $("#depto").empty();
                                                    $("#menu-toggle").hide();
                                                    $("#frmLogin").show();
                                                    $("#frmDatos").hide();
                                                    $("#appcontainer").empty();
                                                    $("#appmenu").hide();
                                                    $("#dropdownMenuUsuario").empty();
                                                    $("#listaFormularios").empty();
                                                    $("#contenido").empty();
                                                    ocultarOpciones();

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

                                cargarlistaForm = function (cveModulo) {

                                    var dModulo = new Modulos(cveModulo);
                                    var dFormulario = new Formulario(null, dModulo);
                                    dFormulario = JSON.stringify(dFormulario);
                                    console.log(dFormulario);
                                    $
                                            .ajax({
                                                data: dFormulario,
                                                url: 'opcionesmenu',
                                                dataType: 'json',
                                                contentType: "application/json",
                                                type: 'post',
                                                beforeSend: function () {

                                                },
                                                success: function (response) {

                                                    var lista = ""
                                                    $("#contenido").empty();
                                                    $("#listaFormularios").empty();
                                                    SoloMostrarOpciones();

                                                    $
                                                            .each(
                                                                    response,
                                                                    function (index, element) {
                                                                        if (index == 0) {
                                                                            lista += '<li class="sidebar-brand"><a href="#">'
                                                                                    + element.modulos.descModulo
                                                                                    + '</a></li>';
                                                                        }
                                                                        lista += '<li><a href="#" onClick="cargaForm(\''
                                                                                + element.rutaFormulario
                                                                                + '\',' + element.cveFormulario + ')">'
                                                                                + element.descFormulario
                                                                                + '</a></li>';

                                                                    });

                                                    $("#listaFormularios").append(lista);

                                                },
                                                error: function (xhr, ajaxOptions, thrownError) {
                                                    $("#ModalMensajeIndex").empty();
                                                    $("#ModalMensajeIndex")
                                                            .append(
                                                                    "No se encontraron entradas con los criterios ingresados");
                                                    $("#ModalErrorIndex").modal();
                                                }
                                            });

                                }
                                mostrarOpciones = function () {

                                    var encontrado = $("#wrapper").hasClass("toggled");
                                    console.log("mostrar opciones" + encontrado);
                                    $("#wrapper").toggleClass("toggled");

                                };
                                SoloMostrarOpciones = function () {
                                    var tam = $("#sidebar-wrapper").width();
                                    console.log("tama�o: " + tam);
                                    if (tam == 0)
                                        $("#wrapper").toggleClass("toggled");
                                };
                                ocularOpciones = function () {
                                    var tam = $("#sidebar-wrapper").width();
                                    console.log("tama�o: " + tam);
                                    if (tam == 250)
                                        $("#wrapper").toggleClass("toggled");
                                };

                                $(function () {
                                    //  cargaForm2("citas");
//                                    cargaForm2("eventos");
                          //  window.open("https://youtu.be/qH0BGGWEWgA", '_blank');

                                    login();
                                })

                                errores = function (errorType, mensaje) {
                                    var continua = true;
                                    if (errorType == 1) {
                                        continua = false;
                                        cerrarSesion();
                                        $("#ModalMensajeCorrect").empty();
                                        $("#ModalMensajeCorrect")
                                                .append(
                                                        "El tiempo de inactividad ha sido excedido, su sesion se ha terminado");
                                        $("#ModalCorrectIndex").modal();
                                    }
                                    return continua;

                                };

                                validarSesion = function () {
                                    var continua = true;
                                    $.ajax({

                                        url: 'validarsesion',
                                        async: false,
                                        dataType: 'json',
                                        contentType: "application/json",
                                        type: 'post',
                                        beforeSend: function () {

                                        },
                                        success: function (response) {
                                            $.each(response, function (index, element) {
                                                continua = errores(element.errorType, element.mensaje);

                                            });




                                        },
                                        error: function (xhr, ajaxOptions, thrownError) {
                                            $("#ModalMensajeIndex").empty();
                                            $("#ModalMensajeIndex")
                                                    .append(
                                                            "No se encontraron entradas con los criterios ingresados");
                                            $("#ModalErrorIndex").modal();
                                        }
                                    });

                                    return continua;

                                };

                                listaMeses = function () {
                                    var month = function (numero, mes) {
                                        this.mes = mes;
                                        this.numero = numero;
                                    };
                                    var months = [mes(1, "Enero"), mes(2, "Febrero"), mes(3, "Marzo"), mes(4, "Abril"), mes(5, "Mayo"), mes(6, "Junio"), mes(7, "Julio"), mes(8, "Agosto"), mes(9, "Septiempre"), mes(10, "Octubre"), mes(11, "Noviembre"), mes(12, "Diciembre")];

                                    return JSON.stringify(months);
                                };


    </script>
    <!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> -->

    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script> -->





</body>
</html>