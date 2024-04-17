/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.iface.IPersonaServ;
import pop.webcobranzas.negocio.INegPersona;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class PersonaServ implements IPersonaServ, Serializable {

    INegPersona iNegPersonaEJB;

    @Override
    public List<MaePersona> listarPersonaUlt() throws Exception {

        iNegPersonaEJB = (INegPersona) Utilidades.getEJBRemote("SessionPersona", INegPersona.class.getName());
        return iNegPersonaEJB.listarPersonaUlt();
    }

    @Override
    public Integer insertar(MaePersona oMaePersona) throws Exception {
        iNegPersonaEJB = (INegPersona) Utilidades.getEJBRemote("SessionPersona", INegPersona.class.getName());     
        return iNegPersonaEJB.insertar(oMaePersona);
    }

    @Override
    public boolean actualizar(MaePersona oMaePersona) throws Exception {
        iNegPersonaEJB = (INegPersona) Utilidades.getEJBRemote("SessionPersona", INegPersona.class.getName());     
        return iNegPersonaEJB.actualizar(oMaePersona);
    }

    @Override
    public void borrar(MaePersona oMaePersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaePersona> listarPersona(MaePersona oMaePersona) throws Exception {
        iNegPersonaEJB = (INegPersona) Utilidades.getEJBRemote("SessionPersona", INegPersona.class.getName());
        return iNegPersonaEJB.listarPersona(oMaePersona);
    }

    @Override
    public MaePersona buscarPerAsignada(int dia, String fondo, Number InversionID) throws Exception {
        iNegPersonaEJB = (INegPersona) Utilidades.getEJBRemote("SessionPersona", INegPersona.class.getName());
        return iNegPersonaEJB.buscarPerAsignada(dia, fondo, InversionID);
    }
    
    

}
