/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTipoCambio;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbTipoCambio")
public class SessionTipoCambio implements INegTipoCambio{

     FactoryDao ofDao = new FactoryDao();

    @Override
    public Integer insertar(MaeTipoCambio oMaeTipoCambio) throws Exception {
        Integer newID = 0;
        
        try {            
            oMaeTipoCambio.setcTipoMoneda("0002");
            newID = ofDao.getTipoCambio().insert(oMaeTipoCambio);
        } catch (Exception e) {
            throw e;        
        }

        return newID;
    }

    @Override
    public void actualizar(MaeTipoCambio oMaeTipoCambio) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(MaeTipoCambio oMaeTipoCambio) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeTipoCambio> listarTipoCambio(MaeTipoCambio oMaeTipoCambio) throws Exception {
       List<MaeTipoCambio> oCambioList = null;

        oCambioList = ofDao.getTipoCambio().fetchAll(oMaeTipoCambio);
        return oCambioList;
        
    }
}
