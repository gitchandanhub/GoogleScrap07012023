package OR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utility.Constants;

public class Gmapobjects
{

	public class gmappage
	{
		public static final String googlesearchbox = Constants.NAME+"~"+"q";
        public static final String googelsearchbox2 = Constants.XPATH+"~"+"//*[@id='lst-ib']";
        public static final String googlesearchresults = Constants.CSSSELECTOR+"~"+"div > div.dbg0pd> span";        
        public static final String Elename = Constants.XPATH+"~"+"//*[starts-with(@id, 'tsuid')]/div/div/a[1]/div/div/div[1]/span";
        public static final String Elename2 = Constants.XPATH+"~"+".//*[starts-with(@id, 'akp_tsuid')]/div/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/div[1]/div/div[2]/h2/span";
        public static final String EleAddress = Constants.XPATH+"~"+"//*[text() = 'Address']/parent::span/following-sibling::span";
        public static final String EleAddress2 = Constants.XPATH+"~"+"//*[@id='akp_tsuid13']/div/div[1]/div/div/div/div[1]/div/div[1]/div/div[3]/div/div[2]/div/div/span[2]";
        public static final String EleAddress3 = Constants.CSSSELECTOR+"~"+".EfDVh.wDYxhc>div:nth-child(1)>div>span:nth-child(2)";
        public static final String Elenumber = Constants.XPATH+"~"+"//*[text() = 'Phone']/parent::span/following-sibling::span";
        public static final String Elenumber2 = Constants.CSSSELECTOR+"~"+".LrzXr.zdqRlf.kno-fv>a";
        public static final String Elenumber3 = Constants.XPATH+"~"+".//*[starts-with(@aria-label, 'Call phone number')]";
        public static final String Elewebsitetext = Constants.XPATH+"~"+".//*[@class = 'kp-header']/div/div/div/div[2]/div/a/div";
        public static final String Elewebsite1 = Constants.XPATH+"~"+"//*[contains(text(),'Website')]/parent::a";
        public static final String Elewebsite = Constants.XPATH+"~"+".//*[text() = 'Website']//parent::div//parent::a";
        public static final String Elewebsite2 = Constants.XPATH+"~"+".//*[@class = 'kp-header']/div/div/div/div[2]/div/a"; 
	}
}
