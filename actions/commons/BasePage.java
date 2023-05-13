package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.portal.UserAddressPageObject;
import pageObjects.nopCommerce.portal.UserBackInStockPageObject;
import pageObjects.nopCommerce.portal.UserChangePasswordPageObject;
import pageObjects.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.portal.UserDownloadableProductPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.portal.UserOrdersPageObject;
import pageObjects.nopCommerce.portal.UserRewardPointPageObject;
import pageUIs.jQuery.UploadFile.HomePageUI;
import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage {
	public static BasePage getBasePageObbject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected String getWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	protected void switchWindowHByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switToWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	protected void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.toLowerCase().startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.toLowerCase().startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.toLowerCase().startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.toLowerCase().startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;
	}

	private String getDynamicLocator(String locatorType, String... values) {
		if (locatorType.toLowerCase().startsWith("xpath")) {
			locatorType = String.format(locatorType, (Object[]) values);
		}
		return locatorType;
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLoacator) {
		return driver.findElements(getByLocator(xpathLoacator));
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).getText();
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
		select.selectByValue(textItem);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}

	protected String getSelectedItemDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	protected void selectElementInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("argument[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	protected void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorByRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).size();
	}

	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElement(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementDisplay(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).isDisplayed();
	}

	protected boolean isElementDisplay(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	protected String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	protected void highlightElement(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	protected void sendkeyToElementByJS(WebDriver driver, String locatorType, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locatorType));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForElementInVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForAllElementInVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForAllElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "Orders":
			return PageGeneratorManager.getUserOrdersLinkPage(driver);
		case "Downloadable products":
			return PageGeneratorManager.getUserDownloadableProductPage(driver);
		case "Back in stock subscriptions":
			return PageGeneratorManager.getUserBackInStockPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public UserMyProductReviewPageObject openMyProductReviewLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserAddressPageObject openAddressesLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserOrdersPageObject openOrdersLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDER_LINK);
		clickToElement(driver, BasePageUI.ORDER_LINK);
		return PageGeneratorManager.getUserOrdersLinkPage(driver);
	}

	public UserDownloadableProductPageObject openDownloadableProductLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		clickToElement(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		return PageGeneratorManager.getUserDownloadableProductPage(driver);
	}

	public UserBackInStockPageObject openBackInStockLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.BACK_IN_STOCK_SUB_LINK);
		clickToElement(driver, BasePageUI.BACK_IN_STOCK_SUB_LINK);
		return PageGeneratorManager.getUserBackInStockPage(driver);
	}

	public UserRewardPointPageObject openRewardPointLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARDS_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARDS_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public UserChangePasswordPageObject openChangePasswordLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, BasePageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getUserChangePasswordPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER_PAGE);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN_PAGE);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN_PAGE);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}

		fullFileName = fullFileName.trim();

		getWebElement(driver, HomePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
	}

	public String getDirectorySlash(String folderName) {
		String separator = System.getProperty("file.separator");
		return separator + folderName + separator;
	}

}
