/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.List;

/**
 *
 * @author HH38092
 */
public class LegEtapa extends Base{

    private static final long serialVersionUID = 1L;
    
    private String cEtapaId;
    private String dEtapa;
    
    public LegEtapa() {
    }

    /**
     * @return the cEtapaId
     */
    public String getCEtapaId() {
        return cEtapaId;
    }

    /**
     * @param cEtapaId the cEtapaId to set
     */
    public void setCEtapaId(String cEtapaId) {
        this.cEtapaId = cEtapaId;
    }

    /**
     * @return the dEtapa
     */
    public String getDEtapa() {
        return dEtapa;
    }

    /**
     * @param dEtapa the dEtapa to set
     */
    public void setDEtapa(String dEtapa) {
        this.dEtapa = dEtapa;
    }
}