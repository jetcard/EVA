/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.iface.IEmailServ;
import pop.webcobranzas.negocio.INegEmail;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class EmailServ implements IEmailServ, Serializable {

    INegEmail iNegEmailEJB;

    @Override
    public List<MaeEmail> listarEmailInver(MaeInversion oMaeInversion) throws Exception {
        iNegEmailEJB = (INegEmail) Utilidades.getEJBRemote("SessionEmail", INegEmail.class.getName());
        return iNegEmailEJB.listarEmailInver(oMaeInversion);
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
        iNegEmailEJB = (INegEmail) Utilidades.getEJBRemote("SessionEmail", INegEmail.class.getName());
        return iNegEmailEJB.listarEmail(oMaeEmail);
    }
    
    @Override
    public List<MaeEmail> listarEmail2(MaeEmail oMaeEmail,String idFondo) throws Exception {
        iNegEmailEJB = (INegEmail) Utilidades.getEJBRemote("SessionEmail", INegEmail.class.getName());
        return iNegEmailEJB.listarEmail2(oMaeEmail, idFondo);
    }

    @Override
    public Integer actualizar2(MaeEmail oMaeEmail) throws Exception {
        iNegEmailEJB = (INegEmail) Utilidades.getEJBRemote("SessionEmail", INegEmail.class.getName());
        return iNegEmailEJB.actualizar2(oMaeEmail);
    }
    
}
