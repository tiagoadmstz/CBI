/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodels;

import br.com.cbi.entities.Categoria_Equipamento;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import java.util.List;

/**
 *
 * @author Tiago
 */
public final class TableModel_Pesquisa_Categoria extends TableModelDefaultAdapter<Categoria_Equipamento> {

    private static final long serialVersionUID = -8042977968535038187L;
    private final String[] columnsName = new String[]{"Código", "Descrição"};

    public TableModel_Pesquisa_Categoria() {
        setColmunName(columnsName);
    }

    public TableModel_Pesquisa_Categoria(List<Categoria_Equipamento> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria_Equipamento cat = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cat.getId().toString();
            case 1:
                return cat.getDescricao();
            default:
                return null;
        }
    }

}
