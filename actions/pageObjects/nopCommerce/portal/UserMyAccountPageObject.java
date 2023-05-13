package pageObjects.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserMyAccountPageUI;

public class UserMyAccountPageObject extends BasePage {
	private WebDriver driver;

	public UserMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void checkToCheckboxGenderMale() {
		waitForElementClickable(driver, UserMyAccountPageUI.GENDER_MALE_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserMyAccountPageUI.GENDER_MALE_CHECKBOX);
	}

	public void inputToFirstNameTextbox(String firstName) {
		sendkeyToElement(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, firstName, "FirstName");
	}

	public void inputToLastNameTextbox(String lastName) {
		sendkeyToElement(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, lastName, "LastName");
	}

	public void checkToDrodownDateOfBirth(String valueDay) {
		selectItemInDefaultDropdown(driver, UserMyAccountPageUI.DATE_OF_BIRTH_DAY_DROPDOWN, valueDay);
	}

	public void checkToDrodownDateOfMonth(String valueMonth) {
		selectItemInDefaultDropdown(driver, UserMyAccountPageUI.DATE_OF_BIRTH_MONTH_DROPDOWN, valueMonth);
	}

	public void checkToDrodownDateOfYear(String valueYear) {
		selectItemInDefaultDropdown(driver, UserMyAccountPageUI.DATE_OF_BIRTH_YEAR_DROPDOWN, valueYear);
	}

	public void inputToEmailTextbox(String emailAccount) {
		sendkeyToElement(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, emailAccount, "Email");
	}

	public void inputToCompanylTextbox(String companyName) {
		sendkeyToElement(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, companyName, "Company");
	}

	public void clickToSaveInfoButton() {
		clickToElement(driver, UserMyAccountPageUI.SAVE_INFO_BUTTON);
	}

	public String getUpdateMassageSuccessfull() {
		return getElementText(driver, UserMyAccountPageUI.UPDATE_MESSAGE_SUCCESSFULL);
	}

	public boolean isGenderMaleIsSelected() {
		return isElementSelected(driver, UserMyAccountPageUI.GENDER_MALE_CHECKBOX);
	}

	public String getFirstNameSaved(String attributeValue) {
		return getElementAttribute(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, attributeValue, "FirstName");
	}

	public String getLastNameSaved(String attributeValue) {
		return getElementAttribute(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, attributeValue, "LastName");
	}

	public String getDateOfBirthDisplay() {
		return getSelectedItemDropdown(driver, UserMyAccountPageUI.DATE_OF_BIRTH_DAY_DROPDOWN);
	}

	public String getDateOfMonthDisplay() {
		return getSelectedItemDropdown(driver, UserMyAccountPageUI.DATE_OF_BIRTH_MONTH_DROPDOWN);
	}

	public String getDateOfYearDisplay() {
		return getSelectedItemDropdown(driver, UserMyAccountPageUI.DATE_OF_BIRTH_YEAR_DROPDOWN);
	}

	public String getEmailDisplay(String attributeValue) {
		return getElementAttribute(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, attributeValue, "Email");
	}

	public void clickToCloseMessage() {
		waitForElementVisible(driver, UserMyAccountPageUI.CLOSE_ICON_UPDATE_MASSAGE_SUCCESSFULL);
		clickToElement(driver, UserMyAccountPageUI.CLOSE_ICON_UPDATE_MASSAGE_SUCCESSFULL);
	}

	public String getCompanyDisplay(String attributeValue) {
		return getElementAttribute(driver, UserMyAccountPageUI.DYNAMIC_TEXTBOX_CUSTOMER_INFO, attributeValue, "Company");
	}

	public void clickToAddressesLink() {
		waitForElementClickable(driver, UserMyAccountPageUI.ADDRESS_LINK);
		clickToElement(driver, UserMyAccountPageUI.ADDRESS_LINK);
	}

	public void clickToAddAddressButton() {
		waitForElementClickable(driver, UserMyAccountPageUI.ADD_ADDRESS_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.ADD_ADDRESS_BUTTON);
	}

	public void inputFirstNameAddressTextbox(String firstNameAddress) {
		sendkeyToElement(driver, UserMyAccountPageUI.FIRSTNAME_ADDRESS_TEXTBOX, firstNameAddress);
	}

