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
public class MaeSesion extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cmaeSesionId;
    private String csesionId;
    private String cusuarioId;
    private Date fingreso;

    public MaeSesion() {
    }

    public Number getCmaeSesionId() {
        return cmaeSesionId;
    }

    public void setCmaeSesionId(Number cmaeSesionId) {
        this.cmaeSesionId = cmaeSesionId;
    }

    public String getCsesionId() {
        return csesionId;
    }

    public void setCsesionId(String csesionId) {
        this.csesionId = csesionId;
    }

    public String getCusuarioId() {
        return cusuarioId;
    }

    public void setCusuarioId(String cusuarioId) {
        this.cusuarioId = cusuarioId;
    }

    public Date getFingreso() {
        return fingreso;
    }

    public void setFingreso(Date fingreso) {
        this.fingreso = fingreso;
    }
    
    
}
