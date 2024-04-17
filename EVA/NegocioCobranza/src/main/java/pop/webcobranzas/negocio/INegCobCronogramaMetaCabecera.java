/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCronogramaMetaCabecera;

/**
 *
 * @author EC23329
 */
@Remote
public interface INegCobCronogramaMetaCabecera {
    Integer insertar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception;

    void actualizar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception;
   
    Integer validarAprobacion(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception;
}
