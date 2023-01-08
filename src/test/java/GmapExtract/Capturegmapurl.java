package GmapExtract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import BusinessFunctions.Browseropertaion;
import OR.Gmapobjects;
import Utility.Library;
import Utility.Readwriteexcel;

public class Capturegmapurl 
{
	static File src;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static FileOutputStream fos;
	String cityfilepath = "./Input\\city_input_metro.xlsx";
	String categoryfilepath = "./Input\\category_input.xlsx";
	public static WebDriver driver = null;
	AtomicInteger count = new AtomicInteger(1);
    @Test
    public void storeurl() throws Exception
    {
    	driver = Browseropertaion.openbrowser("Chrome");
    	CreateExcelgurl("./Input\\Catering.xlsx");
    	Browseropertaion.OpenUrl(driver, "https://www.google.com/search?q=Resort+in+Bihar&sz=0&tbm=lcl&ei=huK4YdLNOvqd4-EP2KmR8Aw&oq=Resort+in+Bihar&gs_l=psy-ab.3...0.0.0.8328680.0.0.0.0.0.0.0.0..0.0....0...1c..64.psy-ab..0.0.0....0.QskZbh54eY0#rlfi=hd:;si:;mv:[[25.0294973,85.42393249999999],[25.022230699999998,85.3959975]];tbs:lrf:!1m4!1u7!2m2!7m1!4e1!1m4!1u2!2m2!2m1!1e1!1m4!1u10!2m2!11m1!1e9!1m4!1u10!2m2!11m1!1e1!1m4!1u10!2m2!11m1!1e3!2m1!1e2!2m7!1e17!4m2!17m1!1e3!4m2!17m1!1e8!3sIAE,lf:1,lf_ui:6");
    	GetUrl(driver, cityfilepath, 0, 1, 507, categoryfilepath, 0, 2); //	135 163  190  42
    }
    
    public static void CreateExcelgurl(String path) throws IOException
    {
    	src = new File(path);
		wb = new XSSFWorkbook(); 
		sheet = wb.createSheet("wp_details");	
		row = sheet.createRow((short)0);  
		row.createCell(0).setCellValue("City");
		row.createCell(1).setCellValue("State");
		row.createCell(2).setCellValue("url");
	    fos = new FileOutputStream(src); 
		wb.write(fos);
    }
    
    public void GetUrl(WebDriver driver, String Citypath, int citysheetindex, int startcity, int citylength,  String Categorypath, int categorysheetindex,  int categorylength) throws Exception
    {
    	for(int i = startcity; i<citylength; i++)
    	{
    		String city = Readwriteexcel.readexcel(Citypath, citysheetindex, i, 0);
    		String state = Readwriteexcel.readexcel(Citypath, citysheetindex, i, 1);
    		int  sr = Readwriteexcel.numreadexcel(Citypath, citysheetindex, i, 2);
    		for(int j =0; j<categorylength; j++)
    		{
    			String category = Readwriteexcel.readexcel(Categorypath, categorysheetindex, j, 0);
    			String input = category+ " " +"in"+ " "+city;    			
    			System.out.println(input + " " +sr);
    			Library.cleartextbox(driver, Gmapobjects.gmappage.googlesearchbox);
    			Thread.sleep(3000);
    			Library.Entertext(driver, Gmapobjects.gmappage.googlesearchbox, input);
    			Thread.sleep(3000);
    			Actions act = new Actions(driver); 
    			act.sendKeys(Keys.ENTER).build().perform(); 
    			Thread.sleep(3000);
    			String requiredurl = driver.getCurrentUrl();
    			Thread.sleep(3000);
    			row = sheet.createRow((short)count.getAndIncrement());  
    			row.createCell(0).setCellValue(city);
    			row.createCell(1).setCellValue(state);
    			row.createCell(2).setCellValue(requiredurl);
    			row.createCell(3).setCellValue(category);
    		    fos = new FileOutputStream(src); 
    			wb.write(fos);
    		}
    	}
    }

}
