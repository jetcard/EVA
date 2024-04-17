/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import pop.comun.dominio.CobUsuario;

/**
 *
 * @author Jyoverar
 */
public interface INegUsuario {
    
    // validar usuario
    CobUsuario validarUsuario(CobUsuario obj) throws Exception;
    
}
