/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobCronogramaMetaResumen;
/**
 *
 * @author HH38092
 */
public interface ICobCronogramaMetaResumenDao {
    Integer insert(CobCronogramaMetaResumen oCobCronogramaMetaResumen);

    void update(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
    void updatexCambioAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen);

    void delete(CobCronogramaMetaResumen oCobCronogramaMetaResumen);

    ArrayList<CobCronogramaMetaResumen> fetchAll(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
    ArrayList<CobCronogramaMetaResumen> fetchxFondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
    ArrayList<CobCronogramaMetaResumen> fetchxFondoxAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
    ArrayList<CobCronogramaMetaResumen> fetchxFondoxAsesorJ(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
    CobCronogramaMetaResumen fetchCall(CobCronogramaMetaResumen oCobCronogramaMetaResumen);
    
}
