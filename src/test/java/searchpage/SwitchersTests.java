package searchpage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.wildberries.Header;
import org.testng.annotations.BeforeTest;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.SearchResultPage;
import testngUtils.Retry;

public class SwitchersTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test()
    public void priceSwitcherTest() {
        get(Header.class).search("Штаны");
        get(SearchResultPage.class)
                .verifyPage()
                .chooseSwitcher("цене");
        get(SearchResultPage.class)
                .verifyPage()
                .waitUntilPageLoaded();
        System.out.println(get(SearchResultPage.class).getProductPricesDouble());
        Assert.assertTrue(get(SearchResultPage.class).verifyPriceSwitcher(), "Smth went wrong");
    }



}
