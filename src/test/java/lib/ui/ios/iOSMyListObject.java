package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListObject extends MyListsObject {

    static {
        ARTICAL_TO_DELETE_TP = "xpath://XCUIElementTypeLink[contains(@name='{ARTICALENAME}')]";
    }

    public iOSMyListObject(RemoteWebDriver driver) {
        super(driver);
    }
}//end class
