package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageUIs.jQuery.UploadFile.PageGenerateManager;

public class Level_11_Upload_Files extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	String wShirtFileName = "whiteShirt.png";
	String bShirtFileName = "blackShirt.png";
	String[] multipleFileName = { "whiteShirt.png", "blackShirt.png" };

	@Parameters({ "browser", "url" })

	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGenerateManager.getHomePageObject(driver);
	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, wShirtFileName);
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(wShirtFileName));
		Assert.assertTrue(homePage.isFileLImageUpLoadedByName(wShirtFileName));
	}

	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, multipleFileName);
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(wShirtFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(bShirtFileName));
		Assert.assertTrue(homePage.isFileLImageUpLoadedByName(wShirtFileName));
		Assert.assertTrue(homePage.isFileLImageUpLoadedByName(bShirtFileName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
