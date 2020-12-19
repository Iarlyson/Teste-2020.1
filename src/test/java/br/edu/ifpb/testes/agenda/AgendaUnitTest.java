package br.edu.ifpb.testes.agenda;

import br.edu.ifpb.testes.agenda.contato.Contato;
import org.junit.Assert;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class AgendaUnitTest {

    Agenda agenda;

    @Before
    public void prepararCenario() {
        agenda = new Agenda();

    }

    @Test
    public void insertirContato() {
        // Realizar o estímulo ao sistema
        Contato novoContato = new Contato(1, "Bruno", "8354564884946");
        agenda.adicionarContato(novoContato);

        // Comparar a saída com o que eu espero (requisito)
        Assert.assertEquals(1, agenda.getQtdeItens());
    }

    @Test
    public void removerContato() {
        // Realizar o estímulo ao sistema
        Contato novoContato = new Contato(1, "Bruno", "8354564884946");
        agenda.adicionarContato(novoContato);

        Assert.assertTrue(agenda.getQtdeItens() == 1);
        agenda.removerItem(novoContato);
        Assert.assertTrue(agenda.getQtdeItens() == 0);
    }
}