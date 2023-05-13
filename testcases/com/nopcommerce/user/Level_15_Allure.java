package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Level_15_Allure extends BaseTest {

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

	@Description("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_01_Register() {
		userRegisterPage = userHomePage.clickToRegisterLink();
		userRegisterPage.inputToEmailTextbox(userEmailAddress);
		userRegisterPage.inputToFirstNameTextbox(userFirstName);
		userRegisterPage.inputToLastNamextbox(userLastName);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
		userRegisterPage.clickToRegisterButton();
		if (userRegisterPage.isMyAccountLinkIsUnDiplayed()) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		} else {
			userHomePage = userRegisterPage.clickLogoutLink();
		}
	}

	@Description("Login to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_02_Login() {
		userLoginPage = userHomePage.clickToLoginLink();
		userLoginPage.inputToEmailTextbox(userEmailAddress);
		userLoginPage.inputToPasswordTextbox(userPassword);
		userLoginPage.clickToLoginButton();
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
