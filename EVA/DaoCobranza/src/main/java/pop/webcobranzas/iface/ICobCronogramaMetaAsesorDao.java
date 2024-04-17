/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobCronogramaMetaAsesor;

/**
 *
 * @author HH38092
 */
public interface ICobCronogramaMetaAsesorDao {
    
    Integer insert(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor);

    void update(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor);
    
    void delete(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor);

    ArrayList<CobCronogramaMetaAsesor> fetchAll(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor);
    
    CobCronogramaMetaAsesor fetchCall(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor);
}