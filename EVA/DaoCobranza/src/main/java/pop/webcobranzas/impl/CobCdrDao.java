/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.util.ArrayList;
import pop.comun.dominio.CobCdr;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.iface.ICobCdrDao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import oracle.jdbc.OracleCallableStatement;
import pop.comun.dominio.CobDevice;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
import pop.webcobranzas.conn.ConexionMySql;

/**
 *
 * @author Jyoverar
 */
public class CobCdrDao extends DBUtil implements ICobCdrDao {
    private Connection cn = null;

    private OracleConnection cnO = null;

    public CobCdrDao() {
    }

    public CobCdrDao(Connection cnx) {
        cn = cnx;
    }

    public CobCdrDao(OracleConnection cnx) {
        cnO = cnx;
    }

    @Override
    public Integer insert(CobCdr oCobCdr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CobCdr oCobCdr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CobCdr oCobCdr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobCdr> fetchAll(CobCdr oCobCdr) {
        ArrayList<CobCdr> newCdrList = new ArrayList<>();
        ResultSet rs1=null;
        CallableStatement cStmt =null;
        Connection cnMySql = null;
        
        try {
            //System.out.println(" <i> fetchAll - Buscamos las llamadas " + LocalDateTime.now());
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat ftB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");
            //Buscamos las llamadas realizadas por numero y fecha

            //Abre conexion a la BD
            ConexionMySql conexMysql = new ConexionMySql();
            cnMySql = conexMysql.getConnection();
            cStmt = cnMySql.prepareCall("{CALL sp_list_record(?,?,?,?,?)}");
            cStmt.setString(1, ft.format(oCobCdr.getfIniBusq()));
            cStmt.setString(2, ft.format(oCobCdr.getfFinBusq()));
            cStmt.setString(3, oCobCdr.getSrc());
            cStmt.setString(4, oCobCdr.getDst());
            cStmt.setString(5, oCobCdr.getDisposition());
            
            java.util.Date dini = new Date();
            System.out.println("sp_list_record.ini");
            rs1 = cStmt.executeQuery();
            java.util.Date dfin = new Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("sp_list_record.fin:"+diferencia);
            
            while (rs1.next()) {
                CobCdr newCdr = new CobCdr();
                newCdr.setCalldate(ftB.parse(rs1.getString("calldate")));
                newCdr.setClid(rs1.getString("clid"));
                newCdr.setSrc(rs1.getString("src"));
                newCdr.setDst(rs1.getString("dst"));
                newCdr.setDcontext(rs1.getString("dcontext"));
                newCdr.setChannel(rs1.getString("channel"));
                newCdr.setDstchannel(rs1.getString("dstchannel"));
                newCdr.setLastapp(rs1.getString("lastapp"));
                newCdr.setLastdata(rs1.getString("lastdata"));
                newCdr.setDuration(rs1.getInt("duration"));
                newCdr.setBillsec(rs1.getInt("billsec"));
                newCdr.setDisposition(rs1.getString("disposition"));
                newCdr.setAmaflags(rs1.getInt("amaflags"));
                newCdr.setAccountcode(rs1.getString("accountcode"));
                newCdr.setUniqueid(rs1.getString("uniqueid"));
                newCdr.setUserfield(rs1.getString("userfield"));

                if (newCdr.getDisposition().equals("ANSWERED")) {
                    if ((rs1.getString("recordingfile").length() > 10) && !rs1.getString("dst").trim().equals("s")) {
                        newCdr.setRecordingfile(rs1.getString("recordingfile").substring(28));
                    } else if ((rs1.getString("recordingfile").length() > 10) && rs1.getString("dst").trim().equals("s")) {
                        newCdr.setRecordingfile(rs1.getString("recordingfile").substring(28));
                    }
                } else {
                    newCdr.setRecordingfile(ftD.format(newCdr.getCalldate()) + "/" + rs1.getString("recordingfile"));
                }
                newCdr.setCnum(rs1.getString("cnum"));
                newCdr.setCnam(rs1.getString("cnam"));
                newCdr.setOutbound_cnum(rs1.getString("outbound_cnum"));
                newCdr.setOutbound_cnam(rs1.getString("outbound_cnam"));
                newCdr.setDst_cnam(rs1.getString("dst_cnam"));
                newCdr.setDid(rs1.getString("did"));
                newCdrList.add(newCdr);
            }
            rs1.close();
            cStmt.close();

        } catch (SQLException | ParseException ex) {
            System.out.println(ex);
            //Logger.getLogger(CobCdrDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
            if (rs1 != null) {
              try { rs1.close(); } catch (SQLException e) { ; }
              rs1 = null;
            }
            if (cStmt != null) {
              try { cStmt.close(); } catch (SQLException e) { ; }
              cStmt = null;
            }
            if (cnMySql != null) {
              try { cnMySql.close(); } catch (SQLException e) { ; }
              cnMySql = null;
            }
        }
        //System.out.println(" <f> fetchAll - Buscamos las llamadas " + LocalDateTime.now());
        return newCdrList;
    }

    @Override
    public ArrayList<CobDevice> fetchAllDevice(CobDevice oCobDevice) {
        ArrayList<CobDevice> newDevList = new ArrayList<>();
        ResultSet rs1 = null;
        CallableStatement cStmt = null;        
        Connection cnMySql = null;
        
        try {
            //System.out.println(" <i> fetchAllDevice " + LocalDateTime.now());
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat ftB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");
            //Buscamos las llamadas realizadas por numero y fecha

            //Abre conexion a la BD
            ConexionMySql conexMysql = new ConexionMySql();
            cnMySql = conexMysql.getConnection();
            
            cStmt = cnMySql.prepareCall("{CALL sp_list_device(?)}");
            cStmt.setString(1, oCobDevice.getDevId());
            
            java.util.Date dini = new Date();
            System.out.println("sp_list_device.ini");
            rs1 = cStmt.executeQuery();
            java.util.Date dfin = new Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("sp_list_device.fin:"+diferencia);

            
            while (rs1.next()) {
                CobDevice newDevice = new CobDevice();
                newDevice.setDevId(rs1.getString("id"));
                newDevice.setDevTech(rs1.getString("tech"));
                newDevice.setDevDial(rs1.getString("dial"));
                newDevice.setDevType(rs1.getString("devicetype"));
                newDevice.setDevUser(rs1.getString("user"));
                newDevice.setDevDescripcion(rs1.getString("description"));

                newDevList.add(newDevice);
            }
            rs1.close();
            cStmt.close();

        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(CobCdrDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
            if (rs1 != null) {
              try { rs1.close(); } catch (SQLException e) { ; }
              rs1 = null;
            }
            if (cStmt != null) {
              try { cStmt.close(); } catch (SQLException e) { ; }
              cStmt = null;
            }
            if (cnMySql != null) {
              try { cnMySql.close(); } catch (SQLException e) { ; }
              cnMySql = null;
            }
        }        
        //System.out.println(" <f> fetchAllDevice " + LocalDateTime.now());
        return newDevList;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    @Override
    public ArrayList<CobCdr> fetchAllNum(String phone, Date date) {
        ArrayList<CobCdr> newCdrList = new ArrayList<>();
        CallableStatement cStmt = null;
        ResultSet rs1 =null;
        Connection cnMySql = null;
        
        try {
            //System.out.println(" <i> fetchAll - Buscamos las llamadas " + LocalDateTime.now());
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat ftB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");
            //Buscamos las llamadas realizadas por numero y fecha

            //Abre conexion a la BD
            ConexionMySql conexMysql = new ConexionMySql();
            cnMySql = conexMysql.getConnection();
            cStmt = cnMySql.prepareCall("{CALL sp_list_call_record(?,?)}");
            cStmt.setString(1, phone.trim());
            cStmt.setString(2, ft.format(date));
            
            java.util.Date dini = new Date();
            System.out.println("sp_list_call_record.ini");
            rs1 = cStmt.executeQuery();
            java.util.Date dfin = new Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("sp_list_call_record.fin:"+diferencia);
            
            while (rs1.next()) {
                CobCdr newCdr = new CobCdr();
                newCdr.setCalldate(ftB.parse(rs1.getString("calldate")));
                newCdr.setClid(rs1.getString("clid"));
                newCdr.setSrc(rs1.getString("src"));
                newCdr.setDst(rs1.getString("dst"));
                newCdr.setDcontext(rs1.getString("dcontext"));
                newCdr.setChannel(rs1.getString("channel"));
                newCdr.setDstchannel(rs1.getString("dstchannel"));
                newCdr.setLastapp(rs1.getString("lastapp"));
                newCdr.setLastdata(rs1.getString("lastdata"));
                newCdr.setDuration(rs1.getInt("duration"));
                newCdr.setBillsec(rs1.getInt("billsec"));
                newCdr.setDisposition(rs1.getString("disposition"));
                newCdr.setAmaflags(rs1.getInt("amaflags"));
                newCdr.setAccountcode(rs1.getString("accountcode"));
                newCdr.setUniqueid(rs1.getString("uniqueid"));
                newCdr.setUserfield(rs1.getString("userfield"));

                if (newCdr.getDisposition().equals("ANSWERED")) {
                    if (rs1.getString("recordingfile").length() > 10) {
                        newCdr.setRecordingfile(rs1.getString("recordingfile").substring(28));
                    }
                } else {
                    newCdr.setRecordingfile(ftD.format(newCdr.getCalldate()) + "/" + rs1.getString("recordingfile"));
                }
                newCdr.setCnum(rs1.getString("cnum"));
                newCdr.setCnam(rs1.getString("cnam"));
                newCdr.setOutbound_cnum(rs1.getString("outbound_cnum"));
                newCdr.setOutbound_cnam(rs1.getString("outbound_cnam"));
                newCdr.setDst_cnam(rs1.getString("dst_cnam"));
                newCdr.setDid(rs1.getString("did"));
                newCdrList.add(newCdr);
            }
            rs1.close();
            cStmt.close();

        } catch (SQLException | ParseException ex) {
            System.out.println(ex);
            //Logger.getLogger(CobCdrDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
            if (rs1 != null) {
              try { rs1.close(); } catch (SQLException e) { ; }
              rs1 = null;
            }
            if (cStmt != null) {
              try { cStmt.close(); } catch (SQLException e) { ; }
              cStmt = null;
            }
            if (cnMySql != null) {
              try { cnMySql.close(); } catch (SQLException e) { ; }
              cnMySql = null;
            }
        }
        //System.out.println(" <f> fetchAll - Buscamos las llamadas " + LocalDateTime.now());
        return newCdrList;
    }

    @Override
    public ArrayList<CobCdr> fetchAllOra(CobCdr oCobCdr) {
        //System.out.println(" <i> CobLlamadasDao fetchAllOra " + LocalDateTime.now());
        ArrayList<CobCdr> newCdrList = new ArrayList<>();
        SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_BUS_COB_LLAMADAS_MYSQL(?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busOra(oCobCdr);       
            // execute procedure
            runSearch(oList, this.getCnO(), cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            SimpleDateFormat ftB = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

            while (resultSet.next()) {
                CobCdr newCdr = new CobCdr();
                newCdr.setCalldate(resultSet.getDate("calldate"));
                newCdr.setClid(resultSet.getString("clid"));
                newCdr.setSrc(resultSet.getString("src"));
                newCdr.setDst(resultSet.getString("dst"));
                newCdr.setDcontext(resultSet.getString("dcontext"));
                newCdr.setChannel(resultSet.getString("channel"));
                newCdr.setDstchannel(resultSet.getString("dstchannel"));
                newCdr.setLastapp(resultSet.getString("lastapp"));
                newCdr.setLastdata(resultSet.getString("lastdata"));
                newCdr.setDuration(resultSet.getInt("duration"));
                newCdr.setBillsec(resultSet.getInt("billsec"));
                newCdr.setDisposition(resultSet.getString("disposition"));
                newCdr.setAmaflags(resultSet.getInt("amaflags"));
                newCdr.setAccountcode(resultSet.getString("accountcode"));
                newCdr.setUniqueid(resultSet.getString("uniqueid"));
                newCdr.setUserfield(resultSet.getString("userfield"));
                /*
                if (newCdr.getDisposition().equals("ANSWERED")) {
                    if (resultSet.getString("recordingfile") != null) {
                        if ((resultSet.getString("recordingfile").length() > 10) && !newCdr.getDst().trim().equals("s")) {
                            newCdr.setRecordingfile(resultSet.getString("recordingfile").substring(28));
                        } else if ((resultSet.getString("recordingfile").length() > 10) && newCdr.getDst().trim().equals("s")) {
                            newCdr.setRecordingfile(resultSet.getString("recordingfile").substring(28));
                        }
                    }
                } else {
                    newCdr.setRecordingfile(ftD.format(newCdr.getCalldate()) + "/" + resultSet.getString("recordingfile"));
                }
                */
                if (resultSet.getString("recordingfile") != null) {
                    if (resultSet.getString("recordingfile").trim().length() > 0) {
                        if (resultSet.getString("recordingfile").indexOf("asterisk", 1) > 0) {
                            newCdr.setRecordingfile(resultSet.getString("recordingfile").substring(28));
                        } else {
                            newCdr.setRecordingfile(ftD.format(newCdr.getCalldate()) + "/" + resultSet.getString("recordingfile"));
                        }
                    }
                }

                newCdr.setCnum(resultSet.getString("cnum"));
                newCdr.setCnam(resultSet.getString("cnam"));
                newCdr.setOutbound_cnum(resultSet.getString("outbound_cnum"));
                newCdr.setOutbound_cnam(resultSet.getString("outbound_cnam"));
                newCdr.setDst_cnam(resultSet.getString("dst_cnam"));
                newCdr.setDid(resultSet.getString("did"));
                newCdr.setListTchn(resultSet.getString("LIST_TCHN"));
                newCdr.setListTchn2(resultSet.getString("LIST_TCHN2"));
                newCdr.setCalldateB(ftB.format(newCdr.getCalldate()));

                newCdrList.add(newCdr);
            }

            resultSet.close();
            if (cmd != null) {
                cmd.close();
            }

        } catch (Exception e) {
            System.out.println(e);
            //Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
            if (cnO != null) {
              try { cnO.close(); } catch (SQLException e) { ; }
              cnO = null;
            }
        }        
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        return newCdrList;
    }

    private List<ParameterOracle> listParameters_busOra(CobCdr oCobCdr) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_FINICIO", oCobCdr.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN", oCobCdr.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_SRC", oCobCdr.getSrc(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DST", oCobCdr.getDst(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_DISPOSITION", oCobCdr.getDisposition(), OracleTypes.VARCHAR, ParameterDirection.Input));

        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

   @Override
    public ArrayList<CobCdr> fetchAllOrav2(CobCdr oCobCdr) {
        ArrayList<CobCdr> newCdrList = new ArrayList<>();
        ResultSet rs1=null;
        CallableStatement cStmt =null;
        Connection cnMySql = null;
        int numllamdas=0;
        try {
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat ftB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat ftD = new SimpleDateFormat("yyyy/MM/dd");
            
            //Abre conexion a la BD
            ConexionMySql conexMysql = new ConexionMySql();
            cnMySql = conexMysql.getConnection();
            cStmt = cnMySql.prepareCall("{CALL sp_bus_llamadas(?,?,?,?,?)}");
            cStmt.setString(1, ft.format(oCobCdr.getfIniBusq()));
            cStmt.setString(2, ft.format(oCobCdr.getfFinBusq()));
            cStmt.setString(3, oCobCdr.getSrc());
            cStmt.setString(4, oCobCdr.getDst());
            cStmt.setString(5, oCobCdr.getDisposition());
            
            java.util.Date dini = new Date();
            System.out.println("sp_bus_llamadas.ini");
            rs1 = cStmt.executeQuery();
            java.util.Date dfin = new Date();
            long diferencia = ( dini.getTime() - dfin.getTime() );//MILLSECS_PER_DAY; 
            System.out.println("sp_bus_llamadas.fin:"+diferencia);
            
            //DECLARANDO FUNCION ORACLE
            CallableStatement stmtOra=null;
            OracleConnection oraCon=this.getCnO();
            
            while (rs1.next()) {
                numllamdas++;
                CobCdr newCdr = new CobCdr();
                newCdr.setCalldate(ftB.parse(rs1.getString("calldate")));
                newCdr.setClid(rs1.getString("clid"));
                newCdr.setSrc(rs1.getString("src"));
                newCdr.setDst(rs1.getString("dst"));
                newCdr.setDcontext(rs1.getString("dcontext"));
                newCdr.setChannel(rs1.getString("channel"));
                newCdr.setDstchannel(rs1.getString("dstchannel"));
                newCdr.setLastapp(rs1.getString("lastapp"));
                newCdr.setLastdata(rs1.getString("lastdata"));
                newCdr.setDuration(rs1.getInt("duration"));
                newCdr.setBillsec(rs1.getInt("billsec"));
                newCdr.setDisposition(rs1.getString("disposition"));
                newCdr.setAmaflags(rs1.getInt("amaflags"));
                newCdr.setAccountcode(rs1.getString("accountcode"));
                newCdr.setUniqueid(rs1.getString("uniqueid"));
                newCdr.setUserfield(rs1.getString("userfield"));

                if (rs1.getString("recordingfile") != null) {
                    if (rs1.getString("recordingfile").trim().length() > 0) {
                        if (rs1.getString("recordingfile").indexOf("asterisk", 1) > 0) {
                            newCdr.setRecordingfile(rs1.getString("recordingfile").substring(28));
                        } else {
                            newCdr.setRecordingfile(ftD.format(newCdr.getCalldate()) + "/" + rs1.getString("recordingfile"));
                        }
                    }
                }
                
                newCdr.setCnum(rs1.getString("cnum"));
                newCdr.setCnam(rs1.getString("cnam"));
                newCdr.setOutbound_cnum(rs1.getString("outbound_cnum"));
                newCdr.setOutbound_cnam(rs1.getString("outbound_cnam"));
                newCdr.setDst_cnam(rs1.getString("dst_cnam"));
                newCdr.setDid(rs1.getString("did"));
                
                String numero1="";
                if(rs1.getString("did").trim().equals(""))
                {
                    numero1=rs1.getString("dst").trim().substring(1);
                }
                else
                {
                    numero1=rs1.getString("src").trim();
                }
                newCdr.setListTchn(fnCodigoTCHN(numero1));
                
                String numero2="";
                if(rs1.getString("did").trim().equals(""))
                {
                    numero2=rs1.getString("src").trim();
                }
                else
                {
                    numero2=rs1.getString("dst").trim();
                }
                newCdr.setListTchn2(fnCodigoTCHN(numero2));

                newCdr.setCalldateB(ftB.format(newCdr.getCalldate()));
                
                newCdrList.add(newCdr);
            }
            rs1.close();
            cStmt.close();
        } catch (SQLException | ParseException ex) {
            System.out.println(ex);
            //Logger.getLogger(CobCdrDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
            if (rs1 != null) {
              try { rs1.close(); } catch (SQLException e) { ; }
              rs1 = null;
            }
            if (cStmt != null) {
              try { cStmt.close(); } catch (SQLException e) { ; }
              cStmt = null;
            }
            if (cnMySql != null) {
              try { cnMySql.close(); } catch (SQLException e) { ; }
              cnMySql = null;
            }
        }
        System.out.println("num llamadas:"+String.valueOf(numllamdas));
        //System.out.println(" <f> fetchAll - Buscamos las llamadas " + LocalDateTime.now());
        return newCdrList;
    }    
    
    private String fnCodigoTCHN(String Numero)
    {
        //DECLARANDO FUNCION ORACLE
        CallableStatement stmt=null;
        OracleConnection oraCon=this.getCnO();
        String rpta=null;
        try
        {
            String sql="begin ? := PKG_COB_LLAMADAS.FN_CODIGO_TCHN(?); end;";
            stmt = oraCon.prepareCall(sql);
            stmt.registerOutParameter(1, OracleTypes.VARCHAR);
            stmt.setString(2, Numero);
            stmt.execute();
            rpta=stmt.getString(1);
            stmt.close();
        }
        catch(Exception ex)
        {
             System.out.println(ex);
        }
        return rpta;
    }    
    
    public OracleConnection getCnO() {
        return cnO;
    }

    public void setCnO(OracleConnection cnO) {
        this.cnO = cnO;
    }

}
