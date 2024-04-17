/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;
 

import java.util.List;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.MaeReporte;
import pop.webcobranzas.ifacerep.IRepMetasCobranzasServ;
import pop.webcobranzas.procesos.IRepMetasCobranzas;
import pop.webcobranzas.util.Utilidades;
import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.reporte.RepMetaRecaudo;

/**
 *
 * @author EC23329
 */
public class RepMetasCobranzasServ implements IRepMetasCobranzasServ{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    IRepMetasCobranzas iRepMetasCobranzasEJB;

    //@Override
    //public byte[] exportarSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception {
    //    iRepSaldoDeudorEJB = (IRepSaldoDeudor) Utilidades.getEJBRemoteRep("SessionSaldoDeudor", IRepSaldoDeudor.class.getName());
    //    return iRepSaldoDeudorEJB.exportarSaldoDeudor(oSaldoDeudor, oMaeReporte);
    //}
    
    //@Override
    //public byte[] exportartRepMetasCobranzas(List<CobCronogramaMetaDetalle> oCobCronogramaMetaDetalle) throws Exception{
    //    iRepMetasCobranzasEJB = (IRepMetasCobranzas) Utilidades.getEJBRemoteRep("SessionMetasCobranzasRep", IRepMetasCobranzas.class.getName());
    //    return iRepMetasCobranzasEJB.exportartRepMetasCobranzas(oCobCronogramaMetaDetalle);
    //}
    
    @Override
    public byte[] exportartRepMetasCobranzas(RepMetaCobranza oRepMetaCobranza) throws Exception {
        iRepMetasCobranzasEJB = (IRepMetasCobranzas) Utilidades.getEJBRemoteRep("SessionMetasCobranzasRep", IRepMetasCobranzas.class.getName());
        return iRepMetasCobranzasEJB.exportartRepMetasCobranzas(oRepMetaCobranza);    
    }
    
    @Override
    public byte[] exportarRepMetasRecaudo(RepMetaRecaudo oRepMetaRecaudo) throws Exception {
        iRepMetasCobranzasEJB = (IRepMetasCobranzas) Utilidades.getEJBRemoteRep("SessionMetasCobranzasRep", IRepMetasCobranzas.class.getName());
        return iRepMetasCobranzasEJB.exportartRepMetasRecaudo(oRepMetaRecaudo);    
    }
}
