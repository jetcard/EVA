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
public class LegEtapaTchn extends Base{
    private MaeFondo fondo;
    private MaeInversion maeInversion;
    private String lsCodigoTchn;
    private String lsCodJuz;
    private String lsCodEsp;
    private String lsNroExp;
    private Date ld_fecha;
    private Number lnMonto_deman;
    private String lsDescripcion;
    private String ls_UsuarioAdd;    

    public LegEtapaTchn() {
    }

    
    public MaeFondo getFondo() {
        return fondo;
    }

    public void setFondo(MaeFondo fondo) {
        this.fondo = fondo;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public String getLsCodigoTchn() {
        return lsCodigoTchn;
    }

    public void setLsCodigoTchn(String lsCodigoTchn) {
        this.lsCodigoTchn = lsCodigoTchn;
    }

    public String getLsCodJuz() {
        return lsCodJuz;
    }

    public void setLsCodJuz(String lsCodJuz) {
        this.lsCodJuz = lsCodJuz;
    }

    public String getLsCodEsp() {
        return lsCodEsp;
    }

    public void setLsCodEsp(String lsCodEsp) {
        this.lsCodEsp = lsCodEsp;
    }

    public String getLsNroExp() {
        return lsNroExp;
    }

    public void setLsNroExp(String lsNroExp) {
        this.lsNroExp = lsNroExp;
    }

    public Date getLd_fecha() {
        return ld_fecha;
    }

    public void setLd_fecha(Date ld_fecha) {
        this.ld_fecha = ld_fecha;
    }

    public Number getLnMonto_deman() {
        return lnMonto_deman;
    }

    public void setLnMonto_deman(Number lnMonto_deman) {
        this.lnMonto_deman = lnMonto_deman;
    }

    public String getLsDescripcion() {
        return lsDescripcion;
    }

    public void setLsDescripcion(String lsDescripcion) {
        this.lsDescripcion = lsDescripcion;
    }

    public String getLs_UsuarioAdd() {
        return ls_UsuarioAdd;
    }

    public void setLs_UsuarioAdd(String ls_UsuarioAdd) {
        this.ls_UsuarioAdd = ls_UsuarioAdd;
    }


    
    
    
}