	public void inputLastNameAddressTextbox(String lastNameAddress) {
		sendkeyToElement(driver, UserMyAccountPageUI.LASTNAME_ADDRESS_TEXTBOX, lastNameAddress);
	}

	public void inputEmailAddressTextbox(String emailAddress) {
		sendkeyToElement(driver, UserMyAccountPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);

	}

	public void inputCompanyAddressTextbox(String companyAddress) {
		sendkeyToElement(driver, UserMyAccountPageUI.COMPANY_ADDRESS_TEXTBOX, companyAddress);
	}

	public void selectToDropdownAddressCountry(String textItem) {
		selectItemInDefaultDropdown(driver, UserMyAccountPageUI.ADDRESS_COUNTRY_DROPDOWN, textItem);
	}

	public void selectToDropdownAddressState(String textItem) {
		selectItemInDefaultDropdown(driver, UserMyAccountPageUI.ADDRESS_STATE_DROPDOWN, textItem);
	}

	public void inputToAddressCity(String cityName) {
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_CITY_TEXTBOX, cityName);
	}

	public void inputToAddressAddress1(String address) {
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_1_TEXTBOX, address);
	}

	public void inputToAddressAddress2(String address) {
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_2_TEXTBOX, address);
	}

	public void inputToAddressZipCode(String zipCode) {
		sendkeyToElement(driver, UserMyAccountPageUI.ZIP_CODE_TEXTBOX, zipCode);
	}

	public void inputToAddressPhoneNumber(String phoneNumber) {
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_PHONE_NUMBER_TEXTBOX, phoneNumber);
	}

	public void inputToAddressFaxNumber(String faxNumber) {
		sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_FAX_NUMBER_TEXTBOX, faxNumber);
	}

	public void clickToSaveAddressButton() {
		clickToElement(driver, UserMyAccountPageUI.SAVE_ADDRESS_BUTTON);
	}

	public String getNameCompanyAfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.NAME_COMPANY_AFTER_SAVE_ADDRESS);
	}

	public String getEmailAfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.EMAIL_AFTER_SAVE_ADDRESS);
	}

	public String getPhoneAfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.PHONE_NUMBER_AFTER_SAVE_ADDRESS);
	}

	public String getFaxNumberAfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.FAX_NUMBER_AFTER_SAVE_ADDRESS);
	}

	public String getAddress1AfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_1_AFTER_SAVE_ADDRESS);
	}

	public String getAddress2AfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.ADDRESS_2_AFTER_SAVE_ADDRESS);
	}

	public String getCityAfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.CITY_AFTER_SAVE_ADDRESS);
	}

	public String getCountryAfterSaveAddress() {
		return getElementText(driver, UserMyAccountPageUI.COUNTRY_AFTER_SAVE_ADDRESS);
	}

	public void clickToChangePasswordButton() {
		waitForAllElementVisible(driver, UserMyAccountPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, UserMyAccountPageUI.CHANGE_PASSWORD_BUTTON);
	}

	public boolean isDisplayMyAccountLink() {
		return isElementDisplay(driver, UserMyAccountPageUI.MY_ACCOUNT_LINK);
	}

	public void inputToOldPasswordTextbox(String oldPassword) {
		waitForAllElementVisible(driver, UserMyAccountPageUI.OLD_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserMyAccountPageUI.OLD_PASSWORD_TEXTBOX, oldPassword);
	}

	public void inputToNewPasswordTextbox(String newPassword) {
		waitForElementVisible(driver, UserMyAccountPageUI.NEW_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserMyAccountPageUI.NEW_PASSWORD_TEXTBOX, newPassword);

	}

	public void inputToConfirmNewPasswordTextbox(String newPasswordConfirm) {
		waitForElementVisible(driver, UserMyAccountPageUI.CONFIRM_NEW_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserMyAccountPageUI.CONFIRM_NEW_PASSWORD_TEXTBOX, newPasswordConfirm);
	}

	public void clickToChangePasswordLink() {
		waitForElementVisible(driver, UserMyAccountPageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, UserMyAccountPageUI.CHANGE_PASSWORD_LINK);
	}

	public boolean isCustomerInfoIsDisplayed() {
		waitForElementVisible(driver, UserMyAccountPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplay(driver, UserMyAccountPageUI.CUSTOMER_INFO_HEADER);
	}

}
