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

    public TelaCadastroPessoa(Pessoa usuario) {
        this.usuario = usuario;
        if (!(usuario instanceof Dono)) {
            FuncionarioCheck.setVisible(false);
        }
        InputSenha.setVisible(false);
        InputConfirmarSenha.setVisible(false);
        ConfirmarSenhaLabel.setVisible(false);
        SenhaLable.setVisible(false);
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
                        InputSenha.getText().isEmpty() || InputConfirmarSenha.getText().isEmpty()){
                            throw new RuntimeException("Um ou mais campos estão vazios");
                        }

                        if(!InputSenha.getText().equals(InputConfirmarSenha.getText())){
                            throw new RuntimeException("Senhas informadas são diferentes");
                        }

                        pessoa = new Funcionario(InputNome.getText(),InputCPF.getText(),InputTelefone.getText(),
                                (Genero) comboBoxGenero.getSelectedItem(), Integer.parseInt(InputIdade.getText()),
                                0,InputSenha.getText());
                    } else {
                        if(InputNome.getText().isEmpty() || InputCPF.getText().isEmpty() || InputTelefone.getText().isEmpty() || InputIdade.getText().isEmpty()){
                            throw new RuntimeException("Um ou mais campos estão vazios");
                        }

                        pessoa = new Cliente(InputNome.getText(),InputCPF.getText(),InputTelefone.getText(),
                                (Genero) comboBoxGenero.getSelectedItem(), Integer.parseInt(InputIdade.getText()), 0);
                    }
                    PessoaController pessoaController = new PessoaController();
                    pessoaController.cadastrarPessoa(pessoa);
                    JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
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
                ConfirmarSenhaLabel.setVisible(!cadastrandoFuncionario);
                SenhaLable.setVisible(!cadastrandoFuncionario);
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
        setContentPane(CadastroPessoa);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }
}
