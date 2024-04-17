/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaAgrupxFecha  extends Base {
    private static final long serialVersionUID = 1L;
    
    private String Fecha;
    private String NroCodigos;
    private String CuotaMes;
    private String TotalAtrasado;
    private String n_anio;
    private String n_mes;
    private String c_fondo_id;
    private String c_usuario_id;
    private String moneda;

    
 
   /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the NroCodigos
     */
    public String getNroCodigos() {
        return NroCodigos;
    }

    /**
     * @param NroCodigos the NroCodigos to set
     */
    public void setNroCodigos(String NroCodigos) {
        this.NroCodigos = NroCodigos;
    }

    /**
     * @return the CuotaMes
     */
    public String getCuotaMes() {
        return CuotaMes;
    }

    /**
     * @param CuotaMes the CuotaMes to set
     */
    public void setCuotaMes(String CuotaMes) {
        this.CuotaMes = CuotaMes;
    }

    /**
     * @return the TotalAtrasado
     */
    public String getTotalAtrasado() {
        return TotalAtrasado;
    }

    /**
     * @param TotalAtrasado the TotalAtrasado to set
     */
    public void setTotalAtrasado(String TotalAtrasado) {
        this.TotalAtrasado = TotalAtrasado;
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

    @Override
    public String toString() {
        return "CobCronogramaMetaAgrupxFecha{" + "Fecha=" + Fecha + ", NroCodigos=" + NroCodigos + ", CuotaMes=" + CuotaMes + ", TotalAtrasado=" + TotalAtrasado + ", n_anio=" + n_anio + ", n_mes=" + n_mes + ", c_fondo_id=" + c_fondo_id + ", c_usuario_id=" + c_usuario_id + '}';
    }
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
