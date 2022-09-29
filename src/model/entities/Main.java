package model.entities;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Pessoa usuario;
    static int matricula;

    public static void main(String[] args) {
//        Automovel moto = new Moto("ABC1234", "Off-road", 20000, false, 155.3, "LS2", true);
//        Automovel.listaAutomoveis.add(moto);
//        Automovel carro = new Carro("XYZ9876", "Fusca", 25000, false, 4, false, true);
//        Automovel.listaAutomoveis.add(carro);
//        Pessoa dono = new Dono("Flávio", "12345678910", "47992008870", "Masculino", 60, 1, "loló");
//        Pessoa.listaPessoas.add(dono);
//        Pessoa funcionario = new Funcionario("Cássia", "98765432101", "47992008810", "Feminino", 29, 2, "cassioLimdo");
//        Pessoa.listaPessoas.add(funcionario);
//        Pessoa cliente = new Cliente("Marcia", "19283746501", "47991175171", "Feminino", 45, 1);
//        Pessoa.listaPessoas.add(cliente);
//        Pessoa cliente2 = new Cliente("Marcos", "57846381769", "47982017583", "Masculino", 36, 2);
//        Pessoa.listaPessoas.add(cliente2);
//        Pessoa cliente3 = new Cliente("Bruno", "12685898276", "47987461524", "Masculino", 26, 3);
//        Pessoa.listaPessoas.add(cliente3);
        login();
    }

    public static void login() {
        System.out.println("----- LOGIN ----- \nQual seu cpf?");
        String cpf = sc.next();

        System.out.println("Qual a sua senha?");
        String senha = sc.next();

        int checar = Pessoa.listaPessoas.get(0).checarCredenciais(cpf, senha);

        if (checar != -1) {
            usuario = Pessoa.listaPessoas.get(checar);
            menuPrincipal();
        } else {
            System.out.println("Credenciais inválidas!");
            login();
        }
    }

    public static void menuPrincipal() {
        System.out.println("""
                ----- MENU -----\s
                 1 - Cadastrar automóvel               2 - Editar automóvel\s
                 3 - Listar automóveis no estoque      4 - Remover automóvel\s
                 5 - Listar automóveis vendidos        6 - Vender automóvel\s
                 7 - Cadastrar Pessoa                  8 - Remover Pessoa\s
                 9 - Editar Pessoa                    10 - Listar Pessoas
                11 - Deslogar                         12 - Sair""");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.println("----- MENU CADASTRO -----");
                usuario.cadastroAutomovel();
            }
            case 2 -> editarAutomovel();
            case 3 -> listarAutomovel();
            case 4 -> removerAutomovel();
            case 5 -> listarVendidos();
            case 6 -> venderAutomovel();
            case 7 -> cadastrarMenu();
            case 8 -> removerMenu();
            case 9 -> editarMenu();
            case 10 -> listarMenu();
            case 11 -> login();
            case 12 -> System.exit(0);
        }
    }

    public static void removerMenu() {
        if (matricula != 1) {
            removerPessoa(1);
        } else {
            int opcao = escolherPessoa("remover");

            if (opcao == 0) {
                menuPrincipal();
            }

            removerPessoa(opcao);

        }

        System.out.println("Remoção Completa");
        menuPrincipal();
    }

    public static void removerPessoa(int opcao) {
        int posicao, id;
        do {
            id = pegarID();
            posicao = Pessoa.listaPessoas.get(0).pegarPosicao(id, opcao);
            if (posicao == -1) {
                System.out.println("ID inválido");
            }
        } while (posicao == -1);

        System.out.println("Quer mesmo remover essa pessoa dos registros? \n1 - Sim \n2 - Não");
        int escolha = sc.nextInt();

        if (escolha == 1) {
            Pessoa.listaPessoas.remove(posicao);
            System.out.println("Remoção concluída!");
        } else {
            System.out.println("Remoção cancelada!");
        }
        menuPrincipal();

    }

    public static void editarMenu() {
        if (matricula != 1) {
            editarPessoa(1);
        } else {
            int opcao = escolherPessoa("editar");

            if (opcao == 0) {
                menuPrincipal();
            }

            editarPessoa(opcao);

        }

        System.out.println("Edição Completa");
        menuPrincipal();
    }

    public static void editarPessoa(int opcao) {
        int posicao, id;
        do {
            id = pegarID();
            posicao = Pessoa.listaPessoas.get(0).pegarPosicao(id, opcao);
            if (posicao == -1) {
                System.out.println("ID inválido");
            }
        } while (posicao == -1);

        if (opcao == 1) {
            Pessoa.listaPessoas.set(posicao, cadastrarPessoa(1));
        } else {
            Pessoa.listaPessoas.set(posicao, cadastrarPessoa(2));
        }
    }

    public static int pegarID() {
        System.out.println("Qual o ID da pessoa?");
        return sc.nextInt();
    }

    public static void listarMenu() {
        if (matricula != 1) {
            listarPessoas(1);
        } else {
            int opcao = escolherPessoa("listar");

            if (opcao == 0) {
                menuPrincipal();
            }

            listarPessoas(opcao);
        }

        System.out.println("Listagem Completa");
        menuPrincipal();
    }

    public static void listarPessoas(int opcao) {
        System.out.println("----- LISTAR -----");
        if (opcao == 1) {
            for (Pessoa pessoa : Pessoa.listaPessoas) {
                if (pessoa instanceof Cliente) {
                    System.out.println(pessoa);
                }
            }
        } else {
            for (Pessoa pessoa : Pessoa.listaPessoas) {
                if (pessoa instanceof Funcionario) {
                    System.out.println(pessoa);
                }
            }
        }
    }

    public static void cadastrarMenu() {
        if (matricula != 1) {
            Pessoa.listaPessoas.add(cadastrarPessoa(1));
        } else {
            int opcao = escolherPessoa("cadastrar");

            if (opcao == 0) {
                menuPrincipal();
            }

            Pessoa.listaPessoas.add(cadastrarPessoa(opcao));
        }

        System.out.println("Cadastro concluído!");
        menuPrincipal();
    }

    public static int escolherPessoa(String acao) {
        System.out.println("Quer " + acao + " um cliente ou um funcionário? \n1 - model.entities.Cliente \n2 - Funcionário \nDigite 0 para voltar");
        return sc.nextInt();
    }

    public static Pessoa cadastrarPessoa(int opcao) {
        System.out.println("----- CADASTRO ----- \nNome :");
        String nome = sc.next();

        System.out.println("CPF:");
        String cpf = sc.next();

        System.out.println("Telefone:");
        String telefone = sc.next();

        System.out.println("Gênero:");
        String genero = sc.next();

        System.out.println("Idade");
        int idade = sc.nextInt();

        if (opcao == 1) {
            int matricula = Pessoa.listaPessoas.get(0).matriculaNova(1);
            return new Cliente(nome, cpf, telefone, Genero.valueOf(genero), idade, matricula);
        } else {
            int matricula = Pessoa.listaPessoas.get(0).matriculaNova(2);

            System.out.println("Senha:");
            String senha = sc.next();

            return new Funcionario(nome, cpf, telefone,  Genero.valueOf(genero), idade, matricula, senha);
        }
    }

    public static void venderAutomovel() {
        double porc = -1;
        boolean checar = false;
        String nome = "";
        int vez = 0, contador = 0, indiceCliente = 0;

        System.out.println("----- MENU VENDA -----");
        int escolha = escolherMotoCarro();

        if (escolha == 0) {
            menuPrincipal();
        } else if (escolha > 2 || escolha < 1) {
            System.out.println("Opção inválida");
            venderAutomovel();
        }

        String placa = qualAutomovel();

        int posicao = Automovel.listaAutomoveis.get(0).verificaAutomovel(escolha, placa);

        if (posicao == -1) {
            System.out.println("Placa inválida!");
            venderAutomovel();
        }

        do {
            System.out.println("Para qual cliente será vendido? \nDigite 0 para cancelar a ação");
            for (int i = 0; i < Pessoa.listaPessoas.size(); i++) {
                if (Pessoa.listaPessoas.get(i) instanceof Cliente) {
                    if (vez == 0) {
                        System.out.println(Pessoa.listaPessoas.get(i).getNome());
                    } else {
                        if (Pessoa.listaPessoas.get(i).getNome().length() >= nome.length()) {
                            for (int j = 0; j < nome.length(); j++) {
                                if (nome.charAt(j) == Pessoa.listaPessoas.get(i).getNome().charAt(j)) {
                                    contador++;
                                }
                            }
                        }
                        if (contador == nome.length()) {
                            System.out.println(Pessoa.listaPessoas.get(i).getNome());
                        }
                    }
                }
                contador = 0;
                vez++;
            }
            nome = sc.next();
            for (int i = 0; i < Pessoa.listaPessoas.size(); i++) {
                if (Pessoa.listaPessoas.get(i) instanceof Cliente) {
                    if (nome.equals(Pessoa.listaPessoas.get(i).getNome())) {
                        checar = true;
                        indiceCliente = i;
                        break;
                    }
                }
            }
            if (nome.equals(0)) {
                menuPrincipal();
            }

        } while (!checar);

        int pegarPosicao = Pessoa.listaPessoas.get(0).pegarPosicao(matricula, 2);

        if (Pessoa.listaPessoas.get(pegarPosicao) instanceof Funcionario) {
            System.out.println("Você quer alterar o porcentual de comissão? \n1 - Sim \n2 - Não");
            int opcao = sc.nextInt();

            if (opcao == 1) {
                do {
                    System.out.println("Qual a porcentagem desejada (0,xx)?");
                    porc = sc.nextDouble();

                    if (porc > 0.02) {
                        System.out.println("Porcentagem informada mais alta do que a padrão!");
                    }
                } while (porc > 0.02);
            }
        }

        usuario.mudarSalario(Automovel.listaAutomoveis.get(posicao).getValor(), porc);

        Automovel.listaAutomoveis.get(posicao).setVendido(true);
        Automovel.listaAutomoveis.get(posicao).setCliente(((Cliente) Pessoa.listaPessoas.get(indiceCliente)));

        System.out.println("Venda concluída!");
        menuPrincipal();
    }

    public static void removerAutomovel() {
        System.out.println("----- MENU DE REMOÇÃO -----");
        int automovel = escolherMotoCarro();

        String placa = qualAutomovel();

        if (automovel == 0) {
            menuPrincipal();
        } else if (automovel == 1 || automovel == 2) {
            int posicao = Automovel.listaAutomoveis.get(0).verificaAutomovel(automovel, placa);
            if (posicao != -1) {
                System.out.println("Quer mesmo deletar esse model.entities.Carro \n1 - Sim \n2 - Não");
                int escolha = sc.nextInt();

                if (escolha == 1) {
                    Automovel.listaAutomoveis.remove(posicao);
                    System.out.println("Remoção concluída");
                    removerAutomovel();
                } else {
                    menuPrincipal();
                }
            } else {
                System.out.println("Placa inválida!");
                removerAutomovel();
            }
        } else {
            System.out.println("Número digitado inválido!");
            removerAutomovel();
        }
    }

    public static void listarAutomovel() {
        System.out.println("----- MENU DE LISTAGEM -----");
        int automovel = escolherMotoCarro();

        if (automovel == 0) {
            menuPrincipal();
        } else if (automovel == 1) {
            for (int i = 0; i < Automovel.listaAutomoveis.size(); i++) {
                if (!Automovel.listaAutomoveis.get(i).isVendido() && Automovel.listaAutomoveis.get(i) instanceof Carro) {
                    System.out.println(Automovel.listaAutomoveis.get(i).toString());
                }
            }
            System.out.println("Listagem concluída!");
            listarAutomovel();
        } else if (automovel == 2) {
            for (int i = 0; i < Automovel.listaAutomoveis.size(); i++) {
                if (!Automovel.listaAutomoveis.get(i).isVendido() && Automovel.listaAutomoveis.get(i) instanceof Moto) {
                    System.out.println(Automovel.listaAutomoveis.get(i).toString());
                }
            }
            System.out.println("Listagem concluída!");
            listarAutomovel();
        } else {
            System.out.println("Número digitado inválido!");
            listarAutomovel();
        }
    }

    public static void listarVendidos() {
        System.out.println("----- MENU DE LISTAGEM -----");
        int automovel = escolherMotoCarro();

        if (automovel == 0) {
            menuPrincipal();
        } else if (automovel == 1) {
            for (int i = 0; i < Automovel.listaAutomoveis.size(); i++) {
                if (Automovel.listaAutomoveis.get(i).isVendido() && Automovel.listaAutomoveis.get(i) instanceof Carro) {
                    System.out.println(Automovel.listaAutomoveis.get(i).toString());
                }
            }

            System.out.println("Listagem concluída!");
            listarVendidos();
        } else if (automovel == 2) {
            for (int i = 0; i < Automovel.listaAutomoveis.size(); i++) {
                if (Automovel.listaAutomoveis.get(i).isVendido() && Automovel.listaAutomoveis.get(i) instanceof Moto) {
                    System.out.println(Automovel.listaAutomoveis.get(i).toString());
                }
            }

            System.out.println("Listagem concluída!");
            listarVendidos();
        } else {
            System.out.println("Número digitado inválido!");
            listarAutomovel();
        }
    }

    public static void editarAutomovel() {
        System.out.println("----- MENU EDIÇÃO -----");
        int automovel = escolherMotoCarro();

        System.out.println("Quer re-cadastrar um automóvel completamente? \n1 - Sim \n2 - Não \nDigite 0 Para encerrar");
        int escolha = sc.nextInt();

        if (automovel == 0) {
            menuPrincipal();
        } else if (automovel == 1 || automovel == 2) {
            System.out.println("----- EDITAR" + ((automovel == 1) ? "CARRO" : "MOTO") + "-----");
            String placa = qualAutomovel();

            if (placa.equals("0")) {
                editarAutomovel();
            }

            int verificar = Automovel.listaAutomoveis.get(0).verificaAutomovel(automovel, placa);

            if (verificar != -1) {
                if (escolha == 1) {
                    Automovel.listaAutomoveis.set(verificar, novoAutomovel(escolha));
                } else if (escolha == 2) {
                    System.out.println("Qual atributo você gostaria de editar?");
                    for(int i = 0; i < Automovel.listaAutomoveis.size();i++){
                        System.out.println((i+1) + " - " + Automovel.listaAutomoveis.get(verificar).atributos()[i]);
                    }
                    escolha = sc.nextInt();
                    System.out.println(Automovel.listaAutomoveis.get(verificar).atributos()[escolha]);
                    System.out.println(Automovel.listaAutomoveis.get(verificar).editar(escolha));
                } else if (escolha == 0) {
                    editarAutomovel();
                }
            } else {
                System.out.println("Placa informada inválida!");
                editarAutomovel();
            }
        } else {
            System.out.println("Número digitado inválido!");
            editarAutomovel();
        }
    }
    public static String tecladin(){
        return sc.next();
    }

    public static boolean eConversivel() {
        int opcao;
        do {
            System.out.println("É conversível? \n1 - true \n2 - false");
            opcao = sc.nextInt();
            if (opcao == 1) {
                return true;
            } else {
                System.out.println("Opção inválida");
            }
        } while (opcao > 2 || opcao < 1);
        return false;
    }

    public static boolean eManual() {
        int opcao;
        boolean manual = false;

        do {
            System.out.println("É manual? \n1 - true \n2 - false");
            opcao = sc.nextInt();

            if (opcao == 1) {
                manual = true;
            } else {
                System.out.println("Opção inválida");
            }
        } while (opcao > 2 || opcao < 1);

        return manual;
    }

    public static boolean temPeDeApoio() {
        int opcao;
        boolean temPe = false;

        do {
            System.out.println("Tem pé de apoio? \n1 - Sim \n2 - Não");
            opcao = sc.nextInt();

            if (opcao == 1) {
                temPe = true;
            } else {
                System.out.println("Opção inválida");
            }
        } while (opcao > 2 || opcao < 1);

        return temPe;
    }

    public static Automovel novoAutomovel(int opcao) {
        System.out.println("Cadastro: \nPlaca: ");
        String placa = sc.next();

        System.out.println("Modelo: ");
        String modelo = sc.next();

        System.out.println("Valor ");
        double valor = sc.nextDouble();
        if (opcao == 1) {
            Carro carro = new Carro(placa, modelo, valor, false);
            carro.setManual(eManual());
            carro.setConversivel(eConversivel());
            carro.setQtdPortas(Integer.parseInt(interage(3, carro)));
            return carro;
        } else if (opcao == 2) {
            Moto moto = new Moto(placa, modelo, valor, false);
            moto.setTemPezinho(temPeDeApoio());
            moto.setCapacete(interage(4,moto));
            moto.setVelocidadeMaxima(Double.parseDouble(interage(3,moto)));
            return moto;
        }
        return null;
    }

    public static String interage(int atributo, Automovel auto){
        System.out.println(auto.atributos()[atributo]);
        return sc.next();
    }

    public static String qualAutomovel() {
        System.out.println("Qual a placa do automóvel? \nDigite 0 Para encerrar");
        return sc.next();
    }

    public static int escolherMotoCarro() {
        System.out.println("1 - model.entities.Carro \n2 - model.entities.Moto \nDigite 0 Para encerrar");
        return sc.nextInt();
    }

}