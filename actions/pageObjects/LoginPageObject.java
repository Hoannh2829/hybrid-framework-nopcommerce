package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {

	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMassageEmailAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.ERROR_EMAIL_MASSAGE);
		return getElementText(driver, LoginPageUI.ERROR_EMAIL_MASSAGE);
	}

	public void inputToEmailTextbox(String emailAdress) {
		waitForAllElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAdress);
	}

	public void inputToPasswordTextbox(String passwordEmail) {
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordEmail);
	}

	public String getErrorMassageUnsuccessfull() {
		waitForAllElementVisible(driver, LoginPageUI.UNSUCCESSFULL_ERROR_MASSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFULL_ERROR_MASSAGE);
	}

	public void clickLogoutLink() {
		waitForElementInVisible(driver, LoginPageUI.LOGOUT_LINK);
		clickToElement(driver, LoginPageUI.LOGOUT_LINK);
	}

}
