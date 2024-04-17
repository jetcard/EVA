/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobDevice;

/**
 *
 * @author Jyoverar
 */
public interface ICdrServ {

    Integer insertar(CobCdr oCobCdr) throws Exception;

    void actualizar(CobCdr oCobCdr) throws Exception;

    void borrar(CobCdr oCobCdr) throws Exception;

    List<CobCdr> listar(CobCdr oCobCdr) throws Exception;
    
    List<CobDevice> listarDispositivo(CobDevice oCobDevice) throws Exception;
}
