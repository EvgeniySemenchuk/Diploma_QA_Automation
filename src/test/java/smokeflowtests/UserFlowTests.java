package smokeflowtests;

import entities.Product;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.*;

import java.util.List;

import static driver.DriverCreation.getWebDriver;

public class UserFlowTests extends BaseTest {

    @Test(priority = 1)
    public void openPageAndCookiesTest() {
        get(Header.class).open();
        Assert.assertEquals(getWebDriver().getCurrentUrl(), "https://www.wildberries.by/", "Wrong url");
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 2, dependsOnMethods = "openPageAndCookiesTest",dataProvider = "item")
    public void searchTest(Product product) {
        get(Header.class).search(product);
        get(SearchResultPage.class).verifyPage();
        Assert.assertEquals(get(SearchResultPage.class).getSearchResult(), "Стол", "Wrong search result");
    }

    @Test(priority = 3, dependsOnMethods = "searchTest")
    public void addToBasketTest() {
        get(SearchResultPage.class)
                .addToBasket(1)
                .waitUntilPageLoaded();
        Assert.assertEquals(get(Header.class).getNumberOfItemsInBasket(), "1");
        get(SearchResultPage.class)
                .addToBasket(2)
                .waitUntilPageLoaded();
        Assert.assertEquals(get(Header.class).getNumberOfItemsInBasket(), "2");
    }

    @Test(priority = 4, dependsOnMethods = "addToBasketTest")
    public void productPageTest() {
        get(SearchResultPage.class)
                .moveToProduct("", 6);
        String productName = get(ProductPage.class).getProductName();
        get(Header.class)
                .waitUntilPageLoaded()
                .search(get(ProductPage.class).getArticleNumber());
        Assert.assertEquals(get(ProductPage.class).getProductName(), productName, "search by article works incorrectly");
        get(Header.class).backToMainPage();
    }

    @Test(priority = 5, dependsOnMethods = "productPageTest")
    public void basketPriceTests() {
        get(Header.class).moveToShoppingCard();
        get(BasketPage.class)
                .waitUntilPageLoaded();
        for (int i = 0; i < 3; i++) {
            get(BasketPage.class).plusProductQuantity("", 1);
            Assert.assertEquals(get(BasketPage.class).getTotalPriceNum(), get(BasketPage.class).getTotalPriceBySum(), "The real total price doesn't equal to the one displayed on page");
        }
    }

    @Test(priority = 6, dependsOnMethods = "basketPriceTests", description = "price tests")
    public void basketDeleteItemTest() {
        get(BasketPage.class).deleteProduct("", 1);
        get(BasketPage.class).deleteProduct("", 1);
        Assert.assertTrue(get(BasketPage.class).basketIsEmplty());
    }

    @Test(priority = 7, dependsOnMethods = "basketDeleteItemTest", dataProvider = "item")
    public void addToFavouritesTests(Product product) {
        get(Header.class).search(product);
        get(SearchResultPage.class)
                .waitUntilPageLoaded()
                .addToFavorites(product, 3)
                .waitUntilPageLoaded()
                .addToFavorites(product, 4)
                .waitUntilPageLoaded()
                .addToFavorites(product, 5);
        get(Header.class).moveToFavorites();
        get(FavoritesPage.class)
                .verifyPage()
                .waitUntilPageLoaded();
        Assert.assertEquals(get(FavoritesPage.class).favoritesBasketSize(), "3", "Wrong favorites basket size");
    }

    @Test(priority = 8, dependsOnMethods = "addToFavouritesTests", dataProvider = "breadCrumbs")
    public void navigationBarSearchTest2(List<String> breadCrumbs, String lastCategory) {
        get(Header.class).openNavigationBar();
        get(NavigationBarPage.class)
                .search(breadCrumbs.get(0), breadCrumbs.get(1), lastCategory);
        get(SearchResultPage.class).verifyPage();
        Assert.assertEquals(get(SearchResultPage.class).getSearchResult(), lastCategory, "Navigation search failed");
        Assert.assertEquals(get(SearchResultPage.class).getNaviCategory(), breadCrumbs,"Navigation search failed");
    }

    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Стол");
                }}},

        };
    }

    @DataProvider(name = "breadCrumbs")
    public Object[][] getData2() {
        return new Object[][]{
                {List.of("Игрушки", "Для малышей"),"Кубики"},
                {List.of("Красота", "Волосы"),"Стайлинг"},
        };
    }




}
