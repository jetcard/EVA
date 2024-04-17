/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.TabArchivo;

/**
 *
 * @author Jyoverar
 */
public interface IArchivoServ {
    
     Integer insertar(TabArchivo oTabArchivo) throws Exception;
     
     List<TabArchivo> listar(TabArchivo oTabArchivo) throws Exception;
    
}
