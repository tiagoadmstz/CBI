/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.fs.api.interfaces.ManipulaBean;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "CAD_ESTADO", indexes = {
    @Index(columnList = "SIGLA")
})
@NamedQueries(value = {
    @NamedQuery(name = "estado.findAll", query = "SELECT est FROM Estado AS est")
})
@SequenceGenerator(name = "SEQ_ESTADO", initialValue = 1,allocationSize = 1)
public class Estado extends ManipulaBean<Estado> {

    private static final long serialVersionUID = 2609454703954632268L;
    private Long id;
    private String sigla;
    private String nome;

    public Estado() {
    }

    public Estado(Long id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    @Id
    @Column(name = "ESTADO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ESTADO")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SIGLA", length = 2)
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Column(name = "NOME", length = 150)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", sigla=" + sigla + ", nome=" + nome + '}';
    }
    
}
