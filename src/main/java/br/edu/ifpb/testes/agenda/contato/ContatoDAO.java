package br.edu.ifpb.testes.agenda.contato;

import br.edu.ifpb.testes.agenda.conexao.ConexaoException;
import br.edu.ifpb.testes.agenda.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContatoDAO {

    private Connection conexao;

    public ContatoDAO() {
        try {
            this.conexao = ConexaoFactory.getConnection();
        } catch (ConexaoException e) {
            e.printStackTrace();
        }
    }

    public ContatoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Contato c) throws SQLException {
        String query = "INSERT INTO agenda(nome, telefone) VALUES (?, ?)";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(query);
        preparedStatement.setString(1, c.getNome());
        preparedStatement.setString(2, c.getTelefone());
        preparedStatement.execute();
    }



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
