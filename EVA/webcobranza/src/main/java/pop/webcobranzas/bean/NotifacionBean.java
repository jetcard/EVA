/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeNotificacion;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.INotificacionServ;
import pop.webcobranzas.servicio.NotificacionServ;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class NotifacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    // notificacion de busqueda
    private MaeNotificacion maeNotificacionB = new MaeNotificacion();
    
    // lista de Notificaciones
    private List<MaeNotificacion> maeNotificacionsList;
    
    // sericios de notificaciones
    private INotificacionServ notificacionServ = new NotificacionServ();

    public NotifacionBean() {
        
        try {
            maeNotificacionB.setCusuarioPaId(SessionUtils.getUserName().trim());
            maeNotificacionsList = notificacionServ.listarNotificacion(maeNotificacionB);
            System.out.println("pop.webcobranzas.bean.NotifacionBean.<init>()" + maeNotificacionsList.size());
        } catch (Exception ex) {
            Logger.getLogger(NotifacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    
//     public void actualizarNotificacion(MaeNotificacion oMaeNotificacion) {
//        try {
//            oMaeNotificacion.setBenviado("01");
//            notificacionServ.actualizar(oMaeNotificacion);
//        } catch (Exception ex) {
//            Logger.getLogger(NotifacionBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     }
    
    public INotificacionServ getNotificacionServ() {
        return notificacionServ;
    }

    public void setNotificacionServ(INotificacionServ notificacionServ) {
        this.notificacionServ = notificacionServ;
    }

    public MaeNotificacion getMaeNotificacionB() {
        return maeNotificacionB;
    }

    public void setMaeNotificacionB(MaeNotificacion maeNotificacionB) {
        this.maeNotificacionB = maeNotificacionB;
    }       

    public List<MaeNotificacion> getMaeNotificacionsList() {
        return maeNotificacionsList;
    }

    public void setMaeNotificacionsList(List<MaeNotificacion> maeNotificacionsList) {
        this.maeNotificacionsList = maeNotificacionsList;
    }
    
     
    
}
