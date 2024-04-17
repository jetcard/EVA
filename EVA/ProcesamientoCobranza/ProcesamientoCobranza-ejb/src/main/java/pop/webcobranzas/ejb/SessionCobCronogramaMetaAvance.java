/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCronogramaMetaAvance;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobCronogramaMetaAvance;

/**
 *
 * @author EC23329
 */
@Stateless(mappedName = "CobCronogramaMetaAvance")
public class SessionCobCronogramaMetaAvance implements INegCobCronogramaMetaAvance{
    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public List<CobCronogramaMetaAvance> buscarCobCronogramaMetaAvance(CobCronogramaMetaAvance oCobCronogramaMetaAvance) throws Exception {
         List<CobCronogramaMetaAvance> oCobCronogramaMetaAvanceList = null;

        oCobCronogramaMetaAvanceList = ofDao.getCronogramaMetaAvance().fetchAll(oCobCronogramaMetaAvance);
        
        return oCobCronogramaMetaAvanceList;
    }
}
