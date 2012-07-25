package parceirosDaEstrada.web.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;

import parceirosDaEstrada.web.client.ControleDeAcesso;

import componentesdosistema.Usuario;

@SuppressWarnings("serial")
public class ControleDeAcessoImpl 
		extends HttpServlet  
       implements ControleDeAcesso  {

	public Boolean Login(String login, String senha, String nome, String endereco, String email)
	{
		return true;
	}
	
	public Boolean GravaUsuario(String login, String senha, String nome, String endereco, String email)
	{
		Boolean returnValue = true;
		String buffer = "";
		Usuario usuario = null;
		PersistenceManager manager = null;
		
		try
		{
			usuario = new Usuario(login, senha, nome, endereco, email);
			usuario.setKey(login);
			manager = PMF.get().getPersistenceManager();
			manager.makePersistent(usuario);
		}
		catch (Exception ex)
		{
			returnValue = false;
			buffer = ex.toString();
		}
		finally
		{
			if (null != manager)
			{
				manager.close();
			}
		}
		return returnValue;
	}
	
	public Usuario[] RetornaUsuarios()
	{
		//Usuario[] retorno = new Usuario[1];
		//retorno[0] = new Usuario("Flavio", "Moraes");
		
		String query = "";
		PersistenceManager manager = null;
		Usuario[] retorno = null;
		List<Usuario> usuarios = null;
		
		try
		{
			query = "select from " + Usuario.class.getName();
			manager = PMF.get().getPersistenceManager();
			usuarios = (List<Usuario>)manager.newQuery(query).execute();

			retorno = new Usuario[usuarios.size()];
			
			for (int i = 0; i < retorno.length; i++)
			{
				retorno[i] = usuarios.get(i);
			}
		}
		catch (Exception ex)
		{
			retorno = null;
			query = ex.toString();
		}
		finally
		{
			if (null != manager)
			{
				manager.close();
			}
		}
		return retorno;
	}
}
