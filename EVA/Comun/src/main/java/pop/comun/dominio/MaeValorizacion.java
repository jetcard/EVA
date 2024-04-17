/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class MaeValorizacion extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cValorizacionId;
    private Number cInmuebleId;
    private String cFondoId;
    private Number cPeritoId;
    private String cInforme;
    private String cConservId;
    private String cServiciosId;
    private Number nNiveles;
    private Number iTipoCambio;
    private Number iValTerreno;
    private Number iValEdificacion;
    private Number iValReposicion;
    private Number iValComercial;
    private Number iValRealizacion;
    private Date fRealizacion;
    

    public MaeValorizacion() {
    }

    public MaeValorizacion(Number cValorizacionId) {
        this.cValorizacionId = cValorizacionId;
    }

    public Number getCValorizacionId() {
        return cValorizacionId;
    }

    public void setCValorizacionId(Number cValorizacionId) {
        this.cValorizacionId = cValorizacionId;
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public Number getCPeritoId() {
        return cPeritoId;
    }

    public void setCPeritoId(Number cPeritoId) {
        this.cPeritoId = cPeritoId;
    }

    public String getCInforme() {
        return cInforme;
    }

    public void setCInforme(String cInforme) {
        this.cInforme = cInforme;
    }

    public String getCConservId() {
        return cConservId;
    }

    public void setCConservId(String cConservId) {
        this.cConservId = cConservId;
    }

    public String getCServiciosId() {
        return cServiciosId;
    }

    public void setCServiciosId(String cServiciosId) {
        this.cServiciosId = cServiciosId;
    }

    public Number getNNiveles() {
        return nNiveles;
    }

    public void setNNiveles(Number nNiveles) {
        this.nNiveles = nNiveles;
    }

    public Number getITipoCambio() {
        return iTipoCambio;
    }

    public void setITipoCambio(Number iTipoCambio) {
        this.iTipoCambio = iTipoCambio;
    }

    public Number getIValTerreno() {
        return iValTerreno;
    }

    public void setIValTerreno(Number iValTerreno) {
        this.iValTerreno = iValTerreno;
    }

    public Number getIValEdificacion() {
        return iValEdificacion;
    }

    public void setIValEdificacion(Number iValEdificacion) {
        this.iValEdificacion = iValEdificacion;
    }

    public Number getIValReposicion() {
        return iValReposicion;
    }

    public void setIValReposicion(Number iValReposicion) {
        this.iValReposicion = iValReposicion;
    }

    public Number getIValComercial() {
        return iValComercial;
    }

    public void setIValComercial(Number iValComercial) {
        this.iValComercial = iValComercial;
    }

    public Number getIValRealizacion() {
        return iValRealizacion;
    }

    public void setIValRealizacion(Number iValRealizacion) {
        this.iValRealizacion = iValRealizacion;
    }

    public Date getFRealizacion() {
        return fRealizacion;
    }

    public void setFRealizacion(Date fRealizacion) {
        this.fRealizacion = fRealizacion;
    }

    public Number getCInmuebleId() {
        return cInmuebleId;
    }

    public void setCInmuebleId(Number cInmuebleId) {
        this.cInmuebleId = cInmuebleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cValorizacionId != null ? cValorizacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeValorizacion)) {
            return false;
        }
        MaeValorizacion other = (MaeValorizacion) object;
        if ((this.cValorizacionId == null && other.cValorizacionId != null) || (this.cValorizacionId != null && !this.cValorizacionId.equals(other.cValorizacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeValorizacion[ cValorizacionId=" + cValorizacionId + " ]";
    }
    
}
