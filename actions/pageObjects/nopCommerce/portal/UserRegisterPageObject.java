package pageObjects.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	@Step("Click to Register button")
	public void clickToRegisterButton() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageFirstNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageLastNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessagePasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageEmailTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageConfirmPasswordTextbox() {
		waitForAllElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Input to FistName textbox with value: {0}")
	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	@Step("Input to LastName textbox with value: {0}")
	public void inputToLastNamextbox(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	@Step("Input to EmailAdress textbox with value: {0}")
	public void inputToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAdress);
	}

	@Step("Input to Password textbox with value: {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Input to Confirm Password textbox with value: {0}")
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MASSAGE);
		return getElementText(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MASSAGE);
	}

	@Step("Click to Logout link")
	public UserHomePageObject clickLogoutLink() {
		waitForElementVisible(driver, UserRegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void registerAsAccount(String FirstName, String LastName, String EmailAddress, String PasswordandConfirmPassword) {
		inputToFirstNameTextbox(FirstName);
		inputToLastNamextbox(LastName);
		inputToEmailTextbox(EmailAddress);
		inputToPasswordTextbox(PasswordandConfirmPassword);
		inputToConfirmPasswordTextbox(PasswordandConfirmPassword);
		clickToRegisterButton();
	}

	public boolean isMyAccountLinkIsUnDiplayed() {
		return isElementUndisplayed(driver, UserRegisterPageUI.MY_ACCOUT_LINK);
	}

	@Step("Check display My Acount link display")
	public boolean isMyAccountLinkIsDisplayed() {
		return isElementDisplay(driver, UserRegisterPageUI.MY_ACCOUT_LINK);
	}

}
