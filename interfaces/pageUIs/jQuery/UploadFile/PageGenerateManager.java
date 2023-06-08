package pageUIs.jQuery.UploadFile;

import org.openqa.selenium.WebDriver;

import pageObjects.jQuery.uploadFile.HomePageObject;

public class PageGenerateManager {
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
}
