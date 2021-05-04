package tests.Android;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.*;
import lib.ui.ArticalePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticalPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Ex6_task_tests")
public class Ex6_task extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("testEx6")
    @Description("Find Article, click on it and check title")
    @Step("Starting test 'testEx6'")
    @Severity(value = SeverityLevel.BLOCKER)
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

        //ArticalePageObject.takeScreenshot("testEx6");

        Assert.assertEquals(
                "Not equals text titles",
                articaleTitleToClick,
                actualArticaleTitle
        );
    }
}//end class


