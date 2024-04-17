/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import pop.comun.dominio.MaeDeposito;
import pop.comun.dominio.MaeFondo;
import pop.comun.dominio.MaeInversion;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.iface.IDepositoServ;
import pop.webcobranzas.servicio.DepositoServ;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class DepositoBusMesBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private MaeDeposito deposito = new MaeDeposito();
    private MaeFondo maeFondo =new MaeFondo();
    private List<MaeDeposito> listDepositos;
    private List<MaeDeposito> listDepo;
    private MaeInversion maeInversion = new MaeInversion();
    private MaePersona maePersona = new MaePersona();
    private IDepositoServ depositoServ;
    
    
    /**
     * Creates a new instance of DepositoBusBean
     */
    public DepositoBusMesBean() {
        maeInversion.setMaeFondo(maeFondo);
        maeInversion.setcPersonaId(maePersona);
        deposito.setMaeInversion(maeInversion);
    }
    
    public String actualizarDepo(){
         try {
            listDepositos = getDepositoServ().listarDepositosMes(deposito);
            System.out.println("pop.webcobranzas.bean.depositoBusBeanMes.<init>()");
            System.out.println("   Lista de Depositos = " + listDepositos.size());
            
        } catch (Exception e) {
            System.out.println("============== actualizarDepo + " + e.getMessage());
        }
         
         return "reporteOperacionMes";
         
    }
    

    public List<MaeDeposito> getListDepositos() {
        return listDepositos;
    }

    public void setListDepositos(List<MaeDeposito> listDepositos) {
        this.listDepositos = listDepositos;
    }

    public List<MaeDeposito> getListDepo() {
        return listDepo;
    }

    public void setListDepo(List<MaeDeposito> listDepo) {
        this.listDepo = listDepo;
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
     * @return the depositoServ
     */
    public DepositoServ getDepositoServ() {
        return new DepositoServ();
    }

    /**
     * @param depositoServ the depositoServ to set
     */
    public void setDepositoServ(DepositoServ depositoServ) {
        this.depositoServ = depositoServ;
    }

    /**
     * @return the maeInversion
     */
    public MaeInversion getMaeInversion() {
        return maeInversion;
    }

    /**
     * @param maeInversion the maeInversion to set
     */
    public void setMaeInversion(MaeInversion maeInversion) {
        this.maeInversion = maeInversion;
    }

    /**
     * @return the maePersona
     */
    public MaePersona getMaePersona() {
        return maePersona;
    }

    /**
     * @param maePersona the maePersona to set
     */
    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    /**
     * @return the maeFondo
     */
    public MaeFondo getMaeFondo() {
        return maeFondo;
    }

    /**
     * @param maeFondo the maeFondo to set
     */
    public void setMaeFondo(MaeFondo maeFondo) {
        this.maeFondo = maeFondo;
    }
    
    
    
}

