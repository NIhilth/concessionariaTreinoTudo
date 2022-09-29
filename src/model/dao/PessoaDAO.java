package model.dao;

import model.entities.*;
import model.factory.ConexaoFactory;
import model.factory.PessoaFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PessoaDAO {
    private static Collection<Pessoa> listaPessoas = new ArrayList<>();
    private Connection connection;

    public PessoaDAO(){
        this.connection = new ConexaoFactory().conectaBD();
    }

    public Collection<Pessoa> selectAll(){
        Collection<Pessoa> pessoasCollection = new ArrayList<>();
        String sql = "SELECT * from pessoa ";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet != null && resultSet.next()) {
                    String senha = null;

                    Pessoa pessoa = new PessoaFactory().getPessoa(
                            resultSet.getInt("tipo"),
                            resultSet.getString("nome"),
                            resultSet.getString("cpf"),
                            resultSet.getString("telefone"),
                            Genero.valueOf(resultSet.getString("genero")),
                            resultSet.getInt("idade"),
                            resultSet.getInt("matricula"),
                            senha
                    );
                    pessoasCollection.add(pessoa);
                }
            } catch (SQLException excecao) {
                excecao.printStackTrace();
            }
        } catch (SQLException excecao) {
            excecao.printStackTrace();
        }

        return pessoasCollection;
    }

    public Pessoa selectByCPF(String cpf) {
        String sql = "select * from pessoa where cpf = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cpf);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    Pessoa pessoa = new PessoaFactory().getPessoa(
                            resultSet.getInt("tipo"),
                            resultSet.getString("nome"),
                            resultSet.getString("cpf"),
                            resultSet.getString("telefone"),
                            Genero.valueOf(resultSet.getString("genero")),
                            resultSet.getInt("idade"),
                            resultSet.getInt("matricula"),
                            ""
                    );

                    if(pegarTipo(pessoa) != 1){
                        String[] infoFuncionario = getInfoFuncionario(pessoa.getCpf());
                        ((Funcionario) pessoa).setSenha(infoFuncionario[0]);
                        ((Funcionario) pessoa).setSalario(Double.parseDouble(infoFuncionario[1]));
                    }
                    return pessoa;
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Cpf da pessoa inválido");
    }

    public void insert(Pessoa novaPessoa){
        String sql = "insert into pessoa values  (?,?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            Integer tipo = pegarTipo(novaPessoa);
            Integer matricula = pegarMatricula(tipo);

            statement.setString(1, novaPessoa.getCpf());
            statement.setString(2, novaPessoa.getNome());
            statement.setString(3, novaPessoa.getTelefone());
            statement.setString(4, novaPessoa.getGenero().getNome());
            statement.setInt(5, novaPessoa.getIdade());
            statement.setInt(6, tipo);
            statement.setInt(7, matricula);
            try{
                statement.execute();
                if(tipo != 1){
                    insertFuncionario((Funcionario) novaPessoa);
                } else {
                    Pessoa pessoaCadastrada = selectByCPF(novaPessoa.getCpf());
                    listaPessoas.add(pessoaCadastrada);
                    return;
                }
            } catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void remove(String cpf) {
        String sql = "delete from pessoa where cpf = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cpf);
            try{
                Pessoa pessoa = selectByCPF(cpf);
                if(pessoa instanceof Funcionario){
                    removeFuncionario(cpf);
                }
                statement.execute();
                return;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Cpf da pessoa inválido");
    }

    private void insertFuncionario(Funcionario funcionario) {
        String sql = "insert into funcionario values (?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            Integer tipo = pegarTipo(funcionario);
            Double salario;
            if(tipo == 2){
                salario = 1250.0;
            } else {
                salario = 30000.0;
            }

            statement.setDouble(1, salario);
            statement.setString(2,  funcionario.getSenha());
            statement.setString(3, funcionario.getCpf());
            try{
                statement.execute();
                funcionario.setSenha(funcionario.getSenha());
                listaPessoas.add(funcionario);
                return;
            } catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private String[] getInfoFuncionario(String cpf){
        String sql = "select * from funcionario where PESSOA_cpf = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cpf);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new String[]{resultSet.getString("senha"), String.valueOf(resultSet.getDouble("salario"))};
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Cpf do funcionário inválido");
    }

    private void removeFuncionario(String cpf) {
        String sql = "delete from funcionario where PESSOA_cpf = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cpf);
            try{
                statement.execute();
                return;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Cpf da pessoa inválido");
    }

    private Integer pegarMatricula(Integer tipoDaMatricula) {
        Collection<Pessoa> pessoas = selectAll();
        Integer matricula = 1;
        for(Pessoa pessoa : pessoas){
            Integer tipoDaPessoa = pegarTipo(pessoa);
            if(tipoDaPessoa == tipoDaMatricula){
                matricula++;
            }
        }
        return matricula;
    }

    private Integer pegarTipo(Pessoa pessoa){
        if(pessoa instanceof Dono){
            return  3;
        } else if (pessoa instanceof Funcionario){
            return 2;
        } else {
            return 1;
        }
    }

}
