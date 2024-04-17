/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.io.Serializable;

/**
 *
 * @author HH38092
 */
public class LegalAsesor implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String cusuarioId;
    private String nombreyapellido;
    
    public String getCusuarioId() {
        return cusuarioId;
    }

    public void setCusuarioId(String cusuarioId) {
        this.cusuarioId = cusuarioId;
    }

    /**
     * @return the nombreyapellido
     */
    public String getNombreyapellido() {
        return nombreyapellido;
    }

    /**
     * @param nombreyapellido the nombreyapellido to set
     */
    public void setNombreyapellido(String nombreyapellido) {
        this.nombreyapellido = nombreyapellido;
    }
   
}
