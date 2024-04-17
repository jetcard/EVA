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
public class MaeInversionEstado extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cInversionEstadoId;
    private String cFondoId;
    private String cInversionId;
    private String cTipo_inv;
    private Number cMaeInversionId;
    private String eEstadoId;
    

    public MaeInversionEstado() {
    }

    public MaeInversionEstado(Number cInversionEstadoId) {
        this.cInversionEstadoId = cInversionEstadoId;
    }

    public Number getCInversionEstadoId() {
        return cInversionEstadoId;
    }

    public void setCInversionEstadoId(Number cInversionEstadoId) {
        this.cInversionEstadoId = cInversionEstadoId;
    }

    public String getEEstadoId() {
        return eEstadoId;
    }

    public void setEEstadoId(String eEstadoId) {
        this.eEstadoId = eEstadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cInversionEstadoId != null ? cInversionEstadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeInversionEstado)) {
            return false;
        }
        MaeInversionEstado other = (MaeInversionEstado) object;
        if ((this.cInversionEstadoId == null && other.cInversionEstadoId != null) || (this.cInversionEstadoId != null && !this.cInversionEstadoId.equals(other.cInversionEstadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeInversionEstado[ cInversionEstadoId=" + cInversionEstadoId + " ]";
    }

    /**
     * @return the cFondoId
     */
    public String getcFondoId() {
        return cFondoId;
    }

    /**
     * @param cFondoId the cFondoId to set
     */
    public void setcFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    /**
     * @return the cInversionId
     */
    public String getcInversionId() {
        return cInversionId;
    }

    /**
     * @param cInversionId the cInversionId to set
     */
    public void setcInversionId(String cInversionId) {
        this.cInversionId = cInversionId;
    }

    /**
     * @return the cTipo_inv
     */
    public String getcTipo_inv() {
        return cTipo_inv;
    }

    /**
     * @param cTipo_inv the cTipo_inv to set
     */
    public void setcTipo_inv(String cTipo_inv) {
        this.cTipo_inv = cTipo_inv;
    }

    /**
     * @return the cMaeInversionId
     */
    public Number getcMaeInversionId() {
        return cMaeInversionId;
    }

    /**
     * @param cMaeInversionId the cMaeInversionId to set
     */
    public void setcMaeInversionId(Number cMaeInversionId) {
        this.cMaeInversionId = cMaeInversionId;
    }
    
    
}
