/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import pop.comun.dominio.CobSeguimiento;

/**
 *
 * @author Jyoverar
 */
public interface ICobSeguimientoServ {

    Integer insertar(CobSeguimiento oCobSeguimiento) throws Exception;

    CobSeguimiento buscar(CobSeguimiento oCobSeguimiento) throws Exception;
}
