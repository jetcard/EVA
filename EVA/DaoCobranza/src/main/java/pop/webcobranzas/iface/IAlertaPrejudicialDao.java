/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.AlertaPreJudicial;
import pop.comun.dominio.LegalTchn;

/**
 *
 * @author PR154357
 */
public interface IAlertaPrejudicialDao {
    
    ArrayList<AlertaPreJudicial> consultarAlertasPreJudiciales(LegalTchn oLegalTchn);
    
}

