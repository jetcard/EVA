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
public class CobCompromiso extends CobSeguimientoDet{
    
    private Number ccodCompromisoId;
    private String ccodCompromiso;
    private Date ffecha;
    private double imonto;
    private String dcuota;
    private String eestadoId;
    private Date ffecObs;
    private String dobservacion;
    private String drespuesta;
    private String ctipoNexoId;
    private Number cnexoId;
    private double imontoDepostado;
    //fecha de ejecucion
    private Date finiBusqFecEje;
    private Date ffinBusqFecEje;
    
    private List<MaeDeposito> maeDepositos;
    
    private CobMaeSeguimiento cobMaeSeguimiento;
    //private CobSeguimiento cobSeguimiento;

    public Number getCcodCompromisoId() {
        return ccodCompromisoId;
    }

    public void setCcodCompromisoId(Number ccodCompromisoId) {
        this.ccodCompromisoId = ccodCompromisoId;
    }

    public String getCcodCompromiso() {
        return ccodCompromiso;
    }

    public void setCcodCompromiso(String ccodCompromiso) {
        this.ccodCompromiso = ccodCompromiso;
    }

    public Date getFfecha() {
        return ffecha;
    }

    public void setFfecha(Date ffecha) {
        this.ffecha = ffecha;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }

    public Date getFfecObs() {
        return ffecObs;
    }

    public void setFfecObs(Date ffecObs) {
        this.ffecObs = ffecObs;
    }

    public String getDobservacion() {
        return dobservacion;
    }

    public void setDobservacion(String dobservacion) {
        this.dobservacion = dobservacion;
    }

    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public String getDcuota() {
        return dcuota;
    }

    public void setDcuota(String dcuota) {
        this.dcuota = dcuota;
    }

    public double getImonto() {
        return imonto;
    }

    public void setImonto(double imonto) {
        this.imonto = imonto;
    }

    public String getDrespuesta() {
        return drespuesta;
    }

    public void setDrespuesta(String drespuesta) {
        this.drespuesta = drespuesta;
    }

//    public CobSeguimiento getCobSeguimiento() {
//        return cobSeguimiento;
//    }
//
//    public void setCobSeguimiento(CobSeguimiento cobSeguimiento) {
//        this.cobSeguimiento = cobSeguimiento;
//    }

    public String getCtipoNexoId() {
        return ctipoNexoId;
    }

    public void setCtipoNexoId(String ctipoNexoId) {
        this.ctipoNexoId = ctipoNexoId;
    }

    public Number getCnexoId() {
        return cnexoId;
    }

    public void setCnexoId(Number cnexoId) {
        this.cnexoId = cnexoId;
    }

    public List<MaeDeposito> getMaeDepositos() {
        return maeDepositos;
    }

    public void setMaeDepositos(List<MaeDeposito> maeDepositos) {
        this.maeDepositos = maeDepositos;
    }

    public double getImontoDepostado() {
        return imontoDepostado;
    }

    public void setImontoDepostado(double imontoDepostado) {
        this.imontoDepostado = imontoDepostado;
    }

    public Date getFiniBusqFecEje() {
        return finiBusqFecEje;
    }

    public void setFiniBusqFecEje(Date finiBusqFecEje) {
        this.finiBusqFecEje = finiBusqFecEje;
    }

    public Date getFfinBusqFecEje() {
        return ffinBusqFecEje;
    }

    public void setFfinBusqFecEje(Date ffinBusqFecEje) {
        this.ffinBusqFecEje = ffinBusqFecEje;
    }

    

}
