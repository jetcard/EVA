/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeCuotaPago;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCuotaPago;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCuotaPago")
public class SessionCuotaPago implements INegCuotaPago{

    FactoryDao ofDao = new FactoryDao();
    
    @Override
    public List<MaeCuotaPago> listarCuotaPago(MaeCuotaPago oMaeCuotaPago) throws Exception {
        List<MaeCuotaPago> oCuoPagList = null;

        oCuoPagList = ofDao.getMaeCuotaPago().pendingPayment(oMaeCuotaPago);
        return oCuoPagList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public MaeCuotaPago calcularCuotaPagoFuturo(MaeCuotaPago oMaeCuotaPago) throws Exception {
        MaeCuotaPago oCuoPag = null;

        oCuoPag = ofDao.getMaeCuotaPago().pendingPaymentFuture(oMaeCuotaPago);
        return oCuoPag;
    }
}
