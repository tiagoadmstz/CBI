/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.cbi.enumerators.TIPO_ENDERECO;
import br.com.fs.api.interfaces.ManipulaBean;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tiago
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_ENDERECOS", indexes = {
    @Index(columnList = "LOGRADOURO")
    ,@Index(columnList = "BAIRRO")
    ,@Index(columnList = "CEP")
})
@NamedQueries(value = {
    @NamedQuery(name = "endereco.findAll", query = "SELECT ed FROM Endereco AS ed")
})
@SequenceGenerator(name = "SEQ_ENDERECO", initialValue = 1, allocationSize = 1)
public class Endereco extends ManipulaBean<Endereco> {

    private static final long serialVersionUID = -7284709182645903139L;
    private Long id;
    private TIPO_ENDERECO tipo_endereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String observacao;
    private Cidade cidade;

    public Endereco() {
    }

    @Id
    @Column(name = "ENDERECO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ENDERECO", length = 2)
    public TIPO_ENDERECO getTipo_endereco() {
        return tipo_endereco;
    }

    public void setTipo_endereco(TIPO_ENDERECO tipo_endereco) {
        this.tipo_endereco = tipo_endereco;
    }

    @Column(name = "CEP", length = 10)
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Column(name = "LOGRADOURO", length = 255)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Column(name = "NUMERO", length = 50)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "BAIRRO", length = 150)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "COPLEMENTO", length = 150)
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Column(name = "OBSERVACAO", length = 255)
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @JoinColumns(value = {
        @JoinColumn(name = "CIDADE", referencedColumnName = "CIDADE")
        ,@JoinColumn(name = "NOME_CIDADE", referencedColumnName = "NOME")
    })
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = Cidade.class)
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", tipo_endereco=" + tipo_endereco + ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero + ", bairro=" + bairro + ", complemento=" + complemento + ", observacao=" + observacao + ", cidade=" + cidade + '}';
    }

}
