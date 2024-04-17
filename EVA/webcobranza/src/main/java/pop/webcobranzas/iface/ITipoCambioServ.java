/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeTipoCambio;

/**
 *
 * @author Jyoverar
 */
public interface ITipoCambioServ {
    
    Integer insertar(MaeTipoCambio oMaeTipoCambio) throws Exception;

    void actualizar(MaeTipoCambio oMaeTipoCambio) throws Exception;

    void borrar(MaeTipoCambio oMaeTipoCambio) throws Exception;

    List<MaeTipoCambio> listarTipoCambio(MaeTipoCambio oMaeTipoCambio) throws Exception;
    
}
