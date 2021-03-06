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

    public void adicionarContato(Contato contato){
        if(!this.contatos.contains(contato)){
            contatos.add(contato);
        }
    }


    public boolean atualizarContatoDao(Contato contato) throws SQLException {
        for (int i = 0; i < this.contatoDAO.listar().size(); i++) {
            if (this.contatoDAO.listar().get(i).getNome().equals(contato.getNome())){
                contatoDAO.atualizar(contato);
                return true;
            }
        }
        return false;
    }


    public boolean atualizarContato(Contato contato) {

          for (int i = 0; i < this.contatos.size(); i++) {
              if (contatos.get(i).getNome().equals(contato.getNome())) {
                  contatos.set(i, contato);
                  return true;
              }
          }
          return false;
    }

    public boolean removerContatoDao(Contato contato) throws SQLException {
        for (int i = 0; i < this.contatoDAO.listar().size(); i++) {
            if (this.contatoDAO.listar().get(i).getNome().equals(contato.getNome())) {
                return contatoDAO.excluir(contato);
            }
        }
        return false;
    }

    public void removerContato(Contato contato) {
        contatos.remove(contato);
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

    public boolean temContato(Contato contato) {
        return this.contatos.contains(contato);
    }


    public int getQtdeNumeroContatosDao() throws SQLException {
        for (int i = 0; i < this.contatoDAO.listar().size(); i++) {
            System.out.println( this.contatoDAO.listar().get(i).getNome());
        }
            return this.contatoDAO.listar().size();
    }


    public int getQtdeNumeroContatos() {
        return this.contatos.size();
    }

    public void ordenaContatos(){
        Collections.sort(this.contatos);
    }

    public List<Contato> getContatoBanco() {
        try {
            return this.contatoDAO.listar();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "contatos=" + contatos +
                '}';
    }
}
