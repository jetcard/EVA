/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.MaeUbigeo;

/**
 *
 * @author Jhon Yovera
 */
public interface IUbigeoServ {

    List<MaeUbigeo> listarProvincia(MaeUbigeo oMaeUbigeo) throws Exception;

    List<MaeUbigeo> listarDistrito(MaeUbigeo oMaeUbigeo) throws Exception;
    
    List<MaeUbigeo> buscarUbigeo(MaeUbigeo oMaeUbigeo) throws Exception;
}
