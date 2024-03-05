package filters;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Filters;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class PriceFilterTest extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1)
    public void defectPriceFilterTest() {
        get(Header.class).search("Штаны");
        get(SearchResultPage.class)
                .verifyPage()
                .openFilters();
        get(Filters.class)
                .verifyPage()
                .priceTo("200")
                .close();
        get(SearchResultPage.class)
                .verifyPage()
                .waitUntilPageLoaded();
        Assert.assertTrue(get(SearchResultPage.class).getProductPricesDouble().stream()
               .allMatch(price -> price <= 200), "Price filter works incorrectly");
    }

}
