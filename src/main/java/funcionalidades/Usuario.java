package funcionalidades;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import excecoes.Excecoes;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class Usuario {

	private String ID = "";
	private String idInteresse;
	private PerfilDoUsuario perfil;
	private List<Carona> caronas;
	private Map<String, String> emails;
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
	public Usuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		perfil = new PerfilDoUsuario(login, senha, nome, endereco, email);
		caronas = new ArrayList<Carona>();
		emails = new TreeMap<String, String>();
		mensagens = new ArrayList<String>();
	}

	/**
	 * Método que cadastra uma nova carona criada pelo Usuario
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
	 *             Quando algum dos atributos do método possuir uma entrada
	 *             inválida
	 */
	public String cadastrarCarona(String origem, String destino, String data, String hora, String vagas) throws Exception {
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
	public String cadastrarCaronaMunicipal(String origem, String destino, String cidade, String data, String hora, String vagas) throws Exception{
		Carona novaCarona = new Carona(origem, destino, cidade, data, hora, vagas);
		novaCarona.setDono(this);
		String caronaID = String.valueOf(Math.abs(new Random().nextInt()));
		novaCarona.setID(caronaID);
		caronas.add(novaCarona);
		perfil.adicionaCarona(caronaID);
		return novaCarona.getID();
	}

	/**
	 * Método que retorna o identificador (id) do usuário
	 * 
	 * @return Identificador (id) do usuário
	 */
	public String getID() {
		return this.ID;
	}

	/**
	 * Método que muda o identificador (id) do Usuario
	 * 
	 * @param iD
	 *            Novo valor para o identificador (iD) do usuário
	 */
	public void setID(String iD) {
		this.ID = iD;
	}

	/**
	 * Método que retorna o login do usuário
	 * 
	 * @return Login do usuário
	 */
	public String getLogin() {
		return perfil.getLogin();
	}

	/**
	 * Método que retorna a senha do usuário
	 * 
	 * @return Senha do usuário
	 */
	public String getSenha() {
		return perfil.getSenha();
	}

	/**
	 * Método que retorna o nome do usuário
	 * 
	 * @return Nome do usuário
	 */
	public String getNome() {
		return perfil.getNome();
	}

	/**
	 * Método que retorna uma lista de caronas pertecentes ao usuário
	 * 
	 * @return Lista de caronas pertecentes ao usuário
	 */
	public List<Carona> getCaronas() {
		return caronas;
	}

	/**
	 * Método que retorna o email do usuário
	 * 
	 * @return Email do usuário
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
	 * Método que retorna o atributo login do usuário
	 * 
	 * @param login
	 *            Login do usuario
	 * @param atributo
	 *            Atributo solicitado no método
	 * @return Login cadastrado no perfil do usuário
	 */
	public String getAtributo(String atributo) throws Exception {
		return perfil.getAtributo(atributo);
	}

	/**
	 * Método que retorna o perfil do usiario, através de seu login
	 * 
	 * @param login
	 *            Login do usuário
	 * @return Perfil do usuário
	 * @throws Exception
	 *             Caso o login seja inválido
	 */
	public PerfilDoUsuario getPerfil(String login) throws Exception {
		if (!this.getLogin().equals(login))
			throw new Exception(Excecoes.LOGIN_INVALIDO);
		return perfil;
	}

	/**
	 * Método que adiciona uma nova carona
	 * 
	 * @param novaCarona
	 *            Nova carona a ser adicionada
	 */
	public void adicionaCarona(Carona novaCarona) {
		caronas.add(novaCarona);
	}

	/**
	 * Método que muda o histórico de vagas
	 * 
	 * @param carona
	 *            Carona que irá adicionar uma vaga
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
	 * 				O login de quem está pedindo para fazer parte desta carona
	 * @param review
	 * 				O review do dono da carona, que pode ser faltou, não faltou etc.
	 * @throws Exception
	 * 				Caso o review não seja nem "faltou", nem "não faltou".
	 * 			
	 */
	public void reviewVagaEmCarona(String idCarona, String loginCaroneiro, String review) throws Exception {
		if (review.equals("não dou mais carona")) {
			throw new Exception(Excecoes.OPCAO_INVALIDA);
		}

		if (review.equals("não funcionou")) {
			throw new Exception(Excecoes.USUARIO_NAO_VAGA_CARONA);
		}

		for (Carona carona : caronas) {
			if (carona.getID().equals(idCarona)) {
				for (Usuario caroneiro : carona.getTodosCaroneiros()) {
					if (caroneiro.getLogin().equals(loginCaroneiro)) {
						if (review.equals("faltou")) {
							caroneiro.setFaltasEmCaronas();
						}
						else if (review.equals("não faltou")) {
							caroneiro.setPresencasEmCaronas();
						}
					}
				}
			}
		}
	}

	/**
	 * Método que muda o número de faltas em caronas
	 */
	public void setFaltasEmCaronas() {
		perfil.setFaltaCaronas();
	}

	/**
	 * Método que muda o número de presenças em caronas
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
	
}