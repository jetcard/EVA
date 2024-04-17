/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegBanco;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbBanco")
public class SessionBanco implements INegBanco{

     FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(MaeBanco oMaeBanco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeBanco oMaeBanco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeBanco oMaeBanco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeBanco> listarBanco(MaeBanco oMaeBanco) throws Exception {
        List<MaeBanco> oBancoList = null;
        
        oBancoList = ofDao.getBanco().fetchAll(oMaeBanco);
        return oBancoList;
    }

    @Override
    public List<MaeBancoCuenta> listarBancoCuenta(MaeBancoCuenta oMaeBancoCuenta) throws Exception {
        List<MaeBancoCuenta> oBancoCuenList = null;

        oBancoCuenList = ofDao.getBanco().fetchAllCuenta(oMaeBancoCuenta);
        return oBancoCuenList;
    }
    
}
