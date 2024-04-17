/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobCronogramaMetaResumen;

/**
 *
 * @author HH38092
 */
public interface ICobCronogramaMetaResumenServ {
   
    Integer insertar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;

    void actualizar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;

    void borrar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;

    List<CobCronogramaMetaResumen> listarCronogramaMetaResumen(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;
    
    List<CobCronogramaMetaResumen> listarCronogramaMetaFondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;    
    
    List<CobCronogramaMetaResumen> listarCronogramaMetaFondoxAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;    
    
    List<CobCronogramaMetaResumen> listarCronogramaMetaFondoxAsesorJ(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;    
    
}
