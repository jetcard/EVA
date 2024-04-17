/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeSesion;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegSesion;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbSesion")
public class SessionSesion implements INegSesion{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(MaeSesion oMaeSesion) throws Exception {
        Integer newID = 0;
        
        try {
            // open conection
            //ofDao.getConexionDaoOracle().ConexionOpen();
            // execute command
            newID = ofDao.getMaeSesion().insert(oMaeSesion);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public void borrar(MaeSesion oMaeSesion) throws Exception {
        
        try {
            ofDao.getMaeSesion().delete(oMaeSesion);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<MaeSesion> listarSesion(MaeSesion oMaeSesion) throws Exception {
        List<MaeSesion> oSesList = null;

        oSesList = ofDao.getMaeSesion().fetchAll(oMaeSesion);        
        return oSesList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
