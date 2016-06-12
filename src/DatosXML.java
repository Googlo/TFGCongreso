
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import org.tartarus.snowball.ext.spanishStemmer;

import java.util.StringTokenizer;

public class DatosXML {
	private ArrayList<ArrayList<String>> datos;
	
	public DatosXML(String path) throws IOException{
		
		datos = new ArrayList<ArrayList<String>>();
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(path);
		File[] ficheros = xmlFile.listFiles();
		
		for(int x = 0; x < ficheros.length; x++){
			File xmlDefinitivo = new File(path + ficheros[x].getName());
			try{
				Document document = (Document) builder.build(xmlDefinitivo);
				Element rootNode = document.getRootElement();
				rootNode = rootNode.getChild("iniciativa");
				List list = rootNode.getChildren("intervencion");
	
				for(int i = 0; i < list.size(); i++){
					
					ArrayList<String> aux = new ArrayList<String>();
					String discurso = new String();
					
					Element node = (Element) list.get(i);
					aux.add(node.getChildText("interviniente"));
					
					node = node.getChild("discurso");
					List listaParrafo = node.getChildren("parrafo");
					
					for(int j = 0; j < listaParrafo.size(); j++){
						Element nodeParrafo = (Element) listaParrafo.get(j);
						discurso = discurso + " " + nodeParrafo.getText();
					}
					aux.add(discurso);
					
					datos.add(aux);
				}
			} catch (IOException io){
				System.out.println(io.getMessage());
			} catch (JDOMException jdomex){
				System.out.println(jdomex.getMessage());
			}
		}
	}
	public String reemplazarTexto(String texto){
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		return texto.replaceAll("[\\.,¿?¡!;()'\"<>:`´'-'-_]","").toLowerCase();
	}
	public Set<String> Tokenizar(String texto, Stopwords stw){
		Set<String> tokenSet = new HashSet();
		StringTokenizer tokens = new StringTokenizer(reemplazarTexto(texto));
		while(tokens.hasMoreTokens()){
			String token = tokens.nextToken();
			if(!stw.contains(token)){
				token = Stemming(token);
				tokenSet.add(token);
			}
		}
		return tokenSet;
	}
	public String Stemming(String token){
		spanishStemmer stemmer = new spanishStemmer();
		String TextoTokenizar = token;
		stemmer.setCurrent(token);
		if(stemmer.stem()){
			TextoTokenizar = stemmer.getCurrent();
		}
		return TextoTokenizar;
	}
	public String GetDato(Integer i, Integer j){
		return datos.get(i).get(j);
	}
}
