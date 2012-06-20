package controllers;


public class SistemaWebMain {
	private static SistemaController sistema = new SistemaController();
	private static String IDUsuario;
	
	//insere dados iniciais ao sistema
		public static void start() throws Exception{
			getSistema().criarUsuario("si1", "si1si1", "SI 1", "UFCG", "si1@ufcg.edu.br");
			getSistema().criarUsuario("tonyrodrigues", "deustony2012", "Antonio Rorigues", "UFCG", "antonio.rodrigues@ccc.ufcg.edu.br");
			getSistema().criarUsuario("laerciov", "si1laercio", "Laercioi", "UFCG", "laerciov@gmail.com");
			getSistema().criarUsuario("eduardor", "si1eduardo", "Eduardo", "UFCG", "eduardor@ufcg.edu.br");
			getSistema().criarUsuario("rodolfor", "si1rodolfo", "Rodolfo", "UFCG", "rodolfor@ufcg.edu.br");
			getSistema().criarUsuario("diego", "si1diego", "SI 1", "UFCG", "diego@ufcg.edu.br");
			
			String sessaoTony = getSistema().abrirSessao("tonyrodrigues", "deustony2012");
			getSistema().cadastrarCarona(sessaoTony, "Belem", "Campina Grande", "04/07/2012", "9:30", "2");
			getSistema().cadastrarCarona(sessaoTony, "Belem", "Joao Pessoa", "01/09/2012", "5:30", "4");
			getSistema().cadastrarCarona(sessaoTony, "Campina Grande", "Natal", "04/07/2012", "9:30", "2");
			getSistema().cadastrarCarona(sessaoTony, "Campina Grande", "Joao Pessoa", "18/10/2012", "9:30", "1");
			getSistema().cadastrarCarona(sessaoTony, "Belem", "Guarabira", "22/06/2012", "17:30", "3");
			getSistema().cadastrarCarona(sessaoTony, "Joao Pessoa", "Campina Grande", "04/07/2012", "9:30", "2");
			getSistema().cadastrarCarona(sessaoTony, "Patos", "Campina Grande", "25/07/2012", "19:40", "2");

			String sessaoSI = getSistema().abrirSessao("si1", "si1si1");
			getSistema().cadastrarCarona(sessaoSI, "Campina Grande", "Campina Grande", "28/06/2012", "17:30", "2");
			getSistema().cadastrarCarona(sessaoSI, "Campina Grande", "Belem", "29/06/2012", "14:30", "4");
			getSistema().cadastrarCarona(sessaoSI, "Recife", "Natal", "17/08/2012", "9:30", "6");
			getSistema().cadastrarCarona(sessaoSI, "Campina Grande", "Patos", "18/10/2012", "11:30", "2");
			getSistema().encerrarSessao("tonyrodrigues");getSistema().encerrarSessao("si1");
		}
	
	public static SistemaController getSistema() {
		return sistema;
	}
	public static String getIDUsuario() {
		return IDUsuario;
	}
	public static void setIDUsuario(String iDUsuario) {
		IDUsuario = iDUsuario;
	}
	public static void abrirSessao(String login, String senha) throws Exception {
		setIDUsuario(
				getSistema().abrirSessao(login, senha));
		
	}
}