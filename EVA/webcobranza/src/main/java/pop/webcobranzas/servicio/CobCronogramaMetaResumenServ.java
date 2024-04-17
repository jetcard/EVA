/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.iface.ICobCronogramaMetaResumenServ;
import pop.webcobranzas.negocio.INegCobCronogramaMetaResumen;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author HH38092
 */
public class CobCronogramaMetaResumenServ implements ICobCronogramaMetaResumenServ, Serializable {

    INegCobCronogramaMetaResumen iNegCobCronogramaMetaResumenEJB;
    
    @Override
    public Integer insertar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        iNegCobCronogramaMetaResumenEJB = (INegCobCronogramaMetaResumen) Utilidades.getEJBRemote("SessionCobCronogramaMetaResumen", INegCobCronogramaMetaResumen.class.getName());     
        return iNegCobCronogramaMetaResumenEJB.insertar(oCobCronogramaMetaResumen);
    }

    @Override
    public void borrar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCronogramaMetaResumen> listarCronogramaMetaResumen(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        iNegCobCronogramaMetaResumenEJB = (INegCobCronogramaMetaResumen) Utilidades.getEJBRemote("SessionCobCronogramaMetaResumen", INegCobCronogramaMetaResumen.class.getName());     
        return iNegCobCronogramaMetaResumenEJB.buscarCronogramaMetaResumen(oCobCronogramaMetaResumen);
    }

    @Override
    public void actualizar(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        iNegCobCronogramaMetaResumenEJB = (INegCobCronogramaMetaResumen) Utilidades.getEJBRemote("SessionCobCronogramaMetaResumen", INegCobCronogramaMetaResumen.class.getName());     
        iNegCobCronogramaMetaResumenEJB.actualizar(oCobCronogramaMetaResumen);
    }
 
    @Override
    public List<CobCronogramaMetaResumen> listarCronogramaMetaFondo(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        iNegCobCronogramaMetaResumenEJB = (INegCobCronogramaMetaResumen) Utilidades.getEJBRemote("SessionCobCronogramaMetaResumen", INegCobCronogramaMetaResumen.class.getName());     
        return iNegCobCronogramaMetaResumenEJB.buscarCronogramaMetaResumenxFondo(oCobCronogramaMetaResumen);
    }

    @Override
    public List<CobCronogramaMetaResumen> listarCronogramaMetaFondoxAsesor(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        iNegCobCronogramaMetaResumenEJB = (INegCobCronogramaMetaResumen) Utilidades.getEJBRemote("SessionCobCronogramaMetaResumen", INegCobCronogramaMetaResumen.class.getName());     
        return iNegCobCronogramaMetaResumenEJB.buscarCronogramaMetaResumenxFondoxAsesor(oCobCronogramaMetaResumen);
    }    
    
    @Override
    public List<CobCronogramaMetaResumen> listarCronogramaMetaFondoxAsesorJ(CobCronogramaMetaResumen oCobCronogramaMetaResumen) throws Exception {
        iNegCobCronogramaMetaResumenEJB = (INegCobCronogramaMetaResumen) Utilidades.getEJBRemote("SessionCobCronogramaMetaResumen", INegCobCronogramaMetaResumen.class.getName());     
        return iNegCobCronogramaMetaResumenEJB.buscarCronogramaMetaResumenxFondoxAsesorJ(oCobCronogramaMetaResumen);
    } 
}