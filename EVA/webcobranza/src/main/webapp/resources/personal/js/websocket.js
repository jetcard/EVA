/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:8080/webcobranza/notificacion/" + document.getElementById("usernamePost").innerHTML.trim());
//var socket = new WebSocket("ws://192.168.0.92:8585/webcobranza/notificacion");

//var socket = new WebSocket("ws://192.168.70.113:8989/" + document.getElementById("usernamePost").innerHTML.trim());

socket.onopen = function onOpen() {
    console.log("onOpen notificacion");
};

socket.onmessage = function onMessage(event) {
    var notificacion = JSON.parse(event.data);
    if (notificacion.action === "add") {
        printNotifyElement(notificacion);
    }
    if (notificacion.action === "remove") {
        document.getElementById("notifi" + notificacion.id).remove();
    }
};

socket.onerror = function onError(evt) {
    console.log("onError " + evt.data);
};

function printNotifyElement(notificacion) {
    // li principal
    var liNotify = document.getElementById("liNotify");
    // a de cantidad de notificaciones
    var aLiNotify = liNotify.children[0];
    // span de cantidad 
    var spanAIlNotify = aLiNotify.children[1];
    //spanAIlNotify.innerHTML = notificacion.id;
    spanAIlNotify.innerHTML = notificacion.cant;

    // ul de notfiticacion
    var ulLiNotify = liNotify.children[1];
    // todas las notificaciones
    var liIni = ulLiNotify.children[0];

    // creando  li
    var notifyLi = document.createElement("li");

    if (notificacion.tipo == "0001") {
        // label-warning-light si es de tipo 
        notifyLi.setAttribute("class", "label-warning-light");
    }

    // creando href
    var notifyLiA = document.createElement("a");
    notifyLiA.setAttribute("id", "notifi" + notificacion.id);

    // creando  div
    var notifyLiADiv = document.createElement("div");


    // creando  i
    var notifyLiADivI = document.createElement("i");
    notifyLiADivI.setAttribute("class", "fa fa-envelope fa-fw");


    // creando  span
    var notifyLiADivSpan = document.createElement("span");
    notifyLiADivSpan.setAttribute("class", "pull-right text-muted small");
    //
    notifyLiADiv.appendChild(notifyLiADivI);
    if (notificacion.tipo == "0001") {
        notifyLiADiv.innerHTML = "<i class='fa fa-bell'></i> " + notificacion.titulo;
    } else {
        notifyLiADiv.innerHTML = "<i class='fa fa-envelope fa-fw'></i> " + notificacion.titulo;
    }



    // creando script
//    var dvscript = document.createElement("div");
//    dvscript.innerHTML="<script type='text/javascript'>$(document).ready(function () {toastr.success('Recordatorio TCHN08620', 'Mensaje');});</script>";
//    document.body.appendChild(dvscript);


    notifyLiADiv.appendChild(notifyLiADivSpan);
    notifyLiA.appendChild(notifyLiADiv);

    //notifyLiA.appendChild(script);

    notifyLi.appendChild(notifyLiA);
    ulLiNotify.insertBefore(notifyLi, liIni);

    // ul de notfiticacion
    if (notificacion.tipo == "0001") {
        var divNotifyMaster = liNotify.children[2];
        var scriptElement = document.createElement('script');
        scriptElement.setAttribute('type', 'text/javascript');
        scriptElement.innerText = 'var options = {body: \'' + notificacion.cuerpo + '\',icon: \'http://localhost:8080/webcobranza/resources/personal/img/alarm.png\'}; var notification = new Notification(\'' + notificacion.titulo + '\', options);';
        divNotifyMaster.appendChild(scriptElement);
    }
    /*
     var notifyLi = document.createElement("li");
     ulNotify.appendChild(notifyLi);
     
     var notifyLiDiv = document.createElement("div");
     // agrego un titulo
     notifyLiDiv.innerHTML = "jhon innerHTML";
     
     var notifyLiDivI = document.createElement("i");
     notifyLiDivI.setAttribute("class","fa fa-envelope fa-fw");
     // agrego el i
     notifyLiDiv.appendChild(notifyLiDivI);
     // agrego el titu
     notifyLi.appendChild(notifyLiDiv);
     */
}

function removeDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}