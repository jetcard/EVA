/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.TabTipoDocumentoEstado;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTabTipoDocumentoEstado;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbTipoDocumentoEstado")
public class SessionTipoDocumentoEstado implements INegTabTipoDocumentoEstado{

    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public List<TabTipoDocumentoEstado> listarTipoDocumentoEstado(TabTipoDocumentoEstado oTabTipoDocumentoEstado) throws Exception {
        List<TabTipoDocumentoEstado> oTipoDocEstList = null;
        
        oTipoDocEstList = ofDao.getTipoDocumentoEstado().fetchAll(oTabTipoDocumentoEstado);
        return oTipoDocEstList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
