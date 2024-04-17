/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

 
import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class LegalTchn extends Base{
    private MaeFondo fondo;
    private MaeInversion maeInversion;
    private Number nroCuotasAtrasadas;
    private String idetapa;
    private String idestado;    
    private String etapa;
    private String estado;
    private String DIR1;
    private Date ldFecha;
    private Date ldFechaAutomisor;
    private Date ldFechaTasa;
    private Double lnCapital;
    private Double lnInteres;
    private Double lnMora;
    private Double lnTotal;
    private Double nvaloriza;
    private String Ddescripcion;
    private String lsIndicador;
    private String lsusuario;
    private Integer lnDiastra;
    private boolean fmostrar;
    private boolean fmodificar;
    private boolean fnuevo;
    private String fcerrar;
    private String fremate;
    private String fconclusion;
    private boolean fnuevoEt2;
    private boolean fmostrarEt2;
    private boolean fmostrarEt3;
    private boolean fmostrarView1;
    private boolean fmostrarView2;
    private boolean fmostrarView3;
    private boolean fmodificarEt2;
    private String lsCodJuz;
    private String lsCodEsp;
    private String lsNroExp;
    private Double lnmontoAdm;
    private String lsNomJuz;
    private String lsNomEsp;
    private int iCapital;
    private int iPopular;
    private int iMype;
    private int iPerez;
    private int iTotalFondo;
    private Date fIniBusq;
    private Date fFinBusq;

      
    private int iOptimo;
    private int iNormal;
    private int iRegular;
    private int iDeficiente;
    private int iTotalIndi;
    
    private String lsAsesor;
    private int iCalifi;
    private int iPostula;
    private int iProba;
    private int iDesiso;
    private int iImpug;
    private int iEjecu;
    private int iFinal1;
    private int iTotalAse;
    private String moneda;
    private String simbolo;
    private String nroExpediente;
    private Date fechajudicial;
    private Date fechacancelado;

 

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }    

    public String getDIR1() {
        return DIR1;
    }

    public void setDIR1(String DIR1) {
        this.DIR1 = DIR1;
    }    
    
     public String getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(String idetapa) {
        this.idetapa = idetapa;
    }

    public String getIdestado() {
        return idestado;
    }

    public void setIdestado(String idestado) {
        this.idestado = idestado;
    }
    
    
     public Double getNvaloriza() {
        return nvaloriza;
    }

    public void setNvaloriza(Double nvaloriza) {
        this.nvaloriza = nvaloriza;
    }
    
    
    public Date getLdFecha() {
        return ldFecha;
    }

    public void setLdFecha(Date ldFecha) {
        this.ldFecha = ldFecha;
    }


   
    
    public String getLsusuario() {
        return lsusuario;
    }

    public void setLsusuario(String lsusuario) {
        this.lsusuario = lsusuario;
    }
     
   
    public Double getLnCapital() {
        return lnCapital;
    }

    public void setLnCapital(Double lnCapital) {
        this.lnCapital = lnCapital;
    }

    public Double getLnInteres() {
        return lnInteres;
    }

    public void setLnInteres(Double lnInteres) {
        this.lnInteres = lnInteres;
    }

    public Double getLnMora() {
        return lnMora;
    }

    public void setLnMora(Double lnMora) {
        this.lnMora = lnMora;
    }

    public Double getLnTotal() {
        return lnTotal;
    }

    public void setLnTotal(Double lnTotal) {
        this.lnTotal = lnTotal;
    } 
    
    public Integer getLnDiastra() {
        return lnDiastra;
    }

    public void setLnDiastra(Integer lnDiastra) {
        this.lnDiastra = lnDiastra;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Date getLdFechaTasa() {
        return ldFechaTasa;
    }

    public void setLdFechaTasa(Date ldFechaTasa) {
        this.ldFechaTasa = ldFechaTasa;
    }


    public boolean isFmostrar() {
        return fmostrar;
    }

    public void setFmostrar(boolean fmostrar) {
        this.fmostrar = fmostrar;
    }

    public boolean isFmodificar() {
        return fmodificar;
    }

    public void setFmodificar(boolean fmodificar) {
        this.fmodificar = fmodificar;
    }

    public boolean isFnuevo() {
        return fnuevo;
    }

    public void setFnuevo(boolean fnuevo) {
        this.fnuevo = fnuevo;
    }

    public String getDdescripcion() {
        return Ddescripcion;
    }

    public void setDdescripcion(String Ddescripcion) {
        this.Ddescripcion = Ddescripcion;
    }

    
    public String getLsIndicador() {
        return lsIndicador;
    }

    public void setLsIndicador(String lsIndicador) {
        this.lsIndicador = lsIndicador;
    }
    
    
    
    public String getFcerrar() {
        return fcerrar;
    }

    public void setFcerrar(String fcerrar) {
        this.fcerrar = fcerrar;
    }

    public String getFconclusion() {
        return fconclusion;
    }

    public void setFconclusion(String fconclusion) {
        this.fconclusion = fconclusion;
    }

    public boolean isFnuevoEt2() {
        return fnuevoEt2;
    }

    public void setFnuevoEt2(boolean fnuevoEt2) {
        this.fnuevoEt2 = fnuevoEt2;
    }

    public boolean isFmostrarEt2() {
        return fmostrarEt2;
    }

    public void setFmostrarEt2(boolean fmostrarEt2) {
        this.fmostrarEt2 = fmostrarEt2;
    }

    public boolean isFmodificarEt2() {
        return fmodificarEt2;
    }

    public void setFmodificarEt2(boolean fmodificarEt2) {
        this.fmodificarEt2 = fmodificarEt2;
    }

    public String getLsCodJuz() {
        return lsCodJuz;
    }

    public void setLsCodJuz(String lsCodJuz) {
        this.lsCodJuz = lsCodJuz;
    }

    public String getLsCodEsp() {
        return lsCodEsp;
    }

    public void setLsCodEsp(String lsCodEsp) {
        this.lsCodEsp = lsCodEsp;
    }

    public String getLsNroExp() {
        return lsNroExp;
    }

    public void setLsNroExp(String lsNroExp) {
        this.lsNroExp = lsNroExp;
    }

    public Double getLnmontoAdm() {
        return lnmontoAdm;
    }

    public void setLnmontoAdm(Double lnmontoAdm) {
        this.lnmontoAdm = lnmontoAdm;
    }

    public boolean isFmostrarEt3() {
        return fmostrarEt3;
    }

    public void setFmostrarEt3(boolean fmostrarEt3) {
        this.fmostrarEt3 = fmostrarEt3;
    }


    public String getLsNomJuz() {
        return lsNomJuz;
    }

    public void setLsNomJuz(String lsNomJuz) {
        this.lsNomJuz = lsNomJuz;
    }

    public String getLsNomEsp() {
        return lsNomEsp;
    }

    public void setLsNomEsp(String lsNomEsp) {
        this.lsNomEsp = lsNomEsp;
    }
    
    public boolean isFmostrarView1() {
        return fmostrarView1;
    }

    public void setFmostrarView1(boolean fmostrarView1) {
        this.fmostrarView1 = fmostrarView1;
    }

    public boolean isFmostrarView2() {
        return fmostrarView2;
    }

    public void setFmostrarView2(boolean fmostrarView2) {
        this.fmostrarView2 = fmostrarView2;
    }

    public boolean isFmostrarView3() {
        return fmostrarView3;
    }

    public void setFmostrarView3(boolean fmostrarView3) {
        this.fmostrarView3 = fmostrarView3;
    }
    
    public String getFremate() {
        return fremate;
    }

    public void setFremate(String fremate) {
        this.fremate = fremate;
    }
    
    public Date getLdFechaAutomisor() {
        return ldFechaAutomisor;
    }

    public void setLdFechaAutomisor(Date ldFechaAutomisor) {
        this.ldFechaAutomisor = ldFechaAutomisor;
    }


    public int getiCapital() {
        return iCapital;
    }

    public void setiCapital(int iCapital) {
        this.iCapital = iCapital;
    }

    public int getiPopular() {
        return iPopular;
    }

    public void setiPopular(int iPopular) {
        this.iPopular = iPopular;
    }

    public int getiMype() {
        return iMype;
    }

    public void setiMype(int iMype) {
        this.iMype = iMype;
    }

    public int getiPerez() {
        return iPerez;
    }

    public void setiPerez(int iPerez) {
        this.iPerez = iPerez;
    }

    public int getiTotalFondo() {
        return iTotalFondo;
    }

    public void setiTotalFondo(int iTotalFondo) {
        this.iTotalFondo = iTotalFondo;
    }

    public int getiOptimo() {
        return iOptimo;
    }

    public void setiOptimo(int iOptimo) {
        this.iOptimo = iOptimo;
    }

    public int getiNormal() {
        return iNormal;
    }

    public void setiNormal(int iNormal) {
        this.iNormal = iNormal;
    }

    public int getiRegular() {
        return iRegular;
    }

    public void setiRegular(int iRegular) {
        this.iRegular = iRegular;
    }

    public int getiDeficiente() {
        return iDeficiente;
    }

    public void setiDeficiente(int iDeficiente) {
        this.iDeficiente = iDeficiente;
    }

    public int getiTotalIndi() {
        return iTotalIndi;
    }

    public void setiTotalIndi(int iTotalIndi) {
        this.iTotalIndi = iTotalIndi;
    }

    public String getLsAsesor() {
        return lsAsesor;
    }

    public void setLsAsesor(String lsAsesor) {
        this.lsAsesor = lsAsesor;
    }

    public int getiCalifi() {
        return iCalifi;
    }

    public void setiCalifi(int iCalifi) {
        this.iCalifi = iCalifi;
    }

    public int getiPostula() {
        return iPostula;
    }

    public void setiPostula(int iPostula) {
        this.iPostula = iPostula;
    }

    public int getiProba() {
        return iProba;
    }

    public void setiProba(int iProba) {
        this.iProba = iProba;
    }

    public int getiDesiso() {
        return iDesiso;
    }

    public void setiDesiso(int iDesiso) {
        this.iDesiso = iDesiso;
    }

    public int getiImpug() {
        return iImpug;
    }

    public void setiImpug(int iImpug) {
        this.iImpug = iImpug;
    }

    public int getiEjecu() {
        return iEjecu;
    }

    public void setiEjecu(int iEjecu) {
        this.iEjecu = iEjecu;
    }

    public int getiFinal1() {
        return iFinal1;
    }

    public void setiFinal1(int iFinal1) {
        this.iFinal1 = iFinal1;
    }

    public int getiTotalAse() {
        return iTotalAse;
    }

    public void setiTotalAse(int iTotalAse) {
        this.iTotalAse = iTotalAse;
    }
    
      
    public MaeFondo getFondo() {
        return fondo;
    }

    public void setFondo(MaeFondo fondo) {
        this.fondo = fondo;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public Number getNroCuotasAtrasadas() {
        return nroCuotasAtrasadas;
    }

    public void setNroCuotasAtrasadas(Number nroCuotasAtrasadas) {
        this.nroCuotasAtrasadas = nroCuotasAtrasadas;
    }
 
 public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * @return the nroExpediente
     */
    public String getNroExpediente() {
        return nroExpediente;
    }

    /**
     * @param nroExpediente the nroExpediente to set
     */
    public void setNroExpediente(String nroExpediente) {
        this.nroExpediente = nroExpediente;
    }    

    public Date getFechajudicial() {
        return fechajudicial;
    }

    public void setFechajudicial(Date fechajudicial) {
        this.fechajudicial = fechajudicial;
    }

    public Date getFechacancelado() {
        return fechacancelado;
    }

    public void setFechacancelado(Date fechacancelado) {
        this.fechacancelado = fechacancelado;
    }

    
    @Override
    public String toString() {
        return "LegalTchn{" + "fondo=" + fondo + ", maeInversion=" + maeInversion + ", nroCuotasAtrasadas=" + nroCuotasAtrasadas + ", idetapa=" + idetapa + ", idestado=" + idestado + ", etapa=" + etapa + ", estado=" + estado + ", DIR1=" + DIR1 + ", ldFecha=" + ldFecha + ", ldFechaAutomisor=" + ldFechaAutomisor + ", ldFechaTasa=" + ldFechaTasa + ", lnCapital=" + lnCapital + ", lnInteres=" + lnInteres + ", lnMora=" + lnMora + ", lnTotal=" + lnTotal + ", nvaloriza=" + nvaloriza + ", Ddescripcion=" + Ddescripcion + ", lsIndicador=" + lsIndicador + ", lsusuario=" + lsusuario + ", lnDiastra=" + lnDiastra + ", fmostrar=" + fmostrar + ", fmodificar=" + fmodificar + ", fnuevo=" + fnuevo + ", fcerrar=" + fcerrar + ", fremate=" + fremate + ", fconclusion=" + fconclusion + ", fnuevoEt2=" + fnuevoEt2 + ", fmostrarEt2=" + fmostrarEt2 + ", fmostrarEt3=" + fmostrarEt3 + ", fmostrarView1=" + fmostrarView1 + ", fmostrarView2=" + fmostrarView2 + ", fmostrarView3=" + fmostrarView3 + ", fmodificarEt2=" + fmodificarEt2 + ", lsCodJuz=" + lsCodJuz + ", lsCodEsp=" + lsCodEsp + ", lsNroExp=" + lsNroExp + ", lnmontoAdm=" + lnmontoAdm + ", lsNomJuz=" + lsNomJuz + ", lsNomEsp=" + lsNomEsp + ", iCapital=" + iCapital + ", iPopular=" + iPopular + ", iMype=" + iMype + ", iPerez=" + iPerez + ", iTotalFondo=" + iTotalFondo + ", iOptimo=" + iOptimo + ", iNormal=" + iNormal + ", iRegular=" + iRegular + ", iDeficiente=" + iDeficiente + ", iTotalIndi=" + iTotalIndi + ", lsAsesor=" + lsAsesor + ", iCalifi=" + iCalifi + ", iPostula=" + iPostula + ", iProba=" + iProba + ", iDesiso=" + iDesiso + ", iImpug=" + iImpug + ", iEjecu=" + iEjecu + ", iFinal1=" + iFinal1 + ", iTotalAse=" + iTotalAse + ", moneda=" + moneda + ", simbolo=" + simbolo + ", nroExpediente=" + nroExpediente + '}';
    }

      public Date getFIniBusq() {
        return fIniBusq;
    }

    public void setFIniBusq(Date fIniBusq) {
        this.fIniBusq = fIniBusq;
    }

    public Date getFFinBusq() {
        return fFinBusq;
    }

    public void setFFinBusq(Date fFinBusq) {
        this.fFinBusq = fFinBusq;
    }
 

}

