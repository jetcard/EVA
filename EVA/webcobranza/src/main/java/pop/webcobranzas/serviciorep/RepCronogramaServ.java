/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.serviciorep;

import java.util.List;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeReporte;
import pop.webcobranzas.ifacerep.IRepCronogramaServ;
import pop.webcobranzas.procesos.IRepCronograma;
import pop.webcobranzas.util.Utilidades;

/**
 *
 * @author Jyoverar
 */
public class RepCronogramaServ implements IRepCronogramaServ{

    IRepCronograma iRepCronogramaEJB;
    
    @Override
    public byte[] imprimirCronograma(List<MaeCronograma> oMaeCronogramas, MaeReporte maeReporte) throws Exception {
        iRepCronogramaEJB = (IRepCronograma) Utilidades.getEJBRemoteRep("SessionCronogramaRep", IRepCronograma.class.getName());
        return iRepCronogramaEJB.imprimirCronograma(oMaeCronogramas, maeReporte);
    }
    
}
