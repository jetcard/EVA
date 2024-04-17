/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobCronogramaMetaResumen;

/**
 *
 * @author HH38092
 */
@Stateless(mappedName = "CobCronogramaMetaResumen")
public class SessionCobCronogramaMetaResumen  implements INegCobCronogramaMetaResumen {
    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public Integer insertar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCronogramaMetaResumen> buscarCronogramaMetaResumen(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
     //   logger.info("buscarCronogramaMetaResumen()");
        List<CobCronogramaMetaResumen> oCobCronogramaMetaResumenList = null;

        oCobCronogramaMetaResumenList = ofDao.getCronogramaMetaResumen().fetchAll(oCobCronogramaMetaResumen);
        
		return oCobCronogramaMetaResumenList;
    }
    
    @Override
    public List<CobCronogramaMetaResumen> buscarCronogramaMetaResumenxFondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
       // logger.info("buscarCronogramaMetaResumenxFondo()");
        List<CobCronogramaMetaResumen> oCobCronogramaMetaResumenList = null;

        oCobCronogramaMetaResumenList = ofDao.getCronogramaMetaResumen().fetchxFondo(oCobCronogramaMetaResumen);
        
        return oCobCronogramaMetaResumenList;
    }
    
    @Override
    public List<CobCronogramaMetaResumen> buscarCronogramaMetaResumenxFondoxAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        //logger.info("buscarCronogramaMetaResumenxFondoxAsesor()");
        List<CobCronogramaMetaResumen> oCobCronogramaMetaResumenList = null;

        oCobCronogramaMetaResumenList = ofDao.getCronogramaMetaResumen().fetchxFondoxAsesor(oCobCronogramaMetaResumen);

        return oCobCronogramaMetaResumenList;
    }     
    
    @Override
    public List<CobCronogramaMetaResumen> buscarCronogramaMetaResumenxFondoxAsesorJ(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        //logger.info("buscarCronogramaMetaResumenxFondoxAsesor()");
        List<CobCronogramaMetaResumen> oCobCronogramaMetaResumenList = null;

        oCobCronogramaMetaResumenList = ofDao.getCronogramaMetaResumen().fetchxFondoxAsesorJ(oCobCronogramaMetaResumen);

        return oCobCronogramaMetaResumenList;
    }     
}