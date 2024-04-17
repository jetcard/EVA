/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;

/**
 *
 * @author Jyoverar
 */
public interface IBancoServ {
    
    Integer insertar(MaeBanco oMaeBanco) throws Exception;

    void actualizar(MaeBanco oMaeBanco) throws Exception;

    void borrar(MaeBanco oMaeBanco) throws Exception;

    List<MaeBanco> listarBanco(MaeBanco oMaeBanco) throws Exception;
    
    List<MaeBancoCuenta> listarBancoCuenta(MaeBancoCuenta oMaeBancoCuenta) throws Exception;
    
}
