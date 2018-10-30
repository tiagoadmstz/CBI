/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.main;

import br.com.cbi.frames.Form_Marcas;
import br.com.fs.api.util.ControleInstancias;
import br.com.fs.api.util.Utilidades;

/**
 *
 * @author Tiago
 */
public class Main {

    public static void main(String[] args) {
        Utilidades.mudaLookAndFeel(Utilidades.WINDOWS);
        Form_Marcas marcas = (Form_Marcas) ControleInstancias.getInstance(Form_Marcas.class.getName());
        marcas.setVisible(true);
    }

}
