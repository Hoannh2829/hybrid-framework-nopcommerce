package pageObject.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTOTN);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTOTN);
	}

	public boolean isEmailAddressTextboxDisplayed() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplay(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public boolean isReEnterEmailTextboxDisplay() {
		return isElementDisplay(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}

	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
	}

	public void clickToCloseIconAtRegisterForm() {
		waitForElementClickable(driver, LoginPageUI.ICON_CLOSE_AT_REGISTER_FORM);
		clickToElement(driver, LoginPageUI.ICON_CLOSE_AT_REGISTER_FORM);
	}

	public boolean isReEnterEmailTextboxUnDisplay() {
		return isElementUndisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}
}
