/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeDeposito;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.servicio.DepositoServ;
//import pop.webcobranzas.util.SessionUtils;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class DepositoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MaeDeposito> listDepositos;
    private IDepositoServ depositoServ;
    private String name;
    private String message;
    
    
    /**
     * Creates a new instance of DemoBean
     */
    public DepositoBean() {
    }
    
    // actualiza los depositos
    public void actualizarDepositos(){
         try {
             System.out.println("["+SessionUtils.getUserName()+"] "+" DepositoBean - actualizarDepositos ");
            listDepositos = getDepositoServ().listarDepositosUlt();
            //System.out.println("pop.webcobranzas.bean.DemoBean.<init>()");
            //System.out.println("   Lista de Depositos = " + listDepositos.size());
        } catch (Exception e) {
            
        }
         
    }

    
    /**
     * @return the listDepositos
     */
    public List<MaeDeposito> getListDepositos() {
        return listDepositos;
    }

    /**
     * @param listDepositos the listDepositos to set
     */
    public void setListDepositos(List<MaeDeposito> listDepositos) {
        this.listDepositos = listDepositos;
    }

    /**
     * @return the depositoServ
     */
    public DepositoServ getDepositoServ() {
        return new DepositoServ();
    }

    /**
     * @param depositoServ the depositoServ to set
     */
    public void setDepositoServ(DepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return "";//SessionUtils.getUserName();
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
