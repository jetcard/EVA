/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.TabTipoDocumento;

/**
 *
 * @author Jyoverar
 */
public interface ITipoDocumentoServ {

    List<TabTipoDocumento> listarTipoDocumento(TabTipoDocumento oTabTipoDocumento) throws Exception;

}
