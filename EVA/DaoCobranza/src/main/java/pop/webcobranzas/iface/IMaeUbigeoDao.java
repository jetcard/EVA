/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeUbigeo;

/**
 *
 * @author Jhon Yovera
 */
public interface IMaeUbigeoDao {

    ArrayList<MaeUbigeo> fetchProvince(MaeUbigeo oMaeUbigeo);

    ArrayList<MaeUbigeo> fetchDistrict(MaeUbigeo oMaeUbigeo);
    
    ArrayList<MaeUbigeo> fetchAll(MaeUbigeo oMaeUbigeo);
}
