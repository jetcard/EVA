/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;


import java.util.List;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeReporte;
import pop.webcobranzas.ifacerep.IRepEstadoCuentaServ;
import pop.webcobranzas.procesos.IRepEstadoCuenta; 
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class RepEstadoCuentaServ implements IRepEstadoCuentaServ {

    IRepEstadoCuenta iRepEstadoCuentaEJB;

    @Override
    public byte[] imprimirEstadoCuenta(List<MaeEstadoCuenta> oMaeEstadoCuentas, MaeReporte maeReporte) throws Exception {
        iRepEstadoCuentaEJB = (IRepEstadoCuenta) Utilidades.getEJBRemoteRep("SessionEstadoCuentaRep", IRepEstadoCuenta.class.getName());
        return iRepEstadoCuentaEJB.imprimirEstadoCuenta(oMaeEstadoCuentas, maeReporte);
    }

    @Override
    public byte[] exportartEstadoCuenta(List<MaeEstadoCuenta> oMaeEstadoCuentas, MaeReporte maeReporte) throws Exception {
        iRepEstadoCuentaEJB = (IRepEstadoCuenta) Utilidades.getEJBRemoteRep("SessionEstadoCuentaRep", IRepEstadoCuenta.class.getName());
        return iRepEstadoCuentaEJB.exportartEstadoCuenta(oMaeEstadoCuentas, maeReporte);
    }

    

}
