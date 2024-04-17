/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeNotificacion;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegNotificacion {

    Integer insertar(MaeNotificacion oMaeNotificacion) throws Exception;

    void actualizar(MaeNotificacion oMaeNotificacion) throws Exception;

    void borrar(MaeNotificacion oMaeNotificacion) throws Exception;

    List<MaeNotificacion> listarNotificacion(MaeNotificacion oMaeNotificacion) throws Exception;

    List<MaeNotificacion> listarNotificacionMsj(MaeNotificacion oMaeNotificacion) throws Exception;

}
