package entrance;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.EntrancePage;
import pageObject.wildberries.Header;

public class BoundaryValueTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
        get(Header.class).entrance();
    }

    @Test(priority = 1, dataProvider = "phone")
    public void boundaryTest(String phoneNumber, Boolean result) {
        get(EntrancePage.class)
                .enterNumber(phoneNumber)
                .acceptAgreement();
        Assert.assertEquals(get(EntrancePage.class).codeBtnIsEnabled(), result, "Wrong expected result");
        get(EntrancePage.class)
                .acceptAgreement()
                .waitUntilPageLoaded()
                .clearInput();
    }

    @DataProvider(name = "phone")
    public Object[][] getData() {
        return new Object[][]{
                {"37533355566", false},
                {"375333555667", true},
                {"3753335556604", false},
        };
    }

}
