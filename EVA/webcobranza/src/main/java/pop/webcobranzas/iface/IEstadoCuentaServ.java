/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;

/**
 *
 * @author Jyoverar
 */
public interface IEstadoCuentaServ {

    List<CobTchn> listarTchn(CobTchn oCobTchn) throws Exception;

    List<MaeEstadoCuenta> listarEstadoCuenta(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception;

    List<MaeEstadoCuenta> listarEstadoCuentaB(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception;

    List<MaeDeposito> listarEstadoCuentaC(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception;
    
    List<MaeEstadoCuenta> listarEstadoCuentaD(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception;
}
