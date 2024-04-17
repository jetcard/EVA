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
public interface IGenerarDemandaDao {
    
    List<LegGeneraDemanda> buscarDemandado(LegGeneraDemanda oLegGeneraDemanda);
    
    List<LegGeneraDemanda> generarDemanda(LegGeneraDemanda oLegGeneraDemanda);
    
    Integer insertHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda);
    
    Integer deleteHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda);
    
    List<LegGeneraDemanda> grillaHistoricoDemanda();
}