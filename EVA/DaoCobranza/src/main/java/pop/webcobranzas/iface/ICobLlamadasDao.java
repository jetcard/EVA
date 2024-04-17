/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobLlamadas;

/**
 *
 * @author Jyoverar
 */
public interface ICobLlamadasDao {
    
    Integer insert(CobLlamadas oCobLlamadas);

    void update(CobLlamadas oCobLlamadas);

    void delete(CobLlamadas oCobLlamadas);

    ArrayList<CobLlamadas> fetchAll(CobLlamadas oCobLlamadas);
    
    ArrayList<CobLlamadas> findAbstractCall(CobLlamadas oCobLlamadas);
    
    ArrayList<CobLlamadas> fetchAllPhone(CobCdr oCobCdr);
    
}
