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
        get(Header.class).uploadImage("socks.png");
        get(SearchResultPage.class).moveToProduct("Носки", 1);
        System.out.println(get(ProductPage.class).getProductPrice());
    }


}
