package parceirosDaEstrada.web.client;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import componentesdosistema.Usuario;

@RemoteServiceRelativePath("parceirosService")
public interface ControleDeAcesso extends RemoteService {

	public Boolean Login(String login, String senha, String nome, String endereco, String email);
	public Boolean GravaUsuario(String login, String senha, String nome, String endereco, String email);

	Usuario[] RetornaUsuarios();
}
