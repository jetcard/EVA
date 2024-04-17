/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeTipoCambio;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegTipoCambio {
    
    Integer insertar(MaeTipoCambio oMaeTipoCambio) throws Exception;

    void actualizar(MaeTipoCambio oMaeTipoCambio) throws Exception;

    void borrar(MaeTipoCambio oMaeTipoCambio) throws Exception;

    List<MaeTipoCambio> listarTipoCambio(MaeTipoCambio oMaeTipoCambio) throws Exception;
    
}
