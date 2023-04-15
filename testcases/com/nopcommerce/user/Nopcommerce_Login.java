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

public class Nopcommerce_Login extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress = "huuhoan" + generateNumber() + "@gmail.com";;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Login_Emty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		waitForElementClickable(driver, "//button[@class='button-1 login-button']");
		clickToElement(driver, "//button[@class='button-1 login-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		sendkeyToElement(driver, "//input[@id='Email']", "Huuhoan123");
		clickToElement(driver, "//button[@class='button-1 login-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Login_Unregistered_Email() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		sendkeyToElement(driver, "//input[@id='Email']", "huuhoan2829@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		clickToElement(driver, "//button[@class='button-1 login-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_Emty_Password() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		sendkeyToElement(driver, "//input[@id='FirstName']", "Nguyen");
		sendkeyToElement(driver, "//input[@id='LastName']", "Hoan");
		sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		clickToElement(driver, "//button[@id='register-button']");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
		clickToElement(driver, "//button[@class='button-1 login-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void TC_05_Login_Invalid_Password() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendkeyToElement(driver, "//input[@id='Password']", "123457");
		clickToElement(driver, "//button[@class='button-1 login-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Valid_Email_Password() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		clickToElement(driver, "//button[@class='button-1 login-button']");

		Assert.assertEquals(getPageTitle(driver), "nopCommerce demo store");
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
