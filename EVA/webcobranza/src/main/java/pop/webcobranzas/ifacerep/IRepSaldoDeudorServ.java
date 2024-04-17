/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ifacerep;

import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.reporte.RepSaldoDeudor;

/**
 *
 * @author Jyoverar
 */
public interface IRepSaldoDeudorServ {

    byte[] imprimirSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception;

    byte[] exportarSaldoDeudor(RepSaldoDeudor oSaldoDeudor, MaeReporte oMaeReporte) throws Exception;
}
