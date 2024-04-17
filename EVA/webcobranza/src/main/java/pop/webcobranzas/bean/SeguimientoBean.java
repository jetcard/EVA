/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.CobLlamadas;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobRequerimientoCartas;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeCargo;
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeCuotaPagoDet;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeNotificacion;
import pop.comun.dominio.MaeTelefono;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.TabDocumentos;
import pop.comun.dominio.TabTipoDocumento;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ICargoServ;
import pop.webcobranzas.iface.ICobSeguimientoServ;
import pop.webcobranzas.iface.ICompromisoServ;
import pop.webcobranzas.iface.ICuotaPagoServ;
import pop.webcobranzas.iface.IDocumentosServ;
import pop.webcobranzas.iface.ILlamadaServ;
import pop.webcobranzas.iface.INotificacionServ;
import pop.webcobranzas.iface.ISeguimientoServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.iface.ITelefonoServ;
import pop.webcobranzas.servicio.CargoServ;
import pop.webcobranzas.servicio.CobSeguimientoServ;
import pop.webcobranzas.servicio.CompromisoServ;
import pop.webcobranzas.servicio.CuotaPagoServ;
import pop.webcobranzas.servicio.DocumentosServ;
import pop.webcobranzas.servicio.LlamadaServ;
import pop.webcobranzas.servicio.NotificacionServ;
import pop.webcobranzas.servicio.SeguimientoServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.servicio.TelefonoServ;

