package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "dowloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_SCREENSHORT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;

	public static final String DB_DEV_URL = "32.18.252.185:9860";
	public static final String DB_DEV_USER = "automationfc";
	public static final String DB_DEV_PASS = "P@ssw0rd1!";

	public static final String DB_USER_URL = "32.18.252.185:9860";
	public static final String DB_USER_USER = "automationfc";
	public static final String DB_USER_PASS = "P@ssw0rd1!";

	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final long RETRY_TEST_FAIL = 3;

	public static final String JAVA_VERSION = System.getProperty("java.version");
}
