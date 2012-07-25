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
public class RecomendacaoPorLugarPopular extends Recomendacao{
	public RecomendacaoPorLugarPopular(Usuario usuario, List<Carona> caronasCadastradas) {
		super(usuario, caronasCadastradas);
	}
	private String buscaDestinoMaisProcurado(){
		Map<String, Integer> popularidadesDosDestinos = new TreeMap<String, Integer>();
			for (Carona carona: getCaronasCadastradas()){
			if (popularidadesDosDestinos.containsKey(carona.getDestino())){
				popularidadesDosDestinos.put(carona.getDestino(), popularidadesDosDestinos.get(carona.getDestino())+1);
			}else{
				popularidadesDosDestinos.put(carona.getDestino(), 1);
			}
			
		}
		String destinoMaisProcurado = "";
		int valor = 0;
		for (String destino: popularidadesDosDestinos.keySet()){
			if (popularidadesDosDestinos.get(destino) > valor){
				valor = popularidadesDosDestinos.get(destino);
				destinoMaisProcurado = destino;
			}
		}
		return destinoMaisProcurado;
	}
	public List<Carona> gerarRecomendacoes() {
		List<Carona> caronasRecomendadas = new ArrayList<Carona>();
		String destinoMaisProcurado = buscaDestinoMaisProcurado();
		for (Carona carona: getCaronasCadastradas()){
			if (!carona.getTodosCaroneiros().contains(getUsuario()) && carona.getDestino().equals(destinoMaisProcurado)
					&& !carona.getDono().getLogin().equals(getUsuario().getLogin()))
				caronasRecomendadas.add(carona);
		}
		return caronasRecomendadas;
	}

}
