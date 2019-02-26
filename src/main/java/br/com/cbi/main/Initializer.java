/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.main;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.entities.Usuario_Sistema;
import br.com.cbi.frames.Form_Main;
import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.frames.Form_Splash;
import br.com.fs.api.util.ConfigUtil;
import br.com.fs.api.util.ControleInstancias;
import br.com.fs.api.util.Utilidades;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tiago
 */
@Component
public class Initializer implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        DataBaseHelper db = new DataBaseHelper();
        Form_Splash splash = new Form_Splash(db, new Form_Main(), ConfigUtil.getSystemTitle());
        //Form_Splash splash = new Form_Splash(db, null, ConfigUtil.getSystemTitle());
        ControleInstancias.setControleInstancias(Form_Splash.class.getName(), splash);
        splash.setVisible(true);
        Usuario_Sistema user = new Usuario_Sistema();
        user.setNomeUsuario("Administrador");
        user.setLogin("admin");
        user.setSenha(Utilidades.encriptyPassword("admin"));
        db.getOperation(EntityManagerHelper.SALVAR, user, EntityManagerHelper.DERBYDB_PU);
    }

}
