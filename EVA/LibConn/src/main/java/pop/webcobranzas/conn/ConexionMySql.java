/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jyoverar
 */
public class ConexionMySql {
    
    public Connection getConnection() {
        Connection cn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");            
            cn = DriverManager.getConnection("jdbc:mysql://192.168.70.15:3306/asteriskcdrdb", "evaelatoor", "evaela4X");
        } catch (ClassNotFoundException e) {
            cn = null;
        } catch (SQLException e) {
            cn = null;
        }
        return cn;
    }     
    
    public static Connection getConnectionStatic() {
        Connection cn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://192.168.70.15:3306/asteriskcdrdb", "evaelatoor", "evaela4X");
        } catch (ClassNotFoundException e) {
            cn = null;
        } catch (SQLException e) {
            cn = null;
        }
        return cn;
    }    
    
    
}
