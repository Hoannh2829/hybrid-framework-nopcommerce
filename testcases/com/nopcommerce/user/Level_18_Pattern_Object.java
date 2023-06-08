package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Level_18_Pattern_Object extends BaseTest {

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private String userFirstName, userLastName, userEmailAddress, userPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userFirstName = "Automation";
		userLastName = "FC";
		userEmailAddress = "afc" + generateFakeNumner() + "@mail.com";
		userPassword = "123456";

	}

	@Test
	public void Register() {
		log.info("Register - Step 01: Click to Register link");
		userRegisterPage = userHomePage.clickToRegisterLink();

		log.info("Register - Step 02: Input to Email textbox with value:'" + userEmailAddress + "'");
		userRegisterPage.inputToTextboxByID(driver, "Email", userEmailAddress);

		log.info("Register - Step 03: Input to FirstName textbox with value:'" + userFirstName + "'");
		userRegisterPage.inputToTextboxByID(driver, "FirstName", userFirstName);

		log.info("Register - Step 04: Input to LastName textbox with value:'" + userLastName + "'");
		userRegisterPage.inputToTextboxByID(driver, "LastName", userLastName);

		log.info("Register - Step 05: Input to Password textbox with value:'" + userPassword + "'");
		userRegisterPage.inputToTextboxByID(driver, "Password", userPassword);

		log.info("Register - Step 06: Input to ConfirmPassword textbox with value:'" + userPassword + "'");
		userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", userPassword);

		log.info("Register - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByText(driver, "Register");

		log.info("Register - Step 08: Click logout link if login auto after registed");
		if (userRegisterPage.isMyAccountLinkIsUnDiplayed()) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		} else {
			userHomePage = userRegisterPage.clickLogoutLink();
		}
	}

	@Test
	public void TC_02_Login() {
		log.info("Login - Step 01: Open login Page");
		userLoginPage = userHomePage.clickToLoginLink();

		log.info("Login - Step 02: Login with info registered");
		userLoginPage.inputToTextboxByID(driver, "Email", userEmailAddress);
		userLoginPage.inputToTextboxByID(driver, "Password", userPassword);

		log.info("Login - Step 03: Click to login button");
		userHomePage = userLoginPage.clickToButtonByText(driver, "Log in");

		log.info("Login - Step 04: Verify My Account Link display");
		verifyTrue(userHomePage.isMyAccountLinkDisplayed());

		log.info("Login - Step 05: Clock to My Account Link");
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();

		log.info("Login - Step 06: Click to Logout Link");
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		log.info("Login - Step 07: Verify My Account Link undisplay");
		verifyFalse(userHomePage.isMyAccountLinkDisplayed());

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
