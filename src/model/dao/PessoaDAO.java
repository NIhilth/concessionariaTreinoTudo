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

    public ArrayList<Pessoa> selectAll(){
        ArrayList<Pessoa> pessoasCollection = new ArrayList<>();
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

    public void update(String antigoCpf,Pessoa pessoa){
        String sql = "update pessoa set nome = ? , telefone = ? , genero = ? , idade = ? , cpf = ? where cpf = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getTelefone());
            statement.setString(3, pessoa.getGenero().getNome());
            statement.setInt(4, pessoa.getIdade());
            statement.setString(5, pessoa.getCpf());
            statement.setString(6, antigoCpf);
            try{
                if(pessoa instanceof Funcionario){
                    updateFuncionario(antigoCpf, (Funcionario) pessoa);
                }
                statement.execute();
                return;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Edição da pessoa deu errado");
    }

    private void updateFuncionario(String antigoCpf, Funcionario pessoa) {
        String sql = "update funcionario set salario = ?,senha = ? where PESSOA_cpf = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1, pessoa.getSalario());
            statement.setString(2, pessoa.getSenha());
            statement.setString(3, antigoCpf);
            try{
                statement.execute();
                return;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        throw new RuntimeException("Edição do funcionário deu errado");
    }

    private void insertFuncionario(Funcionario funcionario) {
        String sql = "insert into funcionario values (?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1, funcionario.getSalario());
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
        Integer maiorMatricula = 0;
        for(Pessoa pessoa : pessoas){
            Integer tipoDaPessoa = pegarTipo(pessoa);
            if(tipoDaPessoa == tipoDaMatricula){
                if(pessoa.getMatricula() > maiorMatricula){
                    maiorMatricula = pessoa.getMatricula();
                }
            }
        }
        return maiorMatricula + 1;
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
