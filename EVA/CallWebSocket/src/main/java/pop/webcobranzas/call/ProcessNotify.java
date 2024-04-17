/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.call;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import pop.comun.dominio.MaeNotificacion;
import pop.properties.ManageProperties;
import pop.webcobranzas.endpoint.NotifyEndpoint;

/**
 *
 * @author Jhon Yovera
 */
public class ProcessNotify {

    private WebSocketContainer container;
    private NotifyEndpoint endpoint;
    
    public void buscarAlertas(List<MaeNotificacion> list) throws URISyntaxException {
		ManageProperties properties=new ManageProperties();
		
        System.out.println(" <i> ProcessNotify BuscarAlertas " + LocalDateTime.now());
        container = ContainerProvider.getWebSocketContainer();
        endpoint = new NotifyEndpoint();
        
        try {
            //System.out.println("    inicio conexcion ");
             container.connectToServer(endpoint,new URI("ws://"+
                                                       properties.getUrl_servidorweb()+
                                                       "/webcobranza/notificacion/SERVER") );

            //System.out.println("        envia msj ");
            endpoint.sendMessage(list);
            //System.out.println("        finaliza msj ");
            
        } catch (DeploymentException | IOException ex) {
            Logger.getLogger(ProcessNotify.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(" <f> ProcessNotify BuscarAlertas " + LocalDateTime.now());
    }

}
