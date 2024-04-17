/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import pop.comun.dominio.LegGeneraDemanda;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.impl.GenerarDemandaDao;
import pop.webcobranzas.negocio.INegGenerarDemanda;

/**
 *
 * @author PR154357
 */
@Stateless(mappedName = "ejbGenerarDemanda")
public class SessionGenerarDemanda implements INegGenerarDemanda {
    private static final Logger LOG = Logger.getLogger(GenerarDemandaDao.class.getName());
    private FactoryDao ofDao = new FactoryDao();

    @Override
    public List<LegGeneraDemanda> buscarDemandado(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        List<LegGeneraDemanda> oGenerarDemandaList = null;
        oGenerarDemandaList = ofDao.getGenerarDemanda().buscarDemandado(oLegGeneraDemanda);
        return oGenerarDemandaList;
    }
    
    @Override
    public Integer insertHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        return ofDao.getGenerarDemanda().insertHistoricoDemanda(oLegGeneraDemanda);
    }

    @Override
    public List<LegGeneraDemanda> generarDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        List<LegGeneraDemanda> oGenerarDemandaList = null;
        oGenerarDemandaList = ofDao.getGenerarDemanda().generarDemanda(oLegGeneraDemanda);
        return oGenerarDemandaList;
    }

    @Override
    public List<LegGeneraDemanda> grillaHistoricoDemanda() throws Exception {
        return ofDao.getGenerarDemanda().grillaHistoricoDemanda();
    }

    @Override
    public Integer deleteHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) throws Exception {
        return ofDao.getGenerarDemanda().deleteHistoricoDemanda(oLegGeneraDemanda);
    }
    
}