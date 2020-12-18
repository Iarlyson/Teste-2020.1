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

public class AgendaIntegrationTest {

    private JdbcDatabaseContainer container;
    private IDatabaseTester databaseTester;
    private Agenda agenda;
    private Connection conexao;

    @Before
    public void setUp() throws Exception {
        if(container == null) {
            PostgreSQLContainer postgreSQLContainer;
            postgreSQLContainer = new PostgreSQLContainer().withUsername("postgresql").withPassword("123456").withDatabaseName("exemplo-testes");
            container = postgreSQLContainer.withInitScript("postgres/create_schema.sql");
            container.start();
            conexao = DriverManager.getConnection(container.getJdbcUrl(), "postgresql", "123456");
        }
        configurarDBUnit();
        this.databaseTester.onSetup();
        ContatoDAO contatoDAO = new ContatoDAO(conexao);
        // tirar agenda e botar em cadastro.
        agenda = new Agenda(contatoDAO);
        //agenda = new Agenda(agenda2);
    }

    private void configurarDBUnit() throws ClassNotFoundException, FileNotFoundException, DataSetException {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(("src/test/resources/agenda.xml")));
        this.databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                container.getJdbcUrl(), "postgresql", "123456");
        this.databaseTester.setDataSet(dataSet);
        this.databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        this.databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
    }

    /*Verificar se já está no banco, se estiver ele retonar o que está no banco*/
    @Test
    public void consultarContatoDisponivel() throws ContatoForadeAgendaException {
        Contato contato = new Contato(5,"Iarlyson","8399445858");

        Assert.assertEquals(true, this.agenda.temContato(contato));
    }

    @Test(expected = ContatoForadeAgendaException.class)
    public void consultarQuandoContatoIndisponivel() throws ContatoForadeAgendaException {
        Contato contatoIndisponivel = new Contato(5,"Iarlyson","84999554555");
        this.agenda.temContato(contatoIndisponivel);
    }


    @After
    public void finalizar() throws Exception {
        databaseTester.onTearDown();
        conexao.close();
    }


}
