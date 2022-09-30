package view;

import controller.PessoaController;
import model.entities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TelaCadastroPessoa extends JFrame {
    private Boolean cadastrandoFuncionario = false;
    private Pessoa usuario;
    private Pessoa pessoaPraEditar;
    private JButton voltarButton;
    private JButton cadastrarButton;
    private JPanel CadastroPessoa;
    private JTextField InputCPF;
    private JTextField InputTelefone;
    private JTextField InputIdade;
    private JComboBox comboBoxGenero;
    private JTextField InputNome;
    private JCheckBox FuncionarioCheck;
    private JPasswordField InputSenha;
    private JPasswordField InputConfirmarSenha;
    private JLabel ConfirmarSenhaLabel;
    private JLabel SenhaLable;
    private JTextField InputSalario;
    private JLabel SalarioLabel;
    private JLabel titulo;

    public TelaCadastroPessoa(Pessoa usuario, String cpf) {
        this.usuario = usuario;
        if(cpf != null){
            this.pessoaPraEditar = new PessoaController().selecionarPessoa(cpf);
            FuncionarioCheck.setVisible(false);
            if(pessoaPraEditar instanceof Cliente){
                InputSenha.setVisible(false);
                InputConfirmarSenha.setVisible(false);
                InputSalario.setVisible(false);
                SenhaLable.setVisible(false);
                ConfirmarSenhaLabel.setVisible(false);
                SalarioLabel.setVisible(false);
            }
        } else {
            if(!(usuario instanceof Dono)){
                FuncionarioCheck.setVisible(false);
            }

            InputSenha.setVisible(false);
            InputConfirmarSenha.setVisible(false);
            InputSalario.setVisible(false);
            SenhaLable.setVisible(false);
            ConfirmarSenhaLabel.setVisible(false);
            SalarioLabel.setVisible(false);
        }

        criarComponentes();
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Pessoa pessoa = null;
                    if(cadastrandoFuncionario){
                        if(InputNome.getText().isEmpty() || InputCPF.getText().isEmpty() ||
                        InputTelefone.getText().isEmpty() || InputIdade.getText().isEmpty() ||
                        InputSenha.getText().isEmpty() || InputConfirmarSenha.getText().isEmpty()
                        || InputSalario.getText().isEmpty()){
                            throw new RuntimeException("Um ou mais campos estão vazios");
                        }

                        if(!InputSenha.getText().equals(InputConfirmarSenha.getText())){
                            throw new RuntimeException("Senhas informadas são diferentes");
                        }

                        pessoa = new Funcionario(InputNome.getText(),InputCPF.getText(),InputTelefone.getText(),
                                (Genero) comboBoxGenero.getSelectedItem(), Integer.parseInt(InputIdade.getText()),
                                0,InputSenha.getText(), Double.parseDouble(InputSalario.getText()));
                    } else {
                        if(InputNome.getText().isEmpty() || InputCPF.getText().isEmpty() || InputTelefone.getText().isEmpty() || InputIdade.getText().isEmpty()){
                            throw new RuntimeException("Um ou mais campos estão vazios");
                        }

                        pessoa = new Cliente(InputNome.getText(),InputCPF.getText(),InputTelefone.getText(),
                                (Genero) comboBoxGenero.getSelectedItem(), Integer.parseInt(InputIdade.getText()), 0);
                    }
                    PessoaController pessoaController = new PessoaController();
                    String acao = "";
                    if(pessoaPraEditar != null){
                        pessoaController.editarPessoa(cpf,pessoa);
                        acao = "editada";
                    } else {
                        pessoaController.cadastrarPessoa(pessoa);
                        acao = "cadastrada";
                    }
                    JOptionPane.showMessageDialog(null, "Pessoa "+ acao +" com sucesso!");
                    voltar();
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        FuncionarioCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                InputSenha.setVisible(!cadastrandoFuncionario);
                InputConfirmarSenha.setVisible(!cadastrandoFuncionario);
                InputSalario.setVisible(!cadastrandoFuncionario);
                SenhaLable.setVisible(!cadastrandoFuncionario);
                ConfirmarSenhaLabel.setVisible(!cadastrandoFuncionario);
                SalarioLabel.setVisible(!cadastrandoFuncionario);
                cadastrandoFuncionario = !cadastrandoFuncionario;
            }
        });
    }

    private void voltar() {
        MenuPessoas cadastroPessoa = new MenuPessoas(usuario);
        dispose();
        this.usuario = null;
        cadastroPessoa.setVisible(true);
    }

    private void criarComponentes() {
        comboBoxGenero.setModel(new DefaultComboBoxModel(Genero.values()));
        if(this.pessoaPraEditar != null){
            titulo.setText("EDIÇÃO PESSOA");
            cadastrarButton.setText("EDITAR");

            Pessoa pessoa = pessoaPraEditar;
            InputNome.setText(pessoa.getNome());
            InputCPF.setText(pessoa.getCpf());
            InputTelefone.setText(pessoa.getTelefone());
            InputIdade.setText(String.valueOf(pessoa.getIdade()));
            comboBoxGenero.setSelectedItem(pessoa.getGenero());
            if(pessoa instanceof Funcionario){
                InputSalario.setText(String.valueOf(((Funcionario)pessoa).getSalario()));
                InputSenha.setText(((Funcionario) pessoa).getSenha());
                InputConfirmarSenha.setText(((Funcionario) pessoa).getSenha());
            }
        }
        setContentPane(CadastroPessoa);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }
}
