package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        //super.setUp();

        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenToPortrait();
        this.skipWelcomeScreensForIOSapp();
        this.openWikiWebPageForMobileWEB();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
        //super.tearDown();
    }

    @Step("Rotate Screen to Portrait")
    protected void rotateScreenToPortrait() {

        if (driver instanceof AppiumDriver) {

            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip Welcome Screens For IOS app")
    private void skipWelcomeScreensForIOSapp() throws InterruptedException {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.goThroughWelcomePages();
        }
    }

    @Step("Open Wiki WebPage For Mobile WEB")
    protected void openWikiWebPageForMobileWEB() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWEB()  does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile(){
         String path = System.getProperty("allure.results.directory");
         try {
             Properties properties = new Properties();
             FileOutputStream fileOutputStream = new FileOutputStream(path + "/environment.properties");
             properties.setProperty("Environment", Platform.getInstance().getPlatformVar());
             properties.store(fileOutputStream,"See https://github.com/allure-framework/allure-app/wiki/Environment");
             fileOutputStream.close();
         }catch (Exception e){
             System.err.println("IO problem when writting allure properties file");
             e.printStackTrace();
         }
    }
}//end class
