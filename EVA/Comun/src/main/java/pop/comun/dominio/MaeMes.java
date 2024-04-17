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
public class MaeMes  extends Base{
    private static final long serialVersionUID = 1L;
    
    private String cMesId;
    private String dMes;

    /**
     * @return the cMesId
     */
    public String getCMesId() {
        return cMesId;
    }

    /**
     * @param cMesId the cMesId to set
     */
    public void setCMesId(String cMesId) {
        this.cMesId = cMesId;
    }

    /**
     * @return the dMes
     */
    public String getDMes() {
        return dMes;
    }

    /**
     * @param dMes the dMes to set
     */
    public void setDMes(String dMes) {
        this.dMes = dMes;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCMesId() != null ? getCMesId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeAnio)) {
            return false;
        }
        MaeMes other = (MaeMes) object;
        if ((this.getCMesId() == null && other.getCMesId() != null) || (this.getCMesId() != null && !this.cMesId.equals(other.cMesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xpop.comun.dominio.MaeMes[ cMesId=" + getCMesId() + " ]";
    }        
}
