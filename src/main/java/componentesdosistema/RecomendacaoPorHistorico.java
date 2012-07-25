package componentesdosistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class RecomendacaoPorHistorico extends Recomendacao{
	
	public RecomendacaoPorHistorico(Usuario usuario, List<Carona> caronasCadastradas) {
		super(usuario, caronasCadastradas);
	}
	public String buscaDestinoMaisBuscadasPorEsseUsuario(){
		Map<String, Integer> destinoMaisProcurado = new TreeMap<String, Integer>();
		for (Carona carona: getCaronasCadastradas()){
			if (carona.getTodosCaroneiros().contains(getUsuario())){
				if (destinoMaisProcurado.containsKey(carona.getDestino())){
					destinoMaisProcurado.put(carona.getDestino(), destinoMaisProcurado.get(carona.getDestino())+1);
				}else{
					destinoMaisProcurado.put(carona.getDestino(), 1);
				}
			}
		}
		String destinoMaisProcuradoPeloUsuario = "";
		int valor = 0;
		for (String destino: destinoMaisProcurado.keySet()){
			if (destinoMaisProcurado.get(destino) > valor){
				valor = destinoMaisProcurado.get(destino);
				destinoMaisProcuradoPeloUsuario = destino;
			}
		}
		return destinoMaisProcuradoPeloUsuario;
	}

	public String buscaOrigemMaisBuscadaPorEsseUsuario(){
		Map<String, Integer> origemMaisProcurado = new TreeMap<String, Integer>();
		for (Carona carona: getCaronasCadastradas()){
			if (carona.getTodosCaroneiros().contains(getUsuario())){
				if (origemMaisProcurado.containsKey(carona.getOrigem())){
					origemMaisProcurado.put(carona.getOrigem(), origemMaisProcurado.get(carona.getOrigem())+1);
				}else{
					origemMaisProcurado.put(carona.getOrigem(), 1);
				}
			}
		}
		String origemMaisProcuradoPeloUsuario = "";
		int valor = 0;
		for (String origem: origemMaisProcurado.keySet()){
			if (origemMaisProcurado.get(origem) > valor){
				valor = origemMaisProcurado.get(origem);
				origemMaisProcuradoPeloUsuario = origem;
			}
		}
		return origemMaisProcuradoPeloUsuario;
	}

	public List<Carona> gerarRecomendacoes() {
		List<Carona> caronasRecomendadas = new ArrayList<Carona>();
		String destinoMaisProcurado = buscaDestinoMaisBuscadasPorEsseUsuario();
		String origemMaisProcurada = buscaOrigemMaisBuscadaPorEsseUsuario();
		for (Carona carona: getCaronasCadastradas()){
			if (!carona.getTodosCaroneiros().contains(getUsuario()) && (carona.getDestino().equals(destinoMaisProcurado) || carona.getOrigem().equals(origemMaisProcurada))
					&& !carona.getDono().getLogin().equals(getUsuario().getLogin()))
				caronasRecomendadas.add(carona);
		}
		return caronasRecomendadas;
	}

}
