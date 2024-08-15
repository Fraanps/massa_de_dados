package br.com.fps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class initSelenium {


  public static void main(String[] args) {
    // Defina o caminho completo para o executável do ChromeDriver
    System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/drivers/chromedriver");

    // Inicialize o driver
    WebDriver driver = new ChromeDriver();

    // por questão de segurança - esperar uns segundos até a aplicação abrir
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // Agora você pode usar o driver para interagir com o navegador
    driver.get("http://seubarriga.wcaquino.me/");

    // fazendo login
    driver.findElement(By.id("email")).sendKeys("fran@admin");
    driver.findElement(By.id("senha")).sendKeys("123456");
    driver.findElement(By.tagName("button")).click();

    // Feche o navegador
    driver.quit();
  }


  //fran@admin 123456
}
