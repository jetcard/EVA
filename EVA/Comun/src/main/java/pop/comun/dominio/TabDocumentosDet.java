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
public class TabDocumentosDet extends Base {

    private static final long serialVersionUID = 1L;

    private int ctabDocumentosDetId;
    private TabDocumentos tabDocumentos;
    private TabTipoDocumentoEstado tabTipoDocumentoEstado;
    private Date ffecha;
    private String dobs;
    private String ctablaId;
    private String ctablaDetId;
    private String dtablaDet;
    private String bsoloLectura;
    private String bcargo;
    private Date fopc1;
    private Date fopc2;
    private Number nopc1;
    private Number nopc2;
    private String dopc1;
    private String dopc2;
    
    private List<TabArchivo> tabArchivos; 

    public int getCtabDocumentosDetId() {
        return ctabDocumentosDetId;
    }

    public void setCtabDocumentosDetId(int ctabDocumentosDetId) {
        this.ctabDocumentosDetId = ctabDocumentosDetId;
    }

    public TabDocumentos getTabDocumentos() {
        return tabDocumentos;
    }

    public void setTabDocumentos(TabDocumentos tabDocumentos) {
        this.tabDocumentos = tabDocumentos;
    }

    public TabTipoDocumentoEstado getTabTipoDocumentoEstado() {
        return tabTipoDocumentoEstado;
    }

    public void setTabTipoDocumentoEstado(TabTipoDocumentoEstado tabTipoDocumentoEstado) {
        this.tabTipoDocumentoEstado = tabTipoDocumentoEstado;
    }

    public Date getFfecha() {
        return ffecha;
    }

    public void setFfecha(Date ffecha) {
        this.ffecha = ffecha;
    }

    public String getDobs() {
        return dobs;
    }

    public void setDobs(String dobs) {
        this.dobs = dobs;
    }

    public String getCtablaId() {
        return ctablaId;
    }

    public void setCtablaId(String ctablaId) {
        this.ctablaId = ctablaId;
    }

    public String getCtablaDetId() {
        return ctablaDetId;
    }

    public void setCtablaDetId(String ctablaDetId) {
        this.ctablaDetId = ctablaDetId;
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

    public List<TabArchivo> getTabArchivos() {
        return tabArchivos;
    }

    public void setTabArchivos(List<TabArchivo> tabArchivos) {
        this.tabArchivos = tabArchivos;
    }

    public String getDtablaDet() {
        return dtablaDet;
    }

    public void setDtablaDet(String dtablaDet) {
        this.dtablaDet = dtablaDet;
    }

    public Date getFopc1() {
        return fopc1;
    }

    public void setFopc1(Date fopc1) {
        this.fopc1 = fopc1;
    }

    public Date getFopc2() {
        return fopc2;
    }

    public void setFopc2(Date fopc2) {
        this.fopc2 = fopc2;
    }

    public Number getNopc1() {
        return nopc1;
    }

    public void setNopc1(Number nopc1) {
        this.nopc1 = nopc1;
    }

    public Number getNopc2() {
        return nopc2;
    }

    public void setNopc2(Number nopc2) {
        this.nopc2 = nopc2;
    }

    public String getDopc1() {
        return dopc1;
    }

    public void setDopc1(String dopc1) {
        this.dopc1 = dopc1;
    }

    public String getDopc2() {
        return dopc2;
    }

    public void setDopc2(String dopc2) {
        this.dopc2 = dopc2;
    }

}
