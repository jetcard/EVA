/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;

/**
 *
 * @author Jyoverar
 */
public interface IEmailServ {

    Integer insertar(MaeEmail oMaeEmail) throws Exception;

    void actualizar(MaeEmail oMaeEmail) throws Exception;

    void borrar(MaeEmail oMaeEmail) throws Exception;
    
     Integer actualizar2(MaeEmail oMaeEmail) throws Exception;

    List<MaeEmail> listarEmail(MaeEmail oMaeEmail) throws Exception;

    List<MaeEmail> listarEmail2(MaeEmail oMaeEmail,String idFondo) throws Exception;
      
    List<MaeEmail> listarEmailInver(MaeInversion oMaeInversion) throws Exception;

}
