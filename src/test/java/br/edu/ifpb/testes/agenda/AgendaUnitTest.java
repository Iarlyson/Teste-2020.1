package br.edu.ifpb.testes.agenda;

import br.edu.ifpb.testes.agenda.contato.Contato;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgendaUnitTest {

    Agenda agenda;

    @Before
    public void prepararCenario() {
        //Criando a agenda para os testes
        agenda = new Agenda();
    }

    @Test
    public void insertirContato() {
        // Criando o objeto e adicionado na lista
        Contato novoContato = new Contato(1, "Bruno", "8354564884946");
        agenda.adicionarContato(novoContato);

        // comparando com o resultado que espero
        Assert.assertEquals(1, agenda.getQtdeNumeroContatos());
    }


    @Test
    public void atualizarrContato() {
        // Criando o objeto e adicionado na lista
        Contato novoContato = new Contato(1, "Iarlyson", "83999445858");
        agenda.adicionarContato(novoContato);
        // comparando com o resultado que espero
        Assert.assertEquals(1, agenda.getQtdeNumeroContatos());

        // Atualizando o objeto e adicionado na lista
        Contato updateContato = new Contato(1, "Iarlyson", "83545648849");

        // comparando com o resultado que espero
        Assert.assertEquals(true, agenda.atualizarContato(updateContato));
    }

    @Test
    public void removerContato() {
        // Criando o objeto e adicionado na lista
        Contato novoContato = new Contato(1, "Bruno", "8354564884946");
        agenda.adicionarContato(novoContato);

        // comparando com o resultado que espero
        Assert.assertTrue(agenda.getQtdeNumeroContatos() == 1);
        agenda.removerContato(novoContato);
        Assert.assertTrue(agenda.getQtdeNumeroContatos() == 0);
    }

    @Test
    public void testaInserirContatoRepetido(){
        // Testando criar dois contatos com o mesmo nome e inseri-los na agenda
        // Lembrando que dois contatos se diferenciam pelo nome

        String nome1 = "Ronaldo";
        String nome2 = "Adriano";
        String nome3 = "Ronaldo";

        Contato contato1 = new Contato(nome1, "1111");
        Contato contato2 = new Contato(nome2, "1111");
        Contato contato3 = new Contato(nome3, "1111");

        // verificando se a agenda está vazia

        Assert.assertTrue(agenda.getQtdeNumeroContatos() == 0);

        // adicionando os dois primeiros contatos à agenda

        agenda.adicionarContato(contato1);
        agenda.adicionarContato(contato2);

        // verificando se a agenda possui dois contatos

        Assert.assertTrue(agenda.getQtdeNumeroContatos() == 2);

        Assert.assertTrue(agenda.temContato(contato1));
        Assert.assertTrue(agenda.temContato(contato2));

        // assegurando que a agenda contém o contato3 mesmo ele nao sendo inserido
        // já que ele é igual ao contato 1 pelo mesmo nome
        Assert.assertTrue(agenda.temContato(contato3));

        // confirmando que a lista só terá 2 contatos mesmo tentando inserir o terceiro
        agenda.adicionarContato(contato3);
        Assert.assertTrue(agenda.getQtdeNumeroContatos() == 2);
    }

    @Test
    public void ordemAlfabetica(){
        // Criando vários contatos de nomes diderentes
        Contato cont1 = new Contato(1,"Alana", "11111111");
        Contato cont2 = new Contato(2,"Bianca", "222222222");
        Contato cont3 = new Contato(3,"Carla", "33333333");

        // adicionando os tres contatos à agenda

        agenda.adicionarContato(cont3);
        agenda.adicionarContato(cont2);
        agenda.adicionarContato(cont1);

        // Assegurando que nao estao em ordem alfabetica

        Assert.assertTrue(agenda.toString().equals(
                "Agenda{contatos=[Contato{id=3, nome='Carla', telefone='33333333'}, Contato{" +
            "id=2, nome='Bianca', telefone='222222222'}, " +
            "Contato{id=1, nome='Alana', telefone='11111111'}]}"));

        // Ordenando a agenda

        agenda.ordenaContatos();

        Assert.assertEquals(agenda.toString(),
                "Agenda{contatos=[Contato{id=1, nome='Alana', telefone='11111111'}, " +
                        "Contato{id=2, nome='Bianca', telefone='222222222'}, " +
                        "Contato{id=3, nome='Carla', telefone='33333333'}]}");

    }

}// fim dos testes