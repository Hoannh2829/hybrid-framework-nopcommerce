package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Common_01_Register extends BaseTest {

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private String userFirstName, userLastName;
	public static String userEmailAddress, userPassword;

	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userFirstName = "Automation";
		userLastName = "FC";
		userEmailAddress = "afc" + generateFakeNumner() + "@mail.com";
		userPassword = "123456";

		log.info("Register - Step 01: Click to Register link");
		userRegisterPage = userHomePage.clickToRegisterLink();

		log.info("Register - Step 02: Input to Email textbox with value:'" + userEmailAddress + "'");
		userRegisterPage.inputToEmailTextbox(userEmailAddress);

		log.info("Register - Step 03: Input to FirstName textbox with value:'" + userFirstName + "'");
		userRegisterPage.inputToFirstNameTextbox(userFirstName);

		log.info("Register - Step 04: Input to LastName textbox with value:'" + userLastName + "'");
		userRegisterPage.inputToLastNamextbox(userLastName);

		log.info("Register - Step 05: Input to Password textbox with value:'" + userPassword + "'");
		userRegisterPage.inputToPasswordTextbox(userPassword);

		log.info("Register - Step 06: Input to ConfirmPassword textbox with value:'" + userPassword + "'");
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);

		log.info("Register - Step 07: Click to Register button");
		userRegisterPage.clickToRegisterButton();

		log.info("Register - Step 08: Verify Register success message is displayed");
		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed.");

		log.info("Register - Step 09: Click logout link if login auto after registed");
		if (userRegisterPage.isMyAccountLinkIsUnDiplayed()) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		} else {
			userHomePage = userRegisterPage.clickLogoutLink();
		}
	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}
}
