/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeCuotaPago;
import pop.webcobranzas.iface.ICuotaPagoServ;
import pop.webcobranzas.negocio.INegCuotaPago;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CuotaPagoServ implements ICuotaPagoServ, Serializable {

    INegCuotaPago iNegCuotaPagoEJB;

    @Override
    public List<MaeCuotaPago> listarCuotaPago(MaeCuotaPago oMaeCuotaPago) throws Exception {
        iNegCuotaPagoEJB = (INegCuotaPago) Utilidades.getEJBRemote("SessionCuotaPago", INegCuotaPago.class.getName());
        return iNegCuotaPagoEJB.listarCuotaPago(oMaeCuotaPago);
    }

    @Override
    public MaeCuotaPago calcularCuotaPagoFuturo(MaeCuotaPago oMaeCuotaPago) throws Exception {
        iNegCuotaPagoEJB = (INegCuotaPago) Utilidades.getEJBRemote("SessionCuotaPago", INegCuotaPago.class.getName());
        return iNegCuotaPagoEJB.calcularCuotaPagoFuturo(oMaeCuotaPago);
    }
    
}
