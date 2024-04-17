/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.ArrayList;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeHipoteca;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegHipoteca;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbHipoteca")
public class SessionHipoteca implements INegHipoteca{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(MaeHipoteca oMaeHipoteca)  throws Exception{
         Integer newID = 0;

        try {
            // open conection
            //ofDao.getConexionDao().ConexionOpen();
            // execute command
            newID = ofDao.getHipoteca().insert(oMaeHipoteca);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public void actualizar(MaeHipoteca oMaeHipoteca)   throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeHipoteca oMaeHipoteca)  throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeHipoteca> listar(MaeHipoteca oMaeHipoteca)  throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaeHipoteca listarHipoteca(MaeHipoteca oMaeHipoteca)  throws Exception {
        MaeHipoteca maeHipoteca = null;
        
        maeHipoteca = ofDao.getHipoteca().fetch(oMaeHipoteca);
        return maeHipoteca;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
