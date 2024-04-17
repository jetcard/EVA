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
public class MaeUbigeo extends Base{

    private static final long serialVersionUID = 1L;
    
    private String cUbigeoId;
    private String cUbigeoPad;
    private String dUbigeo;
    private String dDUbigeoDist;
    private String dDUbigeoProv;
    private String dDUbigeoDep;
    
    private MaeUbigeo maeUbigeo;
    
    public MaeUbigeo() {
    }

    public MaeUbigeo(String cUbigeoId) {
        this.cUbigeoId = cUbigeoId;
    }

    public String getCUbigeoId() {
        return cUbigeoId;
    }

    public void setCUbigeoId(String cUbigeoId) {
        this.cUbigeoId = cUbigeoId;
    }

    public String getCUbigeoPad() {
        return cUbigeoPad;
    }

    public void setCUbigeoPad(String cUbigeoPad) {
        this.cUbigeoPad = cUbigeoPad;
    }

    public String getDUbigeo() {
        return dUbigeo;
    }

    public void setDUbigeo(String dUbigeo) {
        this.dUbigeo = dUbigeo;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cUbigeoId != null ? cUbigeoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeUbigeo)) {
            return false;
        }
        MaeUbigeo other = (MaeUbigeo) object;
        if ((this.cUbigeoId == null && other.cUbigeoId != null) || (this.cUbigeoId != null && !this.cUbigeoId.equals(other.cUbigeoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeUbigeo[ cUbigeoId=" + cUbigeoId + " ]";
    }

    /**
     * @return the dDUbigeoDist
     */
    public String getDDUbigeoDist() {
        return dDUbigeoDist;
    }

    /**
     * @param dDUbigeoDist the dDUbigeoDist to set
     */
    public void setDDUbigeoDist(String dDUbigeoDist) {
        this.dDUbigeoDist = dDUbigeoDist;
    }

    /**
     * @return the dDUbigeoProv
     */
    public String getDDUbigeoProv() {
        return dDUbigeoProv;
    }

    /**
     * @param dDUbigeoProv the dDUbigeoProv to set
     */
    public void setDDUbigeoProv(String dDUbigeoProv) {
        this.dDUbigeoProv = dDUbigeoProv;
    }

    /**
     * @return the dDUbigeoDep
     */
    public String getDDUbigeoDep() {
        return dDUbigeoDep;
    }

    /**
     * @param dDUbigeoDep the dDUbigeoDep to set
     */
    public void setDDUbigeoDep(String dDUbigeoDep) {
        this.dDUbigeoDep = dDUbigeoDep;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }
    
}
