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
public class MaeDepositoPago extends Base{

    private static final long serialVersionUID = 1L;
    
    
    private Number cMaeDepositoId;
    private Number cMaeInversionId;
    private String cFondoId;
    private String cInversionId;
    private String cTipo_inv;
    private Number nDeposito;
    private Number nSecuencia;
    private Number iPagado;
    private Number nCuota;
    private Number nDetalle;
    private Number nCargo;
    private String cConceptoId;
    private Date fPago;
    private String cGrpConcepto;
    
    private String ddescripcion;
    private String moneda;
    private String simbolo;

    
    public MaeDepositoPago() {
    }

    public Number getIPagado() {
        return iPagado;
    }

    public void setIPagado(Number iPagado) {
        this.iPagado = iPagado;
    }

    public Number getNCuota() {
        return nCuota;
    }

    public void setNCuota(Number nCuota) {
        this.nCuota = nCuota;
    }

    public Number getNDetalle() {
        return nDetalle;
    }

    public void setNDetalle(Number nDetalle) {
        this.nDetalle = nDetalle;
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

    public Date getFPago() {
        return fPago;
    }

    public void setFPago(Date fPago) {
        this.fPago = fPago;
    }

    public String getCGrpConcepto() {
        return cGrpConcepto;
    }

    public void setCGrpConcepto(String cGrpConcepto) {
        this.cGrpConcepto = cGrpConcepto;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getcMaeDepositoId() != null ? getcMaeDepositoId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeDepositoPago)) {
            return false;
        }
        MaeDepositoPago other = (MaeDepositoPago) object;
        if ((this.getcMaeDepositoId() == null && other.getcMaeDepositoId() != null) || (this.getcMaeDepositoId() != null && !this.cMaeDepositoId.equals(other.cMaeDepositoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeDepositoPago[ maeDepositoPagoPK=" + getcMaeDepositoId() + " ]";
    }

    /**
     * @return the cMaeDepositoId
     */
    public Number getcMaeDepositoId() {
        return cMaeDepositoId;
    }

    /**
     * @param cMaeDepositoId the cMaeDepositoId to set
     */
    public void setcMaeDepositoId(Number cMaeDepositoId) {
        this.cMaeDepositoId = cMaeDepositoId;
    }

    /**
     * @return the cMaeInversionId
     */
    public Number getcMaeInversionId() {
        return cMaeInversionId;
    }

    /**
     * @param cMaeInversionId the cMaeInversionId to set
     */
    public void setcMaeInversionId(Number cMaeInversionId) {
        this.cMaeInversionId = cMaeInversionId;
    }

    /**
     * @return the cFondoId
     */
    public String getcFondoId() {
        return cFondoId;
    }

    /**
     * @param cFondoId the cFondoId to set
     */
    public void setcFondoId(String cFondoId) {
        this.cFondoId = cFondoId;
    }

    /**
     * @return the cInversionId
     */
    public String getcInversionId() {
        return cInversionId;
    }

    /**
     * @param cInversionId the cInversionId to set
     */
    public void setcInversionId(String cInversionId) {
        this.cInversionId = cInversionId;
    }

    /**
     * @return the cTipo_inv
     */
    public String getcTipo_inv() {
        return cTipo_inv;
    }

    /**
     * @param cTipo_inv the cTipo_inv to set
     */
    public void setcTipo_inv(String cTipo_inv) {
        this.cTipo_inv = cTipo_inv;
    }

    /**
     * @return the nDeposito
     */
    public Number getnDeposito() {
        return nDeposito;
    }

    /**
     * @param nDeposito the nDeposito to set
     */
    public void setnDeposito(Number nDeposito) {
        this.nDeposito = nDeposito;
    }

    /**
     * @return the nSecuencia
     */
    public Number getnSecuencia() {
        return nSecuencia;
    }

    /**
     * @param nSecuencia the nSecuencia to set
     */
    public void setnSecuencia(Number nSecuencia) {
        this.nSecuencia = nSecuencia;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
}
