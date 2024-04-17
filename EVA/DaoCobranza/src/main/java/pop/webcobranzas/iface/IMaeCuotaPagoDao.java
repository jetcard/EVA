/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.iface;

import java.util.ArrayList;
import pop.comun.dominio.MaeCuotaPago;

/**
 *
 * @author Jyoverar
 */
public interface IMaeCuotaPagoDao {
    
    Integer insert(MaeCuotaPago oMaeCuotaPago);

    void update(MaeCuotaPago oMaeCuotaPago);

    void delete(MaeCuotaPago oMaeCuotaPago);

    ArrayList<MaeCuotaPago> fetchAll(MaeCuotaPago oMaeCuotaPago);
    
    ArrayList<MaeCuotaPago> pendingPayment(MaeCuotaPago oMaeCuotaPago);
    
    MaeCuotaPago pendingPaymentFuture(MaeCuotaPago oMaeCuotaPago);
    
    
}
