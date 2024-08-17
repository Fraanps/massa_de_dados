package br.com.fps.estrategia2;

import br.com.fps.entidades.Conta;
import br.com.fps.entidades.Usuario;
import br.com.fps.service.ContaService;
import br.com.fps.service.UsuarioService;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class ContasServiceTest {

  static Faker faker = new Faker();
  ContaService contaService = new ContaService();
  UsuarioService userService = new UsuarioService();
  private static Usuario usuarioGlobal;
  private Conta contaTeste;

  @BeforeClass
  public static void criarUsuario(){
    usuarioGlobal = new Usuario(faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
  }


  @Test
  public void testInserir() throws Exception {
    Usuario user = new Usuario(faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
    Usuario usuarioSalvo = userService.salvar(user);
    Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
    Conta contaSalva = contaService.salvar(conta);
    Assert.assertNotNull(contaSalva);
  }

  @Test
  public void testAlterar() throws Exception {
    Conta conta = inserirConta();
    String novoNome = faker.gameOfThrones().character();
    conta.setNome(novoNome);
    Conta contaAlterada = contaService.salvar(conta);
    Assert.assertEquals(novoNome, contaAlterada.getNome());
  }

  @Test
  public void testConsultar() throws Exception {
    Conta conta = inserirConta();
    Conta contaBuscada = contaService.findById(conta.getId());

    Assert.assertEquals(conta.getNome(), contaBuscada.getNome());


  }

  @Test
  public void testExcluir() throws Exception {
    Conta conta = inserirConta();
    contaService.printAll();
    contaService.delete(conta);

    Conta contaBuscada = contaService.findById(conta.getId());
    Assert.assertNull(contaBuscada);



  }

  private Conta inserirConta() throws Exception {
    Usuario user = new Usuario(faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
    Usuario usuarioSalvo = userService.salvar(user);
    Conta conta = new Conta(faker.dragonBall().character(), usuarioSalvo);
    Conta contaSalva = contaService.salvar(conta);
    return contaSalva;
  } 
}
