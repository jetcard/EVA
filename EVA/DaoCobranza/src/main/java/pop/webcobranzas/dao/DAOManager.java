/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Jyoverar
 */
public class DAOManager {

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }

    public void open() throws SQLException {
        if (this.con == null)//|| !this.con.isOpen())
        {
            this.con = src.getConnection();
        }
    }

    public void close() throws SQLException {
        if (this.con != null)//&& this.con.isOpen())
        {
            this.con.close();
        }
    }

    //Private
    private DataSource src;
    private Connection con;

    private DAOManager() throws Exception {
        try {
            InitialContext ctx = new InitialContext();
            this.src = (DataSource) ctx.lookup("java:jboss/datasources/OracleDS");
        } catch (Exception e) {
            throw e;
        }
    }

    private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;

        static {
            ThreadLocal<DAOManager> dm;
            try {
                dm = new ThreadLocal<DAOManager>() {
                    @Override
                    protected DAOManager initialValue() {
                        try {
                            return new DAOManager();
                        } catch (Exception e) {
                            return null;
                        }
                    }
                };
            } catch (Exception e) {
                
                }
                dm = null;
                INSTANCE = dm;
            }

        }
    }
