using ExcelDataReader;
using NUnit.Framework;
using OfficeOpenXml;
using System;
using System.Data;
using System.IO;

namespace ExcelDataProvider
{
    public class Tests
    {

        [Test]
        public void UsingExcelDataReader()
        {
            System.Text.Encoding.RegisterProvider(System.Text.CodePagesEncodingProvider.Instance);
            using (FileStream xlsFile = File.Open("D:\\repository\\SDET\\javaSDET\\ExcelFiles\\excelDriven.xlsx", FileMode.Open, FileAccess.Read))
            {
                using (IExcelDataReader sheet = ExcelReaderFactory.CreateReader(xlsFile))
                {
                    do
                    {
                        while (sheet.Read()) //reads Each ROW 
                                             // At end of Rows  will Return False 
                                             // and End While loop.
                        {
                            for (int column = 0; column < sheet.FieldCount; column++)
                            {
                                //Console.WriteLine(reader.GetString(column));//Will blow up if the value is decimal etc. 
                                Console.WriteLine(sheet.GetValue(column));//Get Value returns object
                            }
                        }
                    } while (sheet.NextResult()); //Move to NEXT SHEET
                                                  // When ALL the ROWS in sheet are done 
                                                  // and End While loop.

                }
            }
        }

        [Test]
        public void UsingEPPlus()
        {
            using (ExcelPackage xlsFile = new ExcelPackage(new FileInfo("D:\\repository\\SDET\\javaSDET\\ExcelFiles\\excelDriven.xlsx")))
            {
                ExcelWorksheet firstSheet = xlsFile.Workbook.Worksheets["CareerDevs"];
                Console.WriteLine("Sheet 1 Data");
                Console.WriteLine($"Cell A2 Value   : {firstSheet.Cells["A2"].Text}");
                Console.WriteLine($"Cell A2 Color   : {firstSheet.Cells["A2"].Style.Font.Color.LookupColor()}");
                Console.WriteLine($"Cell B2 Formula : {firstSheet.Cells["B2"].Formula}");
                Console.WriteLine($"Cell B2 Value   : {firstSheet.Cells["B2"].Text}");
                Console.WriteLine($"Cell B2 Border  : {firstSheet.Cells["B2"].Style.Border.Top.Style}");
                Console.WriteLine("");

                ExcelWorksheet secondSheet = xlsFile.Workbook.Worksheets["Data"];
                Console.WriteLine($"Sheet 2 Data");
                Console.WriteLine($"Cell A2 Formula : {secondSheet.Cells["A2"].Formula}");
                Console.WriteLine($"Cell A2 Value   : {secondSheet.Cells["A2"].Text}");
            }
        }

        [Test]
        public void WorkingWithExcelDataReader()
        {
            System.Text.Encoding.RegisterProvider(System.Text.CodePagesEncodingProvider.Instance);
            //This Excel 2 Sheets: sheet1: CareerDevs , sheet2: Data
            using (FileStream xlsFile = File.Open("D:\\repository\\SDET\\javaSDET\\ExcelFiles\\excelDriven.xlsx", FileMode.Open, FileAccess.Read))
            {
                using (IExcelDataReader sheet = ExcelReaderFactory.CreateReader(xlsFile))
                {
                    sheet.Read(); //grabs First: CareerDevs sheet the first Row: 1
                   // Console.WriteLine(sheet.GetValue(0)); //  Output Row 1 Cell A: Header row Typical
                    sheet.NextResult(); // grabs Next Sheet: Data
                    sheet.Read(); // get Row 1: Header Row. Move onto
                    sheet.Read(); // to get Row 2.
                    Console.WriteLine("Data Item in sheet Row 2 Cell B: {0}",sheet.GetValue(1));  // Output Data Sheet Row 2 Cell B.
                }
            }
        }
    }
}