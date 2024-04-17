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
public class MaeCuotaPagoDet extends Base{

    private static final long serialVersionUID = 1L;
        
    private MaeCuotaPago maeCuotaPago;
    private int ndetalle;
    private String cconceptoId;
    private double iconcepto;
    private String bcancelado;
    private double ipendiente;
    private Date fProceso;
    private double ipenAcum;
    private Date fgenCcpto;
    private double ptasaIm;
    private double iadeudadoIm;
    private Date finiIm;
    private Date ffinIm;
    private String eFlagNew;

    
    
    public MaeCuotaPagoDet() {
    }

    public MaeCuotaPago getMaeCuotaPago() {
        return maeCuotaPago;
    }

    public void setMaeCuotaPago(MaeCuotaPago maeCuotaPago) {
        this.maeCuotaPago = maeCuotaPago;
    }

    public int getNdetalle() {
        return ndetalle;
    }

    public void setNdetalle(int ndetalle) {
        this.ndetalle = ndetalle;
    }

    public String getCconceptoId() {
        return cconceptoId;
    }

    public void setCconceptoId(String cconceptoId) {
        this.cconceptoId = cconceptoId;
    }

    public double getIconcepto() {
        return iconcepto;
    }

    public void setIconcepto(double iconcepto) {
        this.iconcepto = iconcepto;
    }

    public String getBcancelado() {
        return bcancelado;
    }

    public void setBcancelado(String bcancelado) {
        this.bcancelado = bcancelado;
    }

    public double getIpendiente() {
        return ipendiente;
    }

    public void setIpendiente(double ipendiente) {
        this.ipendiente = ipendiente;
    }

    public Date getfProceso() {
        return fProceso;
    }

    public void setfProceso(Date fProceso) {
        this.fProceso = fProceso;
    }

    public double getIpenAcum() {
        return ipenAcum;
    }

    public void setIpenAcum(double ipenAcum) {
        this.ipenAcum = ipenAcum;
    }

    public Date getFgenCcpto() {
        return fgenCcpto;
    }

    public void setFgenCcpto(Date fgenCcpto) {
        this.fgenCcpto = fgenCcpto;
    }

    public double getPtasaIm() {
        return ptasaIm;
    }

    public void setPtasaIm(double ptasaIm) {
        this.ptasaIm = ptasaIm;
    }

    public double getIadeudadoIm() {
        return iadeudadoIm;
    }

    public void setIadeudadoIm(double iadeudadoIm) {
        this.iadeudadoIm = iadeudadoIm;
    }

    public Date getFiniIm() {
        return finiIm;
    }

    public void setFiniIm(Date finiIm) {
        this.finiIm = finiIm;
    }

    public Date getFfinIm() {
        return ffinIm;
    }

    public void setFfinIm(Date ffinIm) {
        this.ffinIm = ffinIm;
    }
    
    public String geteFlagNew() {
        return eFlagNew;
    }

    public void seteFlagNew(String eFlagNew) {
        this.eFlagNew = eFlagNew;
    }        
}
