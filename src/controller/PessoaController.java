package controller;

import model.entities.Cliente;
import model.entities.Funcionario;
import model.entities.Pessoa;
import model.service.PessoaService;

public class PessoaController {
    private Funcionario model;
    private PessoaService pessoaService = new PessoaService();

    public Funcionario validaLogin(String cpf, String senha) {
        Pessoa pessoa = pessoaService.selectByCPF(cpf);
        if(pessoa instanceof Cliente){
            throw new RuntimeException("Pessoa não permitida!");
        }
        if(((Funcionario) pessoa).validaLogin(senha)){
            return (Funcionario)pessoa;
        }
        throw new RuntimeException("Senha inválida");
    }

    public void removerPessoa(String cpf) {
        pessoaService.remove(cpf);
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoaService.insert(pessoa);
    }
}
