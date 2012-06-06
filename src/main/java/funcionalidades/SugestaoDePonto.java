package funcionalidades;

import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Antonio, Diego, Eduardo, Laercio, Rodolfo
 * 
 */
public class SugestaoDePonto {
	private Set<String> pontosSugeridos;
	private String sugestaoID;
	private String IDCarona;
	private boolean sugestoesFinalizadas;

	/**
	 * Construtor da classe SugestaoDePonto
	 */
	public SugestaoDePonto() {
		pontosSugeridos = new TreeSet<String>();
		sugestoesFinalizadas = false;
	}

	/**
	 * Metodo que adiciona uma sugestao a lista de sugestoes
	 * 
	 * @param ponto
	 *            Sugestao de ponto de encontro
	 */
	public void adicionaSugestao(String ponto) {
		pontosSugeridos.add(ponto);
	}

	/**
	 * Metodo que retorna uma sugestao de ponto de encontro
	 * 
	 * @return ID da sugestao de ponto de encontro
	 */
	public String getSugestaoID() {
		return sugestaoID;
	}

	/**
	 * Metodo que modifica a ID da sugestao de ponto de encontro
	 * 
	 * @param sugestaoID
	 *            ID da sugestao de ponto de encontro
	 */
	public void setSugestaoID(String sugestaoID) {
		this.sugestaoID = sugestaoID;
	}

	/**
	 * Metodo que retorna todos os pontos sugeridos
	 * 
	 * @return Pontos sugeridos
	 */
	public Set<String> getPontosSugeridos() {
		return pontosSugeridos;
	}

	/**
	 * Metodo que modifica o ID da carona
	 * 
	 * @param iDCarona
	 *            ID da carona
	 */
	public void setIDCarona(String iDCarona) {
		IDCarona = iDCarona;
	}

	/**
	 * Metodo que retorna o ID da carona
	 * 
	 * @return ID da carona
	 */
	public String getIDCarona() {
		return IDCarona;
	}

	/**
	 * Metodo que faz ser possivel adicionar sugestoes a carona ou nao
	 */
	public void encerrarSugestoes() {
		this.sugestoesFinalizadas = true;
	}

	/**
	 * Metodo que verfica se as sugestoes foram finalizadas
	 * 
	 * @return True se as sugestoes foram finalizadas, False, caso contrario
	 */
	public boolean isSugestoesFinalizadas() {
		return sugestoesFinalizadas;
	}
	
	public String getTodosPontosSugeridos(){
		String pontos = "[";
		
		for(String ponto : pontosSugeridos){
			pontos += ponto + ";";
		}
		
		return pontos.substring(0, pontos.length()-1) + "]";
	}
}