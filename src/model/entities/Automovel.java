package model.entities;

import java.util.ArrayList;

public abstract class Automovel {

    static ArrayList<Automovel> listaAutomoveis = new ArrayList<>();

    private String placa, modelo;
    private double valor;
    private boolean vendido;
    private Cliente cliente;


    public Cliente getCliente() {
        return cliente;
    }

    public abstract String editar(int escolha);

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public abstract String[] atributos();

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Automovel(String placa, String modelo, double valor, boolean vendido) {
        this.placa = placa;
        this.modelo = modelo;
        this.valor = valor;
        this.vendido = vendido;
    }

    public Automovel(){}

    @Override
    public String toString() {
        return "\nPlaca: " + placa +
                "\nModelo: " + modelo +
                "\nValor: " + valor;
    }

    public int verificaAutomovel(int tipo, String placa){
        if(tipo == 1){
            for(int i = 0; i< listaAutomoveis.size(); i++){
                if(!listaAutomoveis.get(i).isVendido() && listaAutomoveis.get(i) instanceof Carro && listaAutomoveis.get(i).getPlaca().equals(placa)) {
                    return i;
                }
            }
        } else {
            for(int i = 0; i< listaAutomoveis.size(); i++){
                if(!listaAutomoveis.get(i).isVendido() && listaAutomoveis.get(i) instanceof Moto && listaAutomoveis.get(i).getPlaca().equals(placa)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
