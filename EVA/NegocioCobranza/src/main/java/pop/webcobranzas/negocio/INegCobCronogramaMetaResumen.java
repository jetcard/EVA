/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCronogramaMetaResumen;

/**
 *
 * @author HH38092
 */
@Remote
public interface INegCobCronogramaMetaResumen {
    Integer insertar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;

    void actualizar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;

    void borrar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;

    List<CobCronogramaMetaResumen> buscarCronogramaMetaResumen(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;   
    
    List<CobCronogramaMetaResumen> buscarCronogramaMetaResumenxFondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;       
    
    List<CobCronogramaMetaResumen> buscarCronogramaMetaResumenxFondoxAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;
    
    List<CobCronogramaMetaResumen> buscarCronogramaMetaResumenxFondoxAsesorJ(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception;
}
