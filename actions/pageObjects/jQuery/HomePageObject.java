package pageObjects.jQuery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public void inputToHeaderTextboxByLabel(String headerLable, String textValue) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLable);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, textValue, headerLable);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLable);
	}

	public boolean isPageNumberActive(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		return isElementDisplay(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public List<String> getValueEachRowAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGE);

		List<String> allRowValuesAllPage = new ArrayList<String>();
		for (int index = 1; index <= totalPage; index++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_INDEX, String.valueOf(index));
			sleepInSecond(1);

			List<WebElement> AllRowElementEachRow = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_PAGE);

			for (WebElement EachRow : AllRowElementEachRow) {
				allRowValuesAllPage.add(EachRow.getText());
			}
		}
		for (String value : allRowValuesAllPage) {
			System.out.println(value);
		}
		return allRowValuesAllPage;

	}

	public void enterToTextboxAtRowNumberByColumnName(String columnName, String rowIndex, String textValue) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, textValue, rowIndex, String.valueOf(columnIndex));
	}

	public void selectDropDownByColumnNameAtRowNumber(String columnName, String rowIndex, String valueToSelect) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToSelect, rowIndex, String.valueOf(columnIndex));
	}

	public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowIndex) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		checkToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
	}

	public void uncheckToCheckboxByColumnNameAtRowNumber(String columnName, String rowIndex) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		uncheckToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
	}

	public void clickToIconByRowNumber(String rowIndex, String iconName) {
		waitForElementClickable(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowIndex, iconName);
		clickToElement(driver, HomePageUI.ICON_NAME_BY_ROW_NUMBER, rowIndex, iconName);
	}

}
