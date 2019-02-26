/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.enumerators;

/**
 *
 * @author Tiago
 */
public enum TIPO_ENTIDADE {

    F("Física"), J("Jurídica");

    private final String valor;

    private TIPO_ENTIDADE(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
