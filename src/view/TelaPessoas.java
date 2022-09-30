package view;

import controller.PessoaController;
import model.entities.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPessoas extends JFrame{
    private Pessoa usuarioLogado;
    private JPanel TelaPessoas;
    private JButton voltarButton;
    private JTable tabelaPessoas;

    public TelaPessoas(Pessoa usuario){
        this.usuarioLogado = usuario;
        criarComponentes();
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPessoas menuPessoas = new MenuPessoas(usuarioLogado);
                usuarioLogado = null;
                menuPessoas.setVisible(true);
            }
        });
    }

    private void criarComponentes() {
        PessoaController pessoaController = new PessoaController();
        tabelaPessoas.setModel(new DefaultTableModelCollection(pessoaController.selecionarPessoas(usuarioLogado)));
        tabelaPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setContentPane(TelaPessoas);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }
}
