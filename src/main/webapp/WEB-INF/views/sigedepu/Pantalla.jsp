<style>
    .atendiendo{
        font-size: 16px;
        
    }
</style>
<div class="form-group ">
    <div class="col-md-5">
        <video class='video' width="840" height="680" style='float: left;'>
            <source src="" type="video/mp4" controls>
            Tu navegador no implementa el elemento
            <code>video</code>
            .
        </video>
    </div>

    <div class="widget-panel"
         style="margin-top: 20px; background-color: #ffffff; border-color: #ffffff; color: #27ae60;">
        <div class="row ">
            <div class="col-md-8 actual">
                <div class="list-group active text-center" id="atencion">
                    <div class="list-group-item active" style="border-color: #ffffff; background-color: #ffffff;"
                         style="padding: 30px 15px;">
                        <h2 style="font-size: 60px;">Pase A:</h2>
                        <strong><h1
                                style="font-size: 130px; display: block; color: #27ae60;"
                                id="modulo"></h1></strong>
                        <h2 style="font-size: 80px; display: block; color: #27ae60;"
                            id="peticionario"></h2>
                    </div>
                </div>

            </div>



            <div class="col-md-3 atendiendo">

                <div class="list-group atendiendo" id="head-informativa"
                     style="margin: 0; color: #27ae60;">
                    <div class="list-group-item active" style="border-color: #ffffff; background-color: #ffffff;">
                        <div class="row">
                            <div class="col-md-8 text-center">
                                <h3 style="color: #27ae60;" class="list-group-item-heading">Atendiendo</h3>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="list-group" id="list-informativa"
                     style="color: #27ae60;"></div>

            </div>




        </div>

    </div>
</div>

