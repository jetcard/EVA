/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeHipoteca;

/**
 *
 * @author Jyoverar
 */
public interface IMaeHipotecaDao {
    
    Integer insert(MaeHipoteca oMaeHipoteca);
    
    void update(MaeHipoteca oMaeHipoteca);
    
    void delete(MaeHipoteca oMaeHipoteca);
    
    ArrayList<MaeHipoteca> fetchAll(MaeHipoteca oMaeHipoteca);
    
    MaeHipoteca fetch(MaeHipoteca oMaeHipoteca);

}
