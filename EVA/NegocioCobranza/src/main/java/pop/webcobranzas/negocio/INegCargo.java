/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import javax.ejb.Remote;
import pop.comun.dominio.MaeCargo;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegCargo {

    MaeCargo calcularCargoAtrasado(MaeCargo oMaeCargo) throws Exception;
}
