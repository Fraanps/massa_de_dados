package br.com.fps.estrategia2;

import com.github.javafaker.Faker;
import junit.framework.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ContasTesteWeb {
  private ChromeDriver driver;
  private Faker faker = new Faker();


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
  public void ct1_inserirConta(){
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Adicionar")).click();
    driver.findElement(By.id("nome")).sendKeys(faker.harryPotter().character());
    driver.findElement(By.tagName("button")).click();

    String textAlert = driver.findElement(By.cssSelector("div[role=alert]")).getText();
    Assert.assertEquals("Conta adicionada com sucesso!", textAlert);
  }

  @Test
  public void ct2_consultarConta(){

    String conta = inserirConta();
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Listar")).click();
    driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a")).click();
    String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
    Assert.assertEquals(conta, nomeConta);
  }

  @Test
  public void ct3_alterarConta(){
    String conta = inserirConta();
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Listar")).click();
    driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a")).click();

    driver.findElement(By.id("nome")).sendKeys(" Alterada");
    driver.findElement(By.tagName("button")).click();

    String textAlert = driver.findElement(By.cssSelector("div[role=alert]")).getText();
    Assert.assertEquals("Conta alterada com sucesso!", textAlert);
  }

  @Test
  public void ct4_deletarConta(){
    String conta = inserirConta();
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Listar")).click();
    driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a[2]")).click();

    String textAlert = driver.findElement(By.cssSelector("div[role=alert]")).getText();
    Assert.assertEquals("Conta removida com sucesso!", textAlert);
  }

  private String inserirConta(){
    String registro = faker.harryPotter().character();
    driver.findElement(By.className("dropdown-toggle")).click();
    driver.findElement(By.linkText("Adicionar")).click();
    driver.findElement(By.id("nome")).sendKeys(registro);
    driver.findElement(By.tagName("button")).click();
    return registro;
  }


  @AfterEach
  public void fecharBrowserTest(){
    driver.quit();
  }
}
