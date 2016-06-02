import java.io.IOException;

public class Main{
	public static void main(String[] args) throws IOException {
		DatosExcel datos = new DatosExcel("C:\\Users\\Nico\\Desktop\\TFG\\ListaDiputadosVIII.xls");
		DatosXML daXML = new DatosXML("C:\\Users\\Nico\\Desktop\\TFG\\ColeccionIniciativas\\iniciativas08\\");
		
		System.out.println(daXML.GetDato(13000, 0));
		System.out.println(datos.GetDato(1,2)); 
	}
}
