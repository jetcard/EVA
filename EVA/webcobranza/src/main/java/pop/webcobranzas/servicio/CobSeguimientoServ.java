/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import pop.comun.dominio.CobSeguimiento;
import pop.webcobranzas.iface.ICobSeguimientoServ;
import pop.webcobranzas.negocio.INegCobSeguimiento;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CobSeguimientoServ implements ICobSeguimientoServ, Serializable {

    INegCobSeguimiento iNegCobSeguimientoEJB;

    @Override
    public Integer insertar(CobSeguimiento oCobSeguimiento) throws Exception {
        iNegCobSeguimientoEJB = (INegCobSeguimiento) Utilidades.getEJBRemote("SessionCobSeguimiento", INegCobSeguimiento.class.getName());
        return iNegCobSeguimientoEJB.insertar(oCobSeguimiento);
    }

    @Override
    public CobSeguimiento buscar(CobSeguimiento oCobSeguimiento) throws Exception {
        iNegCobSeguimientoEJB = (INegCobSeguimiento) Utilidades.getEJBRemote("SessionCobSeguimiento", INegCobSeguimiento.class.getName());
        return iNegCobSeguimientoEJB.buscar(oCobSeguimiento);
    }
    
}
