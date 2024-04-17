/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var countMsj = 0;
function startApp() {
    // crear ws cliente
    var wsUri = "ws://192.168.70.204:8989";
    console.log(wsUri);
    var client = new WebSocket(wsUri);

    // abrir ws
    client.onopen = function (event) {
        var log = document.getElementById("log");
        log.textContent = log.textContent + "\n" + "conexion establecida";
        // creando  span
        var estadoSpan = document.createElement("span");
        estadoSpan.setAttribute("class", "heading-online");
        estadoSpan.textContent = "Online";
        // buscando div
        var divEstado = document.getElementById("divEstado");
        divEstado.appendChild(estadoSpan);

    };

    // cerrar ws
    client.onclone = function (event) {
        var log = document.getElementById("log");
        log.textContent = log.textContent + "\n" + "conexion cerrada";
    };

    // error en ws
    client.onerror = function (event) {
        var log = document.getElementById("log");
        log.textContent = log.textContent + "\n" + "conexion con error";
        // creando  span
        var estadoSpan = document.createElement("span");
        estadoSpan.setAttribute("class", "heading-offline");
        estadoSpan.textContent = "Offline";
        // buscando div
        var divEstado = document.getElementById("divEstado");
        divEstado.appendChild(estadoSpan);
    };

    // mensaje
    client.onmessage = function (event) {
        var response = JSON.parse(event.data);
        console.log(response);
        switch (response.type) {
            case "success":
                /*alert(response.message);*/
                var message = document.getElementById("message").value;
                document.getElementById("message").value = "";
                // creando  i
                var iCheck = document.createElement("i");
                iCheck.setAttribute("id", "iChk_" + countMsj);
                iCheck.setAttribute("class", "fa fa-circle-thin");
                // creando  span
                var msjSpanCheck = document.createElement("span");
                msjSpanCheck.setAttribute("class", "message-time pull-right");
                msjSpanCheck.appendChild(iCheck);
                // creando  span
                var msjSpan = document.createElement("span");
                msjSpan.setAttribute("class", "message-time pull-right");
                msjSpan.textContent = "Jueves";
                // creando  div message
                var msjDiv = document.createElement("div");
                msjDiv.setAttribute("class", "message-text");
                msjDiv.textContent = message;
                // creando  div sender
                var senderDiv = document.createElement("div");
                senderDiv.setAttribute("class", "sender");
                //
                senderDiv.appendChild(msjDiv);
                senderDiv.appendChild(msjSpanCheck);
                senderDiv.appendChild(msjSpan);
                // creando  div main sender
                var senderMainDiv = document.createElement("div");
                senderMainDiv.setAttribute("class", "col-sm-12 message-main-sender");
                //
                senderMainDiv.appendChild(senderDiv);
                // creando  div message body
                var msgBodyDiv = document.createElement("div");
                msgBodyDiv.setAttribute("class", "row message-body");
                msgBodyDiv.appendChild(senderMainDiv);

                // buscando div
                var divPaint = document.getElementById("div_987154718");
                divPaint.appendChild(msgBodyDiv);

                break;
            case "error" :
                alert(response.message);
                break;
            case "notification" :
                var log = document.getElementById("log");
                if (response.success) {
                    log.textContent = log.textContent + "\n" +
                            "informe de exito : " + response.message;
                    if (response.message == "SMS entregado") {
                        // buscando div
                        var iCheck = document.getElementById("iChk_" + countMsj);
                        iCheck.className = "fa fa-circle fa-color-blue-40";
                    }
                    if (response.message == "SMS enviado") {
                        // buscando div
                        var iCheck = document.getElementById("iChk_" + countMsj);
                        iCheck.className = "fa fa-circle fa-color-red";
                    }

                } else {
                    log.textContent = log.textContent + "\n" +
                            "error en informe : " + response.message;
                }
                break;
            case "received" :
                var log = document.getElementById("log");
                log.textContent = log.textContent + "\n" +
                        "informe de recepcion : " + response.from + " - " + response.message;

                // creando  span
                var msjSpan = document.createElement("span");
                msjSpan.setAttribute("class", "message-time pull-right");
                msjSpan.textContent = "Jueves";
                // creando  div message
                var msjDiv = document.createElement("div");
                msjDiv.setAttribute("class", "message-text");
                msjDiv.textContent = response.message;
                // creando  div receiver
                var receiverDiv = document.createElement("div");
                receiverDiv.setAttribute("class", "receiver");
                //
                receiverDiv.appendChild(msjDiv);
                receiverDiv.appendChild(msjSpan);
                // creando  div main receiver
                var receiverMainDiv = document.createElement("div");
                receiverMainDiv.setAttribute("class", "col-sm-12 message-main-receiver");
                //
                receiverMainDiv.appendChild(receiverDiv);
                // creando  div message body
                var msgBodyDiv = document.createElement("div");
                msgBodyDiv.setAttribute("class", "row message-body");
                msgBodyDiv.appendChild(receiverMainDiv);

                // buscando div  
                var divPaint = document.getElementById("div_987154718");
                divPaint.appendChild(msgBodyDiv);

                break;
        }
    };

    document.getElementById("send").onclick = function () {
        countMsj = countMsj + 1;
        // to
        var to = document.getElementById("to").textContent;
        // message
        var message = document.getElementById("message").value;
        //
        var json = {
            to: to,
            message: message
        };

        // mengirim ke server via websocket
        client.send(JSON.stringify(json));

    };
}
;



window.onload = startApp;
