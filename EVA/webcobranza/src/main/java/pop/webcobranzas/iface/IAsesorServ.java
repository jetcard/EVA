/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeAsesor;

/**
 *
 * @author EC23329
 */
public interface IAsesorServ {
    
    List<MaeAsesor> listarAsesor(MaeAsesor oMaeAsesor) throws Exception;    
    
    List<MaeAsesor> listarAsesorUser(MaeAsesor oMaeAsesor) throws Exception;    
}
