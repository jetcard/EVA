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
public class CobCronogramaMetaAvance extends Base{
    private static final long serialVersionUID = 1L;
    
    private String  n_anio;
    private String  n_mes;        
    private Date    f_fecha;
    private String  c_usuario_id;
    private Integer i_id;
    private String  c_concepto;    
    private double  i_total;
    private String  c_fondo_id;
    
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
     * @return the f_inicio
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
     * @return the i_id
     */
    public Integer getI_id() {
        return i_id;
    }

    /**
     * @param i_id the i_id to set
     */
    public void setI_id(Integer i_id) {
        this.i_id = i_id;
    }    
    
    /**
     * @return the c_usuario_id
     */
    public String getC_concepto() {
        return c_concepto;
    }

    /**
     * @param c_concepto the c_concepto to set
     */
    public void setC_concepto(String c_concepto) {
        this.c_concepto = c_concepto;
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
     * @param c_fondo_id the c_fondo_id to set
     */
    public String getC_fondo_id() {
        return c_fondo_id;
    }

    public void setC_fondo_id(String c_fondo_id) {
        this.c_fondo_id = c_fondo_id;
    }
}
