package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {
    static
    {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TMP = "xpath://XCUIElementTypeLink[[contains(@name='{SUBSTRING}')]";
        SEARCH_EMPTY_RESULT = "id:No results found";
    }
    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}//end class

