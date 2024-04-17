/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Jyoverar
 */
public class SampleTransactionManager {

    Connection connection = null;
    static ThreadLocal local = new ThreadLocal();

    public static void startTransaction() throws SQLException, NamingException {
        InitialContext ctx = new InitialContext();
        Connection con = ((DataSource) ctx.lookup("java:jboss/datasources/OracleDS")).getConnection();
        con.setAutoCommit(false);
        local.set(con);
    }

    public static Connection getConnection() throws SQLException {
        Connection con = (Connection) local.get();
        return con;
    }

    public static void commit() {
        Connection con = (Connection) local.get();
        if (con != null) {
            try {
                con.commit();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void rollback() {
        Connection con = (Connection) local.get();
        if (con != null) {
            try {
                con.rollback();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
