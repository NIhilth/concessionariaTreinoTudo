package view;

import model.entities.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAutomoveis extends JFrame  implements ActionListener {

    private Pessoa usuario;
    private JButton cadastrarButton;
    private JButton venderButton;
    private JButton listarEstoqueButton;
    private JButton editarButton;
    private JButton removerButton;
    private JButton listarVendasButton;
    private JButton voltarButton;
    private JPanel MenuAutomoveis;

    public MenuAutomoveis(Pessoa usuario){
        this.usuario = usuario;
        criarComponentes();
    }

    private void criarComponentes() {
        setContentPane(MenuAutomoveis);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        cadastrarButton.addActionListener(this);
        cadastrarButton.setActionCommand("cadastrarAutomóvel");
        venderButton.addActionListener(this);
        venderButton.setActionCommand("venderAutomóvel");
        listarEstoqueButton.addActionListener(this);
        listarEstoqueButton.setActionCommand("listarAutomóveis");
        editarButton.addActionListener(this);
        editarButton.setActionCommand("editarAutomóvel");
        removerButton.addActionListener(this);
        removerButton.setActionCommand("removerAutomóvel");
        listarVendasButton.addActionListener(this);
        listarVendasButton.setActionCommand("listarVendidos");
        voltarButton.addActionListener(this);
        voltarButton.setActionCommand("sair");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if(comando.equals("cadastrarAutomóvel")){
            /*modal pra ele escolher se é moto ou automóvel
            que daí que leva ele pra tela de cadastrar  um automóvel*/
        } else if(comando.equals("venderAutomóvel")){
            /*modal pra ele informar a placa do automóvel e o cpf
            do cliente que comprou ele*/
        } else if(comando.equals("listarAutomóveis")){
            /*tela dos automóveis pra estoque*/
        } else if(comando.equals("editarAutomóvel")){
            /*modal pra ele informar a aplaca do automóvel
            que daí que leva ele pra tela de editar um automóvel
            que pode ser uma enjanbração da tela de cadastrar*/
        } else if(comando.equals("removerAutomóvel")){
            /*modal pra ele informar a placa do automóvel*/
        } else if(comando.equals("listarVendidos")){
            /*tela dos automóveis que foram vendidos*/
        } else if(comando.equals("sair")){
            Menu menu = new Menu(usuario);
            usuario = null;
            dispose();
            menu.setVisible(true);
        }
    }
}
