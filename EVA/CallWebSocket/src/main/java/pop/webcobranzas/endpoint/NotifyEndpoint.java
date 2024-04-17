/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.endpoint;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import pop.comun.dominio.MaeNotificacion;

/**
 *
 * @author Jhon Yovera
 */
public class NotifyEndpoint extends Endpoint {

    private Session session;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;

        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                System.out.println("!!!! retrive " + message);
            }
        });
    }

    public void sendMessage(List<MaeNotificacion> list) throws IOException {

        final JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage;
        try {
            for (MaeNotificacion maeNotificacion : list) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                addMessage = provider.createObjectBuilder()
                        .add("action", "add")
                        .add("id", (int) maeNotificacion.getCmaeNotificacionId())
                        .add("cant", (int) maeNotificacion.getNcantNotificacion())
                        .add("dusu", maeNotificacion.getCusuarioDeId())
                        .add("pusu", maeNotificacion.getCusuarioPaId())
                        .add("tipo", maeNotificacion.getCtipoId())
                        .add("fecha", formatter.format(maeNotificacion.getFnotificacion()))
                        .add("titulo", maeNotificacion.getDtitulo())
                        .add("cuerpo", maeNotificacion.getDcuerpo())
                        .build();

                //System.out.println("Sending message to endpoint: " + addMessage.toString());
                session.getBasicRemote().sendText(addMessage.toString());
            }
            session.close();
        } catch (IOException ex) {
            Logger.getLogger(NotifyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.close();
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("pop.webcobranzas.endpoint.NotifyClientEndpoint.onClose()");
    }
    
}
