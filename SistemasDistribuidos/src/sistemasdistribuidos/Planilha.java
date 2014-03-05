package sistemasdistribuidos;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Planilha {

    public void gerar(String caminho, int qtdLinhas, int qtdColunas) throws IOException {
    	
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet1 = wb.createSheet("planilha um");
    	
    	for (int i = 0; i < qtdLinhas; i++) {
    		
    		HSSFRow row = sheet1.createRow(i);
    		
            HSSFCellStyle style = wb.createCellStyle();
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);
            
            for (int j = 0; j < qtdColunas; j++) {
            
	            HSSFRichTextString strValor = new HSSFRichTextString("Linha: " + i + " Coluna " + j);
	            HSSFCell cell = row.createCell(j);
	            cell.setCellStyle(style);
	            cell.setCellValue(strValor);
            
            }
    		
    	} 
    	
        FileOutputStream stream = new FileOutputStream(caminho + "/planilha1.xls");

        wb.write(stream);
  }
  
}
