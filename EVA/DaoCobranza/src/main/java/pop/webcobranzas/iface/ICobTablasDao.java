/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobTablas;

/**
 *
 * @author Jyoverar
 */
public interface ICobTablasDao {

    Integer insert(CobTablas oCobTablas);

    void update(CobTablas oCobTablas);

    void delete(CobTablas oCobTablas);

    ArrayList<CobTablas> fetchAll(CobTablas oCobTablas);

}
