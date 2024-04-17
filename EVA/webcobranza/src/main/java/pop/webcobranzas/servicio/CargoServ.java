/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import pop.comun.dominio.MaeCargo;
import pop.webcobranzas.iface.ICargoServ;
import pop.webcobranzas.negocio.INegCargo;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CargoServ implements ICargoServ, Serializable{
    
    INegCargo iNegCargoEJB;

    @Override
    public MaeCargo calcularCargoAtrasado(MaeCargo oMaeCargo) throws Exception {
        iNegCargoEJB = (INegCargo) Utilidades.getEJBRemote("SessionCargo", INegCargo.class.getName());
        return iNegCargoEJB.calcularCargoAtrasado(oMaeCargo);
    }
    
}
