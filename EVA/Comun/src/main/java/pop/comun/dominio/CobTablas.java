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
public class CobTablas extends Base{

    private static final long serialVersionUID = 1L;
    
    private String ctablaId;
    private String ctablaDetId;
    private String ddescripcion;
    private String ddescCorta;
    private Integer priori;
    
    private Integer campo1 = 0;
    
    
    public CobTablas() {
    }

    public String getCtablaId() {
        return ctablaId;
    }

    public void setCtablaId(String ctablaId) {
        this.ctablaId = ctablaId;
    }

    public String getCtablaDetId() {
        return ctablaDetId;
    }

    public void setCtablaDetId(String ctablaDetId) {
        this.ctablaDetId = ctablaDetId;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }

    public String getDdescCorta() {
        return ddescCorta;
    }

    public void setDdescCorta(String ddescCorta) {
        this.ddescCorta = ddescCorta;
    }

    /**
     * @return the campo1
     */
    public Integer getCampo1() {
        return campo1;
    }

    /**
     * @param campo1 the campo1 to set
     */
    public void setCampo1(Integer campo1) {
        this.campo1 = campo1;
    }

    /**
     * @return the priori
     */
    public Integer getPriori() {
        return priori;
    }

    /**
     * @param priori the priori to set
     */
    public void setPriori(Integer priori) {
        this.priori = priori;
    }

    
}
