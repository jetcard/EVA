/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaePersona;

/**
 *
 * @author Jyoverar
 */
public interface IMaePersonaDao {

    Integer insert(MaePersona oMaePersona);

    boolean update(MaePersona oMaePersona);

    void delete(MaePersona oMaePersona);

    ArrayList<MaePersona> fetchAll(MaePersona oMaePersona);

    ArrayList<MaePersona> fetchDashboard(MaePersona oMaePersona);
    
    MaePersona findAssignedPerson( int dia, String fondo, Number InversionID);
    
}
