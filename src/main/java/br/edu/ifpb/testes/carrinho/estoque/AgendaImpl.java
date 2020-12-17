package br.edu.ifpb.testes.carrinho.estoque;

import br.edu.ifpb.testes.carrinho.contato.Contato;
import br.edu.ifpb.testes.carrinho.contato.ContatoDAO;
import br.edu.ifpb.testes.carrinho.contato.ContatoDAOImpl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AgendaImpl implements Agenda {

    private ContatoDAO contatoDAO;

    public AgendaImpl() {
        this.contatoDAO = new ContatoDAOImpl();
    }

    public AgendaImpl(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public boolean temItem(Contato contato) {
        try {
            return this.contatoDAO.listar().contains(contato);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Contato> getContatoBanco() {
        try {
            return this.contatoDAO.listar();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptyList();
        }
    }

}
