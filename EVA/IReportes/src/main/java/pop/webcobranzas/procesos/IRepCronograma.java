/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.procesos;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeReporte;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface IRepCronograma {

    byte[] imprimirCronograma(List<MaeCronograma> oMaeCronogramas, MaeReporte maeReporte) throws Exception;

}
