/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeNotificacion;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegNotificacion;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbNotificacion")
public class SessionNotificacion implements INegNotificacion {

    FactoryDao ofDao = new FactoryDao();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Integer insertar(MaeNotificacion oMaeNotificacion) throws Exception {
        Integer newID = 0;
        
        try {
            // open conection
            ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getMaeNotificacion().insert(oMaeNotificacion);
        } catch (Exception e) {
            throw e;
        } 

        return newID;
    }

    @Override
    public void borrar(MaeNotificacion oMaeNotificacion) throws Exception {
        try {
            
            ofDao.getMaeNotificacion().delete(oMaeNotificacion);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<MaeNotificacion> listarNotificacion(MaeNotificacion oMaeNotificacion) throws Exception {
        List<MaeNotificacion> oSesList = null;

        oSesList = ofDao.getMaeNotificacion().fetchAll(oMaeNotificacion);
        return oSesList;
    }

    @Override
    public List<MaeNotificacion> listarNotificacionMsj(MaeNotificacion oMaeNotificacion) throws Exception {
        List<MaeNotificacion> oSesList = null;

        oSesList = ofDao.getMaeNotificacion().fetchAllMsj(oMaeNotificacion);
        return oSesList;
    }

    @Override
    public void actualizar(MaeNotificacion oMaeNotificacion) throws Exception {
        
        try {
            // open conection
            //ofDao.getConexionDaoOracle().ConexionOpen();
            // execute command
            ofDao.getMaeNotificacion().update(oMaeNotificacion);
        } catch (Exception e) {
            throw e;
        } 
    }
}
