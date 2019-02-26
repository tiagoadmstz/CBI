/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.odbc;

import br.com.cbi.entities.Categoria_Equipamento;
import br.com.cbi.entities.Marca_Equipamento;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago
 */
public class OdbcTest {

    //@Test
    public void connectionTest() {
        try {
            ConnectionOdbc connection = new ConnectionOdbc();
            assertFalse(connection.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void searchTest() {
        try {
            ConnectionOdbc connection = new ConnectionOdbc();
            AccessUtil access = new AccessUtil(connection);
            access.getResultSet("SELECT DISTINCT Marca FROM Marcas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void test() {
        try {
            CbiAccessClone clonar = new CbiAccessClone();
            //clonar.registrarMarcas().run();
            //clonar.registrarCategorias().run();
            clonar.registrarCidades().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
