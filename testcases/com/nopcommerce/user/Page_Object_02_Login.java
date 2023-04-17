package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Page_Object_02_Login extends BasePage {
	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private String projectPath = System.getProperty("user.dir");
	private String validEmail, firstName, invalidEmail, notFoundEmail, lastName, password, invalidPassword;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		homePage = new HomePageObject(driver);
		loginPage = new LoginPageObject(driver);
		registerPage = new RegisterPageObject(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "huuhoan123";
		notFoundEmail = "cfa" + generateFakeNumber() + "@mail.com";
		validEmail = "afc" + generateFakeNumber() + "@mail.com";
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
