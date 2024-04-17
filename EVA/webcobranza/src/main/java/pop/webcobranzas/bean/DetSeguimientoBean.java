/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobDevice;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeReporte;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ICdrServ;
import pop.webcobranzas.iface.ISeguimientoServ;
import pop.webcobranzas.ifacerep.IRepSeguimientoDetServ;
import pop.webcobranzas.servicio.CdrServ;
import pop.webcobranzas.servicio.SeguimientoServ;
import pop.webcobranzas.serviciorep.RepSeguimientoDetServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class DetSeguimientoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // cdr busqueda
    private CobCdr cobCdrB = new CobCdr();
    // CobDevice busqueda
    private CobDevice cobDeviceB = new CobDevice();

    // list cdr
    private List<CobMaeSeguimiento> cobMaeSeguimientos;
    // list cdr
    private List<CobDevice> cobDevices;

    // servicios
    private ICdrServ cdrServ = new CdrServ();
    private ISeguimientoServ seguimientoServ = new SeguimientoServ();
    private IRepSeguimientoDetServ seguimientoDetServ = new RepSeguimientoDetServ();

    /**
     * Creates a new instance of DetSeguimientoBean
     */
    public DetSeguimientoBean() {
    }

    public void listarDispositivo() {
        try {
            System.out.println("pop.webcobranzas.bean.CdrBean.listarDispositivo()");
            if (cobDevices == null) {
                cobDevices = getCdrServ().listarDispositivo(cobDeviceB);
            }
            System.out.println(" cantidad de cobDevices -> " + cobDevices.size());
        } catch (Exception e) {
        }
    }

    public void buscarSeguimientos() {
        System.out.println(" =========== buscarCdr = " + new Date());
        try {
            CobMaeSeguimiento oCobMaeSeguimiento = new CobMaeSeguimiento();
            MaeInversion mi = new MaeInversion();
            MaeFondo mf = new MaeFondo();
            mf.setCFondoId("0001");
            mi.setMaeFondo(mf);
            oCobMaeSeguimiento.setMaeInversion(mi);
            cobMaeSeguimientos = getSeguimientoServ().listarSeguimientoLlamada(oCobMaeSeguimiento);
        } catch (Exception ex) {
            Logger.getLogger(DetSeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" =========== buscarCdr = " + new Date());
    }

     public void imprimirDetSeguimiento() {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] matriz = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            //maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());
            
            matriz = getSeguimientoDetServ().exportarReporte(cobMaeSeguimientos, maeReporte);
            
            baos.write(matriz);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/vnd.ms-excel");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                hsr.setHeader("Content-disposition", "attachment;filename=myfile.xlsx");
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

    
    public CobCdr getCobCdrB() {
        return cobCdrB;
    }

    public void setCobCdrB(CobCdr cobCdrB) {
        this.cobCdrB = cobCdrB;
    }

    public CobDevice getCobDeviceB() {
        return cobDeviceB;
    }

    public void setCobDeviceB(CobDevice cobDeviceB) {
        this.cobDeviceB = cobDeviceB;
    }

    public List<CobDevice> getCobDevices() {
        return cobDevices;
    }

    public void setCobDevices(List<CobDevice> cobDevices) {
        this.cobDevices = cobDevices;
    }

    public ICdrServ getCdrServ() {
        return cdrServ;
    }

    public void setCdrServ(ICdrServ cdrServ) {
        this.cdrServ = cdrServ;
    }

    public ISeguimientoServ getSeguimientoServ() {
        return seguimientoServ;
    }

    public void setSeguimientoServ(ISeguimientoServ seguimientoServ) {
        this.seguimientoServ = seguimientoServ;
    }

    public List<CobMaeSeguimiento> getCobMaeSeguimientos() {
        return cobMaeSeguimientos;
    }

    public void setCobMaeSeguimientos(List<CobMaeSeguimiento> cobMaeSeguimientos) {
        this.cobMaeSeguimientos = cobMaeSeguimientos;
    }

    public IRepSeguimientoDetServ getSeguimientoDetServ() {
        return seguimientoDetServ;
    }

    public void setSeguimientoDetServ(IRepSeguimientoDetServ seguimientoDetServ) {
        this.seguimientoDetServ = seguimientoDetServ;
    }

}
