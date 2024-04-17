/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;

import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.ifacerep.IRepSaldoDeudorServ;
import pop.webcobranzas.procesos.IRepSaldoDeudor;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class RepSaldoDeudorServ implements IRepSaldoDeudorServ {

    IRepSaldoDeudor iRepSaldoDeudorEJB;

    @Override
    public byte[] imprimirSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception {
        iRepSaldoDeudorEJB = (IRepSaldoDeudor) Utilidades.getEJBRemoteRep("SessionSaldoDeudor", IRepSaldoDeudor.class.getName());
        return iRepSaldoDeudorEJB.imprimirSaldoDeudor(oSaldoDeudor, oMaeReporte);
    }

    @Override
    public byte[] exportarSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception {
        iRepSaldoDeudorEJB = (IRepSaldoDeudor) Utilidades.getEJBRemoteRep("SessionSaldoDeudor", IRepSaldoDeudor.class.getName());
        return iRepSaldoDeudorEJB.exportarSaldoDeudor(oSaldoDeudor, oMaeReporte);
    }

}
