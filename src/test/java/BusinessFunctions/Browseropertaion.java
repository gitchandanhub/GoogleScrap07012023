package BusinessFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class Browseropertaion 
{
	public static WebDriver driver = null;
     public static WebDriver openbrowser(String browsername) throws InterruptedException
     {
    	 if(browsername.equalsIgnoreCase("CHROME"))
    	 {
    		    System.setProperty("webdriver.chrome.driver", "./Driver\\chromedriver108.exe");
    	    	System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
    	    	System.setProperty("webdriver.chrome.silentOutput", "true");  
    	    	driver = new ChromeDriver();
    	    	driver.manage().window().maximize();
    	    	Thread.sleep(2000);
    	 }
    	 else if(browsername.equalsIgnoreCase("Firefox"))
    	 {
    		 
    	 }
    	 else if(browsername.equalsIgnoreCase("Edge"))
    	 {
    		 
    	 }
    	 else
    	 {
    		 System.out.println("Please enter correct browser name");
    	 }
    	 return driver;
     }
     
     public static void OpenUrl(WebDriver driver, String url) throws InterruptedException
     {
    	 driver.navigate().to(url);
    	 Thread.sleep(3000);
    	 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
     }
     
     public static void refresh(WebDriver driver) throws InterruptedException
     {
    	 driver.navigate().refresh();
    	 Thread.sleep(3000);
     }
     
     public static void closebrowser(WebDriver driver) throws Exception
     {
    	 try
    	 {
    	 driver.quit();
    	 }
    	 catch(Exception e)
    	 {
    		 throw(e);
    	 }
     }
}
