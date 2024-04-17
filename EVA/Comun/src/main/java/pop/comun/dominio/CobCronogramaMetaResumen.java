/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;
import pop.comun.dominio.Base;
import pop.comun.dominio.CobCronogramaMetaResumen;

/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaResumen extends Base{
    private static final long serialVersionUID = 1L;

    private String n_anio;
    private String n_mes;
    private String c_fondo_id;
    private String c_fondo_desc;
    private String c_usuario_id;
    private String judicial;
    private String n_cant_tchn;
    private double i_cuota;
    private double i_total_atrasado;
    private double i_total_otros;
    private double i_total;
    private double i_porcentajeAsesor;
    private String v_cuota;
    private String v_total_atrasado;
    private String v_total_otros;
    private String v_total;
    private String v_porcentajeAsesor;
    private String e_estado;
    private String c_usuario_add;
    private String f_usuario_add;
    private String c_usuario_mod;
    private String f_usuario_mod;
    private String c_estado_cron;
    private Date f_fecha_id;
    private String n_quincena;
    private String c_est_tchn;
    private String moneda;

    
    private List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList;
    
    private List<CobCronogramaMetaResumen> cobCronogramaMetaFondoList;

    public CobCronogramaMetaResumen() {
    }
    
    /**
     * @return the n_anio
     */
    public String getN_anio() {
        return n_anio;
    }

    /**
     * @param n_anio the n_anio to set
     */
    public void setN_anio(String n_anio) {
        this.n_anio = n_anio;
    }

    /**
     * @return the n_mes
     */
    public String getN_mes() {
        return n_mes;
    }

    /**
     * @param n_mes the n_mes to set
     */
    public void setN_mes(String n_mes) {
        this.n_mes = n_mes;
    }

    /**
     * @return the c_fondo_id
     */
    public String getC_fondo_id() {
        return c_fondo_id;
    }

    /**
     * @param c_fondo_id the c_fondo_id to set
     */
    public void setC_fondo_id(String c_fondo_id) {
        this.c_fondo_id = c_fondo_id;
    }

    /**
     * @return the c_usuario_id
     */
    public String getC_usuario_id() {
        return c_usuario_id;
    }

    /**
     * @param c_usuario_id the c_usuario_id to set
     */
    public void setC_usuario_id(String c_usuario_id) {
        this.c_usuario_id = c_usuario_id;
    }

    /**
     * @return the n_cant_tchn
     */
    public String getN_cant_tchn() {
        return n_cant_tchn;
    }

    /**
     * @param n_cant_tchn the n_cant_tchn to set
     */
    public void setN_cant_tchn(String n_cant_tchn) {
        this.n_cant_tchn = n_cant_tchn;
    }

  

    /**
     * @return the e_estado
     */
    public String getE_estado() {
        return e_estado;
    }

    /**
     * @param e_estado the e_estado to set
     */
    public void setE_estado(String e_estado) {
        this.e_estado = e_estado;
    }

    /**
     * @return the c_usuario_add
     */
    public String getC_usuario_add() {
        return c_usuario_add;
    }

    /**
     * @param c_usuario_add the c_usuario_add to set
     */
    public void setC_usuario_add(String c_usuario_add) {
        this.c_usuario_add = c_usuario_add;
    }

    /**
     * @return the f_usuario_add
     */
    public String getF_usuario_add() {
        return f_usuario_add;
    }

    /**
     * @param f_usuario_add the f_usuario_add to set
     */
    public void setF_usuario_add(String f_usuario_add) {
        this.f_usuario_add = f_usuario_add;
    }

    /**
     * @return the c_usuario_mod
     */
    public String getC_usuario_mod() {
        return c_usuario_mod;
    }

    /**
     * @param c_usuario_mod the c_usuario_mod to set
     */
    public void setC_usuario_mod(String c_usuario_mod) {
        this.c_usuario_mod = c_usuario_mod;
    }

    /**
     * @return the f_usuario_mod
     */
    public String getF_usuario_mod() {
        return f_usuario_mod;
    }

    /**
     * @param f_usuario_mod the f_usuario_mod to set
     */
    public void setF_usuario_mod(String f_usuario_mod) {
        this.f_usuario_mod = f_usuario_mod;
    }    

   /**
     * @return the i_cuota
     */
    public double getI_cuota() {
        return i_cuota;
    }

    /**
     * @param i_cuota the i_cuota to set
     */
    public void setI_cuota(double i_cuota) {
        this.i_cuota = i_cuota;
    }

    /**
     * @return the i_total_atrasado
     */
    public double getI_total_atrasado() {
        return i_total_atrasado;
    }

    /**
     * @param i_total_atrasado the i_total_atrasado to set
     */
    public void setI_total_atrasado(double i_total_atrasado) {
        this.i_total_atrasado = i_total_atrasado;
    }

    /**
     * @return the i_total_otros
     */
    public double getI_total_otros() {
        return i_total_otros;
    }

    /**
     * @param i_total_otros the i_total_otros to set
     */
    public void setI_total_otros(double i_total_otros) {
        this.i_total_otros = i_total_otros;
    }

    /**
     * @return the i_total
     */
    public double getI_total() {
        return i_total;
    }

    /**
     * @param i_total the i_total to set
     */
    public void setI_total(double i_total) {
        this.i_total = i_total;
    }
 
   /**
     * @return the i_porcentajeAsesor
     */
    public double getI_porcentajeAsesor() {
        return i_porcentajeAsesor;
    }

    /**
     * @param i_porcentajeAsesor the i_porcentajeAsesor to set
     */
    public void setI_porcentajeAsesor(double i_porcentajeAsesor) {
        this.i_porcentajeAsesor = i_porcentajeAsesor;
    }    

    /**
     * @return the c_fondo_desc
     */
    public String getC_fondo_desc() {
        return c_fondo_desc;
    }

    /**
     * @param c_fondo_desc the c_fondo_desc to set
     */
    public void setC_fondo_desc(String c_fondo_desc) {
        this.c_fondo_desc = c_fondo_desc;
    }
    
    /**
     * @return the judicial
     */
    public String getJudicial() {
        return judicial;
    }

    /**
     * @param judicial the judicial to set
     */
    public void setJudicial(String judicial) {
        this.judicial = judicial;
    }    
    
    
            
    /**
     * @return the v_cuota
     */
    public String getV_cuota() {
        return v_cuota;
    }

    /**
     * @param v_cuota the v_cuota to set
     */
    public void setV_cuota(String v_cuota) {
        this.v_cuota = v_cuota;
    }
    
    /**
     * @return the v_total_atrasado;

     */
    public String getV_total_atrasado() {
        return v_total_atrasado;
    }

    /**
     * @param v_total_atrasado the v_total_atrasado to set
     */
    public void setV_total_atrasado(String v_total_atrasado) {
        this.v_total_atrasado = v_total_atrasado;
    }
    
    
            
    /**
     * @return the v_total_otros;

     */
    public String getV_total_otros() {
        return v_total_otros;
    }

    /**
     * @param v_total_otros the v_total_otros to set
     */
    public void setV_total_otros(String v_total_otros) {
        this.v_total_otros = v_total_otros;
    }
            
            
    /**
     * @return the v_total
     */
    public String getV_total() {
        return v_total;
    }

    /**
     * @param v_total the v_total to set
     */
    public void setV_total(String v_total) {
        this.v_total = v_total;
    }

    /**
     * @return the v_porcentajeAsesor
     */
    public String getV_porcentajeAsesor() {
        return v_porcentajeAsesor;
    }

    /**
     * @param v_porcentajeAsesor the v_porcentajeAsesor to set
     */
    public void setV_porcentajeAsesor(String v_porcentajeAsesor) {
        this.v_porcentajeAsesor = v_porcentajeAsesor;
    }

    /**
     * @return the c_estado_cron
     */
    public String getC_estado_cron() {
        return c_estado_cron;
    }

    /**
     * @param c_estado_cron the c_estado_cron to set
     */
    public void setC_estado_cron(String c_estado_cron) {
        this.c_estado_cron = c_estado_cron;
    }
    
    /**
     * @return the f_fecha_id
     */
    public Date getF_fecha_id() {
        return f_fecha_id;
    }

    /**
     * @param f_fecha_id the f_fecha_id to set
     */
    public void setF_fecha_id(Date f_fecha_id) {
        this.f_fecha_id = f_fecha_id;
    }
    
    
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaResumenList() {
        return cobCronogramaMetaResumenList;
    }

    public void setCronogramaMetaResumenList(List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList) {
        this.cobCronogramaMetaResumenList = cobCronogramaMetaResumenList;
    }
        
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaFondoList() {
        return cobCronogramaMetaFondoList;
    }

    public void setCobCronogramaMetaFondoList(List<CobCronogramaMetaResumen> cobCronogramaMetaFondoList) {
        this.cobCronogramaMetaFondoList = cobCronogramaMetaFondoList;
    }
    
    public String getN_quincena() {
        return n_quincena;
    }

    public void setN_quincena(String n_quincena) {
        this.n_quincena = n_quincena;
    }
    
    public String getC_est_tchn() {
        return c_est_tchn;
    }

    public void setC_est_tchn(String c_est_tchn) {
        this.c_est_tchn = c_est_tchn;
    }
    
    @Override
    public String toString() {
        return "CobCronogramaMetaResumen{" + "n_anio=" + n_anio + ", n_mes=" + n_mes + ", c_fondo_id=" + c_fondo_id + ", c_fondo_desc=" + c_fondo_desc + ", c_usuario_id=" + c_usuario_id + ", judicial=" + judicial + ", n_cant_tchn=" + n_cant_tchn + ", i_cuota=" + i_cuota + ", i_total_atrasado=" + i_total_atrasado + ", i_total_otros=" + i_total_otros + ", i_total=" + i_total + ", i_porcentajeAsesor=" + i_porcentajeAsesor + ", v_total=" + v_total + ", v_porcentajeAsesor=" + v_porcentajeAsesor + ", e_estado=" + e_estado + ", c_usuario_add=" + c_usuario_add + ", f_usuario_add=" + f_usuario_add + ", c_usuario_mod=" + c_usuario_mod + ", f_usuario_mod=" + f_usuario_mod + '}';
    }
    
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
