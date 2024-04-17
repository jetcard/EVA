/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobLlamadas;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCobLlamada;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbLlamada")
public class SessionLlamada implements INegCobLlamada {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(CobLlamadas oCobLlamadas) throws Exception {
        Integer newID = 0;

        try {           
            newID = ofDao.getCobLlamada().insert(oCobLlamadas);
        } catch (Exception e) {
            throw e;
        } 

        return newID;
    }

    @Override
    public void borrar(CobLlamadas oCobLlamadas) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobLlamadas> listar(CobLlamadas oCobLlamadas) throws Exception {
        List<CobLlamadas> lstCobllamadas = null;
        
        lstCobllamadas = ofDao.getCobLlamada().fetchAll(oCobLlamadas);
        return lstCobllamadas;
    }
    
    @Override
    public List<CobLlamadas> listarResumenLlamada(CobLlamadas oCobLlamadas) throws Exception {
        List<CobLlamadas> lstCobllamadas = null;
        
        lstCobllamadas = ofDao.getCobLlamada().findAbstractCall(oCobLlamadas);
        return lstCobllamadas;
    }    

    @Override
    public List<CobLlamadas> listarTele(CobCdr oCobCdr) throws Exception {
        List<CobLlamadas> lstCobllamadas = null;
        
        lstCobllamadas = ofDao.getCobLlamada().fetchAllPhone(oCobCdr);
        return lstCobllamadas;
    }

}
