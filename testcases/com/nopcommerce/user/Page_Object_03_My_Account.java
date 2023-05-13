package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserMyAccountPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Page_Object_03_My_Account extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;

	private String emailAdress, emailAlternative, passwordEmail, alternatePassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
		loginPage = new UserLoginPageObject(driver);
		myAccountPage = new UserMyAccountPageObject(driver);

		emailAdress = "huuhoantr" + myAccountPage.generateFakeNumber() + "@gmail.com";
		emailAlternative = "automationfc" + myAccountPage.generateFakeNumber() + "@gmail.com";
		passwordEmail = "123456";
		alternatePassword = "654321";

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

	@Test
	public void TC_03_Change_Password() {
		homePage.refreshPage();
		loginPage.clickLogoutButton();
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAlternative);
		loginPage.inputToPasswordTextbox(passwordEmail);
		loginPage.clickToLoginButton();
		homePage.clickToMyAccountLink();
		myAccountPage.clickToChangePasswordButton();

		myAccountPage.inputToOldPasswordTextbox(passwordEmail);
		myAccountPage.inputToNewPasswordTextbox(alternatePassword);
		myAccountPage.inputToConfirmNewPasswordTextbox(alternatePassword);
		myAccountPage.clickToChangePasswordLink();
		myAccountPage.clickToCloseMessage();
		sleepInSecond(5);

		loginPage.clickLogoutButton();
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAlternative);
		loginPage.inputToPasswordTextbox(passwordEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMassageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and " + "try again.\nThe credentials provided are incorrect");
		loginPage.inputToEmailTextbox(emailAlternative);
		loginPage.inputToPasswordTextbox(alternatePassword);
		loginPage.clickToLoginButton();
		Assert.assertTrue(myAccountPage.isDisplayMyAccountLink());
	}
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
