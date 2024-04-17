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
public class LegDemanTchn extends Base{
    private MaeFondo fondo;
    private MaeInversion maeInversion;
    private String lsCodigoTchn;
    private String ls_tipoEtapa;
    private Date ld_fecha;
    private Number lnMonto_Capital;
    private Number lnMonto_Interes;
    private Number lnMonto_Mora;
    private Number lnMonto;
    private String lsdescrip;
  

   
    private String ls_UsuarioAdd;    

    public LegDemanTchn() {
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

    public String getLs_tipoEtapa() {
        return ls_tipoEtapa;
    }

    public void setLs_tipoEtapa(String ls_tipoEtapa) {
        this.ls_tipoEtapa = ls_tipoEtapa;
    }

    public Date getLd_fecha() {
        return ld_fecha;
    }

    public void setLd_fecha(Date ld_fecha) {
        this.ld_fecha = ld_fecha;
    }

    public Number getLnMonto_Capital() {
        return lnMonto_Capital;
    }

    public void setLnMonto_Capital(Number lnMonto_Capital) {
        this.lnMonto_Capital = lnMonto_Capital;
    }

    public Number getLnMonto_Interes() {
        return lnMonto_Interes;
    }

    public void setLnMonto_Interes(Number lnMonto_Interes) {
        this.lnMonto_Interes = lnMonto_Interes;
    }

    public Number getLnMonto_Mora() {
        return lnMonto_Mora;
    }

    public void setLnMonto_Mora(Number lnMonto_Mora) {
        this.lnMonto_Mora = lnMonto_Mora;
    }

    public Number getLnMonto() {
        return lnMonto;
    }

    public void setLnMonto(Number lnMonto) {
        this.lnMonto = lnMonto;
    }

    public String getLs_UsuarioAdd() {
        return ls_UsuarioAdd;
    }

    public void setLs_UsuarioAdd(String ls_UsuarioAdd) {
        this.ls_UsuarioAdd = ls_UsuarioAdd;
    }
    
    public String getLsdescrip() {
        return lsdescrip;
    }

    public void setLsdescrip(String lsdescrip) {
        this.lsdescrip = lsdescrip;
    }    

}
