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
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaCabecera;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobCronogramaMetaCabecera;

/**
 *
 * @author EC23329
 */
@Stateless(mappedName = "CobCronogramaMetaCabrcera")
public class SessionCobCronogramaMetaCabecera implements INegCobCronogramaMetaCabecera{
    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public Integer insertar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception {
        Integer newID = 0;

        try {
            newID = ofDao.getCronogramaMetaCabecera().insert(oCobCronogramaMetaCabecera);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
        

    }

    @Override
    public void actualizar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception {
        
   //     logger.info("getCronogramaMetaCabecera: Inicio update");     
        ofDao.getCronogramaMetaCabecera().update(oCobCronogramaMetaCabecera);
     //   logger.info("getCronogramaMetaCabecera: Termino update");     
        return;
    }
    
    @Override
    public Integer validarAprobacion(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception {
        Integer newID = 0;

        try {
            newID = ofDao.getCronogramaMetaCabecera().validarAprobacion(oCobCronogramaMetaCabecera);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
        

    }
}
