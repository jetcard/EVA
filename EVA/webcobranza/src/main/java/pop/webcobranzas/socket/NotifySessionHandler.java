/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pop.comun.dominio.MaeNotificacion;
import pop.comun.dominio.MaeSesion;
import pop.webcobranzas.iface.INotificacionServ;
import pop.webcobranzas.iface.ISesionServ;
import pop.webcobranzas.servicio.NotificacionServ;
import pop.webcobranzas.servicio.SesionServ;

/**
 *
 * @author Jyoverar
 */
@ApplicationScoped
public class NotifySessionHandler {

    private final Set<Session> sessions = new HashSet<>();

    private ISesionServ sesionServ = new SesionServ();
    private INotificacionServ notificacionServ = new NotificacionServ();

    //private final Set<MaeSesion> sessiones = new HashSet<>();
    private final Set<MaeNotificacion> notificaciones = new HashSet<>();

    public void addSession(Session session, String name) {
        //System.out.println(" usuario " + name + "  - id - " + session.getId());
        //System.out.println("[" + name + "] " + " NotifySessionHandler - addSession " + "  - id - " + session.getId());

        if (!name.equals("SERVER")) {
            
            System.out.println("[" + name + "] " + " NotifySessionHandler - addSession " + "  - id - " + session.getId());
            
            session.getUserProperties().put("username", name);
            //System.out.println("-- NotifySessionHandler - addSession() " + session.getUserProperties().get("username"));
            sessions.add(session);
            // adicionando a la base de datos las sessiones
            MaeSesion oMaeSesion = new MaeSesion();
            oMaeSesion.setCsesionId(session.getId());
            oMaeSesion.setCusuarioId(name);
            //System.out.println(" ----- Inico Agregar Session ------ ");
            try {
                getSesionServ().insertar(oMaeSesion);
                //Thread.sleep(1000);
            } catch (Exception ex) {
                Logger.getLogger(NotifySessionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(" ----- Fin Agregar Session ------ ");
            for (MaeNotificacion notificacion : notificaciones) {
                if (notificacion.getCusuarioPaId().equals(name)) {
                    JsonObject addMessage = createAddMessage(notificacion);
                    sendToSession(session, addMessage);
                }
            }
        }
    }

    public void removeSession(Session session, String name) {
        try {

            MaeSesion oMaeSesion = new MaeSesion();
            oMaeSesion.setCsesionId(session.getId());
            oMaeSesion.setCusuarioId(name);
            //System.out.println(" ----- Inico Borrar Session ------ ");
            try {
                getSesionServ().borrar(oMaeSesion);
                //Thread.sleep(1000);
            } catch (Exception ex) {
                Logger.getLogger(NotifySessionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(" ----- Fin Borrar Session ------ ");
            sessions.remove(session);

        } catch (Exception e) {
            System.out.println(" removeSession  error ----- ");
        }

    }

    public List<MaeNotificacion> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }

    public void addNotificacion(MaeNotificacion notificacion, String name) {
        //device.setId(deviceId);
        boolean bAdd = true;
        //System.out.println("pop.webcobranzas.socket.NotifySessionHandler.addNotificacion() cantidad --- > " + notificacion.getNcantNotificacion());
        for (MaeNotificacion notify : notificaciones) {
            if (((int) notify.getCmaeNotificacionId()) == ((int) notificacion.getCmaeNotificacionId())) {
                bAdd = false;
                break;
            }
        }
        if (bAdd) {
            notificacion.setBenviado("01");
            notificacion.setcUsuarioMod(name.toUpperCase().trim());
            try {
                System.out.println("pop.webcobranzas.socket.NotifySessionHandler.addNotificacion()   -  Actualizar");
                getNotificacionServ().actualizar(notificacion);
            } catch (Exception ex) {
                Logger.getLogger(NotifySessionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            notificaciones.add(notificacion);
            //deviceId++;
            JsonObject addMessage = createAddMessage(notificacion);
            // enviar a las sessiones
            sendToAllConnectedSessions(addMessage);
        }

    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            //Logger.getLogger(NotificacionSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendToAllConnectedSessions(JsonObject message) {

        for (Session session : sessions) {
            //System.out.println(" - NotifySessionHandler.sendToAllConnectedSessions() from " + message.getString("pusu") + " to " + session.getUserProperties().get("username"));
            if (message.getString("pusu").equals(session.getUserProperties().get("username"))
                    || message.getString("pusu").equals("all")) {
                sendToSession(session, message);
            }
        }
    }

    private JsonObject createAddMessage(MaeNotificacion oMaeNotificacion) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", oMaeNotificacion.getCmaeNotificacionId().toString())
                .add("cant", "" + oMaeNotificacion.getNcantNotificacion())
                .add("dusu", oMaeNotificacion.getCusuarioDeId())
                .add("pusu", oMaeNotificacion.getCusuarioPaId())
                .add("tipo", oMaeNotificacion.getCtipoId())
                .add("titulo", oMaeNotificacion.getDtitulo())
                .add("cuerpo", oMaeNotificacion.getDcuerpo())
                //.add("description", oMaeNotificacion.getFnotificacion().toString())
                .build();
        return addMessage;
    }

    private MaeNotificacion getNotificacionById(int id) {
        for (MaeNotificacion objNot : notificaciones) {
            if ((int) objNot.getCmaeNotificacionId() == id) {
                return objNot;
            }
        }
        return null;
    }

    public void removeNotificacion(int id, String name) {

        MaeNotificacion notificacion = getNotificacionById(id);

        if (notificacion != null) {
            //System.out.println(" removeNotificacion() - > " + notificacion.getCmaeNotificacionId());
            notificaciones.remove(notificacion);
            notificacion.setcUsuarioMod(name);
            notificacion.setBrecibido("01");
            try {
                getNotificacionServ().actualizar(notificacion);
            } catch (Exception ex) {
                Logger.getLogger(NotifySessionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .add("pusu", name)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        } // pos caida de servidor
        else {

            notificacion = new MaeNotificacion();
            notificacion.setCmaeNotificacionId(id);
            notificacion.setBenviado("01");
            notificacion.setBrecibido("01");
            notificacion.setcUsuarioMod(name);
            System.out.println(" removeNotificacion() B -> " + notificacion.getCmaeNotificacionId());
            try {
                getNotificacionServ().actualizar(notificacion);
            } catch (Exception ex) {
                Logger.getLogger(NotifySessionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ISesionServ getSesionServ() {
        return sesionServ;
    }

    public void setSesionServ(ISesionServ sesionServ) {
        this.sesionServ = sesionServ;
    }

    public INotificacionServ getNotificacionServ() {
        return notificacionServ;
    }

    public void setNotificacionServ(INotificacionServ notificacionServ) {
        this.notificacionServ = notificacionServ;
    }

}
