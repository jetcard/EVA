/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import pop.webcobranzas.msj.IMensajeTexto;

/**
 *
 * @author Jyoverar
 */
@Singleton(mappedName = "SessionBeanMensajeTexto")
@Startup
public class SessionBeanMensajeTexto implements IMensajeTexto{
    
    private final String WS_SERVER_URL = "ws://192.168.70.204:8989"; //fictitious
    private Session session = null;

    
    @PostConstruct
    public void bootstrap() {
        WebSocketContainer webSocketContainer = null;
        try {
            webSocketContainer = ContainerProvider.getWebSocketContainer();
            session = webSocketContainer.connectToServer(StockMensajeTextoClient.class, new URI(WS_SERVER_URL));

            System.out.println("Connected to WS endpoint " + WS_SERVER_URL);
            session.addMessageHandler(new MessageHandler.Whole<String>() {

                @Override
                public void onMessage(String msg) {
                    System.out.println(".onMessage()------------- msg ---" + msg );
                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            Logger.getLogger(SessionBeanMensajeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void destroy() {
        close();
    }

    private void close() {
        try {
            session.close();
            System.out.println("CLOSED Connection to WS endpoint " + WS_SERVER_URL);
        } catch (IOException ex) {
            Logger.getLogger(SessionBeanMensajeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
