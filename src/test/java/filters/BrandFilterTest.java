package filters;

import entities.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Filters;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;
import testngUtils.Retry;

public class BrandFilterTest extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "brandFilter")
    public void brandFilterTest(Product product) {
        get(Header.class).search(product);
        get(SearchResultPage.class)
                .verifyPage()
                .openFilters();
        get(Filters.class)
                .verifyPage()
                .selectBrand(product)
                .close();
        get(SearchResultPage.class)
                .verifyPage()
                .waitUntilPageLoaded();
        Assert.assertEquals(get(SearchResultPage.class).getTotalUsedFilters(), "1", "Wrong filter number");
        Assert.assertTrue(get(SearchResultPage.class).verifyAllBrandSearch(product.getProductBrand()), "Brand filter works incorrectly");
    }

    @DataProvider(name = "brandFilter")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Ручки");
                    setProductBrand("Brauberg");
                }}},
        };
    }

}
