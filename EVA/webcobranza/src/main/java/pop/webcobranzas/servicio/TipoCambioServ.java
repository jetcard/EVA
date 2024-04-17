/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.iface.ITipoCambioServ;
import pop.webcobranzas.negocio.INegTipoCambio;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class TipoCambioServ implements ITipoCambioServ, Serializable {

    INegTipoCambio iNegTipoCambioEJB;

    @Override
    public Integer insertar(MaeTipoCambio oMaeTipoCambio) throws Exception {
        iNegTipoCambioEJB = (INegTipoCambio) Utilidades.getEJBRemote("SessionTipoCambio", INegTipoCambio.class.getName());     
        return iNegTipoCambioEJB.insertar(oMaeTipoCambio);
    }

    @Override
    public void actualizar(MaeTipoCambio oMaeTipoCambio) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeTipoCambio oMaeTipoCambio) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeTipoCambio> listarTipoCambio(MaeTipoCambio oMaeTipoCambio) throws Exception {
        iNegTipoCambioEJB = (INegTipoCambio) Utilidades.getEJBRemote("SessionTipoCambio", INegTipoCambio.class.getName());
        return iNegTipoCambioEJB.listarTipoCambio(oMaeTipoCambio);
    }
    
}
