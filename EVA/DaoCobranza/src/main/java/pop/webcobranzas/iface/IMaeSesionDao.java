/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeSesion;

/**
 *
 * @author Jyoverar
 */
public interface IMaeSesionDao {
    
    Integer insert(MaeSesion oMaeSesion);

    void update(MaeSesion oMaeSesion);

    void delete(MaeSesion oMaeSesion);

    ArrayList<MaeSesion> fetchAll(MaeSesion oMaeSesion);
    
}
