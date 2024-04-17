/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobDevice;
import pop.webcobranzas.iface.ICdrServ;
import pop.webcobranzas.negocio.INegCdr;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CdrServ implements ICdrServ, Serializable {

    INegCdr iNegCdrEJB;

    @Override
    public Integer insertar(CobCdr oCobCdr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(CobCdr oCobCdr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(CobCdr oCobCdr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCdr> listar(CobCdr oCobCdr) throws Exception {
       iNegCdrEJB = (INegCdr) Utilidades.getEJBRemote("SessionCdr", INegCdr.class.getName());
        return iNegCdrEJB.listar(oCobCdr);
    }

    @Override
    public List<CobDevice> listarDispositivo(CobDevice oCobDevice) throws Exception {
        iNegCdrEJB = (INegCdr) Utilidades.getEJBRemote("SessionCdr", INegCdr.class.getName());
        return iNegCdrEJB.listarDispositivo(oCobDevice);
    }
    
}
