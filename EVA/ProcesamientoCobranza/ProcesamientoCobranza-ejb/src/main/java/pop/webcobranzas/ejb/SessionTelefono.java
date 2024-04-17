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
import java.util.List;
import javax.ejb.Stateless;
import oracle.jdbc.OracleConnection;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeTelefono;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.negocio.INegTelefono;
import pop.webcobranzas.util.Connect;
import pop.webcobranzas.util.ObjConnection;

/**
 *
 * @author Jyoverar
 */
@Stateless(mappedName = "ejbTelefono")
public class SessionTelefono implements INegTelefono {

    FactoryDao ofDao = new FactoryDao();

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<MaeTelefono> listarTelfonoInver(MaeInversion oMaeInversion) throws Exception {
        List<MaeTelefono> oTelList = null;
        //ofDao.getConexionDao().ConexionOpen();
        System.out.println("pasando por session");
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();
        oTelList = ofDao.getTelefono().fetchPhoneInver(oMaeInversion);
        //ofDao.getConexionDao().ConexionClose();
        cn.close();
        return oTelList;
    }

    @Override
    public Integer insertar(MaeTelefono oMaeTelefono) throws Exception {
        Integer newID = 0;
        OracleConnection cn = null;
        Conexion conex = new Conexion();
        cn = conex.ConexionOpen();

        try {
            newID = ofDao.getTelefono().insert(oMaeTelefono);
        } catch (Exception e) {
            throw e;
        } 
        return newID;
    }

    @Override
    public Integer insertarPresta(MaeTelefono oMaeTelefono) throws SQLException,Exception {
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

    
    @Override
    public boolean actualizar(MaeTelefono oMaeTelefono) throws Exception {
        boolean rpta = false;
       

        try {
            rpta = ofDao.getTelefono().update(oMaeTelefono);
        } catch (Exception e) {
            throw e;
        } 
        return rpta;
    }

    @Override
    public boolean modificar(MaeTelefono oMaeTelefono) throws Exception {
        boolean rpta = false;
       

        try {
            rpta = ofDao.getTelefono().modificar(oMaeTelefono);
        } catch (Exception e) {
            throw e;
        } 
        return rpta;
    }

    
    @Override
    public void borrar(MaeTelefono oMaeTelefono) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaeTelefono> listarTelefono(MaeTelefono oMaeTelefono) throws Exception {
        List<MaeTelefono> oTelList = null;
       
        oTelList = ofDao.getTelefono().fetchAll(oMaeTelefono);
        return oTelList;
    }

    @Override
    public List<MaeTelefono> listarTelefonoEnvioMsj(MaeInversion oMaeInversion) throws Exception {
        List<MaeTelefono> oTelList = null;
        
        oTelList = ofDao.getTelefono().listPhoneSendMsj(oMaeInversion);        
        return oTelList;
    }

}
