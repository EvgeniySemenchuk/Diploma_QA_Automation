package basket;

import entities.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class AddNumberOfItemsTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void changeNumberItemTest(Product product) {
        get(Header.class).search(product);
        get(SearchResultPage.class)
                .verifyPage()
                .addToBasket("", 1);
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded();
        Assert.assertEquals(get(BasketPage.class).getTotalPriceNum(), get(BasketPage.class).getTotalPriceBySum(), "The real total price doesn't equal to the one displayed on page");
        for (int i = 0; i < 5; i++) {
            get(BasketPage.class).plusProductQuantity("");
            Assert.assertEquals(get(BasketPage.class).getTotalPriceNum(), get(BasketPage.class).getTotalPriceBySum(), "The real total price doesn't equal to the one displayed on page");
        }
    }


    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Книга");
                }}},
        };
    }
}
