package searchpage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class DataExceedingLimitTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1 , dataProvider = "filepath")
    public void dataExceedingLimitTest(String filepath, Boolean expectedResult) {
            get(Header.class).searchByFile(filepath);
            get(SearchResultPage.class).waitUntilPageLoaded();
            Assert.assertEquals(get(SearchResultPage.class).searchResultIsDisplayed(), expectedResult, "Allowed limit in search field works incorrectly");
    }

    @DataProvider(name = "filepath")
    public Object[][] getData() {
        return new Object[][]{
                {"src/test/resources/files/484symbols.txt", true},
                {"src/test/resources/files/485symbols.txt", false},

        };
    }

}
