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
public class TabDocumentos extends Base {

    private static final long serialVersionUID = 1L;

    private int ctabDocumentosId;
    private MaeInversion maeInversion;
    private TabTipoDocumento tabTipoDocumento;
    private String eestadoId;
    
     private List<TabDocumentosDet> tabDocumentosDets;

    public int getCtabDocumentosId() {
        return ctabDocumentosId;
    }

    public void setCtabDocumentosId(int ctabDocumentosId) {
        this.ctabDocumentosId = ctabDocumentosId;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public TabTipoDocumento getTabTipoDocumento() {
        return tabTipoDocumento;
    }

    public void setTabTipoDocumento(TabTipoDocumento tabTipoDocumento) {
        this.tabTipoDocumento = tabTipoDocumento;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }

    public List<TabDocumentosDet> getTabDocumentosDets() {
        return tabDocumentosDets;
    }

    public void setTabDocumentosDets(List<TabDocumentosDet> tabDocumentosDets) {
        this.tabDocumentosDets = tabDocumentosDets;
    }

}
