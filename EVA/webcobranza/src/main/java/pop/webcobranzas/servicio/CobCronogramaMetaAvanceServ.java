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
import pop.comun.dominio.CobCronogramaMetaAvance;
import pop.webcobranzas.iface.ICobCronogramaMetaAvanceServ;
import pop.webcobranzas.iface.ICobCronogramaMetaDetalleServ;
import pop.webcobranzas.negocio.INegCobCronogramaMetaAvance;
import pop.webcobranzas.negocio.INegCobCronogramaMetaDetalle;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author EC23329
 */
public class CobCronogramaMetaAvanceServ implements ICobCronogramaMetaAvanceServ, Serializable {
    
    INegCobCronogramaMetaAvance iNegCobCronogramaMetaAvanceEJB;
    
    @Override
    public List<CobCronogramaMetaAvance> listarRepCronogramaMetaAvance(CobCronogramaMetaAvance oCobCronogramaMetaAvance) throws Exception {
        iNegCobCronogramaMetaAvanceEJB = (INegCobCronogramaMetaAvance) Utilidades.getEJBRemote("SessionCobCronogramaMetaAvance", INegCobCronogramaMetaAvance.class.getName());     
        return iNegCobCronogramaMetaAvanceEJB.buscarCobCronogramaMetaAvance(oCobCronogramaMetaAvance);
        
    }
}
