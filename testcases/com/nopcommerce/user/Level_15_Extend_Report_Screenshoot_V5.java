package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_15_Extend_Report_Screenshoot_V5 extends BaseTest {

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
		ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Register - Step 02: Input to userFirstName, userLastName, userEmailAddress, userPassword textbox with value:" + " '" + userFirstName + "', '" + userLastName + "', '" + userEmailAddress + "','" + userPassword + "'");
		userRegisterPage.registerAsAccount(userFirstName, userLastName, userEmailAddress, userPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Try click to Logout Link if auto login after registered");
		try {
			userHomePage = userRegisterPage.clickLogoutLink();
		} catch (Exception e) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		}
	}

	@Test
	public void TC_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to 'Login' page");
		userLoginPage = userHomePage.clickToLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Input to Email-Password textbox with value: '" + userEmailAddress + "', '" + userPassword + "'");
		userLoginPage.loginAsUser(userEmailAddress, userPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Verify My Account Link display");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to My Account Link");
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Click to Logout Link");
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Verify My Account Link display");
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
