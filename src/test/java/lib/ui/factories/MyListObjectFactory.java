package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsObject;
import lib.ui.android.AndroidMyListObject;
import lib.ui.ios.iOSMyListObject;
import lib.ui.mobileWeb.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListObjectFactory {

    public static MyListsObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListObject(driver);
        } else if(Platform.getInstance().isIOS()){
            return new iOSMyListObject(driver);
        }else {
            return new MWMyListPageObject(driver);
        }
    }
}//end class

