/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.odbc;

import br.com.cbi.dal.DataBaseHelper;
import br.com.cbi.entities.Categoria_Equipamento;
import br.com.cbi.entities.Cidade;
import br.com.cbi.entities.Marca_Equipamento;
import br.com.fs.api.util.FileUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Tiago
 */
public class CbiAccessClone {

    private final ConnectionOdbc connection;
    private final AccessUtil access;
    private final Map<String, String> map;

    public CbiAccessClone() {
        connection = new ConnectionOdbc();
        access = new AccessUtil(connection);
        map = new HashMap();
    }

    public Runnable registrarMarcas() {
        return () -> {
            map.clear();
            map.put("id", "Código");
            map.put("descricao", "Marca");
            List<Marca_Equipamento> marcas = (List<Marca_Equipamento>) access.getObjectList("SELECT * FROM Marcas", Marca_Equipamento.class, map);
            DataBaseHelper dbh = new DataBaseHelper();
            marcas.stream().forEach(m -> dbh.getOperation(DataBaseHelper.SALVAR, m, DataBaseHelper.DERBYDB_PU));
        };
    }

    public Runnable registrarCategorias() {
        return () -> {
            map.clear();
            map.put("descricao", "descricao");
            List<Categoria_Equipamento> categorias = (List<Categoria_Equipamento>) access.getObjectList("SELECT * FROM categoriaEquipamento", Categoria_Equipamento.class, map);
            DataBaseHelper dbh = new DataBaseHelper();
            categorias.stream().forEach(c -> dbh.getOperation(DataBaseHelper.SALVAR, c, DataBaseHelper.DERBYDB_PU));
        };
    }

    public Runnable registrarCidades() {
        return () -> {
            map.clear();
            map.put("nome", "Descricao");
            map.put("estadostr", "UF");
            List<Cidade> cidades = (List<Cidade>) access.getObjectList("SELECT * FROM tblCidades", Cidade.class, map);
            List<String> linhas = cidades.stream().map(c -> c.toString()).collect(Collectors.toList());
            for (int i = 0; i < linhas.size(); i++) {
                String l = linhas.get(i);
                l = l.replace("{cidade}", String.valueOf(i + 1));
                linhas.set(i, l);
            }
            FileUtil.writeFile("sql.txt", linhas);
            //DataBaseHelper dbh = new DataBaseHelper();
            //cidades.stream().forEach(c -> dbh.getOperation(DataBaseHelper.SALVAR, c, DataBaseHelper.DERBYDB_PU));
        };
    }

}
