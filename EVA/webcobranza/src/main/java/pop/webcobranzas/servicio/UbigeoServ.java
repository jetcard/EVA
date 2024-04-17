/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.negocio.INegUbigeo;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jhon Yovera
 */
public class UbigeoServ implements IUbigeoServ, Serializable {

    INegUbigeo iNegUbieoEJB;

    @Override
    public List<MaeUbigeo> listarProvincia(MaeUbigeo oMaeUbigeo) throws Exception {
        iNegUbieoEJB = (INegUbigeo) Utilidades.getEJBRemote("SessionUbigeo", INegUbigeo.class.getName());
        return iNegUbieoEJB.listarProvincia(oMaeUbigeo);
    }

    @Override
    public List<MaeUbigeo> listarDistrito(MaeUbigeo oMaeUbigeo) throws Exception {
        iNegUbieoEJB = (INegUbigeo) Utilidades.getEJBRemote("SessionUbigeo", INegUbigeo.class.getName());
        return iNegUbieoEJB.listarDistrito(oMaeUbigeo);
    }

    @Override
    public List<MaeUbigeo> buscarUbigeo(MaeUbigeo oMaeUbigeo) throws Exception {
        iNegUbieoEJB = (INegUbigeo) Utilidades.getEJBRemote("SessionUbigeo", INegUbigeo.class.getName());
        return iNegUbieoEJB.buscarUbigeo(oMaeUbigeo);
    }

}
