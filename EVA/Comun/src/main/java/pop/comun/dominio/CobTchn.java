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
public class CobTchn extends Base{
    private MaeFondo fondo;
    private MaeInversion maeInversion;
    private Number nroCuotasAtrasadas;
    private Date fechaUltDeposito;
    private Number totalDeposito;
    private String cancelado;
    private String ampliado;
    private String refinanciado;
    private String judicial;
    private String transfAmpl;    
    private String transfrefin;        
    private String transfendosado;   
    private String ejudicial;   

    
    private String moneda;   
    private String simbolo;   

    
    public String getTransfAmpl() {
        return transfAmpl;
    }

    public void setTransfAmpl(String transfAmpl) {
        this.transfAmpl = transfAmpl;
    }

    public String getTransfrefin() {
        return transfrefin;
    }

    public void setTransfrefin(String transfrefin) {
        this.transfrefin = transfrefin;
    }

    public String getTransfendosado() {
        return transfendosado;
    }

    public void setTransfendosado(String transfendosado) {
        this.transfendosado = transfendosado;
    }
         

    public CobTchn() {
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

    public Date getFechaUltDeposito() {
        return fechaUltDeposito;
    }

    public void setFechaUltDeposito(Date fechaUltDeposito) {
        this.fechaUltDeposito = fechaUltDeposito;
    }

    public Number getTotalDeposito() {
        return totalDeposito;
    }

    public void setTotalDeposito(Number totalDeposito) {
        this.totalDeposito = totalDeposito;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getAmpliado() {
        return ampliado;
    }

    public void setAmpliado(String ampliado) {
        this.ampliado = ampliado;
    }

    public String getRefinanciado() {
        return refinanciado;
    }

    public void setRefinanciado(String refinanciado) {
        this.refinanciado = refinanciado;
    }

    public String getJudicial() {
        return judicial;
    }

    public void setJudicial(String judicial) {
        this.judicial = judicial;
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
    
    public String getEjudicial() {
        return ejudicial;
    }

    public void setEjudicial(String ejudicial) {
        this.ejudicial = ejudicial;
    }
}
