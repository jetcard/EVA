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
public class MaeControl extends Base{

    private static final long serialVersionUID = 1L;
    private MaeFondo maeFondo;
    private String ctipoProcesoId; 
    private Date ffecha;
    private String eestadoId;

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public String getCtipoProcesoId() {
        return ctipoProcesoId;
    }

    public void setCtipoProcesoId(String ctipoProcesoId) {
        this.ctipoProcesoId = ctipoProcesoId;
    }

    public Date getFfecha() {
        return ffecha;
    }

    public void setFfecha(Date ffecha) {
        this.ffecha = ffecha;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }
   
    
    
}
