<input type="hidden" id="hddIdEncuesta" value="0">
<input type="hidden" id="hddIdAplicacion" value="0">
<input type="hidden" id="hddIdPregunta" value="0">
<input type="hidden" id="hddIdRespuesta" value="0">




</style>  


<div class="form-group row">
    <h1 id="tituloEncuesta"></h1>


</div>
<div id="frmEncuestas" >
    <div class="dropdown-divider"></div>

    <div class=" " id="listaEncuestas">
        <div  id="listaRegEncuesta"></div> 
    </div>
    <div class="  form-group row btn-toolbar col-10" id = "regEncuestas" style="display: none;">

        <div class="col-10">

            <div class = "" id="listaPreguntas"></div>
        </div>

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
</div>
<script>
    var preguntas = [];


    salirEncuesta = function () {
        $("#regEncuestas").hide();
        $("#listaEncuestas").show();
        $("#hddIdEncuesta").val(0);
        var encuesta = new Object();
        encuesta.activo = "S";

        $.ajax({
            url: "listaencuestas",
            type: 'POST',
            async: false,
            data: JSON.stringify(encuesta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                var card =
                        '  <div class="card h-100" onclick="cargarEncuesta(IDENCUESTA)">' +
                        '    <img class="card-img-top" src="resources/images/logoido2.png" style="padding: 10px; width:55%;" alt="Card image cap">' +
                        '    <div class="card-body" style="background-color:#646569; color:#FFFFFF;">' +
                        '      <h7 class="card-title">TITLE</h7>' +
                        '      <p class="card-text">&nbsp;</p>' +
                        '    </div>' +
                        '  </div>';


                var table = '<div class="card-deck" style="width:50vh">';


                $.each(response, function (index, element) {
                    var publicado = "";
                    if (element.publicar === "S") {
                        table += card.replace('TITLE', element.descEncuesta).replace('IDENCUESTA', element.idEncuesta);
                    }



                });
                table += '</div>';
                $("#listaRegEncuesta").empty();
                $("#listaRegEncuesta").show();
                $("#listaRegEncuesta").append(table);


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };

    cargarEncuesta = function (idEncuesta) {
        var session = validarSesion();
        console.log("session =>" + session);
        if (validarSesion()) {
            $("#regEncuestas").show();
            $("#listaEncuestas").hide();
            var encuesta = new Object();
            encuesta.idEncuesta = idEncuesta;

            $.ajax({
                url: "listaencuestas",
                type: 'POST',
                data: JSON.stringify(encuesta),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {

                    $.each(response, function (index, element) {
                        $("#hddIdEncuesta").val(element.idEncuesta);
                        $("#tituloEncuesta").append(element.descEncuesta);

                    });


                    cargaListaPreguntas();




                }, error: function (xhr, ajaxOptions, thrownError) {
                    bootbox.dialog({
                        closeButton: true,
                        message: "Error al guardar la insitituci&oacute;n",
                        title: "Error",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-danger"
                            }
                        }
                    });
                }
            });
        }

    };


    cargaListaPreguntas = function () {
        aplicacionEncuesta();

        var pregunta = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = $("#hddIdEncuesta").val();
        pregunta.encuesta = encuesta;
        pregunta.activo = "S";
        $.ajax({
            url: "listapreguntas",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                var table = "<table class=\"table table-striped\">" +
                        "<thead>" +
//                        " <tr>" +
//                        "  <th scope=\"col\">#</th>" +
//                        " <th scope=\"col\">Pregunta</th>" +
//                        " </tr>" +
                        "</thead><tbody>";
                var boton = " <button type=\"button\" class=\"btn btn-warning btn-sm btn-space\"" +
                        "onclick=\"mostrarModalRespuesta(idPregunta)\">Agregar Respuesta</button>";
                var respuesta = "";
                var html = "";
                var idPregunta = "";
                var espacio = "";
                $.each(response, function (index, element) {
                    respuesta = "";
                    html = "";
                    idPregunta = element.idPregunta;
                    $.each(element.respuesta, function (index, element) {

                        if (element.tipoRespuesta.idTipoRespuesta == 1) {
                            html = element.tipoRespuesta.descHtml;
                            respuesta += html.replace("contenido",  element.descRespuesta ).replace("valor", element.idRespuesta);
                        }
                        else if (element.tipoRespuesta.idTipoRespuesta !== 4) {
                            html = element.tipoRespuesta.descHtml;
                            respuesta += html.replace("contenido", "<b>" + element.descRespuesta + "</b><br>").replace("valor", element.idRespuesta).replace("nombre", "pregunta" + idPregunta);
                        } else {
                            respuesta += "<b>" + element.descRespuesta + "</b><br>";
                        }

                        $.each(element.listahijos, function (index, element) {
                            html = element.tipoRespuesta.descHtml;
                            respuesta += "&nbsp;&nbsp;&nbsp;&nbsp;" + html.replace("contenido", "<b>" + element.descRespuesta + "</b><br>").replace("valor", element.idRespuesta).replace("nombre", "pregunta" + idPregunta);

                        });



                    });
                    var pregunta = new Object();
                    pregunta.nombre = "pregunta" + idPregunta;
                    pregunta.idPregunta = idPregunta;
                    pregunta.contestada = "N";
                    preguntas.push(pregunta);

                    table += "<tr>" +
                            "<th class=\"rengpreguntas\" scope=\"row\">" + (index + 1) + "</th>" +
                            "<td> <label class=\"rengpreguntas\">" + element.descPregunta + "</label><br>" + respuesta + "</td>" +
                            "</tr>";
                });
                table += "<tr>" +
                        "<td colspan='2'> <button type=\"button\" class=\"btn btn-danger btn-space\"  onclick=\"terminarEncuesta()\">Terminar Encuesta</button></td>" +
                        "</tr>";
                table += " </tbody>" +
                        "</table>";
                $("#listaPreguntas").empty();
                $("#listaPreguntas").show();
                $("#listaPreguntas").append(table);




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    };

    cargarPregunta = function (idPregunta) {

        var pregunta = new Object();
        pregunta.idPregunta = idPregunta;
        pregunta.activo = "S";
        $.ajax({
            url: "listapreguntas",
            type: 'POST',
            data: JSON.stringify(pregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {



                $.each(response, function (index, element) {
                    $("#hddIdPregunta").val(element.idPregunta);
                    $("#txtDescPregunta").val(element.descPregunta);


                });





            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al guardar la insitituci&oacute;n",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };
    cargaTipoRespuesta = function () {
        $("#regEncuestas").show();
        $("#listaEncuestas").hide();
        var tipoPregunta = new Object();
        tipoPregunta.activo = "S";
        $("#cargaTipoRespuesta").empty();

        $.ajax({
            url: "listatiposrespuestas",
            type: 'POST',
            data: JSON.stringify(tipoPregunta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                var option = "";
                $.each(response, function (index, element) {
                    option += "<option value='" + element.idTipoRespuesta + "'>" + element.descTipoRespuesta + "</option>";
                });

                $("#cargaTipoRespuesta").append(option);




            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error cargar los tipos de preguntas",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };

    terminarEncuesta = function () {


        var idAplicacionEncuesta = $("#hddIdAplicacion").val();
        console.log("terminar encuesta" + preguntas.toString());
        var listaPreguntas = [];

        var guardar = true;
        $(".respuestas").each(function (index) {

            if ($(this).prop("checked")) {
                var preguntasrespuestas = new Object();
                var preguntas2 = preguntas.find(x => x.nombre === $(this).prop("name"));
                preguntas2.contestada = "S";
                var pregunta = new Object();
                var respuesta = new Object();
                pregunta.idPregunta = preguntas2.idPregunta;
                preguntasrespuestas.pregunta = pregunta;
                respuesta.idRespuesta = $(this).val();
                preguntasrespuestas.respuesta = respuesta;
                var aplicacionEncuesta = new Object();
                aplicacionEncuesta.idAplicacionEncuesta = idAplicacionEncuesta;
                preguntasrespuestas.aplicacion = aplicacionEncuesta;
                listaPreguntas.push(preguntasrespuestas);
                console.log($(this).prop("name") + "<==>" + $(this).prop("checked") + "<==>" + $(this).val());
            }
        });

        $.each(preguntas, function (index, element) {
            if (element.contestada == "N") {
                var nombre = $("label[name=" + element.nombre + "]").prop("name");
                console.log(nombre);
                guardar = false;
            }
        });
        guardar = true;
        if (guardar) {
            $.ajax({
                url: "guardarrespuestasencuesta",
                type: 'POST',
                data: JSON.stringify(listaPreguntas),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    salirEncuesta();
                    $("#hddIdAplicacion").val(response.idAplicacionEncuesta);
                    bootbox.dialog({
                        closeButton: true,
                        message: "Encuesta enviada, gracias.",
                        title: "Enviada",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-success"
                            }
                        }
                    });


                }, error: function (xhr, ajaxOptions, thrownError) {
                    bootbox.dialog({
                        closeButton: true,
                        message: "Error cargar los tipos de preguntas",
                        title: "Error",
                        buttons: {
                            success: {
                                label: "Aceptar",
                                className: "btn-danger"
                            }
                        }
                    });
                }
            });
            console.log("todas las preguntas fueron contestadas");
        } else
            console.log("faltan preguntas por responder");
    };

    aplicacionEncuesta = function () {
        var aplicacion = new Object();
        var encuesta = new Object();
        encuesta.idEncuesta = $("#hddIdEncuesta").val();
        aplicacion.encuesta = encuesta;
        $.ajax({
            url: "guardaraplicacion",
            type: 'POST',
            data: JSON.stringify(aplicacion),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {

                $("#hddIdAplicacion").val(response.idAplicacionEncuesta);



            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al generar la encuesta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });

    };


    $(function () {
        salirEncuesta();
        $('#product-card').hover(function () {
            $(this).addClass('animate');
            $('div.carouselNext, div.carouselPrev').addClass('visible');
        }, function () {
            $(this).removeClass('animate');
            $('div.carouselNext, div.carouselPrev').removeClass('visible');
        });

        // Flip card to the back side
        $('#view_details').click(function () {
            $('div.carouselNext, div.carouselPrev').removeClass('visible');
            $('#product-card').addClass('flip-10');
            setTimeout(function () {
                $('#product-card').removeClass('flip-10').addClass('flip90').find('div.shadow').show().fadeTo(80, 1, function () {
                    $('#product-front, #product-front div.shadow').hide();
                });
            }, 50);

            setTimeout(function () {
                $('#product-card').removeClass('flip90').addClass('flip190');
                $('#product-back').show().find('div.shadow').show().fadeTo(90, 0);
                setTimeout(function () {
                    $('#product-card').removeClass('flip190').addClass('flip180').find('div.shadow').hide();
                    setTimeout(function () {
                        $('#product-card').css('transition', '100ms ease-out');
                        $('#cx, #cy').addClass('s1');
                        setTimeout(function () {
                            $('#cx, #cy').addClass('s2');
                        }, 100);
                        setTimeout(function () {
                            $('#cx, #cy').addClass('s3');
                        }, 200);
                        $('div.carouselNext, div.carouselPrev').addClass('visible');
                    }, 100);
                }, 100);
            }, 150);
        });

        // Flip card back to the front side
        $('#flip-back').click(function () {

            $('#product-card').removeClass('flip180').addClass('flip190');
            setTimeout(function () {
                $('#product-card').removeClass('flip190').addClass('flip90');

                $('#product-back div.shadow').css('opacity', 0).fadeTo(100, 1, function () {
                    $('#product-back, #product-back div.shadow').hide();
                    $('#product-front, #product-front div.shadow').show();
                });
            }, 50);

            setTimeout(function () {
                $('#product-card').removeClass('flip90').addClass('flip-10');
                $('#product-front div.shadow').show().fadeTo(100, 0);
                setTimeout(function () {
                    $('#product-front div.shadow').hide();
                    $('#product-card').removeClass('flip-10').css('transition', '100ms ease-out');
                    $('#cx, #cy').removeClass('s1 s2 s3');
                }, 100);
            }, 150);

        });


        /* ----  Image Gallery Carousel   ---- */

        var carousel = $('#carousel ul');
        var carouselSlideWidth = 335;
        var carouselWidth = 0;
        var isAnimating = false;

        // building the width of the casousel
        $('#carousel li').each(function () {
            carouselWidth += carouselSlideWidth;
        });
        $(carousel).css('width', carouselWidth);

        // Load Next Image
        $('div.carouselNext').on('click', function () {
            var currentLeft = Math.abs(parseInt($(carousel).css("left")));
            var newLeft = currentLeft + carouselSlideWidth;
            if (newLeft == carouselWidth || isAnimating === true) {
                return;
            }
            $('#carousel ul').css({'left': "-" + newLeft + "px",
                "transition": "300ms ease-out"
            });
            isAnimating = true;
            setTimeout(function () {
                isAnimating = false;
            }, 300);
        });

        // Load Previous Image
        $('div.carouselPrev').on('click', function () {
            var currentLeft = Math.abs(parseInt($(carousel).css("left")));
            var newLeft = currentLeft - carouselSlideWidth;
            if (newLeft < 0 || isAnimating === true) {
                return;
            }
            $('#carousel ul').css({'left': "-" + newLeft + "px",
                "transition": "300ms ease-out"
            });
            isAnimating = true;
            setTimeout(function () {
                isAnimating = false;
            }, 300);
        });

    });


</script>

