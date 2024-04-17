/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobLlamadas;
import pop.webcobranzas.iface.ILlamadaServ;
import pop.webcobranzas.servicio.LlamadaServ;

/**
 *
 * @author Jyoverar
 */
@ManagedBean(name = "tipificacionBean", eager = true)
@ViewScoped
public class tipificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CobCdr cobCdr = new CobCdr();

    private List<CobLlamadas> cobLlamadaList;

    // sericios de llamadas
    private ILlamadaServ llamadaServ = new LlamadaServ();

    /**
     * Creates a new instance of tipificacionBean
     */
    public tipificacionBean() {
    }

    public void buscarTipificacion() {
        System.out.println(" buscarTipificacion->");
        try {
            if (cobCdr.getCalldateB()!= null) {
                SimpleDateFormat ftB = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                

                System.out.println(" buscarTipificacion  numerocontac ->" + cobCdr.getDst());
                System.out.println(" buscarTipificacion  fechacontac ->" + cobCdr.getCalldateB());
                System.out.println(" buscarTipificacion  centralcontac ->" + cobCdr.getDid() + cobCdr.getDid().trim().length() );
                System.out.println(" buscarTipificacion  numerocontacb ->" + cobCdr.getSrc());
                String dst="5555555";
                if (cobCdr.getDid().trim().length() == 0) {
                    dst = cobCdr.getDst().substring(1, cobCdr.getDst().length());
                }
                else {
                    if (cobCdr.getSrc().trim().length()==8) {
                        dst = cobCdr.getSrc().substring(1, cobCdr.getSrc().length());
                    }else{
                        dst = cobCdr.getSrc();
                    }
                }

                System.out.println(" buscarTipificacion  numero  ->" + dst);
                
                cobCdr.setCalldate(ftB.parse(cobCdr.getCalldateB()));
                
                //
                cobCdr.setDst(dst);
                //
                cobLlamadaList = this.getLlamadaServ().listarTele(cobCdr);
            }
        } catch (Exception e) {
            System.out.println("pop.webcobranzas.bean.tipificacionBean.buscarTipificacion()" + e.getMessage());
        }
    }

    public CobCdr getCobCdr() {
        return cobCdr;
    }

    public void setCobCdr(CobCdr cobCdr) {
        this.cobCdr = cobCdr;
    }

    public ILlamadaServ getLlamadaServ() {
        return llamadaServ;
    }

    public void setLlamadaServ(ILlamadaServ llamadaServ) {
        this.llamadaServ = llamadaServ;
    }

    public List<CobLlamadas> getCobLlamadaList() {
        return cobLlamadaList;
    }

    public void setCobLlamadaList(List<CobLlamadas> cobLlamadaList) {
        this.cobLlamadaList = cobLlamadaList;
    }

}
