/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobLlamadas;
import pop.webcobranzas.iface.ILlamadaServ;
import pop.webcobranzas.negocio.INegCobLlamada;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class LlamadaServ implements ILlamadaServ, Serializable {

    INegCobLlamada iNegLlamadaEJB;

    @Override
    public Integer insertar(CobLlamadas oCobLlamadas) throws Exception {
        iNegLlamadaEJB = (INegCobLlamada) Utilidades.getEJBRemote("SessionLlamada", INegCobLlamada.class.getName());     
        return iNegLlamadaEJB.insertar(oCobLlamadas);
    }

    @Override
    public void borrar(CobLlamadas oCobLlamadas) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobLlamadas> listar(CobLlamadas oCobLlamadas) throws Exception {
        iNegLlamadaEJB = (INegCobLlamada) Utilidades.getEJBRemote("SessionLlamada", INegCobLlamada.class.getName());     
        return iNegLlamadaEJB.listar(oCobLlamadas);
    }
    
    @Override
    public List<CobLlamadas> listarResumen(CobLlamadas oCobLlamadas) throws Exception {
        iNegLlamadaEJB = (INegCobLlamada) Utilidades.getEJBRemote("SessionLlamada", INegCobLlamada.class.getName());     
        return iNegLlamadaEJB.listarResumenLlamada(oCobLlamadas);
    }    

    @Override
    public List<CobLlamadas> listarTele(CobCdr oCobCdr) throws Exception {
        iNegLlamadaEJB = (INegCobLlamada) Utilidades.getEJBRemote("SessionLlamada", INegCobLlamada.class.getName());     
        return iNegLlamadaEJB.listarTele(oCobCdr);
    }
    
    
    
}
