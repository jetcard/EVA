/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

/**
 *
 * @author PR154357
 */
public class Demandados {
    //private int nro;
    private String nombres;
    private String apePat;
    private String apeMat;
    private String nroDocumento;

    /*public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }*/

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    @Override
    public String toString() {
        //return "Demandados{" + "nro=" + nro + ", nombres=" + nombres + ", apePat=" + apePat + ", apeMat=" + apeMat + ", nroDocumento=" + nroDocumento + '}';
        return "{" +  "nombres=" + nombres + ", apePat=" + apePat + ", apeMat=" + apeMat + ", nroDocumento=" + nroDocumento + '}';
    }

    
}
