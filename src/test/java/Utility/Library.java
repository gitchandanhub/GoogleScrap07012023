package Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Library 
{
	static File src;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static FileOutputStream fos;
	   
      public static WebElement getObject(WebDriver driver, String objectdesc) throws Exception
      {
    	  String identifier = null;
    	  String description = null;
    	  WebElement element = null;
    	  By searchBy = null;
    	  if(objectdesc.equals(null)|| objectdesc.trim().equals(""))
    	  {
    		  throw new Exception("Object description must be provided");
    	  }
    	  else
    	  {
    		  identifier = objectdesc.split("~")[0].trim();
    		  description =  objectdesc.split("~")[1].trim(); 
    		  if(identifier.equals("xpath"))
    		  {
    			  searchBy = By.xpath(description);
    		  }
    		  else if(identifier.equals("xpath"))
    		  {
    			  searchBy = By.xpath(description);
    		  }
    		  else if(identifier.equals("name"))
    		  {
    			  searchBy = By.name(description);
    		  }
    		  else if(identifier.equals("id"))
    		  {
    			  searchBy = By.id(description);
    		  }
    		  else if(identifier.equals("cssselector"))
    		  {
    			  searchBy = By.cssSelector(description);
    		  }
    		  else if(identifier.equals("tagname"))
    		  {
    			  searchBy = By.tagName(description);
    		  }
    		  else if(identifier.equals("linktext"))
    		  {
    			  searchBy = By.linkText(description);
    		  }
    		  else if(identifier.equals("partiallinktext"))
    		  {
    			  searchBy = By.partialLinkText(description);
    		  }
    		  else  if(identifier.equals("classname"))
    		  {
    			  searchBy = By.className(description);
    		  }
    		  else
    		  {
    			  throw new Exception("Object description or searchtype is invalid");
    		  }
    		  element = driver.findElement(searchBy);
    	  }
    	  return element;
      }
      
      public static List<WebElement> getObjects(WebDriver driver, String objectdesc) throws Exception
      {
    	  List<WebElement> list = null;
    	  String identifier = null;
    	  String description = null;
    	  WebElement element = null;
    	  By searchBy = null;
    	  if(objectdesc.equals(null)|| objectdesc.trim().equals(""))
    	  {
    		  throw new Exception("Object description must be provided");
    	  }
    	  else
    	  {
    		  identifier = objectdesc.split("~")[0].trim();
    		  description =  objectdesc.split("~")[1].trim(); 
    		  if(identifier.equals("xpath"))
    		  {
    			  searchBy = By.xpath(description);
    		  }
    		  else if(identifier.equals("xpath"))
    		  {
    			  searchBy = By.xpath(description);
    		  }
    		  else if(identifier.equals("name"))
    		  {
    			  searchBy = By.name(description);
    		  }
    		  else if(identifier.equals("id"))
    		  {
    			  searchBy = By.id(description);
    		  }
    		  else if(identifier.equals("cssselector"))
    		  {
    			  searchBy = By.cssSelector(description);
    		  }
    		  else if(identifier.equals("tagname"))
    		  {
    			  searchBy = By.tagName(description);
    		  }
    		  else if(identifier.equals("linktext"))
    		  {
    			  searchBy = By.linkText(description);
    		  }
    		  else if(identifier.equals("partiallinktext"))
    		  {
    			  searchBy = By.partialLinkText(description);
    		  }
    		  else  if(identifier.equals("classname"))
    		  {
    			  searchBy = By.className(description);
    		  }
    		  else
    		  {
    			  throw new Exception("Object description or searchtype is invalid");
    		  }
    		  list = driver.findElements(searchBy);
    	  }
    	  return list;
      }
    
      public static void click(WebDriver driver, String locator) throws Exception
      {
    	  try
    	  {
    	  getObject(driver, locator).click();
    	  }
    	  catch(Exception e)
    	  {
    		  try
    		  {
    			  JavascriptExecutor jse = (JavascriptExecutor)driver;
    			  jse.executeScript("arguments[0].click();", getObject(driver, locator));
    		  }
    		  catch(Exception e2)
    		  {
    			  throw(e2);
    		  }
    	  }
      }
      
      public static String scraptext(WebDriver driver, String locator) throws Exception
      {
    	  String text = getObject(driver, locator).getText();
    	  return text;
      }
      
      public static String scraplink(WebDriver driver, String locator) throws Exception
      {
    	  String link = getObject(driver, locator).getAttribute("href");
    	  return link;
      }
      public static void pressEnterkey(WebDriver driver, String locator) throws Exception
      {
    	  getObject(driver, locator).click();
    	  Actions act = new Actions(driver); 
  		act.sendKeys(Keys.ENTER).build().perform(); 
      }
      public static void cleartextbox(WebDriver driver, String locator) throws Exception
      {
    	  getObject(driver, locator).clear();
    	  Thread.sleep(2000);
      }
      
      public static void Entertext(WebDriver driver, String locator, String data) throws Exception
      {
    	  getObject(driver, locator).sendKeys(data);
    	  Thread.sleep(2000);
      }
      
     
      
}
