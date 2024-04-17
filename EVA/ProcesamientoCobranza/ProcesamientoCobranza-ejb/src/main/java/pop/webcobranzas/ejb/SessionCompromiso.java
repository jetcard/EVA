/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCompromiso;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobCompromiso;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCompromiso")
public class SessionCompromiso implements INegCobCompromiso {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(CobCompromiso oCobCompromiso) throws Exception {
        Integer newID = 0;
        
        try {
            newID = ofDao.getCobCompromiso().insert(oCobCompromiso);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public void borrar(CobCompromiso oCobCompromiso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCompromiso> listar(CobCompromiso oCobCompromiso) throws Exception {
        List<CobCompromiso> lstCobCompromisos = null;
        
        lstCobCompromisos = ofDao.getCobCompromiso().fetchAll(oCobCompromiso);
        return lstCobCompromisos;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void actualizar(CobCompromiso oCobCompromiso) throws Exception {
        
        try {
            ofDao.getCobCompromiso().update(oCobCompromiso);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<CobCompromiso> listarDeposito(CobCompromiso oCobCompromiso) throws Exception {
        List<CobCompromiso> lstCobCompromisos = null;
        
        lstCobCompromisos = ofDao.getCobCompromiso().fetchDesposit(oCobCompromiso);        
        return lstCobCompromisos;
    }
}
