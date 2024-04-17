/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import java.util.List;
import pop.comun.dominio.CobCronogramaMetaAvance;

/**
 *
 * @author Jhon Yovera
 */
public interface IMaeDashboardDao {
   ArrayList<List<Object>> fetchAll();
   ArrayList<List<Object>> fetchAllMeta(CobCronogramaMetaAvance oCobCronogramaMetaAvance);
   
   Boolean loadDataFPH();
   Boolean loadDataFEM();
   Boolean loadDataFMY();
   Boolean loadDataFPO();
   
}
