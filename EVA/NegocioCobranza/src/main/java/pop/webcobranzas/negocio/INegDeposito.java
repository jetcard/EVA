/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeDeposito;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegDeposito {

    List<MaeDeposito> insertar(List<MaeDeposito> oMaeDeposito) throws Exception;

    List<MaeDeposito> listarDepositosUlt() throws Exception;

    List<MaeDeposito> listarDepositosBank(List<MaeDeposito> oMaeDeposito) throws Exception;

    List<MaeDeposito> listarDepositos(MaeDeposito oMaeDeposito) throws Exception;

    List<MaeDeposito> listarDepositosMes(MaeDeposito oMaeDeposito) throws Exception;
    
    List<MaeDeposito> listarDepositoCompromiso(MaeDeposito oMaeDeposito) throws Exception;
    
    Integer actualizarnoDepositos(MaeDeposito oNodeposito) throws Exception;
     
    Integer eliminarnoDepositos(MaeDeposito oNodeposito) throws Exception;     
     
    List<MaeDeposito> listarNoDeposito(MaeDeposito oNodeposito) throws Exception;
    
    
    List<MaeDeposito> fetchAllResumen(MaeDeposito oNodeposito) throws Exception;
    
    String grabarDepositosNI(MaeDeposito oNodeposito) throws Exception;

}
