/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;

import java.util.List;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.MaeReporte;
import pop.webcobranzas.ifacerep.IRepSeguimientoDetServ;
import pop.webcobranzas.procesos.IRepSeguimientoDet;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class RepSeguimientoDetServ implements IRepSeguimientoDetServ {

    IRepSeguimientoDet iRepeguimientoDetEJB;

    @Override
    public byte[] exportarReporte(List<CobMaeSeguimiento> oCobMaeSeguimientos, MaeReporte maeReporte) throws Exception {
        iRepeguimientoDetEJB = (IRepSeguimientoDet) Utilidades.getEJBRemoteRep("SessionSeguimientoDetRep", IRepSeguimientoDet.class.getName());
        return iRepeguimientoDetEJB.exportarReporte(oCobMaeSeguimientos, maeReporte);
    }

}
