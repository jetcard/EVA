/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class TabArchivo extends Base {

    private static final long serialVersionUID = 1L;
    
    private int ctabArchivoId;
    private TabDocumentosDet tabDocumentosDet;
    private String ctipoArcId;
    private String dserieNumero;
    private int nnumeracion;
    private String dnombreArc;
    private String druta;
    private Date fgeneracion;
    private String eestadoId;
    private boolean bgenerado;
    private byte[] ddatos;

    public TabArchivo() {
    }
    
    public int getCtabArchivoId() {
        return ctabArchivoId;
    }

    public void setCtabArchivoId(int ctabArchivoId) {
        this.ctabArchivoId = ctabArchivoId;
    }

    public TabDocumentosDet getTabDocumentosDet() {
        return tabDocumentosDet;
    }

    public void setTabDocumentosDet(TabDocumentosDet tabDocumentosDet) {
        this.tabDocumentosDet = tabDocumentosDet;
    }

    public String getCtipoArcId() {
        return ctipoArcId;
    }

    public void setCtipoArcId(String ctipoArcId) {
        this.ctipoArcId = ctipoArcId;
    }

    public String getDserieNumero() {
        return dserieNumero;
    }

    public void setDserieNumero(String dserieNumero) {
        this.dserieNumero = dserieNumero;
    }

    public int getNnumeracion() {
        return nnumeracion;
    }

    public void setNnumeracion(int nnumeracion) {
        this.nnumeracion = nnumeracion;
    }

    public String getDnombreArc() {
        return dnombreArc;
    }

    public void setDnombreArc(String dnombreArc) {
        this.dnombreArc = dnombreArc;
    }

    public String getDruta() {
        return druta;
    }

    public void setDruta(String druta) {
        this.druta = druta;
    }

    public Date getFgeneracion() {
        return fgeneracion;
    }

    public void setFgeneracion(Date fgeneracion) {
        this.fgeneracion = fgeneracion;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }

    public boolean isBgenerado() {
        return bgenerado;
    }

    public void setBgenerado(boolean bgenerado) {
        this.bgenerado = bgenerado;
    }

    public byte[] getDdatos() {
        return ddatos;
    }

    public void setDdatos(byte[] ddatos) {
        this.ddatos = ddatos;
    }
    
    
    
    
}
