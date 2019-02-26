/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.ws.rest;

import br.com.cbi.entities.Cidade;
import br.com.cbi.entities.Endereco;
import br.com.cbi.entities.Estado;
import br.com.cbi.ws.rest.bean.ViaCepBean;
import java.io.Serializable;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Tiago
 */
public class ViaCepWs implements Serializable {

    private static final long serialVersionUID = -2330369788549950096L;

    public static ViaCepBean getEnderecoByCep(String cep) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ViaCepBean viacep = restTemplate.getForObject("http://viacep.com.br/ws/{cep}/json/", ViaCepBean.class, cep);
            return viacep;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Endereco convertToEndereco(String cep) {
        ViaCepBean viacep = getEnderecoByCep(cep);
        if (viacep != null) {
            Endereco end = new Endereco();
            end.setBairro(viacep.getBairro());
            end.setCep(viacep.getCep());
            Cidade cidade = new Cidade();
            cidade.setNome(viacep.getLocalidade());
            Estado estado = new Estado();
            estado.setSigla(viacep.getUf());
            cidade.setEstado(estado);
            end.setCidade(cidade);
            end.setComplemento(viacep.getComplemento());
            end.setLogradouro(viacep.getLogradouro());
            return end;
        }
        return null;
    }

}
