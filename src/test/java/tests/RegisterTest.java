package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.AccountSuccessPage;
import pageobjects.LandingPage;
import pageobjects.RegisterPage;
import resources.Base;
import utils.Utilities;

public class RegisterTest extends Base {

    public WebDriver driver;
    LandingPage land;
    RegisterPage registerPage;
    
    public RegisterTest() {
		super();
	}
    
	@BeforeMethod
	public void setup() throws Throwable {
		
		driver= initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
		
	}
	
	@Test(priority=1)
	public void registerWithMandatoryFields() {
		
		land = new LandingPage(driver);
		land.MyAccountOption().click();
		//land.ContinueButton().click();
		land.registerOption().click();
		registerPage = new RegisterPage(driver) ;
		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmailAddress(Utilities.generateNewEmailTimeStamp());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validpassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validpassword"));
		registerPage.selectAgree();
		AccountSuccessPage accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessMessage(),prop.getProperty("accountsuccessmessage"));
		
	}
	
	@Test(priority=2)
	public void registerWithAllFields() {
		
		
		land = new LandingPage(driver);
		land.MyAccountOption().click();
		//land.ContinueButton().click();
		
		land.registerOption().click();
		registerPage = new RegisterPage(driver) ;
		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmailAddress(Utilities.generateNewEmailTimeStamp());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validpassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validpassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectAgree();
		AccountSuccessPage accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessMessage(),prop.getProperty("accountsuccessmessage"));
		
	}
	
	
	
}
