/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.entities.Marca_Equipamento;
import br.com.cbi.frames.Form_Marcas;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Tiago
 */
public final class Listener_Marcas extends ListenerPatternAdapter<Form_Marcas> {

    private final DataBaseHelper db = new DataBaseHelper();
    private final Marca_Equipamento marca = new Marca_Equipamento();

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
                if(event.getKeyCode() == KeyEvent.VK_ENTER){
                    salvar();
                }
            }
        });
    }

    @Override
    protected synchronized void novo() {
        super.novo();
        form.getTxtMarca().requestFocus();
    }

    @Override
    protected synchronized void salvar() {
        super.salvar(db, marca);
    }

}
