package model.entities;

public class Carro extends Automovel{

    private int qtdPortas;
    private boolean conversivel, manual;

    public Carro(String placa, String modelo, double valor, boolean vendido) {
    }

    public int getQtdPortas() {
        return qtdPortas;
    }

    public void setQtdPortas(int qtdPortas) {
        this.qtdPortas = qtdPortas;
    }

    public boolean isConversivel() {
        return conversivel;
    }

    public void setConversivel(boolean conversivel) {
        this.conversivel = conversivel;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public Carro(String placa, String modelo, double valor, boolean vendido, int qtdPortas, boolean conversivel, boolean manual) {
        super(placa, modelo, valor, vendido);
        this.qtdPortas = qtdPortas;
        this.conversivel = conversivel;
        this.manual = manual;
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
//                this.setQtdPortas(Integer.parseInt(Main.tecladin()));
//                break;
//            case 5:
//                this.setManual(Main.eManual());
//                break;
//            case 6:
//                this.setConversivel(Main.eConversivel());
//                break;
//            default:
//                return "Opção inválida!";
//        }
        return "Editado com sucesso!";
    }


    public String[] atributos() {
        return new String[] {"Placa","Modelo","Valor","Quantidade de Portas", "Conversível", "Manual"};
    }

    @Override
    public String toString() {
        return  "\nmodel.entities.Carro: " +
                super.toString() +
                "\nQuantidade de portas: " + qtdPortas +
                "\nÉ conversível: " + conversivel +
                "\nÉ manual: " + manual + "\n";
    }
}
