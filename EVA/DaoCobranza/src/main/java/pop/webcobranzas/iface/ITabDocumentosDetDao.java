/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.TabDocumentosDet;

/**
 *
 * @author Jyoverar
 */
public interface ITabDocumentosDetDao {

    Integer insert(TabDocumentosDet oTabDocumentosDet);

    void update(TabDocumentosDet oTabDocumentosDet);

    void delete(TabDocumentosDet oTabDocumentosDet);

    ArrayList<TabDocumentosDet> fetchAll(TabDocumentosDet oTabDocumentosDet);

    TabDocumentosDet fetchOnly(TabDocumentosDet oTabDocumentosDet);

}
