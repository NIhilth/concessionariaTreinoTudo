package controller;

import model.entities.Cliente;
import model.entities.Dono;
import model.entities.Funcionario;
import model.entities.Pessoa;
import model.service.PessoaService;

import java.util.ArrayList;

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

    public void editarPessoa(String antigoCpf,Pessoa pessoa){pessoaService.update(antigoCpf,pessoa);}

    public Pessoa selecionarPessoa(String cpf){return pessoaService.selectByCPF(cpf);}

    public ArrayList<Pessoa> selecionarPessoas(Pessoa usuario){
        ArrayList<Pessoa> listaPessoas = pessoaService.selectAll();

        if(usuario instanceof Dono) {
            return listaPessoas;
        }

        ArrayList<Pessoa> pessoasPraRemover = new ArrayList<>();

        for(Pessoa pessoa : listaPessoas){
            if(pessoa instanceof Funcionario){
                pessoasPraRemover.add(pessoa);
            }
        }

        for(Pessoa pessoa : pessoasPraRemover){
            listaPessoas.remove(pessoa);
        }

        return listaPessoas;
    }
}
