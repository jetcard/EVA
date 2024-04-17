/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.iface.ICobCronogramaMetaDetalleServ;
import pop.webcobranzas.negocio.INegCobCronogramaMetaDetalle;
import pop.webcobranzas.util.Utilidades;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;
import pop.comun.dominio.reporte.RepMetaRecaudo;
import pop.comun.dominio.CobCronogramaRecaudoResumen;

/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaDetalleServ implements ICobCronogramaMetaDetalleServ, Serializable {

    INegCobCronogramaMetaDetalle iNegCobCronogramaMetaDetalleEJB;
    
    @Override
    public Integer insertar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        return iNegCobCronogramaMetaDetalleEJB.insertar(oCobCronogramaMetaDetalle);
    }

    @Override
    public void borrar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCronogramaMetaDetalle> listarCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        return iNegCobCronogramaMetaDetalleEJB.buscarCronogramaMetaDetalle(oCobCronogramaMetaDetalle);
    }
    
    @Override
    public List<CobCronogramaMetaDetalle> listarRepCronogramaMetaDetalle(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        return iNegCobCronogramaMetaDetalleEJB.buscarRepCronogramaMetaDetalle(oCobCronogramaMetaDetalle);
    }
    
    @Override
    public void actualizar(CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        iNegCobCronogramaMetaDetalleEJB.actualizar(oCobCronogramaMetaDetalle);
    }
    
    @Override
    public void actualizarCambiarAsesor(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalleList) throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        iNegCobCronogramaMetaDetalleEJB.actualizarCambiarAsesor(oCobCronogramaMetaDetalleList);
    }    
    
    @Override
    public List<CobCronogramaMetaAgrupxFecha> listarCronogramaMetaAgrupxFecha(CobCronogramaMetaResumen oCobCronogramaMetaResumen)throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        return iNegCobCronogramaMetaDetalleEJB.buscarCronogramaMetaAgrupxFecha(oCobCronogramaMetaResumen);
    }
    
    @Override
    public RepMetaCobranza reporteMetaCobranza (CobCronogramaMetaDetalle oCobCronogramaMetaDetalle) throws Exception{
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        return iNegCobCronogramaMetaDetalleEJB.buscarReporteMetaCobranza(oCobCronogramaMetaDetalle);
    }
 
    @Override    
    public RepMetaRecaudo reporteMetaRecaudo (CobCronogramaRecaudoDetalle oCobCronogramaRecaudoDetalle) throws Exception{
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
      
        return iNegCobCronogramaMetaDetalleEJB.buscarReporteMetaRecaudo(oCobCronogramaRecaudoDetalle);
    }
    
    @Override
    public List<CobCronogramaRecaudoResumen> listarRepCronogramaRecaudoResumen(CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen) throws Exception {
        iNegCobCronogramaMetaDetalleEJB = (INegCobCronogramaMetaDetalle) Utilidades.getEJBRemote("SessionCobCronogramaMetaDetalle", INegCobCronogramaMetaDetalle.class.getName());     
        return iNegCobCronogramaMetaDetalleEJB.buscarRepCronogramaRecaudoResumen(oCobCronogramaRecaudoResumen);
    }
    
}