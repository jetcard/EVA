/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoCartas;

/**
 *
 * @author RC433838
 */
@Remote
public interface INegCobRequerimiento {

    String removerRequerimiento(CobRequerimientoCartas oRquerimiento) throws Exception; 
    
    String cambiaEstadoReq(CobRequerimientoCartas oRquerimiento) throws Exception;     
    
    String addRequerimiento(CobRequerimientoCriterios oCriterio) throws Exception;
    
    List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception;  
    
    List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRquerimiento) throws Exception;
    
    List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oCriterios) throws Exception;
    
    List<CobRequerimientoCriterios> findReqSugerido() throws Exception;
    
    List<MovimientoCartas> findCartas(MaeInversion oInversion) throws Exception;
       
    
}
