/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.TabDocumentos;

/**
 *
 * @author Jyoverar
 */
public interface IDocumentosServ {

    Integer generar(TabDocumentos oTabDocumentos) throws Exception;
    
    Integer generarManual(TabDocumentos oTabDocumentos) throws Exception;
    
    String banderaGenerar(TabDocumentos oTabDocumentos) throws Exception;

    List<TabDocumentos> listarDocumentos(TabDocumentos oTabDocumentos) throws Exception;

    List<TabDocumentos> listarDocumentosDet(TabDocumentos oTabDocumentos) throws Exception;
    
}
