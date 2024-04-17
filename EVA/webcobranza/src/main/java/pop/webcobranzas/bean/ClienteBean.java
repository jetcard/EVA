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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInmueble;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.comun.dominio.MaeUbigeo;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.iface.IFondoServ;
import pop.webcobranzas.iface.IInversionServ;
import pop.webcobranzas.servicio.DepositoServ;
import pop.webcobranzas.servicio.FondoServ;
import pop.webcobranzas.servicio.InversionServ;

/**
 *
 * @author Jyoverar
 */
@Named
@ViewScoped 
public class ClienteBean implements Serializable {
      
    private static final long serialVersionUID = 1L;
    
    private MaeInversion maeInversion = new MaeInversion();
    private MaeDeposito maeDeposito = new MaeDeposito();  
    private MaeFondo maeFondo = new MaeFondo();
    private MaePersona maePersona = new MaePersona();
    private MaeInmueble maeInmueble = new MaeInmueble();
    private MaeUbigeo maeUbigeo = new MaeUbigeo();
    private IFondoServ fondoServ = new FondoServ();
    private IDepositoServ depositoServ = new DepositoServ();
    private List<MaeDeposito> listDepositos =new ArrayList<>();
    
    private List<MaeInversion> maeInversionList;
    // servicios de inversiones
    private IInversionServ inversionServ = new InversionServ();
    
    private Date finicio;
    private Date ffin;

    // lista de fondos
    private List<MaeFondo> maeFondoList;

    
    
    public IFondoServ getFondoServ() {
        return fondoServ;
    }

    public void setFondoServ(IFondoServ fondoServ) {
        this.fondoServ = fondoServ;
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

    public List<MaeFondo> getMaeFondoList() {
        return maeFondoList;
    }

    public void setMaeFondoList(List<MaeFondo> maeFondoList) {
        this.maeFondoList = maeFondoList;
    }

    /**
     * Creates a new instance of ClienteBusquedaRed
     */
    public ClienteBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setcPersonaId(maePersona);
        maeInversion.setMaeInmueble(maeInmueble);
        maeDeposito.setMaeInversion(maeInversion);
        
    }

    public void listarPropietarios() {
        try {
            System.out.println("pop.webcobranzas.bean.ClienteBean.listarPropietarios()");

            maeInmueble.setMaeUbigeo(maeUbigeo);
            maeInversionList = getInversionServ().listarRedInversion(maeInversion);
            
            System.out.println("   Lista de listarPropietarios = " + maeInversionList.size());

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
    
     public void listarFondos() {
        try {
            maeFondoList = getFondoServ().listarFondos(new MaeFondo());
            System.out.println(" cantidad de Fondos -> " + maeFondoList.size());
        } catch (Exception e) {

        }
    }
     
     public void listarInmueble() {
        try {
            System.out.println("pop.webcobranzas.bean.ClienteBean.listarInmueble()");
            maeInmueble.setMaeUbigeo(maeUbigeo);
            maeInversionList = getInversionServ().listarInmueble(maeInversion);

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
     
     public void listarCronoEstado() {
        try {
            System.out.println("pop.webcobranzas.bean.ClienteBean.listarCronoEstado");
            maeInversionList = getInversionServ().listarCronoEstado(maeInversion);
            System.out.println("   Lista de listarPropietarios = " + maeInversionList.size());

        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
    
      public void  listarNoDeposito(){
         try {
             System.out.print("Cantidad ttr "+maeInversion.getMaeFondo().getCFondoId());
             System.out.print("Cantidad ttr "+maeDeposito.getMaeInversion().getCInversion());
             System.out.print("Cantidad ttr "+maeDeposito.getMaeInversion().getDescripcion());
            listDepositos = getDepositoServ().listarNoDeposito(maeDeposito);
       
       
        } catch (Exception e) {
           
        }
         
    }
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    public MaeInmueble getMaeInmueble() {
        return maeInmueble;
    }

    public void setMaeInmueble(MaeInmueble maeInmueble) {
        this.maeInmueble = maeInmueble;
    }

    public MaeUbigeo getMaeUbigeo() {
        return maeUbigeo;
    }

    public void setMaeUbigeo(MaeUbigeo maeUbigeo) {
        this.maeUbigeo = maeUbigeo;
    }

    

    public List<MaeInversion> getMaeInversionList() {
        return maeInversionList;
    }

    public void setMaeInversionList(List<MaeInversion> maeInversionList) {
        this.maeInversionList = maeInversionList;
    }

    public IInversionServ getInversionServ() {
        return inversionServ;
    }

    public void setInversionServ(IInversionServ inversionServ) {
        this.inversionServ = inversionServ;
    }
 

    public IDepositoServ getDepositoServ() {
        return depositoServ;
    }

    public void setDepositoServ(IDepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }    
    public List<MaeDeposito> getListDepositos() {
        return listDepositos;
    }

    public void setListDepositos(List<MaeDeposito> listDepositos) {
        this.listDepositos = listDepositos;
    }    
   
     public MaeDeposito getMaeDeposito() {
        return maeDeposito;
    }

    public void setMaeDeposito(MaeDeposito maeDeposito) {
        this.maeDeposito = maeDeposito;
    }
}
