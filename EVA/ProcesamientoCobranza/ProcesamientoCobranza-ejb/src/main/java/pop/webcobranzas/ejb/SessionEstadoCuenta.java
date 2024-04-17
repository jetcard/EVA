/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegEstadoCuenta;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbEstadoCuenta")
public class SessionEstadoCuenta implements INegEstadoCuenta {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<CobTchn> listarTchn(CobTchn oCobTchn) throws Exception {
        List<CobTchn> cobTchnList = null;

        cobTchnList = ofDao.getMaeEstadoCuenta().fetchAllTchn(oCobTchn);
        return cobTchnList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<MaeEstadoCuenta> listarEstadoCuenta(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        List<MaeEstadoCuenta> estadoCuentaList = null;

        estadoCuentaList = ofDao.getMaeEstadoCuenta().fetchAll(oMaeEstadoCuenta);
        return estadoCuentaList;
    }

    @Override
    public List<MaeEstadoCuenta> listarEstadoCuentaB(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        List<MaeEstadoCuenta> estadoCuentaList = null;
        
        estadoCuentaList = ofDao.getMaeEstadoCuenta().fetchAllB(oMaeEstadoCuenta);
        return estadoCuentaList;
    }

    @Override
    public List<MaeDeposito> listarEstadoCuentaC(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        List<MaeDeposito> estadoCuentaList = null;

        estadoCuentaList = ofDao.getMaeEstadoCuenta().fetchAllC(oMaeEstadoCuenta);
        return estadoCuentaList;
    }
    
    @Override
    public List<MaeEstadoCuenta> listarEstadoCuentaD(MaeEstadoCuenta oMaeEstadoCuenta) throws Exception {
        List<MaeEstadoCuenta> estadoCuentaList = null;

        estadoCuentaList = ofDao.getMaeEstadoCuenta().fetchAllD(oMaeEstadoCuenta);
        return estadoCuentaList;
    }
}
