/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeAsesor;

/**
 *
 * @author Jyoverar
 */
public interface IMaeFondoDao {
    
    Integer insert(MaeFondo oMaeFondo);
    
    void update(MaeFondo oMaeFondo);
    
    void delete(MaeFondo oMaeFondo);
    
    ArrayList<MaeFondo> fetchAll(MaeFondo oMaeFondo);
    
    ArrayList<MaeFondo> fetchDashboard(MaeFondo oMaeFondo);
    
    ArrayList<MaeFondo> fetchAllUser(MaeAsesor oMaeAsesor);
    
}
