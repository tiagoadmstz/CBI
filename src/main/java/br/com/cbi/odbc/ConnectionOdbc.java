/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.odbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class ConnectionOdbc {

    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private final String url = "jdbc:ucanaccess://C:\\Users\\Tiago\\Desktop\\CBI - EX.accdb";
    private final String user = "root";
    private final String pass = "Pedro1714@#$";

    public ConnectionOdbc() {
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Connection getConnection(String url, String user, String pass) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet getSimpleResultSet(String sql) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getColumnName(Connection conn, String table) {
        try {
            List<String> columns = new ArrayList();
            ResultSet rs = conn.getMetaData().getColumns(null, null, table, null);
            while (rs.next()) {
                columns.add(rs.getString(4));
            }
            return columns;
        } catch (Exception e) {
            return null;
        }
    }

}
