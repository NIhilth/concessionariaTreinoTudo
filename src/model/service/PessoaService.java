package model.service;

import model.dao.PessoaDAO;
import model.entities.Pessoa;

import java.util.ArrayList;

public class PessoaService {
    PessoaDAO pessoaDAO = new PessoaDAO();

    public Pessoa selectByCPF(String cpf){
        return pessoaDAO.selectByCPF(cpf);
    }

    public void insert(Pessoa pessoa) {
        pessoaDAO.insert(pessoa);
    }

    public void remove(String cpf) {
        pessoaDAO.remove(cpf);
    }

    public void update(String antigoCpf,Pessoa pessoa){pessoaDAO.update(antigoCpf,pessoa);}

    public ArrayList<Pessoa> selectAll() {
        return pessoaDAO.selectAll();
    }
}
