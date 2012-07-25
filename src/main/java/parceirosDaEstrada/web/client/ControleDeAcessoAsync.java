package parceirosDaEstrada.web.client;


import componentesdosistema.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ControleDeAcessoAsync {

	public void Login(String login, String senha, String nome, String endereco, String email, AsyncCallback<Boolean> callback);
	public void GravaUsuario(String login, String senha, String nome, String endereco, String email, AsyncCallback<Boolean> callback);

	void RetornaUsuarios(AsyncCallback<Usuario[]> callback);
}
