/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeCorreo;
import pop.webcobranzas.ifacecorreo.ICorreoRecibirServ;
import pop.webcobranzas.serviciocorreo.CorreoRecibirServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class CorreoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // lista de correos
    private List<MaeCorreo> correos ;
    
    // servicios de msj
    private ICorreoRecibirServ correoRecibirServ = new CorreoRecibirServ();

    // mensaje
    private boolean showMsg = false;
    private String mensaje = "";
    private String tipoMsj = "";
    // cantidad de correos
    private int countInbox;
    private int countSend;
    private int countImport;
    private int countDrafts;
    private int countTrash;

    public void listarCantidades() {
        try {
            countInbox = correoRecibirServ.cantidadCorreos("INBOX");
            correos = correoRecibirServ.recibir("INBOX");
            System.out.println("pop.webcobranzas.bean.CorreolBean.listarCantidades() -- >" + correos.size());
            //countInbox = correoRecibkirServ.cantidadCorreos("INBOX");
        } catch (Exception e) {
            System.out.println("e --> " + e.getMessage());
        }
    }


    public ICorreoRecibirServ getCorreoRecibirServ() {
        return correoRecibirServ;
    }

    public void setCorreoRecibirServ(ICorreoRecibirServ correoRecibirServ) {
        this.correoRecibirServ = correoRecibirServ;
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

    public int getCountInbox() {
        return countInbox;
    }

    public void setCountInbox(int countInbox) {
        this.countInbox = countInbox;
    }

    public int getCountSend() {
        return countSend;
    }

    public void setCountSend(int countSend) {
        this.countSend = countSend;
    }

    public int getCountImport() {
        return countImport;
    }

    public void setCountImport(int countImport) {
        this.countImport = countImport;
    }

    public int getCountDrafts() {
        return countDrafts;
    }

    public void setCountDrafts(int countDrafts) {
        this.countDrafts = countDrafts;
    }

    public int getCountTrash() {
        return countTrash;
    }

    public void setCountTrash(int countTrash) {
        this.countTrash = countTrash;
    }

    public List<MaeCorreo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<MaeCorreo> correos) {
        this.correos = correos;
    }

}
