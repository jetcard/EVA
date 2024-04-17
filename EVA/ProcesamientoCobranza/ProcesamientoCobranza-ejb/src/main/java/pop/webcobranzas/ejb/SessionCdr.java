/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobDevice;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.conn.ConexionMySql;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCdr;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCobCdr")
public class SessionCdr implements INegCdr{

    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public Integer insertar(CobCdr oCobCdr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(CobCdr oCobCdr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(CobCdr oCobCdr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCdr> listar(CobCdr oCobCdr) throws Exception {     
        List<CobCdr> list = null;
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        cn.setAutoCommit(false);
        list = ofDao.getCdr(cn).fetchAllOrav2(oCobCdr);
        cn.close();
        return list;
    }

    @Override
    public List<CobDevice> listarDispositivo(CobDevice oCobDevice) throws Exception {
        List<CobDevice> list = null;
      
        list =  ofDao.getCdr().fetchAllDevice(oCobDevice);
        return list;
    }

}
