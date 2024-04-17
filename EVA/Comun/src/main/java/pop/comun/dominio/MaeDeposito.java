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
public class MaeDeposito extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cMaeDepositoId;
    private MaeInversion maeInversion;
    private Number nDeposito;
    private String cDocDepositoId;
    private String cBancoId;
    private String cItipCta;
    private String dBcoDescripcion;
    private String dBcoNoperacion;
    private String dBcoSucursal;
    private Number iBcoDepositado;
    private Number iBcoDepositadoD;
    private Number iBcoSaldo;
    private String dbcoUsuario;
    private String dbcoUtc;
    private String dbcoReferenciaB;
    private Date fBcoDeposito;
    private String bAplicado;
    private Number iSaldo;
    private String bReFinancia;
    private String bAmpliacion;
    private String bProntoPago;
    // flag para ver si se encontro en bd
    private String bencontrado;
    // para mensaje individual
    private String dmsjIndividual;
        
    private String ddescripcion;
    private String moneda;
    private String simbolo;

    private MaeTipoCambio maeTipoCambio;
    
    private List<MaeDepositoPago> maeDepositoPagoList;
    
    private MaeBancoCuenta maeBancoCuenta;
    
    private CobCompromiso cobCompromiso;
    
    private String pDPAGO;
    private Integer pNDEPOSITO;
    private Integer pNCUOTA;
    private Double pCAPITAL;
    private Double pINTERES;
    private Double pMORA;
    private Double pOtros;

    

    public MaeDeposito() {
    }

    
    public String getcDocDepositoId() {
        return cDocDepositoId;
    }

    public void setcDocDepositoId(String cDocDepositoId) {
        this.cDocDepositoId = cDocDepositoId;
    }

    public String getdBcoSucursal() {
        return dBcoSucursal;
    }

    public void setdBcoSucursal(String dBcoSucursal) {
        this.dBcoSucursal = dBcoSucursal;
    }

    public Number getiBcoDepositadoD() {
        return iBcoDepositadoD;
    }

    public void setiBcoDepositadoD(Number iBcoDepositadoD) {
        this.iBcoDepositadoD = iBcoDepositadoD;
    }
    
    public String getCBancoId() {
        return cBancoId;
    }

    public void setCBancoId(String cBancoId) {
        this.cBancoId = cBancoId;
    }

    public String getCItipCta() {
        return cItipCta;
    }

    public void setCItipCta(String cItipCta) {
        this.cItipCta = cItipCta;
    }

    public String getDBcoDescripcion() {
        return dBcoDescripcion;
    }

    public void setDBcoDescripcion(String dBcoDescripcion) {
        this.dBcoDescripcion = dBcoDescripcion;
    }

    public String getDBcoNoperacion() {
        return dBcoNoperacion;
    }

    public void setDBcoNoperacion(String dBcoNoperacion) {
        this.dBcoNoperacion = dBcoNoperacion;
    }

    public Number getIBcoDepositado() {
        return iBcoDepositado;
    }

    public void setIBcoDepositado(Number iBcoDepositado) {
        this.iBcoDepositado = iBcoDepositado;
    }

    public Number getIBcoSaldo() {
        return iBcoSaldo;
    }

    public void setIBcoSaldo(Number iBcoSaldo) {
        this.iBcoSaldo = iBcoSaldo;
    }

    public Date getFBcoDeposito() {
        return fBcoDeposito;
    }

    public void setFBcoDeposito(Date fBcoDeposito) {
        this.fBcoDeposito = fBcoDeposito;
    }

    public String getBAplicado() {
        return bAplicado;
    }

    public void setBAplicado(String bAplicado) {
        this.bAplicado = bAplicado;
    }

    public Number getISaldo() {
        return iSaldo;
    }

    public void setISaldo(Number iSaldo) {
        this.iSaldo = iSaldo;
    }

    public String getBReFinancia() {
        return bReFinancia;
    }

    public void setBReFinancia(String bReFinancia) {
        this.bReFinancia = bReFinancia;
    }

    public String getBAmpliacion() {
        return bAmpliacion;
    }

    public void setBAmpliacion(String bAmpliacion) {
        this.bAmpliacion = bAmpliacion;
    }

    public String getBProntoPago() {
        return bProntoPago;
    }

    public void setBProntoPago(String bProntoPago) {
        this.bProntoPago = bProntoPago;
    }

    public List<MaeDepositoPago> getMaeDepositoPagoList() {
        return maeDepositoPagoList;
    }

    public void setMaeDepositoPagoList(List<MaeDepositoPago> maeDepositoPagoList) {
        this.maeDepositoPagoList = maeDepositoPagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cMaeDepositoId != null ? cMaeDepositoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeDeposito)) {
            return false;
        }
        MaeDeposito other = (MaeDeposito) object;
        if ((this.cMaeDepositoId == null && other.cMaeDepositoId != null) || (this.cMaeDepositoId != null && !this.cMaeDepositoId.equals(other.cMaeDepositoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.popular.web.webcobranza.entities.MaeDeposito[ maeDepositoPK=" + cMaeDepositoId + " ]";
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
     * @return the maeInversion
     */
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    /**
     * @param maeInversion the maeInversion to set
     */
    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    /**
     * @return the maeTipoCambio
     */
    public MaeTipoCambio getMaeTipoCambio() {
        return maeTipoCambio;
    }

    /**
     * @param maeTipoCambio the maeTipoCambio to set
     */
    public void setMaeTipoCambio(MaeTipoCambio maeTipoCambio) {
        this.maeTipoCambio = maeTipoCambio;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }

    public String getDbcoUsuario() {
        return dbcoUsuario;
    }

    public void setDbcoUsuario(String dbcoUsuario) {
        this.dbcoUsuario = dbcoUsuario;
    }

    public String getDbcoUtc() {
        return dbcoUtc;
    }

    public void setDbcoUtc(String dbcoUtc) {
        this.dbcoUtc = dbcoUtc;
    }

    public String getDbcoReferenciaB() {
        return dbcoReferenciaB;
    }

    public void setDbcoReferenciaB(String dbcoReferenciaB) {
        this.dbcoReferenciaB = dbcoReferenciaB;
    }

    public MaeBancoCuenta getMaeBancoCuenta() {
        return maeBancoCuenta;
    }

    public void setMaeBancoCuenta(MaeBancoCuenta maeBancoCuenta) {
        this.maeBancoCuenta = maeBancoCuenta;
    }

    public String getBencontrado() {
        return bencontrado;
    }

    public void setBencontrado(String bencontrado) {
        this.bencontrado = bencontrado;
    }

    public String getDmsjIndividual() {
        return dmsjIndividual;
    }

    public void setDmsjIndividual(String dmsjIndividual) {
        this.dmsjIndividual = dmsjIndividual;
    }

    public CobCompromiso getCobCompromiso() {
        return cobCompromiso;
    }

    public void setCobCompromiso(CobCompromiso cobCompromiso) {
        this.cobCompromiso = cobCompromiso;
    }

 public String getpDPAGO() {
        return pDPAGO;
    }

    public void setpDPAGO(String pDPAGO) {
        this.pDPAGO = pDPAGO;
    }

    public Integer getpNDEPOSITO() {
        return pNDEPOSITO;
    }

    public void setpNDEPOSITO(Integer pNDEPOSITO) {
        this.pNDEPOSITO = pNDEPOSITO;
    }

    public Integer getpNCUOTA() {
        return pNCUOTA;
    }

    public void setpNCUOTA(Integer pNCUOTA) {
        this.pNCUOTA = pNCUOTA;
    }

    public Number getpCAPITAL() {
        return pCAPITAL;
    }

    public void setpCAPITAL(Double pCAPITAL) {
        this.pCAPITAL = pCAPITAL;
    }

    public Double getpINTERES() {
        return pINTERES;
    }

    public void setpINTERES(Double pINTERES) {
        this.pINTERES = pINTERES;
    }

    public Double getpMORA() {
        return pMORA;
    }

    public void setpMORA(Double pMORA) {
        this.pMORA = pMORA;
    }

    public Double getpOtros() {
        return pOtros;
    }

    public void setpOtros(Double pOtros) {
        this.pOtros = pOtros;
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
