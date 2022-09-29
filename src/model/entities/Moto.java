package model.entities;

public class Moto extends Automovel {

    private double velocidadeMaxima;
    private String capacete;
    private boolean temPezinho;

    public Moto(String placa, String modelo, double valor, boolean vendido) {
    }


    public double getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(double velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public String getCapacete() {
        return capacete;
    }

    public void setCapacete(String capacete) {
        this.capacete = capacete;
    }

    @Override
    public String editar(int escolha) {
//        switch (escolha) {
//            case 1:
//                this.setPlaca(Main.tecladin());
//                break;
//            case 2:
//                this.setModelo(Main.tecladin());
//                break;
//            case 3:
//                this.setValor(Double.parseDouble(Main.tecladin()));
//                break;
//            case 4:
//                this.setVelocidadeMaxima(Double.parseDouble(Main.tecladin()));
//                break;
//            case 5:
//                this.setCapacete(Main.tecladin());
//                break;
//            case 6:
//                this.setTemPezinho(Main.temPeDeApoio());
//                break;
//            default:
//                return "Opção inválida!";
//        }
        return "Editado com sucesso!";
    }

    public boolean isTemPezinho() {
        return temPezinho;
    }

    public void setTemPezinho(boolean temPezinho) {
        this.temPezinho = temPezinho;
    }

    public Moto(String placa, String modelo, double valor, boolean vendido, double velocidadeMaxima, String capacete, boolean temPezinho) {
        super(placa, modelo, valor, vendido);
        this.velocidadeMaxima = velocidadeMaxima;
        this.capacete = capacete;
        this.temPezinho = temPezinho;
    }

    public String[] atributos() {
        return new String[]{"Placa", "Modelo", "Valor", "Velocidade Máxima", "Capacete", "Pezinho"};
    }

    @Override
    public String toString() {
        return "\nmodel.entities.Moto: " +
                super.toString() +
                "\nVelocidadeMaxima: " + velocidadeMaxima +
                "\nCapacete: " + capacete +
                "\nTem pé de apoio: " + temPezinho + "\n";
    }
}
