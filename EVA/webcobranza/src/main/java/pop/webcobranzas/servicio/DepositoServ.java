/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import pop.webcobranzas.iface.IDepositoServ;
import java.util.List;
import pop.comun.dominio.MaeDeposito;
import pop.webcobranzas.negocio.INegDeposito;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class DepositoServ implements IDepositoServ, Serializable {

    INegDeposito iNegDepositoEJB;

    @Override
    public List<MaeDeposito> listarDepositosUlt() throws Exception {

        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());
        return iNegDepositoEJB.listarDepositosUlt();
    }

    @Override
    public List<MaeDeposito> listarDepositos(MaeDeposito oMaeDeposito) throws Exception {
        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());
        return iNegDepositoEJB.listarDepositos(oMaeDeposito);
    }

    @Override
    public List<MaeDeposito> listarDepositosMes(MaeDeposito oMaeDeposito) throws Exception {
        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());
        return iNegDepositoEJB.listarDepositosMes(oMaeDeposito);
    }

    @Override
    public List<MaeDeposito> listarDepositosBank(List<MaeDeposito> oMaeDeposito) throws Exception {
        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());
        return iNegDepositoEJB.listarDepositosBank(oMaeDeposito);
    }

    @Override
    public List<MaeDeposito> insertar(List<MaeDeposito> oMaeDeposito) throws Exception {
        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());
        return iNegDepositoEJB.insertar(oMaeDeposito);
    }

    @Override
    public List<MaeDeposito> listarDepositoCompromiso(MaeDeposito oMaeDeposito) throws Exception {
         iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());
        return iNegDepositoEJB.listarDepositoCompromiso(oMaeDeposito);
    }

     public Integer actualizarnoDepositos(MaeDeposito oNodeposito) throws Exception {
          iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());     
        return iNegDepositoEJB.actualizarnoDepositos(oNodeposito);
    }

    @Override
    public Integer eliminarnoDepositos(MaeDeposito oNodeposito) throws Exception {
       iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());     
        return iNegDepositoEJB.eliminarnoDepositos(oNodeposito);
    }

    @Override
    public List<MaeDeposito> listarNoDeposito(MaeDeposito oNodeposito) throws Exception {
        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());     
        return iNegDepositoEJB.listarNoDeposito(oNodeposito);
    }

    @Override
    public String grabarDepositosNI(MaeDeposito oNodeposito) throws Exception {
       iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());     
        return iNegDepositoEJB.grabarDepositosNI(oNodeposito);
    }

    @Override
    public List<MaeDeposito> fetchAllResumen(MaeDeposito oNodeposito) throws Exception {
        iNegDepositoEJB = (INegDeposito) Utilidades.getEJBRemote("SessionDeposito", INegDeposito.class.getName());     
        return iNegDepositoEJB.fetchAllResumen(oNodeposito);
        
    }

 

 
}
