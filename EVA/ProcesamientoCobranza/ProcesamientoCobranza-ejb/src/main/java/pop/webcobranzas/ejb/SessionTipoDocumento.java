/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.TabTipoDocumento;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTabTipoDocumento;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbTipoDocumento")
public class SessionTipoDocumento implements INegTabTipoDocumento{

     FactoryDao ofDao = new FactoryDao();
    
    @Override
    public List<TabTipoDocumento> listarTipoDocumento(TabTipoDocumento oTabTipoDocumento) throws Exception {
        List<TabTipoDocumento> oTipoDocList = null;
        
        oTipoDocList = ofDao.getTipoDocumento().fetchAll(oTabTipoDocumento);
        return oTipoDocList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
