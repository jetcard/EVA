/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.AlertaPreJudicial;
import pop.comun.dominio.LegalTchn;

/**
 *
 * @author PR154357
 */
@Remote
public interface INegAlertaPrejudicial {
    List<AlertaPreJudicial> consultarAlertasPreJudiciales(LegalTchn oLegalTchn) throws Exception;
}
