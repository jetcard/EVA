/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeMenu;

/**
 *
 * @author Jhon
 */
@Named
@ApplicationScoped
public class MenuBean  implements Serializable{
    static final long serialVersionUID = 1L;
    

    private List<MaeMenu> oMenuList = new ArrayList<>();
    
    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
        
        System.out.println("pop.webcobranzas.bean.MenuBean.<init>()");
        
        MaeMenu oMenuSistema = new MaeMenu();       
        
        oMenuSistema.setSimbolo("th-large");
        oMenuSistema.setMenuA("Sistema");
        oMenuSistema.setMenuAUrl("#");
        //oMenuSistema.setMenuAE("active");
        oMenuSistema.setMenuAE("none");
        
        List<MaeMenu> oMenuListSistema = new ArrayList<>();
        //--
        MaeMenu oMenuA1 = new MaeMenu();
        oMenuA1.setMenuA("Dashboard");
        oMenuA1.setMenuAUrl("/dashboard.xhtml");
        //oMenuA1.setMenuAE("active");
        oMenuA1.setMenuAE("none");
        oMenuListSistema.add(oMenuA1);
        //--
        MaeMenu oMenuA2 = new MaeMenu();
        oMenuA2.setMenuA("Administrar");
        oMenuA2.setMenuAUrl("dashboard.xhtml");
        oMenuA2.setMenuAE("none");
        //--
        //oMenuListSistema.add(oMenuA2);
        //--
        MaeMenu oMenuA3 = new MaeMenu();
        oMenuA3.setMenuA("Cargar Datos");
        oMenuA3.setMenuAUrl("cargaDatos.xhtml");
        oMenuA3.setMenuAE("none");
        //--
        //oMenuListSistema.add(oMenuA3);
        
        //--
        MaeMenu oMenuA4 = new MaeMenu();
        oMenuA4.setMenuA("Cargar Datos");
        oMenuA4.setMenuAUrl("/pages/sistema/cargaDatos.xhtml");
        oMenuA4.setMenuAE("none");
        //--
        oMenuListSistema.add(oMenuA4);
        
        // mensajes
        MaeMenu oMenuA5 = new MaeMenu();
        oMenuA5.setMenuA("Mensajes de Texto");
        oMenuA5.setMenuAUrl("/pages/sistema/mensajes.xhtml");
        oMenuA5.setMenuAE("none");
        //--
        oMenuListSistema.add(oMenuA5);
        
        // correos
        MaeMenu oMenuA6 = new MaeMenu();
        oMenuA6.setMenuA("Correos");
        oMenuA6.setMenuAUrl("/pages/sistema/correos.xhtml");
        oMenuA6.setMenuAE("none");
        //--
        oMenuListSistema.add(oMenuA6);
        
        // correos
        MaeMenu oMenuA7 = new MaeMenu();
        oMenuA7.setMenuA("Inbox");
        oMenuA7.setMenuAUrl("/pages/sistema/inbox.xhtml");
        oMenuA7.setMenuAE("none");
        //--
        oMenuListSistema.add(oMenuA7);
        
        // cartas
        MaeMenu oMenuA8 = new MaeMenu();
        oMenuA8.setMenuA("cartas");
        oMenuA8.setMenuAUrl("/pages/util/carta.xhtml");
        oMenuA8.setMenuAE("none");
        //--
        oMenuListSistema.add(oMenuA8);
        
        oMenuSistema.setoMenuList(oMenuListSistema);
        
