package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import lib.Platform;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(String locator, String attributeName, String expectedText, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutSeconds);
        Assert.assertEquals(
                "Titles are not equals",
                expectedText,
                element.getAttribute(attributeName)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }


    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){

        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try{
                this.waitForElementAndClick(locator,error_message,1);
                need_more_attempts = false;
            }catch (Exception e){
                if(current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator,error_message,1);
                }
            }
            ++current_attempts;
        }
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys();
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeElementToLeft(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    10
            );

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(left_x, middle_y));
            } else if (Platform.getInstance().isIOS()) {
                int offset_x = (-1 * element.getSize().getHeight());
                action.moveTo(PointOption.point(offset_x, 0));
            }
            action.release();
            action.perform();
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickByCordinate(int xPoint, int yPoint) throws InterruptedException {
        if (driver instanceof AppiumDriver) {
            try {
                TouchAction touchAction = new TouchAction((AppiumDriver) driver);
                touchAction.tap(PointOption.point(xPoint, yPoint)).perform();
                System.out.println("Clicked by Coordinate");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Fail to clickByCoordinate");
            }
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    private By getLocatorString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of Locator " + locator_with_type);
        }
    }

    public WebElement waitForElementAndSendKeysForIOS(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        new Actions(driver).moveToElement(element).sendKeys(value).perform();
        return element;
    }

    public void clickElementToTHeRightUpperCorner(String locator, String error_message) {

        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator + "/..", error_message, 20);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();
            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();

        } else {
            System.out.println("Method clickElementToTHeRightUpperCorner() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }


    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "", 1).getLocation().getY();

        if (Platform.getInstance().isMW()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Object js_result = javascriptExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }


    public void scrollWebPAgeTitleElementNOTVisiable(String locator, String error_message, long timeoutInSeconds, int max_swipes) {
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message, timeoutInSeconds);

        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }

    }

    public int getAmountsOfElement(String locator) {
        By by = this.getLocatorString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator) {
        return getAmountsOfElement(locator) > 0;
    }


    public String takeScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name +"_screenshot.png";
        try {
            FileUtils.copyFile(source,new File(path));
            System.out.println("The screenshot was taken");
        }catch (Exception e){
            System.out.println("Can't take the screenshot ");
            e.printStackTrace();
        }
        return path;
    }

    @Attachment
    public static byte[] screenShot(String path){
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e){
      System.out.println("Cannot get bytes from screenshot");
        }
        return bytes;
    }

}//end class