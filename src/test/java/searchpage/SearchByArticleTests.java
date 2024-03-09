package searchpage;

import entities.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.ProductPage;
import pageObject.wildberries.SearchResultPage;

public class SearchByArticleTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "item")
    public void searchByArticleTest(Product product) {
        get(Header.class).search(product.getProductName());
        get(SearchResultPage.class)
                .verifyPage()
                .moveToProduct(product, 1);
        String productName = get(ProductPage.class).getProductName();
        get(Header.class)
                .waitUntilPageLoaded()
                .search(get(ProductPage.class).getArticleNumber());
        Assert.assertEquals(get(ProductPage.class).getProductName(), productName, "search by article works incorrectly");
        get(Header.class).backToMainPage();
    }



    @DataProvider(name = "item")
    public Object[][] getData() {
        return new Object[][]{
                {new Product() {{
                    setProductName("Спеши любить");
                }}},
                {new Product() {{
                    setProductName("Лучшее во мне");
                }}},
                {new Product() {{
                    setProductName("Мастер и Маргарита");
                }}},
        };
    }

}
