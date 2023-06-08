package pageUIs.jQuery;

import org.openqa.selenium.WebDriver;

import pageObjects.jQuery.HomePageObject;

public class PageGenerateManager {
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
}
