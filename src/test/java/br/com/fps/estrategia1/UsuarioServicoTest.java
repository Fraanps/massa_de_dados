package br.com.fps.estrategia1;


import br.com.fps.entidades.Usuario;
import br.com.fps.service.UsuarioService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioServicoTest {

  private UsuarioService service = new UsuarioService();
  private static Usuario usuarioGlobal;

  @Test
  public void ct1_inserirUsuario() throws Exception {
    Usuario usuario = new Usuario("Usuario estrategia #1", "user1@email.com", "passwd");
    usuarioGlobal = service.salvar(usuario);
    Assert.assertNotNull(usuarioGlobal.getId());
  }

  @Test
  public void ct2_consultarUsuario() throws Exception {
    Usuario usuario = service.findById(usuarioGlobal.getId());
    Assert.assertEquals("Usuario estrategia #1", usuario.getNome());

  }

  @Test
  public void ct3_alterarUsuario() throws Exception {
    Usuario usuario = service.findById(usuarioGlobal.getId());
    usuario.setNome("Usuario estrategia # Alterado");
    Usuario usuarioAlterado = service.salvar(usuario);

    Assert.assertEquals("Usuario estrategia # Alterado", usuarioAlterado.getNome());
  }

  @Test
  public void ct4_deletarUsuario() throws Exception {
    service.delete(usuarioGlobal);
    Usuario usuarioRemovido = service.findById(usuarioGlobal.getId());
    Assert.assertNull(usuarioRemovido);
  }

}
