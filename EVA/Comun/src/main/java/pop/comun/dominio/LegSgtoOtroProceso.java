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
public class LegSgtoOtroProceso extends Base{
    private int idLegSeguimiento;
    private int idLegOtroProceso;
    private Date fecha;
    private String descripcion;
    private String userRegistro;
    private String responsable;
    /**
     * @return the idLegSeguimiento
     */
    public int getIdLegSeguimiento() {
        return idLegSeguimiento;
    }

    /**
     * @param idLegSeguimiento the idLegSeguimiento to set
     */
    public void setIdLegSeguimiento(int idLegSeguimiento) {
        this.idLegSeguimiento = idLegSeguimiento;
    }

    /**
     * @return the idLegOtroProceso
     */
    public int getIdLegOtroProceso() {
        return idLegOtroProceso;
    }

    /**
     * @param idLegOtroProceso the idLegOtroProceso to set
     */
    public void setIdLegOtroProceso(int idLegOtroProceso) {
        this.idLegOtroProceso = idLegOtroProceso;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     /**
     * @return the userRegistro
     */
    public String getUserRegistro() {
        return userRegistro;
    }

    /**
     * @param userRegistro the userRegistro to set
     */
    public void setUserRegistro(String userRegistro) {
        this.userRegistro = userRegistro;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "LegSgtoOtroProceso{" + "idLegSeguimiento=" + idLegSeguimiento + ", idLegOtroProceso=" + idLegOtroProceso + ", fecha=" + fecha + ", descripcion=" + descripcion + ", userRegistro=" + userRegistro + ", responsable=" + responsable + '}';
    }
        
}
