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
public class MaeReporte extends Base {

    private static final long serialVersionUID = 1L;

    private String nameLogo;
    private String userName;
    private String nameReport;
    private String numAsesor;
    private String mailAsesor;
    private int tipoDoc;

    public String getNumAsesor() {
        return numAsesor;
    }

    public void setNumAsesor(String numAsesor) {
        this.numAsesor = numAsesor;
    }

    public String getMailAsesor() {
        return mailAsesor;
    }

    public void setMailAsesor(String mailAsesor) {
        this.mailAsesor = mailAsesor;
    }

    public String getNameLogo() {
        return nameLogo;
    }

    public void setNameLogo(String nameLogo) {
        this.nameLogo = nameLogo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameReport() {
        return nameReport;
    }

    public void setNameReport(String nameReport) {
        this.nameReport = nameReport;
    }

    /**
     * @return the tipoDoc
     */
    public int getTipoDoc() {
        return tipoDoc;
    }

    /**
     * @param tipoDoc the tipoDoc to set
     */
    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

}
