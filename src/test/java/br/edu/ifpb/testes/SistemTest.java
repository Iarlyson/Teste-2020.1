package br.edu.ifpb.testes;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SistemTest {

    private WebDriver navegador;

    @Before
    public void prepararCenario() {
        System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().deleteAllCookies();
    }
    
    @Test
    public void testFazerLoginErrado() {
        navegador.get("https://iarlyson.github.io/SiteTesteSystem/html/login.html");
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.findElement(By.id("emailLogin")).sendKeys("iarlyson.santana@gmail.com");
        navegador.findElement(By.id("senhaLogin")).sendKeys("123456");
        navegador.findElement(By.id("butãoLogin")).click();
        ;
        try {
            WebDriverWait wait = new WebDriverWait(navegador, 5);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(3000);
            String mensagem = navegador.switchTo().alert().getText();
            Thread.sleep(3000);
            // Aceitando alerta
            //alert.accept();
            Assert.assertEquals("Falha ao logar. O email não existe ou a senha foi digitada incorretamente!", mensagem);
            navegador.quit();
        } catch (Exception e) {
        }
    }
    @Test
    public void testFazerLoginCerto(){
            navegador.get("https://iarlyson.github.io/SiteTesteSystem/html/login.html");
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            navegador.findElement(By.id("emailLogin")).sendKeys("usuarioteste@gmail.com");
            navegador.findElement(By.id("senhaLogin")).sendKeys("123456");
            navegador.findElement(By.id("butãoLogin")).click();;
            try {
                WebDriverWait wait = new WebDriverWait(navegador, 5);
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                Thread.sleep(3000);
                String mensagem = navegador.switchTo().alert().getText();
                Thread.sleep (3000);
                // Aceitando alerta
                //alert.accept();
                Assert.assertEquals("Logado com sucesso", mensagem);
                navegador.quit();
            } catch (Exception e) {
            }
    }
    @Test
    public void testCadastroFaltandoDados() {
        navegador.get("https://iarlyson.github.io/SiteTesteSystem/html/cadastro.html");
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.findElement(By.id("emailLogin")).sendKeys("");
        navegador.findElement(By.id("senhaLogin")).sendKeys("");
        navegador.findElement(By.id("senhaconfirmar")).sendKeys("");

        navegador.findElement(By.id("cadastrar")).click();
        ;
        try {
            WebDriverWait wait = new WebDriverWait(navegador, 5);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(3000);
            String mensagem = navegador.switchTo().alert().getText();
            Thread.sleep(3000);
            // Aceitando alerta
            //alert.accept();
            Assert.assertEquals("Falha ao cadastrar, faltam dados a serem preenchidos!", mensagem);
            navegador.quit();
        } catch (Exception e) {
        }
    }

    @Test
    public void testCadastroSenhaIncompativeis() {
        navegador.get("https://iarlyson.github.io/SiteTesteSystem/html/cadastro.html");
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.findElement(By.id("emailLogin")).sendKeys("iarlyson.santana@gmail.com");
        navegador.findElement(By.id("senhaLogin")).sendKeys("2");
        navegador.findElement(By.id("senhaconfirmar")).sendKeys("");

        navegador.findElement(By.id("cadastrar")).click();
        ;
        try {
            WebDriverWait wait = new WebDriverWait(navegador, 5);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Thread.sleep(3000);
            String mensagem = navegador.switchTo().alert().getText();
            Thread.sleep(3000);
            // Aceitando alerta
            //alert.accept();
            Assert.assertEquals("Senhas incompatíveis!", mensagem);
            navegador.quit();
        } catch (Exception e) {
        }
    }

}
