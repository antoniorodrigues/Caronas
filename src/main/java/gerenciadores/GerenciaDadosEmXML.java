package gerenciadores;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.ls.LSInput;

import funcionalidades.Carona;
import funcionalidades.PerfilDoUsuario;
import funcionalidades.Usuario;



public class GerenciaDadosEmXML {
	private final String[] ATRIBUTOS_DE_PERFIL = {"nome", "endereco", "email", 
			"faltas em vagas de caronas", "historico de caronas",
			"historico de vagas em caronas", "caronas seguras e tranquilas",
			"caronas que não funcionaram", "presenças em vagas de caronas"};

	private final String[] ATRIBUTOS_DA_CARONA = {"origem", "destino", "data", "hora", 
			"vagas", "Ponto de Encontro", "Caroneiros"};


	public List<Usuario> recuperaUsuarios() throws Exception{

		Document doc = null;
		SAXBuilder builder = new SAXBuilder();

		try {

			doc = builder.build("arquivo.xml");
		} catch (Exception e) {
			e.printStackTrace();

		}           
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Element usuariosCadastrados = doc.getRootElement();
		List<Element> listaUsuarios = usuariosCadastrados.getChildren();

		for (Element usuario: listaUsuarios ){

			String idSessao = usuario.getAttributeValue("id");
			String login = usuario.getAttributeValue("login");
			String senha = usuario.getAttributeValue("senha");
			Usuario usuarioRecuperado = new Usuario(login, senha, "null", "null", "null");
			
			usuarioRecuperado.getPerfil(login).setNome(usuario.getChildText("nome"));
			usuarioRecuperado.getPerfil(login).setEndereco(usuario.getChildText("endereco"));
			usuarioRecuperado.getPerfil(login).setEmail(usuario.getChildText("email"));
			usuarioRecuperado.getPerfil(login).setFaltaCaronas(usuario.getChildText("faltasemvagasdecaronas"));
			usuarioRecuperado.getPerfil(login).setCaronasSeguras(usuario.getChildText("caronassegurasetranquilas"));
			usuarioRecuperado.getPerfil(login).setCaronasNaoFuncionaram( usuario.getChildText("caronasquenãofuncionaram"));
			usuarioRecuperado.getPerfil(login).setPresencaCaronas(usuario.getChildText("presençasemvagasdecaronas"));
			for (String caronaDoHistorico: usuario.getChildText("historicodevagasemcaronas").replace("[", "").
					replace("]","").split(",")){
				usuarioRecuperado.getPerfil(login).adicionaCarona(caronaDoHistorico)	;
			}
			
			for (Carona caronaRecuperada: recuperaCaronas(usuario.getChild("CaronasDoUsuario_"+usuarioRecuperado.getLogin()).getChildren())){//CaronasDoUsuario_mark
				usuarioRecuperado.adicionaCarona(caronaRecuperada);
			}usuarios.add(usuarioRecuperado);

		}           
		return usuarios;
	}
	public List<Carona> recuperaCaronas(List<Element> caronas) throws Exception{
		List<Carona> caronasRecuperadas = new ArrayList<Carona>();
		Iterator<Element> iteraCaronas = caronas.iterator();
		while (iteraCaronas.hasNext()){
			Element carona = iteraCaronas.next();
			String caronaID = carona.getAttributeValue("id");
			String origem = carona.getChildText("origem");
			String destino = carona.getChildText("destino");
			String data = carona.getChildText("data");
			String hora = carona.getChildText("hora");
			String vagas = carona.getChildText("vagas");
			String pontoDeEncontro = carona.getChildText("PontodeEncontro");
			Carona caronaRecuperada = new Carona(origem, destino, data, hora, vagas);
			
			for (String caroneiro: carona.getChildText("Caroneiros").split(";")){
				caronaRecuperada.adicionaCaroneiro(caroneiro, pontoDeEncontro);
			}
			caronaRecuperada.setID(caronaID);
			caronasRecuperadas.add(caronaRecuperada);
			
		}
		return caronasRecuperadas;
		
	}

	public void salvaUsuariosXML(List<Usuario> usuarios) throws Exception{
		int salvamentoID = 0;
		Element usuariosCadastrados = new Element("UsuariosCadastrados");
				
		Document documento = new Document(usuariosCadastrados);

		for (Usuario usuario: usuarios){
			
			Element novoCadastro = new Element("Usuario"+salvamentoID);
			novoCadastro.setAttribute("id", usuario.getID());
			novoCadastro.setAttribute("login", usuario.getLogin());
			novoCadastro.setAttribute("senha", usuario.getSenha());


			for (String atributo: ATRIBUTOS_DE_PERFIL){
				PerfilDoUsuario perfil = usuario.getPerfil(usuario.getLogin()); 
				Element atribute = new Element(atributo.replace(" ", ""));
				atribute.setText(""+perfil.getAtributo(perfil.getLogin(), atributo));
				novoCadastro.addContent(atribute);
			}
			novoCadastro.addContent(salvaCaronasXML(usuario.getCaronas(), usuario.getLogin()));
			usuariosCadastrados.addContent(novoCadastro);
			salvamentoID++;
		}

		escreveNoArquivoXML(documento);
		


	}

	public Element salvaCaronasXML(List<Carona> caronas, String loginDoUsuario) throws Exception{
		int salvamentoID = 0;
		Element caronasCadastradas = new Element("CaronasDoUsuario_"+loginDoUsuario);

		for (Carona carona: caronas){
			Element novaCarona = new Element("Carona"+salvamentoID);

			//Adiciona o atributo id ao Contato
			novaCarona.setAttribute("id", carona.getID());
			for (String atributo: ATRIBUTOS_DA_CARONA){ 
				Element atribute = new Element(atributo.replace(" ", ""));
				atribute.setText(""+carona.getAtributoCarona(atributo));
				novaCarona.addContent(atribute);
			}
			caronasCadastradas.addContent(novaCarona);
			salvamentoID++;
		}
		return caronasCadastradas;
	}

	public void escreveNoArquivoXML(Document documento){
		try {

			//Classe responsável para imprimir / gerar o XML
			XMLOutputter xout = new XMLOutputter();

			//Criando o arquivo de saida
			FileWriter arquivo = new FileWriter(
					new File("arquivo.xml"));
			//Imprimindo o XML no arquivo
			xout.output(documento, arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}
}

