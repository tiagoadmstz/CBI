/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodels;

import br.com.cbi.entities.Telefone;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import java.util.List;

/**
 *
 * @author Tiago
 */
public final class TableModel_Telefones extends TableModelDefaultAdapter<Telefone> {

    private static final long serialVersionUID = 7579604012708264321L;
    private final String[] columnsName = new String[]{"Tipo", "Telefone", "Contato"};

    public TableModel_Telefones() {
        setColmunName(columnsName);
    }

    public TableModel_Telefones(List<Telefone> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Telefone telefone = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return telefone.getTipo_telefone() != null ? telefone.getTipo_telefone().getValor() : null;
            case 1:
                return telefone.getTelefone();
            case 2:
                return telefone.getContato();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Telefone telefone = lista.get(rowIndex);
        if (aValue != null) {
            switch (columnIndex) {
                case 0:
                    telefone.setTipo_telefone_string(aValue.toString());
                    break;
                case 1:
                    telefone.setTelefone(aValue.toString());
                    break;
                case 2:
                    telefone.setContato(aValue.toString());
                    break;
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
