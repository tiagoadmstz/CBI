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
@Table(name = "CAD_EMAILS", indexes = {
    @Index(columnList = "EMAIL_DESC")
})
@NamedQueries(value = {
    @NamedQuery(name = "email.findAll", query = "SELECT em FROM Email AS em")
})
@SequenceGenerator(name = "SEQ_EMAIL", initialValue = 1, allocationSize = 1)
public class Email extends ManipulaBean<Email> {

    private static final long serialVersionUID = 6271428054228674154L;
    private Long id;
    private String email;
    private String contato;

    public Email() {
    }

    public Email(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Id
    @Column(name = "EMAIL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMAIL")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "EMAIL_DESC", length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Email{" + "id=" + id + ", email=" + email + ", contato=" + contato + '}';
    }

}
