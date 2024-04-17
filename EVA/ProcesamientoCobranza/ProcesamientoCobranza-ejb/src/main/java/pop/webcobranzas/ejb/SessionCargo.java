/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeCargo;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegCargo;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbCargo")
public class SessionCargo implements INegCargo{
    
    FactoryDao ofDao = new FactoryDao();

    @Override
    public MaeCargo calcularCargoAtrasado(MaeCargo oMaeCargo) throws Exception{
        MaeCargo oCargo = null;

        oCargo = ofDao.getMaeCargo().pendingCharges(oMaeCargo);
        return oCargo;
        
    }
    
}
