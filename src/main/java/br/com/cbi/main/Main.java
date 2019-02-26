/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.main;

import br.com.fs.api.util.Utilidades;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Tiago
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Utilidades.mudaLookAndFeel(Utilidades.WINDOWS);
        SpringApplication.run(Main.class, args);
    }

}
