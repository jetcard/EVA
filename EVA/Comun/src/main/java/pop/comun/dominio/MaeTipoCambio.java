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
public class MaeTipoCambio extends Base{

    private static final long serialVersionUID = 1L;
    
    private int cMaeTipoCambioId;
    private Date fCambio;
    private String cTipoMoneda;
    private double nTipoCambioCom;
    private double nTipoCambioVen;

    
    public Date getfCambio() {
        return fCambio;
    }

    public void setfCambio(Date fCambio) {
        this.fCambio = fCambio;
    }

    public String getcTipoMoneda() {
        return cTipoMoneda;
    }

    public void setcTipoMoneda(String cTipoMoneda) {
        this.cTipoMoneda = cTipoMoneda;
    }

    public int getcMaeTipoCambioId() {
        return cMaeTipoCambioId;
    }

    public void setcMaeTipoCambioId(int cMaeTipoCambioId) {
        this.cMaeTipoCambioId = cMaeTipoCambioId;
    }

    public double getnTipoCambioCom() {
        return nTipoCambioCom;
    }

    public void setnTipoCambioCom(double nTipoCambioCom) {
        this.nTipoCambioCom = nTipoCambioCom;
    }

    public double getnTipoCambioVen() {
        return nTipoCambioVen;
    }

    public void setnTipoCambioVen(double nTipoCambioVen) {
        this.nTipoCambioVen = nTipoCambioVen;
    }
    
    
}
