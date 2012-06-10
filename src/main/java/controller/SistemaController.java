package controller;

import funcionalidades.PerfilDoUsuario;
import funcionalidades.Sistema;




public class SistemaController {
	Sistema sistema;

	public void zerarSistema() throws Exception {
		sistema = new Sistema();
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		return sistema.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo) throws Exception {
		return sistema.getAtributoUsuario(login, atributo);
	}

	public String getAtributoPerfil(String login, String atributo)	throws Exception {
		return sistema.getAtributoUsuario(login, atributo);
	}

	public PerfilDoUsuario visualizarPerfil(String idSessao, String login)	throws Exception {
		return sistema.visualizarPerfil(idSessao, login);
	}

	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {
		return sistema.localizarCarona(idSessao, origem, destino);
	}

	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String vagas) throws Exception {
		return sistema.cadastrarCarona(idSessao, origem, destino, data, hora, vagas);
	}

	public String getAtributoCarona(String ID, String atributo) throws Exception {
		return sistema.getAtributoCarona(ID, atributo);
	}

	public String getTrajeto(String IDCarona) throws Exception {
		return sistema.getTrajeto(IDCarona);
	}

	public String getCarona(String IDCarona) throws Exception {
		return sistema.getCarona(IDCarona);
	}

	public void encerrarSessao(String login) throws Exception {
		sistema.encerrarSessao(login);
	}

	public String getCaronaUsuario(String idSessao, String indexCarona) {
		return sistema.getCaronaUsuario(idSessao, indexCarona);
	}

	public String getTodasCaronasUsuario(String idSessao) {
		return sistema.getTodasCaronasUsuario(idSessao);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontosDeEncontro) throws Exception {
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontosDeEncontro);
	}

	public void responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontosDeEncontro) throws Exception {
		sistema.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontosDeEncontro);
	}

	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao) {
		sistema.desistirRequisicao(idSessao, idCarona, idSugestao);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, 	String pontoDeEncontro) throws Exception {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, pontoDeEncontro);
	}

	public String solicitarVaga(String idSessao, String idCarona) {
		return sistema.solicitarVaga(idSessao, idCarona);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws Exception {
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws NumberFormatException, Exception {
		sistema.aceitarSolicitacao(idSessao, idSolicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception {
		sistema.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona, 	String loginCaroneiro, String review) throws Exception {
		sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}

	public String getSolicitacoesPendentes(String idSessao, String idCarona) throws Exception {
		return sistema.getSolicitacoesPendentes(idSessao, idCarona);
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona)	throws Exception {
		return sistema.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	public String getPontosSugeridos(String idSessao, String idCarona) throws Exception {
		return sistema.getPontosSugeridos(idSessao, idCarona);
	}
	
	public String getPontosEncontro(String idSessao, String idCarona){
		return sistema.getPontosEncontro(idSessao, idCarona);
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review) throws Exception{
		sistema.reviewCarona(idSessao, idCarona, review);
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, String data, String hora, String vagas) throws Exception{
		return sistema.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, data, hora, vagas);
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino) throws Exception{
		return sistema.localizarCaronaMunicipal(idSessao, cidade, origem, destino);
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade) throws Exception{
		return sistema.localizarCaronaMunicipal(idSessao, cidade);
	}
	
	public String cadastrarInteresse (String idSessao, String origem, String destino, String data, String horaInicio, String horaFim) throws Exception{
		 return sistema.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	 }
	 
	 public String verificarMensagensPerfil(String idSessao) throws Exception{
		 return sistema.verificarMensagensPerfil(idSessao);
	 }
	 
	 public String enviarEmail(String idSessao, String destino, String message) throws Exception{
		 return sistema.enviarEmail(idSessao, destino, message);
	 }

	public void reiniciarSistema() throws Exception {
		sistema.reiniciar();
	}

	public void encerrarSistema() throws Exception {
		sistema.encerrar();
	}

}