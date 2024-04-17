/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.msj;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface IEnvioMensajeTexto {
    
    List<MaeTelefono> insertar(List<MaeTelefono> oMaeTelefonos) throws Exception;
    
}
