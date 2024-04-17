/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.Session;

/**
 *
 * @author Jyoverar
 */
@ClientEndpoint
public class StockMensajeTextoClient {
    
//    @OnOpen
//    public void OnOpen(Session session) {
//        System.out.println("== StockMensajeTextoClient  == OnOpen == " + session);
//    }
//
//    @OnMessage
//    public void onMessage(String msg) {
//        System.out.println("== StockMensajeTextoClient  == onMessage == " + msg);
//    }

    @OnClose
    public void closed(Session session) {
        System.out.println("== StockMensajeTextoClient  == closed == " + session + " closed");

    }

    @OnError
    public void error(Throwable error) {
        System.out.println("== StockMensajeTextoClient  == error == " + error.getMessage());

    }

}
