/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.procesos;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.MaeReporte;

/**
 *
 * @author Jyoverar
 */
@Remote
public interface IRepSeguimientoDet {
    
    byte[] exportarReporte(List<CobMaeSeguimiento> oCobMaeSeguimientos, MaeReporte maeReporte) throws Exception;
    
}
