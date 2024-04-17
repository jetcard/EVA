/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.iface.ICompromisoServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.servicio.CompromisoServ;
import pop.webcobranzas.servicio.FondoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class CompromisoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // CobCompromiso por busqueda
    private CobCompromiso cobCompromiso = new CobCompromiso();
    // CobSeguimiento por busqueda
    private CobSeguimiento cobSeguimiento = new CobSeguimiento();
    // CobMaeSeguimiento por busqueda
    private CobMaeSeguimiento cobMaeSeguimiento = new CobMaeSeguimiento();
    // CobMaeSeguimiento por busqueda
    private MaeInversion maeInversion = new MaeInversion();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();

    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista compromiso
    private List<CobCompromiso> cobCompromisoList;
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // sericios de llamadas
    private ICompromisoServ compromisoServ = new CompromisoServ();

    /**
     * Creates a new instance of CompromisoBean
     */
    public CompromisoBean() {
        maeInversion.setMaeFondo(maeFondo);
        cobMaeSeguimiento.setMaeInversion(maeInversion);
        cobSeguimiento.setCobMaeSeguimiento(cobMaeSeguimiento);
        cobCompromiso.setCobSeguimiento(cobSeguimiento);
        
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void buscarCompromisos(){
        
        try {
            cobCompromisoList = this.getCompromisoServ().listarDeposito(cobCompromiso);
        } catch (Exception ex) {
            Logger.getLogger(CompromisoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("pop.webcobranzas.bean.CompromisoBean.buscarCompromisos()");
    }
    
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
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

    public ICompromisoServ getCompromisoServ() {
        return compromisoServ;
    }

    public void setCompromisoServ(ICompromisoServ compromisoServ) {
        this.compromisoServ = compromisoServ;
    }

    public List<CobCompromiso> getCobCompromisoList() {
        return cobCompromisoList;
    }

    public void setCobCompromisoList(List<CobCompromiso> cobCompromisoList) {
        this.cobCompromisoList = cobCompromisoList;
    }

    public CobCompromiso getCobCompromiso() {
        return cobCompromiso;
    }

    public void setCobCompromiso(CobCompromiso cobCompromiso) {
        this.cobCompromiso = cobCompromiso;
    }

    public CobSeguimiento getCobSeguimiento() {
        return cobSeguimiento;
    }

    public void setCobSeguimiento(CobSeguimiento cobSeguimiento) {
        this.cobSeguimiento = cobSeguimiento;
    }

    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

}
