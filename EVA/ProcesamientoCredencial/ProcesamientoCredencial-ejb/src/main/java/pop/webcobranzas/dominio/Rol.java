/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dominio;

/**
 *
 * @author Jyoverar
 */
public class Rol {
    
    private int rolId;
    private String rolNombre;
    private int rolUsuarioId;

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getRolUsuarioId() {
        return rolUsuarioId;
    }

    public void setRolUsuarioId(int rolUsuarioId) {
        this.rolUsuarioId = rolUsuarioId;
    }
    
    
}
