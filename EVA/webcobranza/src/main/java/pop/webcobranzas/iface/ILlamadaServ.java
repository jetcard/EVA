/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobLlamadas;

/**
 *
 * @author Jyoverar
 */
public interface ILlamadaServ {

    Integer insertar(CobLlamadas oCobLlamadas) throws Exception;

    void borrar(CobLlamadas oCobLlamadas) throws Exception;

    List<CobLlamadas> listar(CobLlamadas oCobLlamadas) throws Exception;
    
    List<CobLlamadas> listarResumen(CobLlamadas oCobLlamadas) throws Exception;   
    
    List<CobLlamadas> listarTele(CobCdr oCobCdr) throws Exception;
}
