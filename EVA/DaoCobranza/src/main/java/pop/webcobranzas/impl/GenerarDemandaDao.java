/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import pop.comun.dominio.LegGeneraDemanda;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.common.DBUtil;
import pop.webcobranzas.common.ParameterDirection;
import pop.webcobranzas.common.ParameterOracle;
import pop.webcobranzas.conn.Conexion;
import pop.webcobranzas.iface.IGenerarDemandaDao;

/**
 *
 * @author PR154357
 */
public class GenerarDemandaDao extends DBUtil implements IGenerarDemandaDao{
    
    private OracleConnection cn = null;

    
    public GenerarDemandaDao() {
        super();
    }

    public GenerarDemandaDao(OracleConnection cnx) {
        cn = cnx;
    }
    
    private static final Logger LOG = Logger.getLogger(GenerarDemandaDao.class.getName());
    
    @Override
    public List<LegGeneraDemanda> buscarDemandado(LegGeneraDemanda oLegGeneraDemanda) {
        LOG.info("Ingresa a DAO");
        List<LegGeneraDemanda> lstTchn = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            String sp = "{call EVA.PKG_LEGAL.SP_BUSCAR_DEMANDADO (?,?,?,?,?,?,?,?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_crearDemanda(oLegGeneraDemanda);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            while (resultSet.next()) {
                //fondo
                MaeFondo maeFondo = new MaeFondo();
                maeFondo.setCFondoId(resultSet.getString("C_FONDO_ID"));
                maeFondo.setDFondo(resultSet.getString("D_FONDO"));
                
                //inversion
                MaeInversion newInversion = new MaeInversion();
                newInversion.setCInversion(resultSet.getString("C_INVERSION"));
                newInversion.setcMaeInversionId(Integer.parseInt(resultSet.getString("C_MAE_INVERSION_ID")));
                newInversion.setcTipoInv(resultSet.getString("C_TIPO_INV"));
                newInversion.setMaeFondo(maeFondo);
                
                //persona
                MaePersona persona = new MaePersona();
                persona.setMaeFondo(maeFondo);
                persona.setCPersonaId(Integer.parseInt(resultSet.getString("C_PERSONA_ID")));
                persona.setANroDocumento(resultSet.getString("A_NRO_DOCUMENTO"));
                persona.setDApePat(resultSet.getString("D_APE_PAT"));
                persona.setDApeMat(resultSet.getString("D_APE_MAT"));
                persona.setDNombres(resultSet.getString("D_NOMBRES"));
                newInversion.setcPersonaId(persona);
                
                LegGeneraDemanda generaDemanda = new LegGeneraDemanda();                
                generaDemanda.setFondo(maeFondo);
                generaDemanda.setMaeInversion(newInversion);
                generaDemanda.setMaePersona(persona);
                generaDemanda.setcInmueble(resultSet.getString("C_INMUEBLE"));
                generaDemanda.setiInversion(resultSet.getString("I_INVERSION"));
                generaDemanda.setDireccion(resultSet.getString("A_DIRECCION"));
                generaDemanda.setDireccionNotificacion(resultSet.getString("DIRNOTIF"));
                generaDemanda.setNroCuotasAtrasadas(resultSet.getInt("NROCUOTAS_ATRAS"));
                generaDemanda.setFechaUltDeposito(resultSet.getDate("FEC_ULT_DEPO"));
                generaDemanda.setTotalDeposito(resultSet.getFloat("TOT_DEPOS"));
                generaDemanda.setCancelado(resultSet.getString("CANCELADO"));//1
                generaDemanda.setAmpliado(resultSet.getString("AMPLIACION"));
                generaDemanda.setRefinanciado(resultSet.getString("REFINANCIADO"));//1
                generaDemanda.setJudicial(resultSet.getString("JUDICIAL"));//1
                generaDemanda.setTransfendosado(resultSet.getString("TRANFEND"));
                generaDemanda.setTransfrefin(resultSet.getString("TRANFREF"));
                generaDemanda.setTransfAmpl(resultSet.getString("TRANFAMP"));
                generaDemanda.setEjudicial(resultSet.getString("EJUDICIAL"));
                lstTchn.add(generaDemanda);
            }
            
        } catch (Exception e) {
            //System.out.println(e);
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
        return lstTchn;
    }

    @Override
    public List<LegGeneraDemanda> generarDemanda(LegGeneraDemanda oLegGeneraDemanda) {
        LOG.info("Ingresa a DAO generarDemandaFondo(LegGeneraDemanda "+oLegGeneraDemanda+")");
        LOG.info("FondoId = "+oLegGeneraDemanda.getFondoId());
        List<LegGeneraDemanda> listaTotal = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        String sp = null;
        try {
            if(oLegGeneraDemanda.getFondoId().equalsIgnoreCase("0001")){
                sp = "{call EVA.PKG_LEGAL.SP_GENERA_DEMANDA_SACIF (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                LOG.info("Se ha ejecutado "+sp);
            }else if(oLegGeneraDemanda.getFondoId().equalsIgnoreCase("0002")){
                sp = "{call EVA.PKG_LEGAL.SP_GENERA_DEMANDA_SACIF_POP (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                LOG.info("Se ha ejecutado "+sp);
            }else if(oLegGeneraDemanda.getFondoId().equalsIgnoreCase("0003")){
                sp = "{call EVA.PKG_LEGAL.SP_GENERA_DEMANDA_SACIF_MYP (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                LOG.info("Se ha ejecutado "+sp);
            }else{
                sp = "{call EVA.PKG_LEGAL.SP_GENERA_DEMANDA_SACIF_PRH (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                LOG.info("Se ha ejecutado "+sp);
            }            
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = listParameters_crearDemandaWord(oLegGeneraDemanda);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();
            Set<String> distinctFondoDescrip=new TreeSet<>();
            Set<String> distinctNomApe=new TreeSet<>();
            Set<String> distinctDireccion=new TreeSet<>();
            Set<String> distinctDistrito=new TreeSet<>();
            Set<String> distinctProvincia=new TreeSet<>();
            Set<String> distinctPagoTitulo=new TreeSet<>();
            Set<String> distinctPlazoMeses=new TreeSet<>();
            Set<String> distinctCuotas=new TreeSet<>();
            Set<String> distinctCuotaVencimiento=new TreeSet<>();
            Set<String> distinctNCuota=new TreeSet<>();
            Set<String> distinctFechaEscritura=new TreeSet<>();
            Set<String> distinctCreditoFechaEscritura=new TreeSet<>();
            Set<String> distinctFechaEscrituraFTCHN=new TreeSet<>();
            Set<String> distinctCredito=new TreeSet<>();
            Set<String> distinctGravamen=new TreeSet<>();
            Set<String> distinctAsientoCredito=new TreeSet<>();
            Set<String> distinctPartida=new TreeSet<>();
            Set<String> distinctPagoEnTexto=new TreeSet<>();
            Set<String> distinctFechaCartaNotarial=new TreeSet<>();
            Set<String> distinctValorDolares=new TreeSet<>();
            Set<String> distinctFechaDeCorte=new TreeSet<>();
            Set<String> distinctFechaDemanda=new TreeSet<>();
            Set<String> distinctMontoADemandarLargo=new TreeSet<>();
            Set<String> distinctMontoADemandar=new TreeSet<>();
            Set<String> cargaTCHN=new TreeSet<>();
            Set<String> cargaCINMUEBLE=new TreeSet<>();
            Set<String> cargaFondoId=new TreeSet<>();
            while (resultSet.next()) {
                LegGeneraDemanda generaDemanda = new LegGeneraDemanda();
                generaDemanda.setFondoDescrip(resultSet.getString("D_FONDO"));
                distinctFondoDescrip.add(generaDemanda.getFondoDescrip()!=null?generaDemanda.getFondoDescrip():"");
                distinctNomApe.add(resultSet.getString("NOMBRES")+" "+resultSet.getString("APEPAT")+" "+resultSet.getString("APEMAT")+", identificado(a) con DNI N° "+resultSet.getString("NRODOC"));
                generaDemanda.setDireccion(resultSet.getString("DIRECCION")+", DISTRITO DE "+resultSet.getString("DISTRITO")+", PROVINCIA DE "+resultSet.getString("PROV")+", DEPARTAMENTO DE LIMA");
                distinctDireccion.add(generaDemanda.getDireccion()!=null?generaDemanda.getDireccion():"");
                generaDemanda.setDistrito(resultSet.getString("DISTRITO"));
                distinctDistrito.add(generaDemanda.getDistrito()!=null?generaDemanda.getDistrito():"");
                generaDemanda.setProvincia(resultSet.getString("PROV"));
                distinctProvincia.add(generaDemanda.getProvincia()!=null?generaDemanda.getProvincia():"");
                generaDemanda.setmValNomTitulo(resultSet.getString("MONEDASIMBOLO")+" "+resultSet.getString("NOMTITULO")+" ("+resultSet.getString("NOMTITULO_LETRAS")+" "+resultSet.getString("MONLARGA")+")");
                distinctPagoTitulo.add(generaDemanda.getmValNomTitulo()!=null?generaDemanda.getmValNomTitulo():"");
                generaDemanda.setnPlazoMeses(resultSet.getString("PLAZOMESES"));
                distinctPlazoMeses.add(generaDemanda.getnPlazoMeses()!=null?generaDemanda.getnPlazoMeses():"");
                generaDemanda.setmCuota(resultSet.getString("MONEDASIMBOLO")+" "+resultSet.getString("MCUOTA")+" ("+resultSet.getString("MCUOTA_LETRAS")+" "+resultSet.getString("MONLARGA")+")");
                distinctCuotas.add(generaDemanda.getmCuota()!=null?generaDemanda.getmCuota():"");
                if(resultSet.getString("NCUOTA").equalsIgnoreCase("1")){
                    distinctCuotaVencimiento.add("la primera de ellas el "+resultSet.getString("EMISION_Y_VENCIMIENTO"));
                }
                if(resultSet.getString("NCUOTA").equalsIgnoreCase(resultSet.getString("PLAZOMESES"))){
                    distinctCuotaVencimiento.add("la última el "+resultSet.getString("EMISION_Y_VENCIMIENTO"));               
                }
                distinctNCuota.add(resultSet.getString("NCUOTA"));
                generaDemanda.setfTchn(resultSet.getString("F_ESCRITURA"));                
                generaDemanda.setsTchn(resultSet.getString("STCHN"));
                if(generaDemanda.getsTchn()!=null && generaDemanda.getfTchn()!=null){
                    distinctCreditoFechaEscritura.add(generaDemanda.getsTchn()+" de fecha "+generaDemanda.getfTchn());
                    distinctCreditoFechaEscritura.add(generaDemanda.getsTchn()+" emitido el "+generaDemanda.getfTchn());
                    distinctFechaEscrituraFTCHN.add(generaDemanda.getfTchn()!=null?generaDemanda.getfTchn():"");
                }
                if(generaDemanda.getsTchn()==null){
                    distinctFechaEscritura.add(generaDemanda.getfTchn()!=null?generaDemanda.getfTchn():"");
                }                
                distinctCredito.add(generaDemanda.getsTchn()!=null?generaDemanda.getsTchn():"");
                generaDemanda.setsAshipo(resultSet.getString("ASHIPO"));
                distinctGravamen.add(generaDemanda.getsAshipo()!=null?generaDemanda.getsAshipo():"");
                generaDemanda.setsAsexptchn(resultSet.getString("ASEXPTCHN"));
                distinctAsientoCredito.add(generaDemanda.getsAsexptchn()!=null?generaDemanda.getsAsexptchn():"");
                generaDemanda.setPartida(resultSet.getString("PARTIDA"));
                distinctPartida.add(generaDemanda.getPartida()!=null?generaDemanda.getPartida():"");
                generaDemanda.setPago(resultSet.getString("PAGO_LETRAS")+" ("+resultSet.getString("PAGO")+")");
                distinctPagoEnTexto.add(generaDemanda.getPago()!=null?generaDemanda.getPago():"");
                distinctFechaCartaNotarial.add(resultSet.getString("F_CN")!=null?resultSet.getString("F_CN"):"");
                generaDemanda.setmValorDolRealiza(resultSet.getString("VALORDOL")+" ("+resultSet.getString("VALORDOL_LETRAS")+" dólares americanos)");
                distinctValorDolares.add(generaDemanda.getmValorDolRealiza()!=null?generaDemanda.getmValorDolRealiza():"");
                generaDemanda.setmCorte(resultSet.getString("FCORTE"));
                distinctFechaDeCorte.add(generaDemanda.getmCorte()!=null?generaDemanda.getmCorte():"");
                generaDemanda.setFechaDemanda("SALDO DEUDOR al "+resultSet.getString("FECHADEMANDA"));
                distinctFechaDemanda.add(generaDemanda.getFechaDemanda()!=null?generaDemanda.getFechaDemanda():"");                
                generaDemanda.setMontoADemandarLargo(resultSet.getString("MONEDASIMBOLO")+" "+resultSet.getString("MONTOADEMANDAR")+" ("+resultSet.getString("MONTOADEM_LETRAS")+" "+resultSet.getString("MONLARGA")+")");
                distinctMontoADemandarLargo.add(generaDemanda.getMontoADemandarLargo()!=null?generaDemanda.getMontoADemandarLargo():"");
                generaDemanda.setMontoADemandar(resultSet.getString("MONEDASIMBOLO")+" "+resultSet.getString("MONTOADEMANDAR"));
                distinctMontoADemandar.add(generaDemanda.getMontoADemandar()!=null?generaDemanda.getMontoADemandar():"");
                generaDemanda.setTchn(resultSet.getString("TCHN"));
                cargaTCHN.add(generaDemanda.getTchn());
                generaDemanda.setcInmueble(resultSet.getString("CINMUEBLE"));
                cargaCINMUEBLE.add(generaDemanda.getcInmueble());
                generaDemanda.setFondoId(resultSet.getString("CFONDOID"));
                cargaFondoId.add(generaDemanda.getFondoId());
            }            
            List<String> listaDeNombresConDNI=new ArrayList<>();
            List<String> listaDeCartasNotariales=new ArrayList<>();
            List<String> listaDeCuotasVencimiento=new ArrayList<>(); 
            List<String> listaDeCuotas=new ArrayList<>();
            List<String> listaCreditoFechaEscritura=new ArrayList<>();            
            LegGeneraDemanda objLegGeneraDemanda=new LegGeneraDemanda();
            for(String s:distinctFondoDescrip){
                objLegGeneraDemanda.setFondoDescrip(s);
            }
            for(String s:distinctNomApe){            
                listaDeNombresConDNI.add(s);
            }
            if(listaDeNombresConDNI.size()>0){
                StringBuilder nombresconDNIenlistados = new StringBuilder();
                for (int i = 0; i < listaDeNombresConDNI.size(); i++) {
                    nombresconDNIenlistados.append(listaDeNombresConDNI.get(i));
                    if (i < listaDeNombresConDNI.size() - 1) {
                        if (i == listaDeNombresConDNI.size() - 2) {
                            nombresconDNIenlistados.append(" y ");
                        } else {
                            nombresconDNIenlistados.append(", ");
                        }
                    }
                }
                objLegGeneraDemanda.setNombresDNILista(listaDeNombresConDNI);
                objLegGeneraDemanda.setNombresDNI(nombresconDNIenlistados.toString());                
            }else{
                objLegGeneraDemanda.setNombresDNILista(listaDeNombresConDNI);
                objLegGeneraDemanda.setNombresDNI("");                
            }

            for(String s:distinctDireccion){
                objLegGeneraDemanda.setDireccion(s);
            }
            for(String s:distinctDistrito){
                objLegGeneraDemanda.setDistrito(s);
            }
            for(String s:distinctProvincia){
                objLegGeneraDemanda.setProvincia(s);
            }
            for(String s:distinctPagoTitulo){
                objLegGeneraDemanda.setmValNomTitulo(s);
            }
            for(String s:distinctPlazoMeses){
                 objLegGeneraDemanda.setnPlazoMeses(s);   
            }
            for(String s:distinctCuotas){
                objLegGeneraDemanda.setmCuota(s);    
            }
            for(String s:distinctCuotaVencimiento){
                listaDeCuotasVencimiento.add(s);
            }
            if(listaDeCuotasVencimiento.size()>0){
                StringBuilder vencimientosDeCuotasEnlistados = new StringBuilder();
                for (int i = 0; i < listaDeCuotasVencimiento.size(); i++) {
                    vencimientosDeCuotasEnlistados.append(listaDeCuotasVencimiento.get(i));
                    if (i < listaDeCuotasVencimiento.size() - 1) {
                        if (i == listaDeCuotasVencimiento.size() - 2) {
                            vencimientosDeCuotasEnlistados.append(" y ");
                        } else {
                            vencimientosDeCuotasEnlistados.append(", ");
                        }
                    }
                }
                objLegGeneraDemanda.setCuotasEmisionYVencimientoLista(listaDeCuotasVencimiento);        
                objLegGeneraDemanda.setCuotasEmisionYVencimiento(vencimientosDeCuotasEnlistados.toString()); 
            }else{
                objLegGeneraDemanda.setCuotasEmisionYVencimientoLista(listaDeCuotasVencimiento);        
                objLegGeneraDemanda.setCuotasEmisionYVencimiento("");                 
            }
            for(String s:distinctNCuota){
                listaDeCuotas.add(s);
            }
            if(listaDeCuotas.size()>0){
                StringBuilder vencimientosDeCuotas = new StringBuilder();
                for (int i = 0; i < listaDeCuotas.size(); i++) {
                    vencimientosDeCuotas.append(listaDeCuotas.get(i));
                    if (i < listaDeCuotas.size() - 1) {
                        if (i == listaDeCuotas.size() - 2) {
                            vencimientosDeCuotas.append(" y ");
                        } else {
                            vencimientosDeCuotas.append(", ");
                        }
                    }
                }
                objLegGeneraDemanda.setCuotasLista(listaDeCuotas);        
                objLegGeneraDemanda.setnCuota(vencimientosDeCuotas.toString());
            }else{
                objLegGeneraDemanda.setCuotasLista(listaDeCuotas);        
                objLegGeneraDemanda.setnCuota("");                
            }
            for(String s:distinctFechaEscritura){
               if(!s.equals("")){
                    objLegGeneraDemanda.setfEscritura(s);      
               }
            }
            for(String s:distinctCreditoFechaEscritura){
               listaCreditoFechaEscritura.add(s);
            }
            
            if(listaCreditoFechaEscritura.size()>0){
                objLegGeneraDemanda.setDeFecha(listaCreditoFechaEscritura.get(0)!=null?listaCreditoFechaEscritura.get(0):"");  
                objLegGeneraDemanda.setEmitidoEl(listaCreditoFechaEscritura.get(1)!=null?listaCreditoFechaEscritura.get(1):"");                
            }else{
                objLegGeneraDemanda.setDeFecha("");  
                objLegGeneraDemanda.setEmitidoEl("");                
            }
            
            for(String s:distinctFechaEscrituraFTCHN){
               if(!s.equals("")){
                    objLegGeneraDemanda.setfTchn(s);   
               }
            }
            for(String s:distinctCredito){
               if(!s.equals("")){
                    objLegGeneraDemanda.setsTchn(s);      
               }
            }
            for(String s:distinctGravamen){
                if(!s.equals("")){
                    objLegGeneraDemanda.setsAshipo(s);
                }
            }
            for(String s:distinctAsientoCredito){
                if(!s.equals("")){
                    objLegGeneraDemanda.setsAsexptchn(s);
                }
            }
            for(String s:distinctPartida){
                objLegGeneraDemanda.setPartida(s);
            }
            for(String s:distinctPagoEnTexto){
                objLegGeneraDemanda.setPago(s);
            }
            for(String s:distinctFechaCartaNotarial){            
                listaDeCartasNotariales.add(s);
            }

            if(listaDeCartasNotariales.size()>0){
                StringBuilder cartasNotarialesEnlistadas = new StringBuilder();
                for (int i = 0; i < listaDeCartasNotariales.size(); i++) {
                    cartasNotarialesEnlistadas.append(listaDeCartasNotariales.get(i));
                    if (i < listaDeCartasNotariales.size() - 1) {
                        if (i == listaDeCartasNotariales.size() - 2) {
                            cartasNotarialesEnlistadas.append(" y ");
                        } else {
                            cartasNotarialesEnlistadas.append(", ");
                        }
                    }
                }
                objLegGeneraDemanda.setCartasNotarialesLista(listaDeCartasNotariales);        
                objLegGeneraDemanda.setfCartasNotariales(cartasNotarialesEnlistadas.toString());
            }else{
                objLegGeneraDemanda.setCartasNotarialesLista(listaDeCartasNotariales);
                objLegGeneraDemanda.setfCartasNotariales("");                
            }

            for(String s:distinctValorDolares){
                objLegGeneraDemanda.setmValorDolRealiza(s);                
            }        
            for(String s:distinctFechaDeCorte){
                objLegGeneraDemanda.setmCorte(s);            
            }
            for(String s:distinctFechaDemanda){
                objLegGeneraDemanda.setFechaDemanda(s);
            }
            for(String s:distinctMontoADemandarLargo){
                objLegGeneraDemanda.setMontoADemandarLargo(s);            
            }        
            for(String s:distinctMontoADemandar){
                objLegGeneraDemanda.setMontoADemandar(s);            
            }        
            objLegGeneraDemanda.setUsuario(oLegGeneraDemanda.getUsuario());            
            for(String s:cargaTCHN){
                objLegGeneraDemanda.setTchn(s);
            }
            for(String s:cargaCINMUEBLE){
                objLegGeneraDemanda.setcInmueble(s);
            }
            for(String s:cargaFondoId){
                objLegGeneraDemanda.setFondoId(s);
            }
            listaTotal.add(objLegGeneraDemanda);
            LOG.info("### Documento a presentar ###");
            
            for(LegGeneraDemanda documento:listaTotal){
                        LOG.info("dFondo\t\t: "+documento.getFondoDescrip());
                        LOG.info("nombresDNI\t: "+documento.getNombresDNI());
                        LOG.info("direccion\t: "+documento.getDireccion());
                        LOG.info("distrito\t: "+documento.getDistrito());
                        LOG.info("provincia\t: "+documento.getProvincia());
                        LOG.info("mValNomTitulo\t: "+documento.getmValNomTitulo());
                        LOG.info("nPlazoMeses\t: "+documento.getnPlazoMeses());
                        LOG.info("mCuota\t\t: "+documento.getmCuota());
                        LOG.info("EmisnVencimnto\t: "+documento.getCuotasEmisionYVencimiento());
                        LOG.info("nCuota\t\t: "+documento.getnCuota());
                        LOG.info("fTchn\t\t: "+documento.getfTchn());                        
                        LOG.info("fEscritura\t: "+documento.getfEscritura());
                        LOG.info("deFecha\t\t: "+documento.getDeFecha());
                        LOG.info("emitidoEl\t: "+documento.getEmitidoEl());                                
                        LOG.info("sTchn\t\t: "+documento.getsTchn());
                        LOG.info("sAshipo\t\t: "+documento.getsAshipo()); 
                        LOG.info("sAsexptchn\t: "+documento.getsAsexptchn());
                        LOG.info("partida\t\t: "+documento.getPartida());
                        LOG.info("pago\t\t: "+documento.getPago());
                        LOG.info("fCn\t\t: "+documento.getfCartasNotariales());
                        LOG.info("mValorDolRealiza : "+documento.getmValorDolRealiza());
                        LOG.info("mCorte\t\t: "+documento.getmCorte());
                        LOG.info("fechaDemanda\t: "+documento.getFechaDemanda());
                        LOG.info("montoADemandar\t: "+documento.getMontoADemandar());
                        LOG.info("montoADemandarLargo: "+documento.getMontoADemandarLargo());
                        LOG.info("usuario\t\t: "+documento.getUsuario());
                        LOG.info("Tchn\t\t: "+documento.getTchn());
                        LOG.info("cInmueble\t: "+documento.getcInmueble());
                        LOG.info("FondoId\t\t: "+documento.getFondoId());
            }
        } catch(java.sql.SQLSyntaxErrorException sqle){
            LOG.info("ORA-01722: número no válido");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        finally {
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
        return listaTotal;
    }
    
    private List<ParameterOracle> listParameters_crearDemanda(LegGeneraDemanda oLegGeneraDemanda) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oLegGeneraDemanda.getMaeInversion().getMaeFondo().getCFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oLegGeneraDemanda.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oLegGeneraDemanda.getMaeInversion().getCInversion(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oLegGeneraDemanda.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oLegGeneraDemanda.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", oLegGeneraDemanda.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoPad(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", oLegGeneraDemanda.getMaeInversion().getMaeInmueble().getMaeUbigeo().getCUbigeoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
    private List<ParameterOracle> listParameters_crearDemandaWord(LegGeneraDemanda oLegGeneraDemanda) {
        List<ParameterOracle> oListParam = new ArrayList<>();
        oListParam.add(new ParameterOracle("PI_C_FONDO_ID", oLegGeneraDemanda.getFondoId(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MAE_INVERSION_ID", oLegGeneraDemanda.getMaeInversion().getcMaeInversionId(), OracleTypes.NUMBER, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INVERSION", oLegGeneraDemanda.getTchn(), OracleTypes.VARCHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_A_NRO_DOCUMENTO", oLegGeneraDemanda.getMaeInversion().getcPersonaId().getANroDocumento(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_APENOMBRE", oLegGeneraDemanda.getdDatoBusq(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_P", null, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_UBIGEO_ID_D", null, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_INMUEBLE_P", oLegGeneraDemanda.getcInmueble(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_D_INGRESO_DEMANDA", oLegGeneraDemanda.getFechaDemanda(), OracleTypes.CHAR, ParameterDirection.Input));
        //oListParam.add(new ParameterOracle("PI_C_MONTO_A_DEMANDAR", oLegGeneraDemanda.getMontoADemandar()!=null?oLegGeneraDemanda.getMontoADemandar().replace(".", ","):"", OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_MONTO_A_DEMANDAR", oLegGeneraDemanda.getMontoADemandar()!=null?oLegGeneraDemanda.getMontoADemandar().replace(",", "."):"", OracleTypes.CHAR, ParameterDirection.Input));
        String cantidadSinMoneda = oLegGeneraDemanda.getValorComercial()!=null?oLegGeneraDemanda.getValorComercial().replaceAll("[^0-9.]", ""):"";
        //oListParam.add(new ParameterOracle("PI_C_VALOR_COMERCIAL", cantidadSinMoneda.replace(".", ","), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_VALOR_COMERCIAL", cantidadSinMoneda.replace(",", "."), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        //System.out.println("_PARAMETROS COMPLETADOS_");
        return oListParam;
    }
        
    @Override
    public Integer insertHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;
        try {
            // name of procedure
            String sp = "{call EVA.PKG_LEGAL.SP_HISTORICO_DEMANDA(?,?,?,?,?,?,?,?,?,?,?)}";

            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = insertHistoricoParameters(oLegGeneraDemanda);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);

            newID = getOutputParameter("PO_I_RESPUESTA").getParameterInt();

        } catch (SQLException e) {
            //System.out.println(e);
        }
        catch (Exception e) {
            //System.out.println(e);
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
        return newID;        
    }
    
    private List<ParameterOracle> insertHistoricoParameters(LegGeneraDemanda oLegGeneraDemanda) {
        List<ParameterOracle> oListParam = new ArrayList<>();        

        oListParam.add(new ParameterOracle("PI_D_INGRESO_DEMANDA", oLegGeneraDemanda.getFechaDemanda(), OracleTypes.CHAR, ParameterDirection.Input));
        
        String monto = "";
        if(oLegGeneraDemanda.getMontoADemandarLargo()!=null){
                int indiceParentesis = oLegGeneraDemanda.getMontoADemandarLargo().indexOf("(");
                monto = (indiceParentesis != -1) ? oLegGeneraDemanda.getMontoADemandarLargo().substring(0, indiceParentesis).trim() : oLegGeneraDemanda.getMontoADemandarLargo().trim();
                oLegGeneraDemanda.setMontoADemandarHis(monto);
        }
        oListParam.add(new ParameterOracle("PI_C_MONTO_A_DEMANDAR", monto, OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_FONDO", oLegGeneraDemanda.getFondoHis(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_TCHN", oLegGeneraDemanda.getTchn(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_CLIENTE", oLegGeneraDemanda.getClienteHis(), OracleTypes.CHAR, ParameterDirection.Input));        
        oListParam.add(new ParameterOracle("PI_C_INMUEBLE", oLegGeneraDemanda.getcInmueble(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO", oLegGeneraDemanda.getUsuario(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_WORD_NOMBRE", oLegGeneraDemanda.getNombreArchivoWord().replace(":", "_"), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_RESPUESTA", 0, OracleTypes.INTEGER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }
    
    @Override
    public Integer deleteHistoricoDemanda(LegGeneraDemanda oLegGeneraDemanda) {
        Integer newID = 0;
        OracleCallableStatement cmd = null;
        try {
            // name of procedure
            String sp = "{call EVA.PKG_LEGAL.SP_ELIMINAR_HISTORICO_DEMANDA(?,?,?,?,?)}";
            // list of parameters
            List<ParameterOracle> oLis = new ArrayList<>();
            // fill parameters
            oLis = deleteHistoricoParameters(oLegGeneraDemanda);
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSP(oLis, cn, cmd, sp);
            newID = getOutputParameter("PO_I_RESPUESTA").getParameterInt();
        } catch (SQLException e) {
            //System.out.println(e);
        }
        catch (Exception e) {
            //System.out.println(e);
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
        return newID;        
    }
    
    private List<ParameterOracle> deleteHistoricoParameters(LegGeneraDemanda oLegGeneraDemanda) {
        List<ParameterOracle> oListParam = new ArrayList<>();        
        oListParam.add(new ParameterOracle("PI_C_NOMBRE_ARCHIVO", oLegGeneraDemanda.getNombreArchivoWord(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PI_C_USUARIO", oLegGeneraDemanda.getUsuarioEliminador(), OracleTypes.CHAR, ParameterDirection.Input));
        oListParam.add(new ParameterOracle("PO_I_PROCESO", 0, OracleTypes.INTEGER, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }    

    @Override
    public List<LegGeneraDemanda> grillaHistoricoDemanda() {
        List<LegGeneraDemanda> lstHistoricoDemanda = new ArrayList<>();
        OracleCallableStatement cmd = null;
        OracleResultSet resultSet = null;
        try {
            String sp = "{call EVA.PKG_LEGAL.SP_CONSULTA_HISTORICO_DEMANDA(?,?,?)}";
            // list of parameter
            List<ParameterOracle> oList = new ArrayList<>();
            // fill parameters
            oList = selectParameters();
            //Abre conexion a la BD
            Conexion conex = new Conexion();
            cn = conex.ConexionOpen();
            // execute procedure
            runSearch(oList, cn, cmd, sp);
            resultSet = getOutputParameter("PO_CURSOR_RESULTADO").getParameterResultSet();

            while (resultSet.next()) {
                LegGeneraDemanda generaDemanda = new LegGeneraDemanda();                
                generaDemanda.setIdHistorico(resultSet.getString("ID_HISTORICO"));
                generaDemanda.setFechaDemandaHis(resultSet.getString("FECHA_DEMANDA"));
                generaDemanda.setMontoADemandarHis(resultSet.getString("MONTO_A_DEMANDAR"));
                generaDemanda.setFondoHis(resultSet.getString("FONDO"));
                generaDemanda.setTchnHis(resultSet.getString("TCHN"));
                generaDemanda.setClienteHis(resultSet.getString("CLIENTE"));
                generaDemanda.setcInmuebleHis(resultSet.getString("C_INMUEBLE"));
                generaDemanda.setUsuarioHis(resultSet.getString("CREADO_POR"));
                generaDemanda.setFechaCreacion(resultSet.getString("FECHA_CREACION"));
                generaDemanda.setFlagEliminado(resultSet.getString("FLAG_VISIBLE"));
                generaDemanda.setNombreArchivoWord(resultSet.getString("NOMBRE_ARCHIVO"));
                generaDemanda.setUsuarioEliminador(resultSet.getString("ELIMINADO_POR"));
                generaDemanda.setFechaEliminacion(resultSet.getString("FECHA_ELIMINADO"));
                lstHistoricoDemanda.add(generaDemanda);
            }            
        } catch (Exception e) {
            //System.out.println(e);
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
        return lstHistoricoDemanda;
    }

    private List<ParameterOracle> selectParameters() {
            List<ParameterOracle> oListParam = new ArrayList<>();
            oListParam.add(new ParameterOracle("PO_CURSOR_RESULTADO", null, OracleTypes.CURSOR, ParameterDirection.Output));
            oListParam.add(new ParameterOracle("PO_RESULTADO", "", OracleTypes.VARCHAR, ParameterDirection.Output));
            oListParam.add(new ParameterOracle("PO_ERR_DESC", "", OracleTypes.VARCHAR, ParameterDirection.Output));
        return oListParam;
    }


}