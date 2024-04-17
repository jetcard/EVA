/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pop.comun.dominio.MaePersona;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.iface.IPersonaServ;
import pop.webcobranzas.servicio.PersonaServ;

/**
 *
 * @author Jyoverar
 */
@Named
@RequestScoped
public class PersonaBean {

    private List<MaePersona> listPersonas;
    private IPersonaServ personaServ;
    /**
     * Creates a new instance of PersonaBean
     */
    public PersonaBean() {
        
    }
    
    public void listartPersona(){
         try {
             System.out.println("["+SessionUtils.getUserName()+"] "+" PersonaBean - listartPersona ");
            listPersonas = getPersonaServ().listarPersonaUlt();
            //System.out.println("pop.webcobranzas.bean.PersonaBean.<init>()");
            //System.out.println("   Lista de Personas = " + listPersonas.size());
        } catch (Exception e) {
            
        }
        
    }

    /**
     * @return the listPersonas
     */
    public List<MaePersona> getListPersonas() {
        return listPersonas;
    }

    /**
     * @param listPersonas the listPersonas to set
     */
    public void setListPersonas(List<MaePersona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    /**
     * @return the personaServ
     */
    public PersonaServ getPersonaServ() {
        return new PersonaServ();
    }

    /**
     * @param personaServ the personaServ to set
     */
    public void setPersonaServ(PersonaServ personaServ) {
        this.personaServ = personaServ;
    }
    
}
