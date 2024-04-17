/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeAsesor;
import pop.comun.dominio.MaeEmail;

import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IAsesorServ;
import pop.webcobranzas.iface.ICronogramaServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.iface.IAsesorServ;

import pop.webcobranzas.servicio.AsesorServ;
import pop.webcobranzas.servicio.CronogramaServ;

import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.InversionServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.servicio.UbigeoServ;
import pop.webcobranzas.webservices.Menu;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class ClienteDeudorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SeguimientoBean seguimientoBean;
    
    
    // ubigeo Busqueda
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    // imnueble (busq por provincia/distrito)
    private MaeInmueble maeInmueble = new MaeInmueble();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // Persona (busq por documento de la persona)
    private MaePersona maePersona = new MaePersona();
    private List<MaeInversion> maeResumenList;

    private MaeInversion maeInversion = new MaeInversion();
    // estado inversion
    private CobTablas cobTablasEstInv = new CobTablas();
    //asesor busqueda
    private MaeAsesor maeAsesor = new MaeAsesor();

    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de inversiones con deuda
    private List<MaeInversion> maeInversionList;
   

    
    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista de estado de inversion
    private List<CobTablas> cobTablasEstInvList;
    // lista de telefonos grabar
    private List<MaeTelefono> maeTelefonos;
    // lista de asesores
    private List<MaeAsesor> maeAsesorList;

    
    
    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de inversion
    private InversionServ inversionServ = new InversionServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // servicio de cronograma
    private ICronogramaServ cronogramaServ = new CronogramaServ();
    // servicio de asesor
    private IAsesorServ asesorServ = new AsesorServ();

    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";
    private String simbolo = "S/.";
    private String moneda = "SOLES";
    private int contSeguimiento;

    private boolean deudorBtn01;
    private boolean deudorBtn02;
    private boolean deudorBtn03;
    private boolean deudorBtn04;
    private boolean deudorBtn05;
    private boolean deudorBtn06;
    private boolean deudorBtn07;
    private boolean deudorBtn08;
    private boolean deudorBtn09;
    private boolean deudorBtn10;

   
    public ClienteDeudorBean() {
        try {
            maeInversion.setMaeFondo(maeFondo);
            maeInversion.setcPersonaId(maePersona);
            maeInversion.setMaeInmueble(maeInmueble);
            maeInversion.setMaeAsesor(maeAsesor);
                
            maeTelefonos = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDeudorBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     public void listarPermisosDeudores(LoginBean oLogin) {
        try {
            int x=0;
            for (Menu oMenu : oLogin.getUsuarioSession().getMenu()){
                for (Menu oMenu2 : oLogin.getUsuarioSession().getMenu().get(x).getOMenuList()){
                  if (oMenu2.getCmenu()==18 ){
                      deudorBtn01 = oMenu2.isBtn1();
                      deudorBtn02 = oMenu2.isBtn2();
                      deudorBtn03 = oMenu2.isBtn3();
                      deudorBtn04 = oMenu2.isBtn4();
                      deudorBtn05 = oMenu2.isBtn5();
                      deudorBtn06 = oMenu2.isBtn6();
                      deudorBtn07 = oMenu2.isBtn7();
                      deudorBtn08 = oMenu2.isBtn8();                   
                      deudorBtn09 = oMenu2.isBtn9();
                      deudorBtn10 = oMenu2.isBtn10();                      
                      
                        //System.out.println("secundario"+"item "+ x +"  "+oMenu2.getCmenu()+ oMenu2.getMenuA()+ oMenu2.isBtn1());
                  }
                  
              }   
                x = x + 1; 
           }     
        } catch (Exception e) {

        }
    }

    
    public void listarProvincia() {
        try {
            if (ubigeoProv == null) {
                ubigeoProv = getUbigeoServ().listarProvincia(maeUbigeoP);
            }
            //System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {

        }
    }
    
    public void listarAsesor(){
        try {
            showMsg = false;
            if (maeAsesorList == null) {
                maeAsesorList = getAsesorServ().listarAsesor(new MaeAsesor());
            }
        } catch (Exception e) {
            
        }
    }

   
     
    public void listarFondos() {
        try {
            showMsg = false;
            if (maeFondoList == null) {
                maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarDistrito()");
            ubigeoDist = getUbigeoServ().listarDistrito(maeUbigeoP);
            //System.out.println(" cantidad de Distritos -> " + ubigeoDist.size());
        } catch (Exception e) {
        }
    }

    public void listarEstadoInversion() {
        try {
            if (cobTablasEstInvList == null) {
                cobTablasEstInv.setCtablaId("0608");
                cobTablasEstInvList = getTablasServ().listarTablas(cobTablasEstInv);
            }
            //System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {

        }
    }

    public void listarAccion() {
        try {
            if (seguimientoBean.getCobTablasAccionList() == null) {
                seguimientoBean.listarAccionRem();
            }
            //System.out.println(" cantidad de getCobTablasAccionList -> " + seguimientoBean.getCobTablasAccionList().size());
        } catch (Exception e) {

        }
    }      

    public void listarFamilia() {
        try {
            if (seguimientoBean.getCobTablasFamiliaList() == null) {
                seguimientoBean.listarFamiliaRem();
            }
            //System.out.println(" cantidad de getCobTablasFamiliaList -> " + seguimientoBean.getCobTablasFamiliaList().size());
        } catch (Exception e) {

        }
    }

    public void buscarDeudores() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" ClienteDeudorBean - buscarDeudores ");
            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            //System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            //System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());
            ArrayList<MaeEmail> lstEmails = new ArrayList<>();
            seguimientoBean.setMaeEmailList(lstEmails);
            maeInmueble.setMaeUbigeo(maeUbigeo);
      
            maeInversionList = getInversionServ().listarDeudor(maeInversion);
            if (maeInversionList.size()>1){
                maeResumenList = getInversionServ().listarResumen(maeInversionList.get(maeInversionList.size()-1).getcMaeInversionId());
            }else{
                maeResumenList = getInversionServ().listarResumen(maeInversionList.get(0).getcMaeInversionId());
            }            
//se modifico sabado
            seguimientoBean.getEstadoCuentaBean().getMaeEstadoCuentaList().clear();
            seguimientoBean.getCuotaPagoPrint().setiPendiente(0);
                  //System.out.println("aca esta");
            if (maeResumenList.size()>0){
                //System.out.println("aca entro");
                 //System.out.println("aca entro");
                setSimbolo(maeResumenList.get(0).getSimbolo());
                setMoneda(maeResumenList.get(0).getCmoneda());
                 
            }
            
      
            //System.out.println(getMoneda()+"dsadsa");
            //System.out.println(getSimbolo()+"dsadsa");
   
              
            seguimientoBean.getCronogramaBean().getCronogramaList().clear();
            seguimientoBean.getMaeCuotaPagoList().clear();
            seguimientoBean.getMcpList().clear();
            seguimientoBean.setCuoPendienteTotal(0);
            seguimientoBean.setCuoPendienteFC(0);
           
            seguimientoBean.getMaeInversionSeguiDet().setCInversion("");
            seguimientoBean.getMaeInversionSeguiDet().getcPersonaId().setDApePat("");
            seguimientoBean.getMaeInversionSeguiDet().getcPersonaId().setDApeMat("");
            seguimientoBean.getMaeInversionSeguiDet().getcPersonaId().setDNombres("");
            seguimientoBean.getMaeInversionSeguiDet().setNCuotasAtrasadas(0);
            
            //System.out.println("pop.webcobranzas.bean.InversionBean.<init>()");
            //System.out.println("   Lista de Depositos = " + maeInversionList.size());
            getSeguimientoBean().setShowMsg(false);

        } catch (Exception e) {
            System.err.println("pop.webcobranzas.bean.InversionBean.buscarDeudores()");
        }
    }

    public MaeUbigeo getMaeUbigeoP() {
        return maeUbigeoP;
    }

    public void setMaeUbigeoP(MaeUbigeo maeUbigeoP) {
        this.maeUbigeoP = maeUbigeoP;
    }

    public List<MaeUbigeo> getUbigeoProv() {
        return ubigeoProv;
    }

    public void setUbigeoProv(List<MaeUbigeo> ubigeoProv) {
        this.ubigeoProv = ubigeoProv;
    }

    public List<MaeUbigeo> getUbigeoDist() {
        return ubigeoDist;
    }

    public void setUbigeoDist(List<MaeUbigeo> ubigeoDist) {
        this.ubigeoDist = ubigeoDist;
    }

    public IUbigeoServ getUbigeoServ() {
        return ubigeoServ;
    }

    public void setUbigeoServ(IUbigeoServ ubigeoServ) {
        this.ubigeoServ = ubigeoServ;
    }

    public MaeUbigeo getMaeUbigeoD() {
        return maeUbigeoD;
    }

    public void setMaeUbigeoD(MaeUbigeo maeUbigeoD) {
        this.maeUbigeoD = maeUbigeoD;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public InversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(InversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaeAsesor getMaeAsesor() {
        return maeAsesor;
    }

    public void setMaeAsesor(MaeAsesor maeAsesor) {
        this.maeAsesor = maeAsesor;
    }
    
    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public SeguimientoBean getSeguimientoBean() {
        return seguimientoBean;
    }

    public void setSeguimientoBean(SeguimientoBean seguimientoBean) {
        this.seguimientoBean = seguimientoBean;
    }
    
    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    public IAsesorServ getAsesorServ() {
        return asesorServ;
    }

    public void setAsesorServ(IAsesorServ asesorServ) {
        this.asesorServ = asesorServ;
    }

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }
    
    public List<MaeAsesor> getMaeAsesorList() {
        return maeAsesorList;
    }

    public void setMaeAsesorList(List<MaeAsesor> maeAsesorList) {
        this.maeAsesorList = maeAsesorList;
    }

    public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMsj() {
        return tipoMsj;
    }

    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }

    public List<MaeTelefono> getMaeTelefonos() {
        return maeTelefonos;
    }

    public void setMaeTelefonos(List<MaeTelefono> maeTelefonos) {
        this.maeTelefonos = maeTelefonos;
    }

    public CobTablas getCobTablasEstInv() {
        return cobTablasEstInv;
    }

    public void setCobTablasEstInv(CobTablas cobTablasEstInv) {
        this.cobTablasEstInv = cobTablasEstInv;
    }

    public List<CobTablas> getCobTablasEstInvList() {
        return cobTablasEstInvList;
    }

    public void setCobTablasEstInvList(List<CobTablas> cobTablasEstInvList) {
        this.cobTablasEstInvList = cobTablasEstInvList;
    }

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public ICronogramaServ getCronogramaServ() {
        return cronogramaServ;
    }

    public void setCronogramaServ(ICronogramaServ cronogramaServ) {
        this.cronogramaServ = cronogramaServ;
    }
    public List<MaeInversion> getMaeResumenList() {
        return maeResumenList;
    }

    public void setMaeResumenList(List<MaeInversion> maeResumenList) {
        this.maeResumenList = maeResumenList;
    }
  
     public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
  public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the contSeguimiento
     */
    public int getContSeguimiento() {
        return contSeguimiento;
    }

    /**
     * @param contSeguimiento the contSeguimiento to set
     */
    public void setContSeguimiento(int contSeguimiento) {
        this.contSeguimiento = contSeguimiento;
    }
   

      public boolean isDeudorBtn01() {
        return deudorBtn01;
    }

    public void setDeudorBtn01(boolean deudorBtn01) {
        this.deudorBtn01 = deudorBtn01;
    }

    public boolean isDeudorBtn02() {
        return deudorBtn02;
    }

    public void setDeudorBtn02(boolean deudorBtn02) {
        this.deudorBtn02 = deudorBtn02;
    }

    public boolean isDeudorBtn03() {
        return deudorBtn03;
    }

    public void setDeudorBtn03(boolean deudorBtn03) {
        this.deudorBtn03 = deudorBtn03;
    }

    public boolean isDeudorBtn04() {
        return deudorBtn04;
    }

    public void setDeudorBtn04(boolean deudorBtn04) {
        this.deudorBtn04 = deudorBtn04;
    }

    
    public boolean isDeudorBtn05() {
        return deudorBtn05;
    }

    public void setDeudorBtn05(boolean deudorBtn05) {
        this.deudorBtn05 = deudorBtn05;
    }

     public boolean isDeudorBtn06() {
        return deudorBtn06;
    }

    public void setDeudorBtn06(boolean deudorBtn06) {
        this.deudorBtn06 = deudorBtn06;
    }

    public boolean isDeudorBtn07() {
        return deudorBtn07;
    }

    public void setDeudorBtn07(boolean deudorBtn07) {
        this.deudorBtn07 = deudorBtn07;
    }

    public boolean isDeudorBtn08() {
        return deudorBtn08;
    }

    public void setDeudorBtn08(boolean deudorBtn08) {
        this.deudorBtn08 = deudorBtn08;
    }

    public boolean isDeudorBtn09() {
        return deudorBtn09;
    }

    public void setDeudorBtn09(boolean deudorBtn09) {
        this.deudorBtn09 = deudorBtn09;
    }

    public boolean isDeudorBtn10() {
        return deudorBtn10;
    }

    public void setDeudorBtn10(boolean deudorBtn10) {
        this.deudorBtn10 = deudorBtn10;
    }

      
}
