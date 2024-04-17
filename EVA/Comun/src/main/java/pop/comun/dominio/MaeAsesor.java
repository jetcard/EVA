/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.io.Serializable;

/**
 *
 * @author EC23329
 */
public class MaeAsesor implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String cusuarioId;
    private String dnombre;
    private String ctipo;
    
    public String getCusuarioId() {
        return cusuarioId;
    }

    public void setCusuarioId(String cusuarioId) {
        this.cusuarioId = cusuarioId;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }
    
    public String getCtipo() {
        return ctipo;
    }

    public void setCtipo(String ctipo) {
        this.ctipo = ctipo;
    }

}
