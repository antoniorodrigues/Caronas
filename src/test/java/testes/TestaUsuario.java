package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import componentesdosistema.DadosUsuarioException;
import componentesdosistema.PerfilDoUsuario;
import componentesdosistema.Usuario;


public class TestaUsuario {

	private PerfilDoUsuario p1;
	private PerfilDoUsuario p2;
	private PerfilDoUsuario p3;
	private PerfilDoUsuario p4;
		
	private Usuario user1, user2, user3, user4, user5;
	
	@Before
	public void setUp() throws Exception {

		p1 = new PerfilDoUsuario("login1", "senha1", "nome1", "endereco1",
				"email1");
		p2 = new PerfilDoUsuario("login2", "senha2", "nome2", "endereco2",
				"email2");
		p3 = new PerfilDoUsuario("login3", "senha3", "nome3", "endereco3",
				"email3");
		p4 = new PerfilDoUsuario("login4", "senha4", "nome4", "endereco4",
				"email4");
		
		user1 = new Usuario("u1","1","usuario1","Campina Grande1","u1@gmail.com");
		user2 = new Usuario("u2","2","usuario2","Campina Grande2","u2@gmail.com");
		user3 = new Usuario("u3","3","usuario3","Campina Grande3","u3@gmail.com");
		user4 = new Usuario("u4","4","usuario4","Campina Grande4","u4@gmail.com");
		user5 = new Usuario("u5","5","usuario5","Campina Grande5","u5@gmail.com");
	}
	
