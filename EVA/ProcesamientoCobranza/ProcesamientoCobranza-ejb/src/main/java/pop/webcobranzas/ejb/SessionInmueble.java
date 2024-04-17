/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaePersonaInmueble;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegInmueble;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbInmueble")
public class SessionInmueble implements INegInmueble {

    FactoryDao ofDao = new FactoryDao();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Integer insertar(MaeInmueble oMaeInmueble) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(MaeInmueble oMaeInmueble) throws Exception {
        boolean rpta = false;

        try {
            rpta = ofDao.getInmueble().update(oMaeInmueble);
        } catch (Exception e) {            
            throw e;
        } 
        return rpta;
    }

    @Override
    public void borrar(MaeInmueble oMaeInmueble) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeInmueble> listarEmail(MaeInmueble oMaeInmueble) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaePersonaInmueble> listarPersonaInmueble(MaeInmueble oMaeInmueble) throws Exception {
        List<MaePersonaInmueble> oListMaePersInm;
       
        oListMaePersInm = ofDao.getInmueble().fetchPerson(oMaeInmueble);
        return oListMaePersInm;
    }
}
