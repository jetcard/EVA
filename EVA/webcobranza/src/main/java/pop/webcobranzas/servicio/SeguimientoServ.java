/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.webcobranzas.iface.ISeguimientoServ;
import pop.webcobranzas.negocio.INegCobMaeSeguimiento;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class SeguimientoServ implements ISeguimientoServ, Serializable {

    INegCobMaeSeguimiento iNegSeguimientoEJB;
    
    @Override
    public CobMaeSeguimiento listarSeguimiento(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        //System.out.println("[u0000] " + " SeguimientoBean - obtenerSeguimiento - " );
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        //System.out.println("[u0001] " + " SeguimientoBean - obtenerSeguimiento - s" +iNegSeguimientoEJB);
        CobMaeSeguimiento seg = iNegSeguimientoEJB.listarSeguimiento(oCobMaeSeguimiento);
        //System.out.println("[u0010] " + " SeguimientoBean - obtenerSeguimiento - a" +seg);
        return seg;
    }
 
   
    @Override
    public Integer insertar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.insertar(oCobMaeSeguimiento);
    }

    @Override
    public CobMaeSeguimiento buscar(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.buscar(oCobMaeSeguimiento);
    }

    @Override
    public List<CobMaeSeguimiento> listarSeguimientoLlamada(CobMaeSeguimiento oCobMaeSeguimiento) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.listarSeguimientoLlamada(oCobMaeSeguimiento);
    }
    
    @Override
    public boolean borrar(String cobSeguimientoId,String codLlamadaId,String codLlamada,String usuario) throws Exception {
        iNegSeguimientoEJB = (INegCobMaeSeguimiento) Utilidades.getEJBRemote("SessionSeguimiento", INegCobMaeSeguimiento.class.getName());
        return iNegSeguimientoEJB.borrar(cobSeguimientoId,codLlamadaId,codLlamada,usuario);
    }    
}
