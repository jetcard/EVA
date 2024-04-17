/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaCabecera;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;
/**
 *
 * @author EC23329
 */
public interface ICobCronogramaMetaCabeceraServ {
    Integer insertar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception;

    void actualizar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception;
    
    Integer validarAprobacion(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception;
}
