/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pop.webcobranzas.iface.IDashboardServ;
import pop.webcobranzas.servicio.DashboardServ;

/**
 *
 * @author Jyoverar
 */
@Named
@SessionScoped 
public class CargaDatosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private IDashboardServ dashboardServ = new DashboardServ();
    private String message;

    public String actionFPH() throws Exception {
        System.out.println(" Incio cargarDatosFPH" + new Date());
        dashboardServ.cargarDatosFPH();
        System.out.println(" Fin cargarDatosFPH" + new Date());
        
        System.out.println(message);
        return "";
    }
    
    public String actionFEM() throws Exception {
        System.out.println(" Incio cargarDatosFEM" + new Date());
        dashboardServ.cargarDatosFEM();
        System.out.println(" Fin cargarDatosFEM" + new Date());
        System.out.println(message);
        return "";
    }
    
    public String actionFMY() throws Exception {
        System.out.println(" Incio cargarDatosFMY" + new Date());
        dashboardServ.cargarDatosFMY();
        System.out.println(" Fin cargarDatosFMY" + new Date());
        
        System.out.println(message);
        return "";
    }
    
    public String actionFPO() throws Exception {
        System.out.println(" Incio cargarDatosFPO" + new Date());
        dashboardServ.cargarDatosFPO();
        System.out.println(" Fin cargarDatosFPO" + new Date());
        
        System.out.println(message);
        return "";
    }
    
    public IDashboardServ getDashboardServ() {
        return dashboardServ;
    }
    
    public void setDashboardServ(IDashboardServ dashboardServ) {
        this.dashboardServ = dashboardServ;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
