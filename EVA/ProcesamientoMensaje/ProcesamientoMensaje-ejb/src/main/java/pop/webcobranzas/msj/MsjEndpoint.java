/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.msj;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;

/**
 *
 * @author Jyoverar
 */
public class MsjEndpoint extends Endpoint {

    private Session session;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("pop.webcobranzas.msj.MsjEndpoint.onOpen() ---- " + session);
    }
    
    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("pop.webcobranzas.endpoint.NotifyClientEndpoint.onClose()");
    }

    
    public void sendMessage(String text){
        System.out.println("pop.webcobranzas.msj.MsjEndpoint.sendMessage() " + text);
    }
    
}
