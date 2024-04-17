/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ifacerep;

import java.util.List;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.MaeReporte;

/**
 *
 * @author Jyoverar
 */
public interface IRepSeguimientoDetServ {

    byte[] exportarReporte(List<CobMaeSeguimiento> oCobMaeSeguimientos, MaeReporte maeReporte) throws Exception;

}
