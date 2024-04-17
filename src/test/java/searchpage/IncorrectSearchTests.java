package searchpage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class IncorrectSearchTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1,dataProvider = "search")
    public void notCorrectSearchTest(String searchText) {
        get(Header.class).search(searchText);
        get(SearchResultPage.class).waitUntilPageLoaded();
        Assert.assertEquals(get(SearchResultPage.class).getSearchResult(), searchText, "Wrong search result");
        Assert.assertTrue(get(SearchResultPage.class).notFoundTitleIsDisplayed(), "Not found title doesn't display");
    }

    @DataProvider(name = "search")
    public Object[][] getData() {
        return new Object[][]{
                {"1111221"},
                {"`123"},
                {"!@#$%^&*()_zzx21421"},
        };
    }

}
