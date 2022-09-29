package model.entities;

import java.util.ArrayList;

public abstract class Pessoa {

    static ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    private String nome, cpf, telefone;
    private int idade, matricula;
    private Genero genero;

    public abstract void mudarSalario(double valor,double porcentagem);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Pessoa(String nome, String cpf, String telefone, Genero genero, int idade, int matricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.genero = genero;
        this.idade = idade;
        this.matricula = matricula;
    }

    public Pessoa(){}

    @Override
    public String toString() {
        return  "\nNome: " + nome +
                "\nCpf: " + cpf +
                "\nTelefone: " + telefone +
                "\nGênero: " + genero +
                "\nIdade: " + idade +
                "\nMatrícula: " + matricula;
    }

    public static void cadastroAutomovel() {
        int escolhaAutomovel = Main.escolherMotoCarro();
        if (escolhaAutomovel == 0) {
            Main.menuPrincipal();
        } else if (escolhaAutomovel > 2 || escolhaAutomovel < 1) {
            System.out.println("Número digitado inválido!");
            cadastroAutomovel();
        } else {
            Automovel.listaAutomoveis.add(Main.novoAutomovel(escolhaAutomovel));
            System.out.println("Cadastro completo!");
            cadastroAutomovel();
        }
    }

    public int checarCredenciais(String cpf, String senha) {
        for(Pessoa pessoa : listaPessoas){
            if(pessoa instanceof Funcionario){
                if(pessoa.getCpf().equals(cpf) && ((Funcionario) pessoa).getSenha().equals(senha)){
                    return pessoa.getMatricula();
                }
            }
        }
        return -1;
    }

    public int checarCredenciais(String cpf) {
        for(int i = 0; i < listaPessoas.size(); i++){
            if(listaPessoas.get(i) instanceof Cliente){
                if(listaPessoas.get(i).getCpf().equals(cpf)){
                    return i;
                }
            }
        }
        return -1;
    }

    public int matriculaNova(int escolha) {
        int matricula = 1;
        for(Pessoa pessoa :listaPessoas){
            if(escolha == 1){
                if(pessoa instanceof Cliente){
                    matricula++;
                }
            } else if( escolha == 2){
                if(pessoa instanceof Funcionario){
                    matricula++;
                }
            }
        }
        return matricula;
    }

    public int pegarPosicao(int id, int escolha) {
        for(int i = 0; i < listaPessoas.size(); i++){
            if(escolha == 1){
                if(listaPessoas.get(i) instanceof Cliente && listaPessoas.get(i).getMatricula() == id){
                   return i;
                }
            } else if( escolha == 2){
                if(listaPessoas.get(i) instanceof Funcionario && listaPessoas.get(i).getMatricula() == id){
                   return i;
                }
            }
        }
        return -1;
    }
}
