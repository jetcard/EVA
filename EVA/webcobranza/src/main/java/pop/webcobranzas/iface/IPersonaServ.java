/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaePersona;

/**
 *
 * @author Jyoverar
 */
public interface IPersonaServ {

    Integer insertar(MaePersona oMaePersona) throws Exception;

    boolean actualizar(MaePersona oMaePersona) throws Exception;

    void borrar(MaePersona oMaePersona) throws Exception;

    List<MaePersona> listarPersona(MaePersona oMaePersona) throws Exception;

    List<MaePersona> listarPersonaUlt() throws Exception;

    MaePersona buscarPerAsignada( int dia, String fondo, Number InversionID) throws Exception;
    
}
