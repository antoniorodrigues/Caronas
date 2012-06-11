package gerenciadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import componentesdosistema.Carona;
import componentesdosistema.DadosCaronaException;
import componentesdosistema.SolicitacaoDeCarona;
import componentesdosistema.SolicitacaoIlegalException;
import componentesdosistema.SugestaoDePonto;
import componentesdosistema.Usuario;


public class GerenciadorDeSolicitacoes {
	List<SugestaoDePonto> sugestoesDePontosDeEncontro;
	List<SolicitacaoDeCarona> todasSolicitacoes;
	List<SolicitacaoDeCarona> solicitacoesConfirmadas;
	List<SolicitacaoDeCarona> solicitacoesPendentes;


	public GerenciadorDeSolicitacoes() {
		sugestoesDePontosDeEncontro = new ArrayList<SugestaoDePonto>();
		todasSolicitacoes = new ArrayList<SolicitacaoDeCarona>();
		solicitacoesConfirmadas = new ArrayList<SolicitacaoDeCarona>();
		solicitacoesPendentes = new ArrayList<SolicitacaoDeCarona>();
	}

	public String sugerirPontoEncontro(Usuario usuarioLogado, Carona carona, String sugestoes) throws DadosCaronaException{
		SugestaoDePonto novaSugestao = buscaSugestaoPorCarona(carona.getID());
		List<String> novosPontos = Arrays.asList(sugestoes.split(";"));
		
		if (novaSugestao == null){
			novaSugestao = new SugestaoDePonto();
			novaSugestao.setSugestaoID(String.valueOf(Math.abs(new Random().nextInt())));
			novaSugestao.setIDCarona(carona.getID());
		}
		if (!novaSugestao.isSugestoesFinalizadas()){
			novaSugestao.getPontosSugeridos().addAll(novosPontos);
			sugestoesDePontosDeEncontro.add(novaSugestao);

		}else{
			for (String ponto: novosPontos){
				if (!novaSugestao.getPontosSugeridos().contains(ponto)){
					throw new DadosCaronaException("Ponto Inválido");
				}

			}
		}
		return novaSugestao.getSugestaoID();
	}


	public void responderSugestao(Carona carona, SugestaoDePonto sugestaoDePonto, String novosPontos) throws DadosCaronaException {
		if (novosPontos.equals(""))
			throw new DadosCaronaException("Ponto Inválido");

		List<String> pontosDoDono = Arrays.asList(novosPontos.split(";"));
		sugestaoDePonto.getPontosSugeridos().retainAll(pontosDoDono);
		sugestaoDePonto.getPontosSugeridos().addAll(pontosDoDono);
		sugestaoDePonto.encerrarSugestoes();
	}

	public SugestaoDePonto buscaSugestao(String idSugestao){
		for (SugestaoDePonto sugestao: sugestoesDePontosDeEncontro){
			if (sugestao.getSugestaoID().equals(idSugestao))
				return sugestao;
		}
		return null;
	}
	
	public SugestaoDePonto buscaSugestaoPorCarona(String idCarona){
		for (SugestaoDePonto sugestao: sugestoesDePontosDeEncontro){
			if (sugestao.getIDCarona().equals(idCarona))
				return sugestao;
		}
		return null;
	}

	public String solicitarVaga(Carona carona, String donoDaCarona, Usuario donoDaSolicitacao, String pontoDeEncontro) throws DadosCaronaException {
		if (carona.getCaroneiros().contains(donoDaSolicitacao.getNome())){
			throw new DadosCaronaException("Ponto Inválido");
		}
		
		SolicitacaoDeCarona novaSolicitacao = new SolicitacaoDeCarona(carona, donoDaCarona, donoDaSolicitacao, pontoDeEncontro);
		novaSolicitacao.setIdSolcitacao(String.valueOf(Math.abs(new Random().nextInt())));
		todasSolicitacoes.add(novaSolicitacao);
		solicitacoesPendentes.add(novaSolicitacao);
		return novaSolicitacao.getIdSolcitacao();
	}

	public String solicitarVaga(Carona carona, String donoDaCarona, Usuario donoDaSolicitacao) {

		SolicitacaoDeCarona novaSolicitacao = new SolicitacaoDeCarona(carona, donoDaCarona, donoDaSolicitacao);
		novaSolicitacao.setIdSolcitacao(String.valueOf(Math.abs(new Random().nextInt())));
		todasSolicitacoes.add(novaSolicitacao);
		return novaSolicitacao.getIdSolcitacao();
	}

	public SolicitacaoDeCarona buscaSolicitacao(String idSolicitacao) throws SolicitacaoIlegalException{
		for (SolicitacaoDeCarona solicitacao: todasSolicitacoes){
			if (solicitacao.getIdSolcitacao().equals(idSolicitacao)){
				return solicitacao;
			}
		}
		
		throw new SolicitacaoIlegalException("Solicitação inexistente");
	}

	public void validaSolicitacao(String idSolicitacao) throws NumberFormatException, SolicitacaoIlegalException, DadosCaronaException {
		SolicitacaoDeCarona solicitacao = buscaSolicitacao(idSolicitacao);
		Carona carona = solicitacao.getCarona();
		carona.adicionaCaroneiro(solicitacao.getDonoDaSolicitacao().getNome(), solicitacao.getPontoDeEncontro());
		carona.adicionaCaroneiro(solicitacao.getDonoDaSolicitacao());
		solicitacao.getDonoDaSolicitacao().setHistoricoEmVagas(carona);
		solicitacoesConfirmadas.add(solicitacao);
		solicitacoesPendentes.remove(solicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws DadosCaronaException, SolicitacaoIlegalException {
		SolicitacaoDeCarona solicitacao = buscaSolicitacao(idSolicitacao);
		if (solicitacao == null){
			throw new DadosCaronaException("Ponto Inválido");
		}
		todasSolicitacoes.remove(solicitacao);

	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) {
		sugestoesDePontosDeEncontro.remove(buscaSugestao(idSugestao));
		
	}
	
	public String getSolicitacoesConfirmadas(){
		String idsConfirmadas = "";
		
		for(SolicitacaoDeCarona solicitacao : solicitacoesConfirmadas){
			idsConfirmadas += solicitacao.getIdSolcitacao();
		}
		
		return idsConfirmadas;
	}
	
	public SolicitacaoDeCarona getSolicitacaoConfirmada(String idSolicitacao) throws SolicitacaoIlegalException{
		for(SolicitacaoDeCarona solicitacao : solicitacoesConfirmadas){
			if(solicitacao.getIdSolcitacao().equals(idSolicitacao)){
				return solicitacao;
			}
			
		}
		throw new SolicitacaoIlegalException("Solicitação não confirmada");
	}
	
	public String getSolicitacoesPendentes(){
		String idsPendentes = "";
		
		for(SolicitacaoDeCarona solicitacao : solicitacoesPendentes){
			idsPendentes += solicitacao.getIdSolcitacao();
		}
		
		return idsPendentes;
	}
	
	public String getSolicitacaoPendente(String idCarona){
		for(SolicitacaoDeCarona solicitacao : solicitacoesPendentes){
			if(solicitacao.getCarona().getID().equals(idCarona)){
				return solicitacao.getIdSolcitacao();
			}
		}
		return "";
	}
	
	public String getSolicitacaoCaronaConfirmada(String idCarona){
		for(SolicitacaoDeCarona solicitacao : solicitacoesConfirmadas){
			if(solicitacao.getCarona().getID().equals(idCarona)){
				return solicitacao.getIdSolcitacao();
			}
		}
		return "{}";
	}
}