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
public class TabTipoDocumentoEstado extends Base{

    private static final long serialVersionUID = 1L;
    
    private int ctabTipDocEstId;
    private String dnombre;
    private TabTipoDocumento tabTipoDocumento;
    private int norden;
    private String bsoloLectura;
    private String bcargo;
    private String ctipoVisualzaId;

    public int getCtabTipDocEstId() {
        return ctabTipDocEstId;
    }

    public void setCtabTipDocEstId(int ctabTipDocEstId) {
        this.ctabTipDocEstId = ctabTipDocEstId;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public TabTipoDocumento getTabTipoDocumento() {
        return tabTipoDocumento;
    }

    public void setTabTipoDocumento(TabTipoDocumento tabTipoDocumento) {
        this.tabTipoDocumento = tabTipoDocumento;
    }

    public int getNorden() {
        return norden;
    }

    public void setNorden(int norden) {
        this.norden = norden;
    }

    public String getBsoloLectura() {
        return bsoloLectura;
    }

    public void setBsoloLectura(String bsoloLectura) {
        this.bsoloLectura = bsoloLectura;
    }

    public String getBcargo() {
        return bcargo;
    }

    public void setBcargo(String bcargo) {
        this.bcargo = bcargo;
    }

    public String getCtipoVisualzaId() {
        return ctipoVisualzaId;
    }

    public void setCtipoVisualzaId(String ctipoVisualzaId) {
        this.ctipoVisualzaId = ctipoVisualzaId;
    }
    
}
