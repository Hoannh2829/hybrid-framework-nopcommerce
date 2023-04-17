package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickToRegisterButton() {
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageFirstNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageLastNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessagePasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageConfirmPasswordTextbox() {
		waitForAllElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void inputToLastNamextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void inputToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAdress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MASSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MASSAGE);
	}

	public void clickLogoutLink() {
		waitForElementVisible(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}

}
