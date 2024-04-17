/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.iface.IInversionServ;
import pop.webcobranzas.negocio.INegInversion;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class InversionServ implements IInversionServ, Serializable {

    INegInversion iNegInversionEJB;

    
    @Override
    public List<MaeInversion> listarResumen(Number oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarResumen(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarDeudor(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarDeudor(oMaeInversion);
    }

    @Override
    public List<MaeInversion> listarRedInversion(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarRedInversion(oMaeInversion);
    }

    @Override
    public List<MaeInversion> listar(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listar(oMaeInversion);
    }

    @Override
    public RepSaldoDeudor reporteSaldoDeudor(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.reporteSaldoDeudor(oMaeInversion);
    }

    @Override
    public MaeInversion inversionDocumento(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.inversionDocumento(oMaeInversion);
    }

    @Override
    public List<MaeInversion> listarInmueble(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarInmueble(oMaeInversion);
    }

    @Override
    public List<MaeInversion> listarCronoEstado(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarCronoEstado(oMaeInversion);
    }
    
    @Override
    public List<MaeInversion> listarLegal(MaeInversion oMaeInversion) throws Exception {
         iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarLegal(oMaeInversion);
    }
    
     @Override
    public List<MaeInversion> listarJudi(MaeInversion oMaeInversion) throws Exception {
        iNegInversionEJB = (INegInversion) Utilidades.getEJBRemote("SessionInversion", INegInversion.class.getName());
        return iNegInversionEJB.listarJudi(oMaeInversion);
    }
}
