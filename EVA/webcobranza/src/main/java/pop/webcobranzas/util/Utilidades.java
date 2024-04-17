/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.util;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Jyoverar
 */
public class Utilidades {

    public static Object getEJBRemote(String nameEJB, String iface) throws Exception {
        Context context;
        String JBOSS_CONTEXT = "org.jboss.naming.remote.client.InitialContextFactory";
        //Properties prop = new Properties();
        //Properties properties = new Properties();
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_CONTEXT);
        props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        context = new InitialContext(props);

        try {
            //context = new InitialContext(properties);
            //context = initialContext;//new InitialContext(env);

            final String appName = "ProcesamientoCobranza-ear-1.0-SNAPSHOT";
            final String moduleName = "ProcesamientoCobranza-ejb-1.0-SNAPSHOT";
            final String distinctName = "";

            //String lookup = "java:module/"+ nameEJB + "#"+iface;
            String lookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface;

            System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface);
            System.out.println("lookup: " + lookup);
            return context.lookup(lookup);
        } catch (Exception e) {
            throw new Exception("No se encontro EJB" + nameEJB + ".");
        }
    }

    public static Object getEJBRemoteRep(String nameEJB, String iface) throws Exception {
        Context context;
        String JBOSS_CONTEXT = "org.jboss.naming.remote.client.InitialContextFactory";
        //Properties prop = new Properties();
        //Properties properties = new Properties();
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_CONTEXT);
        props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        context = new InitialContext(props);

        try {
            //context = new InitialContext(properties);
            //context = initialContext;//new InitialContext(env);

            final String appName = "Reportes-ear-1.0-SNAPSHOT";
            final String moduleName = "Reportes-ejb-1.0-SNAPSHOT";
            final String distinctName = "";

            //String lookup = "java:module/"+ nameEJB + "#"+iface;
            String lookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface;

            //System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface);
            //System.out.println("lookup: " + lookup);
            return context.lookup(lookup);
        } catch (Exception e) {
            throw new Exception("No se encontro EJB" + nameEJB + ".");
        }
    }

    public static Object getEJBRemoteMsj(String nameEJB, String iface) throws Exception {
        Context context;
        String JBOSS_CONTEXT = "org.jboss.naming.remote.client.InitialContextFactory";
        //Properties prop = new Properties();
        //Properties properties = new Properties();
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_CONTEXT);
        props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        context = new InitialContext(props);

        try {
            //context = new InitialContext(properties);
            //context = initialContext;//new InitialContext(env);

            final String appName = "ProcesamientoMensaje-ear-1.0-SNAPSHOT";
            final String moduleName = "ProcesamientoMensaje-ejb-1.0-SNAPSHOT";
            final String distinctName = "";

            //String lookup = "java:module/"+ nameEJB + "#"+iface;
            String lookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface;

            //System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface);
            //System.out.println("lookup: " + lookup);
            return context.lookup(lookup);
        } catch (Exception e) {
            throw new Exception("No se encontro EJB" + nameEJB + ".");
        }
    }
    
    public static Object getEJBRemoteCorreo(String nameEJB, String iface) throws Exception {
        Context context;
        String JBOSS_CONTEXT = "org.jboss.naming.remote.client.InitialContextFactory";
        //Properties prop = new Properties();
        //Properties properties = new Properties();
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        //properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_CONTEXT);
        props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        context = new InitialContext(props);

        try {
            //context = new InitialContext(properties);
            //context = initialContext;//new InitialContext(env);

            final String appName = "ProcesamientoCorreo-ear-1.0-SNAPSHOT";
            final String moduleName = "ProcesamientoCorreo-ejb-1.0-SNAPSHOT";
            final String distinctName = "";

            //String lookup = "java:module/"+ nameEJB + "#"+iface;
            String lookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface;

            //System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + nameEJB + "!" + iface);
            //System.out.println("lookup: " + lookup);
            return context.lookup(lookup);
        } catch (Exception e) {
            throw new Exception("No se encontro EJB" + nameEJB + ".");
        }
    }

}
