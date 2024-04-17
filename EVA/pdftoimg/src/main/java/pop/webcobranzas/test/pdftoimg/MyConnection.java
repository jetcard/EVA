/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;

import java.sql.Connection;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.spi.InitialContextFactory;
import javax.sql.DataSource;

/**
 *
 * @author Jyoverar
 */
public class MyConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            Context context;
            String JBOSS_CONTEXT = "org.jboss.naming.remote.client.InitialContextFactory";
            //Properties prop = new Properties();
            //Properties properties = new Properties();
            //properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            //properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            //properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8686");

            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_CONTEXT);
            props.put(Context.PROVIDER_URL, "remote://LOCALHOST:4447");
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            context = new InitialContext(props);

            // TODO code application logic here
            Context initContext;
            initContext = new InitialContext();
//            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
//                    "org.apache.naming.java.javaURLContextFactory");
            //Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = null;
            ds = (DataSource) initContext.lookup("java:/PostgresDS");
            if (ds != null) {
                Connection cn = ds.getConnection();
                System.out.println(" --> " + cn.toString());
                cn.close();
            }
        } catch (Exception e) {
            System.out.println("e ->  " + e.getMessage());
        }

    }

}
