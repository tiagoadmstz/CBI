/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.fs.api.interfaces.ManipulaBean;
import java.util.Objects;
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
@Table(name = "CAD_MARCA_EQUIP", indexes = {
    @Index(columnList = "DESCRICAO")
})
@NamedQueries(value = {
    @NamedQuery(name = "marca_equipamento.findAll", query = "SELECT mc FROM Marca_Equipamento AS mc")
    ,@NamedQuery(name = "marca_equipamento.findById", query = "SELECT mc FROM Marca_Equipamento AS mc WHERE mc.id = :paramId")
    ,@NamedQuery(name = "marca_equipamento.findByMarca", query = "SELECT mc FROM Marca_Equipamento AS mc WHERE mc.descricao = :paramDescricao")
})
@SequenceGenerator(name = "SEQ_CAD_MARCA_EQUIP", sequenceName = "SEQ_CAD_MARCA_EQUIP", initialValue = 1, allocationSize = 1)
public class Marca_Equipamento extends ManipulaBean<Marca_Equipamento> {

    private static final long serialVersionUID = 4730631818413166861L;
    private Long id;
    private String descricao;
    private int version;

    public Marca_Equipamento() {
    }

    public Marca_Equipamento(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Id
    @Column(name = "MARCA")
    @GeneratedValue(generator = "SEQ_CAD_MARCA_EQUIP", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRICAO", length = 150)
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
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.descricao);
        hash = 37 * hash + this.version;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marca_Equipamento other = (Marca_Equipamento) obj;
        if (this.version != other.version) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Marca_Equipamento{" + "id=" + id + ", descricao=" + descricao + '}';
    }

}
