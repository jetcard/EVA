/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeDireccion;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegDireccion;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbDireccion")
public class SessionDireccion implements INegDireccion {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(MaeDireccion oMaeDireccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeDireccion oMaeDireccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeDireccion oMaeDireccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeDireccion> listarDireccion(MaeDireccion oMaeDireccion) throws Exception {
        List<MaeDireccion> oDirecList = null;
       
        oDirecList = ofDao.getDireccion().fetchAll(oMaeDireccion);
        return oDirecList;
    }

}
