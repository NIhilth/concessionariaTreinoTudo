package view;

import model.entities.Funcionario;
import model.entities.Pessoa;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultTableModelCollection extends AbstractTableModel {
    List<Pessoa> dados;
    String[] colunas;

    public DefaultTableModelCollection(Collection<Pessoa> lista){
        this.dados = new ArrayList<>(lista);
        colunas = new String[]{"Nome", "CPF", "Telefone", "Gênero", "Idade", "Matrícula", "Salário"};
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pessoa pessoa = dados.get(rowIndex);
        switch (columnIndex){
            case 0 -> {
                return pessoa.getNome();
            }
            case 1 -> {
                return pessoa.getCpf();
            }
            case 2 -> {
                return pessoa.getTelefone();
            }
            case 3 -> {
                return pessoa.getGenero().getNome();
            }
            case 4 -> {
                return pessoa.getIdade();
            }
            case 5 -> {
                return pessoa.getMatricula();
            }
            default -> {
                if(pessoa instanceof  Funcionario) {
                    return ((Funcionario) pessoa).getSalario();
                }
                return null;
            }
        }
    }

    @Override
    public String getColumnName(int column){
        return colunas[column];
    }

}
