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
import pop.comun.dominio.CobCronogramaMetaAvance;

/**
 *
 * @author EC23329
 */
public interface ICobCronogramaMetaAvanceServ {
    
    List<CobCronogramaMetaAvance> listarRepCronogramaMetaAvance(CobCronogramaMetaAvance oCobCronogramaMetaAvance) throws Exception;
}
