package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	public void enterFirstName(String firstNameText) {

		firstNameField.sendKeys(firstNameText);

	}

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	public void enterLastName(String lastNameText) {

		lastNameField.sendKeys(lastNameText);

	}

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	public void enterEmailAddress(String emailAddressText) {

		emailAddressField.sendKeys(emailAddressText);

	}

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	public void enterTelephone(String telephoneText) {

		telephoneField.sendKeys(telephoneText);

	}

	@FindBy(id = "input-password")
	private WebElement passwordField;

	public void enterPassword(String passwordText) {

		passwordField.sendKeys(passwordText);

	}

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	public void enterPasswordConfirm(String passwordText) {

		confirmPasswordField.sendKeys(passwordText);

	}

	@FindBy(name = "agree")
	private WebElement agreeField;

	public void selectYesNewsletterOption() {

		yesNewsletterOption.click();

	}

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	public void selectAgree() {

		agreeField.click();

	}

	@FindBy(xpath = "(//input[@name='newsletter'])[1]")
	private WebElement yesNewsletterOption;

	public AccountSuccessPage clickOnContinueButton() {

		continueButton.click();
		return new AccountSuccessPage(driver);

	}

}
