/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.LegGeneraDemanda;
import pop.webcobranzas.iface.IGenerarDemanda;
import pop.webcobranzas.negocio.INegGenerarDemanda;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author PR154357
 */
public class GenerarDemandaServ implements IGenerarDemanda, Serializable{
    
    INegGenerarDemanda iNegGenerarDemandaEJB;
    
    @Override
    public List<LegGeneraDemanda> buscarDemandado(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        iNegGenerarDemandaEJB = (INegGenerarDemanda) Utilidades.getEJBRemote("SessionGenerarDemanda", INegGenerarDemanda.class.getName());  
        return iNegGenerarDemandaEJB.buscarDemandado(oLegGeneraDemanda);
    }

    @Override
    public List<LegGeneraDemanda> generarDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        iNegGenerarDemandaEJB = (INegGenerarDemanda) Utilidades.getEJBRemote("SessionGenerarDemanda", INegGenerarDemanda.class.getName());  
        return iNegGenerarDemandaEJB.generarDemanda(oLegGeneraDemanda);
    }
    
    @Override
    public List<LegGeneraDemanda> grillaHistoricoDemanda() throws Exception {
        iNegGenerarDemandaEJB = (INegGenerarDemanda) Utilidades.getEJBRemote("SessionGenerarDemanda", INegGenerarDemanda.class.getName());  
        return iNegGenerarDemandaEJB.grillaHistoricoDemanda();
    }

    @Override
    public Integer insertHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        iNegGenerarDemandaEJB = (INegGenerarDemanda) Utilidades.getEJBRemote("SessionGenerarDemanda", INegGenerarDemanda.class.getName());  
        return iNegGenerarDemandaEJB.insertHistoricoDemanda(oLegGeneraDemanda);
    }

    @Override
    public Integer deleteHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        iNegGenerarDemandaEJB = (INegGenerarDemanda) Utilidades.getEJBRemote("SessionGenerarDemanda", INegGenerarDemanda.class.getName());  
        return iNegGenerarDemandaEJB.deleteHistoricoDemanda(oLegGeneraDemanda);
    }    
}