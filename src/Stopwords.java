import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Stopwords {
	private Set<String> stopwords = new HashSet();
	
	public Stopwords(String path) throws IOException, FileNotFoundException{
		String cadena = new String();
		FileReader f = new FileReader(path);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine())!= null){
			stopwords.add(cadena);
		}
		b.close();
	}
	public void MuestraStopwords(){
		System.out.println(stopwords);
	}
	public Boolean contains(String token){
		if(stopwords.contains(token)){
			return true;
		}
		else{
			return false;
		}
	}
}
