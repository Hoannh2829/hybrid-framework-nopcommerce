package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String userFirstName, userLastName, userEmailAddress, userPassword, adminEmailAddress, adminPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		userFirstName = "Automation";
		userLastName = "FC";
		userEmailAddress = "afc" + generateFakeNumner() + "@mail.com";
		userPassword = "123456";

	}

	@Test
	public void Role_01_User_To_Admin() {
		userRegisterPage = userHomePage.clickToRegisterLink();
		userRegisterPage.registerAsAccount(userFirstName, userLastName, userEmailAddress, userPassword);
		try {
			userHomePage = userRegisterPage.clickLogoutLink();
		} catch (Exception e) {
			userRegisterPage = userHomePage.clickToRegisterLink();
		}

		userLoginPage = userHomePage.clickToLoginLink();
		userLoginPage.loginAsUser(userEmailAddress, userPassword);

		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
