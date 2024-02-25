package SomeTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.*;

public class ClassForTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        get(Header.class).open();
    }

    @Test
    public void searchTest() {
        get(Cookies.class).denyCookies();
        get(Header.class).waitUntilPageLoaded().search("Ручки");
        get(SearchResultPage.class)
                .waitUntilPageLoaded()
                .addToBasket("Ручки",1)
                .addToBasket("Ручки",2);
        System.out.println(get(SearchResultPage.class).getProductNamesData());
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class).waitUntilPageLoaded();
        System.out.println(get(BasketPage.class).getPricesData());


    }


}
