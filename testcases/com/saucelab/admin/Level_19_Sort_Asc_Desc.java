package com.saucelab.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.Sauce.LoginPageObject;
import pageObjects.Sauce.PageGeneratorManager;
import pageObjects.Sauce.ProductPageObject;

public class Level_19_Sort_Asc_Desc extends BaseTest {

	WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToUserNameTextbox("standard_user");
		loginPage.inputToPasswordTextbox("secret_sauce");
		productPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		productPage.selectItemInProductSortDropdowm("Name (A to Z)");
		productPage.isProductNameSortByAscending();

		productPage.selectItemInProductSortDropdowm("Name (Z to A)");
		productPage.isProductNameSortByDescending();

	}

	@Test
	public void Sort_02_Price() {
		productPage.selectItemInProductSortDropdowm("Price (low to high)");
		productPage.isProductPriceSortByAscending();

		productPage.selectItemInProductSortDropdowm("Price (high to low)");
		productPage.isProductPriceSortByDescending();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
