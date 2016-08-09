import java.io.IOException;


public class Main{
	public static void main(String[] args) throws IOException {
		DatosExcel datos = new DatosExcel("C:\\Users\\Nico\\Desktop\\TFG\\ListaDiputadosVIII.xls");
		DatosXML daXML = new DatosXML("C:\\Users\\Nico\\Desktop\\TFG\\ColeccionIniciativas\\iniciativas08\\");
		Stopwords stw = new Stopwords("C:\\Users\\Nico\\Desktop\\TFG\\stopwords_es.txt");
		
		System.out.println(daXML.GetDato(13000, 0));
		System.out.println(datos.GetDato(1,2)); 
		//System.out.println(daXML.Stemming("Sierra Nevada"));
		//stw.MuestraStopwords();
		System.out.println(daXML.Tokenizar(daXML.GetDato(3, 1),stw));
	}
}
