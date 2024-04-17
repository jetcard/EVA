/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.List;


/**
 *
 * @author Jyoverar
 */
public class MaeFondo extends Base{

    private static final long serialVersionUID = 1L;
    
    private String cFondoId;
    private String dFondo;
    private String aDireccion;
    
    private List<MaeInversion> maeInversionList;
    private List<MaeBanco> maeBancoList;
    
       
    public MaeFondo() {
    }

    public MaeFondo(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public String getDFondo() {
        return dFondo;
    }

    public void setDFondo(String dFondo) {
        this.dFondo = dFondo;
    }

    public String getADireccion() {
        return aDireccion;
    }

    public void setADireccion(String aDireccion) {
        this.aDireccion = aDireccion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cFondoId != null ? cFondoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeFondo)) {
            return false;
        }
        MaeFondo other = (MaeFondo) object;
        if ((this.cFondoId == null && other.cFondoId != null) || (this.cFondoId != null && !this.cFondoId.equals(other.cFondoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeFondo[ cFondoId=" + cFondoId + " ]";
    }

    /**
     * @return the maeInversionList
     */
    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    /**
     * @param maeInversionList the maeInversionList to set
     */
    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public List<MaeBanco> getMaeBancoList() {
        return maeBancoList;
    }

    public void setMaeBancoList(List<MaeBanco> maeBancoList) {
        this.maeBancoList = maeBancoList;
    }
    
}
