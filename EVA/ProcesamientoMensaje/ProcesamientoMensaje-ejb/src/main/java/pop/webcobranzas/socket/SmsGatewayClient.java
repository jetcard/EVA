/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Jyoverar
 */
public class SmsGatewayClient extends WebSocketClient{

    public SmsGatewayClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake sh) {
        System.out.println("Cliente -> open");
    }

    @Override
    public void onMessage(String string) {
        System.out.println("Cliente -> message: " + string);
    }

    @Override
    public void onClose(int i, String string, boolean bln) {
        System.out.println("Cliente -> close");
    }

    @Override
    public void onError(Exception excptn) {
        System.out.println("Cliente -> error: " + excptn.getMessage());
    }
    
}
