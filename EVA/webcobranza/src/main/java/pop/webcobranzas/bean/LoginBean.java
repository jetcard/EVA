/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.bean;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException; 
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;
import pop.properties.ManageProperties;
import pop.webcobranzas.filter.SessionUtils;
import pop.webcobranzas.webservices.Menu;
import pop.webcobranzas.webservices.SeguridadWS;
import pop.webcobranzas.webservices.SeguridadWS_Service;
import pop.webcobranzas.webservices.Usuario;


/**
 *
 * @author Jyoverar
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
    @WebServiceRef
    private SeguridadWS_Service service;
    private static final long serialVersionUID = 1L;
    // usuario web service
    private Usuario usuario = new Usuario();
    private Usuario usuarioSession = new Usuario();
    // flat de msj de error
    private String flgError;

    private String username;

    private boolean fnavBar = true;

    private int nllamada;
    private String numAsesor;
    private String mailAsesor;
    private String lastName;
    private String name;
    private String urlServidorWeb;
    
    private String contraseniaA;
    private String contraseniaB;
    private String codigoGenerado;
    private String pageTitle;
	
    // UsuarioBean oUsuarioBean= new UsuarioBean();
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        //System.out.println("pop.webcobranzas.bean.LoginBean.<init>()");
        flgError = "";
    }

    //validate login
    public String validateUsernamePassword() {

        try { //recuperando la ruta actual del web service
            ManageProperties properties=new ManageProperties();
            setUrlServidorWeb(properties.getUrl_servidorweb());
            System.out.println("   pop.webcobranzas.bean.LoginBean.validateUsernamePassword()");
            try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();  // IP
                }
                InetAddress addr = InetAddress.getByName(ipAddress);  // DOMAIN NAME from IP
                String host = addr.getHostName();
                System.out.println("   MAC ipAddress -->" + ipAddress);
                System.out.println("   MAC host -->" + host);

                String userAgent = request.getHeader("User-Agent");
                String os = "";
                String browser = "";
                System.out.println("   USERAGENT -> " + userAgent);
                if (userAgent.toLowerCase().indexOf("windows") >= 0) {
                    os = "windows";
                } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
                    os = "android";
                } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
                    os = "iphone";
                } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
                    os = "Unix";
                } else {
                    os = "Desconocido";
                }
                System.out.println("   OS -> " + os);
                String  user            =   userAgent.toLowerCase();
                if (user.contains("msie")) {
                    String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
                    browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
                } else if (user.contains("safari") && user.contains("version")) {
                    browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                } else if (user.contains("opr") || user.contains("opera")) {
                    if (user.contains("opera")) {
                        browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                    } else if (user.contains("opr")) {
                        browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
                    }
                } else if (user.contains("chrome")) {
                    browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
                } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
                    browser = "Netscape-?";

                } else if (user.contains("firefox")) {
                    browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
                } else if (user.contains("rv")) {
                    browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
                } else {
                    browser = "UnKnown, More-Info: " + userAgent;
                }
                System.out.println("   BROWSER -> " + browser);

            } catch (UnknownHostException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // nuevo ws
            try { // Call Web Service Operation
                SeguridadWS port = service.getSeguridadWSPort();
                Map<String, Object> reqMap = ((BindingProvider) port).getRequestContext();
                Map<String, List<String>> header = new HashMap<>();
                header.put("usernamews", Collections.singletonList("wsValUseEVA"));
                header.put("passwordws", Collections.singletonList("1055c7dc7cc40630f99875b72082ce33243951396b91081685ccdab3c9114d135739561abc38291be26c25b8bc1490e07036bf8512e16a27ea58ee0b12b10f56"));
                reqMap.put(MessageContext.HTTP_REQUEST_HEADERS, header);
                usuarioSession = port.obtenerUsuario(usuario);
                System.out.println(" UsuarioId --> " + usuarioSession.getUsuarioId());
                System.out.println(" Nom     --> " + usuarioSession.getPerNom());
                System.out.println(" ApePat  -->" + usuarioSession.getPerApePat());
                System.out.println(" ApeMat  -->" + usuarioSession.getPerApeMat());
                System.out.println(" eliminarSeguimiento -->" +  usuarioSession.isEliminarSeguimiento());
                System.out.println(" LISTADO DE MENUS -->" +  usuarioSession.getMenu().size());
              
                
            } catch (Exception ex) {
                // TODO handle custom exceptions here
                System.out.println(ex);
            }

//            System.out.println("Result = "+result.getUsuario().getUsuarioid());
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println(ex);
        }

        //if (usuario.getUsuarioid()!=0) {
        if (usuarioSession.isEstado()) {
            HttpSession session = SessionUtils.getSession();

            session.setAttribute("username", usuarioSession.getUsuarioId().toUpperCase().trim());
            username = usuarioSession.getUsuarioId().toUpperCase().trim();

            nllamada = usuarioSession.getNumLlamada();
            numAsesor = usuarioSession.getTelefono().getTelNumero();
            mailAsesor = usuarioSession.getCorreo().getCorDesc();
            lastName = usuarioSession.getPerApePat() + " " + usuarioSession.getPerApeMat();
            name = usuarioSession.getPerNom();

            System.out.println( "[" + username + "] --> " + usuarioSession.getNumLlamada());
            System.out.println( "[" + username + "] --> " + usuarioSession.getTelefono().getTelNumero());
            System.out.println( "[" + username + "] --> " + usuarioSession.getCorreo().getCorDesc());

            for (Menu arg : usuarioSession.getMenu()) {
                System.out.println("[" + username + "] :---- " + arg.getMenuA());
                for (Menu argb : arg.getOMenuList()) {
                    System.out.println("[" + username + "] :------------ " + argb.getMenuA());
                }                
            }

            session.setAttribute("numAdviser", numAsesor);
            session.setAttribute("emailAdviser", mailAsesor);
            session.setAttribute("lastName", lastName);
            session.setAttribute("name", name);
            session.setAttribute("menulist", usuarioSession.getMenu());
            session.setAttribute("opcionespermisomenulist", usuarioSession.getOpcionesPermisosmenu());
            
            
            for (Menu arg : usuarioSession.getOpcionesPermisosmenu()) {
                System.out.println("[" + username + "] ---- " + arg.isBtn1());
            }

            
            if (usuarioSession.getBcontrasenia().equals("S")) {
                return "/pages/sistema/cambiouser?faces-redirect=true";
            } else {
                return "dashboard?faces-redirect=true";
            }

        } else {
            username = "";
            setFlgError("E");
            FacesContext.getCurrentInstance().addMessage(
                    "btnIngresar",
                    new FacesMessage("Error de usuario o contraseña"));
            return "login";
        }

    }

   //validate login
    public String olvidaContrasenia() {
        System.out.println("olvidaContrasenia");
        HttpSession session = SessionUtils.getSession();
        return "/pages/sistema/solicitarcodigo?faces-redirect=true";
    }
    
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login?faces-redirect=true";
    }

    public void cambiarNavBar() {
        System.out.println("pop.webcobranzas.bean.LoginBean.cambiarNavBar()");
        if (isFnavBar()) {
            setFnavBar(false);
        } else {
            setFnavBar(true);
        }

    }

  //validate login
    public String solicitarCodigo() {

        // Call Web Service Operation           
        System.out.println("solcitarCodigo().username:"+usuario.getUsuarioId());
    
        try { // Call Web Service Operation
                SeguridadWS port = service.getSeguridadWSPort();
                Map<String, Object> reqMap = ((BindingProvider) port).getRequestContext();
                Map<String, List<String>> header = new HashMap<>();
                header.put("usernamews", Collections.singletonList("wsValUseEVA"));
                header.put("passwordws", Collections.singletonList("1055c7dc7cc40630f99875b72082ce33243951396b91081685ccdab3c9114d135739561abc38291be26c25b8bc1490e07036bf8512e16a27ea58ee0b12b10f56"));
                reqMap.put(MessageContext.HTTP_REQUEST_HEADERS, header);
                usuarioSession = port.solicitarCodigo(usuario);
                if (usuarioSession.isEstado()) {
                     System.out.println("usuarioSession.isEstado():"+ Boolean.toString(usuarioSession.isEstado()));
                     return "olvidoclave";
                } else {
                    System.out.println("usuarioSession.error()");
                    username = "";
                    setFlgError("E");
                    FacesContext.getCurrentInstance().addMessage(
                            "btnIngresar",
                            new FacesMessage("Error: codigo no generado."));
                    return "solicitarcodigo";
                }
            } catch (Exception ex) {
                // TODO handle custom exceptions here
                
                return "/pages/sistema/solicitarcodigo?faces-redirect=true";
            }
    }        
    
        //validate login
    public String resetearClave() {

        // Call Web Service Operation           
        System.out.println("1.pop.webcobranzas.bean.OlvidoClaveBean.resetearClave()");
    
        if (contraseniaA.equals(contraseniaB)) {
            System.out.println("2.setContraseniaB");
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
                            new FacesMessage("Error: no puede ser menor o igual a 8 digitos "));
                   return "olvidoclave";  
            }
              
                System.out.println("3.politica de seguridad");
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
                                new FacesMessage("Error: no cumple con la politica de seguridad,  debe contener minimo una mayuscula, un numero, y un caracter especial ;:$#%!&+/-@ "));
                         return  "olvidoclave"; 
               }
            
            try { // Call Web Service Operation
                System.out.println("4.getSeguridadWSPort");
                SeguridadWS port = service.getSeguridadWSPort();

                Map<String, Object> reqMap = ((BindingProvider) port).getRequestContext();
                Map<String, List<String>> header = new HashMap<>();
                header.put("usernamews", Collections.singletonList("wsValUseEVA"));
                header.put("passwordws", Collections.singletonList("1055c7dc7cc40630f99875b72082ce33243951396b91081685ccdab3c9114d135739561abc38291be26c25b8bc1490e07036bf8512e16a27ea58ee0b12b10f56"));
                reqMap.put(MessageContext.HTTP_REQUEST_HEADERS, header);

                System.out.println("5.1.UsuarioId:"+usuario.getUsuarioId());
                usuario.setBcontrasenia(codigoGenerado);
                System.out.println("5.2.codigoGenerado:"+codigoGenerado);
                usuario.setContrasenia(contraseniaA);
                usuario.setContraseniaB(contraseniaB);
                
                //COMPROBACION DE CODIGO GENERADO
                usuarioSession = port.validarCodigo(usuario);
                if (!usuarioSession.isEstado()) {
                    setFlgError("E");
                    FacesContext.getCurrentInstance().addMessage(
                            "btnIngresar",
                            new FacesMessage("Error: codigo inválido."));
                    return "olvidoclave";
                }
                /*else
                {
                    setFlgError("E");
                    FacesContext.getCurrentInstance().addMessage(
                            "btnIngresar",
                            new FacesMessage("VALIDO OK"));
                    return "olvidoclave";
                }*/
                System.out.println("6.CODIGO VALIDO OK");
                
                //RESETEO DE CLAVE
                usuarioSession = port.resetearClave(usuario);
                if (usuarioSession.isEstado()) {
                    System.out.println("RESETEO DE CLAVE OK");
                    usuario.setContrasenia(contraseniaA);
                    return "/"+validateUsernamePassword();
                } else {
                    setFlgError("E");
                    FacesContext.getCurrentInstance().addMessage(
                            "btnIngresar",
                            new FacesMessage("Error: no pudo resetear contraseña."));
                    return "olvidoclave";
                }
                
            } catch (Exception ex) {
                return "/pages/sistema/olvidoclave?faces-redirect=true";
            }
        } else {
            setFlgError("E");
            FacesContext.getCurrentInstance().addMessage(
                    "btnIngresar",
                    new FacesMessage("Error: no coinciden las dos contraseñas."));
            return "olvidoclave";
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

    public boolean isFnavBar() {
        return fnavBar;
    }

    public void setFnavBar(boolean fnavBar) {
        this.fnavBar = fnavBar;
    }

    public int getNllamada() {
        return nllamada;
    }

    public void setNllamada(int nllamada) {
        this.nllamada = nllamada;
    }

    public String getNumAsesor() {
        return numAsesor;
    }

    public void setNumAsesor(String numAsesor) {
        this.numAsesor = numAsesor;
    }

    public String getMailAsesor() {
        return mailAsesor;
    }

    public void setMailAsesor(String mailAsesor) {
        this.mailAsesor = mailAsesor;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	 /**
     * @return the urlServidorWeb
     */
    public String getUrlServidorWeb() {
        return urlServidorWeb;
    }

    /**
     * @param urlServidorWeb the urlServidorWeb to set
     */
    public void setUrlServidorWeb(String urlServidorWeb) {
        this.urlServidorWeb = urlServidorWeb;
    }    

    /**
     * @return the codigoGenerado
     */
    public String getCodigoGenerado() {
        return codigoGenerado;
    }

    /**
     * @param codigoGenerado the codigoGenerado to set
     */
    public void setCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
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

       /**
     * @return the pageTitle
     */
    public String getPageTitle() {
        return pageTitle;
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
