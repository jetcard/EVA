/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.webcobranzas.iface.IEstadoCuentaServ;
import pop.webcobranzas.negocio.INegEstadoCuenta;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class EstadoCuentaServ implements IEstadoCuentaServ, Serializable {

    INegEstadoCuenta iNegEstadoCuenta;
    
    @Override
    public List<CobTchn> listarTchn(CobTchn oCobTchn) throws Exception {
        iNegEstadoCuenta = (INegEstadoCuenta) Utilidades.getEJBRemote("SessionEstadoCuenta", INegEstadoCuenta.class.getName());     
        return iNegEstadoCuenta.listarTchn(oCobTchn);
    }

    @Override
    public List<MaeEstadoCuenta> listarEstadoCuenta(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
         iNegEstadoCuenta = (INegEstadoCuenta) Utilidades.getEJBRemote("SessionEstadoCuenta", INegEstadoCuenta.class.getName());     
        return iNegEstadoCuenta.listarEstadoCuenta(oMaeEstadoCuenta);
    }

    @Override
    public List<MaeEstadoCuenta> listarEstadoCuentaB(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        iNegEstadoCuenta = (INegEstadoCuenta) Utilidades.getEJBRemote("SessionEstadoCuenta", INegEstadoCuenta.class.getName());     
        return iNegEstadoCuenta.listarEstadoCuentaB(oMaeEstadoCuenta);
    }

    @Override
    public List<MaeDeposito> listarEstadoCuentaC(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        iNegEstadoCuenta = (INegEstadoCuenta) Utilidades.getEJBRemote("SessionEstadoCuenta", INegEstadoCuenta.class.getName());     
        return iNegEstadoCuenta.listarEstadoCuentaC(oMaeEstadoCuenta);
    }

    @Override
    public List<MaeEstadoCuenta> listarEstadoCuentaD(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        iNegEstadoCuenta = (INegEstadoCuenta) Utilidades.getEJBRemote("SessionEstadoCuenta", INegEstadoCuenta.class.getName());     
        return iNegEstadoCuenta.listarEstadoCuentaD(oMaeEstadoCuenta);
    }
    
    
}
