package GmapExtract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import BusinessFunctions.Browseropertaion;
import OR.Gmapobjects;
import Utility.Library;
import Utility.Readwriteexcel;

public class Capturegmapdata 
{
	public static WebDriver driver = null;
	String urlfilepath = "./Input\\mba.xlsx";
	String datafilepath = "./Output\\";
	String outputfilename = "MBADB3";
	static File src;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static FileOutputStream fos;
	String City = null;
	String category =null;
	String State = null;
	List<WebElement> list;
	AtomicInteger count = new AtomicInteger(1);
	@Test(enabled = true)
	public void scrapgmap() throws Exception
	{
		CreateExcelgmap(datafilepath, outputfilename);
		int lastrownum = Readwriteexcel.getlastrownum(urlfilepath, 0);
		for(int i= 57; i<72; i++)       //64
		{
			driver = Browseropertaion.openbrowser("Chrome");
			City = Readwriteexcel.readexcel(urlfilepath, 0, i, 0);
			
			try {   
			State = Readwriteexcel.readexcel(urlfilepath, 0, i, 1);
			}
			catch(Exception e)
			{}
			String url = Readwriteexcel.readexcel(urlfilepath, 0, i, 2);
			System.out.println(" "+i + " "+ url);
			category = Readwriteexcel.readexcel(urlfilepath, 0, i, 3);
			System.out.println(City + " " +State+ " " +url + " "+category);
			Browseropertaion.OpenUrl(driver, url);
			h(City,State, category,  driver);
            Browseropertaion.closebrowser(driver);
		}
	}

	public void CreateExcelgmap(String path, String name) throws IOException
	{
		src = new File(path+name+".xlsx");
		wb = new XSSFWorkbook(); 
		sheet = wb.createSheet("ME_details");	
		row = sheet.createRow((short)0); 
		row.createCell(0).setCellValue("Name");
		row.createCell(1).setCellValue("Address");
		row.createCell(2).setCellValue("Zipcode");
		row.createCell(3).setCellValue("Number");
		row.createCell(4).setCellValue("website");
		row.createCell(5).setCellValue("City");
		row.createCell(6).setCellValue("State");
		row.createCell(7).setCellValue("Category");
		fos = new FileOutputStream(src); 
		wb.write(fos);
	}

