package funcionalidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import excecoes.AtributoInexistenteException;
import excecoes.DataInvalidaException;
import excecoes.DestinoInvalidoException;
import excecoes.HoraInvalidaException;
import excecoes.OrigemInvalidaException;
import excecoes.VagaInvalidaException;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class Carona {
	private String origem;
	private String destino;
	private String data;
	private String hora;
	private String vagas;
	private String ID;
	private List<Usuario> todosCaroneiros;
	private List<String> caroneiros; // passageiros dessa carona
	private String pontoDeEncontro;

	/**
	 * Construtor da classe Carona
	 * 
	 * @param origem
	 *            Local de origem (ponto de partida) da carona
	 * @param destino
	 *            Local de destino (ponto de chegada) da carona
	 * @param data
	 *            Data que será realizada a carona
	 * @param hora
	 *            Hora de partida da carnoa
	 * @param vagas
	 *            Número de vagas existentes na carona
	 * @throws Exception
	 *             Quando algum dos atributos do método construtor possuir uma
	 *             entrada inválida
	 */
	public Carona(String origem, String destino, String data, String hora,
			String vagas) throws Exception {
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setData(data);
		this.setHora(hora);
		this.setVagas(vagas);
		this.caroneiros = new ArrayList<String>();
		this.todosCaroneiros = new ArrayList<Usuario>();
	}

	/**
	 * Método que retorna a data da carona
	 * 
	 * @return Data da carona
	 */
	public String getData() {
		return data;
	}

	/**
	 * Método que modifica a data da carona
	 * 
	 * @param data
	 *            Data da carona
	 * @throws Exception
	 *             Caso a data da carona seja inválida
	 */
	public void setData(String data) throws Exception {
		if (!verificaDataValida(data))
			throw new DataInvalidaException();

		this.data = data;
	}

	/**
	 * Método que verifica se a data da carona é ou não uma data válida no
	 * sistema.
	 * 
	 * @param data
	 *            Data da carona
	 * @return True, se a data for válida False, se a data não for válida
	 */
	private boolean verificaDataValida(String data) {
		boolean dataValida = (data != null && !data.equals(""));

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try {
			Date minhaData = format.parse(data);

			if (minhaData.before(new Date()))
				dataValida = false;
		} catch (Exception ex) {
			dataValida = false;
		}
		return dataValida;

	}

	/**
	 * Método que verifica se a hora da carona é ou não uma hora válida no
	 * sistema.
	 * 
	 * @param hora
	 *            Hora da carona
	 * @return True, se a hora for válida False, se a hora não for válida
	 */
	private boolean verificaHoraValida(String hora) {
		boolean horaValida = (hora != null && !hora.equals(""));
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);
		try {
			sdf.parse(hora);
		} catch (Exception e) {
			horaValida = false;
		}
		return horaValida;
	}

	/**
	 * Método que retorna a hora da carona
	 * 
	 * @return Hora da carona
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Método que modifica a hora da carona
	 * 
	 * @param hora
	 *            Hora da carona
	 * @throws Exception
	 *             Caso a hora da carona seja inválida
	 */
	public void setHora(String hora) throws Exception {
		if (!verificaHoraValida(hora))
			throw new HoraInvalidaException();
		this.hora = hora;
	}

	/**
	 * Método que retorna o número de vagas da carona
	 * 
	 * @return Número de vagas da carona
	 */
	public String getVagas() {
		return vagas;
	}

	/**
	 * Método que modifica o número de vagas da carona
	 * 
	 * @param vagas
	 *            Número de vagas da carona
	 * @throws Exception
	 *             Caso o número de vagas da carona seja inválido
	 */
	public void setVagas(String vagas) throws Exception {
		if (vagas == null || !vagas.matches("^[0-9]*$")) {
			throw new VagaInvalidaException();
		}
		this.vagas = vagas;
	}

	/**
	 * Método que retorna o local de origem da carona
	 * 
	 * @return Local de origem da carona
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Método que modifica o local de origem da carona
	 * 
	 * @param origem
	 *            Local de origem da carona
	 * @throws Exception
	 *             Caso o local de origem da carona seja inválido
	 */
	public void setOrigem(String origem) throws Exception {
		if (origem == null || origem.equals(""))
			throw new OrigemInvalidaException();
		this.origem = origem;
	}

	/**
	 * Método que retorna o local de destino da carona
	 * 
	 * @return Local de destino da carona
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Método que modifica o local de destino da carona
	 * 
	 * @param destino
	 *            Local de destino da carona
	 * @throws Exception
	 *             Caso o local de destino da carona seja inválido
	 */
	public void setDestino(String destino) throws Exception {
		if (destino == null || destino.equals(""))
			throw new DestinoInvalidoException();

		this.destino = destino;
	}

	/**
	 * Método que retorna o trajeto da carona
	 * 
	 * @return Trajeto da carona
	 */
	public String getTrajeto() {
		return getOrigem() + " - " + getDestino();
	}

	/**
	 * Método que retorna um atributo dentre todos os atributos da carona
	 * (origem, destino, data, hora, número de vagas, ponto de encontro,
	 * caroneiros)
	 * 
	 * @param atributo
	 *            Atributo solicitado no método
	 * @return Atributo solicitado no método
	 * @throws Exception
	 *             Caso não exista o atributo solicitado.
	 */
	public String getAtributoCarona(String atributo) throws Exception {
		if (atributo.equals("origem")) {
			return getOrigem();
		} else if (atributo.equals("destino")) {
			return getDestino();
		} else if (atributo.equals("data")) {
			return getData();
		} else if (atributo.equals("hora")) {
			return getHora();
		} else if (atributo.equals("vagas")) {
			return getVagas();
		} else if (atributo.equals("Ponto de Encontro")) {
			return getPontoDeEncontro();
		} else if (atributo.equals("Caroneiros")) {
			return getSrtingCaroneiros();
		} else {
			throw new AtributoInexistenteException();
		}

	}

	/**
	 * Método que retorna um identificador da carona
	 * 
	 * @return Identificador da carona
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Método que modifica o identificador da carona
	 * 
	 * @param ID
	 *            Identificador da carona
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * Método que adiciona uma pessoa como integrante (passageira) da carona
	 * 
	 * @param caroneiro
	 *            Pessoa integrante futura de uma carona
	 * @param pontoDeEncontro
	 *            Ponto de encontro entre o dono da carona e o caroneiro
	 *            (interessado na carona)
	 * @throws NumberFormatException
	 *             Quando o formato passado ao método como parâmetro é inválido
	 * @throws Exception
	 */
	public void adicionaCaroneiro(String caroneiro, String pontoDeEncontro)
			throws NumberFormatException, Exception {
		setVagas(String.valueOf(Integer.parseInt(this.getVagas()) - 1));
		setPontoDeEncontro(pontoDeEncontro);
		caroneiros.add(caroneiro);
	}

	/**
	 * Método que adiciona um caroneiro na carona
	 * 
	 * @param caroneiro
	 *            Pessoa a ser adicionada na carona
	 */
	public void adicionaCaroneiro(Usuario caroneiro) {
		todosCaroneiros.add(caroneiro);
	}

	/**
	 * Método que retorna todos os caroneiros de uma carona
	 * 
	 * @return Lista de caroneiros de uma carona
	 */
	public List<Usuario> getTodosCaroneiros() {
		return todosCaroneiros;
	}

	/**
	 * Método que retorna o ponto de encontro da origem da carona
	 * 
	 * @return Ponto de encontro da origem da carona
	 */
	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	/**
	 * Método que modifica o ponto de encontro da origem da da carona
	 * 
	 * @param ponto
	 *            de encontro Ponto de encontro da origem da carona
	 */
	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	/**
	 * Método que retorna uma lista dos integrantes da carona
	 * 
	 * @return Lista dos integrantes da carona
	 */
	public List<String> getCaroneiros() {
		return caroneiros;
	}

	/**
	 * Método que retorna uma String contendo uma lista de caroneiros
	 * (integrantes) da carona
	 * 
	 * @return Uma String contendo uma lista de caroneiros (integrantes) da
	 *         carona
	 */
	public String getSrtingCaroneiros() {
		String caroneiros = "";

		for (String usuario : getCaroneiros()) {
			caroneiros += usuario;
		}
		return caroneiros;
	}

	/**
	 * Representação textual de uma carona, contendo origem, destino, data e
	 * hora.
	 */
	@Override
	public String toString() {
		return getOrigem() + " para " + getDestino() + ", no dia " + getData()
				+ ", as " + getHora();
	}
}