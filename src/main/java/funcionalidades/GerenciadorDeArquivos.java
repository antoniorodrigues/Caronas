package funcionalidades;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.jdom2.*; 
import org.jdom2.output.XMLOutputter;

public class GerenciadorDeArquivos {
	
	private void escreveEmArquivo(String fileName, String dados){
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName,false),"UTF-8")));
			outputStream.write(dados);
			
		}catch(FileNotFoundException e2){
			e2.printStackTrace();

		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if (outputStream != null) {
	            outputStream.close();
	         }
		}
	}
public static void main(String[] args) throws IOException {
	 //Declaração dos elementos que irão compor a estrutura do documento.  
	   Element mural = new Element("mural");  
	   Element mensagem = new Element("mensagem");  
	   Element para = new Element("para");  
	   Element de = new Element("de");  
	   Element corpo = new Element("corpo");  
	  
	   //"Setando" os atributos  
	   mensagem.setAttribute("id1", "01");
	   mensagem.setAttribute("id2", "02");  
	     
	   //"Setando" outro atributo agora utilizando da classe Attribute  
	   Attribute prioridade = new Attribute("prioridade","-1");  
	   mensagem.setAttribute(prioridade);     
	  
	   mensagem.addContent(para);  
	   mensagem.addContent(de);  
	   mensagem.addContent(corpo);  
	     
	   mural.addContent(mensagem);  
	        
	   //Criando o documento XML (montado)  
	   Document doc = new Document();  
	   doc.setRootElement(mural);  
	  
	   //Imptrimindo o XML  
	   XMLOutputter xout = new XMLOutputter();  
	   xout.output(doc, System.out);  
}
}
