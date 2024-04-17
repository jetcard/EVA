/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import pop.comun.dominio.MaeNotificacion;

/**
 *
 * @author Jyoverar
 */
@ApplicationScoped
@ServerEndpoint(value="/notificacion/{name}")
public class NotifyWebSocketServer {
    
    @Inject
    private NotifySessionHandler sessionHandler;
        
    @OnOpen
    public void open(Session session, @PathParam("name") String name) {
        
        //System.out.println("   --   LoginBean " + loginBean.toString());
        sessionHandler.addSession(session,name);
    }
    
    @OnClose
    public void close(Session session,@PathParam("name") String name) {
        sessionHandler.removeSession(session,name);
    }
    
    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(NotifyWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }
    
    @OnMessage
    public void handleMessage(String message, Session session,@PathParam("name") String name) {

        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("add".equals(jsonMessage.getString("action"))) {
                MaeNotificacion notificacion = new MaeNotificacion();
                notificacion.setCmaeNotificacionId(jsonMessage.getInt("id"));
                notificacion.setCusuarioDeId(jsonMessage.getString("dusu"));
                notificacion.setCusuarioPaId(jsonMessage.getString("pusu"));
                notificacion.setCtipoId(jsonMessage.getString("tipo"));
                //notificacion.setFnotificacion();
                notificacion.setDtitulo(jsonMessage.getString("titulo"));
                notificacion.setDcuerpo(jsonMessage.getString("cuerpo"));
                notificacion.setNcantNotificacion(jsonMessage.getInt("cant"));
                sessionHandler.addNotificacion(notificacion,name);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeNotificacion(id,name);
            }

        }
    }

}
