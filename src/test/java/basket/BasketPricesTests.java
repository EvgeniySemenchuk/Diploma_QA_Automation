package basket;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class BasketPricesTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void addItemTest(String item) {
        get(Header.class).search(item);
        get(SearchResultPage.class).verifyPage()
                .addToBasket(item, 1)
                .addToBasket(item, 2)
                .addToBasket(item, 3);
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded();
        Assert.assertEquals(get(BasketPage.class).getTotalPriceNum(), get(BasketPage.class).getTotalPriceBySum(), "The real total price doesn't equal to the one displayed on page");
    }

    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {"Портфель"},
                {"Карандаш"},
                {"Стул"},
        };
    }


}
