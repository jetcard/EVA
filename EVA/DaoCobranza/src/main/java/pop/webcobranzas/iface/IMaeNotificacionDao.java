/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeNotificacion;

/**
 *
 * @author Jyoverar
 */
public interface IMaeNotificacionDao {
    
    Integer insert(MaeNotificacion oMaeNotificacion);

    void update(MaeNotificacion oMaeNotificacion);

    void delete(MaeNotificacion oMaeNotificacion);

    ArrayList<MaeNotificacion> fetchAll(MaeNotificacion oMaeNotificacion);
    
    ArrayList<MaeNotificacion> fetchAllMsj(MaeNotificacion oMaeNotificacion);
    
}
