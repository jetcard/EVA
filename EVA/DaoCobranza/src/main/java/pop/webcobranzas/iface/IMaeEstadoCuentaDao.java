/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;

/**
 *
 * @author Jyoverar
 */
public interface IMaeEstadoCuentaDao {
    
    Integer insert(MaeEstadoCuenta oMaeEstadoCuenta);
    
    void update(MaeEstadoCuenta oMaeEstadoCuenta);
    
    void delete(MaeEstadoCuenta oMaeEstadoCuenta);
    
    ArrayList<MaeEstadoCuenta> fetchAll(MaeEstadoCuenta oMaeEstadoCuenta);
    
    ArrayList<MaeEstadoCuenta> fetchAllB(MaeEstadoCuenta oMaeEstadoCuenta);
    
    ArrayList<MaeDeposito> fetchAllC(MaeEstadoCuenta oMaeEstadoCuenta);
    
    ArrayList<MaeEstadoCuenta> fetchAllD(MaeEstadoCuenta oMaeEstadoCuenta);
    
    ArrayList<CobTchn> fetchAllTchn(CobTchn oCobTchn);
    
    
    
}
