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
import pop.comun.dominio.LaftPersona;
import pop.webcobranzas.iface.ILaftPersonaServ;
import pop.webcobranzas.servicio.LaftPersonaServ;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class PersonaLaftBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private LaftPersona laftPersona =new LaftPersona();
    private ILaftPersonaServ laftPersonaServ=new LaftPersonaServ();
    private List<LaftPersona> listLaftPersona;
    
    /**
     * Creates a new instance of PersonaLaftBean
     */
    public PersonaLaftBean() {
    }
    
    public String buscarLaftPersona(){
         try {
            setListLaftPersona(getLaftPersonaServ().listarLaftPersona(laftPersona));
            System.out.println("pop.webcobranzas.bean.PersonaLaftBean.<init>()");
            System.out.println("   Lista de Depositos = " + getListLaftPersona().size());
            
        } catch (Exception e) {
            
        }
         return "personasReportadas";
    }
    
    public String ingresarLaftPersona(){
         try {
            int new_id = getLaftPersonaServ().insert(laftPersona);
            System.out.println("pop.webcobranzas.bean.PersonaLaftBean.<init>()");
            System.out.println("   new_id = " + new_id);
            
        } catch (Exception e) {
            
        }
         return "registrarPerReportada";
    }
    
    
    public LaftPersona getLaftPersona() {
        return laftPersona;
    }
    
    

    public void setLaftPersona(LaftPersona laftPersona) {
        this.laftPersona = laftPersona;
    }

    

    /**
     * @return the listLaftPersona
     */
    public List<LaftPersona> getListLaftPersona() {
        return listLaftPersona;
    }

    /**
     * @param listLaftPersona the listLaftPersona to set
     */
    public void setListLaftPersona(List<LaftPersona> listLaftPersona) {
        this.listLaftPersona = listLaftPersona;
    }

    public ILaftPersonaServ getLaftPersonaServ() {
        return laftPersonaServ;
    }

    public void setLaftPersonaServ(ILaftPersonaServ laftPersonaServ) {
        this.laftPersonaServ = laftPersonaServ;
    }

    
    
}
