/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.dal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tiago
 */
public class DataBaseH2 implements Serializable {

    private static final long serialVersionUID = 1822440067062241198L;

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:/test");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
