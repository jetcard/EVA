/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeAsesor;

/**
 *
 * @author Jyoverar
 */
public interface IFondoServ {

    List<MaeFondo> listarFondos(MaeFondo oMaeFondo) throws Exception;
    
    List<MaeFondo> listarFondosUser(MaeAsesor oMaeAsesor) throws Exception;
}
