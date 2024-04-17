/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author Jyoverar
 */
public interface IMaeTelefonoDao {

    Integer insert(MaeTelefono oMaeTelefono);
    Integer insertPresta(MaeTelefono oMaeTelefono) ;

    boolean update(MaeTelefono oMaeTelefono);
    
    boolean modificar(MaeTelefono oMaeTelefono);

    
    void delete(MaeTelefono oMaeTelefono);

    ArrayList<MaeTelefono> fetchAll(MaeTelefono oMaeTelefono);

    ArrayList<MaeTelefono> fetchPhoneInver(MaeInversion oMaeInversion);
    
    ArrayList<String> listPhone(int cMaeInversionId);
    
    ArrayList<MaeTelefono> listPhoneSendMsj(MaeInversion oMaeInversion);
    
}
