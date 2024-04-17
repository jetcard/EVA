/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeCronograma;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegMaeCronograma;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCronograma")
public class SessionCronograma implements INegMaeCronograma {

    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public Integer insertar(MaeCronograma oMaeCronograma) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeCronograma oMaeCronograma) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeCronograma oMaeCronograma) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeCronograma> buscarCronograma(MaeCronograma oMaeCronograma) throws Exception {
        List<MaeCronograma> oMaeCronoList = null;

        oMaeCronoList = ofDao.getCronograma().fetchSchedule(oMaeCronograma);
        return oMaeCronoList;   
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
