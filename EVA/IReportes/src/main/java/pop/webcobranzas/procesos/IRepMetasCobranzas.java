/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.procesos;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.reporte.RepMetaRecaudo;
/**
 *
 * @author EC23329
 */
@Remote
public interface IRepMetasCobranzas {
    
    //byte[] exportartRepMetasCobranzas(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalle) throws Exception;
    byte[] exportartRepMetasCobranzas(RepMetaCobranza oRepMetaCobranza) throws Exception;
    
    byte[] exportartRepMetasRecaudo(RepMetaRecaudo oRepMetaRecaudo) throws Exception;
}
