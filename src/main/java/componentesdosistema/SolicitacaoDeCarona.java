package componentesdosistema;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class SolicitacaoDeCarona {
	private Carona carona;
	private Usuario donoDaCarona;
	private Usuario donoDaSolicitacao;
	private String pontoDeEncontro;
	private String idSolcitacao;

	/**
	 * Consrutor da classe Solicita��o de Caronas
	 * 
	 * @param carona
	 *            Carona a ser solicitada
	 * @param donoDaCarona
	 *            Dono da carona a ser solicitada
	 * @param donoDasolicitacao
	 *            Dono da solicita��o de uma carona (futuro poss�vel caroneiro)
	 * @param pontoDeEncontro
	 *            Ponto de encontro entre caroneiros e dono da carona
	 */
	public SolicitacaoDeCarona(Carona carona, Usuario donoDasolicitacao, String pontoDeEncontro) {

		this.setCarona(carona);
		this.setDonoDaCarona(carona.getDono());
		this.setDonoDaSolicitacao(donoDasolicitacao);
		this.setPontoDeEncontro(pontoDeEncontro);
	}

	/**
	 * M�todo que solicita uma carona
	 * 
	 * @param carona
	 *            Carona a ser solicitada
	 * @param donoDaCarona
	 *            Dono da carona a ser solicitada
	 * @param donoDasolicitacao
	 *            Dono da solicita��o de uma carona (futuro poss�vel caroneiro)
	 */
	public SolicitacaoDeCarona(Carona carona, Usuario donoDasolicitacao) {
		this(carona, donoDasolicitacao, "");
	}

	public String getAtributo(String idSolicitacao, String atributo) {
		String atributoSolicitado = null;
		if (atributo.equals("origem")) {
			atributoSolicitado = carona.getOrigem();
		} else if (atributo.equals("destino")) {
			atributoSolicitado = carona.getDestino();
		} else if (atributo.equals("Dono da carona")) {
			atributoSolicitado = this.getDonoDaCarona().getNome();
		} else if (atributo.equals("Dono da solicitacao")) {
			atributoSolicitado = getDonoDaSolicitacao().getNome();
		} else if (atributo.equals("Ponto de Encontro")) {
			atributoSolicitado = getPontoDeEncontro();
		}
		return atributoSolicitado;

	}

	/**
	 * M�todo que retorna o dono da carona
	 * 
	 * @return Dono da carona
	 */
	public Usuario getDonoDaCarona() {
		return donoDaCarona;
	}

	/**
	 * M�todo que modifica o dono da carona
	 * 
	 * @param donoDaCarona
	 *            Dono da carona
	 */
	public void setDonoDaCarona(Usuario donoDaCarona) {
		this.donoDaCarona = donoDaCarona;
	}

	/**
	 * M�todo a identifica��o (id) da solicita��o da carona
	 * 
	 * @return Identifica��o da solicita��o da carona
	 */
	public String getIdSolcitacao() {
		return idSolcitacao;
	}

	/**
	 * M�todo que modifica a identifica��o (id) da solicita��o da carona
	 * 
	 * @param idSolicitacao
	 *            Solicita��o da carona
	 */
	public void setIdSolcitacao(String idSolcitacao) {
		this.idSolcitacao = idSolcitacao;
	}

	/**
	 * M�todo que retorna o ponto de encontro da carona
	 * 
	 * @return Ponto de encontro da carona
	 */
	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	/**
	 * M�todo que modifica o ponto de encontro da carona
	 * 
	 * @param pontoDeEncontro
	 *            Ponto de encontro da carona
	 */
	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	/**
	 * M�todo que retorna a carona
	 * 
	 * @return Carona
	 */
	public Carona getCarona() {
		return carona;
	}

	/**
	 * M�todo que modifica a carona
	 * 
	 * @param carona
	 *            Nova carona
	 */
	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	/**
	 * M�todo que retorna o dono da solicita��o de uma carona (futuro poss�vel
	 * caroneiro)
	 * 
	 * @return Dono da solicita��o de uma carona (futuro poss�vel caroneiro)
	 */
	public Usuario getDonoDaSolicitacao() {
		return donoDaSolicitacao;
	}

	/**
	 * M�todo que modifica quem solicita a carona
	 * 
	 * @param donoDaSolicitacao
	 *            Dono da solicita��o de uma carona
	 */
	public void setDonoDaSolicitacao(Usuario donoDaSolicitacao) {
		this.donoDaSolicitacao = donoDaSolicitacao;
	}
}