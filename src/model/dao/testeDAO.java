package model.dao;

import model.entities.*;

public class testeDAO {
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAO();
//        pessoaDAO.insert(new Funcionario("Fununcionario", "33333333333", "54 98364-8866", Genero.FEMININO,32,1,"euSouLegal" ));
//        System.out.println(pessoaDAO.selectByCPF("9999999999"));
        pessoaDAO.remove("67");
    }
}
