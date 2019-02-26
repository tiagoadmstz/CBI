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
import javax.persistence.Version;

/**
 *
 * @author Tiago
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_CATEGORIA_EQUIPAMENTO", indexes = {
    @Index(columnList = "DESCRICAO")
})
@NamedQueries(value = {
    @NamedQuery(name = "categoria_equipamento.findAll", query = "SELECT ct FROM Categoria_Equipamento AS ct")
    ,@NamedQuery(name = "categoria_equipamento.findById", query = "SELECT ct FROM Categoria_Equipamento AS ct WHERE ct.id = :paramId")
    ,@NamedQuery(name = "categoria_equipamento.findByDescricao", query = "SELECT ct FROM Categoria_Equipamento AS ct WHERE ct.descricao = :paramDescricao")
})
@SequenceGenerator(name = "SEQ_CATEGORIA_EQUIP", initialValue = 1, allocationSize = 1)
public class Categoria_Equipamento extends ManipulaBean<Categoria_Equipamento> {

    private static final long serialVersionUID = 5246170522447639324L;
    private Long id;
    private String descricao;
    private int version;

    public Categoria_Equipamento() {
    }

    public Categoria_Equipamento(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Id
    @Column(name = "CATEGORIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORIA_EQUIP")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRICAO", length = 255)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Categoria_Equipamento{" + "id=" + id + ", descricao=" + descricao + ", version=" + version + '}';
    }

}
