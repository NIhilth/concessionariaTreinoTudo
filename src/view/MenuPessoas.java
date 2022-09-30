package view;

import controller.PessoaController;
import model.entities.Dono;
import model.entities.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPessoas extends JFrame implements ActionListener {

    private Pessoa usuario;
    private JButton cadastrarButton;
    private JButton listarButton;
    private JButton editarButton;
    private JButton removerButton;
    private JButton voltarButton;
    private JPanel MenuPessoas;

    public MenuPessoas(Pessoa usuario) {
        this.usuario = usuario;
        criarComponentes();
    }

    private void criarComponentes() {
        setContentPane(MenuPessoas);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        cadastrarButton.addActionListener(this);
        cadastrarButton.setActionCommand("cadastrarPessoa");
        listarButton.addActionListener(this);
        listarButton.setActionCommand("listarPessoas");
        editarButton.addActionListener(this);
        editarButton.setActionCommand("editarPessoa");
        removerButton.addActionListener(this);
        removerButton.setActionCommand("removerPessoa");
        voltarButton.addActionListener(this);
        voltarButton.setActionCommand("sair");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        PessoaController pessoaController = new PessoaController();
        if (comando.equals("cadastrarPessoa")) {
            TelaCadastroPessoa telaCadastroPessoa = new TelaCadastroPessoa(usuario, null);
            saindo();
            telaCadastroPessoa.setVisible(true);
        } else if (comando.equals("listarPessoas")) {
            dispose();
            //tela de listar pessoas
        } else if (comando.equals("editarPessoa")) {
            String cpf = pegarCPF();
            TelaCadastroPessoa telaCadastroPessoa = new TelaCadastroPessoa(usuario, cpf);
            saindo();
            telaCadastroPessoa.setVisible(true);
        } else if (comando.equals("removerPessoa")) {
            String cpf = pegarCPF();
            if (cpf != null) {
                try {
                    pessoaController.removerPessoa(cpf);
                    JOptionPane.showMessageDialog(null, "Pessoa removida com sucesso!");
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null,"Nenhum cpf informado!");
            }
        } else if (comando.equals("sair")) {
            Menu menu = new Menu(usuario);
            saindo();
            menu.setVisible(true);
        }
    }

    private void saindo() {
        usuario = null;
        dispose();
    }

    private String pegarCPF() {
        return JOptionPane.showInputDialog("Qual o cpf da pessoa?");
    }
}
