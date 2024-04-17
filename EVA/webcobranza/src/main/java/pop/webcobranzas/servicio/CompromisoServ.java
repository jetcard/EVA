/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;


import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobCompromiso;
import pop.webcobranzas.iface.ICompromisoServ;
import pop.webcobranzas.negocio.INegCobCompromiso;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CompromisoServ implements ICompromisoServ, Serializable {

    INegCobCompromiso iNegCompromisoEJB;
    
    @Override
    public Integer insertar(CobCompromiso oCobCompromiso) throws Exception {
        iNegCompromisoEJB = (INegCobCompromiso) Utilidades.getEJBRemote("SessionCompromiso", INegCobCompromiso.class.getName());     
        return iNegCompromisoEJB.insertar(oCobCompromiso);
    }

    @Override
    public void borrar(CobCompromiso oCobCompromiso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobCompromiso> listar(CobCompromiso oCobCompromiso) throws Exception {
        iNegCompromisoEJB = (INegCobCompromiso) Utilidades.getEJBRemote("SessionCompromiso", INegCobCompromiso.class.getName());     
        return iNegCompromisoEJB.listar(oCobCompromiso);
    }

    @Override
    public void actualizar(CobCompromiso oCobCompromiso) throws Exception {
        iNegCompromisoEJB = (INegCobCompromiso) Utilidades.getEJBRemote("SessionCompromiso", INegCobCompromiso.class.getName());     
        iNegCompromisoEJB.actualizar(oCobCompromiso);
    }

    @Override
    public List<CobCompromiso> listarDeposito(CobCompromiso oCobCompromiso) throws Exception {
        iNegCompromisoEJB = (INegCobCompromiso) Utilidades.getEJBRemote("SessionCompromiso", INegCobCompromiso.class.getName());     
        return iNegCompromisoEJB.listarDeposito(oCobCompromiso);
    }
    
}
