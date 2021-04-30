package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticalePageObject;
import lib.ui.factories.ArticalPageObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticalPageObject extends ArticalePageObject {

    static {

        TITLE = "xpath://XCUIElementTypeOther[@name='Kotline']";
        ADD_TO_MY_LIST_OPTION = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CROSS_BUTTON = "id:Back";
        CELL = "// XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell";


        MORE_OPTIONS = "xpath://android.widget.ImageView[@content-desc=\"More options\"]";
        ADD_TO_READING_LIST = "xpath://*[contains(@text,'Add to reading list')]";
        PRESS_FOLDER = "xpath://*[contains(@text,'Kotlin_folder')]";
    }

    public iOSArticalPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}//end class
