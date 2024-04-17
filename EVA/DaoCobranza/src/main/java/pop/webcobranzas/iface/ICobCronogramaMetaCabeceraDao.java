/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobCronogramaMetaCabecera;

/**
 *
 * @author EC23329
 */
public interface ICobCronogramaMetaCabeceraDao {
    Integer insert(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera);

    void update(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera);
    
    Integer validarAprobacion(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera);
    
}
