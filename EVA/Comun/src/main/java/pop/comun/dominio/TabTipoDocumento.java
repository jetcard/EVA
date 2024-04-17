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
public class TabTipoDocumento extends Base{

    private static final long serialVersionUID = 1L;
    
    private int ctabTipoDocId;
    private int norden;
    private String dnombre;
    private String dnombreCorto;
    private int ncolor;
    private int ndiasEjecutar;
    private int ndiasAprox;
    private int nproxTipoDoc;

    public int getCtabTipoDocId() {
        return ctabTipoDocId;
    }

    public void setCtabTipoDocId(int ctabTipoDocId) {
        this.ctabTipoDocId = ctabTipoDocId;
    }

    public int getNorden() {
        return norden;
    }

    public void setNorden(int norden) {
        this.norden = norden;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getDnombreCorto() {
        return dnombreCorto;
    }

    public void setDnombreCorto(String dnombreCorto) {
        this.dnombreCorto = dnombreCorto;
    }

    public int getNcolor() {
        return ncolor;
    }

    public void setNcolor(int ncolor) {
        this.ncolor = ncolor;
    }

    public int getNdiasEjecutar() {
        return ndiasEjecutar;
    }

    public void setNdiasEjecutar(int ndiasEjecutar) {
        this.ndiasEjecutar = ndiasEjecutar;
    }

    public int getNdiasAprox() {
        return ndiasAprox;
    }

    public void setNdiasAprox(int ndiasAprox) {
        this.ndiasAprox = ndiasAprox;
    }

    public int getNproxTipoDoc() {
        return nproxTipoDoc;
    }

    public void setNproxTipoDoc(int nproxTipoDoc) {
        this.nproxTipoDoc = nproxTipoDoc;
    }

}
