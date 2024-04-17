/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.TabArchivo;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegArchivo;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "SessionArchivo")
public class SessionArchivo implements INegArchivo {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(TabArchivo oTabArchivo) throws Exception {
        Integer newID = 0;
        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getArchivo().insert(oTabArchivo);
        } catch (Exception e) {
            throw e;
        }
        return newID;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<TabArchivo> listar(TabArchivo oTabArchivo) throws Exception {
        List<TabArchivo> oArchivList = null;

        oArchivList = ofDao.getArchivo().fetchAll(oTabArchivo);
        return oArchivList;
    }
}
