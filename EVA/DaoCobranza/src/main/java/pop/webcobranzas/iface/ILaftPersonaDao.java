/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.LaftPersona;

/**
 *
 * @author Jyoverar
 */
public interface ILaftPersonaDao {
    Integer insert(LaftPersona oLaftPersona);

    void update(LaftPersona oLaftPersona);

    void delete(LaftPersona oLaftPersona);

    ArrayList<LaftPersona> fetchAll(LaftPersona oLaftPersona);
}
