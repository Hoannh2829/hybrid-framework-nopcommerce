package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageUIs.jQuery.PageGenerateManager;

public class Level_10_DataTable_DataGird extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String customURL) {
		driver = getBrowserDriver(browserName, customURL);
		homePage = PageGenerateManager.getHomePageObject(driver);
	}

	// @Test
	// public void Table_01_Paging() {
	// homePage.openPagingByPageNumber("10");
	// Assert.assertTrue(homePage.isPageNumberActive("10"));
	// homePage.openPagingByPageNumber("6");
	// Assert.assertTrue(homePage.isPageNumberActive("6"));
	// homePage.openPagingByPageNumber("7");
	// Assert.assertTrue(homePage.isPageNumberActive("7"));
	//
	// }
	//
	// @Test
	// public void Table_02_Enter_To_Header() {
	// homePage.inputToHeaderTextboxByLabel("Females", "12");
	// homePage.inputToHeaderTextboxByLabel("Country", "Zimbabwe");
	// homePage.inputToHeaderTextboxByLabel("Males", "100");
	// homePage.inputToHeaderTextboxByLabel("Total", "200");
	// sleepInSecond(3);
	//
	// }
	//
	// @Test
	// public void Table_03_Check_Page() {
	// homePage.getValueEachRowAllPage();
	//
	// }
	@Test
	public void Table_04_Enter_To_Textbox() {
		homePage.enterToTextboxAtRowNumberByColumnName("Company", "1", "Zoced 96");
		homePage.enterToTextboxAtRowNumberByColumnName("Contact Person", "3", "Hehe");
		homePage.enterToTextboxAtRowNumberByColumnName("Order Placed", "3", "140");
		homePage.enterToTextboxAtRowNumberByColumnName("Member Since", "3", "29021996");

		homePage.selectDropDownByColumnNameAtRowNumber("Country", "2", "Hong Kong");
		homePage.selectDropDownByColumnNameAtRowNumber("Country", "3", "Japan");
		homePage.selectDropDownByColumnNameAtRowNumber("Country", "1", "Taiwan");

		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "2");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "1");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "3");
		sleepInSecond(3);
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "3");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "2");
		sleepInSecond(3);

		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		sleepInSecond(3);
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		sleepInSecond(3);
		homePage.clickToIconByRowNumber("2", "Move Up");
		sleepInSecond(3);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
