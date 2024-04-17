/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author HH38092
 */
public class LegGastoJudicial extends Base{
    private int idGastoJudicial;
    private String codigoTCHN;
    private String fondo;
    private String etapa;
    private Date fecha;
    private String nroComprobante;
    private String nroexpediente;
    private String tipoGasto;
    private Double monto;
    private String comentario;
    
    private String DescEtapa;
    private String DescTipogasto;
    private String numeroDocumento;
 
    /**
     * @return the idGastoJudicial
     */
    public int getIdGastoJudicial() {
        return idGastoJudicial;
    }

    /**
     * @param idGastoJudicial the idGastoJudicial to set
     */
    public void setIdGastoJudicial(int idGastoJudicial) {
        this.idGastoJudicial = idGastoJudicial;
    }

    /**
     * @return the codigoTCHN
     */
    public String getCodigoTCHN() {
        return codigoTCHN;
    }

    /**
     * @param codigoTCHN the codigoTCHN to set
     */
    public void setCodigoTCHN(String codigoTCHN) {
        this.codigoTCHN = codigoTCHN;
    }

    /**
     * @return the fondo
     */
    public String getFondo() {
        return fondo;
    }

    /**
     * @param fondo the fondo to set
     */
    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    /**
     * @return the etapa
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * @param etapa the etapa to set
     */
    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nroComprobante
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * @param nroComprobante the nroComprobante to set
     */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    /**
     * @return the nroexpediente
     */
    public String getNroexpediente() {
        return nroexpediente;
    }

    /**
     * @param nroexpediente the nroexpediente to set
     */
    public void setNroexpediente(String nroexpediente) {
        this.nroexpediente = nroexpediente;
    }

    /**
     * @return the tipoGasto
     */
    public String getTipoGasto() {
        return tipoGasto;
    }

    /**
     * @param tipoGasto the tipoGasto to set
     */
    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the DescEtapa
     */
    public String getDescEtapa() {
        return DescEtapa;
    }

    /**
     * @param DescEtapa the DescEtapa to set
     */
    public void setDescEtapa(String DescEtapa) {
        this.DescEtapa = DescEtapa;
    }

    /**
     * @return the DescTipogasto
     */
    public String getDescTipogasto() {
        return DescTipogasto;
    }

    /**
     * @param DescTipogasto the DescTipogasto to set
     */
    public void setDescTipogasto(String DescTipogasto) {
        this.DescTipogasto = DescTipogasto;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public String toString() {
        return "LegGastoJudicial{" + "idGastoJudicial=" + idGastoJudicial + ", codigoTCHN=" + codigoTCHN + ", fondo=" + fondo + ", etapa=" + etapa + ", fecha=" + fecha + ", nroComprobante=" + nroComprobante + ", nroexpediente=" + nroexpediente + ", tipoGasto=" + tipoGasto + ", monto=" + monto + ", comentario=" + comentario + ", DescEtapa=" + DescEtapa + ", DescTipogasto=" + DescTipogasto + ", numeroDocumento=" + numeroDocumento + '}';
    }
        
}
