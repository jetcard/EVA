/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.CobTablas;
import pop.comun.dominio.MaeCuotaPago;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeHipoteca;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.TabArchivo;
import pop.comun.dominio.TabDocumentos;
import pop.comun.dominio.TabDocumentosDet;
import pop.comun.dominio.TabTipoDocumento;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IArchivoServ;
import pop.webcobranzas.iface.ICuotaPagoServ;
import pop.webcobranzas.iface.IDocumentosDetServ;
import pop.webcobranzas.iface.IDocumentosServ;
import pop.webcobranzas.iface.IHipotecaServ;
import pop.webcobranzas.iface.IInmuebleServ;
import pop.webcobranzas.iface.IInversionServ;
import pop.webcobranzas.iface.IPersonaServ;
import pop.webcobranzas.iface.ITablasServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.ifacerep.IArcDocumentoServ;
import pop.webcobranzas.servicio.ArchivoServ;
import pop.webcobranzas.servicio.CuotaPagoServ;
import pop.webcobranzas.servicio.DocumentosDetServ;
import pop.webcobranzas.servicio.DocumentosServ;
import pop.webcobranzas.servicio.HipotecaServ;
import pop.webcobranzas.servicio.InmuebleServ;
import pop.webcobranzas.servicio.InversionServ;
import pop.webcobranzas.servicio.PersonaServ;
import pop.webcobranzas.servicio.TablasServ;
import pop.webcobranzas.servicio.UbigeoServ;
import pop.webcobranzas.serviciorep.ArcDocumentoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class DocumentoDetBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private FileUploadBean fileUploadBean;

    // detalle de documento
    private TabDocumentosDet tabDocumentosDet = new TabDocumentosDet();
    // detalle de documento visualizar
    private TabDocumentosDet tabDocumentosDetV = new TabDocumentosDet();
    // fondo 
    private MaeFondo maeFondo = new MaeFondo();
    // inversion busqueda
    private MaeInversion maeInversion = new MaeInversion();
    // documento
    private TabDocumentos tabDocumentos = new TabDocumentos();
    // inmueble
    private MaeInmueble maeInmueble = new MaeInmueble();
    // ubigeo
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    // hipoteca
    private MaeHipoteca maeHipoteca = new MaeHipoteca();
    // ubigeo Provincia
    private MaeUbigeo maeUbigeoP = new MaeUbigeo();
    // ubigeo Distrito
    private MaeUbigeo maeUbigeoD = new MaeUbigeo();
    // sede de la hipoteca
    private CobTablas csede = new CobTablas();
    // sede tchn de la hipoteca
    private CobTablas csedeTCHN = new CobTablas();
    // sede para la busqueda de lista
    private CobTablas cobTablasSede = new CobTablas();
    // cargo para la busqueda de lista
    private CobTablas cobTablasCargo = new CobTablas();
    // cuotaPago realizar formulas con fecha
    private MaeCuotaPago cuotaPagoFechaCorte = new MaeCuotaPago();

    // lista de documentos detalle
    private List<TabDocumentosDet> tabDocumentoDetList;
    // lista de documentos cabecera
    private List<TabDocumentos> tabDocumentosCabList;
    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de sede
    private List<CobTablas> cobTablasSedeList;
    // lista de accion cargo
    private List<CobTablas> cobTablasCargoList;

    // servicios de documentos
    private IDocumentosDetServ documentosDetServ = new DocumentosDetServ();
    // servicios de documentos
    private IDocumentosServ documentosServ = new DocumentosServ();
    // servicios de archivos
    private IArcDocumentoServ arcDocumentoServ = new ArcDocumentoServ();
    // servicios de inversion
    private IInversionServ inversionServ = new InversionServ();
    // servicios de archivo
    private IArchivoServ archivoServ = new ArchivoServ();
    // servicios de inmueble
    private IInmuebleServ inmuebleServ = new InmuebleServ();
    // servicios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de hipoteca
    private IHipotecaServ hipotecaServ = new HipotecaServ();
    // sericios de tablas
    private ITablasServ tablasServ = new TablasServ();
    // sericios de tablas
    private IPersonaServ personaServ = new PersonaServ();
    // servicios de pago
    private ICuotaPagoServ cuotaPagoServ = new CuotaPagoServ();

    // variables
    private Date fechaCorte;
    private Date fechaDemanda;
    private String observaciones;
    private String codcargo;// accion
    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    // 
    private String cmaeUbigeoProv = "";
    private String cmaeUbigeoDist = "";

    /**
     * Creates a new instance of DocumentoDetBean
     */
    public DocumentoDetBean() {
        maeInmueble.setMaeUbigeo(maeUbigeo);
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setMaeInmueble(maeInmueble);
        tabDocumentos.setMaeInversion(maeInversion);
        tabDocumentosDet.setTabDocumentos(tabDocumentos);
        maeHipoteca.setCsede(csede);
        maeHipoteca.setCsedeTchn(csedeTCHN);
    }

    public void documentoDetalle(MaeInversion oInversion) {
        System.out.println("documentoDetalle");
        showMsg = false;

        maeInversion.setMaeFondo(oInversion.getMaeFondo());
        maeInversion.setcMaeInversionId(oInversion.getcMaeInversionId());
        maeInversion.setcTipoInv(oInversion.getcTipoInv());
        maeInversion.setCInversion(oInversion.getCInversion());
        maeInversion.setCcodigoIdent(oInversion.getCcodigoIdent());
        maeInversion.setcPersonaId(oInversion.getcPersonaId());
        maeInversion.setNCuotasAtrasadas(oInversion.getNCuotasAtrasadas());
        maeInversion.setcInversionId(oInversion.getcInversionId());
        maeInversion.setNVencimientoDia(oInversion.getNVencimientoDia());

        if (oInversion.getMaeInmueble() != null) {
            maeInmueble.setCInmuebleId(oInversion.getMaeInmueble().getCInmuebleId());
            maeInmueble.setDDir1(oInversion.getMaeInmueble().getDDir1());
        }

        try {
            // obtener el ultimo estado
            tabDocumentosDetV = this.getDocumentosDetServ().buscarSolo(tabDocumentosDet);
            TabTipoDocumento tipoDocumento = new TabTipoDocumento();
            tabDocumentos.setTabTipoDocumento(tipoDocumento);
            tabDocumentosCabList = this.getDocumentosServ().listarDocumentosDet(tabDocumentos);
        } catch (Exception ex) {
            //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

       System.out.println("pop.webcobranzas.bean.DocumentoDetBean.documentoDetalle()" + new Date());
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
           System.out.println("pop.webcobranzas.bean.InversionBean.listarDistrito()");
            ubigeoDist = getUbigeoServ().listarDistrito(maeUbigeoP);
           System.out.println(" cantidad de Distritos -> " + ubigeoDist.size());
        } catch (Exception e) {
        }
    }

    public void listarSede() {
        try {
            if (getCobTablasSedeList() == null) {
                cobTablasSede.setCtablaId("0609");
                cobTablasSedeList = getTablasServ().listarTablas(cobTablasSede);
            }
           System.out.println(" cantidad de sedes -> " + cobTablasSedeList.size());
        } catch (Exception e) {

        }
    }

    public void listarCargo() {
        try {
            if (getCobTablasCargoList() == null) {
                cobTablasCargo.setCtablaId("0610");
                cobTablasCargoList = getTablasServ().listarTablas(cobTablasCargo);
            }
           System.out.println(" cantidad de cargos -> " + cobTablasCargoList.size());
        } catch (Exception e) {

        }
    }

    public void printPreRep(TabTipoDocumento TabTipoDocumento) {
        try {
            switch (TabTipoDocumento.getCtabTipoDocId()) {
                case 1:
                    printPrePla24H();
                    break;
                case 2:
                    printPreCN();
                    break;
                case 3:
                    printProtesto();
                    break;
                case 5:
                    printPreJudicial();
                    break;
                case 7:
                    printUltAviso();
                    break;
                case 9:
                    printPreNegExtJud();
                    break;
                case 10:
                    printPreNegPreJud();
                    break;
                case 11:
                    printInvNegociacion();
                    break;
            }

        } catch (Exception ex) {
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void printPrePla24H() {
        System.out.println("printPrePla24H");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();

            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            oInversionSend.setfIniBusq(fechaCorte);

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.prePlazo24H(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "PrePla24H_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "PrePla24H_" + name + ".docx" + "\"");
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

    public void printPreJudicial() {
        System.out.println("printPreJudicial");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.preJudicial(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "PreJudicial_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "PreJudicial_" + name + ".docx" + "\"");
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

    public void printProtesto() {
        System.out.println("printProtesto");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.preProtesto(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "Protesto_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "Protesto_" + name + ".docx" + "\"");
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

    public void printUltAviso() {
        System.out.println("printUltAviso");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.preUltAviso(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "Protesto_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "UltAviso_" + name + ".docx" + "\"");
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

    public void printPreNegExtJud() {
        System.out.println("printPreNegExtJud");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);

            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            oInversionSend.setfIniBusq(fechaCorte);

            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(oInversionSend);
            oInversionSend.setRepSaldoDeudor(oSaldoDeudor);

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.preNegExtJudicial(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "PrePla24H_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "NegExtJudi_" + name + ".docx" + "\"");
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

    public void printPreNegPreJud() {
        System.out.println("printPreNegPreJud");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setcInversionId(maeInversion.getcInversionId());
            oInversionSend.setcTipoInv(maeInversion.getcTipoInv());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);

            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setfFinBusq(fechaDemanda);
            // saldo deudor
            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(oInversionSend);
            oInversionSend.setRepSaldoDeudor(oSaldoDeudor);
            // estado de cuenta a la fecha de envio
            cuotaPagoFechaCorte.setMaeInversion(oInversionSend);
            cuotaPagoFechaCorte.setfIniBusq(fechaCorte);
            cuotaPagoFechaCorte.setiPendiente(getCuotaPagoServ().calcularCuotaPagoFuturo(cuotaPagoFechaCorte).getiPendiente());
            List<MaeCuotaPago> cuotaList = new ArrayList<>();
            cuotaList.add(cuotaPagoFechaCorte);
            oInversionSend.setMaeCuotaPagoList(cuotaList);
            //
            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.preNegPreJudicial(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "PrePla24H_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "NegPreJudi_" + name + ".docx" + "\"");
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
    
    public void printInvNegociacion() {
        System.out.println("printInvNegociacion");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();

            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            oInversionSend.setfIniBusq(fechaCorte);

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            bPrint = arcDocumentoServ.preInvNegociacion(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "PrePla24H_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "PreInvNeg_" + name + ".docx" + "\"");
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

    public void grabarDocumentoDetalle(TabDocumentosDet oTabDocumentosDet) {
        System.out.println("pop.webcobranzas.bean.DocumentoBean.grabarDocumentoDetalle() -- A ");
        
        try {

            switch (oTabDocumentosDet.getTabDocumentos().getTabTipoDocumento().getCtabTipoDocId()) {
                case 1:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genPl24H(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0005")) {
                        genCN(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0006")) {
                        genPl24H(oTabDocumentosDet);
                    }
                    break;
                case 2:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genPl24H(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0005")) {
                        genCN(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0006")) {
                        genPl24H(oTabDocumentosDet);
                    }
                    break;
                case 3:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genPreProtesto(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0005")) {
                        genCN(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0006")) {
                        genPl24H(oTabDocumentosDet);
                    }
                    break;
                case 5:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genPreJudicial(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0005")) {
                        genCN(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0006")) {
                        genPl24H(oTabDocumentosDet);
                    }
                    break;
                case 7:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genUltAviso(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0005")) {
                        genCN(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0006")) {
                        genPl24H(oTabDocumentosDet);
                    }
                    break;
                case 9:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genPreNegExtJud(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    }
                    break;
                case 10:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0006")) {
                        genPreNegPreJud(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    }
                    break;
                case 11:
                    if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0002")) {
                        genInvNegociacion(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0003")) {
                        genTipB(oTabDocumentosDet);
                    } else if (oTabDocumentosDet.getTabTipoDocumentoEstado().getCtipoVisualzaId().equals("0004")) {
                        genTipC(oTabDocumentosDet);
                    }
                    break;
            }

            try {
                // obtener el ultimo estado
                tabDocumentosDetV = this.getDocumentosDetServ().buscarSolo(tabDocumentosDet);
                tabDocumentosCabList = this.getDocumentosServ().listarDocumentosDet(tabDocumentos);
                fechaCorte = null;
                observaciones = "";

            } catch (Exception ex) {
                //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

       System.out.println("pop.webcobranzas.bean.DocumentoBean.grabarDocumentoDetalle() -- B");
    }

    private void genPl24H(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genPl24H");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genPlazo24H(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genPreProtesto(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genPreProtesto");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genProtesto(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genPreJudicial(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genPreJudicial");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genPreJudicial(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genUltAviso(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genUltAviso");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genUltAviso(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genPreNegExtJud(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genPreNegExtJud");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
/*
            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(oInversionSend);
            oInversionSend.setRepSaldoDeudor(oSaldoDeudor);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
*/
            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);

            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genNegExtJudicial(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genPreNegPreJud(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genPreNegPreJud");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setfFinBusq(fechaDemanda);
            // saldo deudor
            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(oInversionSend);
            oInversionSend.setRepSaldoDeudor(oSaldoDeudor);
            // estado de cuenta a la fecha de envio
            cuotaPagoFechaCorte.setMaeInversion(oInversionSend);
            cuotaPagoFechaCorte.setfIniBusq(fechaCorte);
            cuotaPagoFechaCorte.setiPendiente(getCuotaPagoServ().calcularCuotaPagoFuturo(cuotaPagoFechaCorte).getiPendiente());
            List<MaeCuotaPago> cuotaList = new ArrayList<>();
            cuotaList.add(cuotaPagoFechaCorte);
            oInversionSend.setMaeCuotaPagoList(cuotaList);

            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);
            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setFopc1(fechaDemanda);

            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genNegPreJudicial(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    private void genInvNegociacion(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genInvNegociacion");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());

            MaeInmueble min = new MaeInmueble();
            min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
            min.setMaeInversion(oInversionSend);

            oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

            TabArchivo tabArchivo = new TabArchivo();

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());
            tabArchivo.setBgenerado(false);
            tabArchivo = arcDocumentoServ.genInvNegociacion(oInversionSend, maeReporte);

            if (tabArchivo.isBgenerado()) {

                tabArchivo.setTabDocumentosDet(documentosDet);
                tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                tabArchivo.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genTipB(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genTipB");
        try {

            TabDocumentosDet documentosDet = new TabDocumentosDet();
            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
            documentosDet.setFfecha(fechaCorte);
            documentosDet.setDobs(observaciones);
            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
            //documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());

            // grabando el detalle del documento
            int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
            if (cdocDet > 0) {
                // grabando datos del archivo
                documentosDet.setCtabDocumentosDetId(cdocDet);
                showMsg = true;
                tipoMsj = "success";
                mensaje = "Se graba el detalle del documento.";

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
            }

        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genTipC(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genTipC");
        List<TabArchivo> lista = this.getFileUploadBean().cargarArchivos();

        MaeInversion oInversionSend = new MaeInversion();
        MaeFondo oFondoSend = new MaeFondo();
        oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
        oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
        oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
        oInversionSend.setCInversion(maeInversion.getCInversion());
        oInversionSend.setMaeFondo(oFondoSend);

        TabDocumentosDet documentosDet = new TabDocumentosDet();
        documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
        documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
        documentosDet.setFfecha(fechaCorte);
        documentosDet.setDobs(observaciones);
        documentosDet.setCtablaDetId(codcargo);
        documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
        documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());

        for (TabArchivo tabArchivo : lista) {
            TabArchivo tb = this.getArcDocumentoServ().guardarArchivo(oInversionSend, tabArchivo);
           System.out.println("tb ---> " + tabArchivo.getDnombreArc() + tb.isBgenerado());
            if (!tb.isBgenerado()) {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se graba el detalle del documento.";
                break;
            } else {
                tb.setTabDocumentosDet(documentosDet);
                tb.setcUsuarioAdd(SessionUtils.getUserName());
                tb.setFgeneracion(fechaCorte);
                // grabando el detalle del documento
                int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                if (cdocDet > 0) {
                    // grabando datos del archivo
                    documentosDet.setCtabDocumentosDetId(cdocDet);
                    //tabArchivo
                    if (this.getArchivoServ().insertar(tb) > 0) {
                        showMsg = true;
                        tipoMsj = "success";
                        mensaje = "Se graba el detalle del documento.";
                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                        break;
                    }
                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                    break;
                }

            }
        }

    }

    public void viewArchivoRep(TabArchivo oTabArchivo, TabTipoDocumento tabTipoDocumento) {
        System.out.println("viewArchivoRep");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;

            bPrint = arcDocumentoServ.imprimirArchivo(oTabArchivo);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                switch (tabTipoDocumento.getCtabTipoDocId()) {
                    case 1:
                        //printPrePla24H();
                        hsr.setHeader("Content-disposition", "inline;filename=\"" + "PrePla24H_" + name + ".pdf" + "\"");
                        break;
                    case 2:
                        //printPreCN();
                        hsr.setHeader("Content-disposition", "inline;filename=\"" + "CartaNotarial_" + name + ".pdf" + "\"");
                        break;
                    case 3:
                        //printProtesto();
                        hsr.setHeader("Content-disposition", "inline;filename=\"" + "Protesto_" + name + ".pdf" + "\"");
                        break;
                    case 5:
                        //printPreJudicial();
                        hsr.setHeader("Content-disposition", "inline;filename=\"" + "PreJudicial_" + name + ".pdf" + "\"");
                        break;
                }
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

    public void viewArchivo(TabArchivo oTabArchivo) {
        System.out.println("viewArchivo");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;

            bPrint = arcDocumentoServ.imprimirArchivo(oTabArchivo);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;

                if (oTabArchivo.getCtipoArcId().equals("0001")) {
                    hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                } else if (oTabArchivo.getCtipoArcId().equals("0003")) {
                    hsr.setContentType("application/pdf");

                }

                hsr.setHeader("Content-disposition", "inline;filename=\"" + oTabArchivo.getDnombreArc() + "\"");

                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

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

    public void printPreCN() {
        System.out.println("printPreCN");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] bPrint = null;
            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());
            if (oInversionSend.getMaeInmueble() != null) {
                // obtener el nombre de la sede 
                for (CobTablas cs : cobTablasSedeList) {
                    if (cs.getCtablaDetId().equals(maeHipoteca.getCsede().getCtablaDetId())) {
                        maeHipoteca.getCsede().setDdescCorta(cs.getDdescCorta());
                    }
                    if (cs.getCtablaDetId().equals(maeHipoteca.getCsedeTchn().getCtablaDetId())) {
                        maeHipoteca.getCsedeTchn().setDdescCorta(cs.getDdescCorta());
                    }
                }

                oInversionSend.getMaeInmueble().setMaeHipoteca(maeHipoteca);
                MaeInmueble min = new MaeInmueble();
                min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
                min.setMaeInversion(oInversionSend);
                // 
                MaeUbigeo mubP = new MaeUbigeo();
                mubP.setCUbigeoId(maeUbigeoP.getCUbigeoId());
                mubP = getUbigeoServ().buscarUbigeo(mubP).get(0);
                //
                MaeUbigeo mubD = new MaeUbigeo();
                mubD.setCUbigeoId(maeUbigeoD.getCUbigeoId());
                mubD = getUbigeoServ().buscarUbigeo(mubD).get(0);
                mubD.setMaeUbigeo(mubP);
                oInversionSend.getMaeInmueble().setMaeUbigeo(mubD);
                //
                oInversionSend.getMaeInmueble().setDDir1(maeInmueble.getDDir1());
               System.out.println("pop.webcobranzas.bean.DocumentoDetBean.printPreCN() --> " + maeInmueble.getDDir1());
                oInversionSend.getMaeInmueble().setDdir3(maeInmueble.getDdir3());
                //
                oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));
                // 
            }

            bPrint = arcDocumentoServ.preCN(oInversionSend, maeReporte);

            baos.write(bPrint);
            String name = maeInversion.getCInversion().trim();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                //hsr.setContentType("application/pdf");
                hsr.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                //hsr.setHeader("Content-disposition", "inline;filename=\"" + "PreCN_" + name + ".pdf" + "\"");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "PreCN_" + name + ".docx" + "\"");
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
            //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void genCN(TabDocumentosDet oTabDocumentosDet) throws Exception {
        System.out.println("genCN");
        try {

            MaeReporte maeReporte = new MaeReporte();
            MaePersona persona = new MaePersona();
            persona = getPersonaServ().buscarPerAsignada((int) maeInversion.getNVencimientoDia(), maeInversion.getMaeFondo().getCFondoId(), maeInversion.getcMaeInversionId());
            maeReporte.setcUsuarioAdd(persona.getTabUsuario().getCusuarioId());
            maeReporte.setUserName(persona.getTabUsuario().getCusuarioId());
            maeReporte.setNumAsesor(persona.getMaeTelefonoList().get(0).getANumero());
            maeReporte.setMailAsesor(persona.getMaeEmailList().get(0).getDemail());
            maeReporte.setDusuarioNombres(persona.getDNombres());
            maeReporte.setDusuarioApellidos(persona.getDApePat() + " " + persona.getDApeMat());

            MaeInversion oInversionSend = new MaeInversion();
            MaeFondo oFondoSend = new MaeFondo();
            oFondoSend.setCFondoId(maeInversion.getMaeFondo().getCFondoId());
            oInversionSend.setcMaeInversionId(maeInversion.getcMaeInversionId());
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setMaeFondo(oFondoSend);
            oInversionSend = getInversionServ().inversionDocumento(oInversionSend);
            oInversionSend.setfIniBusq(fechaCorte);
            oInversionSend.setCcodigoIdent(maeInversion.getCcodigoIdent());

            if (oInversionSend.getMaeInmueble() != null) {
                oInversionSend.getMaeInmueble().setMaeHipoteca(maeHipoteca);
                MaeInmueble min = new MaeInmueble();
                min.setCInmuebleId(oInversionSend.getMaeInmueble().getCInmuebleId());
                min.setMaeInversion(oInversionSend);
                min.setDDir1(maeInmueble.getDDir1());
                min.setDdir3(maeInmueble.getDdir3());
                // 
                MaeUbigeo mubP = new MaeUbigeo();
                mubP.setCUbigeoId(maeUbigeoP.getCUbigeoId());
                mubP = getUbigeoServ().buscarUbigeo(mubP).get(0);
                //
                MaeUbigeo mubD = new MaeUbigeo();
                mubD.setCUbigeoId(maeUbigeoD.getCUbigeoId());
                mubD = getUbigeoServ().buscarUbigeo(mubD).get(0);
                mubD.setMaeUbigeo(mubP);
                oInversionSend.getMaeInmueble().setMaeUbigeo(mubD);
                oInversionSend.getMaeInmueble().setDDir1(maeInmueble.getDDir1());
                oInversionSend.getMaeInmueble().setDdir3(maeInmueble.getDdir3());
                oInversionSend.getMaeInmueble().setMaePersonaInmuebleList(this.getInmuebleServ().listarPersonaInmueble(min));

                // actualizar los datos del inmueble
                if (this.getInmuebleServ().actualizar(oInversionSend.getMaeInmueble())) {

                    // grabando la hipoteca
                    MaeHipoteca maeHipotecaSave = new MaeHipoteca();
                    maeHipotecaSave.setMaeInmueble(min);
                    maeHipotecaSave.setCasientoId(maeHipoteca.getCasientoId());
                    maeHipotecaSave.setCpartidaElecId(maeHipoteca.getCpartidaElecId());
                    //maeHipotecaSave.setCsedeId(maeHipoteca.getCsedeId());
                    CobTablas csedeSave = new CobTablas();
                    CobTablas csedeTchnSave = new CobTablas();

                    // obtener el nombre de la sede 
                    for (CobTablas cs : cobTablasSedeList) {
                        if (cs.getCtablaDetId().equals(maeHipoteca.getCsede().getCtablaDetId())) {
                            csedeSave.setCtablaDetId(maeHipoteca.getCsede().getCtablaDetId());
                            csedeSave.setDdescCorta(cs.getDdescCorta());
                        }
                        if (cs.getCtablaDetId().equals(maeHipoteca.getCsedeTchn().getCtablaDetId())) {
                            csedeTchnSave.setCtablaDetId(maeHipoteca.getCsedeTchn().getCtablaDetId());
                            csedeTchnSave.setDdescCorta(cs.getDdescCorta());
                        }
                    }

                    maeHipotecaSave.setCsede(csedeSave);
                    maeHipotecaSave.setFasiento(maeHipoteca.getFasiento());
                    maeHipotecaSave.setDnomNotaria(maeHipoteca.getDnomNotaria());
                    maeHipotecaSave.setCtchnReal(maeHipoteca.getCtchnReal());
                    maeHipotecaSave.setFemisionTchn(maeHipoteca.getFemisionTchn());
                    maeHipotecaSave.setCasientoTchn(maeHipoteca.getCasientoTchn());

                    maeHipotecaSave.setCsedeTchn(csedeTchnSave);

                    maeHipotecaSave.setcUsuarioAdd(SessionUtils.getUserName());

                    oInversionSend.getMaeInmueble().setMaeHipoteca(maeHipotecaSave);

                    TabArchivo tabArchivo = new TabArchivo();
                    tabArchivo.setBgenerado(false);
                    tabArchivo = arcDocumentoServ.genCN(oInversionSend, maeReporte);

                    if (tabArchivo.isBgenerado()) {

                        int cdocHipo = this.getHipotecaServ().insertar(maeHipotecaSave);
                        // verificar si se grabo la hipoteca
                        if (cdocHipo > 0) {
                            TabDocumentosDet documentosDet = new TabDocumentosDet();
                            documentosDet.setTabDocumentos(oTabDocumentosDet.getTabDocumentos());
                            documentosDet.setTabTipoDocumentoEstado(oTabDocumentosDet.getTabTipoDocumentoEstado());
                            documentosDet.setFfecha(fechaCorte);
                            documentosDet.setDobs(observaciones);
                            documentosDet.setcUsuarioAdd(SessionUtils.getUserName());
                            documentosDet.setCtabDocumentosDetId(oTabDocumentosDet.getCtabDocumentosDetId());

                            tabArchivo.setTabDocumentosDet(documentosDet);
                            tabArchivo.setcUsuarioAdd(SessionUtils.getUserName());
                            tabArchivo.setFgeneracion(fechaCorte);

                            // grabando el detalle del documento
                            int cdocDet = this.getDocumentosDetServ().insertar(documentosDet);
                            if (cdocDet > 0) {
                                // grabando datos del archivo
                                documentosDet.setCtabDocumentosDetId(cdocDet);
                                //tabArchivo
                                if (this.getArchivoServ().insertar(tabArchivo) > 0) {
                                    showMsg = true;
                                    tipoMsj = "success";
                                    mensaje = "Se graba el detalle del documento.";
                                } else {
                                    showMsg = true;
                                    tipoMsj = "error";
                                    mensaje = "No se graba el detalle del documento.";
                                }
                            } else {
                                showMsg = true;
                                tipoMsj = "error";
                                mensaje = "No se graba el detalle del documento.";
                            }

                        } else {
                            showMsg = true;
                            tipoMsj = "error";
                            mensaje = "No se graba el detalle del documento.";
                            //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, "No se genera la hipoteca.");
                            System.out.println(tipoMsj+":1:"+mensaje);
                        }

                    } else {
                        showMsg = true;
                        tipoMsj = "error";
                        mensaje = "No se graba el detalle del documento.";
                        //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, "No se genero el documento.");
                        System.out.println(tipoMsj+":2:"+mensaje);
                    }

                } else {
                    showMsg = true;
                    tipoMsj = "error";
                    mensaje = "No se graba el detalle del documento.";
                    //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, "No se actualizo la direccion.");
                    System.out.println(tipoMsj+":3:"+mensaje);
                }

            } else {
                showMsg = true;
                tipoMsj = "error";
                mensaje = "No se encuentra el inmueble registrado.";
                //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, "No se encuentra el inmueble registrado.");
                System.out.println(tipoMsj+":4:"+mensaje);
            }

        } catch (Exception e) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "No se graba el detalle del documento.";
            //Logger.getLogger(DocumentoDetBean.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(tipoMsj+":5:"+mensaje);
            System.out.println(e);
        }
    }

    public IDocumentosDetServ getDocumentosDetServ() {
        return documentosDetServ;
    }

    public void setDocumentosDetServ(IDocumentosDetServ documentosDetServ) {
        this.documentosDetServ = documentosDetServ;
    }

    public TabDocumentosDet getTabDocumentosDet() {
        return tabDocumentosDet;
    }

    public void setTabDocumentosDet(TabDocumentosDet tabDocumentosDet) {
        this.tabDocumentosDet = tabDocumentosDet;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public TabDocumentos getTabDocumentos() {
        return tabDocumentos;
    }

    public void setTabDocumentos(TabDocumentos tabDocumentos) {
        this.tabDocumentos = tabDocumentos;
    }

    public TabDocumentosDet getTabDocumentosDetV() {
        return tabDocumentosDetV;
    }

    public void setTabDocumentosDetV(TabDocumentosDet tabDocumentosDetV) {
        this.tabDocumentosDetV = tabDocumentosDetV;
    }

    public List<TabDocumentosDet> getTabDocumentoDetList() {
        return tabDocumentoDetList;
    }

    public void setTabDocumentoDetList(List<TabDocumentosDet> tabDocumentoDetList) {
        this.tabDocumentoDetList = tabDocumentoDetList;
    }

    public List<TabDocumentos> getTabDocumentosCabList() {
        return tabDocumentosCabList;
    }

    public void setTabDocumentosCabList(List<TabDocumentos> tabDocumentosCabList) {
        this.tabDocumentosCabList = tabDocumentosCabList;
    }

    public IDocumentosServ getDocumentosServ() {
        return documentosServ;
    }

    public void setDocumentosServ(IDocumentosServ documentosServ) {
        this.documentosServ = documentosServ;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    public IArcDocumentoServ getArcDocumentoServ() {
        return arcDocumentoServ;
    }

    public void setArcDocumentoServ(IArcDocumentoServ arcDocumentoServ) {
        this.arcDocumentoServ = arcDocumentoServ;
    }

    public IInversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(IInversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
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

    public IArchivoServ getArchivoServ() {
        return archivoServ;
    }

    public void setArchivoServ(IArchivoServ archivoServ) {
        this.archivoServ = archivoServ;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public FileUploadBean getFileUploadBean() {
        return fileUploadBean;
    }

    public void setFileUploadBean(FileUploadBean fileUploadBean) {
        this.fileUploadBean = fileUploadBean;
    }

    public MaeHipoteca getMaeHipoteca() {
        return maeHipoteca;
    }

    public void setMaeHipoteca(MaeHipoteca maeHipoteca) {
        this.maeHipoteca = maeHipoteca;
    }

    public IInmuebleServ getInmuebleServ() {
        return inmuebleServ;
    }

    public void setInmuebleServ(IInmuebleServ inmuebleServ) {
        this.inmuebleServ = inmuebleServ;
    }

    public String getCmaeUbigeoProv() {
        return cmaeUbigeoProv;
    }

    public void setCmaeUbigeoProv(String cmaeUbigeoProv) {
        this.cmaeUbigeoProv = cmaeUbigeoProv;
    }

    public String getCmaeUbigeoDist() {
        return cmaeUbigeoDist;
    }

    public void setCmaeUbigeoDist(String cmaeUbigeoDist) {
        this.cmaeUbigeoDist = cmaeUbigeoDist;
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

    public IHipotecaServ getHipotecaServ() {
        return hipotecaServ;
    }

    public void setHipotecaServ(IHipotecaServ hipotecaServ) {
        this.hipotecaServ = hipotecaServ;
    }

    public ITablasServ getTablasServ() {
        return tablasServ;
    }

    public void setTablasServ(ITablasServ tablasServ) {
        this.tablasServ = tablasServ;
    }

    public CobTablas getCobTablasSede() {
        return cobTablasSede;
    }

    public void setCobTablasSede(CobTablas cobTablasSede) {
        this.cobTablasSede = cobTablasSede;
    }

    public List<CobTablas> getCobTablasSedeList() {
        return cobTablasSedeList;
    }

    public void setCobTablasSedeList(List<CobTablas> cobTablasSedeList) {
        this.cobTablasSedeList = cobTablasSedeList;
    }

    public CobTablas getCsede() {
        return csede;
    }

    public void setCsede(CobTablas csede) {
        this.csede = csede;
    }

    public CobTablas getCsedeTCHN() {
        return csedeTCHN;
    }

    public void setCsedeTCHN(CobTablas csedeTCHN) {
        this.csedeTCHN = csedeTCHN;
    }

    public IUbigeoServ getUbigeoServ() {
        return ubigeoServ;
    }

    public void setUbigeoServ(IUbigeoServ ubigeoServ) {
        this.ubigeoServ = ubigeoServ;
    }

    public CobTablas getCobTablasCargo() {
        return cobTablasCargo;
    }

    public void setCobTablasCargo(CobTablas cobTablasCargo) {
        this.cobTablasCargo = cobTablasCargo;
    }

    public List<CobTablas> getCobTablasCargoList() {
        return cobTablasCargoList;
    }

    public void setCobTablasCargoList(List<CobTablas> cobTablasCargoList) {
        this.cobTablasCargoList = cobTablasCargoList;
    }

    public String getCodcargo() {
        return codcargo;
    }

    public void setCodcargo(String codcargo) {
        this.codcargo = codcargo;
    }

    public Date getFechaDemanda() {
        return fechaDemanda;
    }

    public void setFechaDemanda(Date fechaDemanda) {
        this.fechaDemanda = fechaDemanda;
    }

    public IPersonaServ getPersonaServ() {
        return personaServ;
    }

    public void setPersonaServ(IPersonaServ personaServ) {
        this.personaServ = personaServ;
    }

    public MaeCuotaPago getCuotaPagoFechaCorte() {
        return cuotaPagoFechaCorte;
    }

    public void setCuotaPagoFechaCorte(MaeCuotaPago cuotaPagoFechaCorte) {
        this.cuotaPagoFechaCorte = cuotaPagoFechaCorte;
    }

    public ICuotaPagoServ getCuotaPagoServ() {
        return cuotaPagoServ;
    }

    public void setCuotaPagoServ(ICuotaPagoServ cuotaPagoServ) {
        this.cuotaPagoServ = cuotaPagoServ;
    }

}
