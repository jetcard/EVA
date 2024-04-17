/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeBanco;
import pop.comun.dominio.MaeBancoCuenta;
import pop.webcobranzas.iface.IBancoServ;
import pop.webcobranzas.negocio.INegBanco;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class BancoServ implements IBancoServ, Serializable {

    INegBanco iNegBancoEJB;

    @Override
    public Integer insertar(MaeBanco oMaeBanco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeBanco oMaeBanco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeBanco oMaeBanco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeBanco> listarBanco(MaeBanco oMaeBanco) throws Exception {
        iNegBancoEJB = (INegBanco) Utilidades.getEJBRemote("SessionBanco", INegBanco.class.getName());
        return iNegBancoEJB.listarBanco(oMaeBanco);
    }

    @Override
    public List<MaeBancoCuenta> listarBancoCuenta(MaeBancoCuenta oMaeBancoCuenta) throws Exception {
        iNegBancoEJB = (INegBanco) Utilidades.getEJBRemote("SessionBanco", INegBanco.class.getName());
        return iNegBancoEJB.listarBancoCuenta(oMaeBancoCuenta);
    }

}
