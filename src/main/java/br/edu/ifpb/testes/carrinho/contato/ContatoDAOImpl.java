package br.edu.ifpb.testes.carrinho.contato;

import br.edu.ifpb.testes.carrinho.conexao.ConexaoException;
import br.edu.ifpb.testes.carrinho.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContatoDAOImpl implements ContatoDAO {

    private Connection conexao;

    public ContatoDAOImpl() {
        try {
            this.conexao = ConexaoFactory.getConnection();
        } catch (ConexaoException e) {
            e.printStackTrace();
        }
    }

    public ContatoDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void salvar(Contato c) throws SQLException {
        String query = "INSERT INTO agenda(nome, telefone) VALUES (?, ?)";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(query);
        preparedStatement.setString(1, c.getNome());
        preparedStatement.setString(2, c.getTelefone());
        preparedStatement.execute();
    }



    @Override
    public List<Contato> listar() throws SQLException {
        String query = "SELECT * FROM agenda";
        List<Contato> contatos = new LinkedList<>();
        PreparedStatement preparedStatement = conexao.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Contato contato = new Contato(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            contatos.add(contato);
        }
        return contatos;
    }

}
