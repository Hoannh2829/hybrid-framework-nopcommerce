package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Nopcommerce_My_Account extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Nopcommerce_Login login = new Nopcommerce_Login();
	String emailAdress = "huuhoantr" + generateNumber() + "@gmail.com";
	String emailAlternative = "automationfc" + generateNumber() + "@gmail.com";
	String passwordEmail = "123456";
	String alternatePassword = "654321";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Update_Info_Customer() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		sendkeyToElement(driver, "//input[@id='FirstName']", "Nguyen");
		sendkeyToElement(driver, "//input[@id='LastName']", "Hoan");
		sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendkeyToElement(driver, "//input[@id='Password']", "passwordEmail");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "passwordEmail");
		clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(3);
		try {
			clickToElement(driver, "//a[@class='ico-login']");
			waitForElementClickable(driver, "//input[@id='Email']");
			sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
			sendkeyToElement(driver, "//input[@id='Password']", "passwordEmail");
			clickToElement(driver, "//button[@class='button-1 login-button']");
		} catch (Exception e) {
			// TODO: handle exception
		}

		clickToElement(driver, "//a[@class='ico-account']");
		waitForElementClickable(driver, "//input[@id='gender-male']");
		clickToElement(driver, "//input[@id='gender-male']");
		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sleepInSecond(5);
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", "29");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", "2");
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", "1996");
		sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
		sendkeyToElement(driver, "//input[@id='Company']", "Automation FC");
		clickToElement(driver, "//button[@id='save-info-button']");

		Assert.assertEquals(getElementText(driver, "//p[@class='content']"), "The customer info has been updated successfully.");
		Assert.assertTrue(isElementSelected(driver, "//input[@id='gender-male']"));
		sleepInSecond(5);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='FirstName']", "value"), "Automation");
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), "FC");
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), "FC");
		Assert.assertEquals(getSelectedItemDropdown(driver, "//select[@name='DateOfBirthDay']"), "29");
		Assert.assertEquals(getSelectedItemDropdown(driver, "//select[@name='DateOfBirthMonth']"), "February");
		Assert.assertEquals(getSelectedItemDropdown(driver, "//select[@name='DateOfBirthYear']"), "1996");
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Email']", "value"), emailAlternative);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Company']", "value"), "Automation FC");
		clickToElement(driver, "//span[@class='close']");
	}

	@Test
	public void TC_02_Add_Adress() {

		// clickToElement(driver, "//a[@class='ico-login']");
		// waitForElementClickable(driver, "//input[@id='Email']");
		// senkeyToElement(driver, "//input[@id='Email']", emailAlternative);
		// waitForElementClickable(driver, "//input[@id='Password']");
		// clickToElement(driver, "//button[@class='button-1 login-button']");
		refreshToPage(driver);
		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");
		waitForElementClickable(driver, "//div[@class='master-wrapper-content']//a[text()='Addresses']");
		clickToElement(driver, "//div[@class='master-wrapper-content']//a[text()='Addresses']");
		waitForElementClickable(driver, "//button[@class='button-1 add-address-button']");
		clickToElement(driver, "//button[@class='button-1 add-address-button']");

		waitForElementClickable(driver, "//input[@id='Address_FirstName']");
		sendkeyToElement(driver, "//input[@id='Address_FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='Address_LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Address_Email']", emailAlternative);
		sendkeyToElement(driver, "//input[@id='Address_Company']", "Automation FC");
		selectItemInDefaultDropdown(driver, "//select[@id='Address_CountryId']", "82");
		selectItemInDefaultDropdown(driver, "//select[@id='Address_StateProvinceId']", "0");
		sendkeyToElement(driver, "//input[@id='Address_City']", "Ha Noi");
		sendkeyToElement(driver, "//input[@id='Address_Address1']", "Phuong Ha Cau - Ha Dong");
		sendkeyToElement(driver, "//input[@id='Address_Address2']", "Phuong Duong Noi - Ha Dong");
		sendkeyToElement(driver, "//input[@id='Address_ZipPostalCode']", "550555");
		sendkeyToElement(driver, "//input[@id='Address_PhoneNumber']", "0987654321");
		sendkeyToElement(driver, "//input[@id='Address_FaxNumber']", "0999999999");

		clickToElement(driver, "//button[@class='button-1 save-address-button']");

		Assert.assertEquals(getElementText(driver, "//li[@class='name']"), "Automation FC");
		Assert.assertEquals(getElementText(driver, "//li[@class='email']"), "Email: " + emailAlternative);
		Assert.assertEquals(getElementText(driver, "//li[@class='phone']"), "Phone number: 0987654321");
		Assert.assertEquals(getElementText(driver, "//li[@class='fax']"), "Fax number: 0999999999");
		Assert.assertEquals(getElementText(driver, "//li[@class='address1']"), "Phuong Ha Cau - Ha Dong");
		Assert.assertEquals(getElementText(driver, "//li[@class='address2']"), "Phuong Duong Noi - Ha Dong");
		Assert.assertEquals(getElementText(driver, "//li[@class='city-state-zip']"), "Ha Noi, 550555");
		Assert.assertEquals(getElementText(driver, "//li[@class='country']"), "Viet Nam");
		clickToElement(driver, "//span[@class='close']");
	}

	@Test
	public void TC_03_Change_Password() {
		refreshToPage(driver);
		waitForElementClickable(driver, "//a[@class='ico-logout']");
		clickToElement(driver, "//a[@class='ico-logout']");
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		waitForElementClickable(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
		sendkeyToElement(driver, "//input[@id='Password']", "passwordEmail");
		clickToElement(driver, "//button[@class='button-1 login-button']");
		sleepInSecond(3);

		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");
		waitForElementClickable(driver, "//a[text()='Change password']");
		clickToElement(driver, "//a[text()='Change password']");

		waitForElementClickable(driver, "//input[@id='OldPassword']");
		sendkeyToElement(driver, "//input[@id='OldPassword']", "passwordEmail");
		sendkeyToElement(driver, "//input[@id='NewPassword']", "alternatePassword");
		sendkeyToElement(driver, "//input[@id='ConfirmNewPassword']", "alternatePassword");
		clickToElement(driver, "//button[@class='button-1 change-password-button']");
		Assert.assertEquals(getElementText(driver, "//div[@class='bar-notification success']"), "Password was changed");

		waitForElementClickable(driver, "//span[@class='close']");
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(5);

		waitForElementClickable(driver, "//a[@class='ico-logout']");
		clickToElement(driver, "//a[@class='ico-logout']");
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		waitForElementClickable(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
		sendkeyToElement(driver, "//input[@id='Password']", "passwordEmail");
		clickToElement(driver, "//button[@class='button-1 login-button']");
		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		waitForElementClickable(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAlternative);
		sendkeyToElement(driver, "//input[@id='Password']", "alternatePassword");
		clickToElement(driver, "//button[@class='button-1 login-button']");
		Assert.assertTrue(isElementDisplay(driver, "//a[@class='ico-account']"));
	}

	@Test
	public void TC_04_Review_Product() {
		waitForElementClickable(driver, "//img[@alt='nopCommerce demo store']");
		clickToElement(driver, "//img[@alt='nopCommerce demo store']");
		waitForElementClickable(driver, "//a[text()='Computers ']");
		clickToElement(driver, "//a[text()='Computers ']");

		waitForElementClickable(driver, "//a[text()=' Desktops ']");
		clickToElement(driver, "//a[text()=' Desktops ']");
		waitForElementClickable(driver, "//a[text()='Build your own computer']");
		clickToElement(driver, "//a[text()='Build your own computer']");

		waitForElementClickable(driver, "//a[text()='Add your review']");
		clickToElement(driver, "//a[text()='Add your review']");
		waitForElementClickable(driver, "//input[@id='AddProductReview_Title']");
		sendkeyToElement(driver, "//input[@id='AddProductReview_Title']", "bad product");
		sendkeyToElement(driver, "//textarea[@id='AddProductReview_ReviewText']", "sản phẩm không đúng yêu cầu");
		checkToDefaultCheckboxRadio(driver, "//input[@id='addproductrating_1']");
		clickToElement(driver, "//button[@name='add-review']");
		Assert.assertEquals(getElementText(driver, "//span[@class='user']/span"), "Automation");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int generateNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
