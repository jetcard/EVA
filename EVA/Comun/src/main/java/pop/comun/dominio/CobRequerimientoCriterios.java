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
public class CobRequerimientoCriterios extends Base{
 
    private String reqTipo;
    private Integer reqNumero;
    private Integer criItem;

    
    private Integer cuota_ven_tot;
    private Date ult_pago;
    private Integer dias_sinpago;
    private Integer cant_2cuota_atras;
    private Integer frecuencia_pago;
    private String con_tchn;
    private String con_ashipo;
    private Integer cuota_total;
    private Integer cuota_generada;
    private Integer cuota_dia;
    private Date carta_notarial_f1;
    private Integer carta_notarial_r1;
    private String carta_notarial_o1;
    private Date carta_notarial_f2;
    private Integer carta_notarial_r2;
    private String carta_notarial_o2;
    private Date carta_notarial_f3;
    private Integer carta_notarial_r3;
    private String carta_notarial_o3;
    private Date protesto_notarial_f1;
    private Integer protesto_notarial_r1;
    private String protesto_notarial_o1;
    private Date carta_protesto_f1;
    private Integer carta_protesto_r1;
    private String carta_protesto_o1;
    private Date carta_prejecucion_f1;
    private Integer carta_prejecucion_r1;
    private String carta_prejecucion_o1;

    
    
    private CobRequerimientoCartas requerimiento;
    
    private boolean sel=false;    
    

    /**
     * @return the reqTipo
     */
    public String getReqTipo() {
        return reqTipo;
    }

    /**
     * @param reqTipo the reqTipo to set
     */
    public void setReqTipo(String reqTipo) {
        this.reqTipo = reqTipo;
    }

    /**
     * @return the reqNumero
     */
    public Integer getReqNumero() {
        return reqNumero;
    }

    /**
     * @param reqNumero the reqNumero to set
     */
    public void setReqNumero(Integer reqNumero) {
        this.reqNumero = reqNumero;
    }

    /**
     * @return the criItem
     */
    public Integer getCriItem() {
        return criItem;
    }

