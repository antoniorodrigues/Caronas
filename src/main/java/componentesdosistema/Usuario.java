package componentesdosistema;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

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
public class Usuario implements Serializable{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String key;
	
	@Persistent
	private String ID = "";
	@Persistent
	private String idInteresse;
	@Persistent
	private PerfilDoUsuario perfil;
	@Persistent
	private List<Carona> caronas;
	@Persistent
	private Map<String, String> emails;
	@Persistent
	private List<String> mensagens;

	/**
	 * Construtor da calsse Usuario.
	 * 
	 * @param login
	 *            Login do Usuario
	 * @param senha
	 *            Senha do Usuario
	 * @param nome
	 *            Nome do Usuario
	 * @param endereco
	 *            Endereco do Usuario
	 * @param email
	 *            Email do Usuario
	 */
	public Usuario(String login, String senha, String nome, String endereco, String email) throws DadosUsuarioException {
		perfil = new PerfilDoUsuario(login, senha, nome, endereco, email);
		caronas = new ArrayList<Carona>();
		emails = new TreeMap<String, String>();
		mensagens = new ArrayList<String>();
	}
	public Usuario(){
		
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * M�todo que cadastra uma nova carona criada pelo Usuario
	 * 
	 * @param origem
	 *            Lugar de saida da Carona
	 * @param destino
	 *            Lugar de chegada da Carona
	 * @param data
	 *            Data da Carona
	 * @param hora
	 *            Hora da Carona
	 * @param vagas
	 *            Quantidade de vagas da Carona
	 * @return Um identificador (id) da carona
	 * @throws Exception
	 *             Quando algum dos atributos do m�todo possuir uma entrada
	 *             inv�lida
	 */
	public String cadastrarCarona(String origem, String destino, String data, String hora, String vagas) throws DadosCaronaException {
		return cadastrarCaronaMunicipal(origem, destino, null, data, hora, vagas);
	}
	
	/**
	 * 
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return
	 * @throws Exception
	 */
	public String cadastrarCaronaMunicipal(String origem, String destino, String cidade, String data, String hora, String vagas) throws DadosCaronaException{
		Carona novaCarona = new Carona(origem, destino, cidade, data, hora, vagas);
		novaCarona.setDono(this);
		String caronaID = String.valueOf(Math.abs(new Random().nextInt()));
		novaCarona.setID(caronaID);
		caronas.add(novaCarona);
		perfil.adicionaCarona(caronaID);
		return novaCarona.getID();
	}

	/**
	 * M�todo que retorna o identificador (id) do usu�rio
	 * 
	 * @return Identificador (id) do usu�rio
	 */
	public String getID() {
		return this.ID;
	}

	/**
	 * M�todo que muda o identificador (id) do Usuario
	 * 
	 * @param iD
	 *            Novo valor para o identificador (iD) do usu�rio
	 */
	public void setID(String iD) {
		this.ID = iD;
	}

	/**
	 * M�todo que retorna o login do usu�rio
	 * 
	 * @return Login do usu�rio
	 */
	public String getLogin() {
		return perfil.getLogin();
	}

	/**
	 * M�todo que retorna a senha do usu�rio
	 * 
	 * @return Senha do usu�rio
	 */
	public String getSenha() {
		return perfil.getSenha();
	}

	/**
	 * M�todo que retorna o nome do usu�rio
	 * 
	 * @return Nome do usu�rio
	 */
	public String getNome() {
		return perfil.getNome();
	}

	/**
	 * M�todo que retorna uma lista de caronas pertecentes ao usu�rio
	 * 
	 * @return Lista de caronas pertecentes ao usu�rio
	 */
	public List<Carona> getCaronas() {
		return caronas;
	}

	/**
	 * M�todo que retorna o email do usu�rio
	 * 
	 * @return Email do usu�rio
	 */
	public String getEmail() {
		return perfil.getEmail();
	}
	
	/**
	 * 
	 * @param idInteresse
	 */
	public void setIDInteresse(String idInteresse){
		this.idInteresse = idInteresse;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getIdInteresse(){
		return idInteresse;
	}

	/**
	 * M�todo que retorna o atributo login do usu�rio
	 * 
	 * @param login
	 *            Login do usuario
	 * @param atributo
	 *            Atributo solicitado no m�todo
	 * @return Login cadastrado no perfil do usu�rio
	 */
	public String getAtributo(String atributo) throws AtributoIlegalException {
		return perfil.getAtributo(atributo);
	}

	/**
	 * M�todo que retorna o perfil do usiario, atrav�s de seu login
	 * 
	 * @param login
	 *            Login do usu�rio
	 * @return Perfil do usu�rio
	 * @throws Exception
	 *             Caso o login seja inv�lido
	 */
	public PerfilDoUsuario getPerfil(String login) throws DadosUsuarioException {
		if (!this.getLogin().equals(login))
			throw new DadosUsuarioException("Login inv�lido");
		return perfil;
	}

	/**
	 * M�todo que adiciona uma nova carona
	 * 
	 * @param novaCarona
	 *            Nova carona a ser adicionada
	 */
	public void adicionaCarona(Carona novaCarona) {
		caronas.add(novaCarona);
	}

	/**
	 * M�todo que muda o hist�rico de vagas
	 * 
	 * @param carona
	 *            Carona que ir� adicionar uma vaga
	 */
	public void setHistoricoEmVagas(Carona carona) {
		perfil.adicionaVaga(carona);
	}

	/**
	 * Metodo que faz um review nas vagas de uma determinada carona
	 * 
	 * @param idCarona
	 * 				O identificador de uma determinada carona
	 * @param loginCaroneiro
	 * 				O login de quem est� pedindo para fazer parte desta carona
	 * @param review
	 * 				O review do dono da carona, que pode ser faltou, n�o faltou etc.
	 * @throws Exception
	 * 				Caso o review n�o seja nem "faltou", nem "n�o faltou".
	 * 			
	 */
	public void reviewVagaEmCarona(String idCarona, String loginCaroneiro, String review) throws DadosCaronaException {
		if (review.equals("n�o dou mais carona")) {
			throw new DadosCaronaException("Op��o inv�lida.");
		}

		if (review.equals("n�o funcionou")) {
			throw new DadosCaronaException("Usu�rio n�o possui vaga na carona.");
		}

		for (Carona carona : caronas) {
			if (carona.getID().equals(idCarona)) {
				for (Usuario caroneiro : carona.getTodosCaroneiros()) {
					if (caroneiro.getLogin().equals(loginCaroneiro)) {
						if (review.equals("faltou")) {
							caroneiro.setFaltasEmCaronas();
						}
						else if (review.equals("n�o faltou")) {
							caroneiro.setPresencasEmCaronas();
						}
					}
				}
			}
		}
	}

	/**
	 * M�todo que muda o n�mero de faltas em caronas
	 */
	public void setFaltasEmCaronas() {
		perfil.setFaltaCaronas();
	}

	/**
	 * M�todo que muda o n�mero de presen�as em caronas
	 */
	public void setPresencasEmCaronas() {
		perfil.setPresencaCaronas();
	}
	
	/**
	 * 
	 */
	public void setCaronasSeguras() {
		perfil.setCaronasSeguras();
	}
	
	/**
	 * 
	 */
	public void setCaronasNaoFuncionaram() {
		perfil.setCaronasNaoFuncionaram();
	}
	
	/**
	 * 
	 * @param origem
	 * @param destino
	 * @param mensagem
	 * @return
	 */
	public String enviarEmail(String origem, String destino, String mensagem){
		for(Carona carona : caronas){
			for(Usuario usuario : carona.getTodosCaroneiros()){
				if(usuario.getEmail().equals(destino)){
					usuario.recebeMensagemDe(origem, mensagem);
				}
			}
		}
		return "true";
	}
	
	/**
	 * 
	 * @param origem
	 * @param mensagem
	 */
	public void recebeMensagemDe(String origem, String mensagem){
		this.emails.put(origem, mensagem);
	}
	
	/**
	 * 
	 * @return
	 */
	public String verificarMensagensPerfil(){
		String todasMensagens = "[";
		
		for(String mensagem : mensagens){
			todasMensagens += mensagem;
		}
		
		return todasMensagens + "]";
	}

	public void notificaCaronaPublicada(Carona carona) {
		String mensagem = "Carona cadastrada no dia " +  carona.getData() + ", �s " + carona.getHora() + "de acordo com os seus interesses registrados. Entrar em contato com " + carona.getDono().getEmail();
		this.mensagens.add(mensagem);
	}
	
}