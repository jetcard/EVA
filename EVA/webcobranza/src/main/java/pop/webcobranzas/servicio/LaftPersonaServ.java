/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.LaftPersona;
import pop.webcobranzas.iface.ILaftPersonaServ;
import pop.webcobranzas.negocio.INegLaftPersona;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class LaftPersonaServ implements ILaftPersonaServ, Serializable {

    INegLaftPersona iNegLaftPersona;
    
    @Override
    public Integer insert(LaftPersona oLaftPersona) throws Exception  {
        iNegLaftPersona = (INegLaftPersona) Utilidades.getEJBRemote("SessionLaftPersona", INegLaftPersona.class.getName());     
        return iNegLaftPersona.insert(oLaftPersona);
    }

    @Override
    public List<LaftPersona> listarLaftPersona(LaftPersona oLaftPersona) throws Exception {
        iNegLaftPersona = (INegLaftPersona) Utilidades.getEJBRemote("SessionLaftPersona", INegLaftPersona.class.getName());     
        return iNegLaftPersona.listarLaftPersona(oLaftPersona);
    }
    
}
