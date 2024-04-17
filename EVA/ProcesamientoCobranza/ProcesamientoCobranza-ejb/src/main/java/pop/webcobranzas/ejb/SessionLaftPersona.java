/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.LaftPersona;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegLaftPersona;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbLaftPersona")
public class SessionLaftPersona implements INegLaftPersona {
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insert(LaftPersona oLaftPersona) throws Exception  {
        Integer newID = 0;
       
        try {
            newID = ofDao.getLaftPersonaDao().insert(oLaftPersona);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public List<LaftPersona> listarLaftPersona(LaftPersona oLaftPersona) throws Exception {
        List<LaftPersona> oPerList = null;
        
        oPerList = ofDao.getLaftPersonaDao().fetchAll(oLaftPersona);
        return oPerList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
