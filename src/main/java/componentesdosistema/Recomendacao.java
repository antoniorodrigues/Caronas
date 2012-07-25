package componentesdosistema;

import java.util.List;

public abstract class Recomendacao {
	private List<Carona> caronasCadastradas;
	private Usuario usuario;

	public Recomendacao(Usuario usuario, List<Carona> caronasCadastradas) {
		this.caronasCadastradas = caronasCadastradas;
		this.usuario = usuario;
	}
	public abstract List<Carona> gerarRecomendacoes();

	public List<Carona> getCaronasCadastradas() {
		return caronasCadastradas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