	@Test
	public void TestaLoginInvalido() throws DadosUsuarioException{
		try {
			new Usuario(null,"1","usuario1","Campina Grande1","u1g@gmail.com");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			new Usuario("", "123", "Fulano", "sem casa", "zeNinguem@bol.com");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());
		}
	}

	@Test
	public void TestaNomeInvalido() throws DadosUsuarioException{
		try {
			new Usuario("u1","1",null,"Campina Grande1","u1g@gmail.com");
		} catch (Exception e) {
			assertEquals("Nome inválido", e.getMessage());
		}
		
		try {
			new Usuario("u1","1","","Campina Grande1","u1g@gmail.com");
		} catch (Exception e) {
			assertEquals("Nome inválido", e.getMessage());
		}
	}

	@Test
	public void TestaEmailInvalido() throws DadosUsuarioException{
		try {
			new Usuario("u1","1","usuario1","Campina Grande1",null);
		} catch (Exception e) {
			assertEquals("Email inválido", e.getMessage());
		}
		
		try {
			new Usuario("u1","1","usuario1","Campina Grande1","");
		} catch (Exception e) {
			assertEquals("Email inválido", e.getMessage());
		}
	}
	
	@Test
	public void testPerfilUsuario() throws Exception {

		assertEquals(p1.getLogin(), "login1");
		assertEquals(p1.getSenha(), "senha1");
		assertEquals(p1.getNome(), "nome1");
		assertEquals(p1.getEndereco(), "endereco1");
		assertEquals(p1.getEmail(), "email1");

		//assertEquals(p1.getAtributo(p1.getLogin(), "nome"), "nome1");
		//assertEquals(p1.getAtributo(p1.getLogin(), "endereco"), "endereco1");

		assertEquals(p2.getLogin(), "login2");
		assertEquals(p2.getSenha(), "senha2");
		assertEquals(p2.getNome(), "nome2");
		assertEquals(p2.getEndereco(), "endereco2");
		assertEquals(p2.getEmail(), "email2");

		//assertEquals(p2.getAtributo(p2.getLogin(), "nome"), "nome2");
		//assertEquals(p2.getAtributo(p2.getLogin(), "endereco"), "endereco2");

		assertEquals(p3.getLogin(), "login3");
		assertEquals(p3.getSenha(), "senha3");
		assertEquals(p3.getNome(), "nome3");
		assertEquals(p3.getEndereco(), "endereco3");
		assertEquals(p3.getEmail(), "email3");

		//assertEquals(p3.getAtributo(p3.getLogin(), "nome"), "nome3");
		//assertEquals(p3.getAtributo(p3.getLogin(), "endereco"), "endereco3");

		assertEquals(p4.getLogin(), "login4");
		assertEquals(p4.getSenha(), "senha4");
		assertEquals(p4.getNome(), "nome4");
		assertEquals(p4.getEndereco(), "endereco4");
		assertEquals(p4.getEmail(), "email4");

		//assertEquals(p4.getAtributo(p4.getLogin(), "nome"), "nome4");
		//assertEquals(p4.getAtributo(p4.getLogin(), "endereco"), "endereco4");
	}

	@Test
	public void TestaGetLogin() {
		assertTrue(user1.getLogin().equals("u1"));
		assertTrue(user2.getLogin().equals("u2"));
		assertTrue(user3.getLogin().equals("u3"));
		assertTrue(user4.getLogin().equals("u4"));
		assertTrue(user5.getLogin().equals("u5"));

		assertFalse(user1.getLogin().equals(null));
		assertFalse(user2.getLogin().equals(null));
		assertFalse(user3.getLogin().equals(null));
		assertFalse(user4.getLogin().equals(null));
		assertFalse(user5.getLogin().equals(null));

		assertFalse(user1.getLogin().equals(""));
		assertFalse(user2.getLogin().equals(""));
		assertFalse(user3.getLogin().equals(""));
		assertFalse(user4.getLogin().equals(""));
		assertFalse(user5.getLogin().equals(""));

		assertFalse(user1.getLogin().equals("xxx"));
		assertFalse(user2.getLogin().equals("xxx"));
		assertFalse(user3.getLogin().equals("xxx"));
		assertFalse(user4.getLogin().equals("xxx"));
		assertFalse(user5.getLogin().equals("xxx"));

		assertEquals(false, user1.getLogin().equals(user2.getLogin()));
		assertEquals(false, user1.getLogin().equals(user3.getLogin()));
		assertEquals(false, user1.getLogin().equals(user4.getLogin()));
		assertEquals(false, user1.getLogin().equals(user5.getLogin()));
	}

	@Test
	public void TestaGetSenha() {
		assertTrue(user1.getSenha().equals("1"));
		assertTrue(user2.getSenha().equals("2"));
		assertTrue(user3.getSenha().equals("3"));
		assertTrue(user4.getSenha().equals("4"));
		assertTrue(user5.getSenha().equals("5"));

		assertFalse(user1.getSenha().equals(null));
		assertFalse(user2.getSenha().equals(null));
		assertFalse(user3.getSenha().equals(null));
		assertFalse(user4.getSenha().equals(null));
		assertFalse(user5.getSenha().equals(null));

		assertFalse(user1.getSenha().equals(""));
		assertFalse(user2.getSenha().equals(""));
		assertFalse(user3.getSenha().equals(""));
		assertFalse(user4.getSenha().equals(""));
		assertFalse(user5.getSenha().equals(""));

		assertFalse(user1.getSenha().equals("xxx"));
		assertFalse(user2.getSenha().equals("xxx"));
		assertFalse(user3.getSenha().equals("xxx"));
		assertFalse(user4.getSenha().equals("xxx"));
		assertFalse(user5.getSenha().equals("xxx"));

		assertEquals(false, user1.getSenha().equals(user2.getSenha()));
		assertEquals(false, user1.getSenha().equals(user3.getSenha()));
		assertEquals(false, user1.getSenha().equals(user4.getSenha()));
		assertEquals(false, user1.getSenha().equals(user5.getSenha()));
	}

	@Test
	public void TestaGetEmail() {
		assertTrue(user1.getEmail().equals("u1@gmail.com"));
		assertTrue(user2.getEmail().equals("u2@gmail.com"));
		assertTrue(user3.getEmail().equals("u3@gmail.com"));
		assertTrue(user4.getEmail().equals("u4@gmail.com"));
		assertTrue(user5.getEmail().equals("u5@gmail.com"));

		assertFalse(user1.getEmail().equals(null));
		assertFalse(user2.getEmail().equals(null));
		assertFalse(user3.getEmail().equals(null));
		assertFalse(user4.getEmail().equals(null));
		assertFalse(user5.getEmail().equals(null));

		assertFalse(user1.getEmail().equals(""));
		assertFalse(user2.getEmail().equals(""));
		assertFalse(user3.getEmail().equals(""));
		assertFalse(user4.getEmail().equals(""));
		assertFalse(user5.getEmail().equals(""));

		assertFalse(user1.getEmail().equals("xxx"));
		assertFalse(user2.getEmail().equals("xxx"));
		assertFalse(user3.getEmail().equals("xxx"));
		assertFalse(user4.getEmail().equals("xxx"));
		assertFalse(user5.getEmail().equals("xxx"));

		assertEquals(false, user1.getEmail().equals(user2.getEmail()));
		assertEquals(false, user1.getEmail().equals(user3.getEmail()));
		assertEquals(false, user1.getEmail().equals(user4.getEmail()));
		assertEquals(false, user1.getEmail().equals(user5.getEmail()));
	}

	@Test
	public void TestaGetNome() {
		assertTrue(user1.getNome().equals("usuario1"));
		assertTrue(user2.getNome().equals("usuario2"));
		assertTrue(user3.getNome().equals("usuario3"));
		assertTrue(user4.getNome().equals("usuario4"));
		assertTrue(user5.getNome().equals("usuario5"));

		assertFalse(user1.getNome().equals(null));
		assertFalse(user2.getNome().equals(null));
		assertFalse(user3.getNome().equals(null));
		assertFalse(user4.getNome().equals(null));
		assertFalse(user5.getNome().equals(null));

		assertFalse(user1.getNome().equals(""));
		assertFalse(user2.getNome().equals(""));
		assertFalse(user3.getNome().equals(""));
		assertFalse(user4.getNome().equals(""));
		assertFalse(user5.getNome().equals(""));

		assertFalse(user1.getNome().equals("xxx"));
		assertFalse(user2.getNome().equals("xxx"));
		assertFalse(user3.getNome().equals("xxx"));
		assertFalse(user4.getNome().equals("xxx"));
		assertFalse(user5.getNome().equals("xxx"));

		assertEquals(false, user1.getNome().equals(user2.getNome()));
		assertEquals(false, user1.getNome().equals(user3.getNome()));
		assertEquals(false, user1.getNome().equals(user4.getNome()));
		assertEquals(false, user1.getNome().equals(user5.getNome()));
	}
}