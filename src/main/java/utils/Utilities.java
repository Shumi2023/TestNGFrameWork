package utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	public static final int Implicit_Wait_Time = 10;
	public static final int PageLoad_Wait_Time = 5;

	public static String generateNewEmailTimeStamp() {

		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_") + "@gmail.com";

		// Alternate code
		// String timestamp = date.toString().replace(" ","_").replace(":","_");
		// return "farzana"+timestamp+"@gmail.com";

	}

	public static String takeScreenshot(String testName, WebDriver driver)  {

		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			FileUtils.copyFile(srcScreenshot, new File(screenshotFilePath));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return screenshotFilePath;
	}

}
