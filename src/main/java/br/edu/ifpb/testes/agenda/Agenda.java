package br.edu.ifpb.testes.agenda;

import br.edu.ifpb.testes.agenda.contato.Contato;
import br.edu.ifpb.testes.agenda.contato.ContatoDAO;
import br.edu.ifpb.testes.agenda.contato.ContatoForadeAgendaException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {

    private ContatoDAO contatoDAO;
    private List<Contato> contatos = new ArrayList<Contato>();

    public Agenda() {
        this.contatoDAO = new ContatoDAO();
    }

    public Agenda(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }


    public boolean adicionarContatoDao(Contato contato) throws SQLException {
        return contatoDAO.salvar(contato);
    }

    public boolean adicionarContato(Contato contato){
        return contatos.add(contato);
    }
    public boolean temNaAgenda(Contato contato) throws ContatoForadeAgendaException {
        try {
            for (int i = 0; i < this.contatoDAO.listar().size(); i++) {
                if (this.contatoDAO.listar().get(i).getNome().equals(contato.getNome())){
                    System.out.println(contato.getNome());
                    return true;
                }
            }
            throw new ContatoForadeAgendaException("Contato fora da agenda");
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return false;
    }


    public List<Contato> getContatoBanco() {
        try {
            return this.contatoDAO.listar();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean  temContato(Contato contato) throws ContatoForadeAgendaException {
        return temNaAgenda(contato);
    }

    public int getQtdeItensDao() throws SQLException {
        return this.contatoDAO.listar().size();
    }

    public int getQtdeItens() {
        return this.contatos.size();
    }

    public boolean removerItem(Contato contato) {
        return contatos.remove(contato);
    }

}
