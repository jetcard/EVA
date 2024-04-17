/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import pop.webcobranzas.ws.Menu;
import pop.webcobranzas.webservices.Menu; 
import pop.webcobranzas.webservices.MenuEventos;
 
/**
 *
 * @author Jyoverar
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class WebSocketFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpReponse = (HttpServletResponse) response;
        HttpSession ses = httpRequest.getSession(false);
        final PrincipalWithSession p = new PrincipalWithSession(httpRequest.getSession(false));

        HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public Principal getUserPrincipal() {
                return p;
            }
        };
        String reqURI = httpRequest.getRequestURI();

        System.out.println("1. acceso a: (reqURI)" + reqURI);
        
        if (reqURI.equals("/webcobranza/pages/sistema/solicitarcodigo.xhtml")
                || reqURI.equals("/webcobranza/pages/sistema/olvidoclave.xhtml")) {
            System.out.println("2.doFilter:" + reqURI);
            chain.doFilter(wrappedRequest, response);
            return;
        }
                
        if (reqURI.contains("/login.xhtml")
                || (ses != null && ses.getAttribute("username") != null)
                || reqURI.contains("/public/")
                || reqURI.contains("javax.faces.resource")) {
          
            //chain.doFilter(request, response);
            if (ses != null && ses.getAttribute("opcionespermisomenulist") != null
                    && !reqURI.equals("/webcobranza/javax.faces.resource/jsf.js.xhtml")
                    && !reqURI.equals("/webcustodia/javax.faces.resource/jsf.js.xhtml?ln=javax.faces&stage=Development")                    
                    && !reqURI.equals("/webcobranza/javax.faces.resource/omnifaces.js.xhtml")
                    && !reqURI.equals("/webcobranza/dashboard.xhtml")
                    && !reqURI.equals("/webcobranza/pages/sistema/cambiouser.xhtml")) {
                
                            
                
                System.out.println("3.[" + ses.getAttribute("username") + "] accediento a: " + reqURI);

                List<Menu> menu = (List<Menu>) ses.getAttribute("opcionespermisomenulist");
                boolean acceso = false;
                for (Menu arg : menu) {
                    //System.out.println(" filter ---- " + arg.getMenuA());
                    for (Menu argb : arg.getOMenuList()) {
                        //System.out.println(" filter ------------ " + argb.getMenuA() + " " + argb.getMenuAUrl());
                        if (reqURI.contains(argb.getMenuAUrl())) {
                            acceso = true;
                            break;
                        }
                    }
                    
                     for (Menu argb1 : arg.getOMenuList2()) {
                        //System.out.println(" filter argb1------------ " + argb1.getMenuA() + " " + argb1.getMenuAUrl());
                        if (reqURI.contains(argb1.getMenuAUrl())) {
                            acceso = true;
                            break;
                        }
                        
                        for (MenuEventos even : argb1.getListaEventos()) {
                            //System.out.println(" filter even------------ " + even.getMenuA() + " " + even.getMenuAUrl());                            
                            if (reqURI.contains(even.getMenuAUrl())) {
                                acceso = true;
                                break;
                            }                            
                        }
                        
                        
                        if (acceso) {
                            break;
                        } 
                        
                    }                    
                    
                    if (acceso) {
                        break;
                    }
                }
                
                System.out.println("4.[acceso]: " + acceso+reqURI);
                if (acceso) {
                    chain.doFilter(wrappedRequest, response);
                } else {
                    if (reqURI.equals("/webcobranza/login.xhtml")
                            || reqURI.equals("/webcobranza/")) {
                        httpReponse.sendRedirect(httpRequest.getContextPath() + "/dashboard.xhtml");
                    } else {
                        ses.invalidate();
                        httpReponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
                    }
                }
            } else {
                System.out.println("5.wrappedRequest.2");
                chain.doFilter(wrappedRequest, response);
            }

        } else {
            httpReponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
        }

    }

    @Override
    public void destroy() {

    }

}
