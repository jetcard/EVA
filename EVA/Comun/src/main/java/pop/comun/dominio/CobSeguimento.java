/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;


/**
 *
 * @author Jyoverar
 */
public class CobSeguimento extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cSeguimientoId;
    private Number cMaeSeguimientoId;
    private String cFondoId;
    private String cSeguimiento;
    private Date fFechaSeg;
    private String eEstadoId;
    private String bAlerta;

    private List<CobSeguimientoDet> cobSeguimientoDetList;

    public CobSeguimento() {
    }

    public CobSeguimento(Number cSeguimientoId) {
        this.cSeguimientoId = cSeguimientoId;
    }

    public CobSeguimento(Number cSeguimientoId, String eEstadoId) {
        this.cSeguimientoId = cSeguimientoId;
        this.eEstadoId = eEstadoId;
    }

    public Number getCSeguimientoId() {
        return getcSeguimientoId();
    }

    public void setCSeguimientoId(Number cSeguimientoId) {
        this.setcSeguimientoId(cSeguimientoId);
    }

    public String getCFondoId() {
        return cFondoId;
    }

    public void setCFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    public String getCSeguimiento() {
        return cSeguimiento;
    }

    public void setCSeguimiento(String cSeguimiento) {
        this.cSeguimiento = cSeguimiento;
    }

    public Date getFFechaSeg() {
        return fFechaSeg;
    }

    public void setFFechaSeg(Date fFechaSeg) {
        this.fFechaSeg = fFechaSeg;
    }

    public String getEEstadoId() {
        return eEstadoId;
    }

    public void setEEstadoId(String eEstadoId) {
        this.eEstadoId = eEstadoId;
    }

    public String getBAlerta() {
        return bAlerta;
    }

    public void setBAlerta(String bAlerta) {
        this.bAlerta = bAlerta;
    }
    
    public List<CobSeguimientoDet> getCobSeguimientoDetList() {
        return cobSeguimientoDetList;
    }

    public void setCobSeguimientoDetList(List<CobSeguimientoDet> cobSeguimientoDetList) {
        this.cobSeguimientoDetList = cobSeguimientoDetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getcSeguimientoId() != null ? getcSeguimientoId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CobSeguimento)) {
            return false;
        }
        CobSeguimento other = (CobSeguimento) object;
        if ((this.getcSeguimientoId() == null && other.getcSeguimientoId() != null) || (this.getcSeguimientoId() != null && !this.cSeguimientoId.equals(other.cSeguimientoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.CobSeguimento[ cSeguimientoId=" + getcSeguimientoId() + " ]";
    }

    /**
     * @return the cSeguimientoId
     */
    public Number getcSeguimientoId() {
        return cSeguimientoId;
    }

    /**
     * @param cSeguimientoId the cSeguimientoId to set
     */
    public void setcSeguimientoId(Number cSeguimientoId) {
        this.cSeguimientoId = cSeguimientoId;
    }

    /**
     * @return the cMaeSeguimientoId
     */
    public Number getcMaeSeguimientoId() {
        return cMaeSeguimientoId;
    }

    /**
     * @param cMaeSeguimientoId the cMaeSeguimientoId to set
     */
    public void setcMaeSeguimientoId(Number cMaeSeguimientoId) {
        this.cMaeSeguimientoId = cMaeSeguimientoId;
    }
    
}
