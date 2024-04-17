/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegInversion;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbInversion")
public class SessionInversion implements INegInversion {

    FactoryDao ofDao = new FactoryDao();

    @Override
    public List<MaeInversion> listarResumen(Number oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchResumen(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listarDeudor(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchDebtors(oMaeInversion);
        return oInvList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<MaeInversion> listarRedInversion(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchRed(oMaeInversion);
        return oInvList;
    }

     @Override
    public List<MaeInversion> listarInmueble(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchInm(oMaeInversion);
        return oInvList;
    }

    
   @Override
    public List<MaeInversion> listarCronoEstado(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchListCronoEst(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listar(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        
        oInvList = ofDao.getMaeInversionDao().fetchAll(oMaeInversion);
        return oInvList;
    }

    @Override
    public RepSaldoDeudor reporteSaldoDeudor(MaeInversion oMaeInversion) throws Exception {
        RepSaldoDeudor oSaldoDeudor = null;
        
        oSaldoDeudor = ofDao.getMaeInversionDao().reportDebitBalance(oMaeInversion);
        return oSaldoDeudor;
    }

    @Override
    public MaeInversion inversionDocumento(MaeInversion oMaeInversion) throws Exception {
        MaeInversion oInversion = null;
        
        oInversion = ofDao.getMaeInversionDao().fecthInvDoc(oMaeInversion);
        return oInversion;
    }
    
     @Override
    public List<MaeInversion> listarLegal(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;

        oInvList = ofDao.getMaeInversionDao().fetchAllLegal(oMaeInversion);
        return oInvList;
    }
    
    @Override
    public List<MaeInversion> listarJudi(MaeInversion oMaeInversion) throws Exception {
        List<MaeInversion> oInvList = null;
        
        oInvList = ofDao.getMaeInversionDao().fetchAllJudi(oMaeInversion);
        return oInvList;
    }
}
