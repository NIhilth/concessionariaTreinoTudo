package view;

import controller.PessoaController;
import model.entities.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements Runnable {
    private JPanel LoginPanel;
    private JTextField CPFInput;
    private JPasswordField senhaInput;
    private JButton loginButton;

    public Login(){
        criarComponentes();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CPFInput.getText().isEmpty() || senhaInput.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Um dos campos está vazio!");
                } else {
                    PessoaController pessoaController = new PessoaController();
                    try {
                        Pessoa pessoa = pessoaController.validaLogin(CPFInput.getText(), senhaInput.getText());
                        JOptionPane.showMessageDialog(null, "Bem vindo a Johnsons Car Dealership");
                        dispose();
                        Menu menu = new Menu(pessoa);
                        menu.setVisible(true);
                    } catch (RuntimeException exception){
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }
            }
        });
    }

    private void criarComponentes() {
        setContentPane(LoginPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    @Override
    public void run() {
        if (!isVisible()) {
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela já está aberta");
        }
    }

    public static void main(String[] args) {
        Login programa = new Login();
        programa.run();
    }
}
