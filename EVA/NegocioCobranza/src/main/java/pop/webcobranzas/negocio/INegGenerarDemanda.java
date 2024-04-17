/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.LegGeneraDemanda;

/**
 *
 * @author PR154357
 */
@Remote
public interface INegGenerarDemanda {
    
    List<LegGeneraDemanda> buscarDemandado(LegGeneraDemanda ooLegGeneraDemanda) throws Exception;
    
    List<LegGeneraDemanda> generarDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception;  
    
    Integer insertHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception;
    
    Integer deleteHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception;
    
    List<LegGeneraDemanda> grillaHistoricoDemanda() throws Exception;
}