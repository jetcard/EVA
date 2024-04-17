/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;


/**
 *
 * @author Jyoverar
 */
public class MaePersonaInmueble extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cPerInmuebleId;
    private Number cPersonaId;
    private Number cInmuebleId;
    private String cFondoId;
    private Number pInmueble;
    
    private MaePersona maePersona;
    private MaeInmueble maeInmueble;

    public MaePersonaInmueble() {
    }

    public MaePersonaInmueble(Number cPerInmuebleId) {
        this.cPerInmuebleId = cPerInmuebleId;
    }

    public MaePersonaInmueble(Number cPerInmuebleId, String cFondoId) {
        this.cPerInmuebleId = cPerInmuebleId;
        this.cFondoId = cFondoId;
    }

    public Number getCPerInmuebleId() {
        return cPerInmuebleId;
    }

    public void setCPerInmuebleId(Number cPerInmuebleId) {
        this.cPerInmuebleId = cPerInmuebleId;
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public Number getPInmueble() {
        return pInmueble;
    }

    public void setPInmueble(Number pInmueble) {
        this.pInmueble = pInmueble;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cPerInmuebleId != null ? cPerInmuebleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaePersonaInmueble)) {
            return false;
        }
        MaePersonaInmueble other = (MaePersonaInmueble) object;
        if ((this.cPerInmuebleId == null && other.cPerInmuebleId != null) || (this.cPerInmuebleId != null && !this.cPerInmuebleId.equals(other.cPerInmuebleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaePersonaInmueble[ cPerInmuebleId=" + cPerInmuebleId + " ]";
    }

    /**
     * @return the cPersonaId
     */
    public Number getcPersonaId() {
        return cPersonaId;
    }

    /**
     * @param cPersonaId the cPersonaId to set
     */
    public void setcPersonaId(Number cPersonaId) {
        this.cPersonaId = cPersonaId;
    }

    /**
     * @return the cInmuebleId
     */
    public Number getcInmuebleId() {
        return cInmuebleId;
    }

    /**
     * @param cInmuebleId the cInmuebleId to set
     */
    public void setcInmuebleId(Number cInmuebleId) {
        this.cInmuebleId = cInmuebleId;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }
    
    
    
}
