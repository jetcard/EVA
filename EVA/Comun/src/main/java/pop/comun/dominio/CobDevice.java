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
public class CobDevice extends Base{

    private static final long serialVersionUID = 1L;
    
    private String devId;
    private String devTech;
    private String devDial;
    private String devType;
    private String devUser;
    private String devDescripcion;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevTech() {
        return devTech;
    }

    public void setDevTech(String devTech) {
        this.devTech = devTech;
    }

    public String getDevDial() {
        return devDial;
    }

    public void setDevDial(String devDial) {
        this.devDial = devDial;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevUser() {
        return devUser;
    }

    public void setDevUser(String devUser) {
        this.devUser = devUser;
    }

    public String getDevDescripcion() {
        return devDescripcion;
    }

    public void setDevDescripcion(String devDescripcion) {
        this.devDescripcion = devDescripcion;
    }
    
    
    
}
