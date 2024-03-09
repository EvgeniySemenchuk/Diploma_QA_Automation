package favorites;

import entities.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.FavoritesPage;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class AddIFavoritesItemTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void addItemToFavoritesTest(Product product, String expectedSize) {
        get(Header.class).search(product);
        get(SearchResultPage.class)
                .waitUntilPageLoaded()
                .addToFavorites("Ручки", 3)
                .waitUntilPageLoaded()
                .addToFavorites("Ручки", 4)
                .waitUntilPageLoaded()
                .addToFavorites("Ручки", 5);
        get(Header.class).moveToFavorites();
        get(FavoritesPage.class)
                .verifyPage()
                .waitUntilPageLoaded();
        Assert.assertEquals(get(FavoritesPage.class).favoritesBasketSize(), expectedSize, "Wrong favorites basket size");
    }

    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Ручки");
                }}, "3"},
        };
    }

}
