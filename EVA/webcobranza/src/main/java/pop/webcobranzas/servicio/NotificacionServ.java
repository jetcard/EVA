/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeNotificacion;
import pop.webcobranzas.iface.INotificacionServ;
import pop.webcobranzas.negocio.INegNotificacion;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class NotificacionServ implements INotificacionServ, Serializable {

    INegNotificacion iNegNotificacionEJB;

    @Override
    public Integer insertar(MaeNotificacion oMaeNotificacion) throws Exception {
        iNegNotificacionEJB = (INegNotificacion) Utilidades.getEJBRemote("SessionNotificacion", INegNotificacion.class.getName());
        return iNegNotificacionEJB.insertar(oMaeNotificacion);
    }

    @Override
    public void borrar(MaeNotificacion oMaeNotificacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeNotificacion> listarNotificacion(MaeNotificacion oMaeNotificacion) throws Exception {
        iNegNotificacionEJB = (INegNotificacion) Utilidades.getEJBRemote("SessionNotificacion", INegNotificacion.class.getName());
        return iNegNotificacionEJB.listarNotificacion(oMaeNotificacion);
    }

    @Override
    public List<MaeNotificacion> listarNotificacionMsj(MaeNotificacion oMaeNotificacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeNotificacion oMaeNotificacion) throws Exception {
        iNegNotificacionEJB = (INegNotificacion) Utilidades.getEJBRemote("SessionNotificacion", INegNotificacion.class.getName());
        iNegNotificacionEJB.actualizar(oMaeNotificacion);
    }

}
