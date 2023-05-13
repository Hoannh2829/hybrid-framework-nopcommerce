package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private String validEmail, firstName, invalidEmail, notFoundEmail, lastName, password, invalidPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = commons.PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "huuhoan123";
		notFoundEmail = "cfa" + generateFakeNumner() + "@mail.com";
		validEmail = "afc" + generateFakeNumner() + "@mail.com";
		password = "123456";
		invalidPassword = "765432";
	}

	@Test
	public void TC_01_Login_Emty_Data() {
		homePage.clickToLoginLink();

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMassageEmailAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMassageEmailAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Login_Unregistered_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMassageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again." + "\nNo customer account found");
	}

	@Test
	public void TC_04_Login_Emty_Password() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		try {
			registerPage.clickLogoutLink();
		} catch (Exception e) {
			homePage.clickToRegisterLink();
		}

		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMassageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again." + "\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_Invalid_Password() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMassageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again." + "\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Valid_Email_Password() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
