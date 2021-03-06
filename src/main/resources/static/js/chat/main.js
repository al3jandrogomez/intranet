'use strict';


var nameInput = $('#name');
var roomInput = $('#room-id');
var chat = $("#hddIdChat");
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var roomIdDisplay = document.querySelector('#room-id-display');
var atenderPersona = document.querySelector('#atender');
var terminar = document.querySelector('#terminar');
var terminar_asesoria=document.querySelector("#terminar-asesoria");

var stompClient = null;
var currentSubscription;
var username = null;
var roomId = null;
var topic = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect() {
    username = nameInput.val().trim();
//    Cookies.set('name', username);
    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('http://intranet.idpedomex.gob.mx:8091/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
//    event.preventDefault();
}

// Leave the current room and enter a new one.
function enterRoom(newRoomId) {
    roomId = newRoomId;
//    Cookies.set('roomId', roomId);
    var direccion = "";
    if (newRoomId == 1)
        direccion = "DIRECCIÓN REGIONAL TOLUCA";
    else if (newRoomId == 2)
        direccion = "DIRECCIÓN REGIONAL NORORIENTE(TLALNEPANTLA)";
    else if (newRoomId == 3)
        direccion = "DIRECCIÓN REGIONAL ORIENTE (ECATEPEC)";
    roomIdDisplay.textContent = direccion;
    var room = chat.val();
    topic = `/app/chat/${newRoomId}`;

    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }
    currentSubscription = stompClient.subscribe(`/channel/${newRoomId}`, onMessageReceived);

    stompClient.send(`${topic}/addUser`,
            {},
            JSON.stringify({sender: username, type: 'JOIN'})
            );
}

function onConnected() {
    enterRoom(roomInput.val());
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent.startsWith('/join ')) {
        var newRoomId = messageContent.substring('/join '.length);
        enterRoom(newRoomId);
        while (messageArea.firstChild) {
            messageArea.removeChild(messageArea.firstChild);
        }
    } else if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send(`${topic}/sendMessage`, {}, JSON.stringify(chatMessage));
    }
    messageInput.value = '';
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type == 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type == 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}







$(document).ready(function () {



    usernamePage.classList.remove('hidden');
    // usernameForm.addEventListener('submit', connect, true);
    messageForm.addEventListener('submit', sendMessage, true);
    atenderPersona.addEventListener('click', atiendePersona, false);
    terminar.addEventListener('click', cargarAsesoriaPendiente, false);
});
