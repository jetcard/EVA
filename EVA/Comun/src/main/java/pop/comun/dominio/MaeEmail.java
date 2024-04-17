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
public class MaeEmail extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cemailId;
    private String ctipoMailId;
    private String demail;
    private String dusuario;
    private String dcontrasenia;
    private String ddominio;
    private String dsmtp;
    private int npuerto;
    private String bDefault;
    private String msj;
    private String msjEstado;
    private boolean benvio;
    private String dnombre;
    private String cfondoid;

   
    
    private MaePersona maePersona; 

    public int getCemailId() {
        return cemailId;
    }

    public void setCemailId(int cemailId) {
        this.cemailId = cemailId;
    }

    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }

    public String getDusuario() {
        return dusuario;
    }

    public void setDusuario(String dusuario) {
        this.dusuario = dusuario;
    }

    public String getDdominio() {
        return ddominio;
    }

    public void setDdominio(String ddominio) {
        this.ddominio = ddominio;
    }

    public String getDsmtp() {
        return dsmtp;
    }

    public void setDsmtp(String dsmtp) {
        this.dsmtp = dsmtp;
    }

    public int getNpuerto() {
        return npuerto;
    }

    public void setNpuerto(int npuerto) {
        this.npuerto = npuerto;
    }

    public String getbDefault() {
        return bDefault;
    }

    public void setbDefault(String bDefault) {
        this.bDefault = bDefault;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public boolean isBenvio() {
        return benvio;
    }

    public void setBenvio(boolean benvio) {
        this.benvio = benvio;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public String getMsjEstado() {
        return msjEstado;
    }

    public void setMsjEstado(String msjEstado) {
        this.msjEstado = msjEstado;
    }

    public String getDcontrasenia() {
        return dcontrasenia;
    }

    public void setDcontrasenia(String dcontrasenia) {
        this.dcontrasenia = dcontrasenia;
    }

    public String getCtipoMailId() {
        return ctipoMailId;
    }

    public void setCtipoMailId(String ctipoMailId) {
        this.ctipoMailId = ctipoMailId;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getCfondoid() {
        return cfondoid;
    }

    public void setCfondoid(String cfondoid) {
        this.cfondoid = cfondoid;
    }    
}
