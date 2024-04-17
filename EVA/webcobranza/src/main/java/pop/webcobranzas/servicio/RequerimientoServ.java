/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobRequerimientoCriterios;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MovimientoCartas;
import pop.webcobranzas.iface.IRequerimientoServ;
import pop.webcobranzas.negocio.INegCobRequerimiento;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author RC433838
 */
public class RequerimientoServ implements IRequerimientoServ, Serializable {
    
    INegCobRequerimiento iNegRequerimientoEJB;
    
    
    @Override
    public String removerRequerimiento(CobRequerimientoCartas oRequerimiento) throws Exception {
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        return iNegRequerimientoEJB.removerRequerimiento(oRequerimiento);
    }
    
    @Override
    public String cambiaEstadoReq(CobRequerimientoCartas oRequerimiento) throws Exception {
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        return iNegRequerimientoEJB.cambiaEstadoReq(oRequerimiento);
    }    

    @Override
    public String addRequerimiento(CobRequerimientoCriterios oCriterio) throws Exception {
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        return iNegRequerimientoEJB.addRequerimiento(oCriterio);
    }

    @Override
    public List<MaeInversion> allInversiones(MaeInversion oInversion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CobRequerimientoCartas> allRequerimientos(CobRequerimientoCartas oRequerimiento) throws Exception {
                
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        
        return iNegRequerimientoEJB.allRequerimientos(oRequerimiento);
    }

    @Override
    public List<CobRequerimientoCriterios> findCriterios(CobRequerimientoCriterios oCriterio) throws Exception {
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        return iNegRequerimientoEJB.findCriterios(oCriterio);
    }
    
    @Override
    public List<CobRequerimientoCriterios> findReqSugerido() throws Exception {
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        return iNegRequerimientoEJB.findReqSugerido();
    }    

    @Override
    public List<MovimientoCartas> findCartas(MaeInversion oInversion) throws Exception {
        iNegRequerimientoEJB = (INegCobRequerimiento) Utilidades.getEJBRemote("SessionRequerimiento", INegCobRequerimiento.class.getName());
        return iNegRequerimientoEJB.findCartas(oInversion);
    }


    
}
