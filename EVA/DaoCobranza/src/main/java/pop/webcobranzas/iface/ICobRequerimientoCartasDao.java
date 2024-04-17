/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoCartas;

/**
 *
 * @author RC433838
 */
public interface ICobRequerimientoCartasDao {
    
    String removerRequerimiento(CobRequerimientoCartas oRequerimiento); 

    String cambiaEstadoReq(CobRequerimientoCartas oRequerimiento); 
        
    String addRequerimiento(CobRequerimientoCriterios oCriterio); 

    List<MaeInversion> allInversiones(MaeInversion oInversion);
    
    List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRequerimiento);
    
    List<CobRequerimientoCriterios> findReqSugerido();
    
    List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oRequerimiento);
    
    List<MovimientoCartas> findCartas(MaeInversion oInversion);    
    
    
       
    
}
