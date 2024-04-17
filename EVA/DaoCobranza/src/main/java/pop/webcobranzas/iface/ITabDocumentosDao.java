/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.TabDocumentos;

/**
 *
 * @author Jyoverar
 */
public interface ITabDocumentosDao {
    
    Integer generar(TabDocumentos oTabDocumentos);
    
    Integer generateManual(TabDocumentos oTabDocumentos);
    
    String flagGenerate(TabDocumentos oTabDocumentos);
    
    ArrayList<TabDocumentos> fetchAll(TabDocumentos oTabDocumentos);
    
}
