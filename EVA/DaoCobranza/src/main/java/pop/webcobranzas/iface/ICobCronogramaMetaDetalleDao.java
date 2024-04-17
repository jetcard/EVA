/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import java.util.List;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.reporte.RepMetaRecaudo;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;
import pop.comun.dominio.CobCronogramaRecaudoResumen;
/**
 *
 * @author HH38092
 */
public interface ICobCronogramaMetaDetalleDao {
    Integer insert(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);

    void update(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);
    
    void updateList(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList);

    void delete(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);

    ArrayList<CobCronogramaMetaDetalle> fetchAll(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);
    
    ArrayList<CobCronogramaMetaAgrupxFecha> fetchAgrupFecha(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
    CobCronogramaMetaDetalle fetchCall(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);    
    
    ArrayList<CobCronogramaMetaDetalle> fetchRepAll(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);
    
    RepMetaCobranza buscarReporteMetaCobranza(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle);
    
    RepMetaRecaudo buscarReporteMetaRecaudo(CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle);
    
    ArrayList<CobCronogramaRecaudoResumen> fetchRecAll(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen);
}
