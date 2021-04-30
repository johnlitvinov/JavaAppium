package lib.ui;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

public static final String
    LOGIN_BUTTON="xpath://body/div/a[text()='Log in']",
    LOGIN_INPUT="css:input[name='wpName']",
    PASSWORD_INPUT="css:input[name='wpPassword']",
    SUBMIT_BUTTON="css:button#wpLoginAttempt";


    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON,"Can't find auth button",5);
        this.waitForElementAndClick(LOGIN_BUTTON,"Can't find and click auth button",5);
    }


    public void enterLoginData(String login,String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT,login,"Cant input login",10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT,login,"Cant input password",10);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Can't find and click submit button",5);
    }

}//end class