import pop.comun.dominio.MaeEmail;
import pop.webcobranzas.iface.IEmailServ;
import pop.webcobranzas.servicio.EmailServ;
import pop.webcobranzas.servicio.InversionServ;
import static pop.webcobranzas.util.Transferir.*;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class SeguimientoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EstadoCuentaBean estadoCuentaBean;

    @Inject
    private SaldoDeudorBean saldoDeudorBean;

    @Inject
    private CronogramaBean cronogramaBean;
    
    @Inject
    private RequerimientoBean requerimientoBean;

   
    private InversionServ inversionServ = new InversionServ();

    
     
    private List<MaeInversion> maeResumenList;
    // inversion Detalle seguimiento
    private MaeInversion maeInversionSeguiDet = new MaeInversion();
    // cargos que tiene asigndo 
    private MaeCargo maeCargo = new MaeCargo();
    // maeseguimiento principal
    private CobMaeSeguimiento cobMaeSeguimiento = new CobMaeSeguimiento();
    // cuotaPago realizar formulas con fecha
    private MaeCuotaPago cuotaPagoFechaCorte = new MaeCuotaPago();
    // cuotaPago realizar formulas con fecha
    private MaeCuotaPago cuotaPagoPrint = new MaeCuotaPago();
    //
    private CobTablas cobTablasAccion = new CobTablas();
    //
    private CobTablas cobTablasFamilia = new CobTablas();
    //
    // para enviar a grabar  (_G)
    private CobTablas cobTablasAccionG = new CobTablas();
    //
    private CobTablas cobTablasFamiliaG = new CobTablas();
    // 
    private CobLlamadas cobLlamadas = new CobLlamadas();
    // 
    private CobCompromiso cobCompromiso = new CobCompromiso();
    // notificacion de compromiso
    private MaeNotificacion maeNotificacionC = new MaeNotificacion();
    // notificacion de recordatorio
    private MaeNotificacion maeNotificacionR = new MaeNotificacion();
    // telefono
    private MaeTelefono maeTelefono = new MaeTelefono();
    // tablas
    private CobTablas cobTablas = new CobTablas();

    // lista de pagos
    private List<MaeCuotaPago> maeCuotaPagoList;
    // lista de acciones
    private List<CobTablas> cobTablasAccionList;
    // lista de familias
    private List<CobTablas> cobTablasFamiliaList;
    // lista de detalle de cuotas a pagar
    private List<MaeCuotaPago> mcpList = new ArrayList<>();
    // lista de inversiones 
    private List<MaeTelefono> maeTelefonoList;
    // lista de tipo de telefono
    private List<CobTablas> cobTablasTeleList;

    private List<CobTablas> cobTablasUsoList;

    // servicios de pago
    private ICuotaPagoServ cuotaPagoServ = new CuotaPagoServ();
    // servicios de cargos
    private ICargoServ cargoServ = new CargoServ();
    // servicios de seguimiento
    private ISeguimientoServ seguimientoServ = new SeguimientoServ();
    // sericios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // sericios de llamadas
    private ILlamadaServ llamadaServ = new LlamadaServ();
    // sericios de compromiso
    private ICompromisoServ compromisoServ = new CompromisoServ();
    // sericios de notificaciones
    private INotificacionServ notificacionServ = new NotificacionServ();
    // servicios de telefono
    private ITelefonoServ telefonoServ = new TelefonoServ();
    // servicios de cob seguimiento
    private ICobSeguimientoServ cobSeguimientoServ = new CobSeguimientoServ();
    // servicios de documentos
    private IDocumentosServ documentosServ = new DocumentosServ();

   
    
    private MaeEmail maeEmail = new MaeEmail();
    private List<MaeEmail> maeEmailList;
    private IEmailServ emailServ = new EmailServ();
    
    // calculos
    private String cuoPendiente = "0.00";
    private Date fechaCorte;
    private Number cuoPendienteFC = 0;
    private float cuoPendienteOtros = 0;
    private float cuoPendienteTotal = 0;
    private String cuoPendienteDepMes = "0.00";
    private String xFlag = "N";
    private String simbolo = "S/.";

   
    //
    private int nsecuencia = 0;

    // activaciones 
    private boolean contactado;
    private boolean compromiso;
    private boolean recordatorio;
    private boolean showMsg = false;
    // msj
    private String mensaje = "";
    private String tipoMsj = "";
    // Obsercaciones
    private String observacion = "";
    // recordatorio
    private String fechaRec = "";
    
    private String horaRec = "";
    // compromiso
    private boolean showCompromiso = false;
    private String moneda="";

    
    
    public SeguimientoBean() {
        try {
            cobTablas.setCtablaId("0501");
            cobTablasTeleList = getTablasServ().listarTablas(cobTablas);
            cobTablas.setCtablaId("0621");
            cobTablasUsoList = getTablasServ().listarTablas(cobTablas);
                 
        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }

    public void obtenerSeguimientoDetalle(MaeInversion oInversion) {
        //System.out.println(" =========== obtenerSeguimientoDetalle = " + new Date());
        //System.out.println(" AUD - SeguimientoBean - obtenerSeguimientoDetalle - " + oInversion.getcMaeInversionId() + " - " + oInversion.getCInversion() + " - " + SessionUtils.getUserName());
        System.out.println("[" + SessionUtils.getUserName() + "] " + " SeguimientoBean - obtenerSeguimientoDetalle - " + oInversion.getcMaeInversionId() + " - " + oInversion.getCInversion());
        showMsg = false;
        try {
            maeInversionSeguiDet.setMaeFondo(oInversion.getMaeFondo());
            maeInversionSeguiDet.setcMaeInversionId(oInversion.getcMaeInversionId());
            maeInversionSeguiDet.setcTipoInv(oInversion.getcTipoInv());
            maeInversionSeguiDet.setCInversion(oInversion.getCInversion());
            maeInversionSeguiDet.setcPersonaId(oInversion.getcPersonaId());
            maeInversionSeguiDet.setNCuotasAtrasadas(oInversion.getNCuotasAtrasadas());
            maeInversionSeguiDet.setcInversionId(oInversion.getcInversionId());

            TabDocumentos tbDoc = new TabDocumentos();
            tbDoc.setMaeInversion(maeInversionSeguiDet);
            String flg = getDocumentosServ().banderaGenerar(tbDoc);

                   
            maeInversionSeguiDet.setCgeneraDoc(flg);
            Date fecha = new Date();
            MaeCuotaPago oMaeCuotaPago = new MaeCuotaPago();
            oMaeCuotaPago.setMaeInversion(maeInversionSeguiDet);
          
            oMaeCuotaPago.setfProceso(fecha);
            maeResumenList = getInversionServ().listarResumen(oInversion.getcMaeInversionId() );
              System.out.println("resumen"+maeResumenList.size());
            maeCuotaPagoList = getCuotaPagoServ().listarCuotaPago(oMaeCuotaPago);
            if (maeResumenList.size()>0){
                setSimbolo(maeResumenList.get(0).getSimbolo());
                setMoneda(maeResumenList.get(0).getCmoneda());
            }
            if (maeCuotaPagoList.size()>0) {
                xFlag= maeCuotaPagoList.get(0).geteNewflag();
            }else{
                 xFlag= "N";
            }
    
    
            maeCargo.setMaeInversion(maeInversionSeguiDet);
            maeCargo = getCargoServ().calcularCargoAtrasado(maeCargo);
            if (maeCargo.getIPendiente() != null) {
                setCuoPendienteOtros((float) maeCargo.getIPendiente());
            } else {
                setCuoPendienteOtros(0);
            }
             
            cobMaeSeguimiento.setMaeInversion(maeInversionSeguiDet);
                maeTelefono.setANumero("");
                maeTelefono.setNAnexo(0);

            //maeCuotaPagoList.clear();
            mcpList.clear();
            if (cobMaeSeguimiento.getCobSeguimientoList() != null) {
                if (!cobMaeSeguimiento.getCobSeguimientoList().isEmpty()) {
                    cobMaeSeguimiento.getCobSeguimientoList().clear();
                }
            }

            // parametros en blanco
            MaeInmueble maeInmueble = new MaeInmueble();
            MaeUbigeo maeUbigeo = new MaeUbigeo();
            maeInmueble.setMaeUbigeo(maeUbigeo);
            maeInversionSeguiDet.setMaeInmueble(maeInmueble);
            //System.out.println("1.-paso 111 ");
            maeTelefonoList = getTelefonoServ().listarTelfonoInver(maeInversionSeguiDet);
            
            //System.out.println("2.-paso 111 ");
            setFechaCorte(new Date());
            calcularCuoPagFutInicial();
            
            String idFondo=oInversion.getMaeFondo().getCFondoId();
            //System.out.println("id fondo:: "+idFondo);
            maeEmail.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                    
            maeEmailList = getEmailServ().listarEmail2(maeEmail,idFondo);
                
            cronogramaBean.listarCronograma(maeInversionSeguiDet);
            
            CobRequerimientoCartas reqCartas = new CobRequerimientoCartas();
            reqCartas.setInversion(maeInversionSeguiDet);
            reqCartas.getInversion().setcFONDO(idFondo);
            requerimientoBean.obtenerCartasNotariales(reqCartas);

            //cobMaeSeguimiento = getSeguimientoServ().listarSeguimiento(cobMaeSeguimiento);
            //System.out.println("..................Lista de requerimientoBean = " + requerimientoBean.getCartasList().size());
        } catch (Exception e) {
            System.err.println("pop.webcobranzas.bean.InversionBean.obtenerCabDet()");
        }
        //System.out.println(" =========== obtenerSeguimientoDetalle = " + new Date());
    }

    public void obtenerSeguimiento() {
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " SeguimientoBean - obtenerSeguimiento - " + 
            maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion());
            cobMaeSeguimiento.setMaeInversion(maeInversionSeguiDet);
            cobMaeSeguimiento = getSeguimientoServ().listarSeguimiento(cobMaeSeguimiento);
        } catch (Exception e) {
            System.err.println("pop.webcobranzas.bean.InversionBean.obtenerSeguimiento()"+e.getMessage());
        }
    }

    public void calcularCuoPagFutInicial() {
        try {
            //this.setCuoPendienteFC("1552.00");
            //System.out.println("pop.webcobranzas.bean.InversionBean.calcularCuoPagFut()2");
            System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - calcularCuoPagFutInicial - " + maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() + " - " + fechaCorte);
            if (fechaCorte != null) {

                //System.out.println(fechaCorte.toString());
                //System.out.println("            con fecha ");
                cuotaPagoFechaCorte.setMaeInversion(maeInversionSeguiDet);
                cuotaPagoFechaCorte.setfIniBusq(fechaCorte);
                cuotaPagoFechaCorte.setiPendiente(getCuotaPagoServ().calcularCuotaPagoFuturo(cuotaPagoFechaCorte).getiPendiente());

                // estado de cuenta
                maeInversionSeguiDet.setfIniBusq(fechaCorte);
                CobTchn oCobTchn = new CobTchn();
                oCobTchn.setMaeInversion(maeInversionSeguiDet);
                
                oCobTchn.setcUsuarioAdd(SessionUtils.getUserName());
                
                estadoCuentaBean.setMaeInversion(maeInversionSeguiDet);
                estadoCuentaBean.getMaeEstadoCuenta().setfIniBusq(fechaCorte);
                estadoCuentaBean.buscarECDetalle(oCobTchn);

                // saldo deudor
                saldoDeudorBean.setMaeInversionRep(maeInversionSeguiDet);

                this.setCuoPendienteFC(cuotaPagoFechaCorte.getiPendiente());
            } else {
                //System.out.println("            sin fecha ");
                this.setCuoPendienteFC(0);
            }

            //System.out.println(" Fecha de corte " + getCuotaPagoFechaCorte().getfIniBusq().toString() + " - " + cuotaPagoFechaCorte.getiPendiente());

        } catch (Exception e) {
            System.err.println("pop.webcobranzas.bean.SeguimientoBean.calcularCuoPagFutInicial()");
        }
    }

    public void calcularCuoPagFut(AjaxBehaviorEvent event) {
        try {
            //this.setCuoPendienteFC("1552.00");
            //System.out.println("pop.webcobranzas.bean.InversionBean.calcularCuoPagFut()2");
            System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - calcularCuoPagFut - " + 
                    maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() + " - " + fechaCorte);
            if (fechaCorte != null) {
                //System.out.println(fechaCorte.toString());
                //System.out.println("            con fecha ");
                cuotaPagoFechaCorte.setMaeInversion(maeInversionSeguiDet);
                cuotaPagoFechaCorte.setfIniBusq(fechaCorte);
                cuotaPagoFechaCorte.setiPendiente(getCuotaPagoServ().calcularCuotaPagoFuturo(cuotaPagoFechaCorte).getiPendiente());

                // estado de cuenta
                
                MaeCuotaPago oMaeCuotaPago = new MaeCuotaPago();
                oMaeCuotaPago.setMaeInversion(maeInversionSeguiDet);
                oMaeCuotaPago.setfProceso(fechaCorte);
        
                
                maeCuotaPagoList = getCuotaPagoServ().listarCuotaPago(oMaeCuotaPago);
                
                if (maeCuotaPagoList.size()>0) {
                    xFlag= maeCuotaPagoList.get(0).geteNewflag();
                }else{
                     xFlag= "N";
                }


                CobTchn oCobTchn = new CobTchn();
                oCobTchn.setMaeInversion(maeInversionSeguiDet);
                estadoCuentaBean.setMaeInversion(maeInversionSeguiDet);
                estadoCuentaBean.getMaeEstadoCuenta().setfIniBusq(fechaCorte);
                estadoCuentaBean.buscarEC(oCobTchn);

                // saldo deudor
                saldoDeudorBean.setMaeInversionRep(maeInversionSeguiDet);

                this.setCuoPendienteFC(cuotaPagoFechaCorte.getiPendiente());
            } else {
                //System.out.println("            sin fecha ");
                this.setCuoPendienteFC(0);
            }

            //System.out.println(" Fecha de corte " + getCuotaPagoFechaCorte().getfIniBusq().toString() + " - " + cuotaPagoFechaCorte.getiPendiente());

        } catch (Exception e) {
            System.err.println("pop.webcobranzas.bean.SeguimientoBean.calcularCuoPagFut()");
        }
    }

    public void listarAccion(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarAccion()");
            //ubigeoDist = getiUbigeoServ().listarDistrito(maeUbigeoP);
            if (contactado) {
                cobTablasAccion.setCtablaId("0602");
            } else {
                cobTablasAccion.setCtablaId("0603");
            }
            cobTablasFamilia.setCtablaId("0604");
            cobTablasAccionList = getTablasServ().listarTablas(cobTablasAccion);
            cobTablasFamiliaList = getTablasServ().listarTablas(cobTablasFamilia);
            cobTablasFamiliaG.setCtablaDetId(null);
            cobTablasAccionG.setCtablaDetId(null);
            showCompromiso = false;
            //System.out.println("    estado " + contactado);
            //System.out.println("    cobTablasList -> " + cobTablasAccionList.size());
        } catch (Exception e) {
            
        }
    }

    public void listarAccionRem() {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarAccionRem()");
            if (contactado) {
                cobTablasAccion.setCtablaId("0602");
            } else {
                cobTablasAccion.setCtablaId("0603");
            }
            cobTablasAccionList = getTablasServ().listarTablas(cobTablasAccion);
            //System.out.println("    estado contactado" + contactado);
            //System.out.println("    cobTablasAccionList -> " + cobTablasAccionList.size());
        } catch (Exception e) {
        }
    }

    public void listarFamiliaRem() {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarFamiliaRem()");
            cobTablasFamilia.setCtablaId("0604");
            cobTablasFamiliaList = getTablasServ().listarTablas(cobTablasFamilia);
            //System.out.println("    estado contactado" + contactado);
            //System.out.println("    cobTablasList -> " + cobTablasAccionList.size());
        } catch (Exception e) {
        }
    }

    public void activarCompromiso(AjaxBehaviorEvent event) {
        //System.out.println("pop.webcobranzas.bean.SeguimientoBean.activarCompromiso()");

        if (cobTablasAccionG.getCtablaDetId() != null && contactado) {
            if (cobTablasAccionG.getCtablaDetId().equals("0020")
                || cobTablasAccionG.getCtablaDetId().equals("0021")) {
                showCompromiso = true;
                compromiso = showCompromiso;
            } else {
                showCompromiso = false;
                compromiso = showCompromiso;
            }
            //showCompromiso = cobTablasAccionG.getCtablaDetId().equals("0001");
            //compromiso = showCompromiso;
        } else {
            showCompromiso = false;
            compromiso = false;
        }

        //System.out.println("pop.webcobranzas.bean.SeguimientoBean.activarCompromiso() -> " + showCompromiso);
    }

    public void grabarSeguimiento() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - grabarSeguimiento - " + 
                    maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() );
            if (!validarDatos()) {
                showMsg = true;
                return;
            }
            CobMaeSeguimiento cms = new CobMaeSeguimiento();
            cms.setMaeInversion(maeInversionSeguiDet);

            if (cms.getMaeInversion().getcMaeInversionId() == null) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "Seleccione un cliente.";
                return;
            }
            // bucar el mae seguimiento
            cms = getSeguimientoServ().buscar(cms);
            if (cms.getCmaeSeguimientoId() == 0) {
                cms.setMaeInversion(maeInversionSeguiDet);
                cms.setcUsuarioAdd(SessionUtils.getUserName());
                // creamos un mae seguimiento
                int cMaeSegId = getSeguimientoServ().insertar(cms);
                if (cMaeSegId != 0) {
                    cms.setCmaeSeguimientoId(cMaeSegId);
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "error de Mae Seguimiento";
                    return;
                }
            }
            // buscamos el cob seguimiento
            CobSeguimiento cs = new CobSeguimiento();
            cs.setCobMaeSeguimiento(cms);
            // bucar el cob seguimiento
            cs = getCobSeguimientoServ().buscar(cs);
            if ((int) cs.getCcobSeguimientoId() == 0) {
                // creamos un cob seguimiento
                cs.setCobMaeSeguimiento(cms);
                cs.setcUsuarioAdd(SessionUtils.getUserName());
                int cCobSegId = getCobSeguimientoServ().insertar(cs);
                if (cCobSegId != 0) {
                    cs.setCcobSeguimientoId(cCobSegId);
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "error de Cob Seguimiento";
                    return;
                }
            }

            //cobMaeSeguimiento = getSeguimientoServ().listarSeguimiento(cobMaeSeguimiento);
            //cobLlamadas.setCobSeguimiento(cobMaeSeguimiento.getCobSeguimientoList().get(0));
            cobLlamadas.setCobSeguimiento(cs);
            if (contactado) {
                cobLlamadas.setCcodDisposicionId("0602");
            } else {
                cobLlamadas.setCcodDisposicionId("0603");
            }
            cobLlamadas.setCtipoFamiliaId(cobTablasFamiliaG.getCtablaDetId());
            cobLlamadas.setCsituacionId(cobTablasAccionG.getCtablaDetId());
            cobLlamadas.setDdescripcion(observacion);
            cobLlamadas.setcUsuarioAdd(SessionUtils.getUserName());

            int codLlamada = getLlamadaServ().insertar(cobLlamadas);

            if (codLlamada != 0) {
                showMsg = true;
                setMensaje("Los datos se guardaron con exito!!.");
            } else {
                showMsg = true;
                tipoMsj = "error";
                setMensaje("Error en la comunicación. Vuelva a intentarlo.");
                return;
            }
            // grabar compromiso
            if (compromiso) {
                //cobCompromiso.setCobSeguimiento(cobMaeSeguimiento.getCobSeguimientoList().get(0));
                cobCompromiso.setCobSeguimiento(cs);
                cobCompromiso.setDobservacion(observacion);
                cobCompromiso.setCtipoNexoId("0001");
                cobCompromiso.setCnexoId(codLlamada);

                cobCompromiso.setcUsuarioAdd(SessionUtils.getUserName());
                if (getCompromisoServ().insertar(cobCompromiso) != 0) {
                    maeNotificacionC.setCusuarioDeId(SessionUtils.getUserName());
                    maeNotificacionC.setCusuarioPaId(SessionUtils.getUserName());
                    maeNotificacionC.setcUsuarioAdd(SessionUtils.getUserName());
                    maeNotificacionC.setCtipoId("0005");
                    maeNotificacionC.setFnotificacion(cobCompromiso.getFfecha());
                    //maeNotificacionC.setDtitulo("Compromiso " + cobMaeSeguimiento.getMaeInversion().getCInversion().trim());
                    //maeNotificacionC.setDcuerpo("Compromiso de pago del " + cobMaeSeguimiento.getMaeInversion().getCInversion().trim());
                    maeNotificacionC.setDtitulo("Compromiso " + cms.getMaeInversion().getCInversion().trim());
                    maeNotificacionC.setDcuerpo("Compromiso de pago del " + cms.getMaeInversion().getCInversion().trim());

                    maeNotificacionC.seteEstado("01");
                    if (getNotificacionServ().insertar(maeNotificacionC) == 0) {
                        showMsg = true;
                        setMensaje("El compromiso de Pago No se guardaron !!.");
                        tipoMsj = "error";
                        return;
                    }
                }
            }
            // grabar recordatorio
            if (recordatorio) {
                maeNotificacionR.setCusuarioDeId(SessionUtils.getUserName());
                maeNotificacionR.setCusuarioPaId(SessionUtils.getUserName());
                maeNotificacionR.setcUsuarioAdd(SessionUtils.getUserName());
                maeNotificacionR.setCtipoId("0001");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                //System.out.println(" ----------- + " + fechaRec.trim() + " " + horaRec.trim());
                maeNotificacionR.setFnotificacion(formatter.parse(fechaRec.trim() + " " + horaRec.trim()));
                //maeNotificacionR.setDtitulo("Recordatorio " + cobMaeSeguimiento.getMaeInversion().getCInversion().trim());
                //maeNotificacionR.setDcuerpo("Recordatorio de pago del " + cobMaeSeguimiento.getMaeInversion().getCInversion().trim());
                maeNotificacionR.setDtitulo("Recordatorio " + cms.getMaeInversion().getCInversion().trim());
                maeNotificacionR.setDcuerpo(cms.getMaeInversion().getCInversion().trim() + " - " + observacion + "");
                maeNotificacionR.seteEstado("01");
                if (getNotificacionServ().insertar(maeNotificacionR) == 0) {
                    showMsg = true;
                    setMensaje("El recordatorio de pago No se registraron!!.");
                    tipoMsj = "error";
                    return;
                }

            }

            showMsg = true;
            tipoMsj = "success";
            setMensaje("Se registraron los datos ingresados.");

            contactado = false;
            compromiso = false;
            recordatorio = false;
            //
            observacion = "";
            cobCompromiso = new CobCompromiso();
            cobLlamadas = new CobLlamadas();
            cobTablasFamiliaG.setCtablaDetId(null);
            cobTablasAccionG.setCtablaDetId(null);

            listarAccionRem();

        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("grabarSeguimiento[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }

    }

    // funcion para validar
    private boolean validarDatos() {
        // si ha conestado la llamada
        if (contactado) {
            // select de familia obligatorio
            if (cobTablasFamiliaG.getCtablaDetId() == null) {
                tipoMsj = "warning";
                setMensaje("Falta seleccionar familiar.");
                return false;
            }
            // select de accion obligatorio
            if (cobTablasAccionG.getCtablaDetId() == null) {
                tipoMsj = "warning";
                setMensaje("Falta seleccionar acción.");
                return false;
            }

            if (compromiso) {
                if (cobCompromiso.getFfecha() == null || cobCompromiso.getImonto() <= 0) {
                    tipoMsj = "warning";
                    setMensaje("Falta datos de compromiso.");
                    return false;
                }
            }

        } else {
            // sacar el select de familia 
            getCobTablasFamiliaG().setCtablaDetId(null);
            if (cobTablasAccionG.getCtablaDetId() == null) {
                tipoMsj = "warning";
                setMensaje("Falta seleccionar acción.");
                return false;
            }
        }

        if (observacion == null) {
            tipoMsj = "warning";
            setMensaje("Falta agregar la tipificación.");
            return false;
        } else if (observacion.trim().length() == 0) {
            tipoMsj = "warning";
            setMensaje("Falta agregar la tipificación.");
            return false;
        }
        return true;
    }

    public void calularTotal(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.calularTotal()");
            float totalVar;
            totalVar = (Float) maeCargo.getIPendiente() + Float.parseFloat(cuoPendiente);
            setCuoPendienteTotal(totalVar);
            //System.out.println(Float.parseFloat(cuoPendiente));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            setCuoPendienteTotal(0);
        }
    }

    public void calularTotalB() {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.calularTotalB()");
            System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - calularTotalB - " + 
                    maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() );
            
    
            for (MaeCuotaPago mcp : maeCuotaPagoList) {
                if (mcp.getnSecuencia() == nsecuencia) {
                    cuotaPagoPrint = mcp;
                    break;
                }
            }
            cuoPendiente = "" + cuotaPagoPrint.getiPendiente();
            float Tota = 0;
            if (!cuotaPagoPrint.getMaeCuotaPagoDetList().isEmpty()) {

                mcpList = new ArrayList<>();
                mcpList.clear();
                int nsec = 0;
                float cuoTot = 0;
                
                MaeCuotaPago mcp = null;
                for (MaeCuotaPagoDet mcpd : cuotaPagoPrint.getMaeCuotaPagoDetList()) {
                    if (mcpd.getMaeCuotaPago().getnSecuencia() != nsec) {
                        mcp = new MaeCuotaPago();
                        mcp.setnSecuencia(mcpd.getMaeCuotaPago().getnSecuencia());
                        mcp.setDsecuencia(mcpd.getMaeCuotaPago().getDsecuencia());
                        mcp.setfPagoCrono(mcpd.getMaeCuotaPago().getfPagoCrono());
                        mcp.setiCuota(mcpd.getMaeCuotaPago().getiCuota());
                        mcp.seteNewflag(mcpd.getMaeCuotaPago().geteNewflag());
                        cuoTot = 0;
                        mcpList.add(mcp);
                    }
                        
                    // capital
                    if (mcpd.getCconceptoId().equals("0004")) {
                        mcp.setIcapital(mcpd.getIpendiente());
                        cuoTot = (float) (cuoTot + mcpd.getIpendiente());
                        Tota = (float) (Tota + mcpd.getIpendiente());
                        
                    } else if (mcpd.getCconceptoId().equals("0002")) {
                        mcp.setIcompensatorio(mcpd.getIpendiente());
                        cuoTot = (float) (cuoTot + mcpd.getIpendiente());
                        Tota = (float) (Tota + mcpd.getIpendiente());
                        
                    } else if (mcpd.getCconceptoId().equals("0001") && mcpd.geteFlagNew().equals("S") ) {
                        mcp.setIca(mcpd.getIpendiente());
                        cuoTot = (float) (cuoTot + mcpd.getIpendiente());
                        Tota = (float) (Tota + mcpd.getIpendiente());
                        
                    } else if (mcpd.getCconceptoId().equals("0005") && mcpd.geteFlagNew().equals("S")) {
                        mcp.setImoratorio(mcpd.getIpendiente());
                        cuoTot = (float) (cuoTot + mcpd.getIpendiente());
                        Tota = (float) (Tota + mcpd.getIpendiente());
                        
                    }else if (mcpd.getCconceptoId().equals("0001") && mcpd.geteFlagNew().equals("N") ) {
                        mcp.setImoratorio(mcpd.getIpendiente());
                        cuoTot = (float) (cuoTot + mcpd.getIpendiente());
                        Tota = (float) (Tota + mcpd.getIpendiente());
                        
                    }
                    mcp.setiPendiente(cuoTot);
                     
                    nsec = mcpd.getMaeCuotaPago().getnSecuencia();
                }
            }
            // total de cuotas que debe + cargos 
            if (cuotaPagoPrint.getiPendiente() != null) {
                //cuoPendienteTotal = (float) cuotaPagoPrint.getiPendiente() + cuoPendienteOtros;
                cuoPendienteTotal = Tota;
                cuotaPagoPrint.setiPendiente(Tota);
            } else {
                cuoPendienteTotal = cuoPendienteOtros;
                cuotaPagoPrint.setiPendiente(cuoPendienteOtros);
            }
            //System.out.println("--> " + cuotaPagoPrint.getMaeCuotaPagoDetList().size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setCuoPendienteTotal(0);
        }
    }

     public void GrabarEmail(MaeEmail oEmail) {
        int rep =0;
        showMsg = true;
        try {
            Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                // El email a validar
                String email = oEmail.getDemail();

                Matcher mather = pattern.matcher(email);

                if (mather.find() == true) {
                    System.out.println("EMAIL ES1 " +oEmail.getCemailId() + "--" + oEmail.getMaePersona().getANroDocumento() + " - " + oEmail.getDemail()+maeInversionSeguiDet.getcPersonaId().getCPersona());
                    rep=getEmailServ().actualizar2(oEmail);
                }
                if (rep == 0) {
                    tipoMsj = "error";
                    mensaje = "El email ingresado no es válido.";
                  } else {
                    tipoMsj = "success";
                    mensaje = "Se guardo Email";
                    rep =0;
                }
              
             
        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("borrarTel[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }
     
    public void actualizarCompromiso(CobCompromiso oCobCompromiso) {
        //System.out.println("pop.webcobranzas.bean.SeguimientoBean.actualizarCompromisoLike()");
        try {
            if (oCobCompromiso.getEestadoId() == null) {
                oCobCompromiso.setEestadoId("0001");
            } else if (oCobCompromiso.getEestadoId().equals("0001")) {
                oCobCompromiso.setEestadoId("0002");
            } else {
                oCobCompromiso.setEestadoId("0001");
            }
            getCompromisoServ().actualizar(oCobCompromiso);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Operacion exitosa!"));
        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("actualizarCompromiso[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }

    public void agregarTel()  throws SQLException,Exception {
        
        /*System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - agregarTel - " + 
                    maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() + " - " + maeTelefono.getANumero().trim());*/
       System.out.println("["+SessionUtils.getUserName()+"] "+" SISTEMA - agregarTel - " + maeInversionSeguiDet.getcPersonaId() +
               " -- "+maeInversionSeguiDet.getcPersonaId().getANroDocumento() + " - " + maeInversionSeguiDet.getCInversion() + " - " + maeTelefono.getANumero().trim());

            
        showMsg = false;
        System.out.println("pasando por acas");
        if (maeTelefono.getCTipoTel().equals("0001")) { // fijo
            if (maeTelefono.getANumero().trim().length() != 7) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "error de número de teléfono";
            } else {
                MaeTelefono mt = new MaeTelefono();
                mt.setCTipoTel(maeTelefono.getCTipoTel());
                mt.setANumero(maeTelefono.getANumero().trim());
                mt.setNAnexo(maeTelefono.getNAnexo());
                mt.setCUsoTel(maeTelefono.getCUsoTel());
                mt.setBPrede(false);
                mt.setNPrede(0);
                mt.setSEstado("01");
                mt.setBDefault("0");
                mt.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                
                mt.setCTelefonoId(999);
              
                showMsg = false;
                //System.out.println("persona --> " + maeInversionSeguiDet.getcPersonaId().getCPersonaId());
                maeTelefono.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                maeTelefono.setcUsuarioAdd(SessionUtils.getUserName());
                System.out.println("1.[getTelefonoServ().insertar()");                
                int idTelefono = getTelefonoServ().insertar(maeTelefono);
                int resp=0;
                if (idTelefono > 0) {
                      showMsg = true;
                      tipoMsj = "success";
                      mensaje = "se agrego  número de teléfono";
       
                    System.out.println("idTelefono > 0:idTelefono:[" + idTelefono+"]"); 
                    maeTelefono.setCTelefonoId(idTelefono);
                    // parametros en blanco
                    //maeTelefonoList = getTelefonoServ().listarTelfonoInver(maeInversionSeguiDet);
                     maeTelefonoList.add(mt) ;
                    
                    System.out.println("telefonoPresta");
                    maeTelefono.setSEstado("01");
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "error de número de teléfono";
                }

                maeTelefono.setANumero("");
                maeTelefono.setNAnexo(0);
                showMsg = false;
            }
        } else if (maeTelefono.getCTipoTel().equals("0002")  ) { // Celular
            if (maeTelefono.getANumero().trim().length() != 9) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "error de número de celular";
            } else {
                MaeTelefono mt = new MaeTelefono();
                mt.setCTipoTel(maeTelefono.getCTipoTel());
                mt.setANumero(maeTelefono.getANumero().trim());
                mt.setNAnexo(maeTelefono.getNAnexo());
                mt.setCUsoTel(maeTelefono.getCUsoTel());
                mt.setBDefault("0");
                mt.setSEstado("01");
                mt.setBPrede(false);
                mt.setNPrede(0);
                mt.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                mt.setCTelefonoId(999);
               
                showMsg = false;
                //System.out.println("persona --> " + maeInversionSeguiDet.getcPersonaId().getCPersonaId());
                maeTelefono.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                maeTelefono.setcUsuarioAdd(SessionUtils.getUserName());
                System.out.println("2.[getTelefonoServ().insertar()");                
                int idTelefono = getTelefonoServ().insertar(maeTelefono);
                int resp=0;
                if (idTelefono > 0) {
                    System.out.println("idTelefono > 0:idTelefono:[" + idTelefono+"]"); 
                    maeTelefono.setCTelefonoId(idTelefono);
                    // parametros en blanco
                    //maeTelefonoList = getTelefonoServ().listarTelfonoInver(maeInversionSeguiDet);
                     maeTelefonoList.add(mt) ;
                    
                    System.out.println("telefonoPresta22");
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "error de número de teléfono";
                }
                maeTelefono.setANumero("");
                maeTelefono.setNAnexo(0);
                showMsg = false;
            }
        }else{
             if (maeTelefono.getANumero().trim().length() != 9) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "error de número de celular";
            } else {
                MaeTelefono mt = new MaeTelefono();
                mt.setCTipoTel(maeTelefono.getCTipoTel());
                mt.setANumero(maeTelefono.getANumero().trim());
                mt.setNAnexo(maeTelefono.getNAnexo());
                mt.setCUsoTel(maeTelefono.getCUsoTel());
                mt.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                mt.setCTelefonoId(999);
                mt.setBDefault("0");
                mt.setSEstado("01");
                mt.setBPrede(false);
                mt.setNPrede(0);
               
                showMsg = false;
                //System.out.println("persona --> " + maeInversionSeguiDet.getcPersonaId().getCPersonaId());
                maeTelefono.setMaePersona(maeInversionSeguiDet.getcPersonaId());
                maeTelefono.setcUsuarioAdd(SessionUtils.getUserName());
                System.out.println("2.[getTelefonoServ().insertar()");                
                int idTelefono = getTelefonoServ().insertar(maeTelefono);
                int resp=0;
                if (idTelefono > 0) {
                    System.out.println("idTelefono > 0:idTelefono:[" + idTelefono+"]"); 
                    maeTelefono.setCTelefonoId(idTelefono);
                    // parametros en blanco
                     maeTelefonoList.add(mt) ;
                    //getTelefonoServ().listarTelfonoInver(maeInversionSeguiDet);
                    System.out.println("telefonoPresta22");
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "error de número de teléfono";
                }
                maeTelefono.setANumero("");
                maeTelefono.setNAnexo(0);
                showMsg = false;
            }
        }
        System.out.println("showMsg["+showMsg+ "]");
        System.out.println("tipoMsj["+tipoMsj+ "]");
        System.out.println("mensaje["+mensaje+ "]");
    }

    public void borrarSeg(String cobSeguimientoId, String codLlamadaId,String codLlamada,String usuario) {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+"borrarSeg - cobSeguimientoId:"+cobSeguimientoId+" ;codLlamadaId:"+codLlamadaId + ";codLlamada:"+codLlamada+";usuario:"+usuario);
            showMsg = true;
            boolean rpta = getSeguimientoServ().borrar(cobSeguimientoId,codLlamadaId,codLlamada,usuario);
            if (rpta) {
                tipoMsj = "success";
                mensaje = "Se eliminó el seguimiento";
                obtenerSeguimiento();
            }
            else
            {
                tipoMsj = "error";
                mensaje = "No se pudo eliminar seguimiento.";
            }
        } catch (Exception ex) {
            System.out.println("borrarTel[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }
    
    public void ActualizarTel() {
        
        try {
             System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - borrarTel - " + 
                    maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() + " - " );
             showMsg = true;
             
             boolean rpta=false;
            for (MaeTelefono telf1 : maeTelefonoList) {
                telf1.setcUsuarioMod(SessionUtils.getUserName());
                System.out.println("actualizandole telefono  ---" + telf1.getANumero() +"--"+ telf1.getSEstado()+"--"+ telf1.isBPrede());
                if (telf1.isBPrede()){
                    telf1.setNPrede(1);
                }else{
                   telf1.setNPrede(0);
                }
                rpta = getTelefonoServ().modificar(telf1);
               maeTelefonoList = getTelefonoServ().listarTelfonoInver(maeInversionSeguiDet);
    
            }
                maeTelefono.setANumero("");
                maeTelefono.setNAnexo(0);

            if (rpta){
                tipoMsj = "success";
                mensaje = "Se guarda el telefono correctamente";
            }else{
                tipoMsj = "error";
                mensaje = "No se pudo guardar correctamente";
            }
           
         
        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ActualizarTel[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }

    
    public void borrarTel(MaeTelefono oMaeTelefono) {
        
        try {
    showMsg = false;       
             System.out.println("["+SessionUtils.getUserName()+"] "+" SeguimientoBean - borrarTel - " + 
                    maeInversionSeguiDet.getcMaeInversionId() + " - " + maeInversionSeguiDet.getCInversion() + " - " + oMaeTelefono.getANumero().trim());
            
            oMaeTelefono.seteEstado("02");
            //EC: 04/01/2019 No llega el C_FONDO_ID a telefono
            //oMaeTelefono.getMaePersona().setMaeFondo(new MaeFondo());
             
            oMaeTelefono.setMaePersona(maeInversionSeguiDet.getcPersonaId());
            oMaeTelefono.setCFondoId(oMaeTelefono.getMaePersona().getMaeFondo().getCFondoId());            
            oMaeTelefono.setcUsuarioMod(SessionUtils.getUserName());
            boolean rpta = getTelefonoServ().actualizar(oMaeTelefono);
            if (rpta) {
                showMsg = true;       
                maeTelefonoList = getTelefonoServ().listarTelfonoInver(maeInversionSeguiDet);
                //maeTelefonoList.remove(oMaeTelefono);
                tipoMsj = "success";
                mensaje = "Se elimino el telefono correctamente";
    
            }
        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("borrarTel[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }

    public void generarDocumento(int TipoDoc) {
        try {
            TabDocumentos tbDoc = new TabDocumentos();
            tbDoc.setMaeInversion(maeInversionSeguiDet);
            tbDoc.setcUsuarioAdd(SessionUtils.getUserName());

            String flg = getDocumentosServ().banderaGenerar(tbDoc);

            if (flg.equals("01")) {
                //if (getDocumentosServ().generar(tbDoc) != 0) {
                TabTipoDocumento ttd = new TabTipoDocumento();
                ttd.setCtabTipoDocId(TipoDoc);
                tbDoc.setTabTipoDocumento(ttd);
                if (getDocumentosServ().generarManual(tbDoc) != 0) {
                    showMsg = true;
                    tipoMsj = "success";
                    mensaje = "Documento listo para Generar";
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se grabo el documentos.";
                }
            }
            flg = getDocumentosServ().banderaGenerar(tbDoc);

            maeInversionSeguiDet.setCgeneraDoc(flg);
        } catch (Exception ex) {
            //Logger.getLogger(SeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("generarDocumento[" + SeguimientoBean.class.getName() + "] " + ex.getMessage());
        }
    }

    public MaeInversion getMaeInversionSeguiDet() {
        return maeInversionSeguiDet;
    }

    public void setMaeInversionSeguiDet(MaeInversion maeInversionSeguiDet) {
        this.maeInversionSeguiDet = maeInversionSeguiDet;
    }

    public List<MaeCuotaPago> getMaeCuotaPagoList() {
        return maeCuotaPagoList;
    }

    public void setMaeCuotaPagoList(List<MaeCuotaPago> maeCuotaPagoList) {
        this.maeCuotaPagoList = maeCuotaPagoList;
    }

    public ICuotaPagoServ getCuotaPagoServ() {
        return cuotaPagoServ;
    }

    public void setCuotaPagoServ(ICuotaPagoServ cuotaPagoServ) {
        this.cuotaPagoServ = cuotaPagoServ;
    }

    public ICargoServ getCargoServ() {
        return cargoServ;
    }

    public void setCargoServ(ICargoServ cargoServ) {
        this.cargoServ = cargoServ;
    }

    public MaeCargo getMaeCargo() {
        return maeCargo;
    }

    public void setMaeCargo(MaeCargo maeCargo) {
        this.maeCargo = maeCargo;
    }

    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public String getCuoPendiente() {
        return cuoPendiente;
    }

    public void setCuoPendiente(String cuoPendiente) {
        this.cuoPendiente = cuoPendiente;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Number getCuoPendienteFC() {
        return cuoPendienteFC;
    }

    public void setCuoPendienteFC(Number cuoPendienteFC) {
        this.cuoPendienteFC = cuoPendienteFC;
    }

    public String getCuoPendienteDepMes() {
        return cuoPendienteDepMes;
    }

    public void setCuoPendienteDepMes(String cuoPendienteDepMes) {
        this.cuoPendienteDepMes = cuoPendienteDepMes;
    }

    public ISeguimientoServ getSeguimientoServ() {
        return seguimientoServ;
    }

    public void setSeguimientoServ(ISeguimientoServ seguimientoServ) {
        this.seguimientoServ = seguimientoServ;
    }

    public MaeCuotaPago getCuotaPagoFechaCorte() {
        return cuotaPagoFechaCorte;
    }

    public void setCuotaPagoFechaCorte(MaeCuotaPago cuotaPagoFechaCorte) {
        this.cuotaPagoFechaCorte = cuotaPagoFechaCorte;
    }

    public boolean isContactado() {
        return contactado;
    }

    public void setContactado(boolean contactado) {
        this.contactado = contactado;
    }

    public CobTablas getCobTablasAccion() {
        return cobTablasAccion;
    }

    public void setCobTablasAccion(CobTablas cobTablasAccion) {
        this.cobTablasAccion = cobTablasAccion;
    }

    public CobTablas getCobTablasFamilia() {
        return cobTablasFamilia;
    }

    public void setCobTablasFamilia(CobTablas cobTablasFamilia) {
        this.cobTablasFamilia = cobTablasFamilia;
    }

    public List<CobTablas> getCobTablasAccionList() {
        return cobTablasAccionList;
    }

    public void setCobTablasAccionList(List<CobTablas> cobTablasAccionList) {
        this.cobTablasAccionList = cobTablasAccionList;
    }

    public List<CobTablas> getCobTablasFamiliaList() {
        return cobTablasFamiliaList;
    }

    public void setCobTablasFamiliaList(List<CobTablas> cobTablasFamiliaList) {
        this.cobTablasFamiliaList = cobTablasFamiliaList;
    }

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public CobTablas getCobTablasAccionG() {
        return cobTablasAccionG;
    }

    public void setCobTablasAccionG(CobTablas cobTablasAccionG) {
        this.cobTablasAccionG = cobTablasAccionG;
    }

    public CobTablas getCobTablasFamiliaG() {
        return cobTablasFamiliaG;
    }

    public void setCobTablasFamiliaG(CobTablas cobTablasFamiliaG) {
        this.cobTablasFamiliaG = cobTablasFamiliaG;
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

    public CobLlamadas getCobLlamadas() {
        return cobLlamadas;
    }

    public void setCobLlamadas(CobLlamadas cobLlamadas) {
        this.cobLlamadas = cobLlamadas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ILlamadaServ getLlamadaServ() {
        return llamadaServ;
    }

    public void setLlamadaServ(ILlamadaServ llamadaServ) {
        this.llamadaServ = llamadaServ;
    }

    public ICompromisoServ getCompromisoServ() {
        return compromisoServ;
    }

    public void setCompromisoServ(ICompromisoServ compromisoServ) {
        this.compromisoServ = compromisoServ;
    }

    public CobCompromiso getCobCompromiso() {
        return cobCompromiso;
    }

    public void setCobCompromiso(CobCompromiso cobCompromiso) {
        this.cobCompromiso = cobCompromiso;
    }

    public boolean isCompromiso() {
        return compromiso;
    }

    public void setCompromiso(boolean compromiso) {
        this.compromiso = compromiso;
    }

    public INotificacionServ getNotificacionServ() {
        return notificacionServ;
    }

    public void setNotificacionServ(INotificacionServ notificacionServ) {
        this.notificacionServ = notificacionServ;
    }

    public MaeNotificacion getMaeNotificacionC() {
        return maeNotificacionC;
    }

    public void setMaeNotificacionC(MaeNotificacion maeNotificacionC) {
        this.maeNotificacionC = maeNotificacionC;
    }

    public MaeNotificacion getMaeNotificacionR() {
        return maeNotificacionR;
    }

    public void setMaeNotificacionR(MaeNotificacion maeNotificacionR) {
        this.maeNotificacionR = maeNotificacionR;
    }

    public boolean isRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(boolean recordatorio) {
        this.recordatorio = recordatorio;
    }

    public String getFechaRec() {
        return fechaRec;
    }

    public void setFechaRec(String fechaRec) {
        this.fechaRec = fechaRec;
    }

    public String getHoraRec() {
        return horaRec;
    }

    public void setHoraRec(String horaRec) {
        this.horaRec = horaRec;
    }

    public MaeCuotaPago getCuotaPagoPrint() {
        return cuotaPagoPrint;
    }

    public void setCuotaPagoPrint(MaeCuotaPago cuotaPagoPrint) {
        this.cuotaPagoPrint = cuotaPagoPrint;
    }

    public int getNsecuencia() {
        return nsecuencia;
    }

    public void setNsecuencia(int nsecuencia) {
        this.nsecuencia = nsecuencia;
    }

    public List<MaeCuotaPago> getMcpList() {
        return mcpList;
    }

    public void setMcpList(List<MaeCuotaPago> mcpList) {
        this.mcpList = mcpList;
    }

    public EstadoCuentaBean getEstadoCuentaBean() {
        return estadoCuentaBean;
    }

    public void setEstadoCuentaBean(EstadoCuentaBean estadoCuentaBean) {
        this.estadoCuentaBean = estadoCuentaBean;
    }

    public SaldoDeudorBean getSaldoDeudorBean() {
        return saldoDeudorBean;
    }

    public void setSaldoDeudorBean(SaldoDeudorBean saldoDeudorBean) {
        this.saldoDeudorBean = saldoDeudorBean;
    }

    public List<MaeTelefono> getMaeTelefonoList() {
        return maeTelefonoList;
    }

    public void setMaeTelefonoList(List<MaeTelefono> maeTelefonoList) {
        this.maeTelefonoList = maeTelefonoList;
    }

    public ITelefonoServ getTelefonoServ() {
        return telefonoServ;
    }

    public void setTelefonoServ(ITelefonoServ telefonoServ) {
        this.telefonoServ = telefonoServ;
    }

    public MaeTelefono getMaeTelefono() {
        return maeTelefono;
    }

    public void setMaeTelefono(MaeTelefono maeTelefono) {
        this.maeTelefono = maeTelefono;
    }

    public CobTablas getCobTablas() {
        return cobTablas;
    }

    public void setCobTablas(CobTablas cobTablas) {
        this.cobTablas = cobTablas;
    }

    public List<CobTablas> getCobTablasTeleList() {
        return cobTablasTeleList;
    }

    public void setCobTablasTeleList(List<CobTablas> cobTablasTeleList) {
        this.cobTablasTeleList = cobTablasTeleList;
    }

    public String getTipoMsj() {
        return tipoMsj;
    }

    public void setTipoMsj(String tipoMsj) {
        this.tipoMsj = tipoMsj;
    }

    public ICobSeguimientoServ getCobSeguimientoServ() {
        return cobSeguimientoServ;
    }

    public void setCobSeguimientoServ(ICobSeguimientoServ cobSeguimientoServ) {
        this.cobSeguimientoServ = cobSeguimientoServ;
    }

    public float getCuoPendienteOtros() {
        return cuoPendienteOtros;
    }

    public void setCuoPendienteOtros(float cuoPendienteOtros) {
        this.cuoPendienteOtros = cuoPendienteOtros;
    }

    public float getCuoPendienteTotal() {
        return cuoPendienteTotal;
    }

    public void setCuoPendienteTotal(float cuoPendienteTotal) {
        this.cuoPendienteTotal = cuoPendienteTotal;
    }

    public CronogramaBean getCronogramaBean() {
        return cronogramaBean;
    }

    public void setCronogramaBean(CronogramaBean cronogramaBean) {
        this.cronogramaBean = cronogramaBean;
    }

    public boolean isShowCompromiso() {
        return showCompromiso;
    }

    public void setShowCompromiso(boolean showCompromiso) {
        this.showCompromiso = showCompromiso;
    }

    public IDocumentosServ getDocumentosServ() {
        return documentosServ;
    }

    public void setDocumentosServ(IDocumentosServ documentosServ) {
        this.documentosServ = documentosServ;
    }

 public MaeEmail getMaeEmail() {
        return maeEmail;
    }

    public void setMaeEmail(MaeEmail maeEmail) {
        this.maeEmail = maeEmail;
    }

    public List<MaeEmail> getMaeEmailList() {
        return maeEmailList;
    }

    public void setMaeEmailList(List<MaeEmail> maeEmailList) {
        this.maeEmailList = maeEmailList;
    }

    public IEmailServ getEmailServ() {
        return emailServ;
    }

    public void setEmailServ(IEmailServ emailServ) {
        this.emailServ = emailServ;
    }    

public String getxFlag() {
        return xFlag;
    }

    public void setxFlag(String xFlag) {
        this.xFlag = xFlag;
    }
        
     public List<MaeInversion> getMaeResumenList() {
        return maeResumenList;
    }

    public void setMaeResumenList(List<MaeInversion> maeResumenList) {
        this.maeResumenList = maeResumenList;
    }
    
    public InversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(InversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    
     public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    public List<CobTablas> getCobTablasUsoList() {
        return cobTablasUsoList;
    }

    public void setCobTablasUsoList(List<CobTablas> cobTablasUsoList) {
        this.cobTablasUsoList = cobTablasUsoList;
    }

    /**
     * @return the requerimientoBean
     */
    public RequerimientoBean getRequerimientoBean() {
        return requerimientoBean;
    }

    /**
     * @param requerimientoBean the requerimientoBean to set
     */
    public void setRequerimientoBean(RequerimientoBean requerimientoBean) {
        this.requerimientoBean = requerimientoBean;
    }

}
