/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegEmail;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbEmail")
public class SessionEmail implements INegEmail {

    FactoryDao ofDao = new FactoryDao();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<MaeEmail> listarEmailInver(MaeInversion oMaeInversion) throws Exception {
        List<MaeEmail> oEmailList = null;
        
        oEmailList = ofDao.getEmail().fetchEmailInver(oMaeInversion);
        return oEmailList;
    }

    @Override
    public Integer insertar(MaeEmail oMaeEmail) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeEmail oMaeEmail) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeEmail oMaeEmail) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeEmail> listarEmail(MaeEmail oMaeEmail) throws Exception {
        List<MaeEmail> oEmailList = null;
        
        oEmailList = ofDao.getEmail().fetchAll(oMaeEmail);
        return oEmailList;
    }

    
       @Override
    public List<MaeEmail> listarEmail2(MaeEmail oMaeEmail,String idFondo) throws Exception {
        List<MaeEmail> oEmailList = null;
        
        oEmailList = ofDao.getEmail().fetchAll2(oMaeEmail,idFondo);
        return oEmailList;
    }

    @Override
    public Integer actualizar2(MaeEmail oMaeEmail) throws Exception {
        int rep=0;
        rep = ofDao.getEmail().update2(oMaeEmail);
        return rep;
        
    }
    
}
