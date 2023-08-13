package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {

	String extentReportPath = System.getProperty("user.dir")+"\\reports\\extentreport.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
	
	reporter.config().setTheme(Theme.DARK);
	reporter.config().setReportName("Tutorialsiinja Automation Results");
	reporter. config().setDocumentTitle("TestNG Test Results");

	extentReport = new ExtentReports();
	extentReport.attachReporter (reporter);
	
	//new code
	Properties Prop = new Properties();
	File PropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\resources\\data.properties");
	
	try {
		FileInputStream fisConfigProp = new FileInputStream(PropFile);
		Prop.load(fisConfigProp);
	}catch(Throwable e) {
		e.printStackTrace();
	}
	
	extentReport.setSystemInfo("Application URL",Prop.getProperty("url"));
	extentReport.setSystemInfo("Browser Name",Prop.getProperty("browser"));
	extentReport.setSystemInfo("Email",Prop.getProperty("validEmail"));
	extentReport.setSystemInfo("Password",Prop.getProperty("validPassword"));
	extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
	extentReport.setSystemInfo("Username",System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
	
//	extentReport. setSystemInfo( "Operating System", "Windows 10");
//	extentReport.setSystemInfo( "Tested By", "Farzana Shumi");

	return extentReport;
	
	}
	
}
