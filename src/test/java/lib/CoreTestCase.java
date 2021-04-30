package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        driver = Platform.getInstance().getDriver();
        this.rotateScreenToPortrait();
        this.skipWelcomeScreensForIOSapp();
        this.openWikiWebPageForMobileWEB();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenToPortrait() {

        if (driver instanceof AppiumDriver) {

            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomeScreensForIOSapp() throws InterruptedException {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.goThroughWelcomePages();
        }
    }

    protected void openWikiWebPageForMobileWEB() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWEB()  does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
}//end class
