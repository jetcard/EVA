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
public class MaeEstadoCuenta extends Base{
    private MaeFondo fondo;
    private MaeInversion maeInversion;
    private String cinversion;
    private int nsecuencia;
    private Date foperacion;
    private String dcuota;
    private String cconceptoId;
    private String ddescriocion;
    private Number iabono;
    private Number icargo;
    private Number isaldo;
    private Number icapital;
    private Number iinteres;
    private Number imora;
    private int ncuota;
    private int ndeposito;
    private String bTranslado;
    private String moneda;
    private String simbolo;

    
    
    
    public MaeEstadoCuenta() {
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

    public String getCinversion() {
        return cinversion;
    }

    public void setCinversion(String cinversion) {
        this.cinversion = cinversion;
    }

    public int getNsecuencia() {
        return nsecuencia;
    }

    public void setNsecuencia(int nsecuencia) {
        this.nsecuencia = nsecuencia;
    }

    public Date getFoperacion() {
        return foperacion;
    }

    public void setFoperacion(Date foperacion) {
        this.foperacion = foperacion;
    }

    public String getCconceptoId() {
        return cconceptoId;
    }

    public void setCconceptoId(String cconceptoId) {
        this.cconceptoId = cconceptoId;
    }

    public String getDdescriocion() {
        return ddescriocion;
    }

    public void setDdescriocion(String ddescriocion) {
        this.ddescriocion = ddescriocion;
    }
    
    public int getNcuota() {
        return ncuota;
    }

    public void setNcuota(int ncuota) {
        this.ncuota = ncuota;
    }

    public int getNdeposito() {
        return ndeposito;
    }

    public void setNdeposito(int ndeposito) {
        this.ndeposito = ndeposito;
    }

    public String getbTranslado() {
        return bTranslado;
    }

    public void setbTranslado(String bTranslado) {
        this.bTranslado = bTranslado;
    }

    public String getDcuota() {
        return dcuota;
    }

    public void setDcuota(String dcuota) {
        this.dcuota = dcuota;
    }

    public Number getIabono() {
        return iabono;
    }

    public void setIabono(Number iabono) {
        this.iabono = iabono;
    }

    public Number getIcargo() {
        return icargo;
    }

    public void setIcargo(Number icargo) {
        this.icargo = icargo;
    }

    public Number getIsaldo() {
        return isaldo;
    }

    public void setIsaldo(Number isaldo) {
        this.isaldo = isaldo;
    }

    public Number getIcapital() {
        return icapital;
    }

    public void setIcapital(Number icapital) {
        this.icapital = icapital;
    }

    public Number getIinteres() {
        return iinteres;
    }

    public void setIinteres(Number iinteres) {
        this.iinteres = iinteres;
    }

    
  public Number getImora() {
        return imora;
    }

    public void setImora(Number imora) {
        this.imora = imora;
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