<script>
    video = function (idVideo, descVideo, rutaVideo, activo) {
        this.idvideo = idVideo;
        this.descVideo = descVideo;
        this.rutaVideo = rutaVideo;
        this.activo = activo;

    };

    var audio = new Audio('/resources/audio/turn.mp3');



    solicitudAtencion = function (region, fechaRegistro, estatus, fechaRechazo,
            id) {
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


    listaAtencion = function (idRegion) {
        console.log("lista Atencion");

        dRegion = new regiones(idRegion, null);
        dSolicitudes = new solicitudAtencion(dRegion);
        dRegion = JSON.stringify(dRegion);


        var lista = "";
        var nombre = "";
        $
                .ajax({
                    async: false,
                    data: dRegion,
                    url: 'atenciones',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        // 				$("#modalCorrect").empty();
                        // 				$("#modalCorrect").append("Procesando");
                        // 				$("#myModalCorrect").modal();
                    },
                    success: function (response) {
                        $('#modulo').empty();
                        $('#peticionario').empty();
                        $("#list-informativa").empty();

                        $
                                .each(
                                        response,
                                        function (index, element) {
                                            if (index < 3) {
                                                nombre = element.peticionario.nombre
                                                        + " "
                                                        + element.peticionario.paterno
                                                        + " "
                                                        + element.peticionario.materno;
                                                if (index == 0) {
                                                    $('#modulo')
                                                            .append(
                                                                    element.estatus_informativa);
                                                    $('#peticionario').append(
                                                            nombre);
                                                }

                                                lista += '<a style=\"color:#27ae60;\" href="#" class="list-group-item" data-id="' + element.id + '">'
                                                        + '<div class="row atendiendo">'
//                                                        + '<div class="">'
//                                                        + '	<h4 class="list-group-item-heading">'
//                                                        + element.folio
                                                        + '	<h4 class="list-group-item-heading">'
                                                        + nombre
                                                        + '		</h4> <br>'
//                                                        + '</div>'
//                                                        + '<div class="">'
                                                        + '	<h2>'
                                                        + element.estatus_informativa
                                                        + '		</h2>'
//                                                        + '</div>'
                                                        + '</div>';
                                            }

                                        });
                        $("#list-informativa").append(lista);


                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError").append("Error al cargar los datos");
                        $("#myModalError").modal();
                    }
                });

    }
    $(function () {
        $('#pantalla #modulo').append("modulo");
        $('#pantalla #peticionario').append("asesor");
        var region = $("#idRegion").val();
        if (region == 1)
            region = 2;
        else if (region == 2)
            region = 3;
        else if (region == 3)
            region = 1;

        listaAtencion(region);
        var socket = new WebSocket(
                "ws://sigedepu.idpedomex.gob.mx:9000/socket/pantalla-informativa");
        var socketTrabajo = new WebSocket(
                "ws://sigedepu.idpedomex.gob.mx:9000/socket/pantalla-informativa/estudio");
        var socketDireccion = new WebSocket("ws://sigedepu.idpedomex.gob.mx:9000/socket/patrocinio");

        socket.onopen = function () {
            console.log("Socket has been opened!");
        }


        // SOCKET PARA ASESORIAS
        socket.onmessage = function (msg) {
            response = $.parseJSON(msg.data);
            // 			$("#pantalla").append(response);
            // 			$("#pantalla").append(msg.data);
            console.log("Region login" + region + " Region sistema"
                    + response.region.id);
            if (response.region.id == region) {
                audio.play();
                listaAtencion(region);

                // 				console.log("Modulo" + response.asesor.modulo.modulo);
                // 				console.log("Peticionario"
                // 						+ response.peticionario.nombreCompleto);
                // 				$('#modulo').empty();
                // 				$('#peticionario').empty();
                // 				$('#modulo').append(response.asesor.modulo.modulo);
                // 				$('#peticionario').append(
                // 						response.peticionario.nombreCompleto);

            }
        }
        socketTrabajo.onopen = function () {
            console.log("Socket has been opened!");
        }
        socketTrabajo.onmessage = function (msg) {
            response = $.parseJSON(msg.data);
            console.log(response);
            console.log(msg.data);

            // 			console.log("Region login" + region + " Region sistema"
            // 					+ response.region.id);
            if (response.asesoria.solicitudAtencion.region.id == region) {
                audio.play();
                listaAtencion(region);
                // 				console.log("TRABAJO SOCIAL");
                // 				console.log("Peticionario"
                // 						+ response.peticionario.nombreCompleto);
                $('#modulo').empty();
                $('#peticionario').empty();
                $('#modulo').append("TRABAJO SOCIAL");
                $('#peticionario').append(
                        response.peticionario.nombreCompleto);

            }
        };
        
        /*
        socketDireccion.onopen = function () {
            console.log("Socket has been opened!");
        }
        socketDireccion.onmessage = function (evt) {
// 	    	Evomatik.console.log('-> Socket onmessage Direccion');
// 		 	Evomatik.console.log($.parseJSON(evt.data));
            response = $.parseJSON(evt.data);
            if (response.region.id == region) {
                audio.play();
                listaAtencion(region);
// 		 		response.estatusInformativa = 'Dirección';
// 			 	$('#atencion #modulo').html('Dirección').hide().fadeIn();
// 			 	$('#atencion #peticionario').html(response.peticionario.nombreCompleto).hide().fadeIn();
// 			 	defaultTemplate = _.template($("#object-Template-Lista").html());
// 			 	contentTemplate = defaultTemplate({object : response});
// 			 	$('#list-informativa').find('.list-group-item[data-id='+response.id+']').remove();
// 			 	$('#list-informativa').prepend(contentTemplate);
            }
        };}*/
        var listaVideos = [];

        videos = new video(null, null, null, 'S');
        dVideo = JSON.stringify(videos);
        console.log(dVideo);
        $.ajax({
            async: false,
            data: dVideo,
            url: 'listaVideos',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {
                // 				$("#modalCorrect").empty();
                // 				$("#modalCorrect").append("Procesando");
                // 				$("#myModalCorrect").modal();
            },
            success: function (response) {
                // 				$('#myModalCorrect').modal('hide');
                // 				$("#modalCorrect").empty();
                if (response.length > 0) {
                    var option = "";
                    $.each(response, function (index, element) {
                        console.log("agregando videos a la lista");
                        listaVideos.push(element);

                    });

                } else {
                    $("#modalCorrect").append("Error en catalogo de partidas");
                    $("#myModalCorrect").modal();
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                $("#myModalError").append("Error en catalogo de partidas");
                $("#myModalError").modal();
            }
        });

        if (listaVideos.length > 0) {
            var index = listaVideos.length;
            var videoFile = "";
            var $video = $('.video');
            videoSrc = $('source', $video)
                    .attr('src', listaVideos[0].rutaVideo);
            $video[0].volume = listaVideos[0].volumen;

            $video[0].load();
            $video[0].play();
            console.log("Total del videos =" + index);
            $(".video").on(
                    "timeupdate",
                    function (event) {
                        //console.log(this.currentTime+" "+ this.duration);
                        if (this.currentTime === this.duration && index > 0) {

                            index = index - 1;
                            console.log("Siguiente video "
                                    + listaVideos[index].rutaVideo + " faltan"
                                    + index + " videos por reproducir");

                            $('source', $video).attr('src',
                                    listaVideos[index].rutaVideo);

                            $video[0].volume = listaVideos[index].volumen;


                            $video[0].load();
                            $video[0].play();
                        }
                        if (index <= 0) {
                            index = listaVideos.length;
                        }

                    });
        } else {
            console.log("No hay videos en la lista");
        }

    });
</script>