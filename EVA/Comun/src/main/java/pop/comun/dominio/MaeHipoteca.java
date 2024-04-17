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
public class MaeHipoteca extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cmaeHipotecaId;
    private String casientoId;
    private String cpartidaElecId;
    private CobTablas csede;
    private Date fasiento;
    private String dnomNotaria;
    private String ctchnReal;
    private Date femisionTchn;
    private String casientoTchn;
    private CobTablas csedeTchn;
    private MaeInmueble maeInmueble;
    
    public Number getCmaeHipotecaId() {
        return cmaeHipotecaId;
    }

    public void setCmaeHipotecaId(Number cmaeHipotecaId) {
        this.cmaeHipotecaId = cmaeHipotecaId;
    }

    public String getCasientoId() {
        return casientoId;
    }

    public void setCasientoId(String casientoId) {
        this.casientoId = casientoId;
    }

    public String getCpartidaElecId() {
        return cpartidaElecId;
    }

    public void setCpartidaElecId(String cpartidaElecId) {
        this.cpartidaElecId = cpartidaElecId;
    }

    public Date getFasiento() {
        return fasiento;
    }

    public void setFasiento(Date fasiento) {
        this.fasiento = fasiento;
    }

    public String getDnomNotaria() {
        return dnomNotaria;
    }

    public void setDnomNotaria(String dnomNotaria) {
        this.dnomNotaria = dnomNotaria;
    }

    public String getCtchnReal() {
        return ctchnReal;
    }

    public void setCtchnReal(String ctchnReal) {
        this.ctchnReal = ctchnReal;
    }

    public Date getFemisionTchn() {
        return femisionTchn;
    }

    public void setFemisionTchn(Date femisionTchn) {
        this.femisionTchn = femisionTchn;
    }

    public String getCasientoTchn() {
        return casientoTchn;
    }

    public void setCasientoTchn(String casientoTchn) {
        this.casientoTchn = casientoTchn;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public CobTablas getCsede() {
        return csede;
    }

    public void setCsede(CobTablas csede) {
        this.csede = csede;
    }

    public CobTablas getCsedeTchn() {
        return csedeTchn;
    }

    public void setCsedeTchn(CobTablas csedeTchn) {
        this.csedeTchn = csedeTchn;
    } 
}
