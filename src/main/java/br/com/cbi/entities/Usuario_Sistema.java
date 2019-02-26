/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.fs.api.interfaces.ManipulaBean;
import br.com.fs.api.interfaces.entities.Permissoes;
import br.com.fs.api.interfaces.entities.Usuario;
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
@Table(name = "CAD_USUARIO", indexes = {
    @Index(columnList = "LOGIN")
})
@NamedQueries(value = {
    @NamedQuery(name = "usuario.findAll", query = "SELECT user FROM Usuario_Sistema AS user")
    ,@NamedQuery(name = "usuario.findByUser", query = "SELECT user FROM Usuario_Sistema AS user WHERE user.login = :paramUser")
})
@SequenceGenerator(name = "SEQ_USUARIO", initialValue = 1, allocationSize = 1)
public class Usuario_Sistema extends ManipulaBean<Usuario_Sistema> implements Usuario {

    private static final long serialVersionUID = 5193312491055522127L;
    private Long id;
    private String nomeUsuario;
    private String login;
    private String senha;

    public Usuario_Sistema() {
    }

    @Id
    @Column(name = "USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LOGIN", length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    @Column(name = "NOME", length = 255)
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    @Column(name = "SENHA", length = 255)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Permissoes getPermissoes() {
        return null;
    }

    @Override
    public String toString() {
        return "Usuario_Sistema{" + "id=" + id + ", nomeUsuario=" + nomeUsuario + ", login=" + login + ", senha=" + senha + '}';
    }

}
