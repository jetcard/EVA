/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pop.comun.dominio.CobCronogramaMetaAgrupxFecha;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobCronogramaMetaCabecera;
import pop.comun.dominio.CobCronogramaMetaResumen;
import pop.comun.dominio.CobCronogramaMetaAvance;
import pop.comun.dominio.CobCronogramaRecaudoDetalle;
import pop.comun.dominio.CobCronogramaRecaudoResumen;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeAnio;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeMes;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ICobCronogramaMetaDetalleServ;
import pop.webcobranzas.iface.ICobCronogramaMetaCabeceraServ;
import pop.webcobranzas.iface.ICobCronogramaMetaResumenServ;
import pop.webcobranzas.iface.ICobCronogramaMetaAvanceServ;
import pop.webcobranzas.iface.ICronogramaServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.servicio.CobCronogramaMetaDetalleServ;
import pop.webcobranzas.servicio.CobCronogramaMetaCabeceraServ;
import pop.webcobranzas.servicio.CobCronogramaMetaResumenServ;
import pop.webcobranzas.servicio.CobCronogramaMetaAvanceServ;

import pop.webcobranzas.serviciorep.RepMetasCobranzasServ;

import pop.webcobranzas.servicio.CronogramaServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.InversionServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.servicio.UbigeoServ;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pop.comun.dominio.MaeAsesor;
import pop.comun.dominio.MaeDeposito;
import pop.webcobranzas.iface.IAsesorServ;
import pop.webcobranzas.ifacerep.IRepMetasCobranzasServ;
import pop.webcobranzas.servicio.AsesorServ;

import pop.comun.dominio.reporte.RepMetaCobranza;
import pop.comun.dominio.reporte.RepMetaRecaudo;
/**
 *
 * @author HH38092
 */
