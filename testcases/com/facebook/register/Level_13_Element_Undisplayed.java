package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {

	private WebDriver driver;
	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Display() {
		loginPage.clickToCreateNewAccountButton();

		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());

	}

	@Test
	public void TC_02_Verify_Element_Undisplay_In_DOM() {
		loginPage.inputToEmailAddressTextbox("automationfc@gmail.com");
		verifyTrue(loginPage.isReEnterEmailTextboxDisplay());
		sleepInSecond(3);
		loginPage.inputToEmailAddressTextbox("");
		sleepInSecond(3);
		verifyFalse(loginPage.isReEnterEmailTextboxDisplay());
	}

	@Test
	public void TC_03_Verify_Element_Undisplay_Not_In_DOM() {
		loginPage.clickToCloseIconAtRegisterForm();
		verifyTrue(loginPage.isReEnterEmailTextboxUnDisplay());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
