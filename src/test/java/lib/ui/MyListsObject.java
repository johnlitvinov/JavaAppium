package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsObject extends MainPageObject {

    protected static String
            FOLDER_NAME_TP,
            ARTICAL_BY_TITTLE,
            REMOVE_FROM_SAVED_BUTTON,
            ARTICAL_TO_DELETE_TP;

    private static String getArticalToDeleteTp(String articale_name) {
        return ARTICAL_TO_DELETE_TP.replace("{ARTICALENAME}", articale_name);
    }

    private static String getFolderByXpath(String name_folder) {
        return FOLDER_NAME_TP.replace("{FOLDERNAME}", name_folder);
    }

    public MyListsObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_folder) {
        String folder_xpath = getFolderByXpath(name_folder);
        this.waitForElementAndClick(
                folder_xpath,
                "Can't find folder ",
                5
        );
    }

    public void waitForArticaletitleDisappeare(String articale_title) {
        String art_name = getArticalToDeleteTp(articale_title);
        this.waitForElementNotPresent(art_name, "Can't delete articale", 20);
    }

    public void waitForArticaletitleToAppeare(String articale_title) {
        String art_name = getArticalToDeleteTp(articale_title);
        this.waitForElementPresent(art_name, "Can't find saved title", 20);
    }

    private static String getRemoveBUttonByTitle(String articale_name) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articale_name);
    }

    public void swipeToDeleteArticle(String articale_name) {
        this.waitForArticaletitleToAppeare(articale_name);

        String art_name = getArticalToDeleteTp(articale_name);

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(
                    art_name,
                    "Can't delete  articale"
            );
        } else {
            String remove_locator = getRemoveBUttonByTitle(articale_name);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cant click button to remove article from saved",
                    5
            );
        }


        if (Platform.getInstance().isIOS()) {
            this.clickElementToTHeRightUpperCorner(articale_name, "Cant find saved article");
        }

        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticaletitleDisappeare(articale_name);
    }


}//end class
