package pageObjects.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {

	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Login button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getErrorMassageEmailAtEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_EMAIL_MASSAGE);
		return getElementText(driver, UserLoginPageUI.ERROR_EMAIL_MASSAGE);
	}

	@Step("Input to Email textbox with value: {0}")
	public void inputToEmailTextbox(String emailAdress) {
		waitForAllElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAdress);
	}

	@Step("Input to Password textbox with value: {0}")
	public void inputToPasswordTextbox(String passwordEmail) {
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, passwordEmail);
	}

	public String getErrorMassageUnsuccessfull() {
		waitForAllElementVisible(driver, UserLoginPageUI.UNSUCCESSFULL_ERROR_MASSAGE);
		return getElementText(driver, UserLoginPageUI.UNSUCCESSFULL_ERROR_MASSAGE);
	}

	@Step("Click to Logout button")
	public void clickLogoutButton() {
		waitForElementVisible(driver, UserLoginPageUI.LOGOUT_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGOUT_BUTTON);
	}

	public UserHomePageObject loginAsUser(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

}
