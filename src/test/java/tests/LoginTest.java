package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;
	Logger log;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws Throwable {

		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void teardown() {

		driver.close();
		log.debug("Browser got closed");
	}

	@Test(dataProvider = "LoginData")
	public void login(String email, String password, String expectedResult) throws Throwable {

		LandingPage land = new LandingPage(driver);
		land.myAccountDropdown().click();
		land.loginOption().click();

		LoginPage login = new LoginPage(driver);
		login.emailAddressField().sendKeys(email);
		log.debug("Typed email address");
		login.passwordField().sendKeys(password);
		log.debug("Typed password");
		login.loginButton().click();
		log.debug("Login button got clicked");

		AccountPage account = new AccountPage(driver);

		String actualResult = null;

		try {

			if (account.editAccountInformationOption().isDisplayed()) {
				actualResult = "Successfull";
			}

		} catch (Exception e) {

			actualResult = "Failure";
		}

		Assert.assertEquals(actualResult, expectedResult);

	}

	@DataProvider
	public Object[][] LoginData() {
		Object[][] data = { { "shawnc850@gmail.com", "sumis2007", "Successfull" },
				{ "dummy@gmail.com", "1234@", "Failure" } };
		return data;
	}

}
