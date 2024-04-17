/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.io.Serializable;

/**
 *
 * @author Jyoverar
 */
public class CobUsuario implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String cusuarioId;
    private String dnombres;
    private String dapellidos;
    private String cllamadaId;

    public String getCusuarioId() {
        return cusuarioId;
    }

    public void setCusuarioId(String cusuarioId) {
        this.cusuarioId = cusuarioId;
    }

    public String getDnombres() {
        return dnombres;
    }

    public void setDnombres(String dnombres) {
        this.dnombres = dnombres;
    }

    public String getDapellidos() {
        return dapellidos;
    }

    public void setDapellidos(String dapellidos) {
        this.dapellidos = dapellidos;
    }

    public String getCllamadaId() {
        return cllamadaId;
    }

    public void setCllamadaId(String cllamadaId) {
        this.cllamadaId = cllamadaId;
    }

    
    
}
