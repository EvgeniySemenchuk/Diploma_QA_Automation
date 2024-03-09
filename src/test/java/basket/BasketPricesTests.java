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

public class BasketPricesTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void addItemTest(Product product) {
        get(Header.class).search(product);
        get(SearchResultPage.class).verifyPage()
                .addToBasket(product, 1)
                .addToBasket(product, 2)
                .addToBasket(product, 3);
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded();
        Assert.assertEquals(get(BasketPage.class).getTotalPriceNum(), get(BasketPage.class).getTotalPriceBySum(), "The real total price doesn't equal to the one displayed on page");
    }

    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Портфель");
                }}},
                {new Product() {{
                    setProductName("Карандаш");
                }}},
                {new Product() {{
                    setProductName("Стул");
                }}},
        };
    }


}
