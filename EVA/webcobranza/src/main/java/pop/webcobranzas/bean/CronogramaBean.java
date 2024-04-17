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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.MaeCronograma;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeReporte;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ICronogramaServ;
import pop.webcobranzas.ifacerep.IRepCronogramaServ;
import pop.webcobranzas.servicio.CronogramaServ;
import pop.webcobranzas.serviciorep.RepCronogramaServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class CronogramaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // inversion Detalle seguimiento
    private MaeInversion maeInversion = new MaeInversion();
    // inversion Detalle seguimiento
    private MaeCronograma maeCronograma = new MaeCronograma();
    
    // lista de detalle de cuotas a pagar
    private List<MaeCronograma> cronogramaList = new ArrayList<>();
    
    // servicio de cronograma
    private ICronogramaServ cronogramaServ = new CronogramaServ();
    // servicio reporte de cronograma
    private IRepCronogramaServ repCronogramaServ = new RepCronogramaServ();

    /**
     * Creates a new instance of CronogramaBean
     */
    public CronogramaBean() {
    }

    public void listarCronograma(MaeInversion oInversion) {
        try {
            maeCronograma.setMaeInversion(oInversion);
            cronogramaList = getCronogramaServ().buscarCronograma(maeCronograma);
        } catch (Exception ex) {
            Logger.getLogger(CronogramaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void imprimirCronograma() {

        try {
            //System.out.println(" AUD - clienteDeudor - exportEstCuenta - " + cronogramaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + cronogramaList.get(0).getMaeInversion().getCInversion().trim() + " - " + SessionUtils.getUserName());
            System.out.println("[" + SessionUtils.getUserName() + "] " + " CronogramaBean - imprimirCronograma - " + cronogramaList.get(0).getMaeInversion().getcMaeInversionId() + " - " + cronogramaList.get(0).getMaeInversion().getCInversion().trim());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] matriz = null;
            MaeReporte maeReporte = new MaeReporte();
            maeReporte.setcUsuarioAdd(SessionUtils.getUserName());
            //maeReporte.setfIniBusq(maeEstadoCuenta.getfIniBusq());
            maeReporte.setUserName(SessionUtils.getUserName());
            maeReporte.setNumAsesor(SessionUtils.getNumAdviser());
            maeReporte.setMailAsesor(SessionUtils.getEmailAdviser());
            
            matriz = getRepCronogramaServ().imprimirCronograma(cronogramaList, maeReporte);
            
            baos.write(matriz);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                hsr.setHeader("Content-disposition", "inline;filename=\"" + "Cronograma" + cronogramaList.get(0).getMaeInversion().getCInversion().trim()+".pdf" + "\"");
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

    
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public ICronogramaServ getCronogramaServ() {
        return cronogramaServ;
    }

    public void setCronogramaServ(ICronogramaServ cronogramaServ) {
        this.cronogramaServ = cronogramaServ;
    }

    public List<MaeCronograma> getCronogramaList() {
        return cronogramaList;
    }

    public void setCronogramaList(List<MaeCronograma> cronogramaList) {
        this.cronogramaList = cronogramaList;
    }

    public MaeCronograma getMaeCronograma() {
        return maeCronograma;
    }

    public void setMaeCronograma(MaeCronograma maeCronograma) {
        this.maeCronograma = maeCronograma;
    }

    public IRepCronogramaServ getRepCronogramaServ() {
        return repCronogramaServ;
    }

    public void setRepCronogramaServ(IRepCronogramaServ repCronogramaServ) {
        this.repCronogramaServ = repCronogramaServ;
    }

    
}
