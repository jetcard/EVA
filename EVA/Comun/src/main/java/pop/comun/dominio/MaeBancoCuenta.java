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
public class MaeBancoCuenta extends Base{

    private static final long serialVersionUID = 1L;
    
    private MaeBanco maeBanco;
    private int ccuentaId;
    private MaeFondo maeFondo;
    private String ctipoCuenta;
    private String ctipoMoneda;
    private Date fapertura;
    private String dnroCuenta;
    private Number nsaldoContable;
    private Number nsaldoContableOld;
    private Number nsaldoDisponible;
    private Number nsaldoDisponibleOld;
    private Number icostoDepo;
    private Number bsobregiro;
    private MaePersona maePersona;
    private String ccabArchPlan;

    public MaeBanco getMaeBanco() {
        return maeBanco;
    }

    public void setMaeBanco(MaeBanco maeBanco) {
        this.maeBanco = maeBanco;
    }

    public int getCcuentaId() {
        return ccuentaId;
    }

    public void setCcuentaId(int ccuentaId) {
        this.ccuentaId = ccuentaId;
    }
    
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public String getCtipoCuenta() {
        return ctipoCuenta;
    }

    public void setCtipoCuenta(String ctipoCuenta) {
        this.ctipoCuenta = ctipoCuenta;
    }

    public String getCtipoMoneda() {
        return ctipoMoneda;
    }

    public void setCtipoMoneda(String ctipoMoneda) {
        this.ctipoMoneda = ctipoMoneda;
    }

    public Date getFapertura() {
        return fapertura;
    }

    public void setFapertura(Date fapertura) {
        this.fapertura = fapertura;
    }

    public String getDnroCuenta() {
        return dnroCuenta;
    }

    public void setDnroCuenta(String dnroCuenta) {
        this.dnroCuenta = dnroCuenta;
    }

    public Number getNsaldoContable() {
        return nsaldoContable;
    }

    public void setNsaldoContable(Number nsaldoContable) {
        this.nsaldoContable = nsaldoContable;
    }

    public Number getNsaldoContableOld() {
        return nsaldoContableOld;
    }

    public void setNsaldoContableOld(Number nsaldoContableOld) {
        this.nsaldoContableOld = nsaldoContableOld;
    }

    public Number getNsaldoDisponible() {
        return nsaldoDisponible;
    }

    public void setNsaldoDisponible(Number nsaldoDisponible) {
        this.nsaldoDisponible = nsaldoDisponible;
    }

    public Number getNsaldoDisponibleOld() {
        return nsaldoDisponibleOld;
    }

    public void setNsaldoDisponibleOld(Number nsaldoDisponibleOld) {
        this.nsaldoDisponibleOld = nsaldoDisponibleOld;
    }

    public Number getIcostoDepo() {
        return icostoDepo;
    }

    public void setIcostoDepo(Number icostoDepo) {
        this.icostoDepo = icostoDepo;
    }

    public Number getBsobregiro() {
        return bsobregiro;
    }

    public void setBsobregiro(Number bsobregiro) {
        this.bsobregiro = bsobregiro;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public String getCcabArchPlan() {
        return ccabArchPlan;
    }

    public void setCcabArchPlan(String ccabArchPlan) {
        this.ccabArchPlan = ccabArchPlan;
    }
    
    
    
    
}
