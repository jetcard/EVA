/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author RC433838
 */
public class MovimientoCartas  extends Base {
    
        private String codfondo;
	private String dvalor_bv;
	private String tipoformato;
	private String tipocarta;
	private Date femision;
	private Date frecibido;
	private String trecibido;
	private String sperrecibido;
	private String scomentariov;
	private String nom_archivov;
	private String doc_tipo;
	private Integer doc_numero;
	private String doc_situacion;
	private String req_tipo;
	private Integer req_numero;
        
        private MaeCustodia custodia;
        private CobTablas cartas;
        private MaeFondo fondo;
        private CobRequerimientoCartas requerimiento;
        

    /**
     * @return the codfondo
     */
    public String getCodfondo() {
        return codfondo;
    }

    /**
     * @param codfondo the codfondo to set
     */
    public void setCodfondo(String codfondo) {
        this.codfondo = codfondo;
    }

    /**
     * @return the dvalor_bv
     */
    public String getDvalor_bv() {
        return dvalor_bv;
    }

    /**
     * @param dvalor_bv the dvalor_bv to set
     */
    public void setDvalor_bv(String dvalor_bv) {
        this.dvalor_bv = dvalor_bv;
    }

    /**
     * @return the tipoformato
     */
    public String getTipoformato() {
        return tipoformato;
    }

    /**
     * @param tipoformato the tipoformato to set
     */
    public void setTipoformato(String tipoformato) {
        this.tipoformato = tipoformato;
    }

    /**
     * @return the tipocarta
     */
    public String getTipocarta() {
        return tipocarta;
    }

    /**
     * @param tipocarta the tipocarta to set
     */
    public void setTipocarta(String tipocarta) {
        this.tipocarta = tipocarta;
    }

    /**
     * @return the femision
     */
    public Date getFemision() {
        return femision;
    }

    /**
     * @param femision the femision to set
     */
    public void setFemision(Date femision) {
        this.femision = femision;
    }

    /**
     * @return the frecibido
     */
    public Date getFrecibido() {
        return frecibido;
    }

    /**
     * @param frecibido the frecibido to set
     */
    public void setFrecibido(Date frecibido) {
        this.frecibido = frecibido;
    }

    /**
     * @return the trecibido
     */
    public String getTrecibido() {
        return trecibido;
    }

    /**
     * @param trecibido the trecibido to set
     */
    public void setTrecibido(String trecibido) {
        this.trecibido = trecibido;
    }

    /**
     * @return the sperrecibido
     */
    public String getSperrecibido() {
        return sperrecibido;
    }

    /**
     * @param sperrecibido the sperrecibido to set
     */
    public void setSperrecibido(String sperrecibido) {
        this.sperrecibido = sperrecibido;
    }

    /**
     * @return the scomentariov
     */
    public String getScomentariov() {
        return scomentariov;
    }

    /**
     * @param scomentariov the scomentariov to set
     */
    public void setScomentariov(String scomentariov) {
        this.scomentariov = scomentariov;
    }

    /**
     * @return the nom_archivov
     */
    public String getNom_archivov() {
        return nom_archivov;
    }

    /**
     * @param nom_archivov the nom_archivov to set
     */
    public void setNom_archivov(String nom_archivov) {
        this.nom_archivov = nom_archivov;
    }

    /**
     * @return the doc_tipo
     */
    public String getDoc_tipo() {
        return doc_tipo;
    }

    /**
     * @param doc_tipo the doc_tipo to set
     */
    public void setDoc_tipo(String doc_tipo) {
        this.doc_tipo = doc_tipo;
    }

    /**
     * @return the doc_numero
     */
    public Integer getDoc_numero() {
        return doc_numero;
    }

    /**
     * @param doc_numero the doc_numero to set
     */
    public void setDoc_numero(Integer doc_numero) {
        this.doc_numero = doc_numero;
    }

    /**
     * @return the doc_situacion
     */
    public String getDoc_situacion() {
        return doc_situacion;
    }

    /**
     * @param doc_situacion the doc_situacion to set
     */
    public void setDoc_situacion(String doc_situacion) {
        this.doc_situacion = doc_situacion;
    }

    /**
     * @return the custodia
     */
    public MaeCustodia getCustodia() {
        return custodia;
    }

    /**
     * @param custodia the custodia to set
     */
    public void setCustodia(MaeCustodia custodia) {
        this.custodia = custodia;
    }

    /**
     * @return the cartas
     */
    public CobTablas getCartas() {
        return cartas;
    }

    /**
     * @param cartas the cartas to set
     */
    public void setCartas(CobTablas cartas) {
        this.cartas = cartas;
    }

    /**
     * @return the fondo
     */
    public MaeFondo getFondo() {
        return fondo;
    }

    /**
     * @param fondo the fondo to set
     */
    public void setFondo(MaeFondo fondo) {
        this.fondo = fondo;
    }

    /**
     * @return the req_tipo
     */
    public String getReq_tipo() {
        return req_tipo;
    }

    /**
     * @param req_tipo the req_tipo to set
     */
    public void setReq_tipo(String req_tipo) {
        this.req_tipo = req_tipo;
    }

    /**
     * @return the req_numero
     */
    public Integer getReq_numero() {
        return req_numero;
    }

    /**
     * @param req_numero the req_numero to set
     */
    public void setReq_numero(Integer req_numero) {
        this.req_numero = req_numero;
    }

    /**
     * @return the requerimiento
     */
    public CobRequerimientoCartas getRequerimiento() {
        return requerimiento;
    }

    /**
     * @param requerimiento the requerimiento to set
     */
    public void setRequerimiento(CobRequerimientoCartas requerimiento) {
        this.requerimiento = requerimiento;
    }


    
}
