/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobCompromiso;


/**
 *
 * @author Jyoverar
 */
public interface ICobCompromisoDao {
    
    Integer insert(CobCompromiso oCobCompromiso);

    void update(CobCompromiso oCobCompromiso);

    void delete(CobCompromiso oCobCompromiso);

    ArrayList<CobCompromiso> fetchAll(CobCompromiso oCobCompromiso);
    
    CobCompromiso fetchCall(CobCompromiso oCobCompromiso);
    
    ArrayList<CobCompromiso> fetchDesposit(CobCompromiso oCobCompromiso);
    
}
