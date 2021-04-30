package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticalePageObject;
import lib.ui.MyListsObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticalPageObjectFactory;
import lib.ui.factories.MyListObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex11 extends CoreTestCase {
    @Test
    public void testEx11() {

        String search_line = "Kotlin";
        String firstArticleTitle = "Kotlin Island";
        String secondArticleTitle = "Kotline";
        String folder = "Kotlin_folder";

        String iosCellPath = "//XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell";
        String androidCellPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout";


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
        ArticalePageObject.checkCellOnTheScreen();
        assertEquals("Not equals text titles", secondArticleTitle, actualArticaleTitle);

        //ADDITIONAL CHECK
        //Here i check cells on the screen
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.waitForSearchResultToDisappear(androidCellPath);
        } else {
            SearchPageObject.waitForSearchResult(iosCellPath);
        }
    }
}//end class
