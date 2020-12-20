package br.edu.ifpb.testes.contato;

import br.edu.ifpb.testes.agenda.contato.Contato;
import junit.framework.TestCase;
import org.junit.Test;

public class ContatoTest extends TestCase {

    @Test
    public void testaConstrutor(){
        //Testa construtor vazio

        Contato cont1 = new Contato();
        assertEquals(0, cont1.getId());
        assertEquals(null, cont1.getNome());
        assertEquals(null, cont1.getTelefone());

        // Testa construtor com atributos

        Contato cont2 = new Contato(25, "Chico", "35781284");
        assertEquals(25, cont2.getId());
        assertEquals("Chico", cont2.getNome());
        assertEquals("35781284", cont2.getTelefone());

    }

    @Test
    public void testaEquals(){
        // Testando se são diferentes tendo o nome como chave de diferenciação

        Contato cont1 = new Contato(1,"Pedro", "11111111");
        Contato cont2 = new Contato(2,"Francisco", "22222222");

        assertFalse(cont1.equals(cont2));

        // Testando objetos de nomes iguais

        Contato cont3 = new Contato(3,"Maria", "33333333");
        Contato cont4 = new Contato(4,"Maria", "44444444");

        assertTrue(cont3.equals(cont4));
    }


}// fim do teste