/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.TabDocumentosDet;

/**
 *
 * @author Jyoverar
 */
public interface IDocumentosDetServ {
    
    Integer insertar(TabDocumentosDet oTabDocumentosDet) throws Exception;

    List<TabDocumentosDet> listarDocumentosDet(TabDocumentosDet oTabDocumentosDet) throws Exception;

    TabDocumentosDet buscarSolo(TabDocumentosDet oTabDocumentosDet) throws Exception;
    
}
