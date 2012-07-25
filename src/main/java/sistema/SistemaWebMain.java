package sistema;


public class SistemaWebMain {
	private static SistemaController sistema = SistemaController.getInstance();
	
	//insere contatos ao sistema
		@SuppressWarnings("unused")
		public static void start() throws Exception{
			getSistema().criarUsuario("si1", "si1si1", "SI 1", "UFCG", "si1@ufcg.edu.br");
			getSistema().criarUsuario("tonyrodrigues", "deustony2012", "Antonio Rorigues", "UFCG", "antonio.rodrigues@ccc.ufcg.edu.br");
			getSistema().criarUsuario("laerciov", "si1laercio", "Laercio", "UFCG", "laerciov@gmail.com");
			getSistema().criarUsuario("eduardor", "si1eduardo", "Eduardo", "UFCG", "eduardor@ufcg.edu.br");
			getSistema().criarUsuario("rodolfor", "si1rodolfo", "Rodolfo", "UFCG", "rodolfor@ufcg.edu.br");
			getSistema().criarUsuario("diego", "si1diego", "Diego", "UFCG", "diego@ufcg.edu.br");
			
			String sessaoTony = getSistema().abrirSessao("tonyrodrigues", "deustony2012");
			String idCarona1 = getSistema().cadastrarCarona(sessaoTony, "Belem", "Campina Grande", "29/07/2012", "9:30", "2");
			String idCarona2 = getSistema().cadastrarCarona(sessaoTony, "Belem", "Joao Pessoa", "01/09/2012", "5:30", "4");
			String idCarona3 = getSistema().cadastrarCarona(sessaoTony, "Campina Grande", "Natal", "30/08/2012", "9:30", "2");
			String idCarona4 = getSistema().cadastrarCarona(sessaoTony, "Campina Grande", "Joao Pessoa", "18/10/2012", "9:30", "1");
			String idCarona5 = getSistema().cadastrarCarona(sessaoTony, "Belem", "Guarabira", "30/07/2012", "17:30", "3");
			String idCarona6 = getSistema().cadastrarCarona(sessaoTony, "Joao Pessoa", "Campina Grande", "04/08/2012", "9:30", "2");
			String idCarona7 = getSistema().cadastrarCarona(sessaoTony, "Patos", "Campina Grande", "25/07/2012", "19:40", "2");

			String sessaoSI = getSistema().abrirSessao("si1", "si1si1");
			String idCarona8 = getSistema().cadastrarCarona(sessaoSI, "Campina Grande", "Campina Grande", "28/07/2012", "17:30", "2");
			String idCarona9 = getSistema().cadastrarCarona(sessaoSI, "Campina Grande", "Belem", "29/10/2012", "14:30", "4");
			String idCarona10 = getSistema().cadastrarCarona(sessaoSI, "Recife", "Natal", "17/08/2012", "9:30", "6");
			String idCarona11 = getSistema().cadastrarCarona(sessaoSI, "Campina Grande", "Patos", "18/10/2012", "11:30", "2");
			
			String sessaoRodolfo = getSistema().abrirSessao("rodolfor", "si1rodolfo");
			String idCarona12 = getSistema().cadastrarCarona(sessaoRodolfo, "Campina Grande", "Lagoa Seca", "21/11/2012", "11:30", "2");
			String idCarona13 = getSistema().cadastrarCarona(sessaoRodolfo, "Remigio", "Patos", "26/10/2012", "11:30", "2");
			
			getSistema().solicitarVaga(sessaoTony, idCarona8);
			getSistema().solicitarVaga(sessaoTony, idCarona9);
			

			getSistema().solicitarVaga(sessaoRodolfo, idCarona8);
			getSistema().solicitarVaga(sessaoRodolfo, idCarona11);
			getSistema().solicitarVaga(sessaoRodolfo, idCarona5);
			
			getSistema().solicitarVaga(sessaoSI, idCarona2);
			getSistema().solicitarVaga(sessaoSI, idCarona4);
			getSistema().solicitarVaga(sessaoSI, idCarona7);
			
			getSistema().encerrarSessao("tonyrodrigues");
			getSistema().encerrarSessao("si1");
		}
	
	public static SistemaController getSistema() {
		return sistema;
	}

}