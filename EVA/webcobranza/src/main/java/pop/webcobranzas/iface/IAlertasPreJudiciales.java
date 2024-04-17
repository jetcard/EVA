package pop.webcobranzas.iface;

import java.util.List;
import pop.comun.dominio.AlertaPreJudicial;
import pop.comun.dominio.LegalTchn;


public interface IAlertasPreJudiciales {
    
    List<AlertaPreJudicial> consultarAlertasPreJudiciales(LegalTchn oLegalTchn) throws Exception;
    
}
