/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class CobSeguimiento extends Base{
    
    private CobMaeSeguimiento cobMaeSeguimiento;
    private int ccobSeguimientoId;
    private Date ffecIni;
    private Date ffecFin;
    private String eestadoId;
    private CobCronogramaMetaDetalle metaDetalle;
    private MaeCuotaPago cuotaPago;
    private MaeEstadoCuenta estadoCuenta;

    private ArrayList<CobSeguimientoDet> cobSeguimientoDetList;
    
    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public int getCcobSeguimientoId() {
        return ccobSeguimientoId;
    }

    public void setCcobSeguimientoId(int ccobSeguimientoId) {
        this.ccobSeguimientoId = ccobSeguimientoId;
    }

    public Date getFfecIni() {
        return ffecIni;
    }

    public void setFfecIni(Date ffecIni) {
        this.ffecIni = ffecIni;
    }

    public Date getFfecFin() {
        return ffecFin;
    }

    public void setFfecFin(Date ffecFin) {
        this.ffecFin = ffecFin;
    }

    public String getEestadoId() {
        return eestadoId;
    }

    public void setEestadoId(String eestadoId) {
        this.eestadoId = eestadoId;
    }

    public ArrayList<CobSeguimientoDet> getCobSeguimientoDetList() {
        return cobSeguimientoDetList;
    }

    public void setCobSeguimientoDetList(ArrayList<CobSeguimientoDet> cobSeguimientoDetList) {
        this.cobSeguimientoDetList = cobSeguimientoDetList;
    }

    /**
     * @return the metaDetalle
     */
    public CobCronogramaMetaDetalle getMetaDetalle() {
        return metaDetalle;
    }

    /**
     * @param metaDetalle the metaDetalle to set
     */
    public void setMetaDetalle(CobCronogramaMetaDetalle metaDetalle) {
        this.metaDetalle = metaDetalle;
    }

    /**
     * @return the cuotaPago
     */
    public MaeCuotaPago getCuotaPago() {
        return cuotaPago;
    }

    /**
     * @param cuotaPago the cuotaPago to set
     */
    public void setCuotaPago(MaeCuotaPago cuotaPago) {
        this.cuotaPago = cuotaPago;
    }

    /**
     * @return the estadoCuenta
     */
    public MaeEstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    /**
     * @param estadoCuenta the estadoCuenta to set
     */
    public void setEstadoCuenta(MaeEstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
    
    
}
