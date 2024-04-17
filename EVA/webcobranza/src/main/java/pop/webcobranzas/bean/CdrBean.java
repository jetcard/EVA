/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.CobCdr;
import pop.comun.dominio.CobDevice;
import pop.webcobranzas.iface.ICdrServ;
import pop.webcobranzas.servicio.CdrServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class CdrBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // cdr busqueda
    private CobCdr cobCdrB = new CobCdr();
    // CobDevice busqueda
    private CobDevice cobDeviceB = new CobDevice();

    // list cdr
    private List<CobCdr> cobCdrs;
    // list cdr
    private List<CobDevice> cobDevices;

    // servicios
    private ICdrServ cdrServ = new CdrServ();

    public CdrBean() {
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

    public void buscarCdr() {
        System.out.println(" =========== buscarCdr = " + new Date());
        try {
            cobCdrs = getCdrServ().listar(cobCdrB);
        } catch (Exception ex) {
            Logger.getLogger(CdrBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" =========== buscarCdr = " + new Date());
    }

    public CobCdr getCobCdrB() {
        return cobCdrB;
    }

    public void setCobCdrB(CobCdr cobCdrB) {
        this.cobCdrB = cobCdrB;
    }

    public List<CobCdr> getCobCdrs() {
        return cobCdrs;
    }

    public void setCobCdrs(List<CobCdr> cobCdrs) {
        this.cobCdrs = cobCdrs;
    }

    public ICdrServ getCdrServ() {
        return cdrServ;
    }

    public void setCdrServ(ICdrServ cdrServ) {
        this.cdrServ = cdrServ;
    }

    public List<CobDevice> getCobDevices() {
        return cobDevices;
    }

    public void setCobDevices(List<CobDevice> cobDevices) {
        this.cobDevices = cobDevices;
    }

    public CobDevice getCobDeviceB() {
        return cobDeviceB;
    }

    public void setCobDeviceB(CobDevice cobDeviceB) {
        this.cobDeviceB = cobDeviceB;
    }

}
