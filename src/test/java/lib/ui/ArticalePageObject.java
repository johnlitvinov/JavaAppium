package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticalePageObject extends MainPageObject {

    protected static String
            TITLE,
            OPTION_BUTTON,
            ADD_TO_MY_LIST_OPTION,
            ADD_TO_MY_LIST_OVERLAY,
            INPUT,
            REMOVE_FROM_MY_LIST_BUTTON,
            OK_BUTTON,
            CROSS_BUTTON,
            MORE_OPTIONS,
            ADD_TO_READING_LIST,
            PRESS_FOLDER,
            FOOTER_ELEMENT,
            CELL;

    public ArticalePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("waitForTitleElement")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                TITLE,
                "Can't find Title",
                10
        );
    }

    @Step("getArticaleTitle")
    public String getArticaleTitle() {
        WebElement title_element = waitForTitleElement();
        screenShot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }
    @Step("getArticaleTitle")
    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Can't find text 'Add to reading list'",
                5
        );


        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OPTION,
                "Can't find text 'Add to reading list'",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Can't find 'Got it' button ",
                10
        );

        this.waitForElementAndClear(
                INPUT,
                "Can't clear text",
                10
        );

        this.waitForElementAndSendKeys(
                INPUT,
                name_of_folder,
                "Can't input text",
                10
        );

        this.waitForElementAndClick(
                OK_BUTTON,
                "Can't find button 'OK'",
                5
        );
    }




    @Step("closeArticle")
    public void closeArticle() {

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    CROSS_BUTTON,
                    "Can't find button 'X'",
                    5
            );
        }else {
            System.out.println("Methods close articale() do noting" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("addtitle_to_actual_folder")
    public void addtitle_to_actual_folder() {
        this.waitForElementAndClick(
                MORE_OPTIONS,
                "Can't find text 'MoreOption",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_READING_LIST,
                "Can't find text 'Add to reading list'",
                5
        );

        this.waitForElementAndClick(
                PRESS_FOLDER,
                "Can't find text 'Kotlin_folder'",
                5
        );
    }

    @Step("addArticalToMeSavedList")
    public void addArticalToMeSavedList() {
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSaved();
        }
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OPTION,
                "Cant find option to saved Articale to MyList",
                10
        );
    }

    @Step("removeArticleFromSaved")
    public void removeArticleFromSaved(){
        if(this.isElementPresent(REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(REMOVE_FROM_MY_LIST_BUTTON,"Cant remove articale",10);
            this.waitForElementPresent(REMOVE_FROM_MY_LIST_BUTTON,"Cannot find remove button",10);
        }
    }
    @Step("checkCellOnTheScreen")
    public WebElement checkCellOnTheScreen() {
        return this.waitForElementPresent(
                CELL,
                "Can't find Title",
                10
        );
    }

    @Step("swipeToFooter")
    public void swipeToFooter(){
        if(Platform.getInstance().isMW()){
         this.scrollWebPAgeTitleElementNOTVisiable(
                 FOOTER_ELEMENT,
                 "swipeToFooter",
                 20,
                 40
         );
        }
    }

}//end class
