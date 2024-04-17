/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import pop.comun.dominio.MaeCargo;

/**
 *
 * @author Jyoverar
 */
public interface ICargoServ {
     MaeCargo calcularCargoAtrasado(MaeCargo oMaeCargo) throws Exception ;
}
