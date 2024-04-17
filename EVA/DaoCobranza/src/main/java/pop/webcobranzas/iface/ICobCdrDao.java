/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import java.util.Date;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobDevice;

/**
 *
 * @author Jyoverar
 */
public interface ICobCdrDao {
    
    Integer insert(CobCdr oCobCdr);

    void update(CobCdr oCobCdr);

    void delete(CobCdr oCobCdr);

    ArrayList<CobCdr> fetchAll(CobCdr oCobCdr);
    
    ArrayList<CobCdr> fetchAllOra(CobCdr oCobCdr);
    
    ArrayList<CobCdr> fetchAllNum(String phone, Date date);
    
    ArrayList<CobDevice> fetchAllDevice(CobDevice oCobDevice);
    
    ArrayList<CobCdr> fetchAllOrav2(CobCdr oCobCdr);
}
