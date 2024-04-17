/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.reporte.RepSaldoDeudor;

/**
 *
 * @author Jyoverar
 */
public interface IMaeInversionDoa {
    
    Integer insert(MaeInversion oMaeInversion);

    void update(MaeInversion oMaeInversion);

    void delete(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchResumen(Number oMaeInversion);

    ArrayList<MaeInversion> fetchAll(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchDebtors(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchAllLegal(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchRed(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchInm(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchListCronoEst(MaeInversion oMaeInversion);
    
    RepSaldoDeudor reportDebitBalance (MaeInversion oMaeInversion);
    
    MaeInversion fecthInvDoc(MaeInversion oMaeInversion);
    
    ArrayList<MaeInversion> fetchAllJudi(MaeInversion oMaeInversion);
    
}
