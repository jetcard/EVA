/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.iface.IDireccionServ;
import pop.webcobranzas.iface.IEmailServ;
import pop.webcobranzas.negocio.INegDireccion;
import pop.webcobranzas.negocio.INegEmail;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class DireccionServ implements IDireccionServ, Serializable {

    INegDireccion iNegDireccionEJB;

    @Override
    public Integer insertar(MaeDireccion oMaeDireccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeDireccion oMaeDireccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeDireccion oMaeDireccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeDireccion> listarDireccion(MaeDireccion oMaeDireccion) throws Exception {
        iNegDireccionEJB = (INegDireccion) Utilidades.getEJBRemote("SessionDireccion", INegDireccion.class.getName());
        return iNegDireccionEJB.listarDireccion(oMaeDireccion);
    }

    
    
}
