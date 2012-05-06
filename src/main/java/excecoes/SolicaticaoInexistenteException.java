package excecoes;

public class SolicaticaoInexistenteException extends Exception {
	public SolicaticaoInexistenteException(){
		super("Solicitação inexistente");
	}
}
