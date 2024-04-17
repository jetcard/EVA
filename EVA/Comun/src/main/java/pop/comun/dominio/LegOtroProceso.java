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
public class LegOtroProceso extends Base{
    private int idLegOtroProceso;
    private String codigoTCHN;
    private String fondo;
    private String estado;
    private String materia;
    private String tipo;
    private String nroexp;
    private String organocompetente;
    private String especialista;
    private Date fecha;
    private String descripcion;
    private String DescEstado;
    private String descripcionMateria;
    private String descripcionTipo;
    private String nroDocumento;
    private String DescFondo;
    private String nombres;
    private String apellidoPat;
    private String apellidoMat;
    private int numeroSeguimientos;

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
     * @return the codigoTCHN
     */
    public String getCodigoTCHN() {
        return codigoTCHN;
    }

    /**
     * @param codigoTCHN the codigoTCHN to set
     */
    public void setCodigoTCHN(String codigoTCHN) {
        this.codigoTCHN = codigoTCHN;
    }

    /**
     * @return the fondo
     */
    public String getFondo() {
        return fondo;
    }

    /**
     * @param fondo the fondo to set
     */
    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the materia
     */
    public String getMateria() {
        return materia;
    }

    /**
     * @param materia the materia to set
     */
    public void setMateria(String materia) {
        this.materia = materia;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nroexp
     */
    public String getNroexp() {
        return nroexp;
    }

    /**
     * @param nroexp the nroexp to set
     */
    public void setNroexp(String nroexp) {
        this.nroexp = nroexp;
    }

    /**
     * @return the organocompetente
     */
    public String getOrganocompetente() {
        return organocompetente;
    }

    /**
     * @param organocompetente the organocompetente to set
     */
    public void setOrganocompetente(String organocompetente) {
        this.organocompetente = organocompetente;
    }

    /**
     * @return the especialista
     */
    public String getEspecialista() {
        return especialista;
    }

    /**
     * @param especialista the especialista to set
     */
    public void setEspecialista(String especialista) {
        this.especialista = especialista;
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
     * @return the DescEstado
     */
    public String getDescEstado() {
        return DescEstado;
    }

    /**
     * @param DescEstado the DescEstado to set
     */
    public void setDescEstado(String DescEstado) {
        this.DescEstado = DescEstado;
    }

    /**
     * @return the nroDocumento
     */
    public String getNroDocumento() {
        return nroDocumento;
    }

    /**
     * @param nroDocumento the nroDocumento to set
     */
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    /**
     * @return the DescFondo
     */
    public String getDescFondo() {
        return DescFondo;
    }

    /**
     * @param DescFondo the DescFondo to set
     */
    public void setDescFondo(String DescFondo) {
        this.DescFondo = DescFondo;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidoPat
     */
    public String getApellidoPat() {
        return apellidoPat;
    }

    /**
     * @param apellidoPat the apellidoPat to set
     */
    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    /**
     * @return the apellidoMat
     */
    public String getApellidoMat() {
        return apellidoMat;
    }

    /**
     * @param apellidoMat the apellidoMat to set
     */
    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    /**
     * @return the numeroSeguimientos
     */
    public int getNumeroSeguimientos() {
        return numeroSeguimientos;
    }

    /**
     * @param numeroSeguimientos the numeroSeguimientos to set
     */
    public void setNumeroSeguimientos(int numeroSeguimientos) {
        this.numeroSeguimientos = numeroSeguimientos;
    }

    public String getDescripcionMateria() {
        return descripcionMateria;
    }

    public void setDescripcionMateria(String descripcionMateria) {
        this.descripcionMateria = descripcionMateria;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }
    
        
    /*@Override
    public String toString() {
        return "LegOtroProceso{" + "idLegOtroProceso=" + idLegOtroProceso + ", codigoTCHN=" + codigoTCHN + ", fondo=" + fondo + ", estado=" + estado + ", materia=" + materia + ", tipo=" + tipo + ", nroexp=" + nroexp + ", organocompetente=" + organocompetente + ", especialista=" + especialista + ", fecha=" + fecha + ", descripcion=" + descripcion + ", DescEstado=" + DescEstado + ", DescMateria=" + DescMateria + ", DescTipo=" + DescTipo + ", nroDocumento=" + nroDocumento + ", DescFondo=" + DescFondo + ", nombres=" + nombres + ", apellidoPat=" + apellidoPat + ", apellidoMat=" + apellidoMat + ", numeroSeguimientos=" + numeroSeguimientos + '}';
    }   */
}
