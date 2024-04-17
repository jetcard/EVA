/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio.reporte;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pop.comun.dominio.Base;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;

/**
 *
 * @author EC23329
 */
public class RepMetaCobranza extends Base{
    private static final long serialVersionUID = 1L;
    
    private String n_anio;
    private String n_mes;
    private String c_fondo_id;
    private String c_fondo;
    private String c_mae_inversion_id;
    private String c_inversion_id;
    private String c_inversion;
    private String n_quincena;
    private Date f_ejecucion;
    private Date f_vencimiento;
    private Date f_pago;
    private String judicial;
    private String n_cuota_atrasada;
    private double i_cuota;
    private double i_capital;
    private double i_interes;
    private double i_capital_atrasado;
    private double i_interes_atrasado;
    private double i_mora_atrasado;
    private double i_total_atrasado;
    private String c_estado_cron;
    private String c_usuario_id;
    private String n_dia;
    private String e_estado;
    private String c_usuario_add;
    private Date f_usuario_add;
    private String c_usuario_mod;
    private Date f_usuario_mod;
    private boolean selected;
        
    private List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaList;
    private List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList;
    private List<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleList;
    private List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaListJD;
    private List<CobCronogramaMetaResumen> cobCronogramaMetaResumenListJD;
    
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
     * @return the c_fondo
     */
    public String getC_fondo() {
        return c_fondo;
    }

    /**
     * @param c_fondo the c_fondo_id to set
     */
    public void setC_fondo(String c_fondo) {
        this.c_fondo = c_fondo;
    }


    /**
     * @return the c_mae_inversion_id
     */
    public String getC_mae_inversion_id() {
        return c_mae_inversion_id;
    }

    /**
     * @param c_mae_inversion_id the c_mae_inversion_id to set
     */
    public void setC_mae_inversion_id(String c_mae_inversion_id) {
        this.c_mae_inversion_id = c_mae_inversion_id;
    }

    /**
     * @return the c_inversion_id
     */
    public String getC_inversion_id() {
        return c_inversion_id;
    }

    /**
     * @param c_inversion_id the c_inversion_id to set
     */
    public void setC_inversion_id(String c_inversion_id) {
        this.c_inversion_id = c_inversion_id;
    }

    /**
     * @return the c_inversion
     */
    public String getC_inversion() {
        return c_inversion;
    }

    /**
     * @param c_inversion the c_inversion to set
     */
    public void setC_inversion(String c_inversion) {
        this.c_inversion = c_inversion;
    }

    /**
     * @return the n_quincena
     */
    public String getN_quincena() {
        return n_quincena;
    }

    /**
     * @param n_quincena the n_quincena to set
     */
    public void setN_quincena(String n_quincena) {
        this.n_quincena = n_quincena;
    }

    /**
     * @return the f_ejecucion
     */
    public Date getF_ejecucion() {
        return f_ejecucion;
    }

    /**
     * @param f_ejecucion the f_ejecucion to set
     */
    public void setF_ejecucion(Date f_ejecucion) {
        this.f_ejecucion = f_ejecucion;
    }

    /**
     * @return the f_vencimiento
     */
    public Date getF_vencimiento() {
        return f_vencimiento;
    }

    /**
     * @param f_vencimiento the f_vencimiento to set
     */
    public void setF_vencimiento(Date f_vencimiento) {
        this.f_vencimiento = f_vencimiento;
    }

    /**
     * @return the f_pago
     */
    public Date getF_pago() {
        return f_pago;
    }

