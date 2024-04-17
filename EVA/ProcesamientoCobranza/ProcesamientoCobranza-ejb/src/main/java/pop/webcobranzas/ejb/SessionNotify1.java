/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.ejb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeNotificacion;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.call.ProcessNotify;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.DAOManager;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.dao.SampleTransactionManager;

/**
 *
 * @author Jhon Yovera
 */
@Stateless(mappedName = "ejbNotify1")
public class SessionNotify1 {

    FactoryDao ofDao = new FactoryDao();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // cada 5 segundos se ejecuta la tarea
    //@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    //@Schedule(minute = "*/1", hour = "*", persistent = false)
    //@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void execute(Timer timer) {

        try {
            ProcessNotify ea = new ProcessNotify();
            MaeNotificacion oMaeNotificacion = new MaeNotificacion();
            oMaeNotificacion.setfFinBusq(new Date());
            List<MaeNotificacion> oSesList = null;
            
            oSesList = ofDao.getMaeNotificacion().fetchAllMsj(oMaeNotificacion);

            ea.buscarAlertas(oSesList);
            System.out.println("------ SessionNotify - cantidad = " + oSesList.size() + " -  Execution Time : " + new Date());

        } catch (Exception ex) {
            Logger.getLogger(SessionNotify.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(" a " + LocalDateTime.now());
//        InitialContext context;
//        Connection connection = null;
//        CallableStatement callableStatement = null;
//        ResultSet rs = null;
//        String getDBUSERCursorSql = "{call PKG_MAE_INVERSION.SP_BUS_MAE_INVERSION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
//
//        try {
//            context = new InitialContext();
//            DataSource ds = (DataSource) context.lookup("java:jboss/datasources/OracleDS");
//            System.out.println(" b " + LocalDateTime.now());
//
//            connection = ds.getConnection();
//
//            System.out.println(" b 1 " + LocalDateTime.now());
//
//            System.out.println(LocalDateTime.now());
//            // datos
//            MaeInversion maeInversion = new MaeInversion();
//            MaeFondo maeFondo = new MaeFondo();
//            MaePersona maePersona = new MaePersona();
//            maePersona.setCPersonaId(0);
//            maeInversion.setNMeses(0);
//            maeInversion.setcMaeInversionId(0);
//            maeInversion.setNDiasAnio(0);
//            maeInversion.setIInversion(0.0);
//            maeInversion.setPTasa(0.0);
//            maeInversion.setIInteres(0.0);
//            maeInversion.setICuota(0.0);
//            maeInversion.setMaeFondo(maeFondo);
//            maeInversion.setCInmuebleId(0);
//            maeInversion.setCInversion("7913");
//            maeInversion.setcPersonaId(maePersona);
//
//            // proceso
//            ArrayList<MaeInversion> lstInversion = new ArrayList<>();
//            //
//            callableStatement = connection.prepareCall(getDBUSERCursorSql);
//
//            //callableStatement.setInt(1, (int) maeInversion.getcMaeInversionId());
//            callableStatement.setNull(1, java.sql.Types.INTEGER);
//            
//            callableStatement.setString(2, maeInversion.getMaeFondo().getCFondoId());
//            //callableStatement.setString("PI_C_FONDO_ID", maeInversion.getMaeFondo().getCFondoId());
//            callableStatement.setString(3, maeInversion.getcInversionId());
//            callableStatement.setString(4, maeInversion.getcTipoInv());
//            callableStatement.setString(5, maeInversion.getCInversion());
//            callableStatement.setString(6, maeInversion.getCInversionIdOld());
//            callableStatement.setInt(7, (int) maeInversion.getcPersonaId().getCPersonaId());
//            callableStatement.setDate(8, (java.sql.Date) maeInversion.getFColocacion());
//            callableStatement.setDate(9, (java.sql.Date) maeInversion.getFEmision());
//            callableStatement.setDate(10, (java.sql.Date) maeInversion.getFVencimiento());
//            callableStatement.setDouble(11, (Double) maeInversion.getIInversion());
//            callableStatement.setInt(12, (int) maeInversion.getNMeses());
//            callableStatement.setDouble(13, (Double) maeInversion.getPTasa());
//            callableStatement.setInt(14, (int) maeInversion.getNDiasAnio());
//            callableStatement.setDouble(15, (Double) maeInversion.getIInteres());
//            callableStatement.setDouble(16, (Double) maeInversion.getICuota());
//            callableStatement.setInt(17, (int) maeInversion.getCInmuebleId());
//            callableStatement.setString(18, maeInversion.getCorigenId());
//            callableStatement.setString(19, maeInversion.getCcodigoIdent());
//            callableStatement.setString(20, maeInversion.geteEstado());
//
//            callableStatement.registerOutParameter(21, OracleTypes.CURSOR);
//            callableStatement.registerOutParameter(22, OracleTypes.VARCHAR);
//            callableStatement.registerOutParameter(23, OracleTypes.VARCHAR);
//
//            //
//            callableStatement.executeUpdate();
//
//            // get cursor and cast it to ResultSet
//            rs = (ResultSet) callableStatement.getObject(21);
//
//            while (rs.next()) {
//                String userid = rs.getString("C_MAE_INVERSION_ID");
//                String userName = rs.getString("C_INVERSION_ID");
//                String createdBy = rs.getString("C_TIPO_INV");
//                String createdDate = rs.getString("C_INVERSION");
//
//                System.out.println("UserName : " + userid);
//                System.out.println("UserName : " + userName);
//                System.out.println("CreatedBy : " + createdBy);
//                System.out.println("CreatedDate : " + createdDate);
//            }
//
//            System.out.println(LocalDateTime.now());
//
//        } catch (NamingException ex) {
//            Logger.getLogger(SessionNotify1.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(SessionNotify1.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                // close conection
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(SessionNotify1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        System.out.println(" e " + LocalDateTime.now());
//        System.out.println(LocalDateTime.now());

    }

}
