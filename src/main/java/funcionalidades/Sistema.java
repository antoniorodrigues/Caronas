package funcionalidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import excecoes.Excecoes;

import gerenciadores.GerenciaDadosEmXML;
import gerenciadores.GerenciadorDeSolicitacoes;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class Sistema {

	private List<Usuario> usuarios;
	private GerenciadorDeSolicitacoes gerenciadorDeSolicitacoes;
	private GerenciaDadosEmXML gerenciadorDeDadosEmXML;

	/**
	 * Construtor da classe Sistema.
	 */
	public Sistema() {
		usuarios = new ArrayList<Usuario>();
		gerenciadorDeSolicitacoes = new GerenciadorDeSolicitacoes();
		gerenciadorDeDadosEmXML = new GerenciaDadosEmXML();
	}

	/**
	 * Abre uma sess„o para um usu·rio logado.
	 * 
	 * @param login
	 * 			O login do usu·rio.
	 * @param senha
	 * 			A senha do usu·rio.
	 * @return Um identificador (id) do usu·rio logado com sucesso.
	 * @throws Exception
	 * 			Caso o login seja seja nulo, vazio ou j· exista e caso a senha seja nula, vazia ou j· exista.
	 */
	public String abrirSessao(String login, String senha) throws Exception {
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
			throw new Exception(Excecoes.USUARIO_INEXISTENTE);
		}
	}
	
	/**
	 * Verifica se uma sessao È valida ou n„o.
	 * 
	 * @param idSessao
	 * 			Um identificador (id) da sess„o a ser checada.
	 * @return True se o identificador (id) da sess„o checada È v·lida, False caso contr·rio.
	 * @throws Exception
	 * 			Caso o identificador da sess„o a ser verificada seja nulo ou vazio.
	 */
	private boolean checaSessaoValida(String idSessao) throws Exception{
		if(idSessao == null || idSessao.equals("")){
			throw new Exception(Excecoes.SESSAO_INVALIDA);
		}
		
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se o login de um usu·rio È v·lido.
	 * 
	 * @param login
	 * 			O login do usu·rio.
	 * @param senha
	 * 			A senha do usu·rio.
	 * @return O usu·rio confirmado pelo seu login e senha, ou nulo caso contr·rio.
	 * @throws Exception
	 * 			Caso o login seja nulo, vazio ou j· exista, ou caso a senha seja nula, vazia ou j· exista.
	 */
	public Usuario checaLoginValido(String login, String senha) throws Exception {
		if (login == null || login.equals("")) {
			throw new Exception(Excecoes.LOGIN_INVALIDO);
		}
		if (senha == null || senha.equals("")) {
			throw new Exception(Excecoes.SENHA_INVALIDA);
		}
		
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				if (usuario.getSenha().equals(senha)) {
					return usuario;
				}
				else{
					throw new Exception(Excecoes.LOGIN_INVALIDO);
				}
			}
		}
		return null;
	}

	/**
	 * Cria um usu·rio no sistema.
	 * 
	 * @param login
	 *            O login do usu·rio.
	 * @param senha
	 *            A senha do usu·rio.
	 * @param nome
	 *            O nome do usu·rio.
	 * @param endereco
	 *            O endereÁo do usu·rio.
	 * @param email
	 *            O email do usu·rio.
	 * @throws Exception
	 *             Caso qualquer dos atributos seja passado de forma n„o v·lida.
	 */
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				throw new Exception(Excecoes.LOGIN_DUPLICADO);
			} 
			else if (usuario.getEmail().equals(email)) {
				throw new Exception(Excecoes.EMAIL_DUPLICADO);
			}
		}
		usuarios.add(new Usuario(login, senha, nome, endereco, email));
	}

	/**
	 * M√©todo que retorna o atributo login do usu√°rio
	 * 
	 * @param login
	 *            Login do usuario
	 * @param atributo
	 *            Atributo solicitado no m√©todo
	 * @return Login cadastrado no perfil do usu√°rio
	 * @throws Exception
	 *             Caso o login seja inv√°lido
	 */
	public String getAtributoUsuario(String login, String atributo)	throws Exception {
		return getAtributoPerfil(login, atributo);
	}

	/**
	 * M√©todo que retorna o atributo perfil do usu√°rio
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param atributo
	 *            Atributo solicitado no m√©todo
	 * @return Login cadastrado no perfil do usu√°rio
	 * @throws Exception
	 *             Caso o login seja inv√°lido
	 */
	public String getAtributoPerfil(String login, String atributo)throws Exception {
		if (login == null || login.equals(""))
			throw new Exception(Excecoes.LOGIN_INVALIDO);
		
		if (atributo == null || atributo.equals(""))
			throw new Exception(Excecoes.ATRIBUTO_INVALIDO);
		
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login))
				return usuario.getAtributo(login, atributo);
		}
		throw new Exception(Excecoes.USUARIO_INEXISTENTE);
	}

	/**
	 * M√©todo que visualiza o perfil cadastrado do usu√°ro
	 * 
	 * @param idSessao
	 *            Identificador (id) do usu√°rio cadastrado
	 * @param login
	 *            Login do usu√°rio
	 * @return Perfil do usu√°rio
	 * @throws Exception
	 */
	public PerfilDoUsuario visualizarPerfil(String idSessao, String login) throws Exception {
		Usuario user = buscaUsuario(idSessao);
		return user.getPerfil(login);
	}

	/**
	 * M√©todo que fornece o trajeto da carona
	 * 
	 * @param IDCarona
	 *            Identificador (id) da carona
	 * @return O trajeto da carona
	 * @throws Exception
	 *             Caso o trajeto seja inv√°lido ou inexistente
	 */
	public String getTrajeto(String IDCarona) throws Exception {
		Carona carona = buscaCarona(IDCarona);
		if (IDCarona == null)
			throw new Exception(Excecoes.TRAJETO_INVALIDO);
		if (carona == null)
			throw new Exception(Excecoes.TRAJETO_INEXISTENTE);
		return carona.getTrajeto();
	}

	/**
	 * M√©todo que retorna o atributo identificador da carona
	 * 
	 * @param idCarona
	 *            Identificador da carona
	 * @param atributo
	 *            Atributo solicitado no m√©todo
	 * @return Identificador (id) da carona
	 * @throws Exception
	 *             Caso o atributo seja inv√°lido ou identificador seja inv√°lido
	 */
	public String getAtributoCarona(String IDCarona, String atributo) throws Exception {
		if (IDCarona == null || IDCarona.equals("")) {
			throw new Exception(Excecoes.IDENTIFICADOR_CARONA_INVALIDO);
		}
		if (atributo == null || atributo.equals("")) {
			throw new Exception(Excecoes.ATRIBUTO_INVALIDO);
		}
		
		Carona carona = buscaCarona(IDCarona);
		if (carona != null)
			return carona.getAtributoCarona(atributo);
		
		throw new Exception(Excecoes.ITEM_INEXISTENTE);
	}

	/**
	 * M√©todo que busca uma crona pelo seu identificador (id)
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
	 * M√©todo que busca o dono de uma carona espec√≠fica
	 * 
	 * @param idCarona
	 *            Carona espec√≠fica que possui seu identidficador idCarona
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
	 * M√©todo que retorna uma carona atrav√©s de seu identificador
	 * 
	 * @param IDCarona
	 *            Identificador da carona a ser retornada
	 * @return Carona
	 * @throws Exception
	 *             Caso o identificador da carona ou a carona seja nulo
	 */
	public String getCarona(String IDCarona) throws Exception {
		Carona carona = buscaCarona(IDCarona);
		if (IDCarona == null)
			throw new Exception(Excecoes.CARONA_INVALIDA);
		if (carona == null)
			throw new Exception(Excecoes.CARONA_INEXISTENTE);
		return carona.toString();
	}

	/**
	 * M√©todo que pesquisa e retorna uma carona atrav√©s do identificador do
	 * usu√°rio, a rigem e o destino da carona
	 * 
	 * @param idSessao
	 *            Identificador (id) do usuario logado
	 * @param origem
	 *            Origem da carona
	 * @param destino
	 *            Destino da carona
	 * @return String contendo todas as caronas
	 * @throws Exception
	 *             Caso a origem ou o destino sejam inv√°lidos
	 */
	public String localizarCarona(String idSessao, String origem, String destino)throws Exception {
		if (origem == null 	|| !origem.matches("^[ a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸0-9]*$")) {
			throw new Exception(Excecoes.ORIGEM_INVALIDA);
		}
		if (destino == null || !destino	.matches("^[ a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸0-9]*$")) {
			throw new Exception(Excecoes.DESTINO_INVALIDO);
		}
		
		String todasCaronas = "{";
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(idSessao)) {
				for (Carona carona : usuario.getCaronas()) {
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
		return (todasCaronas + "}").replace(",}", "}");
	}

	/**
	 * M√©todo que cadastra uma carona
	 * 
	 * @param idSessao
	 *            Identificador (id) do usu√°rio logado
	 * @param origem
	 *            Origem da carona
	 * @param destino
	 *            Destino da carona
	 * @param data
	 *            Data da carona
	 * @param hora
	 *            Hora da carona
	 * @param vagas
	 *            N√∫mero de vagas da carona
	 * @return usuario cadastrado
	 * @throws Exception
	 *             Caso haja problemas com o identificador (id) do usu√°rio
	 */
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String vagas) throws Exception {
		if (idSessao == null || idSessao.equals(""))
			throw new Exception(Excecoes.SESSAO_INVALIDA);
		if (buscaUsuario(idSessao) == null)
			throw new Exception(Excecoes.SESSAO_INEXISTENTE);

		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(idSessao)) {
				return usuario.cadastrarCarona(origem, destino, data, hora, vagas);
			}
		}
		throw new Exception(Excecoes.USUARIO_INEXISTENTE);
	}

	/**
	 * M√©todo que busca um usu√°rio numa carona
	 * 
	 * @param IDCarona
	 *            Identificador (id) da carona
	 * @return O usu√°rio pertencente a uma carona espec√≠fica
	 */
	private Usuario buscaUsuario(String IDCarona) {
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(IDCarona))
				return usuario;
		}
		return null;
	}

	/**
	 * M√©todo que sugere um ponto de encontro para partida da carona
	 * 
	 * @param idSessao
	 *            Identificador no usu√°rio que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @param pontosDeEncontro
	 *            Lista de pontos de encontros poss√≠veis
	 * @return Pontos de encontro poss√≠veis
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
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
	public void encerrarSessao(String login) throws Exception {
		if (login == null || login.equals("")) {
			throw new Exception(Excecoes.LOGIN_INVALIDO);
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
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
	 *            Identificador no usu√°rio que sugere o ponto de encontro
	 * @param idCarona
	 *            Identificador da carona a ser sugerido um ponto de encontro
	 * @return Identificador da carona em que foi solicitada a vaga
	 */
	public String solicitarVaga(String idSessao, String idCarona) {
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
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
					throw new Exception(Excecoes.SOLICITACAO_INEXISTENTE);
				}
			}
		}
	}

	/**
	 * Metodo que desiste de uma requisicao ja feita
	 * 
	 * @param idSessao
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
	 *            Identificador no usu√°rio que sugere o ponto de encontro
	 * @param idSolicitacao
	 *            Identificador da solicitacao de carona
	 * @throws NumberFormatException
	 *             Caso o id de solicitacao seja invalido
	 * @throws Exception
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws NumberFormatException, Exception {
		for(Usuario usuario : usuarios){
			if(usuario.getID().equals(idSessao)){
				if(!gerenciadorDeSolicitacoes.getSolicitacoesConfirmadas().matches(idSolicitacao)){
					gerenciadorDeSolicitacoes.validaSolicitacao(idSolicitacao);
					usuario.setHistoricoEmVagas(gerenciadorDeSolicitacoes.getSolicitacaoConfirmada(idSolicitacao).getCarona());
				}
				else{
					throw new Exception(Excecoes.SOLICITACAO_INEXISTENTE);
				}
			}
		}
	}

	/**
	 * Metodo que rejeita uma solicitacao de carona
	 * 
	 * @param idSessao
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
		usuarios = gerenciadorDeDadosEmXML.recuperaUsuarios();
	}

	/**
	 * Metodo que retorna uma carona de um determinado usuario
	 * 
	 * @param idSessao
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
		return null;
	}

	/**
	 * Metodo que encerra o sistema e salva os dados no arquivo XML
	 * 
	 * @throws Exception
	 *             Caso haja erro na operacao
	 */
	public void encerrar() throws Exception {
		gerenciadorDeDadosEmXML.salvaUsuariosXML(usuarios);
	}

	/**
	 * Metodo que diz como foi o funcionamento de uma carona. Se ele funcionou
	 * ou nao, ou se nao da mais carona a este usuario
	 * 
	 * @param idSessao
	 *            Identificador no usu√°rio que sugere o ponto de encontro
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
	public void reviewVagaEmCarona(String idSessao, String idCarona, 	String loginCaroneiro, String review) throws Exception {
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(idSessao)) {
				usuario.reviewVagaEmCarona(idCarona, loginCaroneiro, review);
			}
		}
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
	
	 public void reviewCarona(String idSessao, String idCarona, String review){
		 for(Usuario usuario : usuarios){
			 if(usuario.getID().equals(idSessao)){
				 usuario.reviewCarona(idCarona, review);
			 }
		 }
	 }
}