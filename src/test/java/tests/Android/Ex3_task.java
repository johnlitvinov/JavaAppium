package tests.Android;

import lib.*;
import lib.ui.ArticalePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticalPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex3_task extends CoreTestCase {

    @Test
    public void testEx3()
    {
        if (Platform.getInstance().isIOS()){ return;}
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Kotlin";
        SearchPageObject.typeSearchLine(search_line);
        String firstArticleTitleToClick = "Kotlin Island";
        SearchPageObject.clickByArticaleWithSubstring(firstArticleTitleToClick);

        ArticalePageObject articalePageObject = ArticalPageObjectFactory.get(driver);
        articalePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(firstArticleTitleToClick);
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForSearchResultToDisappear(firstArticleTitleToClick);
    }
} //end class

