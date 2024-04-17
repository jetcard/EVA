/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ifacerep;

import java.util.List;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeReporte;

/**
 *
 * @author Jyoverar
 */
public interface IRepCronogramaServ {

    byte[] imprimirCronograma(List<MaeCronograma> oMaeCronogramas, MaeReporte maeReporte) throws Exception;
}
