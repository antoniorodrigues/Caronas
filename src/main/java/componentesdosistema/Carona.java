package componentesdosistema;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
@SuppressWarnings("serial")
@PersistenceCapable  
public class Carona implements Serializable{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String key;

	@PrimaryKey
	private String origem;
	@PrimaryKey
	private String destino;
	@PrimaryKey
	private String cidade;
	@PrimaryKey
	private String data;
	@PrimaryKey
	private String estado;
	@PrimaryKey
	private String hora;
	@PrimaryKey
	private String vagas;
	@PrimaryKey
	private String ID;
	@PrimaryKey
	private Usuario dono;
	@PrimaryKey
	private List<Usuario> todosCaroneiros;
	@PrimaryKey
	private List<String> caroneiros; // passageiros dessa carona
	@PrimaryKey
	private String pontoDeEncontro;

	/**
	 * Constrói um objeto do tipo Carona utilizando como atributos a origem, o destino, a data, a hora e a quantidade de vagas da carona.
	 * 
	 * @param origem
	 * 			Local de origem (ponto de partida) da carona.
	 * @param destino
	 *          Local de destino (ponto de chegada) da carona.
	 * @param data
	 *          Data para a qual a carona é marcada.
	 * @param hora
	 *          Hora de partida da carona.
	 * @param vagas
	 *          Número de vagas existentes na carona.
	 * @throws Exception
	 *          Caso a origem e/ou o destino sejam nulos ou vazios, a data seja inferior a data atual, a hora não esteja no padrão HH:mm ou a quantidade de vagas seja nula ou não-numérica.
	 */
	public Carona(String origem, String destino, String data, String hora, 	String vagas) throws DadosCaronaException {
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setData(data);
		this.setHora(hora);
		this.setVagas(vagas);
		this.caroneiros = new ArrayList<String>();
		this.todosCaroneiros = new ArrayList<Usuario>();
	}
	public Carona(){};
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Constrói um objeto do tipo Carona utilizando como atributos a origem, o destino, a cidade, a data, a hora e a quantidade de vagas da carona. 
	 * 
	 * @param origem
	 * 			Local de origem (ponto de partida) da carona.
	 * @param destino
	 * 			Local de destino (ponto de chegada) da carona.
	 * @param cidade
	 * 			A cidade onde a carona está sendo fornecida.
	 * @param data
	 * 			Data para a qual a carona é marcada.
	 * @param hora
	 * 			Hora de partida da carona.
	 * @param vagas
	 * 			Número de vagas existentes na carona.
	 * @throws Exception
	 * 			Caso a origem e/ou o destino e/ou a cidade sejam nulos ou vazios, a data seja inferior a data atual, a hora não esteja no padrão HH:mm ou a quantidade de vagas seja nula ou não-numérica.
	 */
	public Carona(String origem, String destino, String cidade, String data, String hora, String vagas) throws DadosCaronaException {
		this(origem, destino, data, hora, vagas);
		this.setCidade(cidade);
	}

	/**
	 * Retorna a data para a qual a carona foi marcada.
	 * 
	 * @return A data da carona.
	 */
	public String getData() {
		return data;
	}

	/**
	 * Atribui uma data a carona.
	 * 
	 * @param data
	 *            A data a ser atribuída a carona.
	 * @throws Exception
	 *             Caso a data da carona seja anterior a data atual.
	 */
	public void setData(String data) throws DadosCaronaException {
		if (!verificaDataValida(data))
			throw new DadosCaronaException("Data inválida");

		this.data = data;
	}

	/**
	 * Verifica se a data da carona é ou não anterior a data atual.
	 * 
	 * @param data
	 *            A data da carona.
	 * @return True, se a data for igual ou posterior a data atual, False caso contrário.
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
	 * Retorna a hora da carona.
	 * 
	 * @return A hora da carona.
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Atribui a hora da carona.
	 * 
	 * @param hora
	 *          A hora da carona.
	 * @throws Exception
	 * 			Caso a hora da carona não esteja no padrão HH:mm.
	 */
	public void setHora(String hora) throws DadosCaronaException {
		if (!verificaHoraValida(hora))
			throw new DadosCaronaException("Hora inválida");
		
		this.hora = hora;
	}

	/**
	 * Retorna o número de vagas da carona.
	 * 
	 * @return O número de vagas da carona.
	 */
	public String getVagas() {
		return vagas;
	}

	/**
	 * Atribui o número de vagas da carona.
	 * 
	 * @param vagas
	 * 			O número de vagas da carona.
	 * @throws Exception
	 * 			Caso o número de vagas da carona seja nulo ou não-numérico.
	 */
	public void setVagas(String vagas) throws DadosCaronaException {
		if (vagas == null || !vagas.matches("^[0-9]*$")) {
			throw new DadosCaronaException("Vaga inválida");
		}
		
		this.vagas = vagas;
	}

	/**
	 * Retorna o local de origem da carona.
	 * 
	 * @return O local de origem da carona.
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Atribui o local de origem da carona.
	 * 
	 * @param origem
	 *          O local de origem da carona.
	 * @throws Exception
	 * 			Caso o local de origem da carona seja nulo ou vazio.
	 */
	public void setOrigem(String origem) throws DadosCaronaException {
		if (origem == null || origem.equals(""))
			throw new DadosCaronaException("Origem inválida");
		
		this.origem = origem;
	}

