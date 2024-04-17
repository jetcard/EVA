/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeCronograma;

/**
 *
 * @author Jyoverar
 */
public interface IMaeCronogramaDao {

    Integer insert(MaeCronograma oMaeCronograma);

    void update(MaeCronograma oMaeCronograma);

    void delete(MaeCronograma oMaeCronograma);

    ArrayList<MaeCronograma> fetchSchedule(MaeCronograma oMaeCronograma);
}
