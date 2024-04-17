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
public interface IRequerimientoServ {
    
    String removerRequerimiento(CobRequerimientoCartas oRequerimiento) throws Exception;     
    
    String cambiaEstadoReq(CobRequerimientoCartas oRequerimiento) throws Exception;         
    
    String addRequerimiento(CobRequerimientoCriterios oCriterio) throws Exception; 
    
    List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception;    
    
    List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRequerimiento) throws Exception;   
    
    List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oCriterio) throws Exception;       
    
    List<CobRequerimientoCriterios> findReqSugerido() throws Exception;     
    
    List<MovimientoCartas> findCartas(MaeInversion oInversion) throws Exception;     
    
    
        
    
}
