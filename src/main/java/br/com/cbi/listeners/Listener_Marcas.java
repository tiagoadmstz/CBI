/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.entities.Marca_Equipamento;
import br.com.cbi.frames.Form_Marcas;
import br.com.cbi.tablemodels.TableModel_Pesquisa_Marcas;
import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.frames.PesquisaDefaultForm;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tiago
 */
public final class Listener_Marcas extends ListenerPatternAdapter<Form_Marcas> {

    private final DataBaseHelper db = new DataBaseHelper();
    private final Marca_Equipamento marca = new Marca_Equipamento();
    private TableModel_Pesquisa_Marcas model;
    private TableRowSorter sorter;

    public Listener_Marcas(Form_Marcas form) {
        super(form);
        initComponents();
    }

    @Override
    protected synchronized void attachListener() {
        super.attachListener();
        form.getTxtMarca().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (marca.getId() == null) {
                        salvar();
                    } else {
                        alterar();
                    }
                }
            }
        });
    }

    @Override
    protected synchronized void novo() {
        super.novo();
        marca.clear();
        form.getTxtMarca().requestFocus();
    }

    @Override
    protected synchronized void salvar() {
        super.salvar(db, marca);
    }

    @Override
    protected synchronized void alterar() {
        super.alterar(db, marca);
    }

    @Override
    protected void deletar() {
        super.deletar(db, marca);
    }

    @Override
    protected void pesquisa() {
        model = new TableModel_Pesquisa_Marcas((List<Marca_Equipamento>) db.getObjectListNamedQuery(Marca_Equipamento.class, "marca_equipamento.findAll", null, null, EntityManagerHelper.DERBYDB_PU).orElse(new ArrayList()));
        PesquisaDefaultForm pesquisa = pesquisar("Pesquisa de Marcas", model, null, this);
        pesquisa.setVisible(true);
    }

    @Override
    public void copiarObject(Object object) {
        marca.copiar((Marca_Equipamento) object);
        form.setObject(marca);
    }

}
