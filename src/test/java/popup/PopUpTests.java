package popup;

import entities.Product;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.*;

public class PopUpTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void acceptPopUpTest(Product product, String expectedCount) {
        get(Header.class).search(product);
        get(SearchResultPage.class)
                .verifyPage()
                .addToBasket(product, 1)
                .waitUntilPageLoaded();
        get(PopUpPage.class).chooseSize(1);
        get(SearchResultPage.class)
                .verifyPage()
                .addToBasket(product,2)
                .waitUntilPageLoaded();
        get(PopUpPage.class).chooseSize(1);
        get(Header.class).moveToShoppingCard();
        Assert.assertEquals(get(BasketPage.class).getProductCount(), expectedCount, "Item wasn't added after pupUp");
        get(BasketPage.class)
                .waitUntilPageLoaded()
                .deleteProduct("", 1)
                .deleteProduct("", 2);
    }

    @Test(priority = 2, dataProvider = "item")
    public void popUpChoiceTest(Product product, String expectedCount) {
        get(Header.class).search(product);
        get(SearchResultPage.class)
                .verifyPage()
                .addToBasket(product, 6)
                .waitUntilPageLoaded();
        String size = get(PopUpPage.class).getSizesData().get(0);
        get(PopUpPage.class).chooseSize(size);
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded();
        Assert.assertEquals(get(BasketPage.class).productSize(product, "1"), size, "Size on basket doesn't equal to chosen one before");
    }


    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Кроссовки");
                }}, "2" },
        };
    }

}
