package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

abstract public class NavigationUI extends MainPageObject {


   protected static String
    MY_LIST,
    OPEN_NAVIGATION;



    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }


    public void openNAvigation(){
        if(Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION,"",5);
        } else {
            System.out.println("Method openNAvigation() do nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }


    public void clickMyLists(){

       if(Platform.getInstance().isMW()){
           this.tryClickElementWithFewAttempts(
                   MY_LIST,
                   "Cant find navigation button to My list",
                   5
           );
       }

        this.waitForElementAndClick(
                MY_LIST,
                "Can't find button 'MyList'",
                5
        );
    }
}//end class
