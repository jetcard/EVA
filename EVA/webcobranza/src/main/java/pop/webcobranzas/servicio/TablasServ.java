/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.CobTablas;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.negocio.INegTablas;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class TablasServ implements ITablasServ, Serializable {
    
    INegTablas iNegTablasEJB;

    @Override
    public List<CobTablas> listarTablas(CobTablas oCobTablas) throws Exception {
        iNegTablasEJB = (INegTablas) Utilidades.getEJBRemote("SessionTablas", INegTablas.class.getName());
        return iNegTablasEJB.listarTablas(oCobTablas);
    }
    
}
