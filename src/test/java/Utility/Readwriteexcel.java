package Utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Readwriteexcel 
{
	static File file;
	static FileInputStream fis;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static File src;
	static XSSFWorkbook wb;
	XSSFRow row;
	static FileOutputStream fos;
	public static String readexcel(String path, int sheetnum, int rownum, int cellnum) throws IOException
	{
		file = new File(path);
		fis = new FileInputStream(file);
		workbook = new 	XSSFWorkbook(fis);	
		sheet = workbook.getSheetAt(sheetnum);
		String data = sheet.getRow(rownum).getCell(cellnum).getStringCellValue();
		return data;
	}
	public static int numreadexcel(String path, int sheetnum, int rownum, int cellnum) throws IOException
	{
		file = new File(path);
		fis = new FileInputStream(file);
		workbook = new 	XSSFWorkbook(fis);	
		sheet = workbook.getSheetAt(sheetnum);
		int data = (int) sheet.getRow(rownum).getCell(cellnum).getNumericCellValue();
		return data;
	}
	public static void createexcel(String path, String sheetname) 
	{
		src = new File(path);
		wb = new XSSFWorkbook(); 
		sheet = wb.createSheet(sheetname);	
	}
	public static void writeexcel(int rownum, int cellnum, String data1, String data2, String data3, String data4, String data5, String data6) throws IOException
	{
		XSSFRow row = sheet.createRow((short)rownum); 
		row.createCell(cellnum).setCellValue(data1);
		row.createCell(cellnum+1).setCellValue(data2);
		row.createCell(cellnum+2).setCellValue(data3);
		row.createCell(cellnum+3).setCellValue(data4);
		row.createCell(cellnum+4).setCellValue(data5);
		row.createCell(cellnum+5).setCellValue(data6);
		fos = new FileOutputStream(src); 
		wb.write(fos);
	}
	public static void writeexcel2(int rownum, int cellnum, String data1, String data2, String data3, String data4) throws IOException
	{
		XSSFRow row = sheet.createRow((short)rownum); 
		row.createCell(cellnum).setCellValue(data1);
		row.createCell(cellnum+1).setCellValue(data2);
		row.createCell(cellnum+2).setCellValue(data3);
		row.createCell(cellnum+3).setCellValue(data4);
		fos = new FileOutputStream(src); 
		wb.write(fos);
	}
	public static int getlastrownum(String path, int sheetnum) throws IOException
	{
		file = new File(path);
		fis = new FileInputStream(file);
		workbook = new 	XSSFWorkbook(fis);	
		sheet = workbook.getSheetAt(sheetnum);
		int rownum = sheet.getLastRowNum();
		return rownum;
	}
	/*@Test
public void g() throws IOException
{
	String myurl  = Readwriteexcel.readexcel("D:\\JustDial\\Restaurant\\Ahmedabad\\input\\allcategory.xlsx", 0, 1, 1);
	System.out.println(myurl);
}*/
}