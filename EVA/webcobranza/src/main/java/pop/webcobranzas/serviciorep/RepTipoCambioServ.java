/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;


import java.util.List;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.ifacerep.IRepTipoCambioServ;
import pop.webcobranzas.procesos.IRepTipoCambio;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class RepTipoCambioServ implements IRepTipoCambioServ {

    IRepTipoCambio iRepTipoCambioEJB;

    @Override
    public byte[] imprimirTipoCambio(List<MaeTipoCambio> oMaeTipoCambios) throws Exception {
        iRepTipoCambioEJB = (IRepTipoCambio) Utilidades.getEJBRemoteRep("SessionTipoCambioRep", IRepTipoCambio.class.getName());
        return iRepTipoCambioEJB.imprimirTipoCambio(oMaeTipoCambios);
    }

}
