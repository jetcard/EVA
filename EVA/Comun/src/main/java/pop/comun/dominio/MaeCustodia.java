/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author RC433838
 */
public class MaeCustodia extends Base{
    
        private String c_fondo_id;
        private String dvalor_bv;
        private String documento_id;
        private Date f_registro;
        private String c_estado;
        private String c_comentario;
        private String c_subsanado;
        private Date f_subsanado;	
        private String selecionado;
        private String cerrado;
        private String s_digital;
        private String s_escustodia;
        private String s_destino;
        private Date f_escritura;
        private String s_notaria;
        private String s_ashipo;
        private String s_asexptchn;
        private String s_pehipo;
        private String s_aspoder;
        private String s_pepoder;   
        private String s_tchn;
        private String c_origen;
        private Date d_fgiro;
        
        private MaeInversion inversion;
        private List<MovimientoCartas> cartas;
        private CobTablas estados;
        private CobTablas documentos;

    /**
     * @return the c_fondo_id
     */
    public String getC_fondo_id() {
        return c_fondo_id;
    }

    /**
     * @param c_fondo_id the c_fondo_id to set
     */
    public void setC_fondo_id(String c_fondo_id) {
        this.c_fondo_id = c_fondo_id;
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
     * @return the documento_id
     */
    public String getDocumento_id() {
        return documento_id;
    }

    /**
     * @param documento_id the documento_id to set
     */
    public void setDocumento_id(String documento_id) {
        this.documento_id = documento_id;
    }

    /**
     * @return the f_registro
     */
    public Date getF_registro() {
        return f_registro;
    }

    /**
     * @param f_registro the f_registro to set
     */
    public void setF_registro(Date f_registro) {
        this.f_registro = f_registro;
    }

    /**
     * @return the c_estado
     */
    public String getC_estado() {
        return c_estado;
    }

    /**
     * @param c_estado the c_estado to set
     */
    public void setC_estado(String c_estado) {
        this.c_estado = c_estado;
    }

    /**
     * @return the c_comentario
     */
    public String getC_comentario() {
        return c_comentario;
    }

    /**
     * @param c_comentario the c_comentario to set
     */
    public void setC_comentario(String c_comentario) {
        this.c_comentario = c_comentario;
    }

    /**
     * @return the c_subsanado
     */
    public String getC_subsanado() {
        return c_subsanado;
    }

    /**
     * @param c_subsanado the c_subsanado to set
     */
    public void setC_subsanado(String c_subsanado) {
        this.c_subsanado = c_subsanado;
    }

    /**
     * @return the f_subsanado
     */
    public Date getF_subsanado() {
        return f_subsanado;
    }

    /**
     * @param f_subsanado the f_subsanado to set
     */
    public void setF_subsanado(Date f_subsanado) {
        this.f_subsanado = f_subsanado;
    }

    /**
     * @return the selecionado
     */
    public String getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(String selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the cerrado
     */
    public String getCerrado() {
        return cerrado;
    }

    /**
     * @param cerrado the cerrado to set
     */
    public void setCerrado(String cerrado) {
        this.cerrado = cerrado;
    }

    /**
     * @return the s_digital
     */
    public String getS_digital() {
        return s_digital;
    }

    /**
     * @param s_digital the s_digital to set
     */
    public void setS_digital(String s_digital) {
        this.s_digital = s_digital;
    }

    /**
     * @return the s_escustodia
     */
    public String getS_escustodia() {
        return s_escustodia;
    }

    /**
     * @param s_escustodia the s_escustodia to set
     */
    public void setS_escustodia(String s_escustodia) {
        this.s_escustodia = s_escustodia;
    }

    /**
     * @return the s_destino
     */
    public String getS_destino() {
        return s_destino;
    }

    /**
     * @param s_destino the s_destino to set
     */
    public void setS_destino(String s_destino) {
        this.s_destino = s_destino;
    }

    /**
     * @return the f_escritura
     */
    public Date getF_escritura() {
        return f_escritura;
    }

    /**
     * @param f_escritura the f_escritura to set
     */
    public void setF_escritura(Date f_escritura) {
        this.f_escritura = f_escritura;
    }

    /**
     * @return the s_notaria
     */
    public String getS_notaria() {
        return s_notaria;
    }

    /**
     * @param s_notaria the s_notaria to set
     */
    public void setS_notaria(String s_notaria) {
        this.s_notaria = s_notaria;
    }

    /**
     * @return the s_ashipo
     */
    public String getS_ashipo() {
        return s_ashipo;
    }

    /**
     * @param s_ashipo the s_ashipo to set
     */
    public void setS_ashipo(String s_ashipo) {
        this.s_ashipo = s_ashipo;
    }

    /**
     * @return the s_asexptchn
     */
    public String getS_asexptchn() {
        return s_asexptchn;
    }

    /**
     * @param s_asexptchn the s_asexptchn to set
     */
    public void setS_asexptchn(String s_asexptchn) {
        this.s_asexptchn = s_asexptchn;
    }

    /**
     * @return the s_pehipo
     */
    public String getS_pehipo() {
        return s_pehipo;
    }

    /**
     * @param s_pehipo the s_pehipo to set
     */
    public void setS_pehipo(String s_pehipo) {
        this.s_pehipo = s_pehipo;
    }

    /**
     * @return the s_aspoder
     */
    public String getS_aspoder() {
        return s_aspoder;
    }

    /**
     * @param s_aspoder the s_aspoder to set
     */
    public void setS_aspoder(String s_aspoder) {
        this.s_aspoder = s_aspoder;
    }

    /**
     * @return the s_pepoder
     */
    public String getS_pepoder() {
        return s_pepoder;
    }

    /**
     * @param s_pepoder the s_pepoder to set
     */
    public void setS_pepoder(String s_pepoder) {
        this.s_pepoder = s_pepoder;
    }

    /**
     * @return the s_tchn
     */
    public String getS_tchn() {
        return s_tchn;
    }

    /**
     * @param s_tchn the s_tchn to set
     */
    public void setS_tchn(String s_tchn) {
        this.s_tchn = s_tchn;
    }

    /**
     * @return the c_origen
     */
    public String getC_origen() {
        return c_origen;
    }

    /**
     * @param c_origen the c_origen to set
     */
    public void setC_origen(String c_origen) {
        this.c_origen = c_origen;
    }

    /**
     * @return the d_fgiro
     */
    public Date getD_fgiro() {
        return d_fgiro;
    }

    /**
     * @param d_fgiro the d_fgiro to set
     */
    public void setD_fgiro(Date d_fgiro) {
        this.d_fgiro = d_fgiro;
    }

    /**
     * @return the cartas
     */
    public List<MovimientoCartas> getCartas() {
        return cartas;
    }

    /**
     * @param cartas the cartas to set
     */
    public void setCartas(List<MovimientoCartas> cartas) {
        this.cartas = cartas;
    }

    /**
     * @return the inversion
     */
    public MaeInversion getInversion() {
        return inversion;
    }

    /**
     * @param inversion the inversion to set
     */
    public void setInversion(MaeInversion inversion) {
        this.inversion = inversion;
    }

    /**
     * @return the estados
     */
    public CobTablas getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(CobTablas estados) {
        this.estados = estados;
    }

    /**
     * @return the documentos
     */
    public CobTablas getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(CobTablas documentos) {
        this.documentos = documentos;
    }

   
    
}
