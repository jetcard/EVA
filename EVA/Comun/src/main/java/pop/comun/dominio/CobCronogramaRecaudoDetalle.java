/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;
import pop.comun.dominio.Base;
/**
 *
 * @author EC23329
 */
public class CobCronogramaRecaudoDetalle extends Base{
    private static final long serialVersionUID = 1L;
    
    private String n_anio;
    private String n_mes;
    private Date f_fecha;
    
    private String c_fondo_id;
    private Integer i_posicion;
    private String c_concepto; 
    private String c_judicial;
    private String c_usuario;
    
    private double i_meta;
    private double i_recaudo;
    private double i_recaudo_ant;
    private double i_recaudo_ase;
    private double i_recaudo_cli;
    private String c_usuario_id;
    
    private double i_recaudo_judicial;
    private double i_recaudo_normal;
    
    private double i_cliente;
    private double i_cobranza;
    
    private int i_cantidad;
    private double  i_deposito;
    
    private int i_dias_avance;
    private int i_dias_recaudo;
    private String moneda;

    
    
    private List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList;
    
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
     * @return the f_fecha
     */
    public Date getF_fecha() {
        return f_fecha;
    }

    /**
     * @param f_fecha the f_fecha to set
     */
    public void setF_fecha(Date f_fecha) {
        this.f_fecha = f_fecha;
    }
    
    /**
     * @return the c_judicial
     */
    public String getC_judicial() {
        return c_judicial;
    }

    /**
     * @param c_judicial the c_judicial to set
     */
    public void setC_judicial(String c_judicial) {
        this.c_judicial = c_judicial;
    }
    
    
    public String getC_fondo_id() {
        return c_fondo_id;
    }

    public void setC_fondo_id(String c_fondo_id) {
        this.c_fondo_id = c_fondo_id;
    }

    public Integer getI_posicion() {
        return i_posicion;
    }

    public void setI_posicion(Integer i_posicion) {
        this.i_posicion = i_posicion;
    }

    public String getC_concepto() {
        return c_concepto;
    }

    public void setC_concepto(String c_concepto) {
        this.c_concepto = c_concepto;
    }

    public String getC_usuario() {
        return c_usuario;
    }

    public void setC_usuario(String c_usuario) {
        this.c_usuario = c_usuario;
    }

    public double getI_meta() {
        return i_meta;
    }

    public void setI_meta(double i_meta) {
        this.i_meta = i_meta;
    }

    public double getI_recaudo() {
        return i_recaudo;
    }

    public void setI_recaudo(double i_recaudo) {
        this.i_recaudo = i_recaudo;
    }

    public double getI_recaudo_ant() {
        return i_recaudo_ant;
    }

    public void setI_recaudo_ant(double i_recaudo_ant) {
        this.i_recaudo_ant = i_recaudo_ant;
    }

    public double getI_recaudo_ase() {
        return i_recaudo_ase;
    }

    public void setI_recaudo_ase(double i_recaudo_ase) {
        this.i_recaudo_ase = i_recaudo_ase;
    }

    public double getI_recaudo_cli() {
        return i_recaudo_cli;
    }

    public void setI_recaudo_cli(double i_recaudo_cli) {
        this.i_recaudo_cli = i_recaudo_cli;
    }

    
    public double getI_recaudo_judicial() {
        return i_recaudo_judicial;
    }

    public void setI_recaudo_judicial(double i_recaudo_judicial) {
        this.i_recaudo_judicial = i_recaudo_judicial;
    }

    public double getI_recaudo_normal() {
        return i_recaudo_normal;
    }

    public void setI_recaudo_normal(double i_recaudo_normal) {
        this.i_recaudo_normal = i_recaudo_normal;
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
    
    public double getI_cliente() {
        return i_cliente;
    }

    public void setI_cliente(double i_cliente) {
        this.i_cliente = i_cliente;
    }

    public double getI_cobranza() {
        return i_cobranza;
    }

    public void setI_cobranza(double i_cobranza) {
        this.i_cobranza = i_cobranza;
    }
    public int getI_cantidad() {
        return i_cantidad;
    }

    public void setI_cantidad(int i_cantidad) {
        this.i_cantidad = i_cantidad;
    }

    public double getI_deposito() {
        return i_deposito;
    }

    public void setI_deposito(double i_deposito) {
        this.i_deposito = i_deposito;
    }

    public int getI_dias_avance() {
        return i_dias_avance;
    }

    public void setI_dias_avance(int i_dias_avance) {
        this.i_dias_avance = i_dias_avance;
    }

    public int getI_dias_recaudo() {
        return i_dias_recaudo;
    }

    public void setI_dias_recaudo(int i_dias_recaudo) {
        this.i_dias_recaudo = i_dias_recaudo;
    }    
    
    public List<CobCronogramaRecaudoDetalle> getCobCronogramaRecaudoDetalleList() {
        return cobCronogramaRecaudoDetalleList;
    }

    public void setCobCronogramaRecaudoDetalleList(List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList) {
        this.cobCronogramaRecaudoDetalleList = cobCronogramaRecaudoDetalleList;
    }
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
