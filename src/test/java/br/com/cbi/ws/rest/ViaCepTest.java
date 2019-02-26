/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.ws.rest;

import org.junit.Test;

/**
 *
 * @author Tiago
 */
public class ViaCepTest {

    //@Test
    public void getEnderecoTest() {
        ViaCepWs.getEnderecoByCep("14178068");
        ViaCepWs.getEnderecoByCep("14177160");
        ViaCepWs.getEnderecoByCep("14177000");
    }

}
