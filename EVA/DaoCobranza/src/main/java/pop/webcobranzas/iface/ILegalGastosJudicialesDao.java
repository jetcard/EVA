/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.LegGastoJudicial;
import pop.comun.dominio.LegalTchn;

/**
 *
 * @author HH38092
 */
public interface ILegalGastosJudicialesDao {
    ArrayList<LegGastoJudicial>  fetchAll(LegGastoJudicial oLegGastoJudicial) ;
}
