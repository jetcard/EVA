/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

/**
 *
 * @author HH38092
 */
public class LegTabla extends Base{

    private static final long serialVersionUID = 1L;
    
    private String cTablaId;
    private String dTabla;
    
    public LegTabla() {
    }

    /**
     * @return the cTablaId
     */
    public String getCTablaId() {
        return cTablaId;
    }

    /**
     * @param cTablaId the cTablaId to set
     */
    public void setCTablaId(String cTablaId) {
        this.cTablaId = cTablaId;
    }

    /**
     * @return the dTabla
     */
    public String getDTabla() {
        return dTabla;
    }

    /**
     * @param dTabla the dTabla to set
     */
    public void setDTabla(String dTabla) {
        this.dTabla = dTabla;
    }

 
}