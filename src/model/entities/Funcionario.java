package model.entities;

public class Funcionario extends Pessoa{

    private double salario = 1250;
    private String senha;

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcionario(String nome, String cpf, String telefone, Genero genero, int idade, int matricula, String senha) {
        super(nome, cpf, telefone, genero, idade, matricula);
        this.senha = senha;
    }

    public Funcionario(String nome, String cpf, String telefone, Genero genero, int idade, int matricula, String senha, double salario) {
        super(nome, cpf, telefone, genero, idade, matricula);
        this.senha = senha;
        this.salario = salario;
    }

    public void mudarSalario(double precoAuto, double porc){
        if(porc == -1){
            this.salario += precoAuto * 0.02;
        } else {
            this.salario += precoAuto * porc;
        }
    }

    @Override
    public String toString() {
        return "Funcionário: " +
                super.toString() +
                "\nSalario: " + salario +
                "\nSenha: " + senha + "\n";
    }


    public boolean validaLogin(String senha){
        return this.getSenha().equals(senha);
    }

}
