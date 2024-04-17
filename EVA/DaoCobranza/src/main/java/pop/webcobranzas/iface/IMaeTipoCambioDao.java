/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeTipoCambio;

/**
 *
 * @author Jyoverar
 */
public interface IMaeTipoCambioDao {
    
    Integer insert(MaeTipoCambio oMaeTipoCambio);
    
    void update(MaeTipoCambio oMaeTipoCambio);
    
    void delete(MaeTipoCambio oMaeTipoCambio);
    
    ArrayList<MaeTipoCambio> fetchAll(MaeTipoCambio oMaeTipoCambio);
    
}
