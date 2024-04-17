/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.util.ArrayList;
import pop.comun.dominio.MaeHipoteca;
import pop.webcobranzas.iface.IHipotecaServ;
import pop.webcobranzas.negocio.INegHipoteca;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class HipotecaServ implements IHipotecaServ{
    
    INegHipoteca iNegHipotecaEJB;

    @Override
    public Integer insertar(MaeHipoteca oMaeHipoteca) throws Exception {
        iNegHipotecaEJB = (INegHipoteca) Utilidades.getEJBRemote("SessionHipoteca", INegHipoteca.class.getName());
        return iNegHipotecaEJB.insertar(oMaeHipoteca);
    }

    @Override
    public void actualizar(MaeHipoteca oMaeHipoteca) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeHipoteca oMaeHipoteca) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeHipoteca> listar(MaeHipoteca oMaeHipoteca) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaeHipoteca listarHipoteca(MaeHipoteca oMaeHipoteca) throws Exception {
        iNegHipotecaEJB = (INegHipoteca) Utilidades.getEJBRemote("SessionHipoteca", INegHipoteca.class.getName());
        return iNegHipotecaEJB.listarHipoteca(oMaeHipoteca);
    }
    
}
