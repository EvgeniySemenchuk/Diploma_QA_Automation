package searchpage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class NotificationTextTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void notificationTextTest(String itemName) {
        get(Header.class).search(itemName);
        get(SearchResultPage.class).verifyPage()
                .addToBasket(itemName, 1);
        Assert.assertTrue(get(SearchResultPage.class).waitUntilPageLoaded().notificationTextIsDisplayed(), "Notification text is not displayed");
    }

    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {"Ручки"},
                {"Карандаш"},
                {"Линейка"},
        };
    }

}
