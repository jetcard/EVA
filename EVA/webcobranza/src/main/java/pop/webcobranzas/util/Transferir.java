/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author YR19155
 */
public class Transferir {
        public static Integer  insertarPrestaTEL(MaeTelefono oMaeTelefono) throws SQLException,Exception {
        Integer newID = 0;
        Connection cn = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        
        try {
            cn = Connect.getDbConInitial();
            String consulta = "{call sincronizatelsap(?,?,?,?,?)}";
            cstm = cn.prepareCall(consulta);
          
            cstm.setString(1, oMaeTelefono.getMaePersona().getANroDocumento());
            cstm.setString(2, oMaeTelefono.getANumero());
            cstm.setString(3, oMaeTelefono.getCTipoTel());
            cstm.setBoolean(4, oMaeTelefono.getSEstado().equals("01") ? true : false );
            cstm.setInt(5, oMaeTelefono.getNPrede());
           
            
            rs = cstm.executeQuery();
            newID= 1;
            return newID;
        } catch (Exception ex) {

            return newID;
        } finally {
            if (cstm != null) {
              try { cstm.close(); } catch (Exception e) { ; }
              cstm = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        
    }

        
      public static Integer  insertarPrestaMAIL(MaeEmail oEmail) throws SQLException,Exception {
        Integer newID = 0;
         System.out.println("insertar telefono presta3333");
        Connection cn = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        
        try {
            cn = Connect.getDbConInitial();
            String consulta = "{call sincronizaemailsap(?,?)}";
            cstm = cn.prepareCall(consulta);
          
            cstm.setString(1, oEmail.getMaePersona().getANroDocumento());
            cstm.setString(2, oEmail.getDemail());
            
            rs = cstm.executeQuery();
            System.out.println("finalilo presta1");
            newID= 1;
            return newID;
        } catch (Exception ex) {

            return newID;
        } finally {
            if (cstm != null) {
              try { cstm.close(); } catch (Exception e) { ; }
              cstm = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        
    }
 

      public static Integer  eliminarPrestaTEL(MaeTelefono oMaeTelefono) throws SQLException,Exception {
        Integer newID = 0;
        Connection cn = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        try {
             cn = Connect.getDbConInitial();
            String consulta = "{call sincronizatelsap_eli(?,?)}";
            cstm = cn.prepareCall(consulta);
            
            cstm.setString(1, oMaeTelefono.getMaePersona().getANroDocumento());
            cstm.setString(2, oMaeTelefono.getANumero());
          
            rs = cstm.executeQuery();
            newID= 1;
            return newID;
        } catch (Exception ex) {

            return newID;
        } finally {
            if (cstm != null) {
              try { cstm.close(); } catch (Exception e) { ; }
              cstm = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (Exception e) { ; }
                cn = null;
            }
        }
        
    }

}
