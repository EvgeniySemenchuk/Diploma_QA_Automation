package searchpage;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.wildberries.Header;
import org.testng.annotations.BeforeTest;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.SearchResultPage;

import java.util.Arrays;
import java.util.List;

public class SwitchersTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }



}
