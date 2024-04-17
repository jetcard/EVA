/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import javax.ejb.Remote;
import pop.comun.dominio.CobSeguimiento;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegCobSeguimiento {

    Integer insertar(CobSeguimiento oCobSeguimiento) throws Exception;

    CobSeguimiento buscar(CobSeguimiento oCobSeguimiento) throws Exception;
}
