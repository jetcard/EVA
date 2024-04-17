/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;
import pop.comun.dominio.reporte.RepMetaRecaudo;
import pop.comun.dominio.CobCronogramaRecaudoResumen;
/**
 *
 * @author HH38092
 */
@Remote
public interface INegCobCronogramaMetaDetalle {
   Integer insertar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;

    void actualizar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;
    
    void actualizarCambiarAsesor(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList) throws Exception;

    void borrar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;

    List<CobCronogramaMetaDetalle> buscarCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;       
    
    List<CobCronogramaMetaAgrupxFecha> buscarCronogramaMetaAgrupxFecha(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;           
    
    List<CobCronogramaMetaDetalle> buscarRepCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;       
    
    RepMetaCobranza buscarReporteMetaCobranza(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception; 
    
    RepMetaRecaudo buscarReporteMetaRecaudo(CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle) throws Exception; 
    
    List<CobCronogramaRecaudoResumen> buscarRepCronogramaRecaudoResumen(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen) throws Exception;       
}
