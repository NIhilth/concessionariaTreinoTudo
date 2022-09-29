package model.entities;

public class Dono extends Funcionario{

    private double salario = 30000.0;

    public Dono(String nome, String cpf, String telefone, Genero genero, int idade, int matricula, String senha) {
        super(nome, cpf, telefone, genero, idade, matricula, senha);
    }

    @Override
    public void mudarSalario(double precoAuto, double porc){
        this.setSalario(this.getSalario());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
