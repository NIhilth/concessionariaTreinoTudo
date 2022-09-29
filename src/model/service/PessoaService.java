package model.service;

import model.dao.PessoaDAO;
import model.entities.Pessoa;

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
}
