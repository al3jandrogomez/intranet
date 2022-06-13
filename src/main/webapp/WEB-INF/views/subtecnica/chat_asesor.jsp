<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Chat Application</title>
        <link rel="stylesheet" href="/resources/css/chat/main.css" />
    </head>
    <style>
        .hidden {
        display: none;
    }</style>
    <body>
        <noscript>
        <h2>Sorry! Your browser doesn't support Javascript</h2>
        </noscript>
        <input type="hidden" id="hddCveUsuario" value="0" />
        <input type="hidden" id="hddCveRegion" value="0"/>
        <input type='hidden' id="hddIdChat" value="0"/>
        <input type='hidden' id="name" value="0"/>
        <input type='hidden' id="room-id" value="0"/>
        <div id="username-page">
            <div class="username-page-container">
                <h1 class="title">Lista de personas en espera</h1>
                <div ><input id="atender" type="button" value="Atender Persona">
                    <div id="listaChatPendiente"></div> </div>

            </div>
            <div class="username-page-container">
                <h1 class="title">Lista Personas en Asesoria(No podra atender a otras personas si tiene usuarios en esta lista)</h1>
                <div ><input id="terminar" type="button" value="Terminar asesoria">

                    <div id="listaChatActivas"></div>
                </div>
            </div>
        </div>

        <div id="chat-page" class="hidden">
            <div class="chat-container">
                <div class="chat-header">
                    <h2> <span id="room-id-display"></span> <input type="button" value="Terminar Asesoria" onclick="cargarAsesoriaPendiente()"></h2>
                </div>
                <div class="connecting">
                    Connecting...
                </div>
                <ul id="messageArea">

                </ul>
                <form id="messageForm" name="messageForm" nameForm="messageForm">
                    <div class="form-group">
                        <div class="input-group clearfix">
                            <input type="text" id="message" placeholder="Type a message... or /join [room-id] to join another room."
                                   autocomplete="off" class="form-control"/>
                            <button type="submit" class="primary">Send</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="modal fade" id="terminaAsesoria" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal""
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="modalCorrect">
                        <div id="username-page">
                            <div class="username-page-container">
                                <h1 class="title">Ingrese los siguientes datos</h1>
                                <form id="usernameForm" name="usernameForm">
                                    <div class="form-group">
                                        <input type="text" id="observaciones" placeholder="Observaciones de asesoria" autocomplete="off" class="form-control" />
                                    </div>
                                    
                                    <div class="form-group">
                                        <button id ="terminar_asesoria" type="button" class=" button accent username-submit" onclick="terminarAsesoria()">Terminar Asesoria</button>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" >Cerrar</button>

                    </div>
                </div>
            </div>
        </div>

        <!--<script src="/webjars/jquery/jquery.min.js"></script>-->
        <script src="/webjars/sockjs-client/sockjs.min.js"></script>
        <script src="/webjars/stomp-websocket/stomp.min.js"></script>
        <script src="/webjars/js-cookie/js.cookie.js"></script>
        <script src="/resources/js/chat/chat_asesor.js"></script>
        <script src="/resources/js/chat/main.js"></script>
        
    </body>
</html>