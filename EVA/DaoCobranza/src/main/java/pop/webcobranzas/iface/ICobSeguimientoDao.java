/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.CobSeguimiento;

/**
 *
 * @author Jyoverar
 */
public interface ICobSeguimientoDao {

    Integer insert(CobSeguimiento oCobSeguimiento);

    void update(CobSeguimiento oCobSeguimiento);

    void delete(CobSeguimiento oCobSeguimiento);

    CobSeguimiento fetch(CobSeguimiento oCobSeguimiento);

    ArrayList<CobSeguimiento> fetchAll(CobSeguimiento oCobSeguimiento);

}
