package model.entities;

public class Cliente extends Pessoa{

    @Override
    public void mudarSalario(double valor, double porcentagem) {}

    public Cliente(String nome, String cpf, String telefone, Genero genero, int idade, int matricula) {
        super(nome, cpf, telefone, genero, idade, matricula);
    }

    public Cliente(){}

    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}
