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
public class MaeCargo extends Base{

    private static final long serialVersionUID = 1L;
    
    private MaeInversion maeInversion;
    
    private Number cCargoId;
    
    private Number nCargo;
    private String cConceptoId;
    private Number iCargo;
    private Date fCargo;
    private Date fProceso;
    private String dObs;
    private String bCancelado;
    private Number nDeposito;
    private Number iPendiente;
    private Number nCuota;
    
    
    public MaeCargo() {
    }

    public MaeCargo(Number cCargoId) {
        this.cCargoId = cCargoId;
    }

    public MaeCargo(Number cCargoId, Number nCargo) {
        this.cCargoId = cCargoId;
        this.nCargo = nCargo;
    }

    public Number getCCargoId() {
        return cCargoId;
    }

    public void setCCargoId(Number cCargoId) {
        this.cCargoId = cCargoId;
    }

    public Number getNCargo() {
        return nCargo;
    }

    public void setNCargo(Number nCargo) {
        this.nCargo = nCargo;
    }

    public String getCConceptoId() {
        return cConceptoId;
    }

    public void setCConceptoId(String cConceptoId) {
        this.cConceptoId = cConceptoId;
    }

    public Number getICargo() {
        return iCargo;
    }

    public void setICargo(Number iCargo) {
        this.iCargo = iCargo;
    }

    public Date getFCargo() {
        return fCargo;
    }

    public void setFCargo(Date fCargo) {
        this.fCargo = fCargo;
    }

    public Date getFProceso() {
        return fProceso;
    }

    public void setFProceso(Date fProceso) {
        this.fProceso = fProceso;
    }

    public String getDObs() {
        return dObs;
    }

    public void setDObs(String dObs) {
        this.dObs = dObs;
    }

    public String getBCancelado() {
        return bCancelado;
    }

    public void setBCancelado(String bCancelado) {
        this.bCancelado = bCancelado;
    }

    public Number getNDeposito() {
        return nDeposito;
    }

    public void setNDeposito(Number nDeposito) {
        this.nDeposito = nDeposito;
    }

    public Number getIPendiente() {
        return iPendiente;
    }

    public void setIPendiente(Number iPendiente) {
        this.iPendiente = iPendiente;
    }

    public Number getNCuota() {
        return nCuota;
    }

    public void setNCuota(Number nCuota) {
        this.nCuota = nCuota;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (cCargoId != null ? cCargoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeCargo)) {
            return false;
        }
        MaeCargo other = (MaeCargo) object;
        if ((this.cCargoId == null && other.cCargoId != null) || (this.cCargoId != null && !this.cCargoId.equals(other.cCargoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeCargo[ cCargoId=" + cCargoId + " ]";
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    
    
}
