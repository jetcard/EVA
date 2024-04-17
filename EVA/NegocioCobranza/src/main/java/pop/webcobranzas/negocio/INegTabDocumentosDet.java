/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.TabDocumentosDet;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegTabDocumentosDet {
    
    Integer insertar(TabDocumentosDet oTabDocumentosDet) throws Exception;

    List<TabDocumentosDet> listarDocumentosDet(TabDocumentosDet oTabDocumentosDet) throws Exception;

    TabDocumentosDet buscarSolo(TabDocumentosDet oTabDocumentosDet) throws Exception;

}
