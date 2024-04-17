/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.ArrayList;

/**
 *
 * @author Jyoverar
 */
public class CobLlamadas extends CobSeguimientoDet{

    /**
     * @return the cumplimientoDia
     */
    public int getCumplimientoDia() {
        return cumplimientoDia;
    }

    /**
     * @param cumplimientoDia the cumplimientoDia to set
     */
    public void setCumplimientoDia(int cumplimientoDia) {
        this.cumplimientoDia = cumplimientoDia;
    }


    
    private Number ccodLlamadaId;
    private String ccodLlamada;
    private String ccodDisposicionId;
    private String ctipoFamiliaId;
    private String dtipoFamilia;
    private String csituacionId;
    private String ddescripcion;
    private int totalMes;
    private int totalDia;
    private int cumplimientoMes;
    private int cumplimientoDia;
    
    
               
    
    private CobTablas tipoFamilia;
    private CobTablas tipoAccion;
    //private CobSeguimiento cobSeguimiento;
    
    private ArrayList<CobCdr> cobCdrLits;
    
    private CobCompromiso cobCompromiso;  

    public CobLlamadas() {
    }
    
    public Number getCcodLlamadaId() {
        return ccodLlamadaId;
    }

    public void setCcodLlamadaId(Number ccodLlamadaId) {
        this.ccodLlamadaId = ccodLlamadaId;
    }

    public String getCcodLlamada() {
        return ccodLlamada;
    }

    public void setCcodLlamada(String ccodLlamada) {
        this.ccodLlamada = ccodLlamada;
    }

    public String getCcodDisposicionId() {
        return ccodDisposicionId;
    }

    public void setCcodDisposicionId(String ccodDisposicionId) {
        this.ccodDisposicionId = ccodDisposicionId;
    }

    public String getCtipoFamiliaId() {
        return ctipoFamiliaId;
    }

    public void setCtipoFamiliaId(String ctipoFamiliaId) {
        this.ctipoFamiliaId = ctipoFamiliaId;
    }

    public String getDtipoFamilia() {
        return dtipoFamilia;
    }

    public void setDtipoFamilia(String dtipoFamilia) {
        this.dtipoFamilia = dtipoFamilia;
    }

    public String getCsituacionId() {
        return csituacionId;
    }

    public void setCsituacionId(String csituacionId) {
        this.csituacionId = csituacionId;
    }

    public String getDdescripcion() {
        return ddescripcion;
    }

    public void setDdescripcion(String ddescripcion) {
        this.ddescripcion = ddescripcion;
    }

    public CobTablas getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(CobTablas tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

    public CobTablas getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(CobTablas tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public ArrayList<CobCdr> getCobCdrLits() {
        return cobCdrLits;
    }

    public void setCobCdrLits(ArrayList<CobCdr> cobCdrLits) {
        this.cobCdrLits = cobCdrLits;
    }

//    public CobSeguimiento getCobSeguimiento() {
//        return cobSeguimiento;
//    }
//
//    public void setCobSeguimiento(CobSeguimiento cobSeguimiento) {
//        this.cobSeguimiento = cobSeguimiento;
//    }

    public CobCompromiso getCobCompromiso() {
        return cobCompromiso;
    }

    public void setCobCompromiso(CobCompromiso cobCompromiso) {
        this.cobCompromiso = cobCompromiso;
    }

    /**
     * @return the totalMes
     */
    public int getTotalMes() {
        return totalMes;
    }

    /**
     * @param totalMes the totalMes to set
     */
    public void setTotalMes(int totalMes) {
        this.totalMes = totalMes;
    }
    
    /**
     * @return the cumplimientoMes
     */
    public int getCumplimientoMes() {
        return cumplimientoMes;
    }

    /**
     * @param cumplimientoMes the cumplimientoMes to set
     */
    public void setCumplimientoMes(int cumplimientoMes) {
        this.cumplimientoMes = cumplimientoMes;
    }    

    /**
     * @return the totalDia
     */
    public int getTotalDia() {
        return totalDia;
    }

    /**
     * @param totalDia the totalDia to set
     */
    public void setTotalDia(int totalDia) {
        this.totalDia = totalDia;
    }

      

}
