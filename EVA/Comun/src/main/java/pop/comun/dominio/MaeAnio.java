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
public class MaeAnio  extends Base{
    private static final long serialVersionUID = 1L;
    
    private String cAnioId;
    private String dAnio;

     /**
     * @return the cAnioId
     */
    public String getCAnioId() {
        return cAnioId;
    }

    /**
     * @param cAnioId the cAnioId to set
     */
    public void setCAnioId(String cAnioId) {
        this.cAnioId = cAnioId;
    }

    /**
     * @return the dAnio
     */
    public String getDAnio() {
        return dAnio;
    }

    /**
     * @param dAnio the dAnio to set
     */
    public void setDAnio(String dAnio) {
        this.dAnio = dAnio;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCAnioId() != null ? getCAnioId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeAnio)) {
            return false;
        }
        MaeAnio other = (MaeAnio) object;
        if ((this.getCAnioId() == null && other.getCAnioId() != null) || (this.getCAnioId() != null && !this.cAnioId.equals(other.cAnioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pop.comun.dominio.MaeAnio[ cAnioId=" + getCAnioId() + " ]";
    }    
    
}
