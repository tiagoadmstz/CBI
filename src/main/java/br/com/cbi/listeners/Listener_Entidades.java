/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.entities.Cidade;
import br.com.cbi.entities.Email;
import br.com.cbi.entities.Endereco;
import br.com.cbi.entities.Entidade;
import br.com.cbi.entities.Estado;
import br.com.cbi.entities.Telefone;
import br.com.cbi.enumerators.TIPO_TELEFONE;
import br.com.cbi.frames.Form_Entidades;
import br.com.cbi.tablemodels.TableModel_Emails;
import br.com.cbi.tablemodels.TableModel_Endereco;
import br.com.cbi.tablemodels.TableModel_Telefones;
import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import br.com.fs.api.renderers.DefaultCBIRenderer;
import br.com.fs.api.util.TableAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tiago
 */
public final class Listener_Entidades extends ListenerPatternAdapter<Form_Entidades> {

    private final Entidade entidade = new Entidade();
    private TableModel_Endereco model_endereco;
    private TableModel_Telefones model_telefone;
    private TableModel_Emails model_email;

    public Listener_Entidades(Form_Entidades form) {
        super(form);
        initComponents();
    }

    @Override
    protected synchronized void initComponents() {
        super.initComponents();
        addModel();
    }

    @Override
    protected synchronized void attachListener() {
        super.attachListener();
        form.getTxtData().addFocusListener(this);
    }

    @Override
    protected void addModel() {
        DataBaseHelper emh = new DataBaseHelper();
        //tabela de endereços
        model_endereco = new TableModel_Endereco();
        form.getTbEnderecos().setModel(model_endereco);
        setColumnDesign(form.getTbEnderecos(), new DefaultCBIRenderer());
        TableAction tableAction_endereco = new TableAction(form.getTbEnderecos(), model_endereco);
        List<Cidade> cidades = (List<Cidade>) emh.getObjectListNamedQuery(Cidade.class, "cidade.findAll", null, null, DataBaseHelper.DERBYDB_PU).orElse(new ArrayList());
        setComboBoxColumn(form.getTbEnderecos(), 5, cidades.stream().map(Cidade::getNome).collect(Collectors.toList()).toArray(new Object[cidades.size()]));
        List<Estado> estados = (List<Estado>) emh.getObjectListNamedQuery(Estado.class, "estado.findAll", null, null, DataBaseHelper.DERBYDB_PU).orElse(new ArrayList());
        setComboBoxColumn(form.getTbEnderecos(), 6, estados.stream().map(Estado::getSigla).collect(Collectors.toList()).toArray(new Object[estados.size()]));
        //tabela de telefones
        model_telefone = new TableModel_Telefones();
        form.getTbTelefones().setModel(model_telefone);
        setColumnDesign(form.getTbTelefones(), new DefaultCBIRenderer());
        TableAction tableAction_telefone = new TableAction(form.getTbTelefones(), model_telefone);
        Object[] ob = Arrays.asList(TIPO_TELEFONE.values()).stream().map(t -> t.getValor()).collect(Collectors.toList()).toArray();
        setComboBoxColumn(form.getTbTelefones(), 0, ob);
        //tabela de emails
        model_email = new TableModel_Emails();
        form.getTbEmails().setModel(model_email);
        setColumnDesign(form.getTbEmails(), new DefaultCBIRenderer());
        TableAction tableAction_email = new TableAction(form.getTbEmails(), model_email);
    }

    @Override
    protected synchronized void novo() {
        super.novo();
        model_endereco.addObject(new Endereco());
        model_telefone.addObject(new Telefone());
        model_email.addObject(new Email());
    }

    @Override
    protected synchronized void salvar() {
        form.getObject(entidade);
        entidade.printValues();
    }

}
