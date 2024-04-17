/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.LegalAsesor;

/**
 *
 * @author HH38092
 */
public interface ILegalAsesorDao {
    ArrayList<LegalAsesor>  fetchAll() ;
}
