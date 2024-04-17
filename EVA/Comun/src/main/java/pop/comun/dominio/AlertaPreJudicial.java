/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.io.Serializable;

/**
 *
 * @author PR154357
 */
public class AlertaPreJudicial implements Serializable{
    private String fondo;
    private String codigoTchn;
    private String cliente;
    private String fechaDesembolso;
    private String fechaVencimiento;
    private String moneda;
    private String monto;
    private String cuotasAtrasadas;
    private String cuotasGeneradas;
    private String fechaProtesto;
    private String diasDesdeProtesto;
    
     public AlertaPreJudicial() {
        super();
    }


    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getFondo() {
        return fondo;
    }

    public void setCodigoTchn(String codigoTchn) {
        this.codigoTchn = codigoTchn;
    }

    public String getCodigoTchn() {
        return codigoTchn;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setFechaDesembolso(String fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public String getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMonto() {
        return monto;
    }

    public void setCuotasAtrasadas(String cuotasAtrasadas) {
        this.cuotasAtrasadas = cuotasAtrasadas;
    }

    public String getCuotasAtrasadas() {
        return cuotasAtrasadas;
    }

    public void setCuotasGeneradas(String cuotasGeneradas) {
        this.cuotasGeneradas = cuotasGeneradas;
    }

    public String getCuotasGeneradas() {
        return cuotasGeneradas;
    }

    public void setFechaProtesto(String fechaProtesto) {
        this.fechaProtesto = fechaProtesto;
    }

    public String getFechaProtesto() {
        return fechaProtesto;
    }

    public void setDiasDesdeProtesto(String diasDesdeProtesto) {
        this.diasDesdeProtesto = diasDesdeProtesto;
    }

    public String getDiasDesdeProtesto() {
        return diasDesdeProtesto;
    }
    
}
