package resources;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utilities;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public Base(){
		
		prop = new Properties();
		
		String proPath = System.getProperty("user.dir")+ "\\src\\main\\java\\resources\\data.properties";

		try {
			FileInputStream fis = new FileInputStream(proPath);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
			
	}
	
	public WebDriver initializeDriver() throws Throwable {
		
//		prop = new Properties();
//		
//		String proPath = System.getProperty("user.dir")+ "\\src\\main\\java\\resources\\data.properties";
//		
//		FileInputStream fis = new FileInputStream(proPath);
//		
//		prop.load(fis);
		
		String BrowserName = prop.getProperty("browser");
		
		if(BrowserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();	
			
		}else if(BrowserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();	
		}
		
		 
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(Utilities.Implicit_Wait_Time, TimeUnit.SECONDS);
		 driver.manage().timeouts().pageLoadTimeout(Utilities.PageLoad_Wait_Time, TimeUnit.SECONDS);		 
		 return driver;
	}
	
//public String generateNewEmailTimeStamp() {
//		
//		Date date = new Date();
//		return date.toString().replace(" ","_").replace(":","_")+"@gmail.com";
//		
//		//Alternate code
//		//String timestamp = date.toString().replace(" ","_").replace(":","_");
//		//return "farzana"+timestamp+"@gmail.com";
		
	//}
	
//public String takeScreenshot(String testName,WebDriver driver) throws IOException {
//	
//	File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	String screenshotFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
//	FileUtils.copyFile(srcScreenshot, new File(screenshotFilePath));
//	return screenshotFilePath;
//}


}