    /**
     * @param f_pago the f_pago to set
     */
    public void setF_pago(Date f_pago) {
        this.f_pago = f_pago;
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
     * @return the n_cuota_atrasada
     */
    public String getN_cuota_atrasada() {
        return n_cuota_atrasada;
    }

    /**
     * @param n_cuota_atrasada the n_cuota_atrasada to set
     */
    public void setN_cuota_atrasada(String n_cuota_atrasada) {
        this.n_cuota_atrasada = n_cuota_atrasada;
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
     * @return the i_capital
     */
    public double getI_capital() {
        return i_capital;
    }

    /**
     * @param i_capital the i_capital to set
     */
    public void setI_capital(double i_capital) {
        this.i_capital = i_capital;
    }

    /**
     * @return the i_interes
     */
    public double getI_interes() {
        return i_interes;
    }

    /**
     * @param i_interes the i_interes to set
     */
    public void setI_interes(double i_interes) {
        this.i_interes = i_interes;
    }

    /**
     * @return the i_capital_atrasado
     */
    public double getI_capital_atrasado() {
        return i_capital_atrasado;
    }

    /**
     * @param i_capital_atrasado the i_capital_atrasado to set
     */
    public void setI_capital_atrasado(double i_capital_atrasado) {
        this.i_capital_atrasado = i_capital_atrasado;
    }

    /**
     * @return the i_interes_atrasado
     */
    public double getI_interes_atrasado() {
        return i_interes_atrasado;
    }

    /**
     * @param i_interes_atrasado the i_interes_atrasado to set
     */
    public void setI_interes_atrasado(double i_interes_atrasado) {
        this.i_interes_atrasado = i_interes_atrasado;
    }

    /**
     * @return the i_mora_atrasado
     */
    public double getI_mora_atrasado() {
        return i_mora_atrasado;
    }

    /**
     * @param i_mora_atrasado the i_mora_atrasado to set
     */
    public void setI_mora_atrasado(double i_mora_atrasado) {
        this.i_mora_atrasado = i_mora_atrasado;
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
     * @return the n_dia
     */
    public String getN_dia() {
        return n_dia;
    }

    /**
     * @param n_dia the n_dia to set
     */
    public void setN_dia(String n_dia) {
        this.n_dia = n_dia;
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
    public Date getF_usuario_add() {
        return f_usuario_add;
    }

    /**
     * @param f_usuario_add the f_usuario_add to set
     */
    public void setF_usuario_add(Date f_usuario_add) {
        this.f_usuario_add = f_usuario_add;
    }

    /**
     * @return the f_usuario_mod
     */
    public Date getF_usuario_mod() {
        return f_usuario_mod;
    }

    /**
     * @param f_usuario_mod the f_usuario_mod to set
     */
    public void setF_usuario_mod(Date f_usuario_mod) {
        this.f_usuario_mod = f_usuario_mod;
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
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<CobCronogramaMetaDetalle> getCobCronogramaMetaDetalleList() {
        return cobCronogramaMetaDetalleList;
    }

    public void setCobCronogramaMetaDetalleList(List<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleList) {
        this.cobCronogramaMetaDetalleList = cobCronogramaMetaDetalleList;
    }
    
    public List<CobCronogramaMetaAgrupxFecha> getCobCronogramaMetaAgrupxFechaList() {
        return cobCronogramaMetaAgrupxFechaList;
    }

    public void setCobCronogramaMetaAgrupxFechaList(List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaList) {
        this.cobCronogramaMetaAgrupxFechaList = cobCronogramaMetaAgrupxFechaList;
    }    
    
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaResumenList() {
        return cobCronogramaMetaResumenList;
    }

    public void setCobCronogramaMetaResumenList(List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList) {
        this.cobCronogramaMetaResumenList = cobCronogramaMetaResumenList;
    }
    
    public List<CobCronogramaMetaAgrupxFecha> getCobCronogramaMetaAgrupxFechaListJD() {
        return cobCronogramaMetaAgrupxFechaListJD;
    }

    public void setCobCronogramaMetaAgrupxFechaListJD(List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaListJD) {
        this.cobCronogramaMetaAgrupxFechaListJD = cobCronogramaMetaAgrupxFechaListJD;
    }    
    
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaResumenListJD() {
        return cobCronogramaMetaResumenListJD;
    }

    public void setCobCronogramaMetaResumenListJD(List<CobCronogramaMetaResumen> cobCronogramaMetaResumenListJD) {
        this.cobCronogramaMetaResumenListJD = cobCronogramaMetaResumenListJD;
    }
    
    @Override
    public String toString() {
        return "CobCronogramaMetaDetalle{" + "n_anio=" + n_anio + ", n_mes=" + n_mes + ", c_fondo_id=" + c_fondo_id + ", c_mae_inversion_id=" + c_mae_inversion_id + ", c_inversion_id=" + c_inversion_id + ", c_inversion=" + c_inversion + ", n_quincena=" + n_quincena + ", f_ejecucion=" + f_ejecucion + ", f_vencimiento=" + f_vencimiento + ", f_pago=" + f_pago + ", judicial=" + judicial + ", n_cuota_atrasada=" + n_cuota_atrasada + ", i_cuota=" + i_cuota + ", i_capital=" + i_capital + ", i_interes=" + i_interes + ", i_capital_atrasado=" + i_capital_atrasado + ", i_interes_atrasado=" + i_interes_atrasado + ", i_mora_atrasado=" + i_mora_atrasado + ", i_total_atrasado=" + i_total_atrasado + ", c_estado_cron=" + c_estado_cron + ", c_usuario_id=" + c_usuario_id + ", n_dia=" + n_dia + ", e_estado=" + e_estado + ", c_usuario_add=" + c_usuario_add + ", f_usuario_add=" + f_usuario_add + ", c_usuario_mod=" + c_usuario_mod + ", f_usuario_mod=" + f_usuario_mod + ", selected=" + selected + '}';
    }

}
