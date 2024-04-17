/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaePersonaInmueble;

/**
 *
 * @author Jyoverar
 */
public interface IMaeInmuebleDao {
    
    Integer insert(MaeInmueble oMaeInmueble);
    
    boolean update(MaeInmueble oMaeInmueble);
    
    void delete(MaeInmueble oMaeInmueble);
    
    ArrayList<MaeInmueble> fetchAll(MaeInmueble oMaeInmueble);
    
    ArrayList<MaePersonaInmueble> fetchPerson(MaeInmueble oMaeInmueble);
    
}
