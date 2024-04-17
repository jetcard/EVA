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
public class MaeCronograma extends Base{

    private static final long serialVersionUID = 1L;
    
    private MaeInversion maeInversion;
    private Number nSecuencia;
    private Date fpago;
    private Number icapital;
    private Number iinteres;
    private Number icuota;
    private Number ideposito;
    private Number isaldo;
    private String baprobado;
    private String bcuotaGenerada;
    private Number imora;
    private Number ica;
    private String moneda;
    private String simbolo;

   
    
    public MaeCronograma() {
    }
 
    /**
     * @return the nSecuencia
     */
    public Number getnSecuencia() {
        return nSecuencia;
    }

    /**
     * @param nSecuencia the nSecuencia to set
     */
    public void setnSecuencia(Number nSecuencia) {
        this.nSecuencia = nSecuencia;
    }

    public Number getImora() {
        return imora;
    }

    public void setImora(Number imora) {
        this.imora = imora;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public Date getFpago() {
        return fpago;
    }

    public void setFpago(Date fpago) {
        this.fpago = fpago;
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

    public Number getIcuota() {
        return icuota;
    }

    public void setIcuota(Number icuota) {
        this.icuota = icuota;
    }

    public Number getIdeposito() {
        return ideposito;
    }

    public void setIdeposito(Number ideposito) {
        this.ideposito = ideposito;
    }

    public Number getIsaldo() {
        return isaldo;
    }

    public void setIsaldo(Number isaldo) {
        this.isaldo = isaldo;
    }

    public String getBaprobado() {
        return baprobado;
    }

    public void setBaprobado(String baprobado) {
        this.baprobado = baprobado;
    }

    public String getBcuotaGenerada() {
        return bcuotaGenerada;
    }

    public void setBcuotaGenerada(String bcuotaGenerada) {
        this.bcuotaGenerada = bcuotaGenerada;
    }

	public Number getIca() {
        return ica;
    }

    public void setIca(Number ica) {
        this.ica = ica;
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
