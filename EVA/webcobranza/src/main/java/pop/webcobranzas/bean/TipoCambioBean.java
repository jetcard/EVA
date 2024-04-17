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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import pop.comun.dominio.MaeTipoCambio;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.ITipoCambioServ;
import pop.webcobranzas.ifacerep.IRepTipoCambioServ;
import pop.webcobranzas.servicio.TipoCambioServ;
import pop.webcobranzas.serviciorep.RepTipoCambioServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class TipoCambioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    // lista de tipo cambio 
    private List<MaeTipoCambio> maeTipoCambioList = new ArrayList<>();
    // para busqueda
    private MaeTipoCambio maeTipoCambio = new MaeTipoCambio();
    // para grabar
    private MaeTipoCambio maeTipoCambioG = new MaeTipoCambio();
    // servicios de tipo de cambio
    private ITipoCambioServ cambioServ = new TipoCambioServ();
    // servicios de tipo de cambio reporete
    private IRepTipoCambioServ repTipoCambioServ = new RepTipoCambioServ();
    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";

    public void buscarTipoCambio() {

        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " TipoCambioBean - buscarTipoCambio");
            maeTipoCambioList = cambioServ.listarTipoCambio(maeTipoCambio);
        } catch (Exception ex) {
            showMsg = true;
            tipoMsj = "error";
            mensaje = "Error al buscar tipo de cambio";
            Logger.getLogger(TipoCambioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void grabarTipoCambio() {
        showMsg = true;
        try {
            System.out.println("[" + SessionUtils.getUserName() + "] " + " TipoCambioBean - grabarTipoCambio - " + maeTipoCambioG.getfCambio());
            
            maeTipoCambioG.setcUsuarioAdd(SessionUtils.getUserName());
            int newID = cambioServ.insertar(maeTipoCambioG);
            if (newID == 0) {
                tipoMsj = "error";
                mensaje = "Error al Grabar tipo de cambio";
            } else {
                tipoMsj = "success";
                mensaje = "Se grabo tipo de cambio";
            }
        } catch (Exception ex) {
            tipoMsj = "error";
            mensaje = "Error al Grabar tipo de cambio";
            Logger.getLogger(TipoCambioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printTipoCambio() {
        try {
//<h:commandButton id="printButton" value="Print" actionListener="#{tipoCambioBean.printTipoCambio}" />                        
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Document document = new Document(PageSize.A4);
            //PdfWriter.getInstance(document, baos);
            //PdfWriter.getInstance(document, response.getOutputStream());
            byte[] asss = null;

            asss = repTipoCambioServ.imprimirTipoCambio(maeTipoCambioList);

            baos.write(asss);

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();

            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");
                hsr.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                hsr.setHeader("Content-disposition", "inline;filename=\"" + "newFile.pdf" + "\"");
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
            Logger.getLogger(TipoCambioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<MaeTipoCambio> getMaeTipoCambioList() {
        return maeTipoCambioList;
    }

    public void setMaeTipoCambioList(List<MaeTipoCambio> maeTipoCambioList) {
        this.maeTipoCambioList = maeTipoCambioList;
    }

    public MaeTipoCambio getMaeTipoCambio() {
        return maeTipoCambio;
    }

    public void setMaeTipoCambio(MaeTipoCambio maeTipoCambio) {
        this.maeTipoCambio = maeTipoCambio;
    }

    public ITipoCambioServ getCambioServ() {
        return cambioServ;
    }

    public void setCambioServ(ITipoCambioServ cambioServ) {
        this.cambioServ = cambioServ;
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

    public MaeTipoCambio getMaeTipoCambioG() {
        return maeTipoCambioG;
    }

    public void setMaeTipoCambioG(MaeTipoCambio maeTipoCambioG) {
        this.maeTipoCambioG = maeTipoCambioG;
    }

    public IRepTipoCambioServ getRepTipoCambioServ() {
        return repTipoCambioServ;
    }

    public void setRepTipoCambioServ(IRepTipoCambioServ repTipoCambioServ) {
        this.repTipoCambioServ = repTipoCambioServ;
    }

}
