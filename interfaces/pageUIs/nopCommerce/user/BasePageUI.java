package pageUIs.nopCommerce.user;

public class BasePageUI {
	public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";

	public static final String CUSTOMER_INFO_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Customer info']";
	public static final String ADDRESS_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Addresses']";
	public static final String ORDER_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Orders']";
	public static final String DOWNLOADABLE_PRODUCTS_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Downloadable products']";
	public static final String BACK_IN_STOCK_SUB_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Back in stock subscriptions']";
	public static final String REWARDS_POINT_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Reward points']";
	public static final String CHANGE_PASSWORD_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Change password']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='My product reviews']";

	public static final String LOGOUT_LINK_AT_USER_PAGE = "xpath=//a[@class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN_PAGE = "xpath=//a[text()='Logout']";

	public static final String DYNAMIC_TEXTBOX_BY_ID = "XPATH=//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
}
