package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvide {
	
	/*
	 * Multiple sets of data to our test. 
	 * Data must be in a an Array and can be a multidim array.
	 * 5 Sets of data rows as 5 arrays from data provider to your tests.
	 * The test will run 5 times with 5 separate sets of data arrays.
	 */
	
	DataFormatter frmtData=new DataFormatter();
	
	@Test(dataProvider="driveTest")
	public void testCaseData(String ID,String textField1, String textField2)
	{
		System.out.println(ID+textField1+textField2);
	}
	
	/*
	 * top method is the test bench.
	 * These 2 methods and annotations @ are the skeleton class to create tests.
	 * bottom method is the data set to be tested.
	 */
	
	@DataProvider(name="driveTest")
	public Object[][] getData() throws IOException {
		
	/*
	 * Simple in-line data testing. Example of 3 rows of an excel sheet would look like.
	 * 
	 * Object[][] data= {{"3","Student","Careerdevs"},{"1010","Resistance","Futile"},{"3000","Stop, wait","Go"}};
	 * return data;
	 */
		
	// Get file from source
		FileInputStream fileName=new FileInputStream("D:\\repository\\SDET\\javaSDET\\ExcelFiles\\excelDriven.xlsx");
		
	// get info about Workbook/Sheet: get the first Workbook and Sheet and determine how many Columns and Rows in it.
		XSSFWorkbook workBook=new XSSFWorkbook(fileName);
		XSSFSheet dataSheet=workBook.getSheetAt(0);
		int rowCount=dataSheet.getPhysicalNumberOfRows();
		XSSFRow getRow=dataSheet.getRow(0);
		int colCount=getRow.getLastCellNum();
		
	// Create a new Data Object.
		Object data[][]=new Object[rowCount-1][colCount];
		
	/*
	 * Iterate through the sheet to get the data.
	 * The getRow line the (row+1) is to bypass the header row: this is bad syntax.		
	 * The Loop crashes: goes beyond rowCount variable statement.
	 * Removing the +1 All Test Pass. Header row is revealed.
	 */
		
		for (int row=0; row<rowCount-1; row++) 
		{
			getRow=dataSheet.getRow(row+1);
			for (int col=0; col<colCount; col++)
			{
				XSSFCell cell=getRow.getCell(col);
				data[row][col]=frmtData.formatCellValue(cell);
			}
		}
		workBook.close();
		return data;
	}
}