@Named
@ViewScoped
public class MetasCobranzaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Inject
    private SeguimientoBean seguimientoBean;
    
    private CobCronogramaMetaResumen cobCronogramaMetaResumen = new CobCronogramaMetaResumen();
    
    private CobCronogramaMetaDetalle cobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
    
    private CobCronogramaRecaudoDetalle cobCronogramaRecaudoDetalle = new CobCronogramaRecaudoDetalle();
    
    private CobCronogramaMetaAvance cobCronogramaMetaAvance = new CobCronogramaMetaAvance();
    
    private CobCronogramaRecaudoResumen cobCronogramaRecaudoResumen = new CobCronogramaRecaudoResumen();

    private DashboardBean dasboardBean=new DashboardBean();
    
    private MaeDeposito maeDeposito=new MaeDeposito();
    
    // ubigeo Busqueda
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    // imnueble (busq por provincia/distrito)
    private MaeInmueble maeInmueble = new MaeInmueble();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // Persona (busq por documento de la persona)
    private MaePersona maePersona = new MaePersona();
    // inversion busqueda
    private MaeInversion maeInversion = new MaeInversion();
    // estado inversion
    private CobTablas cobTablasEstInv = new CobTablas();
    // asesor para busqueda
    private MaeAsesor maeAsesor = new MaeAsesor();

    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de inversiones con deuda
    private List<MaeInversion> maeInversionList;
    // lista de estado de inversion
    private List<CobTablas> cobTablasEstInvList;
    // lista de telefonos grabar
    private List<MaeTelefono> maeTelefonos;
    //lista de anios 
    private List<MaeAnio> maeAnioList;
    //lista de anios 
    private List<MaeMes> maeMesList;    
    // lista Depositos
    private List<MaeAsesor> maeAsesorList;
    // lista Fondo
    private List<MaeFondo> maeFondoList;
    
    private List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList;
    
    private List<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleList;
    
    private List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente1List;
    
    private List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente2List;
    
    private List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente3List;
    
    private List<CobCronogramaMetaResumen> cobCronogramaMetaResumenFondosList;
    
    private List<CobCronogramaMetaResumen> cobCronogramaMetaResumenFondoxAsesorList;
    
    private List<CobCronogramaMetaAvance> cobCronogramaMetaAvanceList;
    
    private List<CobCronogramaRecaudoResumen> cobCronogramaRecaudoResumenList;

    
    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de inversion
    private InversionServ inversionServ = new InversionServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // servicio de cronograma
    private ICronogramaServ cronogramaServ = new CronogramaServ();
    // servicios de asesor
    private IAsesorServ asesorServ = new AsesorServ();
    
    private ICobCronogramaMetaResumenServ cobCronogramaMetaResumenServ  = new CobCronogramaMetaResumenServ();
    
    private ICobCronogramaMetaDetalleServ cobCronogramaMetaDetalleServ  = new CobCronogramaMetaDetalleServ();
    
    private ICobCronogramaMetaCabeceraServ cobCronogramaMetaCabeceraServ  = new CobCronogramaMetaCabeceraServ();
    
    private IRepMetasCobranzasServ repMetasCobranzasServ = new RepMetasCobranzasServ();
    
    private ICobCronogramaMetaAvanceServ cobCronogramaMetaAvanceServ  = new CobCronogramaMetaAvanceServ();
    
    private String anioxBusqueda;
    private String mesxBusqueda;
    
    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";
    
    private boolean showGoDeta = false;
    private boolean showResumen = false;
    private boolean showRefreshResumenyGruposFecha=false;
    private boolean showRefreshTables=false;
    private boolean showAsesorGroupFecha1=false;
    private boolean showAsesorGroupFecha2=false;
    private boolean showAsesorGroupFecha3=false;
    private boolean showGoTabAsesor1=false;

    // servicios de inversion
    private CobCronogramaMetaDetalleServ cobCronoMetaDetalleServ = new CobCronogramaMetaDetalleServ();

    public MetasCobranzaBean() {
        try {
            maeInversion.setMaeFondo(maeFondo);
            maeInversion.setcPersonaId(maePersona);
            maeInversion.setMaeInmueble(maeInmueble);
            
            maeTelefonos = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void inicializarVars()
    {
        showGoDeta=false;
        showResumen=false;
        showRefreshResumenyGruposFecha=false;
        showRefreshTables=false;
        showAsesorGroupFecha1=false;
        showAsesorGroupFecha2=false;
        showAsesorGroupFecha3=false;
        showGoTabAsesor1=false;
    }
    
    public void listarAnios() {
        try {
            System.out.println("listarAnios");
            inicializarVars();
            showMsg = false;
            if (maeAnioList == null) {
                maeAnioList = new ArrayList<>();
                
                MaeAnio maeAnio=new MaeAnio();
                maeAnio.setCAnioId("2019");
                maeAnio.setDAnio("2019");
                maeAnioList.add(maeAnio);
                
                MaeAnio maeAnio2=new MaeAnio();
                maeAnio2.setCAnioId("2020");
                maeAnio2.setDAnio("2020");
                maeAnioList.add(maeAnio2);
                
                MaeAnio maeAnio3=new MaeAnio();
                maeAnio3.setCAnioId("2021");
                maeAnio3.setDAnio("2021");
                maeAnioList.add(maeAnio3);
                
                MaeAnio maeAnio4=new MaeAnio();
                maeAnio4.setCAnioId("2022");
                maeAnio4.setDAnio("2022");
                maeAnioList.add(maeAnio4);
                
                MaeAnio maeAnio5=new MaeAnio();
                maeAnio5.setCAnioId("2023");
                maeAnio5.setDAnio("2023");
                maeAnioList.add(maeAnio5);
                
                MaeAnio maeAnio6=new MaeAnio();
                maeAnio6.setCAnioId("2024");
                maeAnio6.setDAnio("2024");
                maeAnioList.add(maeAnio6);
                
                MaeAnio maeAnio7=new MaeAnio();
                maeAnio7.setCAnioId("2025");
                maeAnio7.setDAnio("2025");
                maeAnioList.add(maeAnio7);                
                
                System.out.println("maeAnioList:"+maeAnioList.toString());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void listarMes() {
        try {
            System.out.println("listarMes");
            inicializarVars();
            showMsg = false;
            if (maeMesList == null) {
                System.out.println("maeMesList = new ArrayList<>()");
                maeMesList = new ArrayList<>();
                String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
                for(int i=0;i<12;i++)
                {
                    String nombreMes = MES[i];
                    MaeMes maeMes=new MaeMes();
                    System.out.println("i+1:"+String.valueOf(i+1));
                    maeMes.setCMesId(String.valueOf(i+1));
                    maeMes.setDMes(nombreMes);
                    maeMesList.add(maeMes);
                    System.out.println("maeMesList:"+maeMesList.toString());
                }
             }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }    
    
    public void listarAsesor() {
        try {
            MaeAsesor oMaeAsesor = new MaeAsesor();
            oMaeAsesor.setCtipo("U"); //Gestor Usuario
            
            System.out.println("SessionUtils.getUserId()" + SessionUtils.getUserId());
            System.out.println("SessionUtils.getName()()" + SessionUtils.getName());
            
            maeAsesorList = getAsesorServ().listarAsesor(oMaeAsesor);
            
            System.out.println(" Cantidad de Asesor Usuario-> " + maeAsesorList.size());
        } catch (Exception e) {

        }
    }
    
    public void listarAsesorUser() {
        try {
            MaeAsesor oMaeAsesor = new MaeAsesor();
            
            oMaeAsesor.setCusuarioId(SessionUtils.getUserName());            
            System.out.println("SessionUtils.getUserName()" + SessionUtils.getUserName());
            maeAsesorList = getAsesorServ().listarAsesorUser(oMaeAsesor);            
            System.out.println(" Cantidad de Asesor Usuario-> " + maeAsesorList.size());
            
        } catch (Exception e) {

        }
    }
    
    public void listarFondos() {
        try {
            showMsg = false;
            if (maeFondoList == null) {
                maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }
    
    public void listarFondosUser() {
        try {
            showMsg = false;
            if (maeFondoList == null) {
                MaeAsesor oMaeAsesor = new MaeAsesor();            
                oMaeAsesor.setCusuarioId(SessionUtils.getUserName()); 
                
                maeFondoList = getFondoServ().listarFondosUser(oMaeAsesor);
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }
    
    public void buscarMetaAgrupxFechaAgentes(CobCronogramaMetaResumen cobCronogramaMetaResumen)
    {
        try {
            System.out.println("buscarMetaAgrupxFechaAgentes ini");
            inicializarVars();
             //limpiando las listas hijas
            clearAllChildList();
            listMetaAgrupxFechaAgentes(cobCronogramaMetaResumen);
            System.out.println("buscarMetaAgrupxFechaAgentes fin");
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    public void buscarMetaAgrupxFechaAgentesJ(CobCronogramaMetaResumen cobCronogramaMetaResumen)
    {
        try {
            System.out.println("buscarMetaAgrupxFechaAgentes ini");
            inicializarVars();
             //limpiando las listas hijas
            clearAllChildList();
            listMetaAgrupxFechaAgentesJ(cobCronogramaMetaResumen);
            System.out.println("buscarMetaAgrupxFechaAgentes fin");
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    public void buscarResumenMetasTodos() {
        try {
            System.out.println("buscarResumenMetasTodos ini");
            inicializarVars();
            //limpiando las listas hijas
            clearAllChildList();
            listResumenGroupFondosyAsesorxFondo();
            System.out.println("buscarResumenMetasTodos fin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void aprobarMetas()
    {
        try {
            System.out.println("AprobarMetas ini");
            inicializarVars();
            //limpiando las listas hijas
            clearAllChildList();
            listResumenGroupFondosyAsesorxFondo();
            System.out.println("AprobarMetas fin");            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void buscarResumenMetasTodosJ() {
        try {
            System.out.println("buscarResumenMetasTodosJ ini");
            inicializarVars();
            //limpiando las listas hijas
            clearAllChildList();
            listResumenGroupFondosyAsesorxFondoJ();
            System.out.println("buscarResumenMetasTodosJ fin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void refrescarTablas()
    {
        System.out.println("refrescarTablas ini");
        inicializarVars();
        System.out.println("refrescarTablas: buscarResumenMetasTodos");
        try{cobCronogramaMetaAgrupxFechaAgente1List.clear();}catch(Exception e){;}
        try{cobCronogramaMetaAgrupxFechaAgente2List.clear();}catch(Exception e){;}
        listResumenGroupFondosyAsesorxFondo();
        System.out.println("refrescarTablas: cambiarAsesoresACodigos.size:"+cobCronogramaMetaDetalleList.size());
        //refrescar lista de codigos agrupados por fecha
        if(cobCronogramaMetaDetalleList.size()>0)
        {
            CobCronogramaMetaResumen ocobCronogramaMetaResumen = new CobCronogramaMetaResumen();
            System.out.println("refrescarTablas,obj:"+cobCronogramaMetaDetalleList.get(0).toString());
            ocobCronogramaMetaResumen.setC_fondo_id(cobCronogramaMetaDetalleList.get(0).getC_fondo_id());
            ocobCronogramaMetaResumen.setMoneda(cobCronogramaMetaDetalleList.get(0).getMoneda() );
            ocobCronogramaMetaResumen.setN_anio(cobCronogramaMetaDetalleList.get(0).getN_anio());
            ocobCronogramaMetaResumen.setN_mes(cobCronogramaMetaDetalleList.get(0).getN_mes());
            System.out.println("refrescarTablas,obj2:"+ocobCronogramaMetaResumen.toString());
            listMetaAgrupxFechaAgentes(ocobCronogramaMetaResumen);
        }
        showResumen=true;
        showRefreshTables=true;
        System.out.println("refrescarTablas fin");
    }

    public void buscarAsesoresAsignadosCodigosDetalle(CobCronogramaMetaAgrupxFecha oCobCronogramaMetaAgrupxFecha) {
        String stFec;
        
        stFec = oCobCronogramaMetaAgrupxFecha.getFecha();
        
        try {
            System.out.println("buscarAsesoresAsignadosCodigosDetalle");
            inicializarVars();
            CobCronogramaMetaDetalle oCobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
            oCobCronogramaMetaDetalle.setN_anio(oCobCronogramaMetaAgrupxFecha.getN_anio());
            oCobCronogramaMetaDetalle.setN_mes(oCobCronogramaMetaAgrupxFecha.getN_mes());
            oCobCronogramaMetaDetalle.setC_fondo_id(oCobCronogramaMetaAgrupxFecha.getC_fondo_id());
            oCobCronogramaMetaDetalle.setC_usuario_id(oCobCronogramaMetaAgrupxFecha.getC_usuario_id());
            if (stFec.trim().equals("VENCIDO"))
            {
                oCobCronogramaMetaDetalle.setF_pago(null);
            }
            else
            {
                System.out.println("fecha:"+oCobCronogramaMetaAgrupxFecha.getFecha());
                int anioFecPago=Integer.parseInt(oCobCronogramaMetaAgrupxFecha.getFecha().substring(6,10));
                System.out.println("anioFecPago:"+anioFecPago);
                int mesFecPago=Integer.parseInt(oCobCronogramaMetaAgrupxFecha.getFecha().substring(3,5));
                System.out.println("mesFecPago:"+mesFecPago);
                int diaFecPago=Integer.parseInt(oCobCronogramaMetaAgrupxFecha.getFecha().substring(0,2));
                System.out.println("diaFecPago:"+diaFecPago);
                Date dFechaPago = getDate(anioFecPago, mesFecPago-1, diaFecPago);
                System.out.println("dFechaPago:"+dFechaPago.toString());
                oCobCronogramaMetaDetalle.setF_pago(dFechaPago);
            }
            System.out.println("buscarAsesoresAsignadosCodigosDetalle:seteando cobCronogramaMetaDetalleList");
            cobCronogramaMetaDetalleList = cobCronogramaMetaDetalleServ.listarCronogramaMetaDetalle(oCobCronogramaMetaDetalle);
            System.out.println("buscarAsesoresAsignadosCodigosDetalle:cobCronogramaMetaDetalleList.count:"+cobCronogramaMetaDetalleList.size());
            //getSeguimientoBean().setShowMsg(false);
            showGoDeta=true;
            System.out.println("buscarAsesoresAsignadosCodigosDetalle:termino listar");

        } catch (Exception e) {
            System.out.println(e);
        }
    }    
    
    public void cambiarAsesoresACodigos() {
        try {
            Integer inAprobar = 0;
            
            CobCronogramaMetaCabecera oCobCronogramaMetaCabecera = new CobCronogramaMetaCabecera();
        
            oCobCronogramaMetaCabecera.setN_anio(cobCronogramaMetaResumen.getN_anio());
            oCobCronogramaMetaCabecera.setN_mes(cobCronogramaMetaResumen.getN_mes());
            inAprobar = cobCronogramaMetaCabeceraServ.validarAprobacion(oCobCronogramaMetaCabecera); 
            
            if (inAprobar == 0){            
                System.out.println("cambiarAsesoresACodigos ini");
                inicializarVars();
                System.out.println("cambiarAsesoresACodigos cobCronogramaMetaDetalleList.size():"+cobCronogramaMetaDetalleList.size());
                String sN_anio="";
                String sN_mes="";
                String sC_fondo_id="";
                String sC_Usuario_id="";
                Date dF_Fecha=null;
                //pinta en el log todos los valores de la lista de detalles
                //solamente enviar detalle codigos de aquellos que se han seleccionado
                ArrayList<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleListSelected= new ArrayList<>();
                int idxSelected=0;
                for(int i=0;i<cobCronogramaMetaDetalleList.size();i++)
                {
                    System.out.println("cambiarAsesoresACodigos:"+String.valueOf(i) + ":" + cobCronogramaMetaDetalleList.get(i).toString());  
                    //SE DEBE SETEAR EL VALOR DEL USUARIO QUE MODIFICA EN CADA ITEM DE LA LISTA
                    if(cobCronogramaMetaDetalleList.get(i).isSelected())
                    {
                        System.out.println("selected :"+String.valueOf(i));  
                        sN_anio=cobCronogramaMetaDetalleList.get(i).getN_anio();
                        sN_mes=cobCronogramaMetaDetalleList.get(i).getN_mes();
                        sC_fondo_id=cobCronogramaMetaDetalleList.get(i).getC_fondo_id();
                        sC_Usuario_id=cobCronogramaMetaDetalleList.get(i).getC_usuario_id();
                        dF_Fecha = cobCronogramaMetaDetalleList.get(i).getF_pago();
                        cobCronogramaMetaDetalleList.get(i).setC_usuario_mod(SessionUtils.getUserName());//setea el usuario que esta modificando
                        System.out.println("getOtroAsesor");  
                        String asesorNuevo=getOtroAsesor(sC_fondo_id,sC_Usuario_id);
                        cobCronogramaMetaDetalleList.get(i).setC_usuario_id(asesorNuevo);//hha
                        cobCronogramaMetaDetalleList.get(i).setJudicial("N");//seteando a NO JUDICIAL
                        cobCronogramaMetaDetalleListSelected.add(cobCronogramaMetaDetalleList.get(i));
                        System.out.println("num seleccionados:"+cobCronogramaMetaDetalleListSelected.size());
                        System.out.println("obj selected:"+ cobCronogramaMetaDetalleListSelected.get(idxSelected).toString());
                        idxSelected++;
                    }
                }

                //actualizar META RESUMEN Y META DETALLE
                cobCronogramaMetaDetalleServ.actualizarCambiarAsesor(cobCronogramaMetaDetalleListSelected);

                //actualizar el listado del detalle
                CobCronogramaMetaAgrupxFecha oCobCronogramaMetaAgrupxFecha=new CobCronogramaMetaAgrupxFecha();
                oCobCronogramaMetaAgrupxFecha.setN_anio(sN_anio);
                oCobCronogramaMetaAgrupxFecha.setN_mes(sN_mes);
                oCobCronogramaMetaAgrupxFecha.setC_fondo_id(sC_fondo_id);
                oCobCronogramaMetaAgrupxFecha.setC_usuario_id(sC_Usuario_id);
                if (dF_Fecha != null)
                {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    oCobCronogramaMetaAgrupxFecha.setFecha(dateFormat.format(dF_Fecha));
                }
                System.out.println("cambiarAsesoresACodigos: llamar buscarAsesoresAsignadosCodigosDetalle:" + oCobCronogramaMetaAgrupxFecha.toString());
                buscarAsesoresAsignadosCodigosDetalle(oCobCronogramaMetaAgrupxFecha);

                showGoDeta=true;
                showRefreshResumenyGruposFecha=true;
                System.out.println("cambiarAsesoresACodigos:Termino");
            }
            else{
            
                showMsg = true;
                tipoMsj = "error";
                mensaje = "Meta Aprobada, no se puede cambiar asesor ...";                       
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void clearAllChildList() 
    {
        //limpiando las listas hijas
        try{cobCronogramaMetaAgrupxFechaAgente1List.clear();}catch(Exception e){;}
        try{cobCronogramaMetaAgrupxFechaAgente2List.clear();}catch(Exception e){;}
        try{cobCronogramaMetaAgrupxFechaAgente3List.clear();}catch(Exception e){;}
        try{cobCronogramaMetaDetalleList.clear();}catch(Exception e){;}
    }
    
    public void aprobarMetasTodas()
    {
        try
        {            
            CobCronogramaMetaCabecera oCobCronogramaMetaCabecera = new CobCronogramaMetaCabecera();
        
            oCobCronogramaMetaCabecera.setN_anio(cobCronogramaMetaResumen.getN_anio());
            oCobCronogramaMetaCabecera.setN_mes(cobCronogramaMetaResumen.getN_mes());
            oCobCronogramaMetaCabecera.setC_usuario_aprobado(SessionUtils.getUserName());                   
            
            System.out.println("aprobarMetasTodas: llamar actualizar:" + oCobCronogramaMetaCabecera.toString());
            cobCronogramaMetaCabeceraServ.actualizar(oCobCronogramaMetaCabecera);
            System.out.println("aprobarMetasTodas: terminar actualizar:" + oCobCronogramaMetaCabecera.toString());
            
            showMsg = true;
            tipoMsj = "success";
            mensaje = "La meta ha sido Aprobada ...";
            
            System.out.println("showMsg["+showMsg+ "]");
            System.out.println("tipoMsj["+tipoMsj+ "]");
            System.out.println("mensaje["+mensaje+ "]");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 
    
    public void exportarReporte()
    {
        try
        {    
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            
            CobCronogramaMetaDetalle oCobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
            oCobCronogramaMetaDetalle.setN_anio(cobCronogramaMetaResumen.getN_anio());
            oCobCronogramaMetaDetalle.setN_mes(cobCronogramaMetaResumen.getN_mes());
            //listDepositos = getDepositoServ().listarDepositos(deposito);
            System.out.println("exportarReporte: llamar Lista Meta Detalle " + oCobCronogramaMetaDetalle.toString());
            //cobCronogramaMetaDetalleList  = getCobCronogramaMetaDetalleServ().listarRepCronogramaMetaDetalle(oCobCronogramaMetaDetalle);
            
            //RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(maeInversionRep);            
            cobCronogramaMetaDetalleList  = getCobCronogramaMetaDetalleServ().listarRepCronogramaMetaDetalle(oCobCronogramaMetaDetalle);     
            
            System.out.println("exportarReporte: terminar Lista Meta Detalle " + oCobCronogramaMetaDetalle.toString());
            
            //asss = repMetasCobranzasServ.exportartRepMetasCobranzas(cobCronogramaMetaDetalleList);
            
            /*CobCronogramaMetaCabecera oCobCronogramaMetaCabecera = new CobCronogramaMetaCabecera();
            //CobCronogramaMetaDetalle oCobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
        
            oCobCronogramaMetaCabecera.setN_anio(cobCronogramaMetaResumen.getN_anio());
            oCobCronogramaMetaCabecera.setN_mes(cobCronogramaMetaResumen.getN_mes());
            oCobCronogramaMetaCabecera.setC_usuario_aprobado(SessionUtils.getUserName());       

            
            //oCobCronogramaMetaDetalle
                    
            
            
            System.out.println("aprobarMetasTodas: llamar actualizar:" + oCobCronogramaMetaCabecera.toString());
            cobCronogramaMetaCabeceraServ.actualizar(oCobCronogramaMetaCabecera);
            System.out.println("aprobarMetasTodas: terminar actualizar:" + oCobCronogramaMetaCabecera.toString());
            
            showMsg = true;
            tipoMsj = "success";
            mensaje = "La meta ha sido Aprobada ...";
            
            System.out.println("showMsg["+showMsg+ "]");
            System.out.println("tipoMsj["+tipoMsj+ "]");
            System.out.println("mensaje["+mensaje+ "]");*/
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 
    
    public void generarReporteMetas()
    {
        try
        {    
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            
            //CobCronogramaMetaDetalle oCobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
            cobCronogramaMetaDetalle.setN_anio(cobCronogramaMetaResumen.getN_anio());
            cobCronogramaMetaDetalle.setN_mes(cobCronogramaMetaResumen.getN_mes());
            //listDepositos = getDepositoServ().listarDepositos(deposito);
            RepMetaCobranza oRepMetaCobranza = cobCronoMetaDetalleServ.reporteMetaCobranza(cobCronogramaMetaDetalle);
            System.out.println("   Lista Meta Detalle = " + cobCronogramaMetaDetalleList.size());
            
            asss = repMetasCobranzasServ.exportartRepMetasCobranzas(oRepMetaCobranza);
            baos.write(asss);
       
            
            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/vnd.ms-excel");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "attachment;filename=SaldoDeudor" + maeInversionRep.getCInversion().trim() + ".xlsx");
                hsr.setHeader("Content-disposition", "attachment;filename=reporte_meta.xls");
                hsr.setContentLength(baos.size());
                try {
                    ServletOutputStream out = hsr.getOutputStream();
                    baos.writeTo(out);

                    out.flush();
                } catch (IOException ex) {
                    System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 
    
    public void generarReporteRecaudo()
    {
        try
        {    
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            
            //CobCronogramaMetaDetalle oCobCronogramaMetaDetalle = new CobCronogramaMetaDetalle();
            
            cobCronogramaRecaudoDetalle.setN_anio(cobCronogramaMetaResumen.getN_anio());
            cobCronogramaRecaudoDetalle.setN_mes(cobCronogramaMetaResumen.getN_mes());
            cobCronogramaRecaudoDetalle.setF_fecha(cobCronogramaMetaResumen.getF_fecha_id());
            cobCronogramaRecaudoDetalle.setC_usuario_id(SessionUtils.getUserName());
            //listDepositos = getDepositoServ().listarDepositos(deposito);
            RepMetaRecaudo oRepMetaRecaudo = cobCronoMetaDetalleServ.reporteMetaRecaudo(cobCronogramaRecaudoDetalle);
            System.out.println("   Lista Meta Detalle = " );
                        
            asss = repMetasCobranzasServ.exportarRepMetasRecaudo(oRepMetaRecaudo);
            baos.write(asss);
            
            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/vnd.ms-excel");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "attachment;filename=SaldoDeudor" + maeInversionRep.getCInversion().trim() + ".xlsx");
                hsr.setHeader("Content-disposition", "attachment;filename=avance_recaudacion.xls");
                hsr.setContentLength(baos.size());
                try {
                    ServletOutputStream out = hsr.getOutputStream();
                    baos.writeTo(out);

                    out.flush();
                } catch (IOException ex) {
                    System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
           
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 
    
    public void exportarRecaudo()
    {
        try
        {    
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            
            CobCronogramaMetaAvance oCobCronogramaMetaAvance = new CobCronogramaMetaAvance();
            
            oCobCronogramaMetaAvance.setN_anio(cobCronogramaMetaResumen.getN_anio());
            oCobCronogramaMetaAvance.setN_mes(cobCronogramaMetaResumen.getN_mes());
            oCobCronogramaMetaAvance.setF_fecha(cobCronogramaMetaResumen.getF_fecha_id());
            oCobCronogramaMetaAvance.setC_usuario_id(cobCronogramaMetaResumen.getC_usuario_id());
            oCobCronogramaMetaAvance.setC_fondo_id(cobCronogramaMetaResumen.getC_fondo_id());            
            cobCronogramaMetaAvanceList  = getCobCronogramaMetaAvanceServ().listarRepCronogramaMetaAvance(oCobCronogramaMetaAvance);
            //System.out.println("   Lista Meta Avance = " + cobCronogramaMetaDetalleList.size());
            
            //DashboardBean oDashboardBean = new DashboardBean();            
            //oDashboardBean.cargarGraficoMeta(oCobCronogramaMetaAvance);
            
            dasboardBean.cargarGraficoMeta(oCobCronogramaMetaAvance);
            //System.out.println("   Grafico Avance = ");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 
    
    public void exportarReporteRecaudo(){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            
            CobCronogramaRecaudoResumen oCobCronogramaRecaudoResumen = new CobCronogramaRecaudoResumen();
            oCobCronogramaRecaudoResumen.setN_anio(cobCronogramaMetaResumen.getN_anio());
            oCobCronogramaRecaudoResumen.setN_mes(cobCronogramaMetaResumen.getN_mes());
            oCobCronogramaRecaudoResumen.setF_fecha(cobCronogramaMetaResumen.getF_fecha_id());
                              
            cobCronogramaRecaudoResumenList = getCobCronogramaMetaDetalleServ().listarRepCronogramaRecaudoResumen(oCobCronogramaRecaudoResumen);
            
            System.out.println("exportarReporte: llamar Lista Meta Detalle " + oCobCronogramaRecaudoResumen.toString());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void listResumenGroupFondosyAsesorxFondo() {
        try {
            System.out.println("listResumenGroupFondosyAsesorxFondo ini");
            //lista la sumatoria por fondos
            cobCronogramaMetaResumen.setJudicial("N");//N: NO JUDICIAL
            //RESULTADO 1
            cobCronogramaMetaResumenFondosList = cobCronogramaMetaResumenServ.listarCronogramaMetaFondo(cobCronogramaMetaResumen);
            System.out.println("listResumenGroupFondosyAsesorxFondo:numero fondos1["+cobCronogramaMetaResumenFondosList.size()+"]");
            //añadir el total al resumen
            if(cobCronogramaMetaResumenFondosList.size()>0)
            {
                double totTotal = 0;
                Integer cantTCHN =0;
                for(int j=0;j<cobCronogramaMetaResumenFondosList.size();j++)
                {
                    totTotal = totTotal + cobCronogramaMetaResumenFondosList.get(j).getI_total();
                    cantTCHN = cantTCHN + Integer.parseInt(cobCronogramaMetaResumenFondosList.get(j).getN_cant_tchn());
                }
                CobCronogramaMetaResumen oCobCronogramaMetaResumen = new CobCronogramaMetaResumen();
                oCobCronogramaMetaResumen.setN_anio(cobCronogramaMetaResumen.getN_anio());
                oCobCronogramaMetaResumen.setN_mes(cobCronogramaMetaResumen.getN_mes());
                oCobCronogramaMetaResumen.setC_fondo_desc("TOTAL");
                oCobCronogramaMetaResumen.setI_total(totTotal);
                oCobCronogramaMetaResumen.setN_cant_tchn(cantTCHN.toString());
                cobCronogramaMetaResumenFondosList.add(oCobCronogramaMetaResumen);
            }
            //SE DEBE MOSTRAR EL CONSOLIDADO DE ASESORES POR FONDO
            if(cobCronogramaMetaResumenFondosList.size()>0)
            {
                System.out.println("listResumenGroupFondosyAsesorxFondo, listando asesores");
                cobCronogramaMetaResumen.setJudicial("N");//N:NO JUDICIAL
                //RESULTADO 2
                cobCronogramaMetaResumenFondoxAsesorList = cobCronogramaMetaResumenServ.listarCronogramaMetaFondoxAsesor(cobCronogramaMetaResumen);
                if(cobCronogramaMetaResumenFondoxAsesorList.size()>0)
                {
                    showResumen=true;
                }
            }
            System.out.println("listResumenGroupFondosyAsesorxFondo fin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
    
    
    public void listResumenGroupFondosyAsesorxFondoJ() {
        try {
            System.out.println("listResumenGroupFondosyAsesorxFondoJ ini");
            //lista la sumatoria por fondos
            cobCronogramaMetaResumen.setJudicial("S");//S: JUDICIAL
            //RESULTADO 1
            cobCronogramaMetaResumenFondosList = cobCronogramaMetaResumenServ.listarCronogramaMetaFondo(cobCronogramaMetaResumen);
            System.out.println("listResumenGroupFondosyAsesorxFondoJ:numero fondos1["+cobCronogramaMetaResumenFondosList.size()+"]");
            //añadir el total al resumen
            if(cobCronogramaMetaResumenFondosList.size()>0)
            {
                double totTotal=0;
                for(int j=0;j<cobCronogramaMetaResumenFondosList.size();j++)
                {
                    totTotal = totTotal + cobCronogramaMetaResumenFondosList.get(j).getI_total();
                }
                CobCronogramaMetaResumen oCobCronogramaMetaResumen = new CobCronogramaMetaResumen();
                oCobCronogramaMetaResumen.setN_anio(cobCronogramaMetaResumen.getN_anio());
                oCobCronogramaMetaResumen.setN_mes(cobCronogramaMetaResumen.getN_mes());
                oCobCronogramaMetaResumen.setC_fondo_desc("TOTAL");
                oCobCronogramaMetaResumen.setI_total(totTotal);
                cobCronogramaMetaResumenFondosList.add(oCobCronogramaMetaResumen);
            }
            
            //SE DEBE MOSTRAR EL CONSOLIDADO DE ASESORES POR FONDO
            if(cobCronogramaMetaResumenFondosList.size()>0)
            {
                System.out.println("listResumenGroupFondosyAsesorxFondo, listando asesores");
                cobCronogramaMetaResumen.setJudicial("S");//N:NO JUDICIAL
                //RESULTADO 2
                cobCronogramaMetaResumenFondoxAsesorList = cobCronogramaMetaResumenServ.listarCronogramaMetaFondoxAsesorJ(cobCronogramaMetaResumen);
                if(cobCronogramaMetaResumenFondoxAsesorList.size()>0)
                {
                    showResumen=true;
                }
            }
            
            System.out.println("listResumenGroupFondosyAsesorxFondoJ fin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
    
    public void listMetaAgrupxFechaAgentes(CobCronogramaMetaResumen cobCronogramaMetaResumen)
    {
        try {
            System.out.println("listMetaAgrupxFechaAgentes ini");
            //LISTAR LOS ASESORES
            cobCronogramaMetaResumen.setJudicial("N");//N: NO JUDICIAL
            List<CobCronogramaMetaResumen> oCobCronogramaMetaResumenList = cobCronogramaMetaResumenServ.listarCronogramaMetaResumen(cobCronogramaMetaResumen);
            System.out.println("listMetaAgrupxFechaAgentes.oCobCronogramaMetaResumenList.size():"+oCobCronogramaMetaResumenList.size());
            for(int i=0;i<oCobCronogramaMetaResumenList.size();i++)
            {
                //Por cada asesor se hace lo siguiente
                System.out.println("asesor.[" + String.valueOf(i)+ "]:" + oCobCronogramaMetaResumenList.get(i).getC_usuario_id());
                cobCronogramaMetaResumen.setC_usuario_id(oCobCronogramaMetaResumenList.get(i).getC_usuario_id());
                if(i==0)
                {
                    //lista de fechas agrupadas codigos y totales para un fondo 
                    cobCronogramaMetaAgrupxFechaAgente1List = cobCronogramaMetaDetalleServ.listarCronogramaMetaAgrupxFecha(cobCronogramaMetaResumen);
                    System.out.println("listMetaAgrupxFechaAgentes.1. numero de datos.["+cobCronogramaMetaAgrupxFechaAgente1List.size()+"]");
                }
                else if(i==1)
                {
                    //lista de fechas agrupadas codigos y totales para un fondo 
                    cobCronogramaMetaAgrupxFechaAgente2List = cobCronogramaMetaDetalleServ.listarCronogramaMetaAgrupxFecha(cobCronogramaMetaResumen);
                    System.out.println("listMetaAgrupxFechaAgentes.2. numero de datos.["+cobCronogramaMetaAgrupxFechaAgente2List.size()+"]");
                }
                else if(i==2)
                {
                    //lista de fechas agrupadas codigos y totales para un fondo 
                    cobCronogramaMetaAgrupxFechaAgente3List = cobCronogramaMetaDetalleServ.listarCronogramaMetaAgrupxFecha(cobCronogramaMetaResumen);
                    System.out.println("listMetaAgrupxFechaAgentes.3. numero de datos.["+cobCronogramaMetaAgrupxFechaAgente3List.size()+"]");
                }
            }
            showAsesorGroupFecha1=true;
            showAsesorGroupFecha2=true;
            showAsesorGroupFecha3=true;
            showGoTabAsesor1=true;
            System.out.println("listMetaAgrupxFechaAgentes fin");
        } catch (Exception e) {
            System.out.println(e);
        }        
    }    
        
    public void listMetaAgrupxFechaAgentesJ(CobCronogramaMetaResumen cobCronogramaMetaResumen)
    {
        try {
            System.out.println("listMetaAgrupxFechaAgentes ini");
            //LISTAR LOS ASESORES
            cobCronogramaMetaResumen.setJudicial("S");//N: NO JUDICIAL
            List<CobCronogramaMetaResumen> oCobCronogramaMetaResumenList = cobCronogramaMetaResumenServ.listarCronogramaMetaResumen(cobCronogramaMetaResumen);
            System.out.println("listMetaAgrupxFechaAgentes.oCobCronogramaMetaResumenList.size():"+oCobCronogramaMetaResumenList.size());
            for(int i=0;i<oCobCronogramaMetaResumenList.size();i++)
            {
                //Por cada asesor se hace lo siguiente
                System.out.println("asesor.[" + String.valueOf(i)+ "]:" + oCobCronogramaMetaResumenList.get(i).getC_usuario_id());
                cobCronogramaMetaResumen.setC_usuario_id(oCobCronogramaMetaResumenList.get(i).getC_usuario_id());
                if(i==0)
                {
                    //lista de fechas agrupadas codigos y totales para un fondo 
                    cobCronogramaMetaAgrupxFechaAgente1List = cobCronogramaMetaDetalleServ.listarCronogramaMetaAgrupxFecha(cobCronogramaMetaResumen);
                    System.out.println("listMetaAgrupxFechaAgentes.1. numero de datos.["+cobCronogramaMetaAgrupxFechaAgente1List.size()+"]");
               }
                else if(i==1)
                {
                    //lista de fechas agrupadas codigos y totales para un fondo 
                    cobCronogramaMetaAgrupxFechaAgente2List = cobCronogramaMetaDetalleServ.listarCronogramaMetaAgrupxFecha(cobCronogramaMetaResumen);
                    System.out.println("listMetaAgrupxFechaAgentes.2. numero de datos.["+cobCronogramaMetaAgrupxFechaAgente2List.size()+"]");
                }
            }
            showAsesorGroupFecha1=true;
            showAsesorGroupFecha2=true;
            showGoTabAsesor1=true;
            System.out.println("listMetaAgrupxFechaAgentes fin");
        } catch (Exception e) {
            System.out.println(e);
        }        
    }    
    
    public MaeUbigeo getMaeUbigeoP() {
        return maeUbigeoP;
    }

    public void setMaeUbigeoP(MaeUbigeo maeUbigeoP) {
        this.maeUbigeoP = maeUbigeoP;
    }

    public List<MaeUbigeo> getUbigeoProv() {
        return ubigeoProv;
    }

    public void setUbigeoProv(List<MaeUbigeo> ubigeoProv) {
        this.ubigeoProv = ubigeoProv;
    }

    public List<MaeUbigeo> getUbigeoDist() {
        return ubigeoDist;
    }

    public void setUbigeoDist(List<MaeUbigeo> ubigeoDist) {
        this.ubigeoDist = ubigeoDist;
    }

    public IUbigeoServ getUbigeoServ() {
        return ubigeoServ;
    }

    public void setUbigeoServ(IUbigeoServ ubigeoServ) {
        this.ubigeoServ = ubigeoServ;
    }

    public DashboardBean getDashboardBean(){
        return dasboardBean;
    }
            
    public void setDashboardBean(DashboardBean dashboardBean){
        this.dasboardBean = dashboardBean;
    }
    
    public MaeUbigeo getMaeUbigeoD() {
        return maeUbigeoD;
    }

    public void setMaeUbigeoD(MaeUbigeo maeUbigeoD) {
        this.maeUbigeoD = maeUbigeoD;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public InversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(InversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public SeguimientoBean getSeguimientoBean() {
        return seguimientoBean;
    }

    public void setSeguimientoBean(SeguimientoBean seguimientoBean) {
        this.seguimientoBean = seguimientoBean;
    }

    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMsj() {
        return tipoMsj;
    }

    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }

    public List<MaeTelefono> getMaeTelefonos() {
        return maeTelefonos;
    }

    public void setMaeTelefonos(List<MaeTelefono> maeTelefonos) {
        this.maeTelefonos = maeTelefonos;
    }

    public CobTablas getCobTablasEstInv() {
        return cobTablasEstInv;
    }

    public void setCobTablasEstInv(CobTablas cobTablasEstInv) {
        this.cobTablasEstInv = cobTablasEstInv;
    }

    public List<CobTablas> getCobTablasEstInvList() {
        return cobTablasEstInvList;
    }

    public void setCobTablasEstInvList(List<CobTablas> cobTablasEstInvList) {
        this.cobTablasEstInvList = cobTablasEstInvList;
    }

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public ICronogramaServ getCronogramaServ() {
        return cronogramaServ;
    }

    public void setCronogramaServ(ICronogramaServ cronogramaServ) {
        this.cronogramaServ = cronogramaServ;
    }
    
   /**
     * @return the anioxBusqueda
     */
    public String getAnioxBusqueda() {
        return anioxBusqueda;
    }

    /**
     * @param anioxBusqueda the anioxBusqueda to set
     */
    public void setAnioxBusqueda(String anioxBusqueda) {
        this.anioxBusqueda = anioxBusqueda;
    }

    /**
     * @return the mesxBusqueda
     */
    public String getMesxBusqueda() {
        return mesxBusqueda;
    }

    /**
     * @param mesxBusqueda the mesxBusqueda to set
     */
    public void setMesxBusqueda(String mesxBusqueda) {
        this.mesxBusqueda = mesxBusqueda;
    }
    
    /**
     * @return the maeAnioList
     */
    public List<MaeAnio> getMaeAnioList() {
        return maeAnioList;
    }

    /**
     * @param maeAnioList the maeAnioList to set
     */
    public void setMaeAnioList(List<MaeAnio> maeAnioList) {
        this.maeAnioList = maeAnioList;
    }
    
    /**
     * @return the maeMesList
     */
    public List<MaeMes> getMaeMesList() {
        return maeMesList;
    }

    /**
     * @param maeMesList the maeMesList to set
     */
    public void setMaeMesList(List<MaeMes> maeMesList) {
        this.maeMesList = maeMesList;
    }
    
    
    public MaeAsesor getMaeAsesor() {
        return maeAsesor;
    }
    
    public void setMaeAsesor(MaeAsesor maeAsesor) {
        this.maeAsesor = maeAsesor;
    }

    public List<MaeAsesor> getMaeAsesorList() {
        return maeAsesorList;
    }

    public void setMaeAsesorList(List<MaeAsesor> maeAsesorList) {
        this.maeAsesorList = maeAsesorList;
    }
    
    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }
    
     public IAsesorServ getAsesorServ() {
        return asesorServ;
    }

    public void setAsesorServ(IAsesorServ asesorServ) {
        this.asesorServ = asesorServ;
    }

    
    
    /**
     * @return the cobCronogramaMetaResumen
     */
    public CobCronogramaMetaResumen getCobCronogramaMetaResumen() {
        return cobCronogramaMetaResumen;
    }

    /**
     * @param cobCronogramaMetaResumen the cobCronogramaMetaResumen to set
     */
    public void setCobCronogramaMetaResumen(CobCronogramaMetaResumen cobCronogramaMetaResumen) {
        this.cobCronogramaMetaResumen = cobCronogramaMetaResumen;
    }    

    /**
     * @return the cobCronogramaMetaDetalle
     */
    public CobCronogramaMetaDetalle getCobCronogramaMetaDetalle() {
        return cobCronogramaMetaDetalle;
    }

    /**
     * @param cobCronogramaMetaDetalle the cobCronogramaMetaDetalle to set
     */
    public void setCobCronogramaMetaDetalle(CobCronogramaMetaDetalle cobCronogramaMetaDetalle) {
        this.cobCronogramaMetaDetalle = cobCronogramaMetaDetalle;
    }    
    
    /**
     * @return the cobCronogramaMetaResumenList
     */
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaResumenList() {
        return cobCronogramaMetaResumenList;
    }

    /**
     * @param cobCronogramaMetaResumenList the cobCronogramaMetaResumenList to set
     */
    public void setCobCronogramaMetaResumenList(List<CobCronogramaMetaResumen> cobCronogramaMetaResumenList) {
        this.cobCronogramaMetaResumenList = cobCronogramaMetaResumenList;
    }
    
    /**
     * @return the cobCronogramaMetaDetalleList
     */
    public List<CobCronogramaMetaDetalle> getCobCronogramaMetaDetalleList() {
        return cobCronogramaMetaDetalleList;
    }

    /**
     * @param cobCronogramaMetaDetalleList the cobCronogramaMetaDetalleList to set
     */
    public void setCobCronogramaMetaDetalleList(List<CobCronogramaMetaDetalle> cobCronogramaMetaDetalleList) {
        this.cobCronogramaMetaDetalleList = cobCronogramaMetaDetalleList;
    }
    
    
    public List<CobCronogramaRecaudoResumen> getCobCronogramaRecaudoResumenList() {
        return cobCronogramaRecaudoResumenList;
    }

    public void setCobCronogramaRecaudoResumenList(List<CobCronogramaRecaudoResumen> cobCronogramaRecaudoResumenList) {
        this.cobCronogramaRecaudoResumenList = cobCronogramaRecaudoResumenList;
    }
    
   /**
     * @return the cobCronogramaMetaAgrupxFechaAgente1List
     */
    public List<CobCronogramaMetaAgrupxFecha> getCobCronogramaMetaAgrupxFechaAgente1List() {
        return cobCronogramaMetaAgrupxFechaAgente1List;
    }

    /**
     * @param cobCronogramaMetaAgrupxFechaAgente1List the cobCronogramaMetaAgrupxFechaAgente1List to set
     */
    public void setCobCronogramaMetaAgrupxFechaAgente1List(List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente1List) {
        this.cobCronogramaMetaAgrupxFechaAgente1List = cobCronogramaMetaAgrupxFechaAgente1List;
    }

    /**
     * @return the cobCronogramaMetaAgrupxFechaAgente2List
     */
    public List<CobCronogramaMetaAgrupxFecha> getCobCronogramaMetaAgrupxFechaAgente2List() {
        return cobCronogramaMetaAgrupxFechaAgente2List;
    }

    /**
     * @param cobCronogramaMetaAgrupxFechaAgente2List the cobCronogramaMetaAgrupxFechaAgente2List to set
     */
    public void setCobCronogramaMetaAgrupxFechaAgente2List(List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente2List) {
        this.cobCronogramaMetaAgrupxFechaAgente2List = cobCronogramaMetaAgrupxFechaAgente2List;
    }
    
    /**
     * @return the cobCronogramaMetaAgrupxFechaAgente2List
     */
    public List<CobCronogramaMetaAgrupxFecha> getCobCronogramaMetaAgrupxFechaAgente3List() {
        return cobCronogramaMetaAgrupxFechaAgente3List;
    }

    /**
     * @param cobCronogramaMetaAgrupxFechaAgente3List the cobCronogramaMetaAgrupxFechaAgente3List to set
     */
    public void setCobCronogramaMetaAgrupxFechaAgente3List(List<CobCronogramaMetaAgrupxFecha> cobCronogramaMetaAgrupxFechaAgente3List) {
        this.cobCronogramaMetaAgrupxFechaAgente3List = cobCronogramaMetaAgrupxFechaAgente3List;
    }
    
   /**
     * @return the showGoDeta
     */
    public boolean isShowGoDeta() {
        return showGoDeta;
    }

    /**
     * @param showGoDeta the showGoDeta to set
     */
    public void setShowGoDeta(boolean showGoDeta) {
        this.showGoDeta = showGoDeta;
    }
    
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public String getOtroAsesor(String fondo, String asesorActual)
    {
        for(int i=0;i<cobCronogramaMetaResumenFondoxAsesorList.size();i++)
        {
            if (fondo.trim().equals(cobCronogramaMetaResumenFondoxAsesorList.get(i).getC_fondo_id().trim()))
            {
                if (!asesorActual.trim().equals(cobCronogramaMetaResumenFondoxAsesorList.get(i).getC_usuario_id().trim()))
                {
                    System.out.println("getOtroAsesor:" + cobCronogramaMetaResumenFondoxAsesorList.get(i).getC_usuario_id().trim());
                    return cobCronogramaMetaResumenFondoxAsesorList.get(i).getC_usuario_id().trim();
                }
            }
        }
        return "";
    }

    /**
     * @return the showRefreshResumenyGruposFecha
     */
    public boolean isShowRefreshResumenyGruposFecha() {
        return showRefreshResumenyGruposFecha;
    }

    /**
     * @param showRefreshResumenyGruposFecha the showRefreshResumenyGruposFecha to set
     */
    public void setShowRefreshResumenyGruposFecha(boolean showRefreshResumenyGruposFecha) {
        this.showRefreshResumenyGruposFecha = showRefreshResumenyGruposFecha;
    }

    /**
     * @return the showRefreshTables
     */
    public boolean isShowRefreshTables() {
        return showRefreshTables;
    }

    /**
     * @param showRefreshTables the showRefreshTables to set
     */
    public void setShowRefreshTables(boolean showRefreshTables) {
        this.showRefreshTables = showRefreshTables;
    }
    
    /**
     * @return the cobCronogramaMetaResumenFondosList
     */
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaResumenFondosList() {
        return cobCronogramaMetaResumenFondosList;
    }

    /**
     * @param cobCronogramaMetaResumenFondosList the cobCronogramaMetaResumenFondosList to set
     */
    public void setCobCronogramaMetaResumenFondosList(List<CobCronogramaMetaResumen> cobCronogramaMetaResumenFondosList) {
        this.cobCronogramaMetaResumenFondosList = cobCronogramaMetaResumenFondosList;
    }

    /**
     * @return the cobCronogramaMetaResumenFondoxAsesorList
     */
    public List<CobCronogramaMetaResumen> getCobCronogramaMetaResumenFondoxAsesorList() {
        return cobCronogramaMetaResumenFondoxAsesorList;
    }

    /**
     * @param cobCronogramaMetaResumenFondoxAsesorList the cobCronogramaMetaResumenFondoxAsesorList to set
     */
    public void setCobCronogramaMetaResumenFondoxAsesorList(List<CobCronogramaMetaResumen> cobCronogramaMetaResumenFondoxAsesorList) {
        this.cobCronogramaMetaResumenFondoxAsesorList = cobCronogramaMetaResumenFondoxAsesorList;
    }    
    
     /**
     * @return the cobCronogramaMetaAvanceList
     */
    public List<CobCronogramaMetaAvance> getCobCronogramaMetaAvanceList() {
        return cobCronogramaMetaAvanceList;
    }

    /**
     * @param cobCronogramaMetaAvanceList the cobCronogramaMetaAvanceList to set
     */
    public void setCobCronogramaMetaAvanceList(List<CobCronogramaMetaAvance> cobCronogramaMetaAvanceList) {
        this.cobCronogramaMetaAvanceList = cobCronogramaMetaAvanceList;
    }  
    
    

    /**
     * @return the showResumen
     */
    public boolean isShowResumen() {
        return showResumen;
    }

    /**
     * @param showResumen the showResumen to set
     */
    public void setShowResumen(boolean showResumen) {
        this.showResumen = showResumen;
    }
    

    /**
     * @return the showAsesorGroupFecha1
     */
    public boolean isShowAsesorGroupFecha1() {
        return showAsesorGroupFecha1;
    }

    /**
     * @param showAsesorGroupFecha1 the showAsesorGroupFecha1 to set
     */
    public void setShowAsesorGroupFecha1(boolean showAsesorGroupFecha1) {
        this.showAsesorGroupFecha1 = showAsesorGroupFecha1;
    }

    /**
     * @return the showAsesorGroupFecha2
     */
    public boolean isShowAsesorGroupFecha2() {
        return showAsesorGroupFecha2;
    }

    /**
     * @param showAsesorGroupFecha2 the showAsesorGroupFecha2 to set
     */
    public void setShowAsesorGroupFecha2(boolean showAsesorGroupFecha2) {
        this.showAsesorGroupFecha2 = showAsesorGroupFecha2;
    }    

    /**
     * @return the showAsesorGroupFecha3
     */
    public boolean isShowAsesorGroupFecha3() {
        return showAsesorGroupFecha3;
    }

    /**
     * @param showAsesorGroupFecha3 the showAsesorGroupFecha3 to set
     */
    public void setShowAsesorGroupFecha3(boolean showAsesorGroupFecha3) {
        this.showAsesorGroupFecha3 = showAsesorGroupFecha3;
    }
    
    /**
     * @return the showGoTabAsesor1
     */
    public boolean isShowGoTabAsesor1() {
        return showGoTabAsesor1;
    }

    /**
     * @param showGoTabAsesor1 the showGoTabAsesor1 to set
     */
    public void setShowGoTabAsesor1(boolean showGoTabAsesor1) {
        this.showGoTabAsesor1 = showGoTabAsesor1;
    }

    public ICobCronogramaMetaDetalleServ getCobCronogramaMetaDetalleServ() {
        return cobCronogramaMetaDetalleServ;
    }   
    
    public ICobCronogramaMetaAvanceServ getCobCronogramaMetaAvanceServ() {
        return cobCronogramaMetaAvanceServ;
    } 
}
