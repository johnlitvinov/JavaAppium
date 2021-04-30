package tests.iOS;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.ios.iOSSearchPageObject;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;

public class GetStartedTest extends CoreTestCase {

@Test
    public void test()  {
//    if (Platform.getInstance().isAndroid()){ return;}

    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
    SearchPageObject.initSearchInput();
  }
}//end class
