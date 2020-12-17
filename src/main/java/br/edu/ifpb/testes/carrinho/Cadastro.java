package br.edu.ifpb.testes.carrinho;

import br.edu.ifpb.testes.carrinho.contato.Contato;
import br.edu.ifpb.testes.carrinho.contato.ContatoForadeBancoException;

public interface Cadastro {

    void adicionarItem(Contato contato) throws ContatoForadeBancoException;

    int getQtdeItens();

    void removerItem(Contato contato);

}
