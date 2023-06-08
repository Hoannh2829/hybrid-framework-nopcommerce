package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Common_01_Cookie extends BaseTest {

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private String userFirstName, userLastName;
	private String userEmailAddress, userPassword;
	public static Set<Cookie> loggedCookies;

	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userFirstName = "Automation";
		userLastName = "FC";
		userEmailAddress = "afc" + generateFakeNumner() + "@mail.com";
		userPassword = "123456";
		loggedCookies = driver.manage().getCookies();

		log.info("Pre-Condition - Step 01: Click to Register link");
		userRegisterPage = userHomePage.clickToRegisterLink();

		log.info("Pre-Condition - Step 02: Input to Email textbox with value:'" + userEmailAddress + "'");
		userRegisterPage.inputToEmailTextbox(userEmailAddress);

		log.info("Pre-Condition - Step 03: Input to FirstName textbox with value:'" + userFirstName + "'");
		userRegisterPage.inputToFirstNameTextbox(userFirstName);

		log.info("Pre-Condition - Step 04: Input to LastName textbox with value:'" + userLastName + "'");
		userRegisterPage.inputToLastNamextbox(userLastName);

		log.info("Pre-Condition - Step 05: Input to Password textbox with value:'" + userPassword + "'");
		userRegisterPage.inputToPasswordTextbox(userPassword);

		log.info("Pre-Condition - Step 06: Input to ConfirmPassword textbox with value:'" + userPassword + "'");
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);

		log.info("Pre-Condition - Step 07: Click to Register button");
		userRegisterPage.clickToRegisterButton();

		log.info("Pre-Condition - Step 08: Verify Register success message is displayed");
		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed.");

		log.info("Pre-Condition - Step 09: Click logout link if login auto after registed");
		if (userRegisterPage.isMyAccountLinkIsUnDiplayed()) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		} else {
			userHomePage = userRegisterPage.clickLogoutLink();
		}
		log.info("Pre-Condition - Step 10: Open login Page");
		userLoginPage = userHomePage.clickToLoginLink();

		log.info("Pre-Condition - Step 11: Login with info registered");
		userLoginPage.loginAsUser(userEmailAddress, userPassword);

		log.info("Pre-Condition - Step 12: Verify My Account Link display");
		verifyTrue(userHomePage.isMyAccountLinkDisplayed());

		loggedCookies = userHomePage.getAllCookies(driver);

		driver.quit();

	}

}
