package com.web.commonUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public  String getStringDataFromExcel(String SheetName,int RowNum,int CellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./src/main/resources/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(RowNum);
		String data1 = row.getCell(CellNum).getStringCellValue();
		//System.out.println(data);
		wb.close();
		return data1;
		
		
	}
	public  String getIntDataFromExcel(String SheetName,int RowNum,int CellNum) throws EncryptedDocumentException, IOException, ParseException
	{
		FileInputStream fis=new FileInputStream("./src/main/resources/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(RowNum);
		String data = ((int)row.getCell(CellNum).getNumericCellValue()+ " ");		
		wb.close();
		return data;
	}
	
	
	public void setDataExcel(String sheetname ,int RowNum ,int cellNum, String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./src/main/resources/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row row = sh.getRow(RowNum);
		Cell cel = row.createCell(cellNum);
		cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/main/resources/Book1.xlsx");
		wb.write(fos);
		wb.close();
	}
	
	public  String getDateDataFromExcel(String SheetName,int RowNum,int CellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./src/main/resources/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(RowNum);
		Date date = row.getCell(CellNum).getDateCellValue();
		return date +"/";
	}

	

}
