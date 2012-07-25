package sistema;

import gerenciadores.GerenciadorDeArquivos;
import gerenciadores.GerenciadorDeSolicitacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import componentesdosistema.AtributoIlegalException;
import componentesdosistema.Carona;
import componentesdosistema.DadosCaronaException;
import componentesdosistema.DadosUsuarioException;
import componentesdosistema.PerfilDoUsuario;
import componentesdosistema.Recomendacao;
import componentesdosistema.RecomendacaoPorHistorico;
import componentesdosistema.RecomendacaoPorLugarPopular;
import componentesdosistema.SessaoIlegalException;
import componentesdosistema.SolicitacaoDeCarona;
import componentesdosistema.SolicitacaoIlegalException;
import componentesdosistema.SugestaoDePonto;
import componentesdosistema.Usuario;



/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class Sistema {

	private List<Usuario> usuarios;
	private GerenciadorDeSolicitacoes gerenciadorDeSolicitacoes;
	private Map<String, String> interessesEmCaronas;

	/**
	 * Construtor da classe Sistema.
	 */
	public Sistema() {
		usuarios = new ArrayList<Usuario>();
		gerenciadorDeSolicitacoes = new GerenciadorDeSolicitacoes();
		interessesEmCaronas = new TreeMap<String, String>();
	}

	/**
	 * Abre uma sessão para um usuário logado.
	 * 
	 * @param login
	 * 			O login do usuário.
	 * @param senha
	 * 			A senha do usuário.
	 * @return Um identificador (id) do usuário logado com sucesso.
	 * @throws Exception
	 * 			Caso o login seja seja nulo, vazio ou já exista e caso a senha seja nula, vazia ou já exista.
	 */
	public String abrirSessao(String login, String senha) throws DadosUsuarioException, SessaoIlegalException {
		Usuario usuario = checaLoginValido(login, senha);
		
		if(usuario != null){
			String idSessao = String.valueOf(Math.abs(new Random().nextInt()));
			while(!checaSessaoValida(idSessao)){
				idSessao = String.valueOf(Math.abs(new Random().nextInt()));
			}
			usuario.setID(idSessao);
			return idSessao;
		}
		else{
			throw new DadosUsuarioException("Usuário inexistente");
		}
	}
	
	/**
	 * Verifica se uma sessao é valida ou não.
	 * 
	 * @param idSessao
	 * 			Um identificador (id) da sessão a ser checada.
	 * @return True se o identificador (id) da sessão checada é válida, False caso contrário.
	 * @throws Exception
	 * 			Caso o identificador da sessão a ser verificada seja nulo ou vazio.
	 */
	private boolean checaSessaoValida(String idSessao) throws SessaoIlegalException{
		if(idSessao == null || idSessao.equals("")){
			throw new SessaoIlegalException("Sessão inválida");
		}
		
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se o login de um usuário é válido.
	 * 
	 * @param login
	 * 			O login do usuário.
	 * @param senha
	 * 			A senha do usuário.
	 * @return O usuário confirmado pelo seu login e senha, ou nulo caso contrário.
	 * @throws Exception
	 * 			Caso o login seja nulo, vazio ou já exista, ou caso a senha seja nula, vazia ou já exista.
	 */
	public Usuario checaLoginValido(String login, String senha) throws DadosUsuarioException {
		if (login == null || login.equals("")) {
			throw new DadosUsuarioException("Login inválido");
		}
		if (senha == null || senha.equals("")) {
			throw new DadosUsuarioException("Senha inválida");
		}
		
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				if (usuario.getSenha().equals(senha)) {
					return usuario;
				}
				else{
					throw new DadosUsuarioException("Login inválido");
				}
			}
		}
		
		return null;
	}

	/**
	 * Cria um usuário no sistema.
	 * 
	 * @param login
	 *            O login do usuário.
	 * @param senha
	 *            A senha do usuário.
	 * @param nome
	 *            O nome do usuário.
	 * @param endereco
	 *            O endereço do usuário.
	 * @param email
	 *            O email do usuário.
	 * @throws Exception
	 *             Caso qualquer dos atributos seja passado de forma não-válida.
	 */
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws DadosUsuarioException {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				throw new DadosUsuarioException("Já existe um usuário com este login");
			} 
			else if (usuario.getEmail().equals(email)) {
				throw new DadosUsuarioException("Já existe um usuário com este email");
			}
		}
		usuarios.add(new Usuario(login, senha, nome, endereco, email));
	}

	/**
	 * Método que retorna o atributo login do usuário
	 * 
	 * @param login
	 *            Login do usuario
	 * @param atributo
	 *            Atributo solicitado no método
	 * @return Login cadastrado no perfil do usuário
	 * @throws Exception
	 *             Caso o login seja inválido
	 */
	public String getAtributoUsuario(String login, String atributo)	throws AtributoIlegalException, DadosUsuarioException {
		if (login == null || login.equals(""))
			throw new DadosUsuarioException("Login inválido");
		
		if (atributo == null || atributo.equals(""))
			throw new AtributoIlegalException("Atributo inválido");
		
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login))
				return usuario.getAtributo(atributo);
		}
		throw new DadosUsuarioException("Usuário inexistente");
	}
	
	/**
	 * Método que visualiza o perfil cadastrado do usuáro
	 * 
	 * @param idSessao
	 *            Identificador (id) do usuário cadastrado
	 * @param login
	 *            Login do usuário
	 * @return Perfil do usuário
	 * @throws Exception
	 */
	public PerfilDoUsuario visualizarPerfil(String idSessao, String login) throws Exception {
		Usuario user = buscaUsuario(idSessao);
		return user.getPerfil(login);
	}

	/**
	 * Método que fornece o trajeto da carona
	 * 
	 * @param IDCarona
	 *            Identificador (id) da carona
	 * @return O trajeto da carona
	 * @throws Exception
	 *             Caso o trajeto seja inválido ou inexistente
	 */
	public String getTrajeto(String IDCarona) throws DadosCaronaException {
		Carona carona = buscaCarona(IDCarona);
		if (IDCarona == null)
			throw new DadosCaronaException("Trajeto Inválida");
		if (carona == null)
			throw new DadosCaronaException("Trajeto Inexistente");
		return carona.getTrajeto();
	}

	/**
	 * Método que retorna o atributo identificador da carona
	 * 
	 * @param idCarona
	 *            Identificador da carona
	 * @param atributo
	 *            Atributo solicitado no método
	 * @return Identificador (id) da carona
	 * @throws Exception
	 *             Caso o atributo seja inválido ou identificador seja inválido
	 */
	public String getAtributoCarona(String IDCarona, String atributo) throws AtributoIlegalException, DadosCaronaException {
		if (IDCarona == null || IDCarona.equals("")) {
			throw new DadosCaronaException("Identificador do carona é inválido");
		}
		if (atributo == null || atributo.equals("")) {
			throw new AtributoIlegalException("Atributo inválido");
		}
		
		Carona carona = buscaCarona(IDCarona);
		if (carona != null)
			return carona.getAtributoCarona(atributo);
		
		throw new DadosCaronaException("Item inexistente");
	}

	/**
	 * Método que busca uma crona pelo seu identificador (id)
	 * 
	 * @param IDCarona
	 *            Identificador da carona
	 * @return Carona do respectivo identificador(id)
	 */
	private Carona buscaCarona(String IDCarona) {
		for (Usuario usuario : usuarios) {
			for (Carona carona : usuario.getCaronas()) {
				if (carona.getID().equals(IDCarona))
					return carona;
			}
		}
		return null;
	}

	/**
	 * Método que busca o dono de uma carona específica
	 * 
	 * @param idCarona
	 *            Carona específica que possui seu identidficador idCarona
	 * @return Usuario dono da carona
	 */
	private Usuario buscaDonoDeCarona(String idCarona) {
		for (Usuario usuario : usuarios) {
			for (Carona carona : usuario.getCaronas()) {
				if (carona.getID().equals(idCarona))
					return usuario;
			}
		}
		return null;
	}

	/**
	 * Método que retorna uma carona através de seu identificador
	 * 
	 * @param IDCarona
	 *            Identificador da carona a ser retornada
	 * @return Carona
	 * @throws Exception
	 *             Caso o identificador da carona ou a carona seja nulo
	 */
	public String getCarona(String IDCarona) throws DadosCaronaException {
		Carona carona = buscaCarona(IDCarona);
		if (IDCarona == null){
			throw new DadosCaronaException("Carona Inválida");
		}
		if (carona == null){
			throw new DadosCaronaException("Carona Inexistente");
		}
		return carona.toString();
	}

	/**
	 * Método que pesquisa e retorna uma carona através do identificador do
	 * usuário, a rigem e o destino da carona
	 * 
	 * @param idSessao
	 *            Identificador (id) do usuario logado
	 * @param origem
	 *            Origem da carona
	 * @param destino
	 *            Destino da carona
	 * @return String contendo todas as caronas
	 * @throws Exception
	 *             Caso a origem ou o destino sejam inválidos
	 */
	public String localizarCarona(String idSessao, String origem, String destino)
			throws Exception {
		if (origem == null 	|| !origem.matches("^[ a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü0-9]*$")) {
			throw new DadosCaronaException("Origem inválida");
		}
		if (destino == null || !destino	.matches("^[ a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü0-9]*$")) {
			throw new DadosCaronaException("Destino inválido");
		}
		
		String todasCaronas = "{";
		for(Carona carona: localizarCarona(origem, destino)){
			todasCaronas += carona.getID() + ",";
	
		}
		return (todasCaronas + "}").replace(",}", "}");
	}

	public List<Carona> localizarCarona(String origem, String destino)throws Exception {
		List<Carona> caronasEncontradas = new ArrayList<Carona>();
		for (Usuario usuario : usuarios) {
			for (Carona carona : usuario.getCaronas()) {
			if (origem.equals("") && destino.equals("")) {
				caronasEncontradas.add(carona);
			} else if (carona.getOrigem().equals(origem)
					&& destino.equals("")) {
				caronasEncontradas.add(carona);
			} else if (origem.equals("")
					&& carona.getDestino().equals(destino)) {
				caronasEncontradas.add(carona);
			} else if (carona.getOrigem().equals(origem)
					&& carona.getDestino().equals(destino)) {
				caronasEncontradas.add(carona);
			}
			}
		}
		return caronasEncontradas;
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param cidade
	 * @param origem
	 * @param destino
	 * @return
	 * @throws Exception
	 */
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino) throws DadosCaronaException{
		 if(cidade == null || cidade.equals("")){
			 throw new DadosCaronaException("Cidade inexistente");
		 }
		 
		 String todasCaronas = "{";
		 for(Usuario usuario : usuarios){
			 if(usuario.getID().equals(idSessao)){
				 for(Usuario outro_usuario : usuarios){
					 for(Carona carona : outro_usuario.getCaronas()){
						 if(carona.getCidade() != null && carona.getCidade().equals(cidade)){
							 if (origem.equals("") && destino.equals("")) {
								 todasCaronas += carona.getID() + ",";
							 }
							 if (carona.getOrigem().equals(origem) && destino.equals("")) {
								 todasCaronas += carona.getID() + ",";
							 }
							 else if (origem.equals("") 	&& carona.getDestino().equals(destino)) {
								 todasCaronas += carona.getID() + ",";
							 }
							 else if (carona.getOrigem().equals(origem) 	&& carona.getDestino().equals(destino)) {
								 todasCaronas += carona.getID() + ",";
							 }
						 }
					 }
				 }
			 }
		 }
		 
		 return (todasCaronas + "}").replace(",}", "}");
	 }
	 
	/**
	 * 
	 * @param idSessao
	 * @param cidade
	 * @return
	 * @throws Exception
	 */
	 public String localizarCaronaMunicipal(String idSessao, String cidade) throws Exception{
		 return localizarCaronaMunicipal(idSessao, cidade, "", "");
	 }

	/**
	 * Método que cadastra uma carona
	 * 
	 * @param idSessao
	 *            Identificador (id) do usuário logado
	 * @param origem
	 *            Origem da carona
	 * @param destino
	 *            Destino da carona
	 * @param data
	 *            Data da carona
	 * @param hora
	 *            Hora da carona
	 * @param vagas
	 *            Número de vagas da carona
	 * @return usuario cadastrado
	 * @throws Exception
	 *             Caso haja problemas com o identificador (id) do usuário
	 */
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String vagas) throws DadosUsuarioException, SessaoIlegalException, DadosCaronaException {
		if (idSessao == null || idSessao.equals(""))
			throw new SessaoIlegalException("Sessão inválida");
		
		if (buscaUsuario(idSessao) == null)
			throw new SessaoIlegalException("Sessão inexistente");
		
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(idSessao)) {
				checaInteresseNaCarona(origem, destino, data, hora, vagas);
				return usuario.cadastrarCarona(origem, destino, data, hora, vagas);
			}
		}
		throw new DadosUsuarioException("Usuário inexistente");
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return
	 * @throws Exception
	 */
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, String data, String hora, String vagas) throws DadosUsuarioException, SessaoIlegalException, DadosCaronaException{
		 if (idSessao == null || idSessao.equals("")){
			 throw new SessaoIlegalException("Sessão inválida");
		 }
		 
		 if (buscaUsuario(idSessao) == null){
			 throw new SessaoIlegalException("Sessão inexistente");
		 }
		 
		 if(cidade == null || cidade.equals("")){
			 throw new DadosCaronaException("Cidade inexistente");
		 }
			
		 for(Usuario usuario : usuarios){
			 if(usuario.getID().equals(idSessao)){
				 checaInteresseNaCarona(origem, destino, data, hora, vagas);
				 return usuario.cadastrarCaronaMunicipal(origem, destino, cidade, data, hora, vagas);
			 }
		 }
		 
		 throw new DadosUsuarioException("Usuário inexistente");
	 }
	
	public void checaInteresseNaCarona(String origem, String destino, String data, String hora, String vagas) throws DadosCaronaException{
		for(String idInteressado: interessesEmCaronas.keySet()){
			if(interessesEmCaronas.get(idInteressado).split(";")[0].equals(origem)){
				if(interessesEmCaronas.get(idInteressado).split(";")[1].equals(destino)){
					if(interessesEmCaronas.get(idInteressado).split(";")[2].equals(data) || interessesEmCaronas.get(idInteressado).split(";")[2].equals("")){
						if(interessesEmCaronas.get(idInteressado).split(";")[3].equals(hora) || interessesEmCaronas.get(idInteressado).split(";")[4].equals(hora)){
							notificaInteressados(idInteressado, new Carona(origem, destino, data, hora, vagas));
						}
					}
				}
			}
		}
	}
	
	public void notificaInteressados(String idInteressado, Carona carona){
		for(Usuario usuario : usuarios){
			if(usuario.getIdInteresse().equals(idInteressado)){
				System.out.println(carona.getDono());
				usuario.notificaCaronaPublicada(carona);
			}
		}
	}

	/**
	 * Método que busca um usuário numa carona
	 * 
	 * @param IDCarona
	 *            Identificador (id) da carona
	 * @return O usuário pertencente a uma carona específica
	 */
	private Usuario buscaUsuario(String idSessao) {
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(idSessao))
				return usuario;
		}
		return null;
	}

	/**
	 * Método que sugere um ponto de encontro para partida da carona
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @param pontosDeEncontro
	 *            Lista de pontos de encontros possíveis
	 * @return Pontos de encontro possíveis
	 * @throws Exception
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontosDeEncontro) throws Exception {
		Carona carona = buscaCarona(idCarona);
		Usuario user = buscaUsuario(idSessao);
		return gerenciadorDeSolicitacoes.sugerirPontoEncontro(user, carona, pontosDeEncontro);
	}

	/**
	 * Metodo que responde a uma sugestao de ponto de Encontro
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @param idSugestao
	 *            Idetificador da sugestao de ponto de encontro
	 * @param novosPontos
	 *            Novos pontos de encontro a serem dados como resposta
	 * @throws Exception
	 */
	public void responderSugestaoPontoEncontro(String idSessao,String idCarona, String idSugestao, String novosPontos)	throws Exception {
		SugestaoDePonto sugestaoDePontos = gerenciadorDeSolicitacoes.buscaSugestao(idSugestao);
		Carona carona = buscaCarona(idCarona);
		gerenciadorDeSolicitacoes.responderSugestao(carona, sugestaoDePontos, novosPontos);
	}

	/**
	 * Metodo que encerra a sessao atual
	 * 
	 * @param login
	 *            Login do usuario que esta na sessao
	 * @throws Exception
	 *             Caso o login seja vazio ou uma String nula
	 */
	public void encerrarSessao(String login) throws DadosUsuarioException {
		if (login == null || login.equals("")) {
			throw new DadosUsuarioException("Login inválido");
		}
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				if (!usuario.getID().equals(""))
					usuario.setID("");
			}
		}
	}

	/**
	 * Metodo que solicita uma vaga em um determinado ponto de encontro
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @param pontoDeEncontro
	 *            Ponto de encontro em que vai ser solicitada a vaga
	 * @return Identificador da solicitacao de vaga
	 * @throws Exception
	 *             Caso o ponto de encontro seja invalido
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, 	String pontoDeEncontro) throws Exception {
		Usuario donoDaCarona = buscaDonoDeCarona(idCarona);
		Usuario solcitador = buscaUsuario(idSessao);
		Carona carona = buscaCarona(idCarona);
		return gerenciadorDeSolicitacoes.solicitarVaga(carona, donoDaCarona.getNome(), solcitador, pontoDeEncontro);
	}

	/**
	 * Metodo para solicitar vaga em uma carona
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @return Identificador da carona em que foi solicitada a vaga
	 * @throws SolicitacaoIlegalException 
	 */
	public String solicitarVaga(String idSessao, String idCarona) throws SolicitacaoIlegalException {
		Usuario donoDaCarona = buscaDonoDeCarona(idCarona);
		Usuario solcitador = buscaUsuario(idSessao);
		Carona carona = buscaCarona(idCarona);
		return gerenciadorDeSolicitacoes.solicitarVaga(carona, donoDaCarona.getNome(), solcitador);
	}

	/**
	 * Metodo que retorna um atributo de solicitacao de uma carona
	 * 
	 * @param idSolicitacao
	 *            Identificador da solicitacao de carona
	 * @param atributo
	 *            Atributo a ser procurado
	 * @return Atributo Desejado pelo usuario
	 * @throws Exception
	 *             Caso o atributo nao exista
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo)	throws Exception {
		SolicitacaoDeCarona solicitacao = gerenciadorDeSolicitacoes.buscaSolicitacao(idSolicitacao);
		return solicitacao.getAtributo(idSolicitacao, atributo);
	}
	/**
	 * Metodo que aceita uma solicitacao em um ponto de encontro
	 * 
	 * @param idSessao
	 *            Identificador no usuÃ¡rio que sugere o ponto de encontro
	 * @param idSolicitacao
	 *            Identificador da solicitacao
	 * @throws NumberFormatException
	 *             Caso o id da solicitacao seja invalido
	 * @throws Exception
	 *             Caso a solicitacao nao exista
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws NumberFormatException, Exception {
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				if(!gerenciadorDeSolicitacoes.getSolicitacoesConfirmadas().matches(idSolicitacao)){
					gerenciadorDeSolicitacoes.validaSolicitacao(idSolicitacao);
					usuario.setHistoricoEmVagas(gerenciadorDeSolicitacoes.getSolicitacaoConfirmada(idSolicitacao).getCarona());
				}
				else{
					throw new SolicitacaoIlegalException("Solicitação inexistente");
				}
			}
		}
	}
	/**
	 * Metodo que desiste de uma requisicao ja feita
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @param idSugestao
	 *            Sugestao de ponto de encontro a ser desistida
	 */
	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao) {
		gerenciadorDeSolicitacoes.desistirRequisicao(idSessao, idCarona, idSugestao);
	}

	/**
	 * Metodo que aceita uma solicitacao de carona
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idSolicitacao
	 *            Identificador da solicitacao de carona
	 * @throws NumberFormatException
	 *             Caso o id de solicitacao seja invalido
	 * @throws Exception
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws NumberFormatException, SolicitacaoIlegalException, DadosCaronaException, Exception {
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				if(!gerenciadorDeSolicitacoes.getSolicitacoesConfirmadas().matches(idSolicitacao)){
					gerenciadorDeSolicitacoes.validaSolicitacao(idSolicitacao);
				}
				else{
					throw new SolicitacaoIlegalException("Solicitação inexistente");
				}
			}
		}
	}

	/**
	 * Metodo que rejeita uma solicitacao de carona
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idSolicitacao
	 *            Identificador da solicitacao de carona
	 * @throws Exception
	 *             Caso o ponto de encontro seja invalido
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception {
		gerenciadorDeSolicitacoes.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	/**
	 * Metodo que reinicia o sistema, e recupera todos os dados gravados
	 * 
	 * @throws Exception
	 *             Caso o sistema nao consiga ser reiniciado
	 */
	public void reiniciar() throws Exception {
		usuarios = GerenciadorDeArquivos.recuperaUsuarios();
	}

	/**
	 * Metodo que retorna uma carona de um determinado usuario
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param indexCarona
	 *            O index da carona a ser retornada
	 * @return Carona do usuario determinado
	 */
	public String getCaronaUsuario(String idSessao, String indexCarona) {
		Usuario usuario = buscaUsuario(idSessao);
		Carona carona = usuario.getCaronas().get(Integer.parseInt(indexCarona) - 1);
		return carona.getID();
	}

	/**
	 * Metodo que retorna todas as caronas de um usuario
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @return Todas as ID das caronas do usuario
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		String todasCaronas = "{";
		for (Usuario u : usuarios) {
			if (u.getID().equals(idSessao)) {
				for (Carona c : u.getCaronas()) {
					todasCaronas += c.getID() + ",";
				}
			}
		}
		return (todasCaronas + "}").replace(",}", "}");
	}

	/**
	 * Metodo que retorna as solicitacoes pendentes referentes a determinada carona
	 * 
	 * @param idCarona
	 * 			Identificador da carona
	 * @return
	 * 			Todas as solicitacoes pendentes referentes ao identificador da carona passada como argumento
	 * @throws Exception
	 * 			Caso o identificador da carona seja nulo ou vazio
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona) throws Exception {
		
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				for(Carona carona : usuario.getCaronas()){
					if(carona.getID().equals(idCarona)){
						return gerenciadorDeSolicitacoes.getSolicitacaoPendente(idCarona);
					}
				}
			}
		}
		
		return "{}";
	}
	

	/**
	 * Metodo que retorna as solicitacoes confirmadas de uma determinada carona de um determinado usuario
	 * 
	 * @param idSessao
	 * 			O identificador do usuario dono da carona
	 * @param idCarona
	 * 			O identificador da carona do usuario
	 * @return
	 * 			As solicitacoes que foram confirmadas para a carona do usuario
	 * @throws Exception
	 * 			Caso ou identificador da sessao ou o identificador da carona seja nulo ou vazio
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona)	throws Exception {
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				for(Carona carona : usuario.getCaronas()){
					if(carona.getID().equals(idCarona)){
						return "{" + gerenciadorDeSolicitacoes.getSolicitacaoCaronaConfirmada(idCarona) + "}";
					}
				}
			}
		}
		return "{}";
	}

	/**
	 * Metodo que encerra o sistema e salva os dados no arquivo XML
	 * 
	 * @throws Exception
	 *             Caso haja erro na operacao
	 */
	public void encerrar() throws Exception {
		GerenciadorDeArquivos.salvaUsuariosXML(usuarios);
	}

	/**
	 * Metodo que diz como foi o funcionamento de uma carona. Se ele funcionou
	 * ou nao, ou se nao da mais carona a este usuario
	 * 
	 * @param idSessao
	 *            Identificador no usuário que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @param loginCaroneiro
	 *            Login da pessoa que pegou a carona
	 * @param review
	 *            Dados sobre a carona
	 * @return Informacoes sobre a carona realizada
	 * @throws Exception
	 *             Caso a carona seja invalida
	 */
	public String reviewVagaEmCarona(String idSessao, String idCarona, 	String loginCaroneiro, String review) throws Exception {
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(idSessao)) {
				usuario.reviewVagaEmCarona(idCarona, loginCaroneiro, review);
			}
		}
		return "";
	}

	/**
	 * Metodo que retorna os pontos sugeridos para determinada carona de um determinado usuario
	 * 
	 * @param idSessao
	 * 				O identificador do usuario dono da carona
	 * @param idCarona
	 * 				O identificador da carona do usuario
	 * @return
	 * 				Todos os pontos sugeridos para a carona
	 */
	public String getPontosSugeridos(String idSessao, String idCarona) {
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				for(Carona carona : usuario.getCaronas()){
					if(carona.getID().equals(idCarona)){
						return gerenciadorDeSolicitacoes.buscaSugestaoPorCarona(idCarona).getTodosPontosSugeridos();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Metodo que retorna os pontos de encontro para uma determinada carona de um determinado usuario
	 * 
	 * @param idSessao
	 * 			O identificador do usuario dono da carona
	 * @param idCarona
	 * 			O identificador da carona do usuario
	 * @return
	 * 			Os pontos de encontro para uma determinada carona
	 */
	public String getPontosEncontro(String idSessao, String idCarona){
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				for(Carona carona : usuario.getCaronas()){
					if(carona.getID().equals(idCarona)){
						return gerenciadorDeSolicitacoes.buscaSugestaoPorCarona(idCarona).getTodosPontosSugeridos();
					}
				}
			}
		}
		return null;
	}
	
	 public void reviewCarona(String idSessao, String idCarona, String review) throws DadosCaronaException{
		 for(Usuario caroneiro : usuarios){
			 if(caroneiro.getID().equals(idSessao)){
				 for(Usuario usuario : usuarios){
					 for(Carona carona : usuario.getCaronas()){
						 if(carona.getID().equals(idCarona)){
							 if(carona.getCaroneiros().contains(caroneiro.getNome())){
								 if(review.equals("segura e tranquila")){
									 carona.getDono().setCaronasSeguras();
								 }
								 else if(review.equals("não funcionou")){
									 carona.getDono().setCaronasNaoFuncionaram();
								 }
								 else{
									 throw new DadosCaronaException("Opção inválida.");
								 }
							 }
							 else{
								 throw new DadosCaronaException("Usuário não possui vaga na carona.");
							 }
						 }
					 }
				 }
			 }
		 }
	 }
	 
	 public String cadastrarInteresse (String idSessao, String origem, String destino, String data, String horaInicio, String horaFim) throws DadosCaronaException, DadosUsuarioException{
		 if(origem == null 	|| !origem.matches("^[ a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü0-9]*$")) {
			 throw new DadosCaronaException("Origem inválida");
		 }
		 
		 if(destino == null || !destino	.matches("^[ a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü0-9]*$")) {
			 throw new DadosCaronaException("Destino inválido");
		 }
		 
		 if(data == null){
			 throw new DadosCaronaException("Data inválida");
		 }
		 
		 for(Usuario usuario : usuarios){
			 if(usuario.getID().equals(idSessao)){
				 String idInteresse = String.valueOf(Math.abs(new Random().nextInt()));
				 while(!checaIdInteresseValido(idInteresse)){
					 idInteresse = String.valueOf(Math.abs(new Random().nextInt()));
				 }
				 String caronaInteresse = origem + ";" + destino + ";" + data + ";" + horaInicio + ";" + horaFim;
				 interessesEmCaronas.put(idInteresse, caronaInteresse);
				 usuario.setIDInteresse(idInteresse);
				 return idInteresse;
			 }
		 }
		 
		 throw new DadosUsuarioException("Usuário inexistente"); 
	 }
	 
	 /**
	  * 
	  * @param idInteresse
	  * @return
	  * @throws Exception
	  */
	 public boolean checaIdInteresseValido(String idInteresse) throws DadosCaronaException{
		 if(idInteresse == null || idInteresse.equals("")){
			 throw new DadosCaronaException("Identificador de interesse é inválido");
		 }
		 
		 if(interessesEmCaronas.containsKey(idInteresse)){
			 return false;
		 }
		 
		 return true;
	 }
	 
	 /**
	  * 
	  * @param idSessao
	  * @return
	  * @throws Exception
	  */
	 public String verificarMensagensPerfil(String idSessao) throws DadosUsuarioException{
		 for(Usuario usuario : usuarios){
			 if(usuario.getID().equals(idSessao)){
				 return usuario.verificarMensagensPerfil();
			 }
		 }
		 
		 throw new DadosUsuarioException("Usuário inexistente");
	 }
	 
	 public String enviarEmail(String idSessao, String destino, String message) throws DadosUsuarioException{
		 for(Usuario usuario : usuarios){
			 if(usuario.getID().equals(idSessao)){
				 return usuario.enviarEmail(usuario.getEmail(), destino, message);
			 }
		 }
		 
		 throw new DadosUsuarioException("Usuário inexistente");
	 }
	 public List<Usuario> getUsuarios() {
			return usuarios;
		}
	 public List<SolicitacaoDeCarona> getSolicitacoesPendentes(String login) {
		 return gerenciadorDeSolicitacoes.getSolicitacoesPendentes(login);
	 }
		public String verRecomendacoesPorLugarPopular(String idSessao){
			try {
				Recomendacao recomendacao;
				recomendacao = new RecomendacaoPorLugarPopular(buscaUsuario(idSessao), localizarCarona("", ""));
				String recomendacoes = "{";
				for (Carona c: recomendacao.gerarRecomendacoes())
					recomendacoes+= c.getID()+",";
				return (recomendacoes+"}").replace(",}", "}");
			} catch (Exception e) {
				e.printStackTrace();
				return "{}";
			}
			
		}
		public String verRecomendacoesPorHisorico(String idSessao){
			try {
				Recomendacao recomendacao;
				recomendacao = new RecomendacaoPorHistorico(buscaUsuario(idSessao), localizarCarona("", ""));
				String recomendacoes = "{";
				for (Carona c: recomendacao.gerarRecomendacoes())
					recomendacoes+= c.getID()+",";
				return (recomendacoes+"}").replace(",}", "}");
			} catch (Exception e) {
				e.printStackTrace();
				return "{}";
			}
			
		}
}