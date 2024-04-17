/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.LegGeneraDemanda;

/**
 *
 * @author PR154357
 */
public interface IGenerarDemanda {
    
    List<LegGeneraDemanda> buscarDemandado(LegGeneraDemanda oLegGeneraDemanda) throws Exception;
    
    List<LegGeneraDemanda> generarDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception;
    
    Integer insertHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception;
    
    Integer deleteHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception;
    
    List<LegGeneraDemanda> grillaHistoricoDemanda() throws Exception;
}