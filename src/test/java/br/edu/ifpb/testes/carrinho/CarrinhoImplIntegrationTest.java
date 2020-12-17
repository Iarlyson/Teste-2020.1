package br.edu.ifpb.testes.carrinho;

import br.edu.ifpb.testes.carrinho.contato.Contato;
import br.edu.ifpb.testes.carrinho.contato.ContatoDAO;
import br.edu.ifpb.testes.carrinho.estoque.Agenda;
import br.edu.ifpb.testes.carrinho.estoque.AgendaImpl;
import br.edu.ifpb.testes.carrinho.contato.ContatoDAOImpl;
import br.edu.ifpb.testes.carrinho.contato.ContatoForadeBancoException;
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

public class CarrinhoImplIntegrationTest {

    private JdbcDatabaseContainer container;
    private IDatabaseTester databaseTester;
    private Cadastro cadastro;
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
        ContatoDAO contatoDAO = new ContatoDAOImpl(conexao);
        Agenda agenda = new AgendaImpl(contatoDAO);
        cadastro = new CadastroImpl(agenda);
    }

    private void configurarDBUnit() throws ClassNotFoundException, FileNotFoundException, DataSetException {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(("src/test/resources/produtos_testset.xml")));
        this.databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                container.getJdbcUrl(), "postgresql", "123456");
        this.databaseTester.setDataSet(dataSet);
        this.databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        this.databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
    }

    @Test
    public void consultarContatoDisponivel() throws ContatoForadeBancoException {
        Contato contatoDisponivel = new Contato(1,
                "Iarlyson","83999445858");
        this.cadastro.adicionarItem(contatoDisponivel);
        Assert.assertEquals(1, this.cadastro.getQtdeItens());
    }

    @Test(expected = ContatoForadeBancoException.class)
    public void consultarQuandoContatoIndisponivel() throws ContatoForadeBancoException {
        Contato contatoIndisponivel = new Contato(5,
                "Lucas","84999554555");
        this.cadastro.adicionarItem(contatoIndisponivel);
    }

    @After
    public void finalizar() throws Exception {
        databaseTester.onTearDown();
        conexao.close();
    }


}
