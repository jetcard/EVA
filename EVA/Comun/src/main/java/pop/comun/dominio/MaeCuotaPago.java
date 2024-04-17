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
public class MaeCuotaPago extends Base{



    private static final long serialVersionUID = 1L;
    
    private MaeInversion maeInversion;
    private int nSecuencia;
    private String dsecuencia;
    private String bCancelado;
    private Date fCancelado;
    private String eFormaCancel;
    private Date fProceso;
    private Date fPagoCrono;
    private Number iCuota;
    private Number iPendiente;
    // solo para presentacion
    private Number icapital;
    private Number icompensatorio;
    private Number imoratorio;
    private Number ica;
    private String eNewflag;
    private int totalCuota;
    private int cronograma_pendiente;

    
   
    
    private List<MaeCuotaPagoDet> maeCuotaPagoDetList;

    public MaeCuotaPago() {
    }

    public List<MaeCuotaPagoDet> getMaeCuotaPagoDetList() {
        return maeCuotaPagoDetList;
    }

    public void setMaeCuotaPagoDetList(List<MaeCuotaPagoDet> maeCuotaPagoDetList) {
        this.maeCuotaPagoDetList = maeCuotaPagoDetList;
    }

    
    /**
     * @return the nSecuencia
     */
    public int getnSecuencia() {
        return nSecuencia;
    }

    /**
     * @param nSecuencia the nSecuencia to set
     */
    public void setnSecuencia(int nSecuencia) {
        this.nSecuencia = nSecuencia;
    }

    public Number getiPendiente() {
        return iPendiente;
    }

    public void setiPendiente(Number iPendiente) {
        this.iPendiente = iPendiente;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public Number getIcapital() {
        return icapital;
    }

    public void setIcapital(Number icapital) {
        this.icapital = icapital;
    }

    public Number getIcompensatorio() {
        return icompensatorio;
    }

    public void setIcompensatorio(Number icompensatorio) {
        this.icompensatorio = icompensatorio;
    }

    public Number getImoratorio() {
        return imoratorio;
    }

    public void setImoratorio(Number imoratorio) {
        this.imoratorio = imoratorio;
    }

    public String getbCancelado() {
        return bCancelado;
    }

    public void setbCancelado(String bCancelado) {
        this.bCancelado = bCancelado;
    }

    public Date getfCancelado() {
        return fCancelado;
    }

    public void setfCancelado(Date fCancelado) {
        this.fCancelado = fCancelado;
    }

    public String geteFormaCancel() {
        return eFormaCancel;
    }

    public void seteFormaCancel(String eFormaCancel) {
        this.eFormaCancel = eFormaCancel;
    }

    public Date getfProceso() {
        return fProceso;
    }

    public void setfProceso(Date fProceso) {
        this.fProceso = fProceso;
    }

    public Date getfPagoCrono() {
        return fPagoCrono;
    }

    public void setfPagoCrono(Date fPagoCrono) {
        this.fPagoCrono = fPagoCrono;
    }

    public Number getiCuota() {
        return iCuota;
    }

    public void setiCuota(Number iCuota) {
        this.iCuota = iCuota;
    }

    public String getDsecuencia() {
        return dsecuencia;
    }

    public void setDsecuencia(String dsecuencia) {
        this.dsecuencia = dsecuencia;
    }
    
    public Number getIca() {
        return ica;
    }

    public void setIca(Number ica) {
        this.ica = ica;
    }    
    
    public String geteNewflag() {
        return eNewflag;
    }

    public void seteNewflag(String eNewflag) {
        this.eNewflag = eNewflag;
    }

    /**
     * @return the totalCuota
     */
    public int getTotalCuota() {
        return totalCuota;
    }

    /**
     * @param totalCuota the totalCuota to set
     */
    public void setTotalCuota(int totalCuota) {
        this.totalCuota = totalCuota;
    }
    
    /**
     * @return the cronograma_pendiente
     */
    public int getCronograma_pendiente() {
        return cronograma_pendiente;
    }

    /**
     * @param cronograma_pendiente the cronograma_pendiente to set
     */
    public void setCronograma_pendiente(int cronograma_pendiente) {
        this.cronograma_pendiente = cronograma_pendiente;
    }    
}
