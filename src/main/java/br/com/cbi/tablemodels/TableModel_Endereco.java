/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodels;

import br.com.cbi.entities.Endereco;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import java.util.List;

/**
 *
 * @author Tiago
 */
public final class TableModel_Endereco extends TableModelDefaultAdapter<Endereco> {

    private static final long serialVersionUID = 732263539811974400L;
    private final String[] columnsName = new String[]{"CEP", "Endereço", "Número", "Bairro", "Compl.", "Cidade", "Estado", "Observação"};

    public TableModel_Endereco() {
        setColmunName(columnsName);
    }

    public TableModel_Endereco(List<Endereco> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Endereco end = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return end.getCep();
            case 1:
                return end.getLogradouro();
            case 2:
                return end.getNumero();
            case 3:
                return end.getBairro();
            case 4:
                return end.getComplemento();
            case 5:
                return end.getCidade() != null ? end.getCidade().getNome() : null;
            case 6:
                if (end.getCidade() != null) {
                    if (end.getCidade().getEstado() != null) {
                        return end.getCidade().getEstado().getNome();
                    }
                }
                return null;
            case 7:
                return end.getObservacao();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Endereco endereco = lista.get(rowIndex);
        if (aValue != null) {
            switch (columnIndex) {
                case 0:
                    endereco.setCep(aValue.toString());
                    break;
                case 1:
                    endereco.setLogradouro(aValue.toString());
                    break;
                case 2:
                    endereco.setNumero(aValue.toString());
                    break;
                case 3:
                    endereco.setBairro(aValue.toString());
                    break;
                case 4:
                    endereco.setComplemento(aValue.toString());
                    break;
                case 5:
                case 6:
                    //endereco.setCidade(new Cidade());
                    break;
                case 7:
                    endereco.setObservacao(aValue.toString());
                    break;
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