	/**
	 * Retorna o local de destino da carona.
	 * 
	 * @return O local de destino da carona.
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Atribui o local de destino da carona.
	 * 
	 * @param destino
	 * 			O local de destino da carona.
	 * @throws Exception
	 *  		Caso o local de destino da carona seja nulo ou vazio.
	 */
	public void setDestino(String destino) throws DadosCaronaException {
		if (destino == null || destino.equals(""))
			throw new DadosCaronaException("Destino inválido");

		this.destino = destino;
	}

	/**
	 * Retorna o trajeto da carona.
	 * 
	 * @return O trajeto da carona.
	 */
	public String getTrajeto() {
		return getOrigem() + " - " + getDestino();
	}

	/**
	 * Retorna um dentre todos os atributos da carona.
	 * 
	 * @param atributo
	 * 			Um dos seguintes atributos: origem, destino, data, hora, número de vagas, Ponto de Encontro, Caroneiros, ehMunicipal.
	 * @return O atributo pesquisado.
	 * @throws Exception
	 * 			Caso não exista o atributo solicitado.
	 */
	public String getAtributoCarona(String atributo) throws AtributoIlegalException {
		if(atributo.equals("origem")) {
			return getOrigem();
		}
		else if(atributo.equals("destino")) {
			return getDestino();
		}
		else if (atributo.equals("data")) {
			return getData();
		}
		else if (atributo.equals("hora")) {
			return getHora();
		}
		else if (atributo.equals("vagas")) {
			return getVagas();
		}
		else if (atributo.equals("Ponto de Encontro")) {
			return getPontoDeEncontro();
		}
		else if (atributo.equals("Caroneiros")) {
			return getCaroneiros().toString();
		}
		else if(atributo.equals("ehMunicipal")){
			return isMunicipal();
		}
		else {
			throw new AtributoIlegalException("Atributo inexistente");
		}

	}
	
	/**
	 * Verifica se a carona é ou não municipal.
	 * 
	 * @return True caso a carona seja municipal, False caso contrário.
	 */
	public String isMunicipal(){
		if(this.getCidade() == null){
			return "false";
		}
		
		return "true";
	}

	/**
	 * Retorna o identificador da carona.
	 * 
	 * @return O identificador da carona.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Atribui um identificador a carona.
	 * 
	 * @param ID
	 * 			O identificador da carona.
	 */
	public void setID(String ID) {
		this.ID = ID;
	}
	
	/**
	 * Retorna o dono da carona.
	 * 
	 * @return O dono da carona.
	 */
	public Usuario getDono(){
		return dono;
	}
	
	/**
	 * Atribui um dono a carona.
	 * 
	 * @param dono
	 * 			O dono da carona.
	 */
	public void setDono(Usuario dono){
		this.dono = dono;
	}
	
	/**
	 * Retorna a cidade onde a carona está sendo oferecida.
	 * 
	 * @return A cidade onde a carona está sendo oferecida.
	 */
	public String getCidade(){
		return cidade;
	}
	
	/**
	 * Atribui uma cidade a carona.
	 * 
	 * @param cidade
	 * 			A cidade da carona.
	 */
	public void setCidade(String cidade){
		this.cidade = cidade;
	}

	//TODO: Faltou terminar o javadoc deste método
	/**
	 * Adiciona uma pessoa como integrante (passageira) da carona.
	 * 
	 * @param caroneiro
	 * 			Pessoa integrante futura de uma carona.
	 * @param pontoDeEncontro
	 *          Ponto de encontro entre o dono da carona e o caroneiro (interessado na carona).
	 * @throws NumberFormatException
	 * 			Quando o formato passado ao método como parâmetro é inválido
	 * @throws Exception
	 */
	public void adicionaCaroneiro(String caroneiro, String pontoDeEncontro)throws NumberFormatException, DadosCaronaException {
		setVagas(String.valueOf(Integer.parseInt(this.getVagas()) - 1));
		setPontoDeEncontro(pontoDeEncontro);
		caroneiros.add(caroneiro);
	}

	/**
	 * Adiciona um caroneiro a carona.
	 * 
	 * @param caroneiro
	 * 			Pessoa a ser adicionada a carona.
	 */
	public void adicionaCaroneiro(Usuario caroneiro) {
		todosCaroneiros.add(caroneiro);
	}

	/**
	 * Retorna todos os caroneiros de uma carona.
	 * 
	 * @return Lista de caroneiros de uma carona.
	 */
	public List<Usuario> getTodosCaroneiros() {
		return todosCaroneiros;
	}

	/**
	 * Retorna o ponto de encontro de origem da carona.
	 * 
	 * @return Ponto de encontro de origem da carona.
	 */
	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	/**
	 * Atribui um ponto de encontro de origem a carona.
	 * 
	 * @param pontoDeEncontro
	 * 			O ponto de encontro de origem da carona.
	 */
	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	/**
	 * Retorna uma lista apenas com o nome dos integrantes da carona.
	 * 
	 * @return Lista apenas com o nome dos integrantes da carona.
	 */
	public List<String> getCaroneiros() {
		return caroneiros;
	}

	/**
	 * Representação textual de uma carona, contendo origem, destino, data e hora.
	 */
	@Override
	public String toString() {
		return getOrigem() + " para " + getDestino() + ", no dia " + getData()
				+ ", as " + getHora();
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}