package tests.Android;

import io.qameta.allure.junit4.DisplayName;
import lib.*;
import lib.ui.ArticalePageObject;
import lib.ui.MyListsObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticalPageObjectFactory;
import lib.ui.factories.MyListObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class Ex5_task extends CoreTestCase {

    @Test
    @DisplayName("testEx5")
    public void testEx5() {

        String search_line = "Kotlin";
        String firstArticleTitle = "Kotlin Island";
        String secondArticleTitle = "Kotline";
        String folder = "Kotlin_folder";

        // SEARCH FIRST ITEM AND ADD IT TO THE FOLDER
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine(search_line);
        } else {
            SearchPageObject.sendKeysForIOS(search_line);
        }

        SearchPageObject.clickByArticaleWithSubstring(firstArticleTitle);

        ArticalePageObject articalePageObject = ArticalPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articalePageObject.addArticleToMyList(folder);
        } else {
            articalePageObject.addArticalToMeSavedList();
        }
        articalePageObject.closeArticle();

        // SEARCH Second ITEM AND ADD IT TO THE FOLDER
        SearchPageObject.initSearchInput();

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine(search_line);
        } else {
            SearchPageObject.sendKeysForIOS(search_line);
        }

        SearchPageObject.clickByArticaleWithSubstring(secondArticleTitle);


        if (Platform.getInstance().isAndroid()) {
            articalePageObject.addtitle_to_actual_folder();
        } else {
            articalePageObject.addArticalToMeSavedList();
        }

        articalePageObject.closeArticle();


        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();


        MyListsObject myListsObject = MyListObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsObject.openFolderByName(folder);
        }

        // DELETE ARTICLE TITLE
        myListsObject.swipeToDeleteArticle(firstArticleTitle);




        // CHECK ITEMS IN THE FOLDER
        SearchPageObject.waitForSearchResultToDisappear(firstArticleTitle);
        SearchPageObject.waitForSearchResult(secondArticleTitle);
        ArticalePageObject ArticalePageObject = ArticalPageObjectFactory.get(driver);
        String actualArticaleTitle = ArticalePageObject.getArticaleTitle();

        Assert.assertEquals("Not equals text titles", secondArticleTitle, actualArticaleTitle);
    }
}//end class