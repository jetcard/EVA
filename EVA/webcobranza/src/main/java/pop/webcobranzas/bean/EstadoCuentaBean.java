/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeCorreo;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeEmail;
import pop.comun.dominio.MaeEstadoCuenta;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IEmailServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.IInversionServ;
import pop.webcobranzas.ifacecorreo.ICorreoServ;
import pop.webcobranzas.ifacerep.IRepEstadoCuentaServ;
import pop.webcobranzas.ifacerep.IRepSaldoDeudorServ;
import pop.webcobranzas.servicio.EmailServ;
import pop.webcobranzas.servicio.EstadoCuentaServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.InversionServ;
import pop.webcobranzas.serviciocorreo.CorreoServ;
import pop.webcobranzas.serviciorep.RepEstadoCuentaServ;
import pop.webcobranzas.serviciorep.RepSaldoDeudorServ;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pop.webcobranzas.properties.ManageProperties;

/**
 *
 * @author Jyoverar
 */
@ManagedBean(name = "estadoCuentaBean", eager = true)
@ViewScoped
public class EstadoCuentaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    // ubigeo Busqueda
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    // imnueble (busq por provincia/distrito)
    private MaeInmueble maeInmueble = new MaeInmueble();
    //
    private MaePersona maePersona = new MaePersona();
    private MaeInversion maeInversion = new MaeInversion();
    // inversion para imprimir
    private MaeInversion maeInversionP = new MaeInversion();
    private MaeFondo maeFondo = new MaeFondo();
    private MaeEstadoCuenta maeEstadoCuenta = new MaeEstadoCuenta();
    private CobTchn cobTchn = new CobTchn();
    //
    private List<MaeEstadoCuenta> maeEstadoCuentaDList;

   

    private ManageProperties properties;
    private List<MaeInversion> maeInversionList;
    private List<MaeEstadoCuenta> maeEstadoCuentaList;
    private List<MaeEstadoCuenta> maeEstadoCuentaBList;
    private List<MaeDeposito> maeEstadoCuentaCList;
    private List<CobTchn> cobTchnList;
    // lista de fondos
    private List<MaeFondo> maeFondoList;

    // servicios de estado de cuenta
    private EstadoCuentaServ estadoCuentaServ = new EstadoCuentaServ();
    // servicios de tipo de cambio reporete
    private IRepEstadoCuentaServ repEstadoCuentaServ = new RepEstadoCuentaServ();
    // servicios de correo
    private ICorreoServ correoServ = new CorreoServ();
    // servicios de correos
    private IEmailServ emailServ = new EmailServ();
    // servicios de tipo de cambio reporte
    private IRepSaldoDeudorServ repSaldoDeudorServ = new RepSaldoDeudorServ();
    // servicios de inversion
    private IInversionServ inversionServ = new InversionServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();

    private int iShowButtonsLiqJudic = 0;
    /**
     * Creates a new instance of EstadoCuentaBean
     */
    public EstadoCuentaBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setMaeInmueble(maeInmueble);

        cobTchn.setFondo(maeFondo);
        cobTchn.setMaeInversion(maeInversion);
        properties = new ManageProperties();
        properties.getProperties();

    }

    public void listarFondos() {
        try {

            if (maeFondoList == null) {
                maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            }
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void buscarTchn() {
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - buscarTchn ");
            //System.out.println("pop.webcobranzas.bean.EstadoCuentaBean.buscarTchn()");
            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            //System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            //System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());
            maeInmueble.setMaeUbigeo(maeUbigeo);
            cobTchnList = estadoCuentaServ.listarTchn(cobTchn);
        } catch (Exception e) {

        }
    }

    public void buscarTchnExt() {
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - buscarTchnExt ");
            if (maeInversion.getCInversion() != null) {
                //System.out.println(" maeInversion.getCInversion()->" + maeInversion.getCInversion());
                cobTchnList = estadoCuentaServ.listarTchn(cobTchn);
            }
        } catch (Exception e) {

        }
    }

    public void buscarEC(CobTchn oCobTchnB) {
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - buscarEC - "
                             + oCobTchnB.getMaeInversion().getcMaeInversionId() + " - " + oCobTchnB.getMaeInversion().getCInversion() + " - " + maeInversion.getfIniBusq());            
            maeInversionP = oCobTchnB.getMaeInversion();
            maeInversionP.setcTipoInv("0001");
            maeInversionP.setfIniBusq(maeInversion.getfIniBusq());
            maeEstadoCuenta.setMaeInversion(maeInversionP);
            maeEstadoCuenta.setcUsuarioAdd(SessionUtils.getUserName());
            maeEstadoCuentaList = estadoCuentaServ.listarEstadoCuenta(maeEstadoCuenta);
            System.out.println("1.- Está llamando a maeEstadoCuentaList = "+maeEstadoCuentaList);
            maeEstadoCuentaBList = estadoCuentaServ.listarEstadoCuentaB(maeEstadoCuenta);
            System.out.println("2.- Está llamando a maeEstadoCuentaBList = "+maeEstadoCuentaList);
            maeEstadoCuentaCList = estadoCuentaServ.listarEstadoCuentaC(maeEstadoCuenta);
            System.out.println("3.- Está llamando a maeEstadoCuentaCList = "+maeEstadoCuentaList);
            maeEstadoCuentaDList = estadoCuentaServ.listarEstadoCuentaD(maeEstadoCuenta);
            System.out.println("4.- Está llamando a maeEstadoCuentaDList = "+maeEstadoCuentaList);
            iShowButtonsLiqJudic=1; //para mostrar los botones: CSV , EXCEL, PDF en la pagina 
            System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - buscarEC - "
                    + "iShowButtonsLiqJudic=1;");
        } catch (Exception e) {
            iShowButtonsLiqJudic=0;
            System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - buscarEC - ERROR:"
                             + e.getMessage());
        }
    }

    public void buscarECDetalle(CobTchn oCobTchnB) {
        try {
            //System.out.println("<i> pop.webcobranzas.bean.EstadoCuentaBean.buscarECDetalle - getcMaeInversionId " + oCobTchnB.getMaeInversion().getcMaeInversionId());
            System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - buscarECDetalle - "
                    + oCobTchnB.getMaeInversion().getcMaeInversionId() + " - " + oCobTchnB.getMaeInversion().getCInversion() + " - " + maeInversion.getfIniBusq());
            maeInversionP = oCobTchnB.getMaeInversion();
            maeInversionP.setcTipoInv("0001");
            maeInversionP.setfIniBusq(maeInversion.getfIniBusq());
            //maeInversion.setcMaeInversionId(oCobTchnB.getMaeInversion().getcMaeInversionId());
            //maeInversion.setcTipoInv("0001");
            //maeInversion.setCInversion(oCobTchnB.getMaeInversion().getCInversion());
            //System.out.println(maeInversion.getfIniBusq());
            //maeEstadoCuenta.setMaeInversion(maeInversion);
            maeEstadoCuenta.setMaeInversion(maeInversionP);
            maeEstadoCuenta.setcUsuarioAdd(SessionUtils.getUserName());
            maeEstadoCuentaList = estadoCuentaServ.listarEstadoCuenta(maeEstadoCuenta);
            //maeEstadoCuentaBList = estadoCuentaServ.listarEstadoCuentaB(maeEstadoCuenta);
            maeEstadoCuentaCList = estadoCuentaServ.listarEstadoCuentaC(maeEstadoCuenta);

            //System.out.println("   Lista de buscarEC = " + maeEstadoCuentaList.size());
        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.EstadoCuentaBean.buscarEC() eeeeeeeee");
        }
        //System.out.println("<f> pop.webcobranzas.bean.EstadoCuentaBean.buscarECDetalle");
    }

    public void iniciarFecha() {
        try {
            //System.out.println("pop.webcobranzas.bean.EstadoCuentaBean.iniciarFecha()");
            if (getMaeEstadoCuenta().getfIniBusq() == null) {
                getMaeEstadoCuenta().setfIniBusq(new Date());
            }
        } catch (Exception e) {

        }
    }

    public void printEstCuenta() {

        try {
            
            //HHA20180228
            System.out.println("HHA[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - printEstCuenta - "
                    + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + maeEstadoCuenta.getfIniBusq());
            
            //System.out.println(" AUD - clienteDeudor - printEstCuenta - " + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + SessionUtils.getUserName());
            //System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - printEstCuenta - "
            //        + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + maeEstadoCuenta.getfIniBusq());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());

            asss = repEstadoCuentaServ.imprimirEstadoCuenta(maeEstadoCuentaList, maeReporte);

            baos.write(asss);
            String name = "";
            if (!maeEstadoCuentaList.isEmpty()) {
                name = maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim();
            }

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                hsr.setHeader("Content-disposition", "inline;filename=\"" + "EstadoCuenta" + name + ".pdf" + "\"");
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

            // correo
//            MaeCorreo maeCorreo = new MaeCorreo();
//            List<byte[]> adj = new ArrayList<>();
//            adj.add(asss);
//            maeCorreo.setAdjuntos((ArrayList<byte[]>) adj);
//            List<MaeCorreo> maeCorreoList = new ArrayList<>();
//            maeCorreoList.add(maeCorreo);
//            correoServ.enviar(maeCorreoList);
        } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void exportEstCuenta() {

        try {
            
            //HHA20180228
            System.out.println("HHA[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - exportEstCuenta - "
                    + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + maeEstadoCuenta.getfIniBusq());

            //System.out.println(" AUD - clienteDeudor - exportEstCuenta - " + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + SessionUtils.getUserName());
            //System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - exportEstCuenta - "
            //        + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + maeEstadoCuenta.getfIniBusq());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] asss = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());

            asss = repEstadoCuentaServ.exportartEstadoCuenta(maeEstadoCuentaList, maeReporte);

            baos.write(asss);
            String name = "";
            if (!maeEstadoCuentaList.isEmpty()) {
                name = maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim();
            }

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/vnd.ms-excel");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                hsr.setHeader("Content-disposition", "attachment;filename=EstadoCuenta" + name + ".xlsx");
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

        } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void printSaldoDeudor() {

        try {
            
            //HHA20180228
            System.out.println("HHA[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - printSaldoDeudor - "
                    + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + maeEstadoCuenta.getfIniBusq());
            
            //System.out.println("[" + SessionUtils.getUserName() + "] " + " EstadoCuentaBean - printSaldoDeudor - "
            //        + maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + maeEstadoCuentaList.get(0).getMaeInversion().getCInversion().trim() + " - " + maeEstadoCuenta.getfIniBusq());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] asss = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            MaeInversion miSD = new MaeInversion();
            miSD.setcMaeInversionId(maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId());
            miSD.setcTipoInv("0001");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            miSD.setfIniBusq(formatter.parse("18/11/2016"));
            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(miSD);
            asss = repSaldoDeudorServ.imprimirSaldoDeudor(oSaldoDeudor, maeReporte);  //repEstadoCuentaServ.imprimirEstadoCuenta(maeEstadoCuentaList, maeReporte);
            baos.write(asss);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "EstadoCuenta.pdf" + "\"");
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

        } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void emailEstCuenta() {

        try {

            byte[] datos = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            datos = repEstadoCuentaServ.imprimirEstadoCuenta(maeEstadoCuentaList, maeReporte);
            // 
            MaeInversion maeInversionSend = new MaeInversion();
            MaeFondo maeFondoSend = new MaeFondo();
            MaePersona maePersonaSend = new MaePersona();
            MaeInmueble maeInmuebleSend = new MaeInmueble();
            MaeUbigeo maeUbigeoSend = new MaeUbigeo();

            maeInversionSend.setMaeFondo(maeFondoSend);
            maeInversionSend.setcPersonaId(maePersonaSend);
            maeInmuebleSend.setMaeUbigeo(maeUbigeoSend);
            maeInversionSend.setMaeInmueble(maeInmuebleSend);
            maeInversionSend.setCInversion(maeEstadoCuentaList.get(0).getMaeInversion().getCInversion());
            List<MaeEmail> maeEmailList = emailServ.listarEmailInver(maeInversionSend);
            System.out.println("pop.webcobranzas.bean.EstadoCuentaBean.emailEstCuenta()" + " maeEmailList " + maeEmailList.size());

            if (maeEmailList.size() > 0) {
                maeEmailList.get(0).setfIniBusq(this.maeEstadoCuenta.getfIniBusq());
                List<MaeCorreo> maeCorreoList = new ArrayList<>();
                MaeCorreo maeCorreo = new MaeCorreo();
                maeCorreo.setMaeEmails((ArrayList<MaeEmail>) maeEmailList);
                List<byte[]> adj = new ArrayList<>();
                adj.add(datos);
                maeCorreo.setAdjuntos((ArrayList<byte[]>) adj);
                maeCorreoList.add(maeCorreo);
                correoServ.enviar(maeCorreoList);
            }

        } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void GenerarExcel(List<MaeEstadoCuenta> maeEstadoCuentaList2,Date ffsaldo) {
        List<MaeEstadoCuenta> maeEstCtaList=maeEstadoCuentaList2;
        
        MaeFondo fondonew = new MaeFondo();
        fondonew.setCFondoId(maeEstCtaList.get(0).getFondo().getCFondoId());
        MaeInversion maeInversionnew = new MaeInversion();
        maeInversionnew.setCInversion(maeEstCtaList.get(0).getCinversion() );
        maeInversionnew.setcMaeInversionId(maeEstCtaList.get(0).getMaeInversion().getcMaeInversionId());
        maeInversionnew.setMaeFondo(fondonew);
        
        try {
            maeInversionList =inversionServ.listarLegal(maeInversionnew);
            System.out.println("acaesta"+maeInversionList.size());
            Number ncuotas= maeInversionList.get(0).getNMeses();
            String ls_nombre="Titular: ";
            String ls_direccion="";
            float ln_tasa=0;
            float ln_tasaMor=0;
            Number ln_cuotas=0.00;
            Number ln_cuotasPEN=0;
            Number ln_montodesem=0.00;
            Number ln_montopen=0.00; 
            Date  dfechades= null;
            Date  dfechaPAGO= null;
            double monto_interes;     
            double monto_mora;  
            double SumaTotal ;
            BigDecimal ln_tasa1 ;
            BigDecimal ln_tasaMor1; 
            for (int i = 0; i < maeInversionList.size(); i++) {
                  if (i==maeInversionList.size()-1){
                        ls_nombre =ls_nombre +maeInversionList.get(i).getcPersonaId().getDApePat()+ " " + maeInversionList.get(i).getcPersonaId().getDApeMat()+ " " + maeInversionList.get(i).getcPersonaId().getDNombres() ;
                  }else{
                        ls_nombre =ls_nombre +maeInversionList.get(i).getcPersonaId().getDApePat()+ " " + maeInversionList.get(i).getcPersonaId().getDApeMat()+ " " + maeInversionList.get(i).getcPersonaId().getDNombres() + " y ";
                  }
                  ls_direccion="Direccion: " +maeInversionList.get(i).getMaeInmueble().getDDir1();
                  

                  ln_tasa= maeInversionList.get(i).getPTasa().floatValue() ;
                  
                  //ln_tasa1 = new BigDecimal(ln_tasa);
                  //ln_tasa1 = ln_tasa1.setScale(1, RoundingMode.UP);
                  //ln_tasa=ln_tasa1.doubleValue();
                  
                  //ln_tasaMor=ln_tasa*0.15;
                  ln_tasaMor=maeInversionList.get(i).getpTasaMor().floatValue() ;
                  
                 // ln_tasaMor1 = new BigDecimal(ln_tasaMor);
                  //ln_tasaMor1 = ln_tasaMor1.setScale(3, RoundingMode.UP);
                  //ln_tasaMor =ln_tasaMor1.doubleValue();
                  
                  ln_cuotas=maeInversionList.get(i).getNMeses();
                  ln_montodesem=maeInversionList.get(i).getiCronograma();
                  dfechades = maeInversionList.get(i).getFEmision();
                  ln_cuotasPEN = maeInversionList.get(i).getnTOTALCUOTAS();
                  dfechaPAGO=maeInversionList.get(i).getFVencimiento();
                  ln_montopen=maeInversionList.get(i).getnCUOTASPENDXGENERAR();
            }
            
              
 
                    double dias=(double) ((ffsaldo.getTime()-dfechaPAGO.getTime())/86400000);
                    
                    // CALCULO DE INTERES
                    
                    monto_interes=  1 + (ln_tasa / 100.00);
                    //BigDecimal montoInt = new BigDecimal(monto_interes);
                    //montoInt = montoInt.setScale(4, RoundingMode.UP);
                    
                    BigDecimal datos = new BigDecimal(dias/365.00);
                    datos = datos.setScale(6, RoundingMode.UP);
                    //monto_interes =   (Math.pow(montoInt.doubleValue() ,datos.doubleValue())-1) * ln_montopen.doubleValue() ;  
                    
                    monto_interes =   (Math.pow(monto_interes ,datos.doubleValue())-1) * ln_montopen.doubleValue() ;  
                    
                    BigDecimal datosiNTERES = new BigDecimal(monto_interes);
                    datosiNTERES = datosiNTERES.setScale(2, RoundingMode.UP);
                    
                    
                    
                    // CALCULO DE MORA
                    monto_mora=  1 + (ln_tasaMor / 100.00);
                    //BigDecimal montoMor = new BigDecimal(monto_mora);
                    //montoMor = montoMor.setScale(6, RoundingMode.UP);
                    
                    BigDecimal datosMora = new BigDecimal(dias/365.00);
                    datosMora = datosMora.setScale(10, RoundingMode.UP);
                   // monto_mora =   (Math.pow(montoMor.doubleValue() ,datosMora.doubleValue())-1) * ln_montopen.doubleValue() ;  
                    monto_mora =   (Math.pow(monto_mora ,datosMora.doubleValue())-1) * ln_montopen.doubleValue() ;  
                    
                    BigDecimal datosMoraFinal = new BigDecimal(monto_mora);
                    datosMoraFinal= datosMoraFinal.setScale(2, RoundingMode.UP);
                    
                    SumaTotal = ln_montopen.doubleValue()+datosiNTERES.doubleValue()+datosMoraFinal.doubleValue();
                    BigDecimal datosFinal = new BigDecimal(SumaTotal);
                    datosFinal= datosFinal.setScale(2, RoundingMode.UP);
                
                ls_nombre= ls_nombre + " Codigointerno " + maeEstCtaList.get(0).getCinversion();
                    // Creamos el archivo donde almacenaremos la hoja
                // de calculo, recuerde usar la extension correcta,
                // en este caso .xlsx
                // String ruta_conf=properties.getRuta_conf();
                 String ruta_conf="C:\\TEMP\\";
                File archivo = new File(ruta_conf+"SaldoDeudor.xlsx");

                // Creamos el libro de trabajo de Excel formato OOXML
                Workbook workbook = new XSSFWorkbook();
                DataFormat format = workbook.createDataFormat();
                // La hoja donde pondremos los datos
                Sheet pagina = workbook.createSheet("SaldoDeudor");


                // Creamos el estilo paga las celdas del encabezado
                CellStyle style = workbook.createCellStyle();
                // Indicamos que tendra un fondo azul aqua
                // con patron solido del color indicado

                int FilRow=0;    
                Calendar cal = Calendar.getInstance(); 
                cal.setTime(ffsaldo); 
                cal.add(Calendar.DAY_OF_MONTH,0); 
                Date FechaAnterior =  cal.getTime();
                style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setAlignment(HorizontalAlignment.CENTER);
                Row fila = pagina.createRow(0);
                
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)14);
                style.setFont(font);

                
                style.setBorderBottom(BorderStyle.DOUBLE);
                style.setBorderLeft(BorderStyle.DOUBLE);
                style.setBorderRight(BorderStyle.DOUBLE);
                style.setBorderTop(BorderStyle.DOUBLE);
                style.setAlignment(HorizontalAlignment.CENTER);
                
    
                Cell celda = null;
                for (int i = 0; i <= 8; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                        celda.setCellValue("Estado de Cuenta de Saldo Deudor al " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                    } 
                }

                
                
                //celda.setCellStyle(style);
               // celda.setCellValue("Estado de Cuenta de Saldo Deudor al " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                celda.getSheet().addMergedRegion(new CellRangeAddress(0,0,0,8));
                FilRow=FilRow+1;    
                

                style = workbook.createCellStyle();
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);

                  
                // Creamos el encabezado
                for (int i = 2; i < 10; i++) {
                    // Creamos una celda en esa fila, en la posicion 
                    // indicada por el contador del ciclo
                      fila = pagina.createRow(i);  
                      FilRow= i;
                      for (int e = 0; e < 10; e++) {
                    // Creamos una celda en esa fila, en la
                    // posicion indicada por el contador del ciclo
                        style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        font = workbook.createFont();
                        font.setBold(false);
                        if (e==1 && i== 2){ //Titular
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue(ls_nombre);
                            celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,1,6));
                        }    
                        
                        if (e==1 && i== 3){ //Dirección
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue(ls_direccion);
                            celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,1,6));
                        }   
                        
                        if (e==1 && i== 5){ //Tasa compensatoria
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Tasa compensatoria anual:");
                        } 
                        
                        if (e==2 && i== 5){ //Tasa compensatoria
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            DecimalFormat df = new DecimalFormat("#.##");
                            df.setRoundingMode(RoundingMode.UP);
                            celda.setCellValue(df.format(ln_tasa)+"%");                            
                        }  
                        
                        if (e==5 && i== 5){ //Tasa moratoria
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                            
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Monto desembolsado:");
                        }  
                        
                        if (e==6 && i== 5){ //Tasa moratoria
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellValue( ln_montodesem.doubleValue() );
                        }  
                        
                         if (e==1 && i== 6){ //Tasa moratoria anual
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                             
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Tasa moratoria anual:");
                        } 
                        
                        if (e==2 && i== 6){ //Tasa moratoria anual
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            DecimalFormat df = new DecimalFormat("#.##");
                            df.setRoundingMode(RoundingMode.UP);
                            celda.setCellValue(df.format(ln_tasaMor)+"%");                            
                        }  
                        
                        if (e==5 && i== 6){ //Fecha de desembolso
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                            
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Fecha de desembolso:");
                        }  
                        
                        if (e==6 && i== 6){ //Fecha de desembolso
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(dfechades);
                            celda.setCellValue(xfecha);
                        }  
                        
                        if (e==1 && i== 7){ //Cuotas pagadas
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                            
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Cuotas pagadas:");
                        } 
                        
                        if (e==2 && i== 7){ //Cuotas pagadas
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue(ln_cuotasPEN.intValue() + " de " + ln_cuotas.toString());                            
                        }  
                        
                    }
                }
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());   
                font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);

                String[] titulos = { "Fecha  Pago",
                    "Fecha en que pago", "N° Cuota","Descripción ", "Capital","Interes Compensatorio","Cargo","Abono","Saldo"};



                 fila = pagina.createRow(10);
                 FilRow=FilRow+1;
                // Creamos el encabezado
                for (int i = 0; i < titulos.length; i++) {
                    // Creamos una celda en esa fila, en la posicion 
                    // indicada por el contador del ciclo
                      celda = fila.createCell(i);

                    // Indicamos el estilo que deseamos 
                    // usar en la celda, en este caso el unico 
                    // que hemos creado
                    style.setBorderBottom(BorderStyle.DOUBLE);
                    style.setBorderLeft(BorderStyle.DOUBLE);
                    style.setBorderRight(BorderStyle.DOUBLE);
                    style.setBorderTop(BorderStyle.DOUBLE);                    
                    style.setAlignment(HorizontalAlignment.CENTER);
                    celda.setCellStyle(style);
                    celda.setCellValue(titulos[i]);
                }


                // Ahora creamos una fila en la posicion 1


                // Y colocamos los datos en esa fila
                for (int i = 0; i < maeEstCtaList.size(); i++) {
                    fila = pagina.createRow(i+11); 
                    FilRow=FilRow+1;    
                    for (int e = 0; e < 10; e++) {
                    // Creamos una celda en esa fila, en la
                    // posicion indicada por el contador del ciclo
                    style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    font = workbook.createFont();
                    font.setBold(false);
                    celda = fila.createCell(e);

                        if (e==0){ //Fecha de Pago
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Cargo")) {
                             String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                            }
                        }  
                        if (e==1){//Fecha en que pago
                             if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                             String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                                }
                           }
                        if (e==2){//cuota
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Cargo")) {
                            celda.setCellStyle(style);  
                            celda.setCellValue(maeEstCtaList.get(i).getNcuota() + " de " + ncuotas.toString());
                            }
                        }
                        if (e==3){//Desc.Ope
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }
                            celda.setCellStyle(style);    
                            celda.setCellValue(maeEstCtaList.get(i).getDdescriocion());
                        }
                        if (e==4){//Capital
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);             
                                style.setFont(font);
                            }
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIcapital().doubleValue());
                        }
                        if (e==5){//Interes Compensatorio
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);   
                                style.setFont(font);
                            }
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIinteres().doubleValue());
                        }
                        
                        if (e==6){//Cargo
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }                       
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);                    
                            celda.setCellValue(maeEstCtaList.get(i).getIcargo().doubleValue());
                        }
                        if (e==7){//Abono
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }                    
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIabono().doubleValue());
                        }
                        
                        if (e==8){//Saldo
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }                       
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIsaldo().doubleValue());
                        }
                    }


                }
                FilRow=FilRow+2;
                style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderBottom(BorderStyle.DOUBLE);
                style.setBorderLeft(BorderStyle.DOUBLE);
                style.setBorderRight(BorderStyle.DOUBLE);
                style.setBorderTop(BorderStyle.DOUBLE);
                style.setAlignment(HorizontalAlignment.CENTER);
                                
                fila = pagina.createRow(FilRow);
                celda = fila.createCell(0);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)14);
                style.setFont(font);
                celda.setCellStyle(style);
                cal = Calendar.getInstance(); 
                cal.setTime(ffsaldo); 
                cal.add(Calendar.DAY_OF_MONTH, 0); 
                FechaAnterior =  cal.getTime();
                
                for (int i = 0; i <= 8; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                        celda.setCellValue("Saldo adeudado al: " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                    } 
                }
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,8));
                
                FilRow=FilRow+2;
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)10);
                style.setFont(font);                
                fila = pagina.createRow(FilRow);
                for (int i = 0; i <= 8; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                        style.setAlignment(HorizontalAlignment.LEFT);
                        celda.setCellStyle(style);
                        celda.setCellValue("* Capital adeudado al: (cuotas " + ln_cuotasPEN + ")"  ) ;
                    } 
                      if (i == 4) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                        celda.setCellValue( formatoFecha.format(dfechaPAGO)) ;
                    }
                    if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellValue(ln_montopen.doubleValue()) ;
                    }                      
                   
                  
                }
                
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        
                fila = pagina.createRow(FilRow);
                for (int i = 0; i <= 8; ++i) {
                     font = workbook.createFont();
                    font.setBold(true);
                    font.setFontHeightInPoints((short)10);
                    style.setFont(font);       
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                         DecimalFormat df = new DecimalFormat("#.##");
                        df.setRoundingMode(RoundingMode.DOWN);
                        style.setAlignment(HorizontalAlignment.LEFT);
                        celda.setCellStyle(style);
                        celda.setCellValue("* Interés compensatorio de " + df.format(ln_tasa)+" % al:") ;
                    } 
                    if (i == 4) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                        celda.setCellValue( formatoFecha.format(FechaAnterior)) ;
             
                    }
                    
                     if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellValue(datosiNTERES.doubleValue()) ;
                    }                      
                      
                }
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)10);
                style.setFont(font);                
                fila = pagina.createRow(FilRow);
                for (int i = 0; i <= 8; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                         DecimalFormat df = new DecimalFormat("#.##");
                         df.setRoundingMode(RoundingMode.UP);   
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setAlignment(HorizontalAlignment.LEFT);
                        celda.setCellStyle(style);
                        celda.setCellValue("* Interés moratorio de " + df.format(ln_tasaMor)+" % al:") ;
                    } 
                    
                     if (i == 4) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                        celda.setCellStyle(style);
                        celda.setCellValue( formatoFecha.format(FechaAnterior)) ;
             
                    }
                     
                      
                    if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellStyle(style);
                        celda.setCellValue(datosMoraFinal.doubleValue()) ;
                    }         
                      
                }     
                
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)10);
                style.setFont(font);
                String lsmoneda;              
                fila = pagina.createRow(FilRow);
                 for (int i = 0; i <= 8; ++i) {
                    celda = fila.createCell(i);
                     if (i == 3) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setAlignment(HorizontalAlignment.RIGHT);
                        style.setFont(font);      
                        celda.setCellStyle(style);
                        celda.setCellValue("Monto deuda Total") ;
             
                    }
                     
                      
                    if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellStyle(style);
                        celda.setCellValue(datosFinal.doubleValue()) ;
                    }        
                     if (i == 6) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        celda.setCellStyle(style);
                        if (maeInversionList.size()>0){
                            lsmoneda=maeInversionList.get(0).getCmoneda();
                        }else{
                            lsmoneda="Soles";
                          
                        }   
                        celda.setCellValue(lsmoneda) ;
                    }   
                      
                }     
                 
                
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                

                 for(int columnPosition = 0; columnPosition< 10; columnPosition++) {
                     pagina.autoSizeColumn((short) (columnPosition));
                }

                   // Ahora guardaremos el archivo
                try {
                    // Creamos el flujo de salida de datos,
                    // apuntando al archivo donde queremos 
                    // almacenar el libro de Excel
                    FileOutputStream salida = new FileOutputStream(archivo);

                    // Almacenamos el libro de 
                    // Excel via ese 
                    // flujo de datos
                    workbook.write(salida);

                    // Cerramos el libro para concluir operaciones
                    workbook.close();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     
                    String filePath = ruta_conf+"SaldoDeudor.xlsx";

                    byte[] asss = Files.readAllBytes(new File(filePath).toPath());
               
                    
                    baos.write(asss);

                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    Object response = context.getExternalContext().getResponse();

                    if (response instanceof HttpServletResponse) {
                        HttpServletResponse hsr = (HttpServletResponse) response;
                        hsr.setContentType("application/vnd.ms-excel");
                        hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                        hsr.setHeader("Content-disposition", "attachment;filename=EstadoCuenta" + "SaldoDeudor.xlsx");
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
                    
                    
                    
                     System.out.println( "Archivo creado existosamente en {0}");   
                     
                } catch (FileNotFoundException ex) {
                     System.out.println( "Archivo no localizable en sistema de archivos");

                } catch (IOException ex) {
                     System.out.println( "Error de entrada/salida");
                }

            
                
                
         } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
     public void GenerarExcelJudi(List<MaeEstadoCuenta> maeEstadoCuentaList2,Date ffsaldo) {
        List<MaeEstadoCuenta> maeEstCtaList=maeEstadoCuentaList2;
        
        MaeFondo fondonew = new MaeFondo();
        fondonew.setCFondoId(maeEstCtaList.get(0).getFondo().getCFondoId());
        MaeInversion maeInversionnew = new MaeInversion();
        maeInversionnew.setCInversion(maeEstCtaList.get(0).getCinversion() );
        maeInversionnew.setFEmision(ffsaldo);
        maeInversionnew.setcMaeInversionId(maeEstCtaList.get(0).getMaeInversion().getcMaeInversionId());
        maeInversionnew.setMaeFondo(fondonew);
        
        try {
            maeInversionList =inversionServ.listarJudi(maeInversionnew);
            Number ncuotas= 0;
            String ls_nombre="Titular: ";
            String ls_direccion="";
            float ln_tasa=0;
            float ln_tasaMor=0;
            Number ln_cuotas=0.00;
            Number ln_cuotasPEN=0;
            Number ln_montodesem=0.00;
            Number ln_montopen=0.00; 
            Date  dfechades= null;
            Date  dfechaPAGO= null;
            Number datosiNTERES=0.00;    
            Number datosMoraFinal=0.00;  
            Number SumaTotal ;
            BigDecimal ln_tasa1 ;
            BigDecimal ln_tasaMor1; 
            for (int i = 0; i < maeInversionList.size(); i++) {
                  if (i==maeInversionList.size()-1){
                        ls_nombre =ls_nombre +maeInversionList.get(i).getcPersonaId().getDApePat()+ " " + maeInversionList.get(i).getcPersonaId().getDApeMat()+ " " + maeInversionList.get(i).getcPersonaId().getDNombres() ;
                  }else{
                        ls_nombre =ls_nombre +maeInversionList.get(i).getcPersonaId().getDApePat()+ " " + maeInversionList.get(i).getcPersonaId().getDApeMat()+ " " + maeInversionList.get(i).getcPersonaId().getDNombres() + " y ";
                  }
                  ls_direccion="Direccion: " +maeInversionList.get(i).getMaeInmueble().getDDir1();
                  

                  ln_tasa= maeInversionList.get(i).getPTasa().floatValue() ;
                  
                  //ln_tasa1 = new BigDecimal(ln_tasa);
                  //ln_tasa1 = ln_tasa1.setScale(1, RoundingMode.UP);
                  //ln_tasa=ln_tasa1.doubleValue();
                  
                  //ln_tasaMor=ln_tasa*0.15;
                  ln_tasaMor=maeInversionList.get(i).getpTasaMor().floatValue() ;
                  
                 // ln_tasaMor1 = new BigDecimal(ln_tasaMor);
                  //ln_tasaMor1 = ln_tasaMor1.setScale(3, RoundingMode.UP);
                  //ln_tasaMor =ln_tasaMor1.doubleValue();
                  
                  ln_cuotas=maeInversionList.get(i).getNMeses();
                  ln_montodesem=maeInversionList.get(i).getIInversion();
                  dfechades = maeInversionList.get(i).getFEmision();
                  ln_cuotasPEN = maeInversionList.get(i).getnTOTALCUOTAS();
                  dfechaPAGO=maeInversionList.get(i).getFVencimiento();
                  ln_montopen=maeInversionList.get(i).getnCUOTASPENDXGENERAR();
                  datosiNTERES = maeInversionList.get(i).getIInteres();
                  datosMoraFinal = maeInversionList.get(i).getiMora();
                  
            }
                    
                SumaTotal = ln_montopen.doubleValue()+datosiNTERES.doubleValue()+datosMoraFinal.doubleValue();
                  
                ls_nombre= ls_nombre + " Codigointerno " + maeEstCtaList.get(0).getCinversion();
                    // Creamos el archivo donde almacenaremos la hoja
                // de calculo, recuerde usar la extension correcta,
                // en este caso .xlsx
                // String ruta_conf=properties.getRuta_conf();
                 String ruta_conf="C:\\TEMP\\";
                File archivo = new File(ruta_conf+"SaldoDeudor.xlsx");

                // Creamos el libro de trabajo de Excel formato OOXML
                Workbook workbook = new XSSFWorkbook();
                DataFormat format = workbook.createDataFormat();
                // La hoja donde pondremos los datos
                Sheet pagina = workbook.createSheet("SaldoDeudor");


                // Creamos el estilo paga las celdas del encabezado
                CellStyle style = workbook.createCellStyle();
                // Indicamos que tendra un fondo azul aqua
                // con patron solido del color indicado

                int FilRow=0;    
                Calendar cal = Calendar.getInstance(); 
                cal.setTime(ffsaldo); 
                cal.add(Calendar.DAY_OF_MONTH,0); 
                Date FechaAnterior =  cal.getTime();
                style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setAlignment(HorizontalAlignment.CENTER);
                Row fila = pagina.createRow(0);
                
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)14);
                style.setFont(font);

                
                style.setBorderBottom(BorderStyle.DOUBLE);
                style.setBorderLeft(BorderStyle.DOUBLE);
                style.setBorderRight(BorderStyle.DOUBLE);
                style.setBorderTop(BorderStyle.DOUBLE);
                style.setAlignment(HorizontalAlignment.CENTER);
                
    
                Cell celda = null;
                for (int i = 0; i <= 9; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                        celda.setCellValue("Estado de Cuenta de Saldo Deudor al " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                    } 
                }

                
                
                //celda.setCellStyle(style);
               // celda.setCellValue("Estado de Cuenta de Saldo Deudor al " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                celda.getSheet().addMergedRegion(new CellRangeAddress(0,0,0,9));
                FilRow=FilRow+1;    
                

                style = workbook.createCellStyle();
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);

                  
                // Creamos el encabezado
                for (int i = 2; i < 10; i++) {
                    // Creamos una celda en esa fila, en la posicion 
                    // indicada por el contador del ciclo
                      fila = pagina.createRow(i);  
                      FilRow= i;
                      for (int e = 0; e < 10; e++) {
                    // Creamos una celda en esa fila, en la
                    // posicion indicada por el contador del ciclo
                        style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        font = workbook.createFont();
                        font.setBold(false);
                        if (e==1 && i== 2){ //Titular
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue(ls_nombre);
                            celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,1,6));
                        }    
                        
                        if (e==1 && i== 3){ //Dirección
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue(ls_direccion);
                            celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,1,6));
                        }   
                        
                        if (e==1 && i== 5){ //Tasa compensatoria
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Tasa compensatoria anual:");
                        } 
                        
                        if (e==2 && i== 5){ //Tasa compensatoria
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            DecimalFormat df = new DecimalFormat("#.##");
                            df.setRoundingMode(RoundingMode.UP);
                            celda.setCellValue(df.format(ln_tasa)+"%");                            
                        }  
                        
                        if (e==5 && i== 5){ //Tasa moratoria
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                            
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Monto desembolsado:");
                        }  
                        
                        if (e==6 && i== 5){ //Tasa moratoria
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellValue( ln_montodesem.doubleValue() );
                        }  
                        
                         if (e==1 && i== 6){ //Tasa moratoria anual
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                             
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Tasa moratoria anual:");
                        } 
                        
                        if (e==2 && i== 6){ //Tasa moratoria anual
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            DecimalFormat df = new DecimalFormat("#.##");
                            df.setRoundingMode(RoundingMode.UP);
                            celda.setCellValue(df.format(ln_tasaMor)+"%");                            
                        }  
                        
                        if (e==5 && i== 6){ //Fecha de desembolso
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                            
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Fecha de desembolso:");
                        }  
                        
                        if (e==6 && i== 6){ //Fecha de desembolso
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(dfechades);
                            celda.setCellValue(xfecha);
                        }  
                        
                        if (e==1 && i== 7){ //Estado
                              font = workbook.createFont();
                              font.setBold(true);
                              font.setFontHeightInPoints((short)10);
                              style.setFont(font);                            
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Estado");
                        } 
                        
                        if (e==2 && i== 7){ //Cuotas pagadas
                            celda = fila.createCell(e);
                            celda.setCellStyle(style);
                            celda.setCellValue("Judicial");                            
                        }  
                        
                    }
                }
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());   
                font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);

                String[] titulos = { "Fecha  Pago",
                    "Fecha en que pago", "N° Cuota","Descripción ", "Capital","Interes Compensatorio","Mora","Cargo","Abono","Saldo"};



                 fila = pagina.createRow(10);
                 FilRow=FilRow+1;
                // Creamos el encabezado
                for (int i = 0; i < titulos.length; i++) {
                    // Creamos una celda en esa fila, en la posicion 
                    // indicada por el contador del ciclo
                      celda = fila.createCell(i);

                    // Indicamos el estilo que deseamos 
                    // usar en la celda, en este caso el unico 
                    // que hemos creado
                    style.setBorderBottom(BorderStyle.DOUBLE);
                    style.setBorderLeft(BorderStyle.DOUBLE);
                    style.setBorderRight(BorderStyle.DOUBLE);
                    style.setBorderTop(BorderStyle.DOUBLE);                    
                    style.setAlignment(HorizontalAlignment.CENTER);
                    celda.setCellStyle(style);
                    celda.setCellValue(titulos[i]);
                }


                // Ahora creamos una fila en la posicion 1


                // Y colocamos los datos en esa fila
                for (int i = 0; i < maeEstCtaList.size(); i++) {
                    fila = pagina.createRow(i+11); 
                    FilRow=FilRow+1;    
                    for (int e = 0; e < 11; e++) {
                    // Creamos una celda en esa fila, en la
                    // posicion indicada por el contador del ciclo
                    style = workbook.createCellStyle();
                    if (maeEstCtaList.get(i).getCconceptoId().equals("0008") || maeEstCtaList.get(i).getCconceptoId().equals("0009")){
                        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE .getIndex());              
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    }else{
                        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    }
                    font = workbook.createFont();
                    font.setBold(false);
                    celda = fila.createCell(e);
                    
                        if (e==0){ //Fecha de Pago
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Cargo"))  {
                             String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                            }
                            
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deudor"))  {
                             String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                            }
                            
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Atrasado"))  {
                             String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                            }
                            
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Int.mor.fec"))  {
                              String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                            }
                            if (maeEstCtaList.get(i).getCconceptoId().equals("0008") || maeEstCtaList.get(i).getCconceptoId().equals("0009"))  {
                              String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                            }
                             
                        }  
                        if (e==1){//Fecha en que pago
                             if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                             String xfecha=new SimpleDateFormat("dd/MM/yyyy").format(maeEstCtaList.get(i).getFoperacion());
                              celda.setCellValue(xfecha);
                                }
                           }
                        if (e==2){//cuota
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Cargo")) {
                            celda.setCellStyle(style);  
                            celda.setCellValue(maeEstCtaList.get(i).getNcuota() + " de " + ln_cuotas.toString());
                            }
                        }
                        if (e==3){//Desc.Ope
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }
                            celda.setCellStyle(style);    
                            celda.setCellValue(maeEstCtaList.get(i).getDdescriocion());
                        }
                        if (e==4){//Capital
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);             
                                style.setFont(font);
                            }
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIcapital().doubleValue());
                        }
                        if (e==5){//Interes Compensatorio
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);   
                                style.setFont(font);
                            }
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIinteres().doubleValue());
                        }
                        
                        if (e==6){//Mora
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);   
                                style.setFont(font);
                            }
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getImora().doubleValue());
                        }
                        
                        if (e==7){//Cargo
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }                       
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);                    
                            celda.setCellValue(maeEstCtaList.get(i).getIcargo().doubleValue());
                        }
                        if (e==8){//Abono
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                            }                    
                            style.setDataFormat(format.getFormat("########,###.00"));
                            celda.setCellStyle(style);
                            celda.setCellValue(maeEstCtaList.get(i).getIabono().doubleValue());
                        }
                        
                        if (e==9){//Saldo
                            if (maeEstCtaList.get(i).getDdescriocion().contains("Deposito")) {
                                style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());              
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                font = workbook.createFont();
                                font.setBold(true);
                                style.setFont(font);
                                    
                            }
                                    style.setDataFormat(format.getFormat("########,###.00"));
                                    celda.setCellStyle(style);
                                    celda.setCellValue(maeEstCtaList.get(i).getIsaldo().doubleValue());
                            
                        }
                    }
 
                }
               
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fila = pagina.createRow(FilRow);
                celda = fila.createCell(0);
                font = workbook.createFont();
                font.setBold(false);
                font.setFontHeightInPoints((short)10);
                
                FilRow=FilRow+2;
                fila = pagina.createRow(FilRow);
                style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderBottom(BorderStyle.DOUBLE);
                style.setBorderLeft(BorderStyle.DOUBLE);
                style.setBorderRight(BorderStyle.DOUBLE);
                style.setBorderTop(BorderStyle.DOUBLE);
                style.setAlignment(HorizontalAlignment.CENTER);
                                
                
                celda = fila.createCell(0);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)14);
                style.setFont(font);
                celda.setCellStyle(style);
                cal = Calendar.getInstance(); 
                cal.setTime(ffsaldo); 
                cal.add(Calendar.DAY_OF_MONTH, 0); 
                FechaAnterior =  cal.getTime();
                
                for (int i = 0; i <= 9; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                        celda.setCellValue("Saldo adeudado al: " +  new SimpleDateFormat("dd/MM/yyyy").format(FechaAnterior)) ;
                    } 
                }
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,9));
                
                FilRow=FilRow+2;
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)10);
                style.setFont(font);                
                fila = pagina.createRow(FilRow);
                for (int i = 0; i <= 9; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                        style.setAlignment(HorizontalAlignment.LEFT);
                        celda.setCellStyle(style);
                        celda.setCellValue("* Capital adeudado al: "  ) ;
                    } 
                      if (i == 4) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                         SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                        celda.setCellValue( formatoFecha.format(FechaAnterior)) ;
                    }
                    if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellValue(ln_montopen.doubleValue()) ;
                    }                      
                   
                  
                }
                
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        
                fila = pagina.createRow(FilRow);
                for (int i = 0; i <= 9; ++i) {
                     font = workbook.createFont();
                    font.setBold(true);
                    font.setFontHeightInPoints((short)10);
                    style.setFont(font);       
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                         DecimalFormat df = new DecimalFormat("#.##");
                        df.setRoundingMode(RoundingMode.DOWN);
                        style.setAlignment(HorizontalAlignment.LEFT);
                        celda.setCellStyle(style);
                        celda.setCellValue("* Interés compensatorio de " + df.format(ln_tasa)+" % al:") ;
                    } 
                    if (i == 4) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                        celda.setCellValue( formatoFecha.format(FechaAnterior)) ;
             
                    }
                    
                     if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellValue(datosiNTERES.doubleValue()) ;
                    }                      
                      
                }
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)10);
                style.setFont(font);                
                fila = pagina.createRow(FilRow);
                for (int i = 0; i <= 9; ++i) {
                    celda = fila.createCell(i);
                    celda.setCellStyle(style);
                    if (i == 0) {
                         DecimalFormat df = new DecimalFormat("#.##");
                         df.setRoundingMode(RoundingMode.UP);   
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setAlignment(HorizontalAlignment.LEFT);
                        celda.setCellStyle(style);
                        celda.setCellValue("* Interés moratorio de " + df.format(ln_tasaMor)+" % al:") ;
                    } 
                    
                     if (i == 4) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
                        celda.setCellStyle(style);
                        celda.setCellValue( formatoFecha.format(FechaAnterior)) ;
             
                    }
                     
                      
                    if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));
                        celda.setCellStyle(style);
                        celda.setCellValue(datosMoraFinal.doubleValue()) ;
                    }         
                      
                }     
                
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                FilRow=FilRow+1;
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());              
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                font = workbook.createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short)10);
                style.setFont(font);                
                fila = pagina.createRow(FilRow);
                String lsmoneda;
                 for (int i = 0; i <= 9; ++i) {
                    celda = fila.createCell(i);
                     if (i == 3) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setAlignment(HorizontalAlignment.RIGHT);
                        style.setFont(font);      
                        celda.setCellStyle(style);
                        celda.setCellValue("Monto deuda Total") ;
             
                    }
                     
                      
                    if (i == 5) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        style.setDataFormat(format.getFormat("########,###.00"));   
                        celda.setCellStyle(style);
                        celda.setCellValue(SumaTotal.doubleValue()) ;
                    }        
                     if (i == 6) {
                        font.setBold(false);
                        font.setFontHeightInPoints((short)10);
                        style.setFont(font);       
                        celda.setCellStyle(style);
                        if (maeInversionList.size()>0){
                            lsmoneda=maeInversionList.get(0).getCmoneda();
                        }else{
                            lsmoneda="Soles";
                            
                        }   
                        celda.setCellValue(lsmoneda) ;
                     }
                      
                }     
                 
                
                celda.getSheet().addMergedRegion(new CellRangeAddress(FilRow,FilRow,0,1));
                

                 for(int columnPosition = 0; columnPosition< 10; columnPosition++) {
                     pagina.autoSizeColumn((short) (columnPosition));
                }

                   // Ahora guardaremos el archivo
                try {
                    // Creamos el flujo de salida de datos,
                    // apuntando al archivo donde queremos 
                    // almacenar el libro de Excel
                    FileOutputStream salida = new FileOutputStream(archivo);

                    // Almacenamos el libro de 
                    // Excel via ese 
                    // flujo de datos
                    workbook.write(salida);

                    // Cerramos el libro para concluir operaciones
                    workbook.close();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     
                    String filePath = ruta_conf+"SaldoDeudor.xlsx";

                    byte[] asss = Files.readAllBytes(new File(filePath).toPath());
               
                    
                    baos.write(asss);

                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    Object response = context.getExternalContext().getResponse();

                    if (response instanceof HttpServletResponse) {
                        HttpServletResponse hsr = (HttpServletResponse) response;
                        hsr.setContentType("application/vnd.ms-excel");
                        hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                        hsr.setHeader("Content-disposition", "attachment;filename=EstadoCuenta" + "SaldoDeudor.xlsx");
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
                    
                    
                    
                     System.out.println( "Archivo creado existosamente en {0}");   
                     
                } catch (FileNotFoundException ex) {
                     System.out.println( "Archivo no localizable en sistema de archivos");

                } catch (IOException ex) {
                     System.out.println( "Error de entrada/salida");
                }

            
                
                
         } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
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

    public MaeEstadoCuenta getMaeEstadoCuenta() {
        return maeEstadoCuenta;
    }

    public void setMaeEstadoCuenta(MaeEstadoCuenta maeEstadoCuenta) {
        this.maeEstadoCuenta = maeEstadoCuenta;
    }

    public List<MaeEstadoCuenta> getMaeEstadoCuentaList() {
        return maeEstadoCuentaList;
    }

    public void setMaeEstadoCuentaList(List<MaeEstadoCuenta> maeEstadoCuentaList) {
        this.maeEstadoCuentaList = maeEstadoCuentaList;
    }

    public List<CobTchn> getCobTchnList() {
        return cobTchnList;
    }

    public void setCobTchnList(List<CobTchn> cobTchnList) {
        this.cobTchnList = cobTchnList;
    }

    public CobTchn getCobTchn() {
        return cobTchn;
    }

    public void setCobTchn(CobTchn cobTchn) {
        this.cobTchn = cobTchn;
    }

    public EstadoCuentaServ getEstadoCuentaServ() {
        return estadoCuentaServ;
    }

    public void setEstadoCuentaServ(EstadoCuentaServ estadoCuentaServ) {
        this.estadoCuentaServ = estadoCuentaServ;
    }

    public List<MaeEstadoCuenta> getMaeEstadoCuentaBList() {
        return maeEstadoCuentaBList;
    }

    public void setMaeEstadoCuentaBList(List<MaeEstadoCuenta> maeEstadoCuentaBList) {
        this.maeEstadoCuentaBList = maeEstadoCuentaBList;
    }

    public List<MaeDeposito> getMaeEstadoCuentaCList() {
        return maeEstadoCuentaCList;
    }

    public void setMaeEstadoCuentaCList(List<MaeDeposito> maeEstadoCuentaCList) {
        this.maeEstadoCuentaCList = maeEstadoCuentaCList;
    }

    public IRepEstadoCuentaServ getRepEstadoCuentaServ() {
        return repEstadoCuentaServ;
    }

    public void setRepEstadoCuentaServ(IRepEstadoCuentaServ repEstadoCuentaServ) {
        this.repEstadoCuentaServ = repEstadoCuentaServ;
    }

    public MaeInversion getMaeInversionP() {
        return maeInversionP;
    }

    public void setMaeInversionP(MaeInversion maeInversionP) {
        this.maeInversionP = maeInversionP;
    }

    public ICorreoServ getCorreoServ() {
        return correoServ;
    }

    public void setCorreoServ(ICorreoServ correoServ) {
        this.correoServ = correoServ;
    }

    public IEmailServ getEmailServ() {
        return emailServ;
    }

    public void setEmailServ(IEmailServ emailServ) {
        this.emailServ = emailServ;
    }

    public IRepSaldoDeudorServ getRepSaldoDeudorServ() {
        return repSaldoDeudorServ;
    }

    public void setRepSaldoDeudorServ(IRepSaldoDeudorServ repSaldoDeudorServ) {
        this.repSaldoDeudorServ = repSaldoDeudorServ;
    }

    public IInversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(IInversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    public MaeUbigeo getMaeUbigeoP() {
        return maeUbigeoP;
    }

    public void setMaeUbigeoP(MaeUbigeo maeUbigeoP) {
        this.maeUbigeoP = maeUbigeoP;
    }

    public MaeUbigeo getMaeUbigeoD() {
        return maeUbigeoD;
    }

    public void setMaeUbigeoD(MaeUbigeo maeUbigeoD) {
        this.maeUbigeoD = maeUbigeoD;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
    }

    /**
     * @return the iShowButtonsLiqJudic
     */
    public int getiShowButtonsLiqJudic() {
        return iShowButtonsLiqJudic;
    }

    /**
     * @param iShowButtonsLiqJudic the iShowButtonsLiqJudic to set
     */
    public void setiShowButtonsLiqJudic(int iShowButtonsLiqJudic) {
        this.iShowButtonsLiqJudic = iShowButtonsLiqJudic;
    }
    
    public List<MaeEstadoCuenta> getMaeEstadoCuentaDList() {
        return maeEstadoCuentaDList;
    }

    public void setMaeEstadoCuentaDList(List<MaeEstadoCuenta> maeEstadoCuentaDList) {
        this.maeEstadoCuentaDList = maeEstadoCuentaDList;
    }
}