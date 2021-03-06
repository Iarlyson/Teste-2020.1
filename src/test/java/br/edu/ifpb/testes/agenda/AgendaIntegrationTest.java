package br.edu.ifpb.testes.agenda;

import br.edu.ifpb.testes.agenda.contato.Contato;
import br.edu.ifpb.testes.agenda.contato.ContatoDAO;
import br.edu.ifpb.testes.agenda.contato.ContatoForadeAgendaException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AgendaIntegrationTest {

    private JdbcDatabaseContainer container;
    private IDatabaseTester databaseTester;
    private Agenda agenda;
    private Connection conexao;

    @Before
    public void setUp() throws Exception {
        if(container == null) {
            PostgreSQLContainer postgreSQLContainer;
            postgreSQLContainer = new PostgreSQLContainer().withUsername("postgresql").withPassword("123456").withDatabaseName("agenda");
            container = postgreSQLContainer.withInitScript("postgres/create_schema.sql");
            container.start();
            conexao = DriverManager.getConnection(container.getJdbcUrl(), "postgresql", "123456");
        }
        configurarDBUnit();
        this.databaseTester.onSetup();
        ContatoDAO contatoDAO = new ContatoDAO(conexao);
        agenda = new Agenda(contatoDAO);
    }

    private void configurarDBUnit() throws ClassNotFoundException, FileNotFoundException, DataSetException {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(("src/test/resources/agenda.xml")));
        this.databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                container.getJdbcUrl(), "postgresql", "123456");
        this.databaseTester.setDataSet(dataSet);
        this.databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        this.databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
    }

    @Test
    public void adcionarContatonaAgenda() throws SQLException{
        // Criando o objeto e adicionado na lista
        Contato contatoADD = new Contato(3,"GEAN", "83996167660");
        agenda.adicionarContatoDao(contatoADD);

        // comparando com o resultado que espero
        Assert.assertEquals(3, agenda.getQtdeNumeroContatosDao());
    }

    /*Verificar pelo nome se já está no banco, se estiver ele retonar  que está no banco*/
    @Test
    public void consultarContatoDisponivel() throws ContatoForadeAgendaException {
        // Criando o objeto
        Contato contato = new Contato(1,"Iarlyson","8399445858");
        // comparando com o resultado que espero
        Assert.assertEquals(true, this.agenda.temNaAgenda(contato));
    }

    /*Verificar pelo nome se já está no banco, se não estiver ele retonar uma exceção*/
    @Test(expected = ContatoForadeAgendaException.class)
    public void consultarQuandoContatoIndisponivel() throws ContatoForadeAgendaException {
        // Criando o objeto
        Contato contatoIndisponivel = new Contato(1,"Iarlysons","84999554555");
        this.agenda.temNaAgenda(contatoIndisponivel);
    }

    @Test
    public void atualizarContatodeAgenda() throws SQLException {
        // Criando o objeto
        Contato contatoRemover = new Contato(1,"Iarlyson", "83999445857");
        // Atualizando a agenda e comparando com o resultado que espero
        Assert.assertEquals(true, this.agenda.atualizarContatoDao(contatoRemover));
    }

    @Test
    public void removerContatodeAgenda() throws SQLException {
        // Criando o objeto
        Contato contatoRemover = new Contato(1,"Iarlyson", "83999445858");
        // Removendo na lista e comparando com o resultado que espero
        Assert.assertEquals(true, this.agenda.removerContatoDao(contatoRemover));
    }

    @After
    public void finalizar() throws Exception {
        databaseTester.onTearDown();
        conexao.close();
    }


}// fim do teste
