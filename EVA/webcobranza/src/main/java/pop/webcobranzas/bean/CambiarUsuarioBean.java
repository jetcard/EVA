/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean; 

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;
import pop.properties.ManageProperties;
import pop.webcobranzas.filter.SessionUtils;

import pop.webcobranzas.webservices.SeguridadWS;
import pop.webcobranzas.webservices.SeguridadWS_Service;
import pop.webcobranzas.webservices.Usuario;


@Named
@ViewScoped
public class CambiarUsuarioBean implements Serializable {
	ManageProperties properties=new ManageProperties();
	
    @WebServiceRef
    private SeguridadWS_Service service;

    private static final long serialVersionUID = 1L;

    // usuario web service
    private Usuario usuario = new Usuario();
    private Usuario usuarioSession = new Usuario();
    // flat de msj de error
    private String flgError;

    private String username;

    private String contraseniaA;
    private String contraseniaB;

    /**
     * Creates a new instance of LoginBean
     */
    public CambiarUsuarioBean() {
        System.out.println("pop.webcobranzas.bean.CambiarUsuarioBean.<init>()");
        flgError = "";
    }

    //validate login
    public String changePassword() {

        // Call Web Service Operation           
        System.out.println("pop.webcobranzas.bean.LoginBean.validateUsernamePassword()");

        System.out.println("contraseniaA - > " + contraseniaA);
        System.out.println("contraseniaB - > " + contraseniaB);
        
    
        if (contraseniaA.equals(contraseniaB)) {
            usuario.setContraseniaB(contraseniaA);
            String ls_Cadena=usuario.getContraseniaB();
            Boolean b_mayus=false;
            Boolean b_sig=false ;
            Boolean b_sig2=true ;
            Boolean b_num=false ;
            String ls_nueva_cadena="";
            // validacion de longitud de la contraseña 
            if (usuario.getContraseniaB().length()>=0 && usuario.getContraseniaB().length()<=8 ) {
                 FacesContext.getCurrentInstance().addMessage(
                            "btnIngresar",
                            new FacesMessage("Error de contraseña: no puede ser menor o igual a 8 digitos "));
                   return "cambiouser";  
            }
            // validacion de politica de seguridad
              for (int ll_aux = 0; ll_aux < ls_Cadena.length() ; ++ll_aux) {
                    ls_nueva_cadena=ls_Cadena.trim().substring(ll_aux,ll_aux+1);
                    
                    if ("QWERTYUIOPASDFGHJKLÑZXCVBNM".contains(ls_nueva_cadena)) {
                            b_mayus=true;
                     }
                    if (";:$#%!&+/-@".contains(ls_nueva_cadena)){
                            b_sig=true;			
                    }
                    char caracter = ls_nueva_cadena.toUpperCase().charAt(0);
                    int valorASCII = (int)caracter;
                    if (valorASCII == 42 )
                    {
                        b_sig=true;
                    }  
                    
                    if (valorASCII  == 39 || valorASCII == 34 || valorASCII == 40 || valorASCII == 41 || valorASCII == 46 || valorASCII == 60 || valorASCII == 61 || valorASCII == 62 || valorASCII == 63   ){
                         b_sig2=false;  
                       
                    }
                    
                    if (valorASCII  == 91 || valorASCII == 92 || valorASCII == 93 || valorASCII == 94 || valorASCII == 95 || valorASCII == 96 ){
                         b_sig2=false;  
                         
                    }
                    
                    if (valorASCII  == 123 || valorASCII == 124 || valorASCII == 125 || valorASCII == 126 || valorASCII == 168){
                         b_sig2=false;  
                          
                    }
                    
                    if ("0123456789".contains(ls_nueva_cadena)) {
                            b_num=true;		
                     }     
                }
              if (!b_mayus || !b_sig || !b_num || !b_sig2) 
               {  
                         FacesContext.getCurrentInstance().addMessage(
                                "btnIngresar",
                                new FacesMessage("Error de contraseña: no cumple con la politica de seguridad,  debe contener minimo una mayuscula, un numero, y un caracter especial ;:$#%!&+/-@ "));
                         return  "cambiouser"; 
               }
            
            try { // Call Web Service Operation
                SeguridadWS port = service.getSeguridadWSPort();

                Map<String, Object> reqMap = ((BindingProvider) port).getRequestContext();
                Map<String, List<String>> header = new HashMap<>();
                header.put("usernamews", Collections.singletonList("wsValUseEVA"));
                header.put("passwordws", Collections.singletonList("1055c7dc7cc40630f99875b72082ce33243951396b91081685ccdab3c9114d135739561abc38291be26c25b8bc1490e07036bf8512e16a27ea58ee0b12b10f56"));
                reqMap.put(MessageContext.HTTP_REQUEST_HEADERS, header);

                System.out.println("SessionUtils.getUserName() -> " + SessionUtils.getUserName());
                System.out.println("contraseniaA - > " + contraseniaA);
                usuario.setUsuarioId(SessionUtils.getUserName());
                // TODO process result here
                //System.out.println("  -----> port " + port.cambiarUsuario(usuario));
                usuarioSession = port.cambiarUsuario(usuario);
                if (usuarioSession.isEstado()) {
                    return "/dashboard?faces-redirect=true";
                } else {
                    username = "";
                    setFlgError("E");
                    FacesContext.getCurrentInstance().addMessage(
                            "btnIngresar",
                            new FacesMessage("Error de contraseña: ya fue utilizada anteriormete."));
                    return "cambiouser";
                }
            } catch (Exception ex) {
                // TODO handle custom exceptions here

                return "/pages/sistema/cambiouser?faces-redirect=true";
            }
        } else {
            username = "";
            setFlgError("E");
            FacesContext.getCurrentInstance().addMessage(
                    "btnIngresar",
                    new FacesMessage("Error de contraseña: no coinciden las dos contraseñas"));
            return "cambiouser";
        }

    }

    public String getFlgError() {
        return flgError;
    }

    public void setFlgError(String flgError) {
        this.flgError = flgError;
    }

    public String getUsername() {
        return username.toUpperCase();
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public String getContraseniaA() {
        return contraseniaA;
    }

    public void setContraseniaA(String contraseniaA) {
        this.contraseniaA = contraseniaA;
    }

    public String getContraseniaB() {
        return contraseniaB;
    }

    public void setContraseniaB(String contraseniaB) {
        this.contraseniaB = contraseniaB;
    }

}
