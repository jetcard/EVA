/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ifacecorreo;

import java.util.List;
import pop.comun.dominio.MaeCorreo;

/**
 *
 * @author Jyoverar
 */
public interface ICorreoServ {
    
    List<MaeCorreo> enviar(List<MaeCorreo> oMaeCorreos) throws Exception;
    
}
