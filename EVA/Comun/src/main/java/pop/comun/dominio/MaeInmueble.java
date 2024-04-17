/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class MaeInmueble extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cInmuebleId;
    private Number cInmueble;
    private String cFondoId;
    private String cUbigeoId;
    private String cTipoDir1;
    private String dDir1;
    private Number nDir1;
    private String aDir1;
    private String cTipoDir2;
    private String dDir2;
    private Number nDir2;
    private String aDir2;
    private String ddir3;
    private String bAsignacion;
    private String dAnalisis;
    private String dOpinion;
    private String cConservId;
    private String cUsoId;
    private Number pAsigMax;
    private Date fAntiguedad;
    private MaeUbigeo maeUbigeo;
    
    private MaeInversion maeInversion;
    private MaeHipoteca maeHipoteca;
    
    private List<MaeValorizacion> maeValorizacionList;
    private List<MaePersonaInmueble> maePersonaInmuebleList;  

    public MaeInmueble() {
    }

    public MaeInmueble(Number cInmuebleId) {
        this.cInmuebleId = cInmuebleId;
    }

    public MaeInmueble(Number cInmuebleId, String cFondoId) {
        this.cInmuebleId = cInmuebleId;
        this.cFondoId = cFondoId;
    }

    public Number getCInmuebleId() {
        return cInmuebleId;
    }

    public void setCInmuebleId(Number cInmuebleId) {
        this.cInmuebleId = cInmuebleId;
    }

    public Number getCInmueble() {
        return cInmueble;
    }

    public void setCInmueble(Number cInmueble) {
        this.cInmueble = cInmueble;
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public String getCUbigeoId() {
        return cUbigeoId;
    }

    public void setCUbigeoId(String cUbigeoId) {
        this.cUbigeoId = cUbigeoId;
    }

    public String getCTipoDir1() {
        return cTipoDir1;
    }

    public void setCTipoDir1(String cTipoDir1) {
        this.cTipoDir1 = cTipoDir1;
    }

    public String getDDir1() {
        return dDir1;
    }

    public void setDDir1(String dDir1) {
        this.dDir1 = dDir1;
    }

    public Number getNDir1() {
        return nDir1;
    }

    public void setNDir1(Number nDir1) {
        this.nDir1 = nDir1;
    }

    public String getADir1() {
        return aDir1;
    }

    public void setADir1(String aDir1) {
        this.aDir1 = aDir1;
    }

    public String getCTipoDir2() {
        return cTipoDir2;
    }

    public void setCTipoDir2(String cTipoDir2) {
        this.cTipoDir2 = cTipoDir2;
    }

    public String getDDir2() {
        return dDir2;
    }

    public void setDDir2(String dDir2) {
        this.dDir2 = dDir2;
    }

    public Number getNDir2() {
        return nDir2;
    }

    public void setNDir2(Number nDir2) {
        this.nDir2 = nDir2;
    }

    public String getADir2() {
        return aDir2;
    }

    public void setADir2(String aDir2) {
        this.aDir2 = aDir2;
    }

    public String getBAsignacion() {
        return bAsignacion;
    }

    public void setBAsignacion(String bAsignacion) {
        this.bAsignacion = bAsignacion;
    }

    public String getDAnalisis() {
        return dAnalisis;
    }

    public void setDAnalisis(String dAnalisis) {
        this.dAnalisis = dAnalisis;
    }

    public String getDOpinion() {
        return dOpinion;
    }

    public void setDOpinion(String dOpinion) {
        this.dOpinion = dOpinion;
    }

    public String getCConservId() {
        return cConservId;
    }

    public void setCConservId(String cConservId) {
        this.cConservId = cConservId;
    }

    public String getCUsoId() {
        return cUsoId;
    }

    public void setCUsoId(String cUsoId) {
        this.cUsoId = cUsoId;
    }

    public Number getPAsigMax() {
        return pAsigMax;
    }

    public void setPAsigMax(Number pAsigMax) {
        this.pAsigMax = pAsigMax;
    }

    public Date getFAntiguedad() {
        return fAntiguedad;
    }

    public void setFAntiguedad(Date fAntiguedad) {
        this.fAntiguedad = fAntiguedad;
    }

    public List<MaeValorizacion> getMaeValorizacionList() {
        return maeValorizacionList;
    }

    public void setMaeValorizacionList(List<MaeValorizacion> maeValorizacionList) {
        this.maeValorizacionList = maeValorizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cInmuebleId != null ? cInmuebleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeInmueble)) {
            return false;
        }
        MaeInmueble other = (MaeInmueble) object;
        if ((this.cInmuebleId == null && other.cInmuebleId != null) || (this.cInmuebleId != null && !this.cInmuebleId.equals(other.cInmuebleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeInmueble[ cInmuebleId=" + cInmuebleId + " ]";
    }

    /**
     * @return the maeUbigeo
     */
    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    /**
     * @param maeUbigeo the maeUbigeo to set
     */
    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

   
    public List<MaePersonaInmueble> getMaePersonaInmuebleList() {
        return maePersonaInmuebleList;
    }

    public void setMaePersonaInmuebleList(List<MaePersonaInmueble> maePersonaInmuebleList) {
        this.maePersonaInmuebleList = maePersonaInmuebleList;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeHipoteca getMaeHipoteca() {
        return maeHipoteca;
    }

    public void setMaeHipoteca(MaeHipoteca maeHipoteca) {
        this.maeHipoteca = maeHipoteca;
    }

    public String getDdir3() {
        return ddir3;
    }

    public void setDdir3(String ddir3) {
        this.ddir3 = ddir3;
    }
    
    
    
}
