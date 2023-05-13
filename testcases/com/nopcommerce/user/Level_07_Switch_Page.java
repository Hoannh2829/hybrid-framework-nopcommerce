package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.portal.UserAddressPageObject;
import pageObjects.nopCommerce.portal.UserBackInStockPageObject;
import pageObjects.nopCommerce.portal.UserChangePasswordPageObject;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserDownloadableProductPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.portal.UserOrdersPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;
import pageObjects.nopCommerce.portal.UserRewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserOrdersPageObject ordersPage;
	private UserDownloadableProductPageObject downloadPage;
	private UserBackInStockPageObject backInStockPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserMyProductReviewPageObject myProductPage;

	private String validEmail, firstName, lastName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = commons.PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		validEmail = "afc" + generateFakeNumner() + "@mail.com";
		password = "123456";
	}

	@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNamextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();

		try {
			registerPage.clickLogoutLink();
		} catch (Exception e) {
			homePage.clickToRegisterLink();
		}
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();
		sleepInSecond(3);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_Customer_Info() {
		homePage.clickToMyAccountLink();
		customerInfoPage = homePage.openCustomerInfoPage(driver);

	}

	@Test
	public void User_04_Switch_Page() {
		addressPage = customerInfoPage.openAddressesLink(driver);
		downloadPage = addressPage.openDownloadableProductLink(driver);
		backInStockPage = downloadPage.openBackInStockLink(driver);
		ordersPage = backInStockPage.openOrdersLink(driver);
		changePasswordPage = ordersPage.openChangePasswordLink(driver);
		rewardPointPage = changePasswordPage.openRewardPointLink(driver);
		myProductPage = rewardPointPage.openMyProductReviewLink(driver);
		customerInfoPage = myProductPage.openCustomerInfoPage(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
