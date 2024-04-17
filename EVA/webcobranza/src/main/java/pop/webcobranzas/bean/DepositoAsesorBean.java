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
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaeAsesor;
import pop.webcobranzas.iface.IAsesorServ;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.servicio.AsesorServ;
import pop.webcobranzas.servicio.DepositoServ;
import pop.webcobranzas.servicio.FondoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class DepositoAsesorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // CobCompromiso por busqueda
    private CobCompromiso cobCompromiso = new CobCompromiso();
    // CobMaeSeguimiento por busqueda
    private MaeInversion maeInversion = new MaeInversion();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    // deposito para busqueda
    private MaeDeposito maeDeposito = new MaeDeposito();
    // asesor para busqueda
    private MaeAsesor maeAsesor = new MaeAsesor();
            
    // lista de fondos
    private List<MaeFondo> maeFondoList;
    // lista Depositos
    private List<MaeDeposito> maeDepositoList;
    // lista Depositos
    private List<MaeAsesor> maeAsesorList;
    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // servicios de depositos
    private IDepositoServ depositoServ = new DepositoServ();
    // servicios de asesor
    private IAsesorServ asesorServ = new AsesorServ();
    
    /**
     * Creates a new instance of CompromisoBean
     */
    public DepositoAsesorBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeDeposito.setMaeInversion(maeInversion);
        maeDeposito.setCobCompromiso(cobCompromiso);
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }

    public void buscarDepositosComp(){
        
        try {            
            maeDepositoList = this.getDepositoServ().listarDepositoCompromiso(maeDeposito);
        } catch (Exception ex) {
            Logger.getLogger(DepositoAsesorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("pop.webcobranzas.bean.CompromisoBean.buscarCompromisos()");
    }
        
    public void listarAsesor() {
        try {
            MaeAsesor oMaeAsesor = new MaeAsesor();
            oMaeAsesor.setCtipo("U"); //Gestor Usuario
            
            maeAsesorList = getAsesorServ().listarAsesor(oMaeAsesor);
            System.out.println(" Cantidad de Asesor Usuario-> " + maeAsesorList.size());
        } catch (Exception e) {

        }
    }

    public void listarAsesorMeta() {
        try {
            MaeAsesor oMaeAsesor = new MaeAsesor();
            oMaeAsesor.setCtipo("M"); //Gestor Meta
            
            maeAsesorList = getAsesorServ().listarAsesor(oMaeAsesor);
            System.out.println(" Cantidad de Asesor Meta-> " + maeAsesorList.size());
        } catch (Exception e) {

        }
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

    
    public MaeAsesor getMaeAsesor() {
        return maeAsesor;
    }
    
    public void setMaeAsesor(MaeAsesor maeAsesor) {
        this.maeAsesor = maeAsesor;
    }

    public List<MaeAsesor> getMaeAsesorList() {
        return maeAsesorList;
    }

    public void setMaeAsesorList(List<MaeAsesor> maeAsesorList) {
        this.maeAsesorList = maeAsesorList;
    }
    
     public IAsesorServ getAsesorServ() {
        return asesorServ;
    }

    public void setAsesorServ(IAsesorServ asesorServ) {
        this.asesorServ = asesorServ;
    }

    
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public List<MaeDeposito> getMaeDepositoList() {
        return maeDepositoList;
    }

    public void setMaeDepositoList(List<MaeDeposito> maeDepositoList) {
        this.maeDepositoList = maeDepositoList;
    }

    public IDepositoServ getDepositoServ() {
        return depositoServ;
    }

    public void setDepositoServ(IDepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }

    public CobCompromiso getCobCompromiso() {
        return cobCompromiso;
    }

    public void setCobCompromiso(CobCompromiso cobCompromiso) {
        this.cobCompromiso = cobCompromiso;
    }

    public MaeDeposito getMaeDeposito() {
        return maeDeposito;
    }

    public void setMaeDeposito(MaeDeposito maeDeposito) {
        this.maeDeposito = maeDeposito;
    }

    
    
}
