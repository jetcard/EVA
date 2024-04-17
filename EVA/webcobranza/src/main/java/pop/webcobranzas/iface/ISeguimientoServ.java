/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.CobMaeSeguimiento;

/**
 *
 * @author Jyoverar
 */
public interface ISeguimientoServ {
    
    Integer insertar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;

    CobMaeSeguimiento buscar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;
    
    CobMaeSeguimiento listarSeguimiento(CobMaeSeguimiento oCobMaeSeguimiento)throws Exception;
    
    List<CobMaeSeguimiento> listarSeguimientoLlamada(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception;
    
    boolean borrar(String cobSeguimientoId,String codLlamadaId,String codLlamada,String usuario) throws Exception;
}
