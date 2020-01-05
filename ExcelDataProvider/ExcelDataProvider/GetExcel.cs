using ExcelDataReader;
using NUnit.Framework;
using System;
using System.Data;
using System.IO;

namespace ExcelDataProvider
{
    public class Tests
    {

        [Test]
        public void GetExcelFile()
        {
            System.Text.Encoding.RegisterProvider(System.Text.CodePagesEncodingProvider.Instance);
            using (FileStream xlsFile = File.Open("D:\\repository\\SDET\\javaSDET\\ExcelFiles\\excelDriven.xlsx", FileMode.Open, FileAccess.Read))
            {
                using (IExcelDataReader sheet = ExcelReaderFactory.CreateReader(xlsFile))
                {
                    do
                    {
                        while (sheet.Read()) //Each ROW 
                                              // At end of Row will Return False 
                                              // and End While loop.
                        {
                            for (int column = 0; column < sheet.FieldCount; column++)
                            {
                                //Console.WriteLine(reader.GetString(column));//Will blow up if the value is decimal etc. 
                                Console.WriteLine(sheet.GetValue(column));//Get Value returns object
                            }
                        }
                    } while (sheet.NextResult()); //Move to NEXT SHEET
                                                   // At end of Row will Return False 
                                                   // and End While loop.

                }
            }
        }
    }
}