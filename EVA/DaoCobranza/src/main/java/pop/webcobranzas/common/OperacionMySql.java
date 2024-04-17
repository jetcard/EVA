/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pop.webcobranzas.conn.ConexionMySql;
/**
 *
 * @author Jyoverar
 */
public class OperacionMySql {
    
    static public String ejecutaOperacion(String sql) {
        String mensaje = null;
        try {
            Connection cn = new ConexionMySql().getConnection();
            if (cn == null) {
                mensaje = "No hay acceso a la base de datos";
            } else {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
                cn.close();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje; 
    }
    
    static public List<Object[]> getTabla(String sql) {
        List<Object[]> list = new ArrayList<Object[]>();
        try {
            Connection cn = new ConexionMySql().getConnection();
            if (cn == null) {
                list = null;
            } else {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rm = rs.getMetaData();
                int numCols = rm.getColumnCount();
                // Toma titulos de columnas
                String[] titCols = new String[numCols];
                for (int i = 0; i < numCols; ++i) {
                    titCols[i] = rm.getColumnName(i + 1);
                }
                // fila 0 lleva los titulos de columnas
                list.add(titCols);
                // toma las filas de la consulta
                while (rs.next()) {
                    Object[] fila = new Object[numCols];
                    for (int i = 0; i < numCols; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    list.add(fila);
                }
                cn.close();
            }
        } catch (SQLException e) {
            list = null;
        }
        return list;   
    }
       
    public static Object[] getFila(String sql) {
        List<Object[]> list = OperacionMySql.getTabla(sql);
        Object[] fila = null;
        if (list != null) {
            if (list.size() > 1) {
                fila = (Object[]) list.get(1);
            }
        }
        return fila;
    }
    
    public static Object getColumna(String sql) {
        Object[] fila = getFila(sql);
        Object columna = null;
        if (fila != null) {
            columna = fila[0];
        }
        return columna;
    }
    
}
