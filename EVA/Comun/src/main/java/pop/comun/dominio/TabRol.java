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
public class TabRol extends Base {

    private static final long serialVersionUID = 1L;

    private int crolId;
    private String dnombre;
    private String crol;
    private int cusuarioRolId;

    public int getCrolId() {
        return crolId;
    }

    public void setCrolId(int crolId) {
        this.crolId = crolId;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getCrol() {
        return crol;
    }

    public void setCrol(String crol) {
        this.crol = crol;
    }

    public int getCusuarioRolId() {
        return cusuarioRolId;
    }

    public void setCusuarioRolId(int cusuarioRolId) {
        this.cusuarioRolId = cusuarioRolId;
    }
}
