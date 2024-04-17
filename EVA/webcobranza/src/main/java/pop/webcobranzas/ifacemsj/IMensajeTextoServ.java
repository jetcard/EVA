/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ifacemsj;

import java.util.List;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author Jyoverar
 */
public interface IMensajeTextoServ {
    
    List<MaeTelefono> insertar(List<MaeTelefono> oMaeTelefonos) throws Exception;
    
}
