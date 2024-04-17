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
public class MaeTelefono extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cTelefonoId;
    private int cPersonaId;
    private String cFondoId;
    private String cTipoTel;
    private String DEScTipoTel;
    private String CUsoTel;

    public String getCUsoTel() {
        return CUsoTel;
    }

    public void setCUsoTel(String CUsoTel) {
        this.CUsoTel = CUsoTel;
    }

    public String getDEScTipoTel() {
        return DEScTipoTel;
    }

    public void setDEScTipoTel(String DEScTipoTel) {
        this.DEScTipoTel = DEScTipoTel;
    }
    private String aNumero;
    private int nAnexo;
    private String bDefault;
    private String msj;
    private String msjEstado;
    private boolean benvio;
    private String sEstado;
    private int NPrede;

    private boolean bPrede;

    

    private MaePersona maePersona; 

    public MaeTelefono() {
    }

    public int getCTelefonoId() {
        return cTelefonoId;
    }

    public void setCTelefonoId(int cTelefonoId) {
        this.cTelefonoId = cTelefonoId;
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public String getCTipoTel() {
        return cTipoTel;
    }

    public void setCTipoTel(String cTipoTel) {
        this.cTipoTel = cTipoTel;
    }

    public String getANumero() {
        return aNumero;
    }

    public void setANumero(String aNumero) {
        this.aNumero = aNumero;
    }

    public int getNAnexo() {
        return nAnexo;
    }

    public void setNAnexo(int nAnexo) {
        this.nAnexo = nAnexo;
    }

    public String getBDefault() {
        return bDefault;
    }

    public void setBDefault(String bDefault) {
        this.bDefault = bDefault;
    }

    public int getCPersonaId() {
        return cPersonaId;
    }

    public void setCPersonaId(int cPersonaId) {
        this.cPersonaId = cPersonaId;
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

       public String getSEstado() {
        return sEstado;
    }

    public void setSEstado(String sEstado) {
        this.sEstado = sEstado;
    }

    public boolean isBPrede() {
        return bPrede;
    }

    public void setBPrede(boolean bPrede) {
        this.bPrede = bPrede;
    }
 
  public int getNPrede() {
        return NPrede;
    }

    public void setNPrede(int NPrede) {
        this.NPrede = NPrede;
    }
  
}
