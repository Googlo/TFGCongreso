import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;


public class DatosExcel {
	private ArrayList<ArrayList<String>> datos;
	
	//Abrimos el archivo que se pasa al constructor
	public DatosExcel(String path) throws IOException{
		datos = new ArrayList<ArrayList<String>>();
		FileInputStream file = new FileInputStream(new File (path));
		
		//Con esto obtenemos la pestaña de Excel primera
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		
		//Obtenemos la primera fila para hacer un iterador y recorrer las filas
		//restantes:
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		
		Row row;
		
		//Recorremos todas las celdas
		while(rowIterator.hasNext()){
			ArrayList<String> aux = new ArrayList<String>();
			row = rowIterator.next();
			//Recorremos todas las celdas de la fila
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell celda;
			
			while(cellIterator.hasNext()){
				celda = cellIterator.next();
				switch(celda.getCellType()){
				case Cell.CELL_TYPE_NUMERIC:
					if(DateUtil.isCellDateFormatted(celda)){
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						String fecha = df.format(celda.getDateCellValue());
						aux.add(fecha);
					}
					else{
						String numero = String.valueOf(celda.getNumericCellValue());
						aux.add(numero);
					}
					break;
				case Cell.CELL_TYPE_STRING:
					aux.add(celda.getStringCellValue());
					break;
				}
			}
			datos.add(aux);
		}
	}
	public String GetDato(Integer i, Integer j){
		return datos.get(i).get(j);
	}
}
