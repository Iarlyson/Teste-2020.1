package br.edu.ifpb.testes.carrinho.estoque;

import br.edu.ifpb.testes.carrinho.contato.Contato;

import java.util.List;

public interface Agenda {

    boolean temItem(Contato contato);
    List<Contato> getContatoBanco();

}
