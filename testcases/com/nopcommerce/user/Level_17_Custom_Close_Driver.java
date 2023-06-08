package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Level_17_Custom_Close_Driver extends BaseTest {

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
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
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

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
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

	}

	@Test
	public void Search_01_Emty() {
	}

	@Test
	public void Search_02_Blank() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
