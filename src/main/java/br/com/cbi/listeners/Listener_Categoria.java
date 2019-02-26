/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.entities.Categoria_Equipamento;
import br.com.cbi.frames.Form_Categoria;
import br.com.cbi.tablemodels.TableModel_Pesquisa_Categoria;
import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.frames.PesquisaDefaultForm;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public final class Listener_Categoria extends ListenerPatternAdapter<Form_Categoria> {

    private final DataBaseHelper db = new DataBaseHelper();
    private final Categoria_Equipamento categoria = new Categoria_Equipamento();

    public Listener_Categoria(Form_Categoria form) {
        super(form);
        initComponents();
    }

    @Override
    protected synchronized void attachListener() {
        super.attachListener();
        form.getTxtCategoria().addKeyListener(this);
    }

    @Override
    protected synchronized void novo() {
        super.novo();
        categoria.clear();
        form.getTxtCategoria().requestFocus();
    }

    @Override
    protected synchronized void salvar() {
        super.salvar(db, categoria);
    }

    @Override
    protected synchronized void alterar() {
        super.alterar(db, categoria);
    }

    @Override
    protected void deletar() {
        super.deletar(db, categoria);
    }

    @Override
    protected void pesquisa() {
        TableModel_Pesquisa_Categoria model = new TableModel_Pesquisa_Categoria();
        model.setLista((List<Categoria_Equipamento>) db.getObjectListNamedQuery(Categoria_Equipamento.class, "categoria_equipamento.findAll", null, null, EntityManagerHelper.DERBYDB_PU).orElse(new ArrayList()));
        PesquisaDefaultForm pesquisa = pesquisar("Pesquisa de Categorias", model, null, this);
        pesquisa.setVisible(true);
    }

    @Override
    public void copiarObject(Object object) {
        categoria.copiar((Categoria_Equipamento) object);
        form.setObject(categoria);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (categoria.getId() != null) {
                alterar();
            } else {
                salvar();
            }
        }
    }

}
