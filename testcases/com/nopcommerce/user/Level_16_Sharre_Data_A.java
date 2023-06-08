package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Cookie;
import com.nopcommerce.common.Common_01_Register;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;

public class Level_16_Sharre_Data_A extends BaseTest {

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private String userEmailAddress, userPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmailAddress = Common_01_Register.userEmailAddress;
		userPassword = Common_01_Register.userPassword;

		log.info("Login - Step 01: Open login Page");
		userLoginPage = userHomePage.clickToLoginLink();

		log.info("Login - Step 02: Set cookie and reload page");
		userLoginPage.setCookie(driver, Common_01_Cookie.loggedCookies);
		sleepInSecond(3);
		userLoginPage.refreshToPage(driver);

		log.info("Login - Step 03: Verify My Account Link display");
		verifyTrue(userHomePage.isMyAccountLinkDisplayed());

		userEmailAddress = "afc" + generateFakeNumner() + "@mail.com";
		userPassword = "123456";

	}

	@Test
	public void Search_01_Emty_Data() {

	}

	@Test
	public void Search_02_Product_Name() {

	}

	@Test
	public void Search_03_Absolute_Name() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
