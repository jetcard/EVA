/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class MaeCorreo extends Base {

    private static final long serialVersionUID = 1L;
    private CobUsuario cobUsuario;
    private ArrayList<MaeEmail> maeEmails;
    private String asunto;
    private String contenido;
    private ArrayList<byte[]> adjuntos;
    private String msj;
    private String msjEstado;
    private boolean badjunto;
    private Date fenvio;
    private boolean benvio;

    public CobUsuario getCobUsuario() {
        return cobUsuario;
    }

    public void setCobUsuario(CobUsuario cobUsuario) {
        this.cobUsuario = cobUsuario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public ArrayList<byte[]> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(ArrayList<byte[]> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public String getMsjEstado() {
        return msjEstado;
    }

    public void setMsjEstado(String msjEstado) {
        this.msjEstado = msjEstado;
    }

    public boolean isBenvio() {
        return benvio;
    }

    public void setBenvio(boolean benvio) {
        this.benvio = benvio;
    }

    public ArrayList<MaeEmail> getMaeEmails() {
        return maeEmails;
    }

    public void setMaeEmails(ArrayList<MaeEmail> maeEmails) {
        this.maeEmails = maeEmails;
    }

    public boolean isBadjunto() {
        return badjunto;
    }

    public void setBadjunto(boolean badjunto) {
        this.badjunto = badjunto;
    }

    public Date getFenvio() {
        return fenvio;
    }

    public void setFenvio(Date fenvio) {
        this.fenvio = fenvio;
    }

}
