/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.negocio;

import java.util.List;
import javax.ejb.Remote;
import pop.comun.dominio.CobCronogramaMetaAvance;

/**
 *
 * @author Jhon Yovera
 */
@Remote
public interface INegDashboard {

    List<List<Object>> listarDeposito() throws Exception;
    List<List<Object>> listarDepositoMeta(CobCronogramaMetaAvance oCobCronogramaMetaAvance) throws Exception;

    Boolean cargarDatosFPH() throws Exception;

    Boolean cargarDatosFEM() throws Exception;

    Boolean cargarDatosFMY() throws Exception;

    Boolean cargarDatosFPO() throws Exception;

}
