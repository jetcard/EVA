/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author EC23329
 */
public class CobCronogramaMetaCabecera extends Base{
    private static final long serialVersionUID = 1L;
    
    private Integer n_id_meta;
    private String n_anio;
    private String n_mes;
    private String c_aprobado;
    private Date f_fec_aprobado;
    private String c_usuario_aprobado;
        
    private String e_estado;
    private String c_usuario_add;
    private Date f_usuario_add;
    private String c_usuario_mod;
    private Date f_usuario_mod;

    
    /**
     * @return the n_anio
     */
    public Integer getN_id_meta() {
        return n_id_meta;
    }

    /**
     * @param n_id_meta the n_id_meta to set
     */
    public void setN_id_meta(Integer n_id_meta) {
        this.n_id_meta = n_id_meta;
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
     * @return the c_aprobado
     */
    public String getC_aprobado() {
        return c_aprobado;
    }

    /**
     * @param c_aprobado the c_aprobado to set
     */
    public void setC_aprobado(String c_aprobado) {
        this.c_aprobado = c_aprobado;
    }
    
    /**
     * @return the c_aprobado
     */
    public Date getF_fec_aprobado() {
        return f_fec_aprobado;
    }

    /**
     * @param f_fec_aprobado the f_fec_aprobado to set
     */
    public void setF_fec_aprobado(Date f_fec_aprobado) {
        this.f_fec_aprobado = f_fec_aprobado;
    }
        
    
    /**
     * @return the c_usuario_aprobado
     */
    public String getC_usuario_aprobado() {
        return c_usuario_aprobado;
    }

    /**
     * @param c_usuario_aprobado the c_usuario_aprobado to set
     */
    public void setC_usuario_aprobado(String c_usuario_aprobado) {
        this.c_usuario_aprobado = c_usuario_aprobado;
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
}
