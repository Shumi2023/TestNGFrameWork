package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.SearchPage;
import resources.Base;

public class SearchTest extends Base{

	public WebDriver driver;

	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws Throwable {
		
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null) {
			driver.quit();
		}
		
	}
	
	@Test(priority=1)
	public void searchWithValidProductName() {
		
		LandingPage land = new LandingPage(driver);
		land.enterSearchTextIntoSearchField(prop.getProperty("validproduct"));
		SearchPage searchPage = land.clickOnSearchButton();
	
		Assert.assertTrue(searchPage.verifyTheDisplayOfHpProductInSearchResults());
		Assert.assertTrue(false);
	}
	
	@Test(priority=2)
	public void searchWithInvalidProductName() {
		LandingPage land = new LandingPage(driver);
		land.enterSearchTextIntoSearchField(prop.getProperty("nonexistingproduct"));
		SearchPage searchPage = land.clickOnSearchButton();
		
		Assert.assertEquals(searchPage.retrieveNoProductSearchMessage(),prop.getProperty("noproductinsearchmessage"));
	
	}
	
	@Test(priority=3)
	public void searchByNotProvidingAnyProductName() {
		
		LandingPage land = new LandingPage(driver);
		land.enterSearchTextIntoSearchField("");
		SearchPage searchPage = land.clickOnSearchButton();
		
		Assert.assertEquals(searchPage.retrieveNoProductSearchMessage(),prop.getProperty("noproductinsearchmessage"));
		
	}
	
	
	
}
