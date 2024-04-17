/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCronogramaMetaAsesor;

/**
 *
 * @author HH38092
 */
@Remote
public interface INegCobCronogramaMetaAsesor {
    Integer insertar(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception;

    void actualizar(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception;

    void borrar(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception;

    List<CobCronogramaMetaAsesor> buscarCronogramaMetaAsesor(CobCronogramaMetaAsesor oCobCronogramaMetaAsesor) throws Exception;   
}
