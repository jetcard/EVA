/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.iface.IMaeCronogramaDao;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeDireccion;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class MaeCronogramaDao extends DBUtil implements IMaeCronogramaDao {

    private OracleConnection cn = null;

    public MaeCronogramaDao() {

    }

    public MaeCronogramaDao(OracleConnection cnx) {
        cn = cnx;
    }

    @Override
    public Integer insert(MaeCronograma oMaeCronograma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MaeCronograma oMaeCronograma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaeCronograma oMaeCronograma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MaeCronograma> fetchSchedule(MaeCronograma oMaeCronograma) {
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;

        ArrayList<MaeCronograma> cronogramaList = new ArrayList<>();

        try {
            // name of procedure
            String sp = "{call PKG_MAE_CRONOGRAMA.SP_BUS_MAE_CRONOGRAMA(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters(oMaeCronograma);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                MaeCronograma newCronograma = new MaeCronograma();
                //fondo
                MaeFondo newFondo = new MaeFondo();
                newFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                newFondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcInversionId(resultSet.getString("C_INVERSION_ID"));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setFEmision(resultSet.getDate("F_EMISION"));
                newInversion.setFVencimiento(resultSet.getDate("F_VENCIMIENTO"));
                newInversion.setPTasa(resultSet.getDouble("P_TASA"));
                newInversion.setIInversion(resultSet.getDouble("I_INVERSION"));
                newInversion.setMaeFondo(newFondo);
                //persona
                MaePersona newPersona = new MaePersona();
                newPersona.setMaeFondo(newFondo);
                newPersona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                newPersona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                newPersona.setDApePat(resultSet.getString("D_APE_PAT"));
                newPersona.setDApeMat(resultSet.getString("D_APE_MAT"));
                newPersona.setDNombres(resultSet.getString("D_NOMBRES"));
                //newPersona.setCTipoDocumento(resultSet.getString("D_DESC_CORTA"));
                MaeDireccion newDireccion = new MaeDireccion();
                newDireccion.setADir1(resultSet.getString("D_DIR1"));
                //
                MaeUbigeo newUbigeo = new MaeUbigeo();
                newUbigeo.setDDUbigeoDist(resultSet.getString("D_UBIGEO_D"));
                newUbigeo.setDDUbigeoProv(resultSet.getString("D_UBIGEO_P"));
                newDireccion.setMaeUbigeo(newUbigeo);
                List<MaeDireccion> oListDir = new ArrayList<>();
                oListDir.add(newDireccion);
                newPersona.setMaeDireccionList(oListDir);

                newInversion.setcPersonaId(newPersona);
                
                // cronograma
                newCronograma.setnSecuencia(resultSet.getInt("N_SECUENCIA"));
                newCronograma.setFpago(resultSet.getDate("F_PAGO"));
                newCronograma.setIcapital(resultSet.getFloat("I_CAPITAL"));
                newCronograma.setIinteres(resultSet.getFloat("I_INTERES"));
                newCronograma.setIcuota(resultSet.getFloat("I_CUOTA"));
                newCronograma.setIdeposito(resultSet.getFloat("I_DEPOSITO"));
                newCronograma.setIsaldo(resultSet.getFloat("I_SALDO"));
                newCronograma.setBaprobado(resultSet.getString("B_APROBADO"));
                
                newCronograma.setBcuotaGenerada(resultSet.getString("B_CUOTA_GENERADA"));
                newCronograma.seteEstado(resultSet.getString("E_ESTADO"));
                newCronograma.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                newCronograma.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                newCronograma.setcUsuarioMod(resultSet.getString("C_USUARIO_MOD"));
                newCronograma.setfUsuarioMod(resultSet.getDate("F_USUARIO_MOD"));
                newCronograma.setMoneda(resultSet.getString("MONEDA"));
                if (resultSet.getString("MONEDA").contains("SOLES")){
                    newCronograma.setSimbolo("S/.");
                }else{
                    newCronograma.setSimbolo("$.");
                }
                
                newCronograma.setMaeInversion(newInversion);
                cronogramaList.add(newCronograma);
            }

        } catch (Exception e) {
            Logger.getLogger(MaeCronogramaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (resultSet != null) {
              try { resultSet.close(); } catch (SQLException e) { ; }
              resultSet = null;
            }
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        }          
        return cronogramaList;
    }

     private List<ParameterOracle> listParameters(MaeCronograma oMaeCronograma) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oMaeCronograma.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_INV", oMaeCronograma.getMaeInversion().getcTipoInv(), OracleTypes.CHAR, ParameterDirection.Input));
        
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
