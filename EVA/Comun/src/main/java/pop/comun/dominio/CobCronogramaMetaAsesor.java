/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaAsesor extends Base{
    private static final long serialVersionUID = 1L;
    
    private String n_anio;
    private String n_mes;
    private String c_fondo_id;
    private String c_usuario_id;
    private String n_quincena;
    private Date f_inicio;
    private Date f_fin; 
    private String e_estado;
    private String c_usuario_add;
    private Date f_usuario_add;
    private String c_usuario_mod;
    private Date f_usuario_mod;
    private String moneda;

   
    

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
     * @return the f_inicio
     */
    public Date getF_inicio() {
        return f_inicio;
    }

    /**
     * @param f_inicio the f_inicio to set
     */
    public void setF_inicio(Date f_inicio) {
        this.f_inicio = f_inicio;
    }

    /**
     * @return the f_fin
     */
    public Date getF_fin() {
        return f_fin;
    }

    /**
     * @param f_fin the f_fin to set
     */
    public void setF_fin(Date f_fin) {
        this.f_fin = f_fin;
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
    public Date getF_usuario_mod() {
        return f_usuario_mod;
    }

    /**
     * @param f_usuario_mod the f_usuario_mod to set
     */
    public void setF_usuario_mod(Date f_usuario_mod) {
        this.f_usuario_mod = f_usuario_mod;
    }
    
    @Override
    public String toString() {
        return "CobCronogramaMetaAsesor{" + "n_anio=" + n_anio + ", n_mes=" + n_mes + ", c_fondo_id=" + c_fondo_id + ", c_usuario_id=" + c_usuario_id + ", n_quincena=" + n_quincena + ", f_inicio=" + f_inicio + ", f_fin=" + f_fin + ", e_estado=" + e_estado + ", c_usuario_add=" + c_usuario_add + ", f_usuario_add=" + f_usuario_add + ", c_usuario_mod=" + c_usuario_mod + ", f_usuario_mod=" + f_usuario_mod + '}';
    }   
    
     public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
