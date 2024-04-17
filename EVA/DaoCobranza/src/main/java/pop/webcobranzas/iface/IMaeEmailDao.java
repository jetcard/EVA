/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;

/**
 *
 * @author Jyoverar
 */
public interface IMaeEmailDao {

    Integer insert(MaeEmail oMaeEmail);

    boolean update(MaeEmail oMaeEmail);

    void delete(MaeEmail oMaeEmail);

    ArrayList<MaeEmail> fetchAll(MaeEmail oMaeEmail);

    ArrayList<MaeEmail> fetchEmailInver(MaeInversion oMaeInversion);
    
    Integer update2(MaeEmail oMaeEmail);
     
    ArrayList<MaeEmail> fetchAll2(MaeEmail oMaeEmail,String idFondo); 
}
