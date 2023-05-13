package com.nopcommerce.user;

import java.lang.reflect.Method;

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

public class Level_15_Extend_Report_Screenshoot_V3 extends BaseTest {

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
	public void TC_01_Register(Method method) {

		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.registerAsAccount(userFirstName, userLastName, userEmailAddress, userPassword);

		try {
			userHomePage = userRegisterPage.clickLogoutLink();
		} catch (Exception e) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		}
	}

	@Test
	public void TC_02_Login(Method method) {

		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.loginAsUser(userEmailAddress, userPassword);

		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		userCustomerInfoPage = userHomePage.clickToMyAccountLink();

		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
