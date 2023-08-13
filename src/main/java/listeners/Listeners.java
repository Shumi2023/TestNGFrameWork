package listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utils.ExtentReporter;
import utils.Utilities;
import java.awt.Desktop;

public class Listeners extends Base implements ITestListener {
	
	public WebDriver driver = null;
	//ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onStart(ITestContext context) {

	extentReport = ExtentReporter.getExtentReport();	
		
	}

	
	@Override
	public void onTestStart(ITestResult result) {

		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+" Started executing");
		// configuration for parallel
		extentTestThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		//String testName= result.getName();
		
		//extentTest.log(Status.PASS, testName);
		
		extentTestThread.get().log(Status.PASS,testName+" Test Passed");
			
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//String testName= result.getName();
		
		//extentTest.fail(result.getThrowable());
		
		extentTestThread.get().fail(result.getThrowable());
		extentTestThread.get().log(Status.FAIL,testName+" Test Failed");
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());

		} catch (Exception e) {

			e.printStackTrace();
		}

		String screenshotFilePath = Utilities.takeScreenshot(testName,driver);
		extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testName);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		//String testName= result.getName();
		extentTestThread.get().skip(result.getThrowable());
		extentTestThread.get().log(Status.SKIP,testName+" Test Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	
	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
