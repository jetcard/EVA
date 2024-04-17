/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaePersonaInmueble;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegInmueble {

    Integer insertar(MaeInmueble oMaeInmueble) throws Exception;

    boolean actualizar(MaeInmueble oMaeInmueble) throws Exception;

    void borrar(MaeInmueble oMaeInmueble) throws Exception;

    List<MaeInmueble> listarEmail(MaeInmueble oMaeInmueble) throws Exception;

    List<MaePersonaInmueble> listarPersonaInmueble(MaeInmueble oMaeInmueble) throws Exception;

}
