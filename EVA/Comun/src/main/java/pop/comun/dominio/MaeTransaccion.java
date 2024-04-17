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
public class MaeTransaccion extends Base{

    private static final long serialVersionUID = 1L;
    
    private Number cMaeTransaccionId;
    private Number nTransaccion;
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
    // flag para ver si se encontro en bd
    private String bencontrado;
        
    private String ddescripcion;
    
    private MaeTipoCambio maeTipoCambio;
    private MaeBancoCuenta maeBancoCuenta;
    private TipoTransaccion tipoTransaccion;
    
    public MaeTransaccion() {
    }

    public Number getcMaeTransaccionId() {
        return cMaeTransaccionId;
    }

    public void setcMaeTransaccionId(Number cMaeTransaccionId) {
        this.cMaeTransaccionId = cMaeTransaccionId;
    }

    public Number getnTransaccion() {
        return nTransaccion;
    }

    public void setnTransaccion(Number nTransaccion) {
        this.nTransaccion = nTransaccion;
    }

    public String getcDocDepositoId() {
        return cDocDepositoId;
    }

    public void setcDocDepositoId(String cDocDepositoId) {
        this.cDocDepositoId = cDocDepositoId;
    }

    public String getcBancoId() {
        return cBancoId;
    }

    public void setcBancoId(String cBancoId) {
        this.cBancoId = cBancoId;
    }

    public String getcItipCta() {
        return cItipCta;
    }

    public void setcItipCta(String cItipCta) {
        this.cItipCta = cItipCta;
    }

    public String getdBcoDescripcion() {
        return dBcoDescripcion;
    }

    public void setdBcoDescripcion(String dBcoDescripcion) {
        this.dBcoDescripcion = dBcoDescripcion;
    }

    public String getdBcoNoperacion() {
        return dBcoNoperacion;
    }

    public void setdBcoNoperacion(String dBcoNoperacion) {
        this.dBcoNoperacion = dBcoNoperacion;
    }

    public String getdBcoSucursal() {
        return dBcoSucursal;
    }

    public void setdBcoSucursal(String dBcoSucursal) {
        this.dBcoSucursal = dBcoSucursal;
    }

    public Number getiBcoDepositado() {
        return iBcoDepositado;
    }

    public void setiBcoDepositado(Number iBcoDepositado) {
        this.iBcoDepositado = iBcoDepositado;
    }

    public Number getiBcoDepositadoD() {
        return iBcoDepositadoD;
    }

    public void setiBcoDepositadoD(Number iBcoDepositadoD) {
        this.iBcoDepositadoD = iBcoDepositadoD;
    }

    public Number getiBcoSaldo() {
        return iBcoSaldo;
    }

    public void setiBcoSaldo(Number iBcoSaldo) {
        this.iBcoSaldo = iBcoSaldo;
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

    public Date getfBcoDeposito() {
        return fBcoDeposito;
    }

    public void setfBcoDeposito(Date fBcoDeposito) {
        this.fBcoDeposito = fBcoDeposito;
    }

    public String getbAplicado() {
        return bAplicado;
    }

    public void setbAplicado(String bAplicado) {
        this.bAplicado = bAplicado;
    }

    public String getBencontrado() {
        return bencontrado;
    }

    public void setBencontrado(String bencontrado) {
        this.bencontrado = bencontrado;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }

    public MaeTipoCambio getMaeTipoCambio() {
        return maeTipoCambio;
    }

    public void setMaeTipoCambio(MaeTipoCambio maeTipoCambio) {
        this.maeTipoCambio = maeTipoCambio;
    }

    public MaeBancoCuenta getMaeBancoCuenta() {
        return maeBancoCuenta;
    }

    public void setMaeBancoCuenta(MaeBancoCuenta maeBancoCuenta) {
        this.maeBancoCuenta = maeBancoCuenta;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    
}
