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
import pop.comun.dominio.CobCronogramaRecaudoDetalle;

/**
 *
 * @author EC23329
 */
public class RepMetaRecaudo extends Base{
    
    private static final long serialVersionUID = 1L;
    
    private String n_anio;
    private String n_mes;
    private String c_fondo_id;
    private Integer i_posicion;
    private String c_concepto;    
    private String c_judicial;
    private String c_usuario;
    private double i_meta;
    private String moneda;

   
    private double i_recaudo;
    private double i_recaudo_ant;
    private double i_recaudo_ase;
    private double i_recaudo_cli;
    
    private List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList;
    private List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleJDList;
    private List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleNJList;
    
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

    public String getC_judicial() {
        return c_judicial;
    }

    public void setC_judicial(String c_judicial) {
        this.c_judicial = c_judicial;
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
   
     public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public List<CobCronogramaRecaudoDetalle> getCobCronogramaRecaudoDetalleList() {
        return cobCronogramaRecaudoDetalleList;
    }

    public void setCobCronogramaRecaudoDetalleList(List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleList) {
        this.cobCronogramaRecaudoDetalleList = cobCronogramaRecaudoDetalleList;
    }
    
    
    public List<CobCronogramaRecaudoDetalle> getCobCronogramaRecaudoDetalleJDList() {
        return cobCronogramaRecaudoDetalleJDList;
    }

    public void setCobCronogramaRecaudoDetalleJDList(List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleJDList) {
        this.cobCronogramaRecaudoDetalleJDList = cobCronogramaRecaudoDetalleJDList;
    }

    public List<CobCronogramaRecaudoDetalle> getCobCronogramaRecaudoDetalleNJList() {
        return cobCronogramaRecaudoDetalleNJList;
    }

    public void setCobCronogramaRecaudoDetalleNJList(List<CobCronogramaRecaudoDetalle> cobCronogramaRecaudoDetalleNJList) {
        this.cobCronogramaRecaudoDetalleNJList = cobCronogramaRecaudoDetalleNJList;
    }
}
