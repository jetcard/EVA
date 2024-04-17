/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeDireccion;

/**
 *
 * @author Jyoverar
 */
public interface IMaeDireccionDao {

    Integer insert(MaeDireccion oMaeDireccion);

    boolean update(MaeDireccion oMaeDireccion);

    void delete(MaeDireccion oMaeDireccion);

    ArrayList<MaeDireccion> fetchAll(MaeDireccion oMaeDireccion);
}
