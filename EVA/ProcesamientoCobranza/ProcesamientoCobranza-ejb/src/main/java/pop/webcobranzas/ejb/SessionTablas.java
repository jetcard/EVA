/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobTablas;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTablas;


/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbTablas")
public class SessionTablas implements INegTablas{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception{
        List<CobTablas> oTablasList;
       
        oTablasList = ofDao.getCobTablas().fetchAll(oCobTablas);
        return oTablasList;
    }
}
