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
public class MaeDireccion extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cDireccionId;
    private int cPersonaId;
    private String cFondoId;
    private String cUbigueoId;
    private String ctipoDirId;
    private String cTipoDir1;
    private String dDir1;
    private int nDir1;
    private String aDir1;
    private String cTipoDir2;
    private String dDir2;
    private int nDir2;
    private String aDir2;
    private String dreferencia;
    private String dlatitud;
    private String dlongitud;
    private String bDefault;
    private String aEstado;
    
    private MaeUbigeo maeUbigeo;
    private MaePersona maePersona;
   
    public MaeDireccion() {
    }

    public int getCDireccionId() {
        return cDireccionId;
    }

    public void setCDireccionId(int cDireccionId) {
        this.cDireccionId = cDireccionId;
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public String getCUbigueoId() {
        return cUbigueoId;
    }

    public void setCUbigueoId(String cUbigueoId) {
        this.cUbigueoId = cUbigueoId;
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

    public int getNDir1() {
        return nDir1;
    }

    public void setNDir1(int nDir1) {
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

    public int getNDir2() {
        return nDir2;
    }

    public void setNDir2(int nDir2) {
        this.nDir2 = nDir2;
    }

    public String getADir2() {
        return aDir2;
    }

    public void setADir2(String aDir2) {
        this.aDir2 = aDir2;
    }

    public String getBDefault() {
        return bDefault;
    }

    public void setBDefault(String bDefault) {
        this.bDefault = bDefault;
    }

    public String getAEstado() {
        return aEstado;
    }

    public void setAEstado(String aEstado) {
        this.aEstado = aEstado;
    }

    

    public int getCPersonaId() {
        return cPersonaId;
    }

    public void setCPersonaId(int cPersonaId) {
        this.cPersonaId = cPersonaId;
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

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public String getCtipoDirId() {
        return ctipoDirId;
    }

    public void setCtipoDirId(String ctipoDirId) {
        this.ctipoDirId = ctipoDirId;
    }

    public String getDreferencia() {
        return dreferencia;
    }

    public void setDreferencia(String dreferencia) {
        this.dreferencia = dreferencia;
    }

    public String getDlatitud() {
        return dlatitud;
    }

    public void setDlatitud(String dlatitud) {
        this.dlatitud = dlatitud;
    }

    public String getDlongitud() {
        return dlongitud;
    }

    public void setDlongitud(String dlongitud) {
        this.dlongitud = dlongitud;
    }
    
}
