/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;
import pop.comun.dominio.Base;
import pop.comun.dominio.CobCronogramaMetaDetalle;
/**
 *
 * @author EC23329
 */
public class CobCronogramaRecaudoResumen extends  Base{
    private static final long serialVersionUID = 1L;
    
    private String n_anio;
    private String n_mes;
    private Date f_fecha;
    
    private String c_fondo_id;
    private String c_fondo;
    
    private String c_inversion;
    
    private Date f_deposito;
    private Double i_deposito;
    
    private Date f_deposito_ant;
    private Double i_deposito_ant;
    
    private String c_gestor;
    private String c_asesor;
    private String moneda;

   
    
    private List<CobCronogramaRecaudoResumen> cobCronogramaRecaudoResumenList;
    

    public String getN_anio() {
        return n_anio;
    }

    public void setN_anio(String n_anio) {
        this.n_anio = n_anio;
    }

    public String getN_mes() {
        return n_mes;
    }

    public void setN_mes(String n_mes) {
        this.n_mes = n_mes;
    }

    public Date getF_fecha() {
        return f_fecha;
    }

    public void setF_fecha(Date f_fecha) {
        this.f_fecha = f_fecha;
    }

    public String getC_fondo_id() {
        return c_fondo_id;
    }

    public void setC_fondo_id(String c_fondo_id) {
        this.c_fondo_id = c_fondo_id;
    }

    public String getC_fondo() {
        return c_fondo;
    }

    public void setC_fondo(String c_fondo) {
        this.c_fondo = c_fondo;
    }

    public String getC_inversion() {
        return c_inversion;
    }

    public void setC_inversion(String c_inversion) {
        this.c_inversion = c_inversion;
    }

    public Date getF_deposito() {
        return f_deposito;
    }

    public void setF_deposito(Date f_deposito) {
        this.f_deposito = f_deposito;
    }

    public Double getI_deposito() {
        return i_deposito;
    }

    public void setI_deposito(Double i_deposito) {
        this.i_deposito = i_deposito;
    }

    public Date getF_deposito_ant() {
        return f_deposito_ant;
    }

    public void setF_deposito_ant(Date f_deposito_ant) {
        this.f_deposito_ant = f_deposito_ant;
    }

    public Double getI_deposito_ant() {
        return i_deposito_ant;
    }

    public void setI_deposito_ant(Double i_deposito_ant) {
        this.i_deposito_ant = i_deposito_ant;
    }

    public String getC_gestor() {
        return c_gestor;
    }

    public void setC_gestor(String c_gestor) {
        this.c_gestor = c_gestor;
    }

    public String getC_asesor() {
        return c_asesor;
    }

    public void setC_asesor(String c_asesor) {
        this.c_asesor = c_asesor;
    }
    
    public List<CobCronogramaRecaudoResumen> getCobCronogramaRecaudoResumenList() {
        return cobCronogramaRecaudoResumenList;
    }

    public void setCobCronogramaRecaudoResumenList(List<CobCronogramaRecaudoResumen> cobCronogramaRecaudoResumenList) {
        this.cobCronogramaRecaudoResumenList = cobCronogramaRecaudoResumenList;
    }    
 
     public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
