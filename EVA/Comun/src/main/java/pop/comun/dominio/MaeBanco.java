/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.List;

/**
 *
 * @author Jyoverar
 */
public class MaeBanco extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cbancoId;
    private String ddescripcion;
    private String ddescCorto;
    private int csbs;
    private MaeFondo maeFondo;

    private List<MaeBancoCuenta> maeBancoCuentas;

    public int getCbancoId() {
        return cbancoId;
    }

    public void setCbancoId(int cbancoId) {
        this.cbancoId = cbancoId;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }

    public String getDdescCorto() {
        return ddescCorto;
    }

    public void setDdescCorto(String ddescCorto) {
        this.ddescCorto = ddescCorto;
    }

    public int getCsbs() {
        return csbs;
    }

    public void setCsbs(int csbs) {
        this.csbs = csbs;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public List<MaeBancoCuenta> getMaeBancoCuentas() {
        return maeBancoCuentas;
    }

    public void setMaeBancoCuentas(List<MaeBancoCuenta> maeBancoCuentas) {
        this.maeBancoCuentas = maeBancoCuentas;
    }
    
    
    
}
