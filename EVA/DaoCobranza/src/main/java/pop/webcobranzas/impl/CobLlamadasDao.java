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
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobLlamadas;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.iface.ICobLlamadasDao;

import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.dao.FactoryDao;
/**
 *
 * @author Jyoverar
 */
public class CobLlamadasDao extends DBUtil implements ICobLlamadasDao{

    private OracleConnection cn = null;

    public CobLlamadasDao() {

    }

    public CobLlamadasDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    @Override
    public Integer insert(CobLlamadas oCobLlamadas) {
        //System.out.println(" <i> insertar llamada " + LocalDateTime.now());
        Integer newID = 0;
        OracleCallableStatement cmd = null;
        
        try {                        
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_INS_COB_LLAMADAS(?,?,?,?,?,?,?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertParameters(oCobLlamadas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID =  getOutputParameter("PO_C_COB_LLAMADAS_ID").getParameterInt();
                    
        } catch (SQLException e) {            
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {            
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
        }
        finally 
        {
            if (cmd != null) {
              try { cmd.close(); } catch (SQLException e) { ; }
              cmd = null;
            }
            if (cn != null) {
              try { cn.close(); } catch (SQLException e) { ; }
              cn = null;
            }
        }
        //System.out.println(" <f> insertar llamada " + LocalDateTime.now());
        return newID;
    }

    @Override
    public void update(CobLlamadas oCobLlamadas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CobLlamadas oCobLlamadas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CobLlamadas> fetchAll(CobLlamadas oCobLlamadas) {
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        ArrayList<CobLlamadas> lstLlamadas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_BUS_COB_LLAMADAS(?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_bus(oCobLlamadas);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setMaeFondo(fondo);
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                // llamada
                CobLlamadas cl = new CobLlamadas();
                cl.setCcodLlamadaId(resultSet.getInt("C_COB_LLAMADAS_ID"));
                cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cl.setCobSeguimiento(cs);
                
                CobTablas tf = new CobTablas();
                tf.setDdescCorta(resultSet.getString("D_DESC_CORTA_F"));
                CobTablas ts = new CobTablas();
                ts.setDdescCorta(resultSet.getString("D_DESC_CORTA_S"));
                cl.setTipoFamilia(tf);
                cl.setTipoAccion(ts);
                
                lstLlamadas.add(cl);
                        
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        return lstLlamadas;
    }

    @Override
    public ArrayList<CobLlamadas> findAbstractCall(CobLlamadas oCobLlamadas) {
        System.out.println(" <i>  findAbstractCall " + LocalDateTime.now());
        ArrayList<CobLlamadas> lstLlamadas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_BUS_COB_RESUMEN_LLAMADA(?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            // oList = listParameters_bus(oCobLlamadas);
            
            System.out.println("parm fondo: " + oCobLlamadas.getCobSeguimiento().getCobMaeSeguimiento().getMaeInversion().getMaeFondo().getCFondoId()); 
            
            oList.add(new ParameterOracle("PI_C_FONDO_ID", oCobLlamadas.getCobSeguimiento().getCobMaeSeguimiento().getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));            
            oList.add(new ParameterOracle("PI_FINICIO", oCobLlamadas.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("PI_FFIN", oCobLlamadas.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
            oList.add(new ParameterOracle("PI_C_USUARIO_ID", oCobLlamadas.getCobSeguimiento().getMetaDetalle().getC_usuario_id(), OracleTypes.CHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobLlamadas.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("PI_C_INVERSION", oCobLlamadas.getCobSeguimiento().getCobMaeSeguimiento().getMaeInversion().getCInversion(), OracleTypes.CHAR, ParameterDirection.Input));
            oList.add(new ParameterOracle("PI_CMONEDA", oCobLlamadas.getCobSeguimiento().getCobMaeSeguimiento().getMaeInversion().getCmoneda(), OracleTypes.CHAR, ParameterDirection.Input));         
            oList.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
            oList.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
            oList.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));


            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                
                //System.out.println(" <i> item " + resultSet.getString("C_INVERSION"));
                
                //metas
                CobCronogramaMetaDetalle metaDetalle = new CobCronogramaMetaDetalle();
                metaDetalle.setN_anio(resultSet.getString("N_ANIO"));
                metaDetalle.setN_mes(resultSet.getString("N_MES"));
                metaDetalle.setN_dia(resultSet.getString("N_DIA"));
                metaDetalle.setC_usuario_id(resultSet.getString("C_USUARIO_ID"));
                metaDetalle.setId_meta(resultSet.getInt("REGISTRO"));                
                
                        
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setCmoneda(resultSet.getString("CMONEDA"));
                
                inversion.setMaeFondo(fondo);
                
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                //cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                


                //cuota pago
                MaeCuotaPago cuota = new MaeCuotaPago();
                cuota.setTotalCuota(resultSet.getInt("CUOTAS_PENDIENTE"));
                cuota.setCronograma_pendiente(resultSet.getInt("CRONOGRAMA_PENDIENTE"));
                cuota.setfPagoCrono(resultSet.getDate("FPAGO"));
                
                
                //estado de cuenta
                MaeEstadoCuenta estadoCuenta = new MaeEstadoCuenta();
                estadoCuenta.setIsaldo(resultSet.getFloat("MSALDO"));
                
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                //cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                cs.setMetaDetalle(metaDetalle);
                cs.setCuotaPago(cuota);
                cs.setEstadoCuenta(estadoCuenta);
                

                
                // llamada
                CobLlamadas cl = new CobLlamadas();
                //cl.setCcodLlamadaId(resultSet.getInt("C_COB_LLAMADAS_ID"));
                cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cl.setTotalMes(resultSet.getInt("CANT_LLAMADA"));
                cl.setTotalDia(resultSet.getInt("POR_REC"));
                cl.setCsituacionId(resultSet.getString("C_SITUACION_ID"));
                cl.setCobSeguimiento(cs);
                
                CobTablas ts = new CobTablas();
                ts.setCtablaDetId(resultSet.getString("C_SITUACION_ID"));
                ts.setDdescCorta(resultSet.getString("D_SITUACION"));
                
                cl.setTipoAccion(ts);
                
                lstLlamadas.add(cl);
                
                    
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        
        
        ArrayList<CobLlamadas> grupoLLamada = new ArrayList<>();
        CobLlamadas ultimo;
        int total = 0;
        int contacto= 0;
        
        String anio = "";
        String mes = "";
        String dia = "";
        String asesor = "";
                
        for(CobLlamadas llamada : lstLlamadas){

            
            if( !anio.equals(llamada.getCobSeguimiento().getMetaDetalle().getN_anio()) ||
                !mes.equals(llamada.getCobSeguimiento().getMetaDetalle().getN_mes()) ||
                !dia.equals(llamada.getCobSeguimiento().getMetaDetalle().getN_dia()) ||
                !asesor.equals(llamada.getCobSeguimiento().getMetaDetalle().getC_usuario_id())
                ){
                ultimo = llamada;
                total = ultimo.getCobSeguimiento().getMetaDetalle().getId_meta();
                contacto = ultimo.getTotalDia();
                grupoLLamada.add(ultimo);
                
                anio=llamada.getCobSeguimiento().getMetaDetalle().getN_anio();
                mes=llamada.getCobSeguimiento().getMetaDetalle().getN_mes();
                dia=llamada.getCobSeguimiento().getMetaDetalle().getN_dia();
                asesor=llamada.getCobSeguimiento().getMetaDetalle().getC_usuario_id();
                
            }else{
                total = total + llamada.getCobSeguimiento().getMetaDetalle().getId_meta();
                contacto = contacto + llamada.getTotalDia();
                grupoLLamada.get(grupoLLamada.size()-1).getCobSeguimiento().getMetaDetalle().setTotal_asesor(total);
                grupoLLamada.get(grupoLLamada.size()-1).getCobSeguimiento().getMetaDetalle().setTotal_llamada(contacto);
            }
            
        };
        
        for(CobLlamadas ob1:grupoLLamada ){

            double v1 = 0.0;
            double v2 = 0.0;
            double v3 = 0.0;
            try{
                v1=ob1.getCobSeguimiento().getMetaDetalle().getTotal_llamada();
                v2=ob1.getCobSeguimiento().getMetaDetalle().getTotal_asesor();
                v3 = v1/v2*100;
            }catch(Exception e){
            }
                    
            for(CobLlamadas ob2:lstLlamadas){
                if(
                ob1.getCobSeguimiento().getMetaDetalle().getN_anio().equals(ob2.getCobSeguimiento().getMetaDetalle().getN_anio()) &&
                ob1.getCobSeguimiento().getMetaDetalle().getN_mes().equals(ob2.getCobSeguimiento().getMetaDetalle().getN_mes()) &&
                ob1.getCobSeguimiento().getMetaDetalle().getN_dia().equals(ob2.getCobSeguimiento().getMetaDetalle().getN_dia()) &&
                ob1.getCobSeguimiento().getMetaDetalle().getC_usuario_id().equals(ob2.getCobSeguimiento().getMetaDetalle().getC_usuario_id())
                ){
                    ob2.setCumplimientoDia((int)v3);
                    
                    System.out.println("Asesor Meta: " +ob1.getCobSeguimiento().getMetaDetalle().getC_usuario_id()+
                                       "\tTotal dia: "+ v1+
                                       "\tTotal contacto: "+ v2+
                                       "\tPorcentaje: "+ v3);
                    
                }
            }
            
        }
        
        grupoLLamada = new ArrayList<>();
        ultimo = null;
        total = 0;
        contacto= 0;
        
        anio = "";
        mes = "";
        dia = "";
        asesor = "";      
        
        for(CobLlamadas llamada : lstLlamadas){

            
            if( !anio.equals(llamada.getCobSeguimiento().getMetaDetalle().getN_anio()) ||
                !mes.equals(llamada.getCobSeguimiento().getMetaDetalle().getN_mes()) ||                
                !asesor.equals(llamada.getCobSeguimiento().getMetaDetalle().getC_usuario_id())
                ){
                ultimo = llamada;
                total = ultimo.getCobSeguimiento().getMetaDetalle().getId_meta();
                contacto = ultimo.getTotalDia();
                grupoLLamada.add(ultimo);
                
                anio=llamada.getCobSeguimiento().getMetaDetalle().getN_anio();
                mes=llamada.getCobSeguimiento().getMetaDetalle().getN_mes();
                asesor=llamada.getCobSeguimiento().getMetaDetalle().getC_usuario_id();
                
                    System.out.println("Agrupa mes asesor Meta: " +asesor);                
                
            }else{
                total = total + llamada.getCobSeguimiento().getMetaDetalle().getId_meta();
                contacto = contacto + llamada.getTotalDia();
                grupoLLamada.get(grupoLLamada.size()-1).getCobSeguimiento().getMetaDetalle().setTotal_asesor(total);
                grupoLLamada.get(grupoLLamada.size()-1).getCobSeguimiento().getMetaDetalle().setTotal_llamada(contacto);
            }
            
        };
        
       
        for(CobLlamadas ob1:grupoLLamada ){

            double v1 = 0.0;
            double v2 = 0.0;
            double v3 = 0.0;
            try{
                v1=ob1.getCobSeguimiento().getMetaDetalle().getTotal_llamada();
                v2=ob1.getCobSeguimiento().getMetaDetalle().getTotal_asesor();
                v3 = v1/v2*100;
            }catch(Exception e){
            }
                    
            for(CobLlamadas ob2:lstLlamadas){
                if(
                ob1.getCobSeguimiento().getMetaDetalle().getN_anio().equals(ob2.getCobSeguimiento().getMetaDetalle().getN_anio()) &&
                ob1.getCobSeguimiento().getMetaDetalle().getN_mes().equals(ob2.getCobSeguimiento().getMetaDetalle().getN_mes()) &&
                ob1.getCobSeguimiento().getMetaDetalle().getC_usuario_id().equals(ob2.getCobSeguimiento().getMetaDetalle().getC_usuario_id())
                ){
                    ob2.setCumplimientoMes((int)v3);
                    
                    System.out.println("Asesor Meta Mes-: " +ob1.getCobSeguimiento().getMetaDetalle().getC_usuario_id()+
                                       "\tTotal mes: "+ v1+
                                       "\tTotal contacto mes: "+ v2+
                                       "\tPorcentaje Mes: "+ v3);
                    
                }
            }
            
        }        
        
                
        System.out.println(" <f>  findAbstractCall " + LocalDateTime.now());
        return lstLlamadas;
    }
    
    @Override
    public ArrayList<CobLlamadas> fetchAllPhone(CobCdr oCobCdr) {
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        ArrayList<CobLlamadas> lstLlamadas = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            // name of procedure
            String sp = "{call PKG_COB_LLAMADAS.SP_BUS_COB_LLAMADAS_TELE(?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_busPhone(oCobCdr);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);

            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                // fondo
                MaeFondo fondo = new MaeFondo();
                fondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                fondo.setDFondo(resultSet.getString("D_FONDO"));
                //inversion
                MaeInversion inversion = new MaeInversion();
                inversion.setcMaeInversionId(resultSet.getInt("C_MAE_INVERSION_ID"));
                inversion.setCInversion(resultSet.getString("C_INVERSION"));
                inversion.setMaeFondo(fondo);
                // mae seguimiento
                CobMaeSeguimiento cms = new CobMaeSeguimiento();
                cms.setCmaeSeguimientoId(resultSet.getInt("C_MAE_SEGUIMIENTO_ID"));
                cms.setMaeInversion(inversion);
                // cob seguimiento
                CobSeguimiento cs = new CobSeguimiento();
                cs.setCcobSeguimientoId(resultSet.getInt("C_COB_SEGUIMIENTO_ID"));
                cs.setCobMaeSeguimiento(cms);
                // llamada
                CobLlamadas cl = new CobLlamadas();
                cl.setCcodLlamadaId(resultSet.getInt("C_COB_SEG_DET_ID"));
                cl.setCcodDisposicionId(resultSet.getString("C_DISPOSICION_ID"));
                cl.setCtipoFamiliaId(resultSet.getString("C_TIPO_FAMILIA_ID"));
                cl.setDtipoFamilia(resultSet.getString("D_FAMILIA"));
                cl.setDdescripcion(resultSet.getString("D_DESCRIPCION"));
                cl.setcUsuarioAdd(resultSet.getString("C_USUARIO_ADD"));
                cl.setfUsuarioAdd(resultSet.getDate("F_USUARIO_ADD"));
                cl.setCobSeguimiento(cs);
                
                CobTablas tf = new CobTablas();
                tf.setDdescCorta(resultSet.getString("D_DESC_CORTA_F"));
                CobTablas ts = new CobTablas();
                ts.setDdescCorta(resultSet.getString("D_DESC_CORTA_S"));
                cl.setTipoFamilia(tf);
                cl.setTipoAccion(ts);
                
                lstLlamadas.add(cl);
                        
            }

        } catch (Exception e) {
            Logger.getLogger(CobLlamadasDao.class.getName()).log(Level.SEVERE, null, e);
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
        //System.out.println(" <i> CobLlamadasDao fetchAll " + LocalDateTime.now());
        return lstLlamadas;
    }
    
    
    
    private List<ParameterOracle> insertParameters(CobLlamadas oCobLlamadas){
        List<ParameterOracle> oListParam = new ArrayList<>();
        
        oListParam.add(new ParameterOracle("PI_C_COB_SEGUIMIENTO_ID", oCobLlamadas.getCobSeguimiento().getCcobSeguimientoId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_COB_LLAMADA", oCobLlamadas.getCcodLlamada(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_DISPOSICION_ID", oCobLlamadas.getCcodDisposicionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TIPO_FAMILIA_ID", oCobLlamadas.getCtipoFamiliaId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_FAMILIA", oCobLlamadas.getDtipoFamilia(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_SITUACION_ID",oCobLlamadas.getCsituacionId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_DESCRIPCION", oCobLlamadas.getDdescripcion(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobLlamadas.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
                
        oListParam.add(new ParameterOracle("PO_C_COB_LLAMADAS_ID", 0, OracleTypes.NUMBER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
                
        return oListParam;
    }

    private List<ParameterOracle> listParameters_bus(CobLlamadas oCobLlamadas) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_FINICIO", oCobLlamadas.getfIniBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_FFIN", oCobLlamadas.getfFinBusq(), OracleTypes.DATE, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO_ADD", oCobLlamadas.getcUsuarioAdd(), OracleTypes.CHAR, ParameterDirection.Input));
        
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));

        return oListParam;
    }

    private List<ParameterOracle> listParameters_busPhone(CobCdr oCobCdr) {
        List<ParameterOracle> oListParam = new ArrayList<>();

        oListParam.add(new ParameterOracle("PI_A_NUMERO", oCobCdr.getDst(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_F_USUARIO_ADD", oCobCdr.getCalldate(), OracleTypes.DATE, ParameterDirection.Input));
                
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
