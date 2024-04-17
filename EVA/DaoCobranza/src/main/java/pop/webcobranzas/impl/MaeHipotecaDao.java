/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeHipoteca;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.IMaeHipotecaDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeHipotecaDao extends DBUtil implements IMaeHipotecaDao {

    private OracleConnection cn = null;

    public MaeHipotecaDao() {
    }

    public MaeHipotecaDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeHipoteca oMaeHipoteca) {
       //System.out.println(" <i> MaeHipotecaDao insert " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_HIPOTECA.SP_INS_MAE_HIPOTECA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insParameters(oMaeHipoteca);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_C_MAE_HIPOTECA_ID").getParameterInt();
            
            //if (cmd != null) {
            //    cmd.close();
            //}

        } catch (SQLException e) {
            Logger.getLogger(MaeHipotecaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(MaeHipotecaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }  
        //System.out.println(" <f> MaeHipotecaDao insert " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(MaeHipoteca oMaeHipoteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeHipoteca oMaeHipoteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeHipoteca> fetchAll(MaeHipoteca oMaeHipoteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaeHipoteca fetch(MaeHipoteca oMaeHipoteca) {
        //System.out.println(" <i> MaeHipotecaDao fetch " + LocalDateTime.now());
        MaeHipoteca newHipoteca = new MaeHipoteca();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        try {
            // name of procedure
            String sp = "{call PKG_MAE_HIPOTECA.SP_BUS_MAE_HIPOTECA(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParametersFetch(oMaeHipoteca);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // hipoteca
                newHipoteca.setCmaeHipotecaId(resultSet.getInt("C_MAE_HIPOTECA_ID"));
                newHipoteca.setCasientoId(resultSet.getString("C_ASIENTO_ID"));
                newHipoteca.setCpartidaElecId(resultSet.getString("C_PARTIDA_ELEC_ID"));
                // sede
                CobTablas csede = new CobTablas();  
                csede.setCtablaDetId(resultSet.getString("C_SEDE_ID"));
                newHipoteca.setCsede(csede);                
                newHipoteca.setFasiento(resultSet.getDate("F_ASIENTO"));
                newHipoteca.setDnomNotaria(resultSet.getString("D_NOM_NOTARIA"));
                newHipoteca.setCtchnReal(resultSet.getString("C_TCHN_REAL"));
                newHipoteca.setFemisionTchn(resultSet.getDate("F_EMISION_TCHN"));
                newHipoteca.setCasientoTchn(resultSet.getString("C_ASIENTO_TCHN"));
                // sede TCHN
                CobTablas csedeTchn = new CobTablas();  
                csedeTchn.setCtablaDetId(resultSet.getString("C_SEDE_TCHN_ID"));
                newHipoteca.setCsedeTchn(csedeTchn);                
                //
                newHipoteca.seteEstado(resultSet.getString("E_ESTADO"));
                newHipoteca.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newHipoteca.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newHipoteca.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newHipoteca.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
            }

        } catch (Exception e) {
            Logger.getLogger(MaeHipotecaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { ; }
                resultSet = null;
              }
            if (cmd != null) {
              try { cmd.close(); } catch (Exception e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        //System.out.println(" <f> MaeHipotecaDao fetch " + LocalDateTime.now());
        return newHipoteca;
    }

    private List<ParameterOracle> insParameters(MaeHipoteca oMaeHipoteca) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_C_INMUEBLE_ID",oMaeHipoteca.getMaeInmueble().getCInmuebleId() , OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ASIENTO_ID", oMaeHipoteca.getCasientoId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_PARTIDA_ELEC_ID", oMaeHipoteca.getCpartidaElecId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        //oListParam.add(new ParameterOracle("PI_C_SEDE_ID", oMaeHipoteca.getCsedeId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SEDE_ID", oMaeHipoteca.getCsede().getCtablaDetId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_ASIENTO", oMaeHipoteca.getFasiento(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_NOM_NOTARIA", oMaeHipoteca.getDnomNotaria(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TCHN_REAL", oMaeHipoteca.getCtchnReal(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_EMISION_TCHN", oMaeHipoteca.getFemisionTchn(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_ASIENTO_TCHN", oMaeHipoteca.getCasientoTchn(), OracleTypes.VARCHAR, ParameterDirection.Input));
        //oListParam.add(new ParameterOracle("PI_C_SEDE_TCHN_ID", oMaeHipoteca.getCsedeTchnId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SEDE_TCHN_ID", oMaeHipoteca.getCsedeTchn().getCtablaDetId(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oMaeHipoteca.getcUsuarioAdd(), OracleTypes.VARCHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_C_MAE_HIPOTECA_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    private List<ParameterOracle> listParametersFetch(MaeHipoteca oMaeHipoteca) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_MAE_HIPOTECA_ID", oMaeHipoteca.getCmaeHipotecaId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INMUEBLE_ID", oMaeHipoteca.getMaeInmueble().getCInmuebleId(), OracleTypes.NUMBER, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }
    
    public OracleConnection getCn() {
        return cn;
    }

    public void setCn(OracleConnection cn) {
        this.cn = cn;
    }

}
