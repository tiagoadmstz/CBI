/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.frames.Form_Categoria;
import br.com.cbi.frames.Form_Main;
import br.com.cbi.frames.Form_Marcas;
import br.com.fs.api.frames.Form_Login;
import br.com.fs.api.frames.Form_ReportManager;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import br.com.fs.api.util.ConfigUtil;
import br.com.fs.api.util.ControleInstancias;
import java.awt.event.ActionEvent;

/**
 *
 * @author Tiago
 */
public class Listener_Main extends ListenerPatternAdapter<Form_Main> {

    public Listener_Main(Form_Main form) {
        super(form);
        initComponents();
    }

    @Override
    protected synchronized void attachListener() {
        super.attachListener();
        form.getMenuList().forEach(bt -> bt.addActionListener(this));
    }

    @Override
    public synchronized void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        switch (event.getActionCommand()) {
            case "logout":
                Form_Login login = (Form_Login) ControleInstancias.logout(Form_Login.class);
                login.setEntityManagerHelper(new DataBaseHelper(), new Form_Main(), ConfigUtil.getSystemTitle());
                login.setVisible(true);
                break;
            case "cad_marcas":
                Form_Marcas marcas = (Form_Marcas) ControleInstancias.getInstance(Form_Marcas.class.getName());
                marcas.setVisible(true);
                break;
            case "cad_categorias":
                Form_Categoria categorias = (Form_Categoria) ControleInstancias.getInstance(Form_Categoria.class.getName());
                categorias.setVisible(true);
                break;
            case "report":
                Form_ReportManager report = (Form_ReportManager) ControleInstancias.getInstance(Form_ReportManager.class.getName());
                report.setVisible(true);
                break;
        }
    }

}
