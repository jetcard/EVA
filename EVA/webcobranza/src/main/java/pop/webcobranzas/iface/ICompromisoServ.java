/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobCompromiso;

/**
 *
 * @author Jyoverar
 */
public interface ICompromisoServ {
    
    Integer insertar(CobCompromiso oCobCompromiso) throws Exception;

    void actualizar(CobCompromiso oCobCompromiso) throws Exception;
    
    void borrar(CobCompromiso oCobCompromiso) throws Exception;

    List<CobCompromiso> listar(CobCompromiso oCobCompromiso) throws Exception;
    
    List<CobCompromiso> listarDeposito(CobCompromiso oCobCompromiso) throws Exception;
}