    /**
     * @param criItem the criItem to set
     */
    public void setCriItem(Integer criItem) {
        this.criItem = criItem;
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

    /**
     * @return the sel
     */
    public boolean isSel() {
        return sel;
    }

    /**
     * @param sel the sel to set
     */
    public void setSel(boolean sel) {
        this.sel = sel;
    }

    /**
     * @return the cuota_ven_tot
     */
    public Integer getCuota_ven_tot() {
        return cuota_ven_tot;
    }

    /**
     * @param cuota_ven_tot the cuota_ven_tot to set
     */
    public void setCuota_ven_tot(Integer cuota_ven_tot) {
        this.cuota_ven_tot = cuota_ven_tot;
    }

    /**
     * @return the ult_pago
     */
    public Date getUlt_pago() {
        return ult_pago;
    }

    /**
     * @param ult_pago the ult_pago to set
     */
    public void setUlt_pago(Date ult_pago) {
        this.ult_pago = ult_pago;
    }

    /**
     * @return the dias_sinpago
     */
    public Integer getDias_sinpago() {
        return dias_sinpago;
    }

    /**
     * @param dias_sinpago the dias_sinpago to set
     */
    public void setDias_sinpago(Integer dias_sinpago) {
        this.dias_sinpago = dias_sinpago;
    }

    /**
     * @return the cant_2cuota_atras
     */
    public Integer getCant_2cuota_atras() {
        return cant_2cuota_atras;
    }

    /**
     * @param cant_2cuota_atras the cant_2cuota_atras to set
     */
    public void setCant_2cuota_atras(Integer cant_2cuota_atras) {
        this.cant_2cuota_atras = cant_2cuota_atras;
    }

    /**
     * @return the frecuencia_pago
     */
    public Integer getFrecuencia_pago() {
        return frecuencia_pago;
    }

    /**
     * @param frecuencia_pago the frecuencia_pago to set
     */
    public void setFrecuencia_pago(Integer frecuencia_pago) {
        this.frecuencia_pago = frecuencia_pago;
    }

    /**
     * @return the con_tchn
     */
    public String getCon_tchn() {
        return con_tchn;
    }

    /**
     * @param con_tchn the con_tchn to set
     */
    public void setCon_tchn(String con_tchn) {
        this.con_tchn = con_tchn;
    }

    /**
     * @return the con_ashipo
     */
    public String getCon_ashipo() {
        return con_ashipo;
    }

    /**
     * @param con_ashipo the con_ashipo to set
     */
    public void setCon_ashipo(String con_ashipo) {
        this.con_ashipo = con_ashipo;
    }

    /**
     * @return the carta_notarial_f1
     */
    public Date getCarta_notarial_f1() {
        return carta_notarial_f1;
    }

    /**
     * @param carta_notarial_f1 the carta_notarial_f1 to set
     */
    public void setCarta_notarial_f1(Date carta_notarial_f1) {
        this.carta_notarial_f1 = carta_notarial_f1;
    }

    /**
     * @return the carta_notarial_r1
     */
    public Integer getCarta_notarial_r1() {
        return carta_notarial_r1;
    }

    /**
     * @param carta_notarial_r1 the carta_notarial_r1 to set
     */
    public void setCarta_notarial_r1(Integer carta_notarial_r1) {
        this.carta_notarial_r1 = carta_notarial_r1;
    }

    /**
     * @return the carta_notarial_o1
     */
    public String getCarta_notarial_o1() {
        return carta_notarial_o1;
    }

    /**
     * @param carta_notarial_o1 the carta_notarial_o1 to set
     */
    public void setCarta_notarial_o1(String carta_notarial_o1) {
        this.carta_notarial_o1 = carta_notarial_o1;
    }

    /**
     * @return the carta_notarial_f2
     */
    public Date getCarta_notarial_f2() {
        return carta_notarial_f2;
    }

    /**
     * @param carta_notarial_f2 the carta_notarial_f2 to set
     */
    public void setCarta_notarial_f2(Date carta_notarial_f2) {
        this.carta_notarial_f2 = carta_notarial_f2;
    }

    /**
     * @return the carta_notarial_r2
     */
    public Integer getCarta_notarial_r2() {
        return carta_notarial_r2;
    }

    /**
     * @param carta_notarial_r2 the carta_notarial_r2 to set
     */
    public void setCarta_notarial_r2(Integer carta_notarial_r2) {
        this.carta_notarial_r2 = carta_notarial_r2;
    }

    /**
     * @return the carta_notarial_o2
     */
    public String getCarta_notarial_o2() {
        return carta_notarial_o2;
    }

    /**
     * @param carta_notarial_o2 the carta_notarial_o2 to set
     */
    public void setCarta_notarial_o2(String carta_notarial_o2) {
        this.carta_notarial_o2 = carta_notarial_o2;
    }

    /**
     * @return the carta_notarial_f3
     */
    public Date getCarta_notarial_f3() {
        return carta_notarial_f3;
    }

    /**
     * @param carta_notarial_f3 the carta_notarial_f3 to set
     */
    public void setCarta_notarial_f3(Date carta_notarial_f3) {
        this.carta_notarial_f3 = carta_notarial_f3;
    }

    /**
     * @return the carta_notarial_r3
     */
    public Integer getCarta_notarial_r3() {
        return carta_notarial_r3;
    }

    /**
     * @param carta_notarial_r3 the carta_notarial_r3 to set
     */
    public void setCarta_notarial_r3(Integer carta_notarial_r3) {
        this.carta_notarial_r3 = carta_notarial_r3;
    }

    /**
     * @return the carta_notarial_o3
     */
    public String getCarta_notarial_o3() {
        return carta_notarial_o3;
    }

    /**
     * @param carta_notarial_o3 the carta_notarial_o3 to set
     */
    public void setCarta_notarial_o3(String carta_notarial_o3) {
        this.carta_notarial_o3 = carta_notarial_o3;
    }

    /**
     * @return the protesto_notarial_f1
     */
    public Date getProtesto_notarial_f1() {
        return protesto_notarial_f1;
    }

    /**
     * @param protesto_notarial_f1 the protesto_notarial_f1 to set
     */
    public void setProtesto_notarial_f1(Date protesto_notarial_f1) {
        this.protesto_notarial_f1 = protesto_notarial_f1;
    }

    /**
     * @return the protesto_notarial_r1
     */
    public Integer getProtesto_notarial_r1() {
        return protesto_notarial_r1;
    }

    /**
     * @param protesto_notarial_r1 the protesto_notarial_r1 to set
     */
    public void setProtesto_notarial_r1(Integer protesto_notarial_r1) {
        this.protesto_notarial_r1 = protesto_notarial_r1;
    }

    /**
     * @return the protesto_notarial_o1
     */
    public String getProtesto_notarial_o1() {
        return protesto_notarial_o1;
    }

    /**
     * @param protesto_notarial_o1 the protesto_notarial_o1 to set
     */
    public void setProtesto_notarial_o1(String protesto_notarial_o1) {
        this.protesto_notarial_o1 = protesto_notarial_o1;
    }

    /**
     * @return the carta_protesto_f1
     */
    public Date getCarta_protesto_f1() {
        return carta_protesto_f1;
    }

    /**
     * @param carta_protesto_f1 the carta_protesto_f1 to set
     */
    public void setCarta_protesto_f1(Date carta_protesto_f1) {
        this.carta_protesto_f1 = carta_protesto_f1;
    }

    /**
     * @return the carta_protesto_r1
     */
    public Integer getCarta_protesto_r1() {
        return carta_protesto_r1;
    }

    /**
     * @param carta_protesto_r1 the carta_protesto_r1 to set
     */
    public void setCarta_protesto_r1(Integer carta_protesto_r1) {
        this.carta_protesto_r1 = carta_protesto_r1;
    }

    /**
     * @return the carta_protesto_o1
     */
    public String getCarta_protesto_o1() {
        return carta_protesto_o1;
    }

    /**
     * @param carta_protesto_o1 the carta_protesto_o1 to set
     */
    public void setCarta_protesto_o1(String carta_protesto_o1) {
        this.carta_protesto_o1 = carta_protesto_o1;
    }

    /**
     * @return the carta_prejecucion_f1
     */
    public Date getCarta_prejecucion_f1() {
        return carta_prejecucion_f1;
    }

    /**
     * @param carta_prejecucion_f1 the carta_prejecucion_f1 to set
     */
    public void setCarta_prejecucion_f1(Date carta_prejecucion_f1) {
        this.carta_prejecucion_f1 = carta_prejecucion_f1;
    }

    /**
     * @return the carta_prejecucion_r1
     */
    public Integer getCarta_prejecucion_r1() {
        return carta_prejecucion_r1;
    }

    /**
     * @param carta_prejecucion_r1 the carta_prejecucion_r1 to set
     */
    public void setCarta_prejecucion_r1(Integer carta_prejecucion_r1) {
        this.carta_prejecucion_r1 = carta_prejecucion_r1;
    }

    /**
     * @return the carta_prejecucion_o1
     */
    public String getCarta_prejecucion_o1() {
        return carta_prejecucion_o1;
    }

    /**
     * @param carta_prejecucion_o1 the carta_prejecucion_o1 to set
     */
    public void setCarta_prejecucion_o1(String carta_prejecucion_o1) {
        this.carta_prejecucion_o1 = carta_prejecucion_o1;
    }
    
    /**
     * @return the carta_prejecucion_o1
     */
    public Integer getCarta_notarial_reiteros() {
        Integer valor = 0;
        if(getCarta_notarial_r1()!=null)
            valor = getCarta_notarial_r1();

        if(getCarta_notarial_r2()!=null)
            valor = valor + getCarta_notarial_r2();
        
        if(getCarta_notarial_r3()!=null)
            valor = valor + getCarta_notarial_r3();
        
     if(valor == 0)
         return null;
     else        
        return valor;
     
    }    

    /**
     * @return the cuota_total
     */
    public Integer getCuota_total() {
        return cuota_total;
    }

    /**
     * @param cuota_total the cuota_total to set
     */
    public void setCuota_total(Integer cuota_total) {
        this.cuota_total = cuota_total;
    }

    /**
     * @return the cuota_generada
     */
    public Integer getCuota_generada() {
        return cuota_generada;
    }

    /**
     * @param cuota_generada the cuota_generada to set
     */
    public void setCuota_generada(Integer cuota_generada) {
        this.cuota_generada = cuota_generada;
    }

    /**
     * @return the cuota_dia
     */
    public Integer getCuota_dia() {
        return cuota_dia;
    }

    /**
     * @param cuota_dia the cuota_dia to set
     */
    public void setCuota_dia(Integer cuota_dia) {
        this.cuota_dia = cuota_dia;
    }

    
}
