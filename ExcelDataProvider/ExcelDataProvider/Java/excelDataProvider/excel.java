package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class excel {
	
	@Test
	public void getExcel() throws IOException
	{
		FileInputStream fileName=new FileInputStream("D:\\repository\\SDET\\javaSDET\\ExcelFiles\\excelDriven.xlsx");
	
		XSSFWorkbook workBook=new XSSFWorkbook(fileName);
		XSSFSheet dataSheet=workBook.getSheetAt(0);
		int rowCount=dataSheet.getPhysicalNumberOfRows();
		XSSFRow getRow=dataSheet.getRow(0);
		int colCount=getRow.getLastCellNum();
	
		Object data[][]=new Object[rowCount-1][colCount];

		for (int row=0; row<rowCount-1; row++) 
		{
			getRow=dataSheet.getRow(row+1);
			
			for (int col=0; col<colCount; col++)
			{
				System.out.println(getRow.getCell(col));
			}
		}
		workBook.close();
	}
}