        //---================================== Reporte
        MaeMenu oMenuReporte = new MaeMenu();  
        oMenuReporte.setSimbolo("desktop");
        oMenuReporte.setMenuA("Reporte");
        oMenuReporte.setMenuAUrl("#");
        oMenuReporte.setMenuAE("none");
        List<MaeMenu> oMenuListReporte = new ArrayList<>();
        //--
        //--
        MaeMenu oMenuB1 = new MaeMenu();
        oMenuB1.setMenuA("Reporte Operaciones");
        oMenuB1.setMenuAUrl("/pages/laft/reporte/reporteOperacion.xhtml");
        oMenuB1.setMenuAE("none");
        oMenuListReporte.add(oMenuB1);
        //--
        MaeMenu oMenuB2 = new MaeMenu();
        oMenuB2.setMenuA("Reporte Mensual");
        oMenuB2.setMenuAUrl("/pages/laft/reporte/reporteOperacionMes.xhtml");
        oMenuB2.setMenuAE("none");
        //--
        oMenuListReporte.add(oMenuB2);
        //--
        MaeMenu oMenuB3 = new MaeMenu();
        oMenuB3.setMenuA("Reporte Seguimiento");
        oMenuB3.setMenuAUrl("/pages/reporte/reporteSeguimientos.xhtml");
        oMenuB3.setMenuAE("none");
        //--
        oMenuListReporte.add(oMenuB3);
        //--
        MaeMenu oMenuB4 = new MaeMenu();
        oMenuB4.setMenuA("Reporte Llamadas");
        oMenuB4.setMenuAUrl("/pages/reporte/reporteLlamadas.xhtml");
        oMenuB4.setMenuAE("none");
//        //--
        oMenuListReporte.add(oMenuB4);
//        //--
//        MaeMenu oMenuB5 = new MaeMenu();
//        oMenuB5.setMenuA("Reporte Detalle Seguimiento");
//        oMenuB5.setMenuAUrl("/pages/reporte/reporteDetSeguimientos.xhtml");
//        oMenuB5.setMenuAE("none");
//        //--
//        oMenuListReporte.add(oMenuB5);
        //--
        MaeMenu oMenuB6 = new MaeMenu();
        oMenuB6.setMenuA("Reporte Detalle Compromisos");
        oMenuB6.setMenuAUrl("/pages/reporte/reporteCompromisos.xhtml");
        oMenuB6.setMenuAE("none");
        //--
        oMenuListReporte.add(oMenuB6);
        
        MaeMenu oMenuB7 = new MaeMenu();
        oMenuB7.setMenuA("Reporte por fecha vencimiento Cronograma");
        oMenuB7.setMenuAUrl("/pages/reporte/reporteVencimiento.xhtml");
        oMenuB7.setMenuAE("none");
        //--
        oMenuListReporte.add(oMenuB7);
        
        oMenuReporte.setoMenuList(oMenuListReporte);
        //--================================== consulas
         MaeMenu oMenuConsulta = new MaeMenu();  
        oMenuConsulta.setSimbolo("desktop");
        oMenuConsulta.setMenuA("Consulta");
        oMenuConsulta.setMenuAUrl("#");
        oMenuConsulta.setMenuAE("none");
        List<MaeMenu> oMenuListConsulta= new ArrayList<>();
        
        MaeMenu oMenuT2 = new MaeMenu();
        oMenuT2.setMenuA("Reporte Operaciones");
        oMenuT2.setMenuAUrl("/pages/consulta/ConsultaporDireccion.xhtml");
        oMenuT2.setMenuAE("none");
        oMenuListConsulta.add(oMenuT2);
        oMenuConsulta.setoMenuList(oMenuListConsulta);
        
        //---================================== Personas
        MaeMenu oMenuPersonas = new MaeMenu();  
        oMenuPersonas.setSimbolo("user");
        oMenuPersonas.setMenuA("Personas");
        oMenuPersonas.setMenuAUrl("#");
        oMenuPersonas.setMenuAE("none");
        List<MaeMenu> oMenuListPersonas = new ArrayList<>();
        //--
        MaeMenu oMenuC1 = new MaeMenu();
        oMenuC1.setMenuA("Reportadas");
        oMenuC1.setMenuAUrl("/pages/laft/persona/personasReportadas.xhtml");
        oMenuC1.setMenuAE("none");
        oMenuListPersonas.add(oMenuC1);
        //--
        MaeMenu oMenuC2 = new MaeMenu();
        oMenuC2.setMenuA("Registar");
        oMenuC2.setMenuAUrl("/pages/laft/persona/registrarPerReportada.xhtml");
        oMenuC2.setMenuAE("none");
        //--
        oMenuListPersonas.add(oMenuC2);
        
        oMenuPersonas.setoMenuList(oMenuListPersonas);    
        
        //---================================== Cobranza
        MaeMenu oMenuCobranza = new MaeMenu();  
        oMenuCobranza.setSimbolo("gavel");
        oMenuCobranza.setMenuA("Cobranza");
        oMenuCobranza.setMenuAUrl("#");
        oMenuCobranza.setMenuAE("none");
        List<MaeMenu> oMenuListCobranza = new ArrayList<>();
        //--
        MaeMenu oMenuD1 = new MaeMenu();
        oMenuD1.setMenuA("Deudores");
        oMenuD1.setMenuAUrl("/pages/cobranza/clienteDeudor.xhtml");
        oMenuD1.setMenuAE("none");
        oMenuListCobranza.add(oMenuD1);
        
        MaeMenu oMenuD2 = new MaeMenu();
        oMenuD2.setMenuA("Estado Cuenta");
        oMenuD2.setMenuAUrl("/pages/cobranza/estadoCuenta.xhtml");
        oMenuD2.setMenuAE("none");
        //--
        oMenuListCobranza.add(oMenuD2);
        
