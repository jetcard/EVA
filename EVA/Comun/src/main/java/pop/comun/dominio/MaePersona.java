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
public class MaePersona extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cPersonaId;
    private MaeFondo maeFondo;
    private Number cPersona;
    private String cTipoPersona;
    private String cTipoDocumento;
    private String aNroDocumento;
    private String cClasePersona;
    private String cSexoId;
    private String dApePat;
    private String dApeMat;
    private String dNombres;
    private String dRazonSocial;
    private String dNombreComercial;
    private String cNacionNaci;
    private String cNacionFructuacion;
    private Date fNacimiento;
    private String cEstadoCivil;
    private String cGradoInstruccion;
       
    private List<MaeDireccion> maeDireccionList;
    private List<MaeTelefono> maeTelefonoList;
    private List<MaePersonaInmueble> maePersonaInmuebleList;   
    private List<MaeInversion> maeInversionList;
    private List<MaeEmail> maeEmailList;
    private MaeEmail maeEmail;
    
    private MaeInversion maeInversion;
    
    private TabUsuario tabUsuario;
    
        
    public MaePersona() {
    }

    public Number getCPersona() {
        return cPersona;
    }

    public void setCPersona(Number cPersona) {
        this.cPersona = cPersona;
    }

    public String getCTipoPersona() {
        return cTipoPersona;
    }

    public void setCTipoPersona(String cTipoPersona) {
        this.cTipoPersona = cTipoPersona;
    }

    public String getCTipoDocumento() {
        return cTipoDocumento;
    }

    public void setCTipoDocumento(String cTipoDocumento) {
        this.cTipoDocumento = cTipoDocumento;
    }

    public String getANroDocumento() {
        return aNroDocumento;
    }

    public void setANroDocumento(String aNroDocumento) {
        this.aNroDocumento = aNroDocumento;
    }

    public String getCClasePersona() {
        return cClasePersona;
    }

    public void setCClasePersona(String cClasePersona) {
        this.cClasePersona = cClasePersona;
    }

    public String getDApePat() {
        return dApePat;
    }

    public void setDApePat(String dApePat) {
        this.dApePat = dApePat;
    }

    public String getDApeMat() {
        return dApeMat;
    }

    public void setDApeMat(String dApeMat) {
        this.dApeMat = dApeMat;
    }

    public String getDNombres() {
        return dNombres;
    }

    public void setDNombres(String dNombres) {
        this.dNombres = dNombres;
    }

    public String getDRazonSocial() {
        return dRazonSocial;
    }

    public void setDRazonSocial(String dRazonSocial) {
        this.dRazonSocial = dRazonSocial;
    }

    public String getDNombreComercial() {
        return dNombreComercial;
    }

    public void setDNombreComercial(String dNombreComercial) {
        this.dNombreComercial = dNombreComercial;
    }

    public String getCNacionNaci() {
        return cNacionNaci;
    }

    public void setCNacionNaci(String cNacionNaci) {
        this.cNacionNaci = cNacionNaci;
    }

    public String getCNacionFructuacion() {
        return cNacionFructuacion;
    }

    public void setCNacionFructuacion(String cNacionFructuacion) {
        this.cNacionFructuacion = cNacionFructuacion;
    }

    public Date getFNacimiento() {
        return fNacimiento;
    }

    public void setFNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getCEstadoCivil() {
        return cEstadoCivil;
    }

    public void setCEstadoCivil(String cEstadoCivil) {
        this.cEstadoCivil = cEstadoCivil;
    }

    public String getCGradoInstruccion() {
        return cGradoInstruccion;
    }

    public void setCGradoInstruccion(String cGradoInstruccion) {
        this.cGradoInstruccion = cGradoInstruccion;
    }
   
    public List<MaePersonaInmueble> getMaePersonaInmuebleList() {
        return maePersonaInmuebleList;
    }

    public void setMaePersonaInmuebleList(List<MaePersonaInmueble> maePersonaInmuebleList) {
        this.maePersonaInmuebleList = maePersonaInmuebleList;
    }

    
    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }
    
    public List<MaeDireccion> getMaeDireccionList() {
        return maeDireccionList;
    }

    public void setMaeDireccionList(List<MaeDireccion> maeDireccionList) {
        this.maeDireccionList = maeDireccionList;
    }

    
    public List<MaeTelefono> getMaeTelefonoList() {
        return maeTelefonoList;
    }

    public void setMaeTelefonoList(List<MaeTelefono> maeTelefonoList) {
        this.maeTelefonoList = maeTelefonoList;
    }

   
    /**
     * @return the maeFondo
     */
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    /**
     * @param maeFondo the maeFondo to set
     */
    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    /**
     * @return the cSexoId
     */
    public String getcSexoId() {
        return cSexoId;
    }

    /**
     * @param cSexoId the cSexoId to set
     */
    public void setcSexoId(String cSexoId) {
        this.cSexoId = cSexoId;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public List<MaeEmail> getMaeEmailList() {
        return maeEmailList;
    }

    public void setMaeEmailList(List<MaeEmail> maeEmailList) {
        this.maeEmailList = maeEmailList;
    }

    public MaeEmail getMaeEmail() {
        return maeEmail;
    }

    public void setMaeEmail(MaeEmail maeEmail) {
        this.maeEmail = maeEmail;
    }

    public int getCPersonaId() {
        return cPersonaId;
    }

    public void setCPersonaId(int cPersonaId) {
        this.cPersonaId = cPersonaId;
    }

    public TabUsuario getTabUsuario() {
        return tabUsuario;
    }

    public void setTabUsuario(TabUsuario tabUsuario) {
        this.tabUsuario = tabUsuario;
    }

}
