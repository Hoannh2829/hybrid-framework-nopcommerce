package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Nopcommerce_Search_Advanced_Search extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress = "huuhoan1996@gmail.com";
	String passwordEmail = "123456";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		openPageUrl(driver, "https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Search_Emty() {
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		waitForElementClickable(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendkeyToElement(driver, "//input[@id='Password']", passwordEmail);

		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//button[@class='button-1 search-button']");
		clickToElement(driver, "//button[@class='button-1 search-button']");
		Assert.assertEquals(getElementText(driver, "//div[@class='warning']"), "Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_Search_Data_Not_Exist() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Macbook Pro 2050");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"), "No products were found that matched your criteria.");

	}

	@Test
	public void TC_03_Search_Part_Of_Product_Name() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Lenovo");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='product-item' and @data-productid='3']//h2[@class='product-title']//a"), "Lenovo IdeaCentre 600 All-in-One PC");
		Assert.assertEquals(getElementText(driver, "//div[@class='product-item' and @data-productid='9']//h2//a"), "Lenovo Thinkpad X1 Carbon Laptop");

	}

	@Test
	public void TC_04_Search_With_Product_Name() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "ThinkPad X1 Carbon");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='product-item']//h2[@class='product-title']//a"), "Lenovo Thinkpad X1 Carbon Laptop");

	}

	@Test
	public void TC_05_Search_With_Parent_Categories() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Apple MacBook Pro");
		checkToDefaultCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "1");
		uncheckToDefaultCheckboxRadio(driver, "//input[@id='isc']");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"), "No products were found that matched your criteria.");

	}

	@Test
	public void TC_06_Advanced_Search_And_Sub_Categories() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Apple MacBook Pro");
		checkToDefaultCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "1");
		checkToDefaultCheckboxRadio(driver, "//input[@id='isc']");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='product-item']//h2[@class='product-title']//a"), "Apple MacBook Pro 13-inch");

	}

	@Test
	public void TC_07_Advanced_Search_With_Incorrect_Manufacturer() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Apple MacBook Pro");
		checkToDefaultCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "1");
		checkToDefaultCheckboxRadio(driver, "//input[@id='isc']");
		selectItemInDefaultDropdown(driver, "//select[@id='mid']", "2");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"), "No products were found that matched your criteria.");

	}

	@Test
	public void TC_08_Advanced_Search_Correct_Manufacturer() {
		waitForElementClickable(driver, "//div[@class='footer']//a[text()='Search']");
		clickToElement(driver, "//div[@class='footer']//a[text()='Search']");
		waitForElementClickable(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Apple MacBook Pro");
		checkToDefaultCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "1");
		checkToDefaultCheckboxRadio(driver, "//input[@id='isc']");
		selectItemInDefaultDropdown(driver, "//select[@id='mid']", "1");
		clickToElement(driver, "//button[@class='button-1 search-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='product-item']//h2[@class='product-title']//a"), "Apple MacBook Pro 13-inch");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
