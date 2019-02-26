/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.cbi.converters.LocalDateTimeConverter;
import br.com.cbi.enumerators.TIPO_ENTIDADE;
import br.com.fs.api.interfaces.ManipulaBean;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Tiago
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_ENTIDADES", indexes = {
    @Index(columnList = "RAZAO_SOCIAL")
    ,@Index(columnList = "NOME_FANTASIA")
    ,@Index(columnList = "RAZAO_SOCIAL,NOME_FANTASIA")
})
@NamedQueries(value = {
    @NamedQuery(name = "entidade.findAll", query = "SELECT ent FROM Entidade AS ent")
})
@SequenceGenerator(name = "SEQ_ENTIDADE", initialValue = 1, allocationSize = 1)
public class Entidade extends ManipulaBean<Entidade> {

    private static final long serialVersionUID = 2980304891377165877L;
    private Long id;
    private LocalDateTime data_cadastro;
    private TIPO_ENTIDADE tipo_entidade;
    private Boolean cliente;
    private Boolean fornecedor;
    private Boolean empresa;
    private String razao_social;
    private String nome_fantasia;
    private List<Endereco> enderecos;
    private List<Telefone> telefones;
    private List<Email> emails;

    public Entidade() {
    }

    @Id
    @Column(name = "ENTIDADE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENTIDADE")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_CADASTRO", columnDefinition = "timestamp")
    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ENTIDADE", length = 1)
    public TIPO_ENTIDADE getTipo_entidade() {
        return tipo_entidade;
    }

    public void setTipo_entidade(TIPO_ENTIDADE tipo_entidade) {
        this.tipo_entidade = tipo_entidade;
    }

    public void setTipo_entidade_string(String tipo_entidade) {
        switch (tipo_entidade) {
            case "Jurídica":
                this.tipo_entidade = TIPO_ENTIDADE.J;
            case "Física":
                this.tipo_entidade = TIPO_ENTIDADE.F;
        }
    }

    @Column(name = "CLIENTE", columnDefinition = "boolean")
    public Boolean getCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    @Column(name = "FORNECEDOR", columnDefinition = "boolean")
    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Column(name = "EMPRESA", columnDefinition = "boolean")
    public Boolean getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Boolean empresa) {
        this.empresa = empresa;
    }

    @Column(name = "RAZAO_SOCIAL", length = 255)
    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    @Column(name = "NOME_FANTASIA", length = 255)
    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Endereco.class)
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Telefone.class)
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Email.class)
    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Entidade{" + "id=" + id + ", tipo_entidade=" + tipo_entidade + ", cliente=" + cliente + ", fornecedor=" + fornecedor + ", empresa=" + empresa + ", razao_social=" + razao_social + ", nome_fantasia=" + nome_fantasia + ", telefones=" + telefones + ", emails=" + emails + '}';
    }

}
