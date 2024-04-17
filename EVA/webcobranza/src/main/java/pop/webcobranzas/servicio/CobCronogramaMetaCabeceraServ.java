/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaCabecera;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.webcobranzas.iface.ICobCronogramaMetaCabeceraServ;
import pop.webcobranzas.negocio.INegCobCronogramaMetaCabecera;
import pop.webcobranzas.util.Utilidades;
/**
 *
 * @author EC23329
 */
public class CobCronogramaMetaCabeceraServ implements ICobCronogramaMetaCabeceraServ, Serializable{
    INegCobCronogramaMetaCabecera iNegCobCronogramaMetaCabeceraEJB;
    
    @Override
    public Integer insertar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception {
        iNegCobCronogramaMetaCabeceraEJB = (INegCobCronogramaMetaCabecera) Utilidades.getEJBRemote("SessionCobCronogramaMetaCabecera", INegCobCronogramaMetaCabecera.class.getName());     
        return iNegCobCronogramaMetaCabeceraEJB.insertar(oCobCronogramaMetaCabecera);
    }
    
    @Override
    public void actualizar(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception {
        iNegCobCronogramaMetaCabeceraEJB = (INegCobCronogramaMetaCabecera) Utilidades.getEJBRemote("SessionCobCronogramaMetaCabecera", INegCobCronogramaMetaCabecera.class.getName());     
        iNegCobCronogramaMetaCabeceraEJB.actualizar(oCobCronogramaMetaCabecera);
    }
    
    @Override
    public Integer validarAprobacion(CobCronogramaMetaCabecera oCobCronogramaMetaCabecera) throws Exception {
        iNegCobCronogramaMetaCabeceraEJB = (INegCobCronogramaMetaCabecera) Utilidades.getEJBRemote("SessionCobCronogramaMetaCabecera", INegCobCronogramaMetaCabecera.class.getName());     
        return iNegCobCronogramaMetaCabeceraEJB.validarAprobacion(oCobCronogramaMetaCabecera);
    }
}
