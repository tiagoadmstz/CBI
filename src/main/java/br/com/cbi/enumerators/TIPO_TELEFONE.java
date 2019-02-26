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
public enum TIPO_TELEFONE {

    FR("Fixo Rensidensial"), FC("Fixo Comercial"), CL("Celular");

    private final String valor;

    private TIPO_TELEFONE(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
