/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeAsesor;

/**
 *
 * @author EC23329
 */
@Remote
public interface INegAsesor {    
    List<MaeAsesor> listarAsesor(MaeAsesor oMaeAsesor) throws Exception;
    
    List<MaeAsesor> listarAsesorUser(MaeAsesor oMaeAsesor) throws Exception;
}
