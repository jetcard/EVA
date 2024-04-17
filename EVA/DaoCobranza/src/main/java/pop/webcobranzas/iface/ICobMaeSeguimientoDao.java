/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobMaeSeguimiento;

/**
 *
 * @author Jyoverar
 */
public interface ICobMaeSeguimientoDao {
    
    Integer insert(CobMaeSeguimiento oCobMaeSeguimiento);

    void update(CobMaeSeguimiento oCobMaeSeguimiento);

    boolean delete(String cobSeguimientoId,String codLlamadaId,String codLlamada,String usuario);
    
    CobMaeSeguimiento fetch(CobMaeSeguimiento oCobMaeSeguimiento);

    ArrayList<CobMaeSeguimiento> fetchAll(CobMaeSeguimiento oCobMaeSeguimiento);
    
    CobMaeSeguimiento fetchingSingle(CobMaeSeguimiento oCobMaeSeguimiento);
    
    ArrayList<CobMaeSeguimiento> fetchCall(CobMaeSeguimiento oCobMaeSeguimiento);
    
}
