package pageObjects.Sauce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.Saucelab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdowm(String textValue) {
		waitForElementVisible(driver, ProductPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.SORT_DROPDOWN, textValue);
	}

	public boolean isProductNameSortByAscending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		ArrayList<String> productSotrList = new ArrayList<String>();
		for (String product : productUIList) {
			productSotrList.add(product);
		}
		Collections.sort(productSotrList);
		return productSotrList.equals(productUIList);
	}

	public boolean isProductNameSortByDescending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		ArrayList<String> productSotrList = new ArrayList<String>();
		for (String product : productUIList) {
			productSotrList.add(product);
		}
		Collections.sort(productSotrList);
		Collections.reverse(productSotrList);
		return productSotrList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		ArrayList<Float> productUIList = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE);
		for (WebElement productPrice : productPriceText) {
			String priceText = productPrice.getText().substring(1);
			Float priceTextFloat = Float.parseFloat(priceText);
			productUIList.add(priceTextFloat);
		}
		ArrayList<Float> productSotrList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSotrList.add(product);
		}
		Collections.sort(productSotrList);
		return productSotrList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		ArrayList<Float> productUIList = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE);
		for (WebElement productPrice : productPriceText) {
			String priceText = productPrice.getText().substring(1);
			Float priceTextFloat = Float.parseFloat(priceText);
			productUIList.add(priceTextFloat);
		}
		ArrayList<Float> productSotrList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSotrList.add(product);
		}
		Collections.sort(productSotrList);
		Collections.reverse(productSotrList);
		return productSotrList.equals(productUIList);
	}

}
