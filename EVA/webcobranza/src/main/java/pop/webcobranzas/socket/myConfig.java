/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 *
 * @author Jyoverar
 */
public class myConfig extends Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // save the http session based on idea from
        // http://stackoverflow.com/questions/17936440/accessing-httpsession-from-httpservletrequest-in-a-web-socket-socketendpoint
//        final Object httpSession = request.getHttpSession();
//        if (httpSession != null) {
//            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
//        }
//        super.modifyHandshake(sec, request, response);

        System.out.println(" -      pop.webcobranzas.socket.myConfig.modifyHandshake()");

        try {
            HttpSession session = (HttpSession) request.getHttpSession();
            System.out.println("HttpSession id: " + session.getId());
            System.out.println("HttpSession creation time: " + session.getCreationTime());
        } catch (Exception e) {
            
        }
        super.modifyHandshake(sec, request, response);

    }

}
