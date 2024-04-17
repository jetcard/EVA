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
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;

/**
 *
 * @author YR19155
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException,Exception{
       MaeTelefono oMaeTelefono =new MaeTelefono();
       MaePersona maePersonanew = new MaePersona();
        maePersonanew.setANroDocumento("09685507");
        oMaeTelefono.setMaePersona(maePersonanew);
        oMaeTelefono.setANumero("4048720");
        oMaeTelefono.setCTipoTel("0001");
        oMaeTelefono.setSEstado("0001");
        oMaeTelefono.setNPrede(0);
        insertarPresta(oMaeTelefono); 
     //  MaeEmail gMail=new MaeEmail();
      // gMail.setDemail("yulperu@gmail.com");
      // MaePersona maePersonanew = new MaePersona();
       //maePersonanew.setANroDocumento("09309055");   
       //gMail.setMaePersona(maePersonanew);
      // insertarPrestaMAIL(gMail);
      // eliminarPresta(oMaeTelefono);
      
    }
    
    
    public static Integer  insertarPresta(MaeTelefono oMaeTelefono) throws SQLException,Exception {
        Integer newID = 0;
         System.out.println("insertar telefono presta3333");
        Connection cn = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        System.out.println(oMaeTelefono.getMaePersona().getANroDocumento()+"-"+oMaeTelefono.getANumero()+"-"+oMaeTelefono.getCTipoTel()+ oMaeTelefono.getNPrede());
        try {
            System.out.println("insertar telefono prestaACA");
            //ObjConnection ObjCon = new ObjConnection();
            System.out.println("insertar telefono prestaACA111");
            //ObjCon.setCn(cn);
           System.out.println("insertar telefono prestaACA2");
            //cn = Connect.getDbCon(ObjConnection);
            cn = Connect.getDbConInitial();
            String consulta = "{call sincronizatelsap(?,?,?,?,?)}";
            System.out.println("insertar telefono presta1"+consulta);
            cstm = cn.prepareCall(consulta);
            
            cstm.setString(1, oMaeTelefono.getMaePersona().getANroDocumento());
            cstm.setString(2, oMaeTelefono.getANumero());
            cstm.setString(3, oMaeTelefono.getCTipoTel());
            cstm.setBoolean(4, oMaeTelefono.getSEstado().equals("0001") ? true : false );
            
            cstm.setInt(5, oMaeTelefono.getNPrede());
         
            System.out.println("insertar telefono presta1");
            
            
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
    
         public static Integer  eliminarPresta(MaeTelefono oMaeTelefono) throws SQLException,Exception {
        Integer newID = 0;
         System.out.println("insertar telefono presta3333");
        Connection cn = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        System.out.println(oMaeTelefono.getMaePersona().getANroDocumento()+"-"+oMaeTelefono.getANumero()+"-"+oMaeTelefono.getCTipoTel()+ oMaeTelefono.getNPrede());
        try {
            System.out.println("insertar telefono prestaACA");
            //ObjConnection ObjCon = new ObjConnection();
            System.out.println("insertar telefono prestaACA111");
            //ObjCon.setCn(cn);
           System.out.println("insertar telefono prestaACA2");
            //cn = Connect.getDbCon(ObjConnection);
            cn = Connect.getDbConInitial();
            String consulta = "{call sincronizatelsap_eli(?,?)}";
            System.out.println("insertar telefono presta1"+consulta);
            cstm = cn.prepareCall(consulta);
            System.out.println("insertar telefono presta222"+oMaeTelefono.getMaePersona().getANroDocumento());
    
            cstm.setString(1, oMaeTelefono.getMaePersona().getANroDocumento());
            cstm.setString(2, oMaeTelefono.getANumero());
          
            System.out.println("insertar telefono presta1");
            
            
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

    
    
        
      public static Integer  insertarPrestaMAIL(MaeEmail oEmail) throws SQLException,Exception {
        Integer newID = 0;
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
         
}
