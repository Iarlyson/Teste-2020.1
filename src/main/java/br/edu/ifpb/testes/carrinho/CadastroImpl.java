package br.edu.ifpb.testes.carrinho;

import br.edu.ifpb.testes.carrinho.contato.Contato;
import br.edu.ifpb.testes.carrinho.estoque.Agenda;
import br.edu.ifpb.testes.carrinho.estoque.AgendaImpl;
import br.edu.ifpb.testes.carrinho.contato.ContatoForadeBancoException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CadastroImpl implements Cadastro {

    private List<Contato> contatos = new LinkedList<Contato>();
    private Agenda agenda;

    public CadastroImpl() {
        this.agenda = new AgendaImpl();
    }

    public CadastroImpl(Agenda agenda) {
        this.agenda = agenda;
    }

    public void adicionarItem(Contato contato) throws ContatoForadeBancoException {
        if (!agenda.temItem(contato))
            throw new ContatoForadeBancoException("Contato fora da Agenda");
        this.contatos.add(contato);
        //if (produtos.size() >= 4) {
        //    Collections.sort(this.produtos);
       // }
    }

    public int getQtdeItens() {
        return this.contatos.size();
    }


    public void removerItem(Contato contato) {
        this.contatos.remove(contato);
    }


}
