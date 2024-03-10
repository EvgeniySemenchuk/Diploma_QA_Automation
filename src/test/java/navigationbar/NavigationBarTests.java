package navigationbar;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.NavigationBarPage;
import pageObject.wildberries.SearchResultPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NavigationBarTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "breadCrumbs1")
    public void navigationBarSearchTest1(List<String> breadCrumbs, String lastCategory) {
        get(Header.class).openNavigationBar();
        get(NavigationBarPage.class)
                .search(breadCrumbs.get(0), lastCategory);
        get(SearchResultPage.class).verifyPage();
        Assert.assertEquals(get(SearchResultPage.class).getSearchResult(), lastCategory, "Navigation search failed");
        Assert.assertEquals(get(SearchResultPage.class).getNaviCategory(), breadCrumbs,"Navigation search failed");
    }

    @Test(priority = 2, dataProvider = "breadCrumbs2")
    public void navigationBarSearchTest2(List<String> breadCrumbs, String lastCategory) {
        get(Header.class).openNavigationBar();
        get(NavigationBarPage.class)
                .search(breadCrumbs.get(0), breadCrumbs.get(1), lastCategory);
        get(SearchResultPage.class).verifyPage();
        Assert.assertEquals(get(SearchResultPage.class).getSearchResult(), lastCategory, "Navigation search failed");
        Assert.assertEquals(get(SearchResultPage.class).getNaviCategory(), breadCrumbs,"Navigation search failed");
    }

    @DataProvider(name = "breadCrumbs1")
    public Object[][] getData1() {
        return new Object[][]{
                {List.of("Мужчинам"),"Брюки"},
                {List.of("Женщинам"),"Брюки"},
        };
    }

    @DataProvider(name = "breadCrumbs2")
    public Object[][] getData2() {
        return new Object[][]{
                {List.of("Игрушки", "Для малышей"),"Кубики"},
                {List.of("Красота", "Волосы"),"Стайлинг"},
        };
    }

}
