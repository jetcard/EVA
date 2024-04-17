/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.LaftPersona;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegLaftPersona {

    Integer insert(LaftPersona oLaftPersona) throws Exception;

    List<LaftPersona> listarLaftPersona(LaftPersona oLaftPersona) throws Exception;
}
