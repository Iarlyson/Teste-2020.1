package br.edu.ifpb.testes.carrinho.contato;

import java.sql.SQLException;
import java.util.List;

public interface ContatoDAO {
    
    void salvar(Contato c) throws SQLException;
    List<Contato> listar() throws SQLException;
    
}
