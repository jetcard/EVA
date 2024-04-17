/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeUbigeo;

/**
 *
 * @author Jhon Yovera
 */
@Remote
public interface INegUbigeo {

    List<MaeUbigeo> listarProvincia(MaeUbigeo oMaeUbigeo) throws Exception;

    List<MaeUbigeo> listarDistrito(MaeUbigeo oMaeUbigeo) throws Exception;

    List<MaeUbigeo> buscarUbigeo(MaeUbigeo oMaeUbigeo) throws Exception;
}
