package pop.webcobranzas.servicio;

import java.io.Serializable;
import java.util.List;

import pop.comun.dominio.LegalTchn;

import pop.comun.dominio.AlertaPreJudicial;
import pop.webcobranzas.iface.IAlertasPreJudiciales;
import pop.webcobranzas.negocio.INegAlertaPrejudicial;
import pop.webcobranzas.util.Utilidades;


public class AlertasPreJudicialesServ implements IAlertasPreJudiciales, Serializable {
    
    INegAlertaPrejudicial iNegAlertaPrejudicialEJB;

    @Override
    public List<AlertaPreJudicial> consultarAlertasPreJudiciales(LegalTchn oLegalTchn) throws Exception {
        iNegAlertaPrejudicialEJB = (INegAlertaPrejudicial) Utilidades.getEJBRemote("SessionAlertaPreJudicial", INegAlertaPrejudicial.class.getName());  
        System.out.println("paso consulta 1");
        return iNegAlertaPrejudicialEJB.consultarAlertasPreJudiciales(oLegalTchn);
    }
    
}
