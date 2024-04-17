/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.sql.Clob;
import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class LegSeguiTchn extends Base{
    private MaeFondo fondo;
    private MaeInversion maeInversion;
    private Number lsIdSegui;
    private String lsCodigoTchn;
    private String ls_tipoEtapa;
    private String lsCodEst;
    private String lsDestipoEtapa;
    private Date ld_fecha;
    private String ls_UsuarioAdd;  
    private Number ln_dias;
    private Number lnIndicador ;
    private String lsEstado;
    private String lsDescrip;
    private boolean Fmostrar;
    private boolean Fnuevo;
    private boolean FnuevoEje;

    private boolean FmostrarEje;
    private boolean FmostrarEjeOpc1;
    private boolean FmostrarEjeOpc2;

    
    private boolean Fmodificar;
    private Number lnCorrelativo;
    
    private String fconcluir;
    private boolean cconcluir=false;


    private String fremate;
    private boolean cremate=false;

    
    private Date   ldFechaval;    
    private Double lnMontoval ;
    private Double lnMontodolval ;
    private Double lnInteres;
    private Double lnCostos;
    private Double lnCostas;
    private Double lnIntMora;
    
    private String moneda;
    private String simbolo;

    
    
    public LegSeguiTchn() {
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
 

    public Number getLsIdSegui() {
        return lsIdSegui;
    }

    public void setLsIdSegui(Number lsIdSegui) {
        this.lsIdSegui = lsIdSegui;
    }

    public String getLsCodigoTchn() {
        return lsCodigoTchn;
    }

    public void setLsCodigoTchn(String lsCodigoTchn) {
        this.lsCodigoTchn = lsCodigoTchn;
    }

    public String getLs_tipoEtapa() {
        return ls_tipoEtapa;
    }

    public void setLs_tipoEtapa(String ls_tipoEtapa) {
        this.ls_tipoEtapa = ls_tipoEtapa;
    }

    public String getLsCodEst() {
        return lsCodEst;
    }

    public void setLsCodEst(String lsCodEst) {
        this.lsCodEst = lsCodEst;
    }

    public Date getLd_fecha() {
        return ld_fecha;
    }

    public void setLd_fecha(Date ld_fecha) {
        this.ld_fecha = ld_fecha;
    }

    public String getLs_UsuarioAdd() {
        return ls_UsuarioAdd;
    }

    public void setLs_UsuarioAdd(String ls_UsuarioAdd) {
        this.ls_UsuarioAdd = ls_UsuarioAdd;
    }    
    
    public Number getLn_dias() {
        return ln_dias;
    }

    public void setLn_dias(Number ln_dias) {
        this.ln_dias = ln_dias;
    }

    public Number getLnIndicador() {
        return lnIndicador;
    }

    public void setLnIndicador(Number lnIndicador) {
        this.lnIndicador = lnIndicador;
    }
    
    
     public String getLsEstado() {
        return lsEstado;
    }

    public void setLsEstado(String lsEstado) {
        this.lsEstado = lsEstado;
    }

 
    
    public boolean isFmostrar() {
        return Fmostrar;
    }

    public void setFmostrar(boolean Fmostrar) {
        this.Fmostrar = Fmostrar;
    }

    public boolean isFnuevo() {
        return Fnuevo;
    }

    public void setFnuevo(boolean Fnuevo) {
        this.Fnuevo = Fnuevo;
    }

    public boolean isFmodificar() {
        return Fmodificar;
    }

    public void setFmodificar(boolean Fmodificar) {
        this.Fmodificar = Fmodificar;
    }

    public String getLsDestipoEtapa() {
        return lsDestipoEtapa;
    }

    public void setLsDestipoEtapa(String lsDestipoEtapa) {
        this.lsDestipoEtapa = lsDestipoEtapa;
    }

    public Number getLnCorrelativo() {
        return lnCorrelativo;
    }

    public void setLnCorrelativo(Number lnCorrelativo) {
        this.lnCorrelativo = lnCorrelativo;
    }
    
    
    
    public String getLsDescrip() {
        return lsDescrip;
    }

    public void setLsDescrip(String lsDescrip) {
        this.lsDescrip = lsDescrip;
    }
    
    
     public String getFconcluir() {
        return fconcluir;
    }

    public void setFconcluir(String fconcluir) {
        this.fconcluir = fconcluir;
    }

    public boolean isCconcluir() {
        return cconcluir;
    }

    public void setCconcluir(boolean cconcluir) {
        this.cconcluir = cconcluir;
    }

     public boolean isFmostrarEje() {
        return FmostrarEje;
    }

    public void setFmostrarEje(boolean FmostrarEje) {
        this.FmostrarEje = FmostrarEje;
    }   
    
    public boolean isFnuevoEje() {
        return FnuevoEje;
    }

    public void setFnuevoEje(boolean FnuevoEje) {
        this.FnuevoEje = FnuevoEje;
    }


 public Date getLdFechaval() {
        return ldFechaval;
    }

    public void setLdFechaval(Date ldFechaval) {
        this.ldFechaval = ldFechaval;
    }

    public Double getLnMontoval() {
        return lnMontoval;
    }

    public void setLnMontoval(Double lnMontoval) {
        this.lnMontoval = lnMontoval;
    }

    public Double getLnInteres() {
        return lnInteres;
    }

    public void setLnInteres(Double lnInteres) {
        this.lnInteres = lnInteres;
    }

    public Double getLnCostos() {
        return lnCostos;
    }

    public void setLnCostos(Double lnCostos) {
        this.lnCostos = lnCostos;
    }

    public Double getLnCostas() {
        return lnCostas;
    }

    public void setLnCostas(Double lnCostas) {
        this.lnCostas = lnCostas;
    }

    public boolean isFmostrarEjeOpc1() {
        return FmostrarEjeOpc1;
    }

    public void setFmostrarEjeOpc1(boolean FmostrarEjeOpc1) {
        this.FmostrarEjeOpc1 = FmostrarEjeOpc1;
    }

    public boolean isFmostrarEjeOpc2() {
        return FmostrarEjeOpc2;
    }

    public void setFmostrarEjeOpc2(boolean FmostrarEjeOpc2) {
        this.FmostrarEjeOpc2 = FmostrarEjeOpc2;
    }    
    
     public String getFremate() {
        return fremate;
    }

    public void setFremate(String fremate) {
        this.fremate = fremate;
    }

    public boolean isCremate() {
        return cremate;
    }

    public void setCremate(boolean cremate) {
        this.cremate = cremate;
    }
    
    public Double getLnIntMora() {
        return lnIntMora;
    }

    public void setLnIntMora(Double lnIntMora) {
        this.lnIntMora = lnIntMora;
    }
    
    public Double getLnMontodolval() {
        return lnMontodolval;
    }

    public void setLnMontodolval(Double lnMontodolval) {
        this.lnMontodolval = lnMontodolval;
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
}
