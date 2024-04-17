/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;
import pop.comun.dominio.MaeCronograma;
import pop.webcobranzas.iface.ICronogramaServ;
import pop.webcobranzas.negocio.INegMaeCronograma;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class CronogramaServ implements ICronogramaServ, Serializable {

    INegMaeCronograma iNegCronogramaEJB;

    @Override
    public Integer insertar(MaeCronograma oMaeCronograma) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(MaeCronograma oMaeCronograma) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeCronograma oMaeCronograma) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeCronograma> buscarCronograma(MaeCronograma oMaeCronograma) throws Exception {
        iNegCronogramaEJB = (INegMaeCronograma) Utilidades.getEJBRemote("SessionCronograma", INegMaeCronograma.class.getName());
        System.out.println("[c0001] " + " CronogramaServ - buscarCronograma - h" +iNegCronogramaEJB);
        return iNegCronogramaEJB.buscarCronograma(oMaeCronograma);
    }

    
}
