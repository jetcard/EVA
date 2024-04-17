/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCompromiso;

/**
 *
 * @author Jyoverar
 */
 @Remote
public interface INegCobCompromiso {

    Integer insertar(CobCompromiso oCobCompromiso) throws Exception;
    
    void actualizar(CobCompromiso oCobCompromiso) throws Exception;

    void borrar(CobCompromiso oCobCompromiso) throws Exception;

    List<CobCompromiso> listar(CobCompromiso oCobCompromiso) throws Exception;
    
    List<CobCompromiso> listarDeposito(CobCompromiso oCobCompromiso) throws Exception;
}
