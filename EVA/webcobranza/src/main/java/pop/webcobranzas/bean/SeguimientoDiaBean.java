/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pop.comun.dominio.CobCompromiso;
import pop.comun.dominio.CobCronogramaMetaDetalle;
import pop.comun.dominio.CobLlamadas;
import pop.comun.dominio.CobMaeSeguimiento;
import pop.comun.dominio.CobSeguimiento;
import pop.comun.dominio.CobSeguimientoDet;
import pop.comun.dominio.MaeAsesor;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IAsesorServ;
import pop.webcobranzas.iface.ICompromisoServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.ILlamadaServ;
import pop.webcobranzas.servicio.AsesorServ;
import pop.webcobranzas.servicio.CompromisoServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.LlamadaServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped
public class SeguimientoDiaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // maeseguimiento principal
    private CobMaeSeguimiento cobMaeSeguimiento = new CobMaeSeguimiento();
    // cob seguimiento
    private CobSeguimiento cobSeguimiento = new CobSeguimiento();
    // llamadas
    private CobLlamadas cobLlamadas = new CobLlamadas();
    // compromiso
    private CobCompromiso cobCompromiso = new CobCompromiso();
    // fondo (busq por fondo)
    private MaeFondo maeFondo = new MaeFondo();
    
    private MaeDeposito deposito = new MaeDeposito();    
    
    private Date finicio;
    private Date ffin;

    // lista de fondos
    private List<MaeFondo> maeFondoList;
    
    // asesor meta
    private MaeAsesor asesorMeta = new MaeAsesor();
    private List<MaeAsesor> asesorMetaList;
    
    // asesor contacto
    private MaeAsesor asesorContacto = new MaeAsesor();  
    private List<MaeAsesor> asesorContactoList;
    
    // inversion
    private MaeInversion inversion = new MaeInversion();
    
    
    

    private List<CobSeguimientoDet> cobSeguimientoDetList;

    // servicios de fondos
    private IFondoServ fondoServ = new FondoServ();
    // sericios de llamadas
    private ILlamadaServ llamadaServ = new LlamadaServ();
    // sericios de llamadas
    private ICompromisoServ compromisoServ = new CompromisoServ();
    // servicios de asesor
    private IAsesorServ asesorServ = new AsesorServ();    

    /**
     * Creates a new instance of SeguimientoDiaBean
     */
    public SeguimientoDiaBean() {
    }

    public void listarAsesor() {
        try {
            MaeAsesor oMaeAsesor = new MaeAsesor();
            oMaeAsesor.setCtipo("U"); //Gestor Usuario
            
            asesorContactoList = getAsesorServ().listarAsesor(oMaeAsesor);
            System.out.println(" Cantidad de Asesor Usuario-> " + asesorContactoList.size());
        } catch (Exception e) {

        }
    }

    public void listarAsesorMeta() {
        try {
            MaeAsesor oMaeAsesor = new MaeAsesor();
            oMaeAsesor.setCtipo("M"); //Gestor Meta
            
            asesorMetaList = getAsesorServ().listarAsesor(oMaeAsesor);
            System.out.println(" Cantidad de Asesor Meta-> " + asesorMetaList.size());
        } catch (Exception e) {

        }
    }
    
    public void buscarResumen(){
        try {
            CobCronogramaMetaDetalle meta = new CobCronogramaMetaDetalle();
            meta.setC_usuario_id(asesorMeta.getCusuarioId());
            
            cobLlamadas.setfIniBusq(finicio);
            cobLlamadas.setfFinBusq(ffin);
            cobLlamadas.setcUsuarioAdd(getAsesorContacto().getCusuarioId());
            
            inversion.setCmoneda(deposito.getcMonedaId());
            inversion.setMaeFondo(maeFondo);
            
            cobMaeSeguimiento.setMaeInversion(inversion);
            cobSeguimiento.setMetaDetalle(meta);
            cobSeguimiento.setCobMaeSeguimiento(cobMaeSeguimiento);
            cobLlamadas.setCobSeguimiento(cobSeguimiento);
            
            cobSeguimientoDetList = new ArrayList<>();
            cobSeguimientoDetList.clear();
            for (CobLlamadas cl : getLlamadaServ().listarResumen(cobLlamadas)) {
                cobSeguimientoDetList.add(cl);
                
            }
            /*
            cobCompromiso.setfIniBusq(finicio);
            cobCompromiso.setfFinBusq(ffin);
            cobCompromiso.setcUsuarioAdd(SessionUtils.getUserName());
            for (CobCompromiso cp : getCompromisoServ().listar(cobCompromiso)) {
                cobSeguimientoDetList.add(cp);
            }
            */

            System.out.println("total :---> "+cobSeguimientoDetList.size());
        } catch (Exception ex) {
            Logger.getLogger(SeguimientoDiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void buscar() {
        try {
            cobLlamadas.setfIniBusq(finicio);
            cobLlamadas.setfFinBusq(ffin);
            cobLlamadas.setcUsuarioAdd(SessionUtils.getUserName());
            cobSeguimientoDetList = new ArrayList<>();
            cobSeguimientoDetList.clear();
            for (CobLlamadas cl : getLlamadaServ().listar(cobLlamadas)) {
                cobSeguimientoDetList.add(cl);
            }
            
            cobCompromiso.setfIniBusq(finicio);
            cobCompromiso.setfFinBusq(ffin);
            cobCompromiso.setcUsuarioAdd(SessionUtils.getUserName());
            for (CobCompromiso cp : getCompromisoServ().listar(cobCompromiso)) {
                cobSeguimientoDetList.add(cp);
            }

            System.out.println("  tamanoooooo---> "+cobSeguimientoDetList.size());
        } catch (Exception ex) {
            Logger.getLogger(SeguimientoDiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
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

    public CobLlamadas getCobLlamadas() {
        return cobLlamadas;
    }

    public void setCobLlamadas(CobLlamadas cobLlamadas) {
        this.cobLlamadas = cobLlamadas;
    }

    public CobMaeSeguimiento getCobMaeSeguimiento() {
        return cobMaeSeguimiento;
    }

    public void setCobMaeSeguimiento(CobMaeSeguimiento cobMaeSeguimiento) {
        this.cobMaeSeguimiento = cobMaeSeguimiento;
    }

    public CobSeguimiento getCobSeguimiento() {
        return cobSeguimiento;
    }

    public void setCobSeguimiento(CobSeguimiento cobSeguimiento) {
        this.cobSeguimiento = cobSeguimiento;
    }

    public List<CobSeguimientoDet> getCobSeguimientoDetList() {
        return cobSeguimientoDetList;
    }

    public void setCobSeguimientoDetList(List<CobSeguimientoDet> cobSeguimientoDetList) {
        this.cobSeguimientoDetList = cobSeguimientoDetList;
    }

    public ILlamadaServ getLlamadaServ() {
        return llamadaServ;
    }

    public void setLlamadaServ(ILlamadaServ llamadaServ) {
        this.llamadaServ = llamadaServ;
    }

    public Date getFinicio() {
        return finicio;
    }

    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    public Date getFfin() {
        return ffin;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    public CobCompromiso getCobCompromiso() {
        return cobCompromiso;
    }

    public void setCobCompromiso(CobCompromiso cobCompromiso) {
        this.cobCompromiso = cobCompromiso;
    }

    public ICompromisoServ getCompromisoServ() {
        return compromisoServ;
    }

    public void setCompromisoServ(ICompromisoServ compromisoServ) {
        this.compromisoServ = compromisoServ;
    }

   
    /**
     * @return the inversion
     */
    public MaeInversion getInversion() {
        return inversion;
    }

    /**
     * @param inversion the inversion to set
     */
    public void setInversion(MaeInversion inversion) {
        this.inversion = inversion;
    }

    /**
     * @return the asesorMeta
     */
    public MaeAsesor getAsesorMeta() {
        return asesorMeta;
    }

    /**
     * @param asesorMeta the asesorMeta to set
     */
    public void setAsesorMeta(MaeAsesor asesorMeta) {
        this.asesorMeta = asesorMeta;
    }

    /**
     * @return the asesorContacto
     */
    public MaeAsesor getAsesorContacto() {
        return asesorContacto;
    }

    /**
     * @param asesorContacto the asesorContacto to set
     */
    public void setAsesorContacto(MaeAsesor asesorContacto) {
        this.asesorContacto = asesorContacto;
    }

    /**
     * @return the deposito
     */
    public MaeDeposito getDeposito() {
        return deposito;
    }

    /**
     * @param deposito the deposito to set
     */
    public void setDeposito(MaeDeposito deposito) {
        this.deposito = deposito;
    }

    /**
     * @return the asesorContactoList
     */
    public List<MaeAsesor> getAsesorContactoList() {
        return asesorContactoList;
    }

    /**
     * @param asesorContactoList the asesorContactoList to set
     */
    public void setAsesorContactoList(List<MaeAsesor> asesorContactoList) {
        this.asesorContactoList = asesorContactoList;
    }

    /**
     * @return the asesorMetaList
     */
    public List<MaeAsesor> getAsesorMetaList() {
        return asesorMetaList;
    }

    /**
     * @param asesorMetaList the asesorMetaList to set
     */
    public void setAsesorMetaList(List<MaeAsesor> asesorMetaList) {
        this.asesorMetaList = asesorMetaList;
    }

    /**
     * @return the asesorServ
     */
    public IAsesorServ getAsesorServ() {
        return asesorServ;
    }

    /**
     * @param asesorServ the asesorServ to set
     */
    public void setAsesorServ(IAsesorServ asesorServ) {
        this.asesorServ = asesorServ;
    }

}