        MaeMenu oMenuD3 = new MaeMenu();
        oMenuD3.setMenuA("Propietarios");
        oMenuD3.setMenuAUrl("/pages/cobranza/clienteBusqueda.xhtml");
        oMenuD3.setMenuAE("none");
        //--
        oMenuListCobranza.add(oMenuD3);
        
        MaeMenu oMenuD4 = new MaeMenu();
        oMenuD4.setMenuA("Saldo Deudor");
        oMenuD4.setMenuAUrl("/pages/cobranza/saldoDeudor.xhtml");
        oMenuD4.setMenuAE("none");
        //--
        oMenuListCobranza.add(oMenuD4);
        
        oMenuCobranza.setoMenuList(oMenuListCobranza);    
                
        //---================================== Banco
        MaeMenu oMenuBanco = new MaeMenu();  
        oMenuBanco.setSimbolo("bank");
        oMenuBanco.setMenuA("Caja/Banco");
        oMenuBanco.setMenuAUrl("#");
        oMenuBanco.setMenuAE("none");
        List<MaeMenu> oMenuListBanco = new ArrayList<>();
        //--
        MaeMenu oMenuE1 = new MaeMenu();
        oMenuE1.setMenuA("Deposito Banco");
        oMenuE1.setMenuAUrl("/pages/caja/Deposito/banco.xhtml");
        oMenuE1.setMenuAE("none");
        oMenuListBanco.add(oMenuE1);
        //-
        MaeMenu oMenuE2 = new MaeMenu();
        oMenuE2.setMenuA("Tipo Cambio");
        oMenuE2.setMenuAUrl("/pages/util/tipoCambio.xhtml");
        oMenuE2.setMenuAE("none");
        oMenuListBanco.add(oMenuE2);
        
        MaeMenu oMenuE3 = new MaeMenu();
        oMenuE3.setMenuA("Depositos por Identificar");
        oMenuE3.setMenuAUrl("/pages/caja/Deposito/noidentificados.xhtml");
        oMenuE3.setMenuAE("none");
        oMenuListBanco.add(oMenuE3);
        
        
        oMenuBanco.setoMenuList(oMenuListBanco);
        
        //---================================== manten
        MaeMenu oMenuMantenimiento = new MaeMenu();  
        oMenuMantenimiento.setSimbolo("edit");
        oMenuMantenimiento.setMenuA("Mantenimiento");
        oMenuMantenimiento.setMenuAUrl("#");
        oMenuMantenimiento.setMenuAE("none");
        List<MaeMenu> oMenuListMante = new ArrayList<>();
        //--
        MaeMenu oMenuF1 = new MaeMenu();
        oMenuF1.setMenuA("Cliente");
        oMenuF1.setMenuAUrl("/pages/mantenimiento/cliente.xhtml");
        oMenuF1.setMenuAE("none");
        oMenuListMante.add(oMenuF1);
        
        oMenuMantenimiento.setoMenuList(oMenuListMante);
        
        //---================================== Documentos
        MaeMenu oMenuDocumento = new MaeMenu();  
        oMenuDocumento.setSimbolo("edit");
        oMenuDocumento.setMenuA("Documentos");
        oMenuDocumento.setMenuAUrl("#");
        oMenuDocumento.setMenuAE("none");
        List<MaeMenu> oMenuListDoc = new ArrayList<>();
        //--
        MaeMenu oMenuG1 = new MaeMenu();
        oMenuG1.setMenuA("Documentos");
        oMenuG1.setMenuAUrl("/pages/documento/seguimientoDoc.xhtml");
        oMenuG1.setMenuAE("none");
        oMenuListDoc.add(oMenuG1);
        
        oMenuDocumento.setoMenuList(oMenuListDoc);
        
        
        oMenuList.add(oMenuSistema);
        oMenuList.add(oMenuConsulta);
        oMenuList.add(oMenuReporte);
        oMenuList.add(oMenuPersonas);
        oMenuList.add(oMenuCobranza);
        oMenuList.add(oMenuBanco);
        oMenuList.add(oMenuMantenimiento);
        oMenuList.add(oMenuDocumento);
        
        System.out.println("pop.webcobranzas.bean.MenuBean.<init>() FIN");
                
    }
    
    
      /**
     * @return the oMenuList
     */
    public List<MaeMenu> getoMenuList() {
        return oMenuList;
    }

    /**
     * @param oMenuList the oMenuList to set
     */
    public void setoMenuList(List<MaeMenu> oMenuList) {
        this.oMenuList = oMenuList;
    }
}
