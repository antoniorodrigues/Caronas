package funcionalidades;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class SolicitacaoDeCarona {
	private Carona carona;
	private String donoDaCarona;
	private Usuario donoDaSolicitacao;
	private String pontoDeEncontro;
	private String idSolcitacao;

	/**
	 * Consrutor da classe Solicitação de Caronas
	 * 
	 * @param carona
	 *            Carona a ser solicitada
	 * @param donoDaCarona
	 *            Dono da carona a ser solicitada
	 * @param donoDasolicitacao
	 *            Dono da solicitação de uma carona (futuro possível caroneiro)
	 * @param pontoDeEncontro
	 *            Ponto de encontro entre caroneiros e dono da carona
	 */
	public SolicitacaoDeCarona(Carona carona, String donoDaCarona, Usuario donoDasolicitacao, String pontoDeEncontro) {

		this.setCarona(carona);
		this.setDonoDaCarona(donoDaCarona);
		this.setDonoDaSolicitacao(donoDasolicitacao);
		this.setPontoDeEncontro(pontoDeEncontro);
	}

	/**
	 * Método que solicita uma carona
	 * 
	 * @param carona
	 *            Carona a ser solicitada
	 * @param donoDaCarona
	 *            Dono da carona a ser solicitada
	 * @param donoDasolicitacao
	 *            Dono da solicitação de uma carona (futuro possível caroneiro)
	 */
	public SolicitacaoDeCarona(Carona carona, String donoDaCarona, Usuario donoDasolicitacao) {
		this(carona, donoDaCarona, donoDasolicitacao, "");
	}

	public String getAtributo(String idSolicitacao, String atributo) {
		String atributoSolicitado = null;
		if (atributo.equals("origem")) {
			atributoSolicitado = carona.getOrigem();
		} else if (atributo.equals("destino")) {
			atributoSolicitado = carona.getDestino();
		} else if (atributo.equals("Dono da carona")) {
			atributoSolicitado = this.getDonoDaCarona();
		} else if (atributo.equals("Dono da solicitacao")) {
			atributoSolicitado = getDonoDaSolicitacao().getNome();
		} else if (atributo.equals("Ponto de Encontro")) {
			atributoSolicitado = getPontoDeEncontro();
		}
		return atributoSolicitado;

	}

	/**
	 * Método que retorna o dono da carona
	 * 
	 * @return Dono da carona
	 */
	public String getDonoDaCarona() {
		return donoDaCarona;
	}

	/**
	 * Método que modifica o dono da carona
	 * 
	 * @param donoDaCarona
	 *            Dono da carona
	 */
	public void setDonoDaCarona(String donoDaCarona) {
		this.donoDaCarona = donoDaCarona;
	}

	/**
	 * Método a identificação (id) da solicitação da carona
	 * 
	 * @return Identificação da solicitação da carona
	 */
	public String getIdSolcitacao() {
		return idSolcitacao;
	}

	/**
	 * Método que modifica a identificação (id) da solicitação da carona
	 * 
	 * @param idSolicitacao
	 *            Solicitação da carona
	 */
	public void setIdSolcitacao(String idSolcitacao) {
		this.idSolcitacao = idSolcitacao;
	}

	/**
	 * Método que retorna o ponto de encontro da carona
	 * 
	 * @return Ponto de encontro da carona
	 */
	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	/**
	 * Método que modifica o ponto de encontro da carona
	 * 
	 * @param pontoDeEncontro
	 *            Ponto de encontro da carona
	 */
	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	/**
	 * Método que retorna a carona
	 * 
	 * @return Carona
	 */
	public Carona getCarona() {
		return carona;
	}

	/**
	 * Método que modifica a carona
	 * 
	 * @param carona
	 *            Nova carona
	 */
	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	/**
	 * Método que retorna o dono da solicitação de uma carona (futuro possível
	 * caroneiro)
	 * 
	 * @return Dono da solicitação de uma carona (futuro possível caroneiro)
	 */
	public Usuario getDonoDaSolicitacao() {
		return donoDaSolicitacao;
	}

	/**
	 * Método que modifica quem solicita a carona
	 * 
	 * @param donoDaSolicitacao
	 *            Dono da solicitação de uma carona
	 */
	public void setDonoDaSolicitacao(Usuario donoDaSolicitacao) {
		this.donoDaSolicitacao = donoDaSolicitacao;
	}
}
