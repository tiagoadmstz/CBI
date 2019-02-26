/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.cbi.enumerators.TIPO_TELEFONE;
import br.com.fs.api.interfaces.ManipulaBean;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Tiago
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_TELEFONES", indexes = {
    @Index(columnList = "NUMERO")
})
@SequenceGenerator(name = "SEQ_TELEFONE", initialValue = 1, allocationSize = 1)
public class Telefone extends ManipulaBean<Telefone> {

    private static final long serialVersionUID = 7089780805739807745L;
    private Long id;
    private TIPO_TELEFONE tipo_telefone;
    private String telefone;
    private String contato;

    public Telefone() {
    }

    @Id
    @Column(name = "TELEFONE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TELEFONE")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_TELEFONE", length = 2)
    public TIPO_TELEFONE getTipo_telefone() {
        return tipo_telefone;
    }

    public void setTipo_telefone(TIPO_TELEFONE tipo_telefone) {
        this.tipo_telefone = tipo_telefone;
    }

    public void setTipo_telefone_string(String tipo_telefone) {
        switch (tipo_telefone) {
            case "Fixo Rensidensial":
                this.tipo_telefone = TIPO_TELEFONE.FR;
                break;
            case "Fixo Comercial":
                this.tipo_telefone = TIPO_TELEFONE.FC;
                break;
            case "Celular":
                this.tipo_telefone = TIPO_TELEFONE.CL;
                break;
        }
    }

    @Column(name = "NUMERO", length = 20)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name = "CONTATO", length = 150)
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Telefone{" + "id=" + id + ", tipo_telefone=" + tipo_telefone + ", telefone=" + telefone + ", contato=" + contato + '}';
    }

}
