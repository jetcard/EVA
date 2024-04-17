/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.CobTchn;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeReporte;
import pop.comun.dominio.MaeUbigeo;
import pop.comun.dominio.reporte.RepSaldoDeudor;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.IUbigeoServ;
import pop.webcobranzas.ifacerep.IRepSaldoDeudorServ;
import pop.webcobranzas.servicio.EstadoCuentaServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.InversionServ;
import pop.webcobranzas.servicio.UbigeoServ;
import pop.webcobranzas.serviciorep.RepSaldoDeudorServ;
/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class SaldoDeudorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private SeguimientoBean seguimientoBean;

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
    // inversion busqueda
    private MaeInversion maeInversionRep = new MaeInversion();
    // cobTchn para la lista
    private CobTchn cobTchn = new CobTchn();

    // lista de provincias
    private List<MaeUbigeo> ubigeoProv;
    // lista de distritos
    private List<MaeUbigeo> ubigeoDist;
    // lista de inversiones con deuda
    private List<MaeInversion> maeInversionList;
    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista de tchn 
    private List<CobTchn> cobTchnList;

    // sericios de ubigeo
    private IUbigeoServ ubigeoServ = new UbigeoServ();
    // servicios de inversion
    private InversionServ inversionServ = new InversionServ();
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de estado de cuenta
    private EstadoCuentaServ estadoCuentaServ = new EstadoCuentaServ();
    // servicios de tipo de cambio reporte
    private IRepSaldoDeudorServ repSaldoDeudorServ = new RepSaldoDeudorServ();
    // gastos
    private double gastoAdmin;
    private double gastoLegales;

    public SaldoDeudorBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setMaeInmueble(maeInmueble);

        cobTchn.setFondo(maeFondo);
        cobTchn.setMaeInversion(maeInversion);
    }

    public void listarProvincia() {
        try {
            ubigeoProv = getUbigeoServ().listarProvincia(maeUbigeoP);
            //System.out.println(" cantidad de Provincias -> " + ubigeoProv.size());
        } catch (Exception e) {

        }
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            //System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void listarDistrito(AjaxBehaviorEvent event) {
        try {
            //System.out.println("pop.webcobranzas.bean.InversionBean.listarDistrito()");
            ubigeoDist = getUbigeoServ().listarDistrito(maeUbigeoP);
            //System.out.println(" cantidad de Distritos -> " + ubigeoDist.size());
        } catch (Exception e) {
        }
    }

    public void buscarDeudores() {
        try {
            System.out.println("["+SessionUtils.getUserName()+"] "+" SaldoDeudorBean - buscarDeudores " );
            
            maeUbigeo.setCUbigeoId(maeUbigeoD.getCUbigeoId());
            maeUbigeo.setCUbigeoPad(maeUbigeoP.getCUbigeoId());

            //System.out.println("Ubigeo -> Distrito " + maeUbigeoD.getCUbigeoId());
            //System.out.println("Ubigeo -> Provincia " + maeUbigeoP.getCUbigeoId());

            maeInmueble.setMaeUbigeo(maeUbigeo);
            //maeInversionList = getInversionServ().listarDeudor(maeInversion);

            cobTchnList = estadoCuentaServ.listarTchn(cobTchn);

            //System.out.println("pop.webcobranzas.bean.SaldoDeudorBean.buscarDeudores()");
            maeInversionRep = new MaeInversion();

            getSeguimientoBean().setShowMsg(false);

        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.InversionBean.buscarDeudores()");
        }
    }

    public void asignarInversion(CobTchn oCobTchnB) {
        System.out.println("["+SessionUtils.getUserName()+"] "+" SaldoDeudorBean - asignarInversion - " +
                oCobTchnB.getMaeInversion().getcMaeInversionId() + " - " + oCobTchnB.getMaeInversion().getCInversion().trim());
        maeInversionRep = oCobTchnB.getMaeInversion();
        this.setGastoAdmin(0);
        this.setGastoLegales(0);

    }

    public void printSaldoDeudor() {

        try {
            //HHA20180605
            System.out.println("["+SessionUtils.getUserName()+"] "+" SaldoDeudorBean - printSaldoDeudor - " + 
                    maeInversionRep.getcMaeInversionId() + " - " + maeInversionRep.getCInversion().trim()+ " - " + maeInversionRep.getfIniBusq());
            
            //System.out.println("["+SessionUtils.getUserName()+"] "+" SaldoDeudorBean - printSaldoDeudor - " + 
            //        maeInversionRep.getcMaeInversionId() + " - " + maeInversionRep.getCInversion().trim()+ " - " + maeInversionRep.getfIniBusq() );
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] asss = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            //maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            //MaeInversion miSD = new MaeInversion();
            //miSD.setcMaeInversionId(maeEstadoCuentaList.get(0).getMaeInversion().getcMaeInversionId());
            //miSD.setcTipoInv("0001");
            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //miSD.setfIniBusq(formatter.parse("18/11/2016"));
            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());

            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(maeInversionRep);
            oSaldoDeudor.setIgastLegalFut(gastoLegales);
            oSaldoDeudor.setIgastAdmin(gastoAdmin);
            asss = repSaldoDeudorServ.imprimirSaldoDeudor(oSaldoDeudor, maeReporte); 
            baos.write(asss);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                hsr.setHeader("Content-disposition", "inline;filename=\"" + "SaldoDeudor" + maeInversionRep.getCInversion().trim() + ".pdf" + "\"");
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
            System.out.println("printSaldoDeudor:error[" + ex.getMessage());
            Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportSaldoDeudor() {
        try {
             System.out.println("["+SessionUtils.getUserName()+"] "+" SaldoDeudorBean - exportSaldoDeudor - " + 
                    maeInversionRep.getcMaeInversionId() + " - " + maeInversionRep.getCInversion().trim()+ " - " + maeInversionRep.getfIniBusq() );
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] asss = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());

            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());

            RepSaldoDeudor oSaldoDeudor = inversionServ.reporteSaldoDeudor(maeInversionRep);
            oSaldoDeudor.setIgastLegalFut(gastoLegales);
            oSaldoDeudor.setIgastAdmin(gastoAdmin);
            asss = repSaldoDeudorServ.exportarSaldoDeudor(oSaldoDeudor, maeReporte);  //repEstadoCuentaServ.imprimirEstadoCuenta(maeEstadoCuentaList, maeReporte);
            baos.write(asss);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/vnd.ms-excel");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                hsr.setHeader("Content-disposition", "attachment;filename=SaldoDeudor" + maeInversionRep.getCInversion().trim() + ".xlsx");
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
            Logger.getLogger(EstadoCuentaBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    public CobTchn getCobTchn() {
        return cobTchn;
    }

    public void setCobTchn(CobTchn cobTchn) {
        this.cobTchn = cobTchn;
    }

    public List<CobTchn> getCobTchnList() {
        return cobTchnList;
    }

    public void setCobTchnList(List<CobTchn> cobTchnList) {
        this.cobTchnList = cobTchnList;
    }

    public EstadoCuentaServ getEstadoCuentaServ() {
        return estadoCuentaServ;
    }

    public void setEstadoCuentaServ(EstadoCuentaServ estadoCuentaServ) {
        this.estadoCuentaServ = estadoCuentaServ;
    }

    public IRepSaldoDeudorServ getRepSaldoDeudorServ() {
        return repSaldoDeudorServ;
    }

    public void setRepSaldoDeudorServ(IRepSaldoDeudorServ repSaldoDeudorServ) {
        this.repSaldoDeudorServ = repSaldoDeudorServ;
    }

    public MaeInversion getMaeInversionRep() {
        return maeInversionRep;
    }

    public void setMaeInversionRep(MaeInversion maeInversionRep) {
        this.maeInversionRep = maeInversionRep;
    }

    public double getGastoAdmin() {
        return gastoAdmin;
    }

    public void setGastoAdmin(double gastoAdmin) {
        this.gastoAdmin = gastoAdmin;
    }

    public double getGastoLegales() {
        return gastoLegales;
    }

    public void setGastoLegales(double gastoLegales) {
        this.gastoLegales = gastoLegales;
    }

}
