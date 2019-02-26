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
public enum TIPO_ENDERECO {
    
    RS("Residêncial"), CL("Comercial"), CB("Cobrança");
    
    private final String valor;

    private TIPO_ENDERECO(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
