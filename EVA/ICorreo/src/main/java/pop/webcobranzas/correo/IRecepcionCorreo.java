/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.correo;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeCorreo;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface IRecepcionCorreo {

    int cantidadCorreos(String file) throws Exception;

    List<MaeCorreo> recibir(String file) throws Exception;

}
