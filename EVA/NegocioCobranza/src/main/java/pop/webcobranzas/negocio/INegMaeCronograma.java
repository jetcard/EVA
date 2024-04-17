/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeCronograma;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegMaeCronograma {

    Integer insertar(MaeCronograma oMaeCronograma) throws Exception;

    void actualizar(MaeCronograma oMaeCronograma) throws Exception;

    void borrar(MaeCronograma oMaeCronograma) throws Exception;

    List<MaeCronograma> buscarCronograma(MaeCronograma oMaeCronograma) throws Exception;
}
