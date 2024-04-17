/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.util.List;
import pop.comun.dominio.MaeAsesor;
import pop.webcobranzas.iface.IAsesorServ;
import pop.webcobranzas.negocio.INegAsesor;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author EC23329
 */
public class AsesorServ implements IAsesorServ{
    INegAsesor iNegAsesorEJB;

    @Override
    public List<MaeAsesor> listarAsesor(MaeAsesor oMaeAsesor) throws Exception {
         iNegAsesorEJB = (INegAsesor) Utilidades.getEJBRemote("SessionAsesor", INegAsesor.class.getName());
        return iNegAsesorEJB.listarAsesor(oMaeAsesor);
    }
    
    @Override
    public List<MaeAsesor> listarAsesorUser(MaeAsesor oMaeAsesor) throws Exception {
         iNegAsesorEJB = (INegAsesor) Utilidades.getEJBRemote("SessionAsesor", INegAsesor.class.getName());
        return iNegAsesorEJB.listarAsesorUser(oMaeAsesor);
    }
}
