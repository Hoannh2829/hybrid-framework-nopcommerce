package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.RegisterPageObject;

public class Page_Object_03_My_Account extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;

	private String emailAdress, emailAlternative, passwordEmail, alternatePassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();

		emailAdress = "huuhoantr" + generateFakeNumber() + "@gmail.com";
		emailAlternative = "automationfc" + generateFakeNumber() + "@gmail.com";
		passwordEmail = "123456";
		alternatePassword = "654321";
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		loginPage = new LoginPageObject(driver);
		myAccountPage = new MyAccountPageObject(driver);

	}

	private int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@Test
	public void TC_01_Update_Info_Customer() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox("Nguyen");
		registerPage.inputToLastNamextbox("Hoan");
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(passwordEmail);
		registerPage.inputToConfirmPasswordTextbox(passwordEmail);
		registerPage.clickToRegisterButton();
		try {
			homePage.clickToLoginLink();
			loginPage.inputToEmailTextbox(emailAdress);
			loginPage.inputToPasswordTextbox(passwordEmail);
			loginPage.clickToLoginButton();
		} catch (Exception e) {
			// TODO: handle exception
		}
		homePage.clickToMyAccountLink();
		myAccountPage.checkToCheckboxGenderMale();
		myAccountPage.inputToFirstNameTextbox("Automation");
		myAccountPage.inputToLastNameTextbox("FC");
		myAccountPage.checkToDrodownDateOfBirth("29");
		myAccountPage.checkToDrodownDateOfMonth("2");
		myAccountPage.checkToDrodownDateOfYear("1996");
		myAccountPage.inputToEmailTextbox(emailAlternative);
		myAccountPage.inputToCompanylTextbox("Automation FC");

		myAccountPage.clickToSaveInfoButton();
		Assert.assertEquals(myAccountPage.getUpdateMassageSuccessfull(), "The customer info has been updated successfully.");

		Assert.assertTrue(myAccountPage.isGenderMaleIsSelected());
		Assert.assertEquals(myAccountPage.getFirstNameSaved("value"), "Automation");

		Assert.assertEquals(myAccountPage.getLastNameSaved("value"), "FC");
		Assert.assertEquals(myAccountPage.getDateOfBirthDisplay(), "29");
		Assert.assertEquals(myAccountPage.getDateOfMonthDisplay(), "February");
		Assert.assertEquals(myAccountPage.getDateOfYearDisplay(), "1996");
		Assert.assertEquals(myAccountPage.getEmailDisplay("value"), emailAlternative);
		Assert.assertEquals(myAccountPage.getCompanyDisplay("value"), "Automation FC");
		myAccountPage.clickToCloseMessage();
	}

	@Test
	public void TC_02_Add_Adress() {
		homePage.refreshPage();
		homePage.clickToMyAccountLink();
		myAccountPage.clickToAddressesLink();
		myAccountPage.clickToAddAddressButton();

		myAccountPage.inputFirstNameAddressTextbox("Automation");
		myAccountPage.inputLastNameAddressTextbox("FC");
		myAccountPage.inputEmailAddressTextbox(emailAlternative);
		myAccountPage.inputCompanyAddressTextbox("Automation FC");
		myAccountPage.selectToDropdownAddressCountry("82");
		myAccountPage.selectToDropdownAddressState("0");
		myAccountPage.inputToAddressCity("Ha Noi");
		myAccountPage.inputToAddressAddress1("Phuong Ha Cau - Ha Dong");
		myAccountPage.inputToAddressAddress2("Phuong Duong Noi - Ha Dong");
		myAccountPage.inputToAddressZipCode("550555");
		myAccountPage.inputToAddressPhoneNumber("0987654321");
		myAccountPage.inputToAddressFaxNumber("0999999999");
		myAccountPage.clickToSaveAddressButton();

		Assert.assertEquals(myAccountPage.getNameCompanyAfterSaveAddress(), "Automation FC");
		Assert.assertEquals(myAccountPage.getEmailAfterSaveAddress(), "Email: " + emailAlternative);
		Assert.assertEquals(myAccountPage.getPhoneAfterSaveAddress(), "Phone number: " + "0987654321");
		Assert.assertEquals(myAccountPage.getFaxNumberAfterSaveAddress(), "Fax number: " + "0999999999");
		Assert.assertEquals(myAccountPage.getAddress1AfterSaveAddress(), "Phuong Ha Cau - Ha Dong");
		Assert.assertEquals(myAccountPage.getAddress2AfterSaveAddress(), "Phuong Duong Noi - Ha Dong");
		Assert.assertEquals(myAccountPage.getCityAfterSaveAddress(), "Ha Noi, 550555");
		Assert.assertEquals(myAccountPage.getCountryAfterSaveAddress(), "Viet Nam");

		myAccountPage.clickToCloseMessage();
	}

	// @Test
	// public void TC_03_Change_Password() {
	// refreshToPage(driver);
	// waitForElementClickable(driver, "//a[@class='ico-logout']");
	// clickToElement(driver, "//a[@class='ico-logout']");
	// waitForElementClickable(driver, "//a[@class='ico-login']");
	// clickToElement(driver, "//a[@class='ico-login']");
	//
	// waitForElementClickable(driver, "//input[@id='Email']");
	// sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
	// sendkeyToElement(driver, "//input[@id='Password']", passwordEmail);
	// clickToElement(driver, "//button[@class='button-1 login-button']");
	// sleepInSecond(3);
	//
	// waitForElementClickable(driver, "//a[@class='ico-account']");
	// clickToElement(driver, "//a[@class='ico-account']");
	// waitForElementClickable(driver, "//a[text()='Change password']");
	// clickToElement(driver, "//a[text()='Change password']");
	//
	// waitForElementClickable(driver, "//input[@id='OldPassword']");
	// sendkeyToElement(driver, "//input[@id='OldPassword']", passwordEmail);
	// sendkeyToElement(driver, "//input[@id='NewPassword']", alternatePassword);
	// sendkeyToElement(driver, "//input[@id='ConfirmNewPassword']", alternatePassword);
	// clickToElement(driver, "//button[@class='button-1 change-password-button']");
	// Assert.assertEquals(getElementText(driver, "//div[@class='bar-notification success']"), "Password was changed");
	//
	// waitForElementClickable(driver, "//span[@class='close']");
	// clickToElement(driver, "//span[@class='close']");
	// sleepInSecond(5);
	//
	// waitForElementClickable(driver, "//a[@class='ico-logout']");
	// clickToElement(driver, "//a[@class='ico-logout']");
	// waitForElementClickable(driver, "//a[@class='ico-login']");
	// clickToElement(driver, "//a[@class='ico-login']");
	//
	// waitForElementClickable(driver, "//input[@id='Email']");
	// sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
	// sendkeyToElement(driver, "//input[@id='Password']", passwordEmail);
	// clickToElement(driver, "//button[@class='button-1 login-button']");
	// Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and
	// try again.\nThe credentials provided are incorrect");
	//
	// waitForElementClickable(driver, "//input[@id='Email']");
	// sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
	// sendkeyToElement(driver, "//input[@id='Password']", alternatePassword);
	// clickToElement(driver, "//button[@class='button-1 login-button']");
	// Assert.assertTrue(isElementDisplay(driver, "//a[@class='ico-account']"));
	// }
	//
	// @Test
	// public void TC_04_Review_Product() {
	// waitForElementClickable(driver, "//img[@alt='nopCommerce demo store']");
	// clickToElement(driver, "//img[@alt='nopCommerce demo store']");
	// waitForElementClickable(driver, "//a[text()='Computers ']");
	// clickToElement(driver, "//a[text()='Computers ']");
	//
	// waitForElementClickable(driver, "//a[text()=' Desktops ']");
	// clickToElement(driver, "//a[text()=' Desktops ']");
	// waitForElementClickable(driver, "//a[text()='Build your own computer']");
	// clickToElement(driver, "//a[text()='Build your own computer']");
	//
	// waitForElementClickable(driver, "//a[text()='Add your review']");
	// clickToElement(driver, "//a[text()='Add your review']");
	// waitForElementClickable(driver, "//input[@id='AddProductReview_Title']");
	// sendkeyToElement(driver, "//input[@id='AddProductReview_Title']", "bad product");
	// sendkeyToElement(driver, "//textarea[@id='AddProductReview_ReviewText']", "sản phẩm không đúng yêu cầu");
	// checkToDefaultCheckboxRadio(driver, "//input[@id='addproductrating_1']");
	// clickToElement(driver, "//button[@name='add-review']");
	// Assert.assertEquals(getElementText(driver, "//span[@class='user']/span"), "Automation");
	// }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
