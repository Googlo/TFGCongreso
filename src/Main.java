import java.io.IOException;

public class Main{
	public static void main(String[] args) throws IOException {
		DatosExcel datos = new DatosExcel("C:\\Users\\Nico\\Desktop\\TFG\\ListaDiputadosVIII.xls");
		
		System.out.println(datos.GetDato(1,2)); 
	}
}
