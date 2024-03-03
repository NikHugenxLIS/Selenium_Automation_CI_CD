package com.web.commonUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven
{
    //Identify Testcases column by scanning the entire 1st row
    //once column is identified then scan entire testcase coloum to identify purchase testcase row
    //After you grab purchase testcase row then pull all the data of that row and feed into
    public  ArrayList<String> getData(String testcaseName) throws IOException
    {
        //fileInputStream argument
        ArrayList<String> a = new ArrayList<String>();

        FileInputStream fis = new FileInputStream("C://Users//Lenovo//Desktop//Demodata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();

        for (int i=0; i<sheets; i++)
        {
            if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
            {
                XSSFSheet sheet=workbook.getSheetAt(i);
                //Identify Testcases column by scanning the entire 1st row
                Iterator<Row>  rows = sheet.iterator(); // sheet is collection of rows
                Row firstrow = rows.next();
                Iterator<Cell> cell = firstrow.cellIterator(); //row is collection of cells
                int k=0;
                int coloumn = 0;
                while(cell.hasNext())
                {
                    Cell value = cell.next();
                    if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
                    {
                        coloumn=k;
                    }
                    k++;
                }
                //System.out.println(coloumn);
                //once column is identified then scan entire testcase coloum to identify purchase testcase row
                while(rows.hasNext())
                {
                    Row r = rows.next();
                    if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName))
                    {
                        //After you grab purchase testcase row then pull all the data of that row and feed into test
                        Iterator<Cell> cv = r.cellIterator();
                        while(cv.hasNext())
                        {
                            Cell c = cv.next();
                            if(c.getCellType()==CellType.STRING)
                            {
                                a.add( c.getStringCellValue());
                            }
                            else
                            {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

                            }

                        }

                    }

                }

            }

        }
        return a;
    }
}
