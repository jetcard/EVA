/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ifacerep;

import java.util.List;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.reporte.RepMetaRecaudo;

//import pop.comun.dominio.MaeReporte;
/**
 *
 * @author EC23329
 */
public interface IRepMetasCobranzasServ {
    
    //byte[] exportartRepMetasCobranzas(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalle) throws Exception;

    byte[] exportartRepMetasCobranzas(RepMetaCobranza oRepMetaCobranza) throws Exception ;

    byte[] exportarRepMetasRecaudo(RepMetaRecaudo oRepMetaRecaudo) throws Exception;
}
