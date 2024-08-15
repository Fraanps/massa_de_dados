package br.com.fps.estrategia1;

import junit.framework.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContasTesteWeb {
  WebDriver driver;


  @BeforeEach
  public void login() {

    System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/drivers/chromedriver");
    driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://seubarriga.wcaquino.me/");
    driver.findElement(By.id("email")).sendKeys("fran@admin");
    driver.findElement(By.id("senha")).sendKeys("123456");
    driver.findElement(By.tagName("button")).click();
  }

  @Test
  public void ct1_inserirContaTest(){
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Adicionar")).click();
    driver.findElement(By.id("nome")).sendKeys("Conta estrategia #1");
    driver.findElement(By.tagName("button")).click();

    String textAlert = driver.findElement(By.cssSelector("div[role=alert]")).getText();
    Assert.assertEquals("Conta adicionada com sucesso!", textAlert);
  }

  @Test
  public void ct2_consultarContaTest(){
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Listar")).click();
    driver.findElement(By.xpath("//td[contains(text(), 'Conta estrategia #1')]/..//a")).click();
    String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
    Assert.assertEquals("Conta estrategia #1", nomeConta);
  }

  @Test
  public void ct3_alterarContaTest(){
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Listar")).click();
    driver.findElement(By.xpath("//td[contains(text(), 'Conta estrategia #1')]/..//a")).click();
    driver.findElement(By.id("nome")).clear();
    driver.findElement(By.id("nome")).sendKeys("Conta estrategia #1 alterada");
    driver.findElement(By.tagName("button")).click();

    String textAlert = driver.findElement(By.cssSelector("div[role=alert]")).getText();
    Assert.assertEquals("Conta alterada com sucesso!", textAlert);
  }

  @Test
  public void ct4_deletarContaTest(){
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Listar")).click();
    driver.findElement(By.xpath("//td[contains(text(), 'Conta estrategia #1 alterada')]/..//a[2]")).click();

    String textAlert = driver.findElement(By.cssSelector("div[role=alert]")).getText();
    Assert.assertEquals("Conta removida com sucesso!", textAlert);
  }


  @AfterEach
  public void fecharBrowserTest(){
    driver.quit();
  }
}
