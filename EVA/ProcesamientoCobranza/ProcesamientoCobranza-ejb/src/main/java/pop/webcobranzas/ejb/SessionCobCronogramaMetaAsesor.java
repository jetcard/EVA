/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCronogramaMetaAsesor;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobCronogramaMetaAsesor;

/**
 *
 * @author HH38092
 */
@Stateless(mappedName = "CobCronogramaMetaAsesor")
public class SessionCobCronogramaMetaAsesor  implements INegCobCronogramaMetaAsesor {
    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public Integer insertar(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCronogramaMetaAsesor> buscarCronogramaMetaAsesor(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception {
     //   logger.info("buscarCronogramaMetaResumen()");
        List<CobCronogramaMetaAsesor> oCobCronogramaMetaAsesorList = null;

        oCobCronogramaMetaAsesorList = ofDao.getCronogramaMetaAsesor().fetchAll(oCobCronogramaMetaAsesor);
        
        return oCobCronogramaMetaAsesorList;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}