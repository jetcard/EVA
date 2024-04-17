/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegUbigeo;

/**
 *
 * @author Jhon Yovera
 */
@Stateless(mappedName = "ejbUbigeo")
public class SessionUbigeo implements INegUbigeo {

    FactoryDao ofDao = new FactoryDao();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<MaeUbigeo> listarProvincia(MaeUbigeo oMaeUbigeo) throws Exception {
        List<MaeUbigeo> oProvinciaList = null;
        
        oProvinciaList = ofDao.getMaeUbigeo().fetchProvince(oMaeUbigeo);
        return oProvinciaList;
    }

    @Override
    public List<MaeUbigeo> listarDistrito(MaeUbigeo oMaeUbigeo) throws Exception {
        List<MaeUbigeo> oDistritoList = null;
        
        oDistritoList = ofDao.getMaeUbigeo().fetchDistrict(oMaeUbigeo);
        return oDistritoList;
    }

    @Override
    public List<MaeUbigeo> buscarUbigeo(MaeUbigeo oMaeUbigeo) throws Exception {
        List<MaeUbigeo> oDistritoList = null;    
        
        oDistritoList = ofDao.getMaeUbigeo().fetchAll(oMaeUbigeo);                
        return oDistritoList;
    }
}
