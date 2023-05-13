package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;
//import reportConfig.ExtentManager;

public class Level_15_Extend_Report_Screenshoot_V2 extends BaseTest {

	WebDriver driver;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	UserLoginPageObject userLoginPage;
	UserCustomerInfoPageObject userCustomerInfoPage;
	String userFirstName, userLastName, userEmailAddress, userPassword;

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
		// ExtentManager.startTest(method.getName(), "TC_01_Register");
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Open 'Register' Page");
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Open 'Register' Page");
		// userRegisterPage = userHomePage.clickToRegisterLink();
		//
		// ExtentManager.getTest().log(LogStatus.INFO,
		// "Register - Step 02: Enter to FirstName, LastName, EmailAddress, Password with value is: '" + userFirstName + "', '" + userLastName + "', '" +
		// userEmailAddress + "' , '" + userPassword + "'");
		// userRegisterPage.registerAsAccount(userFirstName, userLastName, userEmailAddress, userPassword);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03: Click Logout link if auto login after register");
		// try {
		// userHomePage = userRegisterPage.clickLogoutLink();
		// } catch (Exception e) {
		// userRegisterPage = userHomePage.clickToRegisterLink();
		// }
		// ExtentManager.endTest();
	}

	@Test
	public void TC_02_Login(Method method) {
		// ExtentManager.startTest(method.getName(), "TC_02_Login");
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 01: Open login Page");
		// userLoginPage = userHomePage.clickToLoginLink();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 02: Login with info registered");
		// userLoginPage.loginAsUser(userEmailAddress, userPassword);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 03: Verify My Account Link display");
		// Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 04: Clock to My Account Link");
		// userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 05: Click to Logout Link");
		// userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 06: Verify My Account Link undisplay");
		// Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		// ExtentManager.endTest();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
