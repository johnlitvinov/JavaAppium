package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TMP,
            SEARCH_EMPTY_RESULT;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String subsstring) {
        return SEARCH_RESULT_BY_SUBSTRING_TMP.replace("{SUBSTRING}", subsstring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can't find and click search init element", 10);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can't find element", 10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Can't input data", 10);
    }

    public void waitForSearchResult(String subsstring) {
        String search_result_xpath = getResultSearchElement(subsstring);
        this.waitForElementPresent(search_result_xpath, "Can't find Search_Result " + subsstring, 30);
    }

    public void waitForSearchResultToDisappear(String subsstring) {
        String search_result_xpath = getResultSearchElement(subsstring);
        this.waitForElementNotPresent(search_result_xpath, "Find Search_Result " + subsstring, 30);
    }

    public void clickByArticaleWithSubstring(String subsstring) {
        String search_result_xpath = getResultSearchElement(subsstring);
        this.waitForElementAndClick(search_result_xpath, "Can't find and click Search_Result " + subsstring, 30);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Find X button", 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Can't find X button", 10);
    }

    public void clickCancelButton() {
        waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can't tap X button", 10);
    }

    public void sendKeysForIOS(String value) {
        waitForElementAndSendKeysForIOS(
                SEARCH_INPUT,
                value,
                "Can't input Data to the search line",
                15
        );
    }
}//end class
