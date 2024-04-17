/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeControl;
import pop.comun.dominio.MaeDeposito;

/**
 *
 * @author Jyoverar
 */
public interface IMaeDepositoDao {
    
    Integer insert(MaeDeposito oMaeDeposito);
    
    void update(MaeDeposito oMaeDeposito);
    
    void delete(MaeDeposito oMaeDeposito);
    
    ArrayList<MaeDeposito> fetchBank(MaeDeposito oMaeDeposito);
    
    ArrayList<MaeDeposito> fetchAll(MaeDeposito oMaeDeposito);
    
    ArrayList<MaeDeposito> fetchDashboard(MaeDeposito oMaeDeposito);
    
    ArrayList<MaeDeposito> fetchAllMonth(MaeDeposito oMaeDeposito);
    
    ArrayList<MaeControl> verifyDay(MaeControl oMaeControl);
    
    ArrayList<MaeDeposito> fetchCommitment(MaeDeposito oMaeDeposito);
    
     Integer actualizarnoDepositos(MaeDeposito noDeposito);

    Integer eliminarnoDepositos(MaeDeposito noDeposito);
    
    String grabarDepositosNI(MaeDeposito noDeposito);
    
    ArrayList<MaeDeposito> fetchAllListar(MaeDeposito noDeposito);
    
    ArrayList<MaeDeposito> fetchAllResumen(MaeDeposito noDeposito);
    
}
