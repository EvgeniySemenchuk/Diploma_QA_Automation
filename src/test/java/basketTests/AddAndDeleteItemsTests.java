package basketTests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class AddAndDeleteItemsTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1,dataProvider = "item")
    public void addItemTest(String item, String addIndex, String deleteIndex) {
        get(Header.class).search(item);
        get(SearchResultPage.class).waitUntilPageLoaded()
                .addToBasket(item,1)
                .addToBasket(item,2)
                .addToBasket(item,3);
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded();
        Assert.assertEquals(get(BasketPage.class).getProductCount(), addIndex);
    }

    @Test(priority = 2, dataProvider="item")
    public void deleteItemTest(String item, String addIndex, String deleteIndex) {
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded()
                        .deleteProduct(item)
                        .deleteProduct(item)
                        .deleteProduct(item);
        Assert.assertEquals(get(BasketPage.class).getBasketSize().toString(), deleteIndex);
    }

    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {"Ручки", "3","6"},
                {"Стол", "6","3"},
                {"Стул", "9","0"},
        };
    }



}
