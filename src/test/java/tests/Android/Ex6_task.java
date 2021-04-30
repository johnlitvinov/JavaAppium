package tests.Android;
import lib.*;

import junit.framework.TestCase;
import lib.ui.ArticalePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticalPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex6_task extends CoreTestCase {

    @Test
    public void testEx6()
    {
        if (Platform.getInstance().isIOS()){ return;}

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Kotlin";
        SearchPageObject.typeSearchLine(search_line);
        String articaleTitleToClick = "Kotline";
        SearchPageObject.clickByArticaleWithSubstring(articaleTitleToClick);
        ArticalePageObject ArticalePageObject = ArticalPageObjectFactory.get(driver);
        String actualArticaleTitle = ArticalePageObject.getArticaleTitle();

        TestCase.assertEquals(
                "Not equals text titles",
                articaleTitleToClick,
                actualArticaleTitle
        );
    }
}//end class