	public void h(String city, String state, String category, WebDriver driver) throws Exception
	{	
      list =  Library.getObjects(driver, Gmapobjects.gmappage.googlesearchresults);
      try
      {
		System.out.println(list.size());
      }
      catch(Exception e)
      {
    	  System.out.println(e);
      }
      try
      {
		if(list.size()==0)
		{
			driver.navigate().refresh();
			Thread.sleep(2000);
			Library.click(driver, Gmapobjects.gmappage.googlesearchbox);			
			Actions act = new Actions(driver); 
			act.sendKeys(Keys.ENTER).build().perform(); 
			Thread.sleep(4000);
			list =  Library.getObjects(driver, Gmapobjects.gmappage.googlesearchresults);
			System.out.println("size of list after ente " +list.size());
			m(city, state, category, driver, list);

		}
		else
		{
			m(city, state, category, driver, list);
		}
      }
      catch(Exception e2)
      {
    	  
      }
      finally
      {
    	  m(city, state, category, driver, list);
      }
		list.clear();
	}
	public void m(String City, String State, String category, WebDriver driver, List<WebElement> list) throws InterruptedException, IOException
	{    	
		for(int i =0; i<list.size(); i++)
		{
			((JavascriptExecutor)driver).executeScript("scroll(10,400)");
			list.get(i).click();
			Thread.sleep(3000);
			row = sheet.createRow((short)count.getAndIncrement());
			row.createCell(5).setCellValue(City);
			row.createCell(6).setCellValue(State);
			row.createCell(7).setCellValue(category);
			fos = new FileOutputStream(src); 
			wb.write(fos);
			try
			{
				String name = list.get(i).getText();
				row.createCell(0).setCellValue(name);
				fos = new FileOutputStream(src); 
				wb.write(fos);
			}
			catch(Exception e2)
			{
				try
				{
                   String name =  Library.scraptext(driver, Gmapobjects.gmappage.Elename);
					row.createCell(0).setCellValue(name);
					fos = new FileOutputStream(src); 
					wb.write(fos);
				}
				catch(Exception e3)
				{
					try
					{
						String name = driver.findElement(By.xpath("//*[@id='akp_tsuid13']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[1]/div/div[1]/div/div[1]/h2/span")).getText();
						row.createCell(0).setCellValue(name);
						fos = new FileOutputStream(src); 
						wb.write(fos);
					}
					catch(Exception e8)
					{

					}
				}
			}
			try
			{
				String address = Library.scraptext(driver, Gmapobjects.gmappage.EleAddress);
				row.createCell(1).setCellValue(address);
				fos = new FileOutputStream(src); 
				wb.write(fos);
				 String[] zip = address.split("\\s+");
				  String zipcode = zip[zip.length-1];
				  if(NumberUtils.isNumber(zipcode))
				  {
				//	  System.out.println(zipcode);
					  row.createCell(2).setCellValue(zipcode);
						fos = new FileOutputStream(src); 
						wb.write(fos);
				  }
				
			}
			catch(Exception e4)
			{
				try
				{
					String address = Library.scraptext(driver, Gmapobjects.gmappage.EleAddress2);
					row.createCell(1).setCellValue(address);
					fos = new FileOutputStream(src); 
					wb.write(fos);
					String[] zip = address.split("\\s+");
					  String zipcode = zip[zip.length-1];
					  if(NumberUtils.isNumber(zipcode))
					  {
						//  System.out.println(zipcode);
						  row.createCell(2).setCellValue(zipcode);
							fos = new FileOutputStream(src); 
							wb.write(fos);
					  }
				}
				catch(Exception e5)
				{
					String address = null;
					row.createCell(1).setCellValue(address);
					fos = new FileOutputStream(src); 
					wb.write(fos);
				}
			}
			try
			{
				String number = Library.scraptext(driver, Gmapobjects.gmappage.Elenumber);
				row.createCell(3).setCellValue(number);
				fos = new FileOutputStream(src); 
				wb.write(fos);
			}
			catch(Exception e6)
			{
				try
				{
					String number = Library.scraptext(driver, Gmapobjects.gmappage.Elenumber2);
					row.createCell(3).setCellValue(number);
					fos = new FileOutputStream(src); 
					wb.write(fos);
				}
				catch(Exception e7)
				{
					try
					{
						String number = driver.findElement(By.xpath(".//*[@class = 'LrzXr zdqRlf kno-fv']")).getText();
						row.createCell(3).setCellValue(number);
						fos = new FileOutputStream(src); 
						wb.write(fos);	
					}
					catch(Exception e8)
					{
						try
						{
							String number = driver.findElement(By.cssSelector(".LrzXr.zdqRlf.kno-fv>span>span")).getText();
							row.createCell(3).setCellValue(number);
							fos = new FileOutputStream(src); 
							wb.write(fos);
						}
						catch(Exception e11)
						{
							String number = null;
							row.createCell(3).setCellValue(number);
							fos = new FileOutputStream(src); 
							wb.write(fos);
						}
					}
				}
			}
			try
			{
				
				if(Library.scraptext(driver, Gmapobjects.gmappage.Elewebsitetext).contains("Website") || Library.scraptext(driver, Gmapobjects.gmappage.Elewebsitetext).startsWith("web"))
				{
					String website = Library.scraplink(driver, Gmapobjects.gmappage.Elewebsite);
				row.createCell(4).setCellValue(website);
				fos = new FileOutputStream(src); 
				wb.write(fos);
			    }
			}
			catch(Exception e)
			{
				try
				{
					String website = Library.scraplink(driver, Gmapobjects.gmappage.Elewebsite);
					row.createCell(4).setCellValue(website);
					fos = new FileOutputStream(src); 
					wb.write(fos);
				}
				catch(Exception e9)
				{
					try
					{
						String website = driver.findElement(By.cssSelector(".CL9Uqc.ab_button")).getAttribute("href");
						row.createCell(4).setCellValue(website);
						fos = new FileOutputStream(src); 
						wb.write(fos);
					}
					catch(Exception e10)
					{
						String website = null;
						row.createCell(4).setCellValue(website);
						fos = new FileOutputStream(src); 
						wb.write(fos);
					}
				}
			}


			try
			{
				String Founded = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[9]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/async-local-kp[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/span[2]")).getText();
				row.createCell(8).setCellValue(Founded);
				fos = new FileOutputStream(src); 
				wb.write(fos);
			}
			catch(Exception e)
			{
				try
				{
					String Founded = driver.findElement(By.cssSelector("body.srp.tbo.vasq:nth-child(2) div.rfli.mdm:nth-child(3) div.BBj6N:nth-child(12) div.col:nth-child(3) div.rhstc5 div.r-iYSmFJGakXPQ:nth-child(2) div.iYSmFJGakXPQ-jWD6pBvCV60.h2yBfgNjGpc__inline-item-view.r-iCW_xhUARSoI async-local-kp.iCW_xhUARSoI-AjwZTeXvKls.r-iMpgniV_zCxk div.iMpgniV_zCxk-AjwZTeXvKls.y.yf div.immersive-container:nth-child(2) div.akp_tsuid3.akp-el div.t3HED div.g.kno-kp.mnr-c.g-blk div.kp-blk.knowledge-panel.Wnoohf.OJXvsb div.xpdopen div.ifM9O:nth-child(1) div:nth-child(2) div.mod:nth-child(4) div.Z1hOCe div.zloOqf.PZPZlf.kno-fb-ctx > span.LrzXr.kno-fv")).getText();
					row.createCell(8).setCellValue(Founded);
					fos = new FileOutputStream(src); 
					wb.write(fos);
				}
				catch(Exception e9)
				{

				}
			}
			try
			{
				String Affiliation = driver.findElement(By.xpath("//*[@id='akp_tsuid3']/div/div/div/div/div/div[1]/div/div[1]/div[2]/div[5]/div/div/span[2]")).getText();
				row.createCell(9).setCellValue(Affiliation);
				fos = new FileOutputStream(src); 
				wb.write(fos);
			}
			catch(Exception e)
			{
				try
				{
					String Affiliation = driver.findElement(By.cssSelector("body.srp.tbo.vasq:nth-child(2) div.rfli.mdm:nth-child(3) div.BBj6N:nth-child(12) div.col:nth-child(3) div.rhstc5 div.r-iYSmFJGakXPQ:nth-child(2) div.iYSmFJGakXPQ-jWD6pBvCV60.h2yBfgNjGpc__inline-item-view.r-iCW_xhUARSoI async-local-kp.iCW_xhUARSoI-AjwZTeXvKls.r-iMpgniV_zCxk div.iMpgniV_zCxk-AjwZTeXvKls.y.yf div.immersive-container:nth-child(2) div.akp_tsuid3.akp-el div.t3HED div.g.kno-kp.mnr-c.g-blk div.kp-blk.knowledge-panel.Wnoohf.OJXvsb div.xpdopen div.ifM9O:nth-child(1) div.mod:nth-child(5) div.Z1hOCe div.zloOqf.PZPZlf.kno-fb-ctx span.LrzXr.kno-fv > a.fl:nth-child(1)")).getText();
					row.createCell(9).setCellValue(Affiliation);
					fos = new FileOutputStream(src); 
					wb.write(fos);
				}
				catch(Exception e9)
				{

				}
			}
		}

		list.clear();	

		k(City, State, category, driver);
	}
	
	// This method will click on gmap page next button
	public void k(String city, String state, String category, WebDriver driver) throws InterruptedException, IOException
	{
		try
		{
			driver.findElement(By.cssSelector("#pnnext>span")).click();
			Thread.sleep(3000);
			List<WebElement> list2 =  Library.getObjects(driver, Gmapobjects.gmappage.googlesearchresults);
			m(city, state, category, driver, list2);
			//h();
		}
		catch(Exception e)
		{
			System.out.println("completed");
		}
	}

}
