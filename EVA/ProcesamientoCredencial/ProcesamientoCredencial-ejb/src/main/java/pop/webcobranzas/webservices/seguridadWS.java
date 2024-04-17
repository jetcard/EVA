/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.webservices;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
//import org.apache.cxf.configuration.security.AuthorizationPolicy;
//import org.apache.cxf.annotations.EndpointProperties;
//import org.apache.cxf.annotations.EndpointProperty;
import pop.webcobranzas.SessionCredencial;
import pop.webcobranzas.dominio.Usuario;

/**
 *
 * @author Jyoverar
 */
@WebService(serviceName = "seguridadWS")
/*@EndpointProperties(value = {
    @EndpointProperty(key = "ws-security.signature.properties", value = "bob.properties"),
    @EndpointProperty(key = "ws-security.encryption.properties", value = "bob.properties"),
    @EndpointProperty(key = "ws-security.signature.username", value = "bob"),
    @EndpointProperty(key = "ws-security.encryption.username", value = "alice"),
    @EndpointProperty(key = "ws-security.callback-handler", value = "org.jboss.test.ws.jaxws.samples.wsse.policy.basic.KeystorePasswordCallback")
})*/
@Stateless()
public class seguridadWS {

    @EJB
    private SessionCredencial ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @Resource
    private WebServiceContext wsc;

    @WebMethod(operationName = "obtenerUsuario")
    public Usuario obtenerUsuario(@WebParam(name = "oUsuario") Usuario oUsuario) throws Exception {
        if (authTest()) {
            return ejbRef.obtenerUsuario(oUsuario);
        } else {
            return new Usuario();
        }
    }

    @WebMethod(operationName = "cambiarUsuario")
    public Usuario cambiarUsuario(@WebParam(name = "oUsuarioB") Usuario oUsuarioB) throws Exception {
        if (authTest()) {
            return ejbRef.cambiarUsuario(oUsuarioB);
        } else {
            return new Usuario();
        }
    }

   
    public boolean authTest() {
        boolean rpta = false;
        MessageContext mc = wsc.getMessageContext();
        //AuthorizationPolicy authorizationPolicy = (AuthorizationPolicy)mc.get("org.apache.cxf.configuration.security.AuthorizationPolicy");
        Map requestHeader = (Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        
        List userList = (List) requestHeader.get("usernamews");
        List passList = (List) requestHeader.get("passwordws");
        String usernamews = "";
        String passwordws = "";

        if (userList != null && passList != null) {
            usernamews = (String) userList.get(0);
            passwordws = (String) passList.get(0);
            // clave popularsafi - SHA3-512
            if ("wsValUseEVA".equals(usernamews) && 
                    "1055c7dc7cc40630f99875b72082ce33243951396b91081685ccdab3c9114d135739561abc38291be26c25b8bc1490e07036bf8512e16a27ea58ee0b12b10f56".equals(passwordws)) {
                rpta = true;
            }
        }
        return rpta;
    }
    
    @WebMethod(operationName = "solicitarCodigo")
    public Usuario solicitarCodigo(@WebParam(name = "oUsuario") Usuario oUsuario) throws Exception {
        if (authTest()) {
            return ejbRef.solicitarCodigo(oUsuario);
        } else {
            return new Usuario();
        }
    }    
    
    @WebMethod(operationName = "validarCodigo")
    public Usuario validarCodigo(@WebParam(name = "oUsuario") Usuario oUsuario) throws Exception {
        if (authTest()) {
            return ejbRef.validarCodigo(oUsuario);
        } else {
            return new Usuario();
        }
    }
    
    @WebMethod(operationName = "resetearClave")
    public Usuario resetearClave(@WebParam(name = "oUsuario") Usuario oUsuario) throws Exception {
        if (authTest()) {
            return ejbRef.resetearClave(oUsuario);
        } else {
            return new Usuario();
        }
    }    
}
