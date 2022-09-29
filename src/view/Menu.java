package view;

import model.entities.Dono;
import model.entities.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private Pessoa usuario;
    private JPanel MenuGeral;
    private JButton automóveisButton;
    private JButton pessoasButton;
    private JButton sairButton;

    public Menu(Pessoa pessoa){
        this.usuario = pessoa;
        criarComponentes();
    }

    private void criarComponentes() {
        setContentPane(MenuGeral);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        automóveisButton.addActionListener(this);
        automóveisButton.setActionCommand("verOpçõesAutomóveis");
        pessoasButton.addActionListener(this);
        pessoasButton.setActionCommand("verOpçõesPessoas");
        sairButton.addActionListener(this);
        sairButton.setActionCommand("sair");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        String comando = e.getActionCommand();
        if(comando.equals("verOpçõesAutomóveis")){
            MenuAutomoveis menuAutomoveis = new MenuAutomoveis(usuario);
            menuAutomoveis.setVisible(true);
        } else if(comando.equals("verOpçõesPessoas")){
            MenuPessoas cadastroPessoa = new MenuPessoas(usuario);
            cadastroPessoa.setVisible(true);
        } else if(comando.equals("sair")){
            usuario = null;
            Login login = new Login();
            login.setVisible(true);
        }
    }
}
