package lib.ui.factories;


import lib.Platform;
import lib.ui.ArticalePageObject;
import lib.ui.android.AndroidArticalPageObject;
import lib.ui.ios.iOSArticalPageObject;
import lib.ui.mobileWeb.MWArticalPageObject;
import lib.ui.mobileWeb.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticalPageObjectFactory {

    public static ArticalePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticalPageObject(driver);
        }else if (Platform.getInstance().isIOS()){
            return new iOSArticalPageObject(driver);
        } else {
        return new MWArticalPageObject(driver);
    }
    }
}//end class
