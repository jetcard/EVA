/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
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
public interface ICobCronogramaMetaDetalleServ {
    Integer insertar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;

    void actualizar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;
            
    void actualizarCambiarAsesor(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList) throws Exception;

    void borrar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;

    List<CobCronogramaMetaDetalle> listarCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;
    
    List<CobCronogramaMetaAgrupxFecha> listarCronogramaMetaAgrupxFecha(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;
    
    List<CobCronogramaMetaDetalle> listarRepCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;
    
    RepMetaCobranza reporteMetaCobranza (CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception;
    
    RepMetaRecaudo reporteMetaRecaudo (CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle) throws Exception;
    
    List<CobCronogramaRecaudoResumen> listarRepCronogramaRecaudoResumen(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen) throws Exception;
}
