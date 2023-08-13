package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	public LandingPage(WebDriver driver) {

	this.driver = driver;
	PageFactory.initElements(driver, this);

	}

	//@FindBy(xpath="//a[@title='My Account']")
	@FindBy(xpath="//a[text()='My Account']")
	
	private WebElement myAccountDropdown;

	public WebElement myAccountDropdown() {

	return myAccountDropdown;
 
	}
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	public WebElement loginOption() {
		return loginOption;
		
	}
	@FindBy(linkText="My Account")
	private WebElement MyAccountOption;
	public WebElement  MyAccountOption() {
		return  MyAccountOption;
	}
		
//	@FindBy(linkText="Continue")
//	private WebElement ContinueButton;
//	public WebElement ContinueButton() {
//	return ContinueButton;
	
	@FindBy(linkText="Register")
	
	private WebElement registerOption;
	
	public WebElement registerOption() {
		return registerOption;
		
	}
	@FindBy(name="search")
	private WebElement searchBoxField;
    public void enterSearchTextIntoSearchField(String searchText) {
	searchBoxField.sendKeys(searchText);
		
	}
	@FindBy(xpath="//i[contains(@class,'fa-search')]")
	private WebElement searchButton;
	public SearchPage clickOnSearchButton() {
	searchButton.click();
	return new SearchPage(driver);
		
	}
}
