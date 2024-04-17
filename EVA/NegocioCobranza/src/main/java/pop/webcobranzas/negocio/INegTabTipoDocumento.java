/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.TabTipoDocumento;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface INegTabTipoDocumento {

    List<TabTipoDocumento> listarTipoDocumento(TabTipoDocumento oTabTipoDocumento) throws Exception;
}
