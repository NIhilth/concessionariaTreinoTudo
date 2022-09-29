package model.factory;

import model.entities.*;

public class PessoaFactory {

    public Pessoa getPessoa(Integer tipo, String nome, String cpf, String telefone, Genero genero, int idade, int matricula, String senha){
        try {
            if (tipo == 1) {
                return new Cliente(nome, cpf, telefone, genero, idade, matricula);
            } else if(tipo == 2){
                return new Funcionario(nome, cpf, telefone, genero, idade, matricula, senha);
            } else {
                return new Dono(nome, cpf, telefone, genero, idade, matricula, senha);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Criação de uma pessoa deu errado");
    }
}
