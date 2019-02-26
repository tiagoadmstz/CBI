/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.fs.api.interfaces.ManipulaBean;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Tiago
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_CIDADE")
@NamedQueries(value = {
    @NamedQuery(name = "cidade.findAll", query = "SELECT cdd FROM Cidade AS cdd")
})
@SequenceGenerator(name = "SEQ_CIDADE", initialValue = 1, allocationSize = 1)
public class Cidade extends ManipulaBean<Cidade> {

    private static final long serialVersionUID = 5156038347463339197L;
    private Long id;
    private String nome;
    private Estado estado;

    public Cidade() {
    }

    @Id
    @Column(name = "CIDADE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIDADE")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME", length = 100)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JoinColumn(name = "ESTADO", referencedColumnName = "ESTADO")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Estado.class)
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cidade{" + "id=" + id + ", nome=" + nome + ", estado=" + estado + '}';
    }

}
