/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.webcobranzas.iface.IDashboardServ;
import pop.webcobranzas.negocio.INegDashboard;
import pop.webcobranzas.util.Utilidades;
import pop.comun.dominio.CobCronogramaMetaAvance;

/**
 *
 * @author Jhon Yovera
 */
public class DashboardServ  implements IDashboardServ, Serializable {
    
    INegDashboard iNegDashboardEJB;

    @Override
    public List<List<Object>> listarDeposito() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.listarDeposito();
    }

    @Override
    public List<List<Object>> listarDepositoMeta(CobCronogramaMetaAvance oCobCronogramaMetaAvance ) throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.listarDepositoMeta(oCobCronogramaMetaAvance);
    }
    
    @Override
    public Boolean cargarDatosFPH() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFPH();
    }

    @Override
    public Boolean cargarDatosFEM() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFEM();
    }

    @Override
    public Boolean cargarDatosFMY() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFMY();
    }

    @Override
    public Boolean cargarDatosFPO() throws Exception {
        iNegDashboardEJB = (INegDashboard) Utilidades.getEJBRemote("SessionDashboard", INegDashboard.class.getName());     
        return iNegDashboardEJB.cargarDatosFPO();
    }
    
    
    
    
}
