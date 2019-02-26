/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodels;

import br.com.cbi.entities.Marca_Equipamento;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class TableModel_Pesquisa_Marcas extends TableModelDefaultAdapter<Marca_Equipamento> {

    private static final long serialVersionUID = 8402911710553815412L;
    private final String[] columnsName = new String[]{"Código", "Descrição"};

    public TableModel_Pesquisa_Marcas() {
        setColmunName(columnsName);
    }

    public TableModel_Pesquisa_Marcas(List<Marca_Equipamento> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Marca_Equipamento marca = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return marca.getId().toString();
            case 1:
                return marca.getDescricao();
            default:
                return null;
        }
    }

}
