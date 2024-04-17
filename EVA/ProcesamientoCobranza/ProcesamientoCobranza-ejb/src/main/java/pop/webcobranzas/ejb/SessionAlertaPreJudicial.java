/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import pop.comun.dominio.AlertaPreJudicial;
import pop.comun.dominio.LegalTchn;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegAlertaPrejudicial;

/**
 *
 * @author PR154357
 */
@Stateless(mappedName = "ejbAlertaPreJudicial")
public class SessionAlertaPreJudicial implements INegAlertaPrejudicial {
    
    private FactoryDao ofDao = new FactoryDao();
    
    @Override
    public List<AlertaPreJudicial> consultarAlertasPreJudiciales(LegalTchn oLegalTchn) throws Exception {
        List<AlertaPreJudicial> oAlertaPreJudicialList = null;        
        System.out.println("paso consulta 2");
        oAlertaPreJudicialList = ofDao.getAlertaPrejudicial().consultarAlertasPreJudiciales(oLegalTchn);
        System.out.println("paso consulta 1222"+oAlertaPreJudicialList.size());
        return oAlertaPreJudicialList;
    }
    
    
}
