/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeCronograma;

/**
 *
 * @author Jyoverar
 */
public interface ICronogramaServ {

    Integer insertar(MaeCronograma oMaeCronograma) throws Exception;

    void actualizar(MaeCronograma oMaeCronograma) throws Exception;

    void borrar(MaeCronograma oMaeCronograma) throws Exception;

    List<MaeCronograma> buscarCronograma(MaeCronograma oMaeCronograma) throws Exception;

}
