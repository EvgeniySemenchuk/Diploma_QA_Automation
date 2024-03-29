package filters;

import io.cucumber.java.sl.In;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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

    @Test(priority = 1, dataProvider = "priceFilter")
    public void defectPriceFilterTest(String itemName, Integer priceFrom, Integer priceTo) {
        get(Header.class).search(itemName);
        get(SearchResultPage.class)
                .verifyPage()
                .openFilters();
        get(Filters.class)
                .verifyPage()
                .priceFrom(priceFrom.toString())
                .waitUntilPageLoaded()
                .priceTo(priceTo.toString())
                .close();
        get(SearchResultPage.class)
                .verifyPage()
                .waitUntilPageLoaded();
        Assert.assertTrue(get(SearchResultPage.class).getProductPricesDouble().stream()
               .allMatch(price -> (price >= priceFrom && price <= priceTo)), "Price filter works incorrectly");
    }

    @DataProvider(name = "priceFilter")
    public Object[][] getData() {
        return new Object[][]{
                {"Штаны", 130 ,200},
        };
    }


}
