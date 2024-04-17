/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.webcobranzas.filter.SessionUtils;

import pop.webcobranzas.iface.IDashboardServ;
import pop.webcobranzas.servicio.DashboardServ;

import pop.comun.dominio.CobCronogramaMetaAvance;
import pop.webcobranzas.iface.ICobCronogramaMetaAvanceServ;
import pop.webcobranzas.servicio.CobCronogramaMetaAvanceServ;

/**
 *
 * @author Jhon Yovera
 */
@Named
@ViewScoped 
public class DashboardBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String data0;
    private String dataA;
    private String dataB;
    private String dataC;
    private String dataD;
        
    private IDashboardServ dashboardServ= new DashboardServ();
    private IDashboardServ dashboardServMeta= new DashboardServ();

    public DashboardBean() {
        
    }

    public void cargarGrafico(){
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" DashboardBean - cargarGrafico ");
            List<List<Object>> list = getDashboardServ().listarDeposito();
            //System.out.println("pop.webcobranzas.bean.DashboardBean.cargarGrafico()");
            dataA=list.get(1).toString();
            dataB=list.get(2).toString();
            dataC=list.get(3).toString();
            dataD=list.get(4).toString();
            data0=list.get(0).toString();
        } catch (Exception e) {
        }
        
    }
    
    public void cargarGraficoMeta(CobCronogramaMetaAvance oCobCronogramaMetaAvance){
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" DashboardBean - cargarGrafico ");
            List<List<Object>> list = getDashboardServ().listarDepositoMeta(oCobCronogramaMetaAvance);
            //System.out.println("pop.webcobranzas.bean.DashboardBean.cargarGrafico()");
            dataA=list.get(1).toString();
            dataB=list.get(2).toString();
            //dataC=list.get(3).toString();
            //dataD=list.get(4).toString();
            data0=list.get(0).toString();
        } catch (Exception e) {
        }
        
    }
    
    public String getDataA() {
        return dataA;
    }

    public void setDataA(String dataA) {
        this.dataA = dataA;
    }

    public String getDataB() {
        return dataB;
    }

    public void setDataB(String dataB) {
        this.dataB = dataB;
    }

    public String getDataC() {
        return dataC;
    }

    public void setDataC(String dataC) {
        this.dataC = dataC;
    }

    public IDashboardServ getDashboardServ() {
        return dashboardServ;
    }

    public void setDashboardServ(IDashboardServ dashboardServ) {
        this.dashboardServ = dashboardServ;
    }

    public String getData0() {
        return data0;
    }

    public void setData0(String data0) {
        this.data0 = data0;
    }

    public String getDataD() {
        return dataD;
    }

    public void setDataD(String dataD) {
        this.dataD = dataD;
    }
    
    

}
