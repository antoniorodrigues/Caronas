package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sistema.Sistema;



public class TestaCarona {

	private Sistema s1, s2;

	@Before
	public void setUp() throws Exception {
		s1 = new Sistema();
		s1.criarUsuario("login1", "senha1", "nome1", "endereco1", "email1");
		s1.criarUsuario("login2", "senha2", "nome2", "endereco2", "email2");
		s1.criarUsuario("login3", "senha3", "nome3", "endereco3", "email3");

		s2 = new Sistema();
		s2.criarUsuario("login1", "senha1", "nome1", "endereco1", "email1");
	}

	@Test
	public void testAbrirEncerrarSessao() throws Exception {

		s1.abrirSessao("login1", "senha1");
		assertEquals(s1.getAtributoUsuario("login1", "nome"), "nome1");
		assertEquals(s1.getAtributoUsuario("login1", "endereco"), "endereco1");
		s1.encerrarSessao("login1");

		s1.abrirSessao("login2", "senha2");
		assertEquals(s1.getAtributoUsuario("login2", "nome"), "nome2");
		assertEquals(s1.getAtributoUsuario("login2", "endereco"), "endereco2");
		s1.encerrarSessao("login2");

		s1.abrirSessao("login3", "senha3");
		assertEquals(s1.getAtributoUsuario("login3", "nome"), "nome3");
		assertEquals(s1.getAtributoUsuario("login3", "endereco"), "endereco3");
		s1.encerrarSessao("login3");

		s2.abrirSessao("login1", "senha1");
		assertEquals(s2.getAtributoUsuario("login1", "nome"), "nome1");
		assertEquals(s2.getAtributoUsuario("login1", "endereco"), "endereco1");
		s2.encerrarSessao("login1");
	}

	@Test
	public void testCadastraCarona() throws Exception {

		String idUsuario = s2.abrirSessao("login1", "senha1");
		// Localiza carona nao cadastrada
		assertEquals(s2.localizarCarona(idUsuario, "origem1", "destino1"), "{"
				+ "}");
		assertEquals(s2.localizarCarona(idUsuario, "origem2", "destino2"), "{"
				+ "}");

		// Cadastra uma carona, para usuario 1. Localiza esta carona e confirma
		// os atributos desta carona
		String idCarona1 = s2.cadastrarCarona(idUsuario, "origem1", "destino1",
				"11/1/2013", "11:11", "1");
		assertEquals(s2.localizarCarona(idUsuario, "origem1", "destino1"), "{"
				+ idCarona1 + "}");
		assertEquals(s2.getAtributoCarona(idCarona1, "origem"), "origem1");
		assertEquals(s2.getAtributoCarona(idCarona1, "destino"), "destino1");

	}

	@Test
	public void testLocalizarCarona() throws Exception {

		String idUsuario = s2.abrirSessao("login1", "senha1");
		// Localiza carona nao cadastrada
		assertEquals(s2.localizarCarona(idUsuario, "origem1", "destino1"), "{"
				+ "}");
		assertEquals(s2.localizarCarona(idUsuario, "origem2", "destino2"), "{"
				+ "}");

		// Cadastra uma carona, para usuario 1. Localiza esta carona e confirma
		// os atributos desta carona
		String idCarona1 = s2.cadastrarCarona(idUsuario, "origem1", "destino1",
				"11/1/2013", "11:11", "1");
		assertEquals(s2.localizarCarona(idUsuario, "origem1", "destino1"), "{"
				+ idCarona1 + "}");
		assertEquals(s2.getAtributoCarona(idCarona1, "origem"), "origem1");
		assertEquals(s2.getAtributoCarona(idCarona1, "destino"), "destino1");
	}

	@Test
	public void testGetTrajeto() throws Exception {

		String idUsuario1 = s1.abrirSessao("login1", "senha1");
		String idCarona1 = s1.cadastrarCarona(idUsuario1, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s1.getTrajeto(idCarona1), "origem1 - destino1");

		String idUsuario2 = s1.abrirSessao("login1", "senha1");
		String idCarona2 = s1.cadastrarCarona(idUsuario2, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s1.getTrajeto(idCarona2), "origem1 - destino1");

		String idUsuario3 = s1.abrirSessao("login1", "senha1");
		String idCarona3 = s1.cadastrarCarona(idUsuario3, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s1.getTrajeto(idCarona3), "origem1 - destino1");

		String idUsuario4 = s2.abrirSessao("login1", "senha1");
		String idCarona4 = s2.cadastrarCarona(idUsuario4, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s2.getTrajeto(idCarona4), "origem1 - destino1");
	}

	@Test
	public void testGetAtributoCarona() throws Exception {

		String idUsuario1 = s1.abrirSessao("login1", "senha1");
		String idCarona1 = s1.cadastrarCarona(idUsuario1, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s1.getAtributoCarona(idCarona1, "origem"), "origem1");
		assertEquals(s1.getAtributoCarona(idCarona1, "destino"), "destino1");

		String idUsuario2 = s1.abrirSessao("login2", "senha2");
		String idCarona2 = s1.cadastrarCarona(idUsuario2, "origem2",
				"destino2", "11/1/2013", "11:11", "2");
		assertEquals(s1.getAtributoCarona(idCarona2, "origem"), "origem2");
		assertEquals(s1.getAtributoCarona(idCarona2, "destino"), "destino2");

		String idUsuario3 = s1.abrirSessao("login3", "senha3");
		String idCarona3 = s1.cadastrarCarona(idUsuario3, "origem3",
				"destino3", "11/1/2013", "11:11", "3");
		assertEquals(s1.getAtributoCarona(idCarona3, "origem"), "origem3");
		assertEquals(s1.getAtributoCarona(idCarona3, "destino"), "destino3");

		String idUsuario4 = s2.abrirSessao("login1", "senha1");
		String idCarona4 = s2.cadastrarCarona(idUsuario4, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s2.getAtributoCarona(idCarona4, "origem"), "origem1");
		assertEquals(s2.getAtributoCarona(idCarona4, "destino"), "destino1");
	}

	@Test
	public void testGetCarona() throws Exception {

		String idUsuario1 = s1.abrirSessao("login1", "senha1");
		String idCarona1 = s1.cadastrarCarona(idUsuario1, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s1.getCarona(idCarona1), "origem1" + " para " + "destino1"
				+ ", no dia " + "11/1/2013" + ", as " + "11:11");

		String idUsuario2 = s1.abrirSessao("login2", "senha2");
		String idCarona2 = s1.cadastrarCarona(idUsuario2, "origem2",
				"destino2", "11/1/2013", "11:11", "2");
		assertEquals(s1.getCarona(idCarona2), "origem2" + " para " + "destino2"
				+ ", no dia " + "11/1/2013" + ", as " + "11:11");

		String idUsuario3 = s1.abrirSessao("login3", "senha3");
		String idCarona3 = s1.cadastrarCarona(idUsuario3, "origem3",
				"destino3", "11/1/2013", "11:11", "3");
		assertEquals(s1.getCarona(idCarona3), "origem3" + " para " + "destino3"
				+ ", no dia " + "11/1/2013" + ", as " + "11:11");

		String idUsuario4 = s2.abrirSessao("login1", "senha1");
		String idCarona4 = s2.cadastrarCarona(idUsuario4, "origem1",
				"destino1", "11/1/2013", "11:11", "1");
		assertEquals(s2.getCarona(idCarona4), "origem1" + " para " + "destino1"
				+ ", no dia " + "11/1/2013" + ", as " + "11:11");
	}
}
