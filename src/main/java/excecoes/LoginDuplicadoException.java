package excecoes;

public class LoginDuplicadoException extends Exception {
	public LoginDuplicadoException() {
		super("Já existe um usuário com este login");
	}
}
