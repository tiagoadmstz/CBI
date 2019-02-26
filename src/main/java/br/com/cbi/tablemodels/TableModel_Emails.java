/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodels;

import br.com.cbi.entities.Email;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import java.util.List;

/**
 *
 * @author Tiago
 */
public final class TableModel_Emails extends TableModelDefaultAdapter<Email> {

    private static final long serialVersionUID = 59715926284322114L;
    private final String[] columnsName = new String[]{"E-mail", "Contato"};

    public TableModel_Emails() {
        setColmunName(columnsName);
    }

    public TableModel_Emails(List<Email> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Email email = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return email.getEmail();
            case 1:
                return email.getContato();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Email email = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                email.setEmail(aValue != null ? aValue.toString() : null);
                break;
            case 1:
                email.setContato(aValue != null ? aValue.toString() : null);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
}
