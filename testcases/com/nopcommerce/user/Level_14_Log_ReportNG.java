package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class Level_14_Log_ReportNG extends BaseTest {

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
	public void TC_01_Register() {
		log.info("Register - Step 01: Open 'Register' Page");

		userRegisterPage = userHomePage.clickToRegisterLink();
		log.info("Register - Step 02: Enter to FirstName, LastName, EmailAddress, Password with value is: '" + userFirstName + "', '" + userLastName + "', '" + userEmailAddress + "' , '" + userPassword + "'");

		userRegisterPage.registerAsAccount(userFirstName, userLastName, userEmailAddress, userPassword);
		log.info("Register - Step 03: Click Logout link if auto login after register");

		try {
			userHomePage = userRegisterPage.clickLogoutLink();
		} catch (Exception e) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		}
		System.out.println(System.getProperty("user.dir"));

	}

	@Test
	public void TC_02_Login() {
		log.info("Login - Step 01: Open login Page");
		userLoginPage = userHomePage.clickToLoginLink();

		log.info("Login - Step 02: Login with info registered");
		userLoginPage.loginAsUser(userEmailAddress, userPassword);

		log.info("Login - Step 03: Verify My Account Link display");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		log.info("Login - Step 04: Clock to My Account Link");
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();

		log.info("Login - Step 05: Click to Logout Link");
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		log.info("Login - Step 03: Verify My Account Link undisplay");
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
