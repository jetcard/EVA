/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeDireccion;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegDireccion {

    Integer insertar(MaeDireccion oMaeDireccion) throws Exception;

    void actualizar(MaeDireccion oMaeDireccion) throws Exception;

    void borrar(MaeDireccion oMaeDireccion) throws Exception;

    List<MaeDireccion> listarDireccion(MaeDireccion oMaeDireccion) throws Exception;
    

}
