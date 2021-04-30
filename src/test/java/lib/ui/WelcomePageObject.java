package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static String
            FIRST_PAGE_LABLE = "id:The free encyclopedia",
            SEARCH_LINE = "id:Search Wikipedia",
            SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void goThroughWelcomePages() throws InterruptedException {
        this.waitForElementPresent(
                FIRST_PAGE_LABLE,
                "Can't find 'The free encyclopedia' label",
                10
        );

        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Can't find 'SKIP'",
                10
        ); //    this.clickByCordinate(90, 834);

        this.waitForElementPresent(
                SEARCH_LINE,
                "Can't find 'Search Wikipedia'",
                10
        );
    }
}//end class


