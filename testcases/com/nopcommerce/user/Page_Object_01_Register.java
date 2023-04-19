package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Page_Object_01_Register extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAdress = "afc" + registerPage.generateFakeNumber() + "@mail.com";
		password = "123456";
	}

	@Test
	public void Register_01_Empty_Data() {
		homePage.clickToRegisterLink();

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessagePasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox("123214325@235");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "Wrong email");
	}

	@Test
	public void Register_03_Register_Success() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		try {
			registerPage.clickLogoutLink();
		} catch (Exception e) {
			homePage.clickToRegisterLink();
		}

	}

	@Test
	public void Register_04_Existing_Email() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox("12345");
		registerPage.inputToConfirmPasswordTextbox("12345");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessagePasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("123457");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, password, emailAdress;
}
